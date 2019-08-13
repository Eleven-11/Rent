<template>
  <div class="app-container">
    <div class="filter-container">
      <el-form>
        <el-form-item>
          <div class="demo-input-suffix" style="float: left">
            昵称：
            <el-input placeholder="请输入内容" style="width: 400px;" v-model="nickname"></el-input>
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
            <el-button type="primary" icon="plus" v-if="true" @click="getWxUserList">查询</el-button>
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
      <el-table-column align="center" label="微信昵称" prop="wxNickname" style="width: 20px;"></el-table-column>
      <el-table-column align="center" label="性别" prop="wxGender" style="width: 20px;"></el-table-column>
      <el-table-column align="center" label="地区" prop="region" style="width: 20px;"></el-table-column>
      <el-table-column align="center" label="注册时间" prop="createTime" width="170"></el-table-column>
      <el-table-column align="center" label="最近活跃时间" prop="updateTime" width="170"></el-table-column>
      <el-table-column align="center" label="用户ID" prop="wxUserId" style="width: 20px;" v-if="false"></el-table-column>
      <el-table-column align="center" label="管理" width="220" >
        <template slot-scope="scope">
          <el-popover
            placement="right"
            width="730"
            trigger="click">
            <el-table :data="gridData">
              <el-table-column width="80" property="poster" label="发帖人"></el-table-column>
              <el-table-column width="100" property="content" label="帖子内容"></el-table-column>
              <el-table-column align="center" label="图片" prop="image"  width="100">
                <template slot-scope="scope">
                  <img :src="scope.row.image" style="width: 60px; height: 60px;"/>
                </template>
              </el-table-column>
              <el-table-column width="150" property="collTime" label="收藏时间"></el-table-column>
              <el-table-column width="150" property="sortTime" label="排序时间" v-if="false"></el-table-column>
              <el-table-column align="center" label="帖子ID" prop="postId" style="width: 20px;"v-if="false"></el-table-column>
              <el-table-column align="center" label="管理" width="270" >
                <template slot-scope="scope">
                  <el-button type="primary" icon="detail" @click="showUpdate(scope.$index)" size="mini">详情</el-button>
                  <el-button type="primary" icon="up" @click="updateUserColl(scope.$index-1,scope.$index)" size="mini" v-if="(scope.$index)!=0">上移</el-button>
                  <el-button type="primary" icon="down" @click="updateUserColl(scope.$index,scope.$index+1)"size="mini"v-if="(scope.$index)!=gridData.length-1">下移</el-button>
                </template>
              </el-table-column>
            </el-table>
            <!--<el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page="gridData.pageNum"
              :page-size="gridData.pageRow"
              :total="totalCount"
              :page-sizes="[10, 20, 50, 100]"
              layout="total, sizes, prev, pager, next, jumper">
            </el-pagination>-->

          <el-button type="primary" v-if="scope.row.wxUserId!=wxUserId" @click="getUserCollList(scope.$index)" slot="reference" size="mini" round plain>收藏帖子</el-button>
          </el-popover>
          <el-popover
            placement="right"
              width="300"
              trigger="click">
              <el-table :data="gridData">
                <el-table-column width="150" property="follows" label="关注者"></el-table-column>
                <el-table-column width="150" property="fans" label="粉丝"></el-table-column>
              </el-table>

          <el-button type="primary" v-if="scope.row.wxUserId!=wxUserId "@click="getFoAndFan(scope.$index)" slot="reference" size="mini" round plain>粉丝&关注</el-button>
          </el-popover>
          <el-popover
            placement="top"
            width="300"
            v-model="fvisible">
            <p style="font-min-size: xx-small">限制用户后该用户将不能使用（发布、评论、私信、点赞功能），但可以浏览帖子和关注其他用户，您确定要限制该用户么？</p>
            <div style="text-align: right; margin: 0">
              <el-date-picker
                v-model="resEndTime"
                type="datetime"
                placeholder="选择禁言时间">
              </el-date-picker>
              <el-button size="mini" type="primary" @click="fvisible=false">取消</el-button>
              <el-button type="primary" size="mini" @click="updateWxUserRes(scope.$index)">确定</el-button>
            </div>
            <el-button type="primary" @click="fvisible = true" v-if="scope.row.wxUserId!=wxUserId" slot="reference" size="mini" round plain>限制用户</el-button>
          </el-popover>

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

  </div>
