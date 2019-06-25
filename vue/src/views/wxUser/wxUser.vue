<template>
  <div class="app-container">
    <div class="filter-container">
      <el-form>
        <el-form-item>
          <div class="demo-input-suffix" style="float: left">
            昵称：<el-input  placeholder="请输入内容" style="width: 400px;"  v-model="nickname"></el-input>
            最近活跃时间：
          </div>
          <div class="block" style="float: left">
            <el-date-picker
              v-model="daterange"
              type="daterange"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
              :default-time="['00:00:00', '23:59:59']">
            </el-date-picker>
            <el-button type="primary" icon="plus" v-if="hasPerm('wxuser:list')" @click="getWxUserList">查询</el-button>
          </div>
        </el-form-item>
      </el-form>
    </div>
    <el-table :data="list" v-loading.body="listLoading" element-loading-text="拼命加载中" border fit
              highlight-current-row>
      <el-table-column align="center" label="序号" width="80">
        <template slot-scope="scope">
          <span v-text="getIndex(scope.$index)"> </span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="微信昵称" prop="nickname" style="width: 20px;"></el-table-column>
      <el-table-column align="center" label="性别" prop="gender" style="width: 20px;"></el-table-column>
      <el-table-column align="center" label="地区" prop="region" style="width: 20px;"></el-table-column>
      <!--<el-table-column align="center" label="关注者" prop="followers" style="width: 20px;"></el-table-column>
      <el-table-column align="center" label="粉丝数量" prop="fans" style="width: 20px;"></el-table-column>-->
      <!--<el-table-column align="center" label="微信角色" width="100">
        <template slot-scope="scope">
          <el-tag type="success" v-text="scope.row.roleName" v-if="scope.row.roleId===1"></el-tag>
          <el-tag type="primary" v-text="scope.row.roleName" v-else></el-tag>
        </template>
      </el-table-column>-->
      <el-table-column align="center" label="注册时间" prop="createTime" width="170"></el-table-column>
      <el-table-column align="center" label="最近活跃时间" prop="updateTime" width="170"></el-table-column>
      <el-table-column align="center" label="管理" width="220" v-if="hasPerm('wxuser:check')">
        <template slot-scope="scope">
          <el-button type="primary" icon="detail">详情</el-button>
          <!--<el-button type="danger" icon="delete" v-if="scope.row.userId!=userId "
                     @click="removeUser(scope.$index)">删除
          </el-button>-->
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="listQuery.pageNum"
      :page-size="listQuery.pageRow"
      :total="totalCount"
      :page-sizes="[10, 20, 50, 100]"
      layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>
    <!--<el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form class="small-space" :model="tempUser" label-position="left" label-width="80px"
               style='width: 300px; margin-left:50px;'>
        <el-form-item label="用户名" required v-if="dialogStatus=='create'">
          <el-input type="text" v-model="tempUser.username">
          </el-input>
        </el-form-item>
        <el-form-item label="密码" v-if="dialogStatus=='create'" required>
          <el-input type="password" v-model="tempUser.password">
          </el-input>
        </el-form-item>
        <el-form-item label="新密码" v-else>
          <el-input type="password" v-model="tempUser.password" placeholder="不填则表示不修改">
          </el-input>
        </el-form-item>
        <el-form-item label="角色" required>
          <el-select v-model="tempUser.roleId" placeholder="请选择">
            <el-option
              v-for="item in roles"
              :key="item.roleId"
              :label="item.roleName"
              :value="item.roleId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="昵称" required>
          <el-input type="text" v-model="tempUser.nickname">
          </el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button v-if="dialogStatus=='create'" type="success" @click="createUser">创 建</el-button>
        <el-button type="primary" v-else @click="updateUser">修 改</el-button>
      </div>
    </el-dialog>-->
  </div>
