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
      <el-table-column align="center" label="粉丝量" prop="fans" style="width: 20px;" v-if="true"></el-table-column>
      <el-table-column align="center" label="用户简介" prop="summary" style="width: 100px"  show-overflow-tooltip></el-table-column>
      <el-table-column align="center" label="管理" width="220" >
        <template slot-scope="scope">
          <el-popover
            placement="right"
            width="730"
            trigger="click">
            <el-table :data="gridData">
              <el-table-column width="80" property="poster" label="发帖人"></el-table-column>
              <el-table-column width="100" property="content" show-overflow-tooltip label="帖子内容"></el-table-column>
              <el-table-column align="center" label="图片" prop="image"  width="100">
                <template slot-scope="scope">
                  <img :src="scope.row.image" style="width: 60px; height: 60px;"/>
                </template>
              </el-table-column>
              <el-table-column width="155" property="collTime" label="收藏时间"></el-table-column>
              <el-table-column width="155" property="sortTime" label="排序时间" v-if="false"></el-table-column>
              <el-table-column align="center" label="帖子ID" prop="postId" style="width: 20px;" v-if="false"></el-table-column>
              <el-table-column align="center" label="索引" prop="outIndex" style="width: 20px;" v-if="true"></el-table-column>
              <el-table-column align="center" label="管理" width="250" >
                <template slot-scope="scope">
                  <el-button type="primary" icon="up" @click="updateUserColl(scope.$index-1,scope.$index)" size="mini" v-if="(scope.$index)!=0">上移</el-button>
                  <el-button type="primary" icon="down" @click="updateUserColl(scope.$index,scope.$index+1)"size="mini"v-if="(scope.$index)!=gridData.length-1">下移</el-button>
                </template>
              </el-table-column>
            </el-table>
          <el-button type="primary" v-if="scope.row.wxUserId!=wxUserId" @click="getUserCollList(scope.$index)" slot="reference" size="mini" round plain>收藏帖子</el-button>
          </el-popover>
          <el-popover
            placement="right"
            width="220"
            trigger="click">
            <el-table :data="fanData">
              <el-table-column width="80" property="fanNickname" label="昵称"></el-table-column>
              <el-table-column align="center" label="头像" prop="fanAvatar"  width="100">
                <template slot-scope="scope">
                  <img :src="scope.row.fanAvatar" style="width: 60px; height: 60px;"/>
                </template>
              </el-table-column>
            </el-table>
            <el-button type="primary" v-if="scope.row.wxUserId!=wxUserId" @click="getFanList(scope.$index)" slot="reference" size="mini" round plain>粉丝列表</el-button>
          </el-popover>
          <el-popover
            placement="right"
            width="220"
            trigger="click">
            <el-table :data="followData">
              <el-table-column width="80" property="followNickname" label="昵称"></el-table-column>
              <el-table-column align="center" label="头像" prop="followAvatar"  width="100">
                <template slot-scope="scope">
                  <img :src="scope.row.followAvatar" style="width: 60px; height: 60px;"/>
                </template>
              </el-table-column>
            </el-table>
            <el-button type="primary" v-if="scope.row.wxUserId!=wxUserId" @click="getFollowList(scope.$index)" slot="reference" size="mini" round plain>关注列表</el-button>
          </el-popover>
          <el-tooltip content="编辑" placement="bottom">
            <el-button type="primary" @click="showUpdate(scope.$index)"size="mini" round plain>修改粉丝</el-button>
          </el-tooltip>
          <el-popover
          placement="top"
          width="300"
          v-model="list[scope.$index].fvisible">
          <p style="font-min-size: xx-small">限制用户后该用户将不能使用（发布、评论、私信、点赞功能），但可以浏览帖子和关注其他用户，您确定要限制该用户么？</p>
          <div style="text-align: right; margin: 0">
            <el-date-picker
              v-model="resEndTime"
              type="datetime"
              placeholder="选择禁言时间">
            </el-date-picker>
            <el-button size="mini" type="primary" @click="list[scope.$index].fvisible=false">取消</el-button>
            <el-button type="primary" size="mini" @click="insertWxUserRes(scope.$index)">确定</el-button>
          </div>
          <el-button type="danger" @click="list[scope.$index].fvisible = true" v-if="list[scope.$index].ifRes == 0" slot="reference" size="mini" round plain>限制用户</el-button>
        </el-popover>
          <el-popover
            placement="top"
            width="300"
            v-model="list[scope.$index].visible">
            <p style="font-min-size: xx-small">确认解除该用户禁言？</p>
            <el-button size="mini" type="primary" @click="list[scope.$index].visible=false">取消</el-button>
            <el-button type="primary" size="mini" @click="endWxUserRes(scope.$index)">确定</el-button>
            <el-button type="success" @click="list[scope.$index].visible = true" v-if="list[scope.$index].ifRes == 1" slot="reference" size="mini" round plain>解除限制</el-button>
          </el-popover>
          <el-dialog v-model="newWxUser" :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" >
            <el-form class="small-space" :model="wxUser" label-position="left" label-width="80px"
                     style='width: 300px; margin-left:50px;'>
              <el-form-item label="粉丝量">
                <el-input type="text" v-model="newWxUser.devFans">
                </el-input>
              </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button @click="dialogFormVisible = false">取 消</el-button>
              <el-button v-if="dialogStatus=='update'" type="success" @click="updateUserDev">修 改</el-button>
            </div>
          </el-dialog>

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
        resQuery: {
          userId: '',
          resEndTime: ''
        },
        listQuery: {
          nickname: this.nickname,
          pageNum: 1,//页码
          pageRow: 50,//每页条数
          startTime: '',
          endTime: ''
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
        wxUser:{
          userId:'',
          devFans:'',
        },
        textMap: {
          update: '编辑',
          create: '新建'
        },
        newWxUser:{
          devFans:'',
          userId:''
        },
        resEndTime: '',
        gridData: [{
          totalColl: 0,//分页组件--数据总条数
          pageNum: 1,//页码
          pageRow: 50,//每页条数
          poster: '',
          content: '',
          image: '',
          collTime: '',
          sortTime: '',
          follows: '',
          fans: '',
          postId: '',
          outIndex:''
        }],
        fanquery:{
          wxUserId:''
        },
        fanData: [{
          fanNickname: '',
          fanAvatar: ''
        }],
        followData: [{
          followNickname: '',
          followAvatar: ''
        }],
        input: '',
        fvisible: false,
        visible: false,
        daterange: [new Date(2018, 16, 24, 10, 10), new Date()],
        startTime: '',
        endTime: '',
      }
    },
    created() {
      this.getWxUserList();
      /* if (this.hasPerm('wxUser:add') || this.hasPerm('wxUser:update')) {
        this.getWxUserList();
      }*/
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
        this.listQuery.startTime = this.formatter(this.daterange[0], 'yyyy-MM-dd hh:mm:ss')
        this.listQuery.endTime = this.formatter(this.daterange[1], 'yyyy-MM-dd hh:mm:ss')
        this.api({
          url: "/bkWxUser/getWxUserlist",
          method: "get",
          params: this.listQuery
        }).then(data => {
          this.listLoading = false;
          this.list = data.list;
          console.log(this.list)
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
          for (var i = 0;i<data.list.length;i++){
            this.gridData[i].outIndex = $index
          }
          this.totalCount = data.totalCount
          console.log(this.gridData)

        })
      },
      /*用户收藏帖子上移下移*/
      updateUserColl($formerIndex, $laterIndex) {
        let formerPost = this.gridData[$formerIndex];
        let laterPost = this.gridData[$laterIndex];
        this.api({
          url: "/userCollection/sortUserColl",
          method: "post",
          params: {
            formerPostId: formerPost.postId,
            laterPostId: laterPost.postId,
            formerSortTime: this.formatter(formerPost.sortTime, 'yyyy-MM-dd hh:mm:ss'),
            laterSortTime: this.formatter(laterPost.sortTime, 'yyyy-MM-dd hh:mm:ss')
          }
        }).then(() => {
          this.getUserCollList(this.gridData[$formerIndex].outIndex);
          console.log("hello")

        })
      },
      /*修改用户粉丝量*/
      updateUserDev(formName) {
        this.newWxUser.userId = this.wxUser.userId
        console.log(this.newWxUser)
        this.listLoading = true;
        this.api({
          url: "/bkWxUser/updateWxUserInfo",
          method: "post",
          params: this.newWxUser
        }).then(() => {
          let msg = "修改成功";
          //隐藏面板
          this.dialogFormVisible = false;
          this.$message({
            message: msg, type: 'success', duration: 1 * 1000,
          })
          this.getWxUserList()
        })
      },
      /*用户权限限制*/
      updateWxUserRes($index) {
        let user = this.list[$index];
        this.api({
          url: "/userRes/insertWxUserRes",
          method: "post",
          params: {
            wxUserId: user.wxUserId,
            resEndTime: this.formatter(this.resEndTime, 'yyyy-MM-dd hh:mm:ss')
          }

        }).then(() => {

          visible = false;
          console.log("hello")

        })
      },
      insertWxUserRes($index) {
        let _vue = this;
        _vue.list[$index].fvisible = false
        if (this.resEndTime == null) {
          _vue.$message.error("请输入禁言结束时间");
        } else {
          this.resQuery.userId = _vue.list[$index].wxUserId;
          this.resQuery.resEndTime = this.formatter(this.resEndTime, 'yyyy-MM-dd hh:mm:ss')
        }
        this.api({
          url: "/userRes/insertWxUserRes",
          method: "post",
          params: this.resQuery
        }).then(data => {
          console.log(this.resQuery)
          _vue.getWxUserList();
        }).catch(err => {
          console.log(err)
          _vue.getWxUserList();
        })
      },
      endWxUserRes($index) {
        let _vue = this;
        this.resQuery.userId = _vue.list[$index].wxUserId;
        _vue.list[$index].visible = false
        this.api({
          url: "/userRes/updateDelWxUserRes",
          method: "post",
          params: this.resQuery
        }).then(data => {
          console.log(this.resQuery)
          _vue.getWxUserList();
        }).catch(err => {
          console.log(err)
          _vue.getWxUserList();
        })
      },
        getFanList($index){
        this.fanquery.wxUserId = this.list[$index].wxUserId;
        this.api({
          url:"/userFollow/getUserFansList",
          method: "get",
          params: this.fanquery
        }).then(data => {
          // console.log(user.list[$index].wxUserId)
          // this.listLoading = false;
          this.fanData = data.list;
          console.log(this.fanData)
        }).catch(err =>{
          console.log(err)
        })
      },
      getFollowList($index){
        this.fanquery.wxUserId = this.list[$index].wxUserId;
        this.api({
          url:"/userFollow/getUserFollowList",
          method: "get",
          params: this.fanquery
        }).then(data => {
          // console.log(user.list[$index].wxUserId)
          // this.listLoading = false;
          this.followData = data.list;
          console.log(this.followData)
        }).catch(err =>{
          console.log(err)
        })
      },
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
        this.wxUser.userId = wxUser.wxUserId;
        this.wxUser.devFans = wxUser.devFans;
        /*this.tempWxUser.nickname = wxUser.username;
        this.tempWxUser.gender = wxUser.nickname;
        this.tempWxUser.region = wxUser.roleId;
        this.tempWxUser.createTime = wxUser.createTime;
        this.tempWxUser.updateTime = wxUser.updateTime;*/
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
    }
  }
  </script>