</template>
<script>
  import {mapGetters} from 'vuex'

  export default {
    data() {
      return {
        nickname: '',
        lists: '',
        totalCount: 0, //分页组件--数据总条数
        list: [],//表格的数据
        listLoading: false,//数据加载等待动画
        listQuery: {
          nickname: this.nickname,
          pageNum: 1,//页码
          pageRow: 50,//每页条数
          startTime:'',
          endTime:''
        },
        /*        roles: [],//角色列表*/
        dialogStatus: 'create',
        dialogFormVisible: false,
        tempWxUser: {
          wxUserId: '',
          nickname: '',
          gender: '',
          region: '',
          createTime: '',
          updateTime: ''
        },
        gridData: [{
          totalColl: 0,//分页组件--数据总条数
          pageNum: 1,//页码
          pageRow: 50,//每页条数
          poster: '',
          content: '',
          image: '',
          collTime: '',
          sortTime:'',
          follows: '',
          fans: '',
          postId:''
        }],
        input: '',
        fvisible:false,
        daterange: [new Date(2018, 16, 24, 10, 10), new Date()],
        resEndTime:'',
        startTime: '',
        endTime: '',
      }
    },
    created() {
      this.getWxUserList();
      if (this.hasPerm('wxUser:add') || this.hasPerm('wxUser:update')) {
        this.getWxUserList();
      }
    },
    computed: {
      ...mapGetters([
        'wxUserId'
      ])
    },
    methods: {
      /*获取用户列表*/
      getWxUserList() {
        this.listQuery.nickname = this.nickname
        //this.startTime = this.formatter(this.daterange[0], 'yyyy-MM-dd hh:mm:ss')
        this.listQuery.startTime = this.formatter(this.daterange[0], 'yyyy-MM-dd hh:mm:ss')
        //this.endTime = this.formatter(this.daterange[1], 'yyyy-MM-dd hh:mm:ss')
        this.listQuery.endTime = this.formatter(this.daterange[1], 'yyyy-MM-dd hh:mm:ss')
        this.api({
          url: "/bkWxUser/getWxUserlist",
          method: "get",
          params: this.listQuery
        }).then(data => {

          console.log( this.listQuery)
          this.listLoading = false;
          this.list = data.list;
          this.totalCount = data.totalCount;
        })
      },

      /*获取用户收藏帖子列表*/
      getUserCollList($index) {

        let user = this.list[$index];

        this.api({
          url: "/userCollection/getUserCollList",
          method: "get",
          params: {
            pageNum: this.gridData.pageNum,
            pageRow: this.gridData.pageRow,
            wxUserId: user.wxUserId
          }
        }).then(data => {
          console.log(user.wxUserId)
          this.listLoading = false;
          this.gridData = data.list;
          this.totalCount = data.totalCount
          console.log(this.gridData)

        })
      },
      /*用户收藏帖子上移下移*/
      updateUserColl($formerIndex,$laterIndex){
        let formerPost = this.gridData[$formerIndex];
        let laterPost = this.gridData[$laterIndex];
        this.api({
          url: "/userCollection/sortUserColl",
          method: "post",
          params: {
            formerPostId:formerPost.postId,
            laterPostId:laterPost.postId,
            formerSortTime: this.formatter(formerPost.sortTime, 'yyyy-MM-dd hh:mm:ss'),
            laterSortTime: this.formatter(laterPost.sortTime, 'yyyy-MM-dd hh:mm:ss')
          }
        }).then(()=>{
          console.log("hello")

        })


      },
      /*获取用户关注和粉丝列表*/
      getFoAndFan($index) {
        let user = this.list[$index];
        var p1 = new Promise((resolve, reject) => {
          getFollows($index);
        });
        var p2 = new Promise((resolve, reject) => {
          getFans($index);
        });

        Promise.all([p1, p2]).then(data => {
          console.log(data)
          this.gridData = data.list;
        })
        getFollows($index)
        {
          this.api({
            url: "/userFollow/getUserFollowList",
            method: "get",
            params: {
              pageNum: this.listQuery.pageNum,
              pageRow: this.listQuery.pageRow,
              wxUserId: user.wxUserId
            }
          }).then(data => {

            this.gridData = data.list;

          })
        }
        getFans($index)
        {
          this.api({
            url: "/userfollow/getUserFansList",
            method: "get",
            params: {
              pageNum: this.listQuery.pageNum,
              pageRow: this.listQuery.pageRow,
              wxUserId: user.wxUserId
            }
          }).then(data => {
            this.gridData = data.list;
          })
        }
      },
      /*用户权限限制*/
      updateWxUserRes($index)
      {
        let user = this.list[$index];
        this.api({
          url: "/userRes/updateWxUserRes",
          method: "post",
          params:{
            wxUserId:user.wxUserId,
            resEndTime:this.formatter(this.resEndTime, 'yyyy-MM-dd hh:mm:ss')
          }

        }).then(() => {

          visible= false;
          console.log("hello")

        })
      },




       /* getFollows($index)
        {
          this.api({
            url: "/wxUser/getUserFollowList",
            method: "get",
            params: {
              pageNum: this.listQuery.pageNum,
              pageRow: this.listQuery.pageRow,
              wxUserId: this.wxUserId
            }
          }).then(data => {
            console.log(this.listQuery.wxUserId)
            this.gridData = data.list;

          })
        },*/
        /*getFans($index){
        this.api({
            url: "/wxUser/getUserFansList",
            method: "get",
            params: {
              pageNum: this.listQuery.pageNum,
              pageRow: this.listQuery.pageRow,
              wxUserId: user.wxUserId
            }
          }).then(data => {
          console.log(this.listQuery.wxUserId)
          let promises = []

            this.gridData = data.list;
          this.listLoading = false;
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
        this.tempWxUser.wxUserId = wxUser.wxUserId;
        this.tempWxUser.nickname = wxUser.username;
        this.tempWxUser.gender = wxUser.nickname;
        this.tempWxUser.region = wxUser.roleId;
        this.tempWxUser.createTime = wxUser.createTime;
        this.tempWxUser.updateTime = wxUser.updateTime;
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
          data: this.wxUser
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
      formatter(thistime, fmt) {
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
    },
  }
  </script>