</template>
<script>
  import {mapGetters} from 'vuex'

  export default {
    data() {
      return {
        nickname:'',
        totalCount: 0, //分页组件--数据总条数
        list: [],//表格的数据
        listLoading: false,//数据加载等待动画
        listQuery: {
          nickname:this.nickname,
          pageNum: 1,//页码
          pageRow: 50,//每页条数
          startTime:''
        },
        /*        roles: [],//角色列表*/
        dialogStatus: 'create',
        dialogFormVisible: false,
        tempWxUser: {
          nickname: '',
          gender: '',
          region: '',
          followers: '',
          fans: '',
          createTime:'',
          updateTime:''
        },
        input:'',
        daterange:[],
        startTime:'',
        endTime:''
      }
    },
    created() {
      this.getWxUserList();
      if (this.hasPerm('wxuser:add') || this.hasPerm('wxuser:update')) {
        this.getWxUserList();
      }
    },
    computed: {
      ...mapGetters([
        'wxuserId'
      ])
    },
    methods: {
      getWxUserList(){
        console.log("1111")
        this.listQuery.nickname = this.nickname
        this.startTime=this.formatter(this.daterange[0],'yyyy-MM-dd hh:mm:ss')
        this.endTime =this.formatter(this.daterange[1],'yyyy-MM-dd hh:mm:ss')

        console.log(this.endTime)
        console.log("222")
        this.api({
          url:"/wxuser/list",
          method:"get",
          params:this.listQuery
        }).then(data=> {
          this.listLoading = false;
          this.list = data.list;
          /*console.log(this.listQuery.daterange[0])*/
        })
      },

      /*getList() {
        //查询列表
        this.listLoading = true;
        this.api({
          url: "/wxuser/list",
          method: "get",
          params: this.listQuery
        }).then(data => {
          this.listLoading = false;
          this.list = data.list;
          this.totalCount = data.totalCount;
        })
      },*/
      handleSizeChange(val) {
        //改变每页数量
        this.listQuery.pageRow = val
        this.handleFilter();
      },
      handleCurrentChange(val) {
        //改变页码
        this.listQuery.pageNum = val
        this.getList();
      },
      handleFilter() {
        //查询事件
        this.listQuery.pageNum = 1
        this.getList()
      },
      getIndex($index) {
        //表格序号
        return (this.listQuery.pageNum - 1) * this.listQuery.pageRow + $index + 1
      },
      showCreate() {
        //显示新增对话框
        this.tempUser.username = "";
        this.tempUser.password = "";
        this.tempUser.nickname = "";
        this.tempUser.roleId = "";
        this.tempUser.userId = "";
        this.dialogStatus = "create"
        this.dialogFormVisible = true
      },
      showUpdate($index) {
        /*表格数据填充*/
        let wxUser = this.list[$index];
        this.tempWxUser.nickname = wxUser.username;
        this.tempWxUser.gender = wxUser.nickname;
        this.tempWxUser.region = wxUser.roleId;
        this.tempWxUser.createTime = wxUser.createTime;
        this.tempWxUser.updateTime = wxUser.updateTime;
       /* this.tempWxUser.followers = tempWxUser.followers;
        this.tempWxUser.fans = tempWxUser.fans;*/
        this.dialogStatus = "update"
        this.dialogFormVisible = true
      },
      createUser() {
        //添加新用户
        this.api({
          url: "/user/addUser",
          method: "post",
          data: this.tempUser
        }).then(() => {
          this.getWxUserList();
          this.dialogFormVisible = false
        })
      },
      updateUser() {
        //修改用户信息
        let _vue = this;
        this.api({
          url: "/user/updateUser",
          method: "post",
          data: this.wxuser
        }).then(() => {
          let msg = "修改成功";
          this.dialogFormVisible = false
          if (this.userId === this.tempWxUser.userId) {
            msg = '修改成功,部分信息重新登录后生效'
          }
          this.$message({
            message: msg,
            type: 'success',
            duration: 1 * 1000,
            onClose: () => {
              _vue.getWxUserList();
            }
          })
        })
      },
      removeUser($index) {
        let _vue = this;
        this.$confirm('确定删除此用户?', '提示', {
          confirmButtonText: '确定',
          showCancelButton: false,
          type: 'warning'
        }).then(() => {
          let user = _vue.list[$index];
          user.deleteStatus = '2';
          _vue.api({
            url: "/user/updateUser",
            method: "post",
            data: user
          }).then(() => {
            _vue.getWxUserList()
          }).catch(() => {
            _vue.$message.error("删除失败")
          })
        })
      },
      formatter (thistime, fmt) {
        let $this = new Date(thistime)
        let o = {
          'M+': $this.getMonth() + 1,
          'd+': $this.getDate(),
          'h+': $this.getHours(),
          'm+': $this.getMinutes(),
          's+': $this.getSeconds(),
          'q+': Math.floor(($this.getMonth() + 3) / 3),
          'S': $this.getMilliseconds()
        }
        if (/(y+)/.test(fmt)) {
          fmt = fmt.replace(RegExp.$1, ($this.getFullYear() + '').substr(4 - RegExp.$1.length))
        }
        for (var k in o) {
          if (new RegExp('(' + k + ')').test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? (o[k]) : (('00' + o[k]).substr(('' + o[k]).length)))
          }
        }
        return fmt
      }
    }
  }
</script>
