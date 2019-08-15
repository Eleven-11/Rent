<template>
  <div class="app-container">
    <div class="filter-container">
      <el-form>
        <el-form-item>
          <div class="demo-input-suffix" style="float: left">
            发帖人：
            <el-input placeholder="请输入内容" style="width: 400px;" v-model="nickname"></el-input>
          </div>
            帖子类型：
          <el-select v-model="selector" style="width: 400px;" filterable placeholder="请选择类型" @change="selectTemp($event)">
            <el-option
              v-for="item in options"
              :key="item.postTypeId"
              :label="item.postTypeName"
              :value="item.postTypeId">
            </el-option>
          </el-select>
          <div class="block" style="float: left">
            活跃时间：
            <el-date-picker
              v-model="daterange"
              type="daterange"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
              style="width: 400px;"
              :default-time="['00:00:00', '23:59:59']">
            </el-date-picker>
            <div class="demo-input-suffix" style="float: left">
              关键词：
              <el-input placeholder="请输入查询关键词" style="width: 400px;" v-model="keyword"></el-input>
            </div>


            <el-button type="primary" icon="plus" v-if="true" @click="getList">查询</el-button>
          </div>
        </el-form-item>
      </el-form>
    </div>
    <el-table :data="list" v-loading.body="listLoading" element-loading-text="拼命加载中" border fit
              highlight-current-row>
      <el-table-column align="center" label="序号" width="60">
        <template slot-scope="scope">
          <span v-text="getIndex(scope.$index)"> </span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="发帖人" prop="poster" width="100"></el-table-column>
      <el-table-column align="center" label="发帖人头像" prop="posterAvatar"  width="100">
        <template slot-scope="scope">
          <img :src="scope.row.posterAvatar" style="width: 60px; height: 60px;"/>
        </template>
      </el-table-column>
      <el-table-column align="center" label="帖子Id" prop="postId" width="90" v-if="false"></el-table-column>
      <el-table-column align="center" label="发布时间" prop="createTime" width="220"></el-table-column>
      <el-table-column align="center" label="地址" prop="address"  width="120"></el-table-column>
      <el-table-column align="center" label="帖子内容" prop="content" width="200" ></el-table-column>
      <el-table-column align="center" label="最低价" prop="minPrice" ></el-table-column>
      <el-table-column align="center" label="最高价" prop="maxPrice" ></el-table-column>
      <el-table-column align="center" label="联系方式" prop="phone" width="160"></el-table-column>
      <el-table-column align="center" label="浏览量" prop="browse" ></el-table-column>
      <el-table-column align="center" label="评论数量" prop="comments" ></el-table-column>
      <el-table-column align="center" label="点赞数量" prop="likes" ></el-table-column>
      <el-table-column align="center" label="中介费" prop="fee" ></el-table-column>
      <el-table-column align="center" label="发帖人id" prop="userId" v-if="false"></el-table-column>
      <el-table-column align="center" label="是否删除" prop="isDel" v-if="true"></el-table-column>
      <el-table-column align="center" label="近期活跃时间" prop="activeTime" width="220" ></el-table-column>
      <el-table-column align="center" label="上架状态" prop="isLowerShelf" width="220" v-if="false"></el-table-column>
      <el-table-column align="center" label="禁言状态" prop="ifRes" width="220" v-if="true" ></el-table-column>
      <el-table-column align="center" label="管理" width="220">
        <template slot-scope="scope">
          <el-popover
            placement="right"
            width="730"
            trigger="click">
            <el-table :data="commentData">
              <el-table-column fixed="left" align="center" label="序号" width="80">
                <template slot-scope="scope">
                  <span v-text="getIndex(scope.$index)"> </span>
                </template>
              </el-table-column>
              <el-table-column align="center" label="评论id" prop="commentId" v-if="false"></el-table-column>

              <el-table-column align="center" prop="startNickname" label="昵称" width="200"></el-table-column>
              <el-table-column align="center" prop="startImg" label="头像" width="100">
                <template slot-scope="scope">
                  <img :src="scope.row.startImg" style="width: 60px; height: 60px;"/>
                </template>
              </el-table-column>
              <el-table-column align="center" prop="receiveNickname" label="被回复人昵称" width="200"></el-table-column>
              <el-table-column align="center" prop="receiveImg" label="被回复人头像" width="150">
                <template slot-scope="scope">
                  <img :src="scope.row.receiveImg == null ? 'http://192.168.1.7:8080/image/static/1565848267.png':  scope.row.receiveImg" style="width: 60px; height: 60px;"/>
                </template>
              </el-table-column>
              <el-table-column align="center" prop="content" label="内容" width="200"></el-table-column>
              <el-table-column align="center" label="评论时间" prop="createTime" width="155" v-if="true"></el-table-column>
            </el-table>
            <el-button type="primary" @click="getPostCommentList(scope.$index)" slot="reference" size="mini" plain>评论</el-button>
          </el-popover>
          <el-button type="info" plain icon="delete"  size="mini" v-if="list[scope.$index].isLowerShelf == '上架'"
                     @click="setOnShelf(scope.$index)">下架
          </el-button>
          <el-button type="primary" plain icon="delete"  size="mini" v-if="list[scope.$index].isLowerShelf == '下架'"
                     @click="setOnShelf(scope.$index)">上架
          </el-button>
          <el-button type="primary" plain  size="mini" icon="edit" @click="showUpdate(scope.$index)">修改</el-button>
          <el-button type="danger" plain icon="delete" size="mini" v-if="list[scope.$index].isDel == '未删除'"
                     placement="bottom"  @click="removePost(scope.$index)">删除
          </el-button>
          <el-button type="success" plain size="mini" icon="delete" v-else
                   @click="recoverPost(scope.$index)">恢复
          </el-button>
          <el-button type="success" plain size="mini" icon="delete" v-else
                     @click="setOnTop(scope.$index)">置顶
          </el-button>
          <el-popover
            placement="bottom"
            width="300"
            v-model="list[scope.$index].fvisible"
            trigger="click">
            <div style="text-align: right; margin: 0">
              <p style="font-min-size: xx-small">是否解除该用户禁言?</p>
              <el-button size="mini" type="primary" @click="list[scope.$index].fvisible = false">取消</el-button>
              <el-button type="primary" size="mini" @click="endWxUserRes(scope.$index)">确定</el-button>
            </div>
            <el-button  slot="reference" type="success" plain v-if="list[scope.$index].ifRes == 1" icon="delete" size="mini" @click="list[scope.$index].fvisible = true">解禁</el-button>
          </el-popover>
          <el-popover
            placement="bottom"
            width="300"
            v-model="list[scope.$index].visible"
            trigger="click">
            <div style="text-align: right; margin: 0">
              <p style="font-min-size: xx-small">限制用户后该用户将不能使用（发布、评论、私信、点赞功能），但可以浏览帖子和关注其他用户，您确定要限制该用户么？</p>
              <el-date-picker
                v-model="resEndTime"
                type="datetime"
                placeholder="选择禁言时间">
              </el-date-picker>
              <el-button size="mini" type="primary" @click="list[scope.$index].visible = false">取消</el-button>
              <el-button type="primary" size="mini" @click="insertWxUserRes(scope.$index)">确定</el-button>
            </div>
            <el-button  slot="reference" type="danger" plain v-if="list[scope.$index].ifRes == 0" icon="delete" size="mini" @click="list[scope.$index].visible = true">封禁</el-button>
          </el-popover>
      </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="gridData.pageNum"
      :page-size="gridData.pageRow"
      :total="totalCount"
      :page-sizes="[10, 20, 50, 100]"
      layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form class="small-space" :model="tempPost" label-position="left" label-width="80px"
               style='width: 300px; margin-left:50px;'>
        <el-form-item label="浏览量" >
          <el-input type="text"v-if="dialogStatus ='update'" v-model="tempPost.devBrowse" placeholder="不填则表示不修改">
          </el-input>
        </el-form-item>
        <el-form-item label="点赞量" >
          <el-input type="text" v-if="dialogStatus ='update'" v-model="tempPost.devLike" placeholder="不填则表示不修改">
          </el-input>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <!--<el-button v-if="dialogStatus=='create'" type="success" @click="createUser">创 建</el-button>-->
        <el-button type="primary" @click="updateUser">修 改</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
  import {mapGetters} from 'vuex'

  export default {
    data() {
      return {
        totalCount: 0, //分页组件--数据总条数
        list: [],//表格的数据
        listLoading: false,//数据加载等待动画
        manage:'',
        listQuery: {
          pageNum: 1,//页码
          pageRow: 50,//每页条数
          nickname:this.nickname,
          startTime:'',
          endTime:'',
          typeId:'',
          keyword:'',
          postId:''
        },
        resQuery:{
          userId:'',
          resEndTime:'',
        },
        gridData: [{
          totalCount: 0,//分页组件--数据总条数
          pageNum: 1,//页码
          pageRow: 50,//每页条数
        }],
        checkAll: false,
        navigationTopTitles:[{
          navigationTopId:'',
          navigationTopTitle:''
        }],
        checkedTitles:[],
        // titles: navigationTopTitles,
        isIndeterminate: true,
        selector:'',
        options:[{
          postTypeId:'',
          postTypeName:'',
          postTypeImg:''

        }],
        daterange: [new Date(2018, 16, 24, 10, 10), new Date()],
        visible:false,
        resEndTime:'',
        roles: [],//角色列表
        dialogStatus: 'create',
        dialogFormVisible: false,
        textMap: {
          top:'置顶',
          update: '编辑',
          create: '新建用户'
        },
        commentData:[],
        tempPost: {
          content: '',
          phone: '',
          browse: '',
          likes: '',
          address: '',
          userId:'',
          devBrowse:'',
          devLike:'',
          postId:'',
          isDel:''
        }
      }
    },
    created() {
      this.getList();
      this.getPostTypeList();
    },
    computed: {
      ...mapGetters([
        'userId'
      ])
    },
    methods: {
      /**
       * 模版选中后触发
       */
      selectTemp($event){
        console.log($event)
        this.listQuery.typeId = $event
        // this.selector = $event.selector.postTypeName;
        // console.log(this.selector)
        // this.listQuery.typeId = $event.postTypeId;
        // console.log(this.listQuery.typeId)
      },
      //时间搓转化
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
      },
      getPostCommentList($index) {
        this.listLoading = false;
        this.listQuery.postId = this.list[$index].postId;
        this.api({
          url: "/postComment/getCommentList",
          method: "get",
          params: this.listQuery
        }).then(data => {
          this.listLoading = false;
          this.commentData = data.list;
          for (var i =0 ;i<this.commentData.length; i++){
            this.commentData[i].createTime =  this.formatter(this.commentData[i].createTime, 'yyyy-MM-dd hh:mm:ss')
            if(this.commentData[i].receiveNickname =='null' && this.commentData[i].receiveImg =='null')
            {
              this.commentData[i].receiveNickname ='';
              this.commentData[i].receiveImg ='';
            }
          }
        })
      },
      getPostTypeList() {
        this.api({
          url: "/postType/getPostTypelist",
          method: "get"
        }).then(data => {
          console.log(data)
          this.options = data.list;
          /*this.selector.key = this.options.postTypeId;
          this.selector.value = this.options.postTypeName;
          this.options.add("HDKSH");
          this.options.add("DHJSKHF")*/
        })

      },

      getNavigationTitle(){
        this.api({
          url: "/navigationTop/getNavigationTitle",
          method: "get"
        }).then(data => {
          console.log(data)
          this.navigationTopTitles = data.list;
        });
      },

      getAllRoles(){
        this.api({
          url: "/user/getAllRoles",
          method: "get"
        }).then(data => {
          this.roles = data.list;
        })
      },
      getList() {
        //查询列表
        this.listLoading = true;
        this.listQuery.nickname = this.nickname
        this.listQuery.keyword = this.keyword
        if(this.daterange!=null){
          this.listQuery.startTime = this.formatter(this.daterange[0], 'yyyy-MM-dd hh:mm:ss')
          this.listQuery.endTime = this.formatter(this.daterange[1], 'yyyy-MM-dd hh:mm:ss')
        }
        this.api({
          url: "/postBase/getPostBaseList",
          method: "get",
          params: this.listQuery
        }).then(data => {
          this.listLoading = false;
          this.list = data.list;
          console.log(this.list)
          for (var i =0 ;i<this.list.length; i++){
            if (this.list[i].minPrice == '' || this.list[i].minPrice == 'null' ||this.list[i].minPrice == 0) {
              this.list[i].minPrice = '无最低价'
            }
            if(this.list[i].maxPrice == ''|| this.list[i].maxPrice == 'null' ||this.list[i].maxPrice == 0){
              this.list[i].maxPrice = '无最高价'
            }
            if (this.list[i].fee == '0') {
              this.list[i].fee = '无中介费'
            }else{
              this.list[i].fee = '有中介费'
            }
            if (this.list[i].isDel == '0') {
              this.list[i].isDel = '未删除'
            }else{
              this.list[i].isDel = '已删除'
            }
            if (this.list[i].isLowerShelf == '0') {
              this.list[i].isLowerShelf = '下架'
            }else{
              this.list[i].isLowerShelf = '上架'
            }
            this.list[i].createTime = this.formatter(this.list[i].createTime,'yyyy-MM-dd hh:mm:ss')
            this.list[i].activeTime = this.formatter(this.list[i].activeTime,'yyyy-MM-dd hh:mm:ss')
            this.list[i].content = this.list[i].content.substring(0,38)
          }
          this.totalCount = data.totalCount;
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
        this.tempPost.content = "";
        this.tempPost.phone = "";
        this.tempPost.browse = "";
        this.tempPost.likes = "";
        this.tempPost.address = "";
        this.dialogStatus = "create"
        this.dialogFormVisible = true
      },
      showUpdate($index) {
        let user = this.list[$index];
        this.tempPost.content = user.content;
        this.tempPost.phone = user.phone;
        this.tempPost.browse = user.browse;
        this.tempPost.likes = user.likes;
        this.tempPost.address = user.address;
        this.tempPost.postId = user.postId;
        this.tempPost.deleteStatus = '1';
        this.dialogStatus = "update"
        this.dialogFormVisible = true
        console.log( this.tempPost)
      },
      setOnTop($index) {
        this.dialogStatus = "top";
        this.dialogFormVisible = true
        this.getNavigationTitle();
      },
      createUser() {
        //添加新用户
        this.api({
          url: "/user/addUser",
          method: "post",
          data: this.tempPost
        }).then(() => {
          this.getList();
          this.dialogFormVisible = false
        })
      },
      updateUser() {
        //修改用户信息
        let _vue = this;
        this.api({
          url: "/postBase/updatePostDev",
          method: "post",
          params: this.tempPost
        }).then(() => {
          let msg = "修改成功";
          this.dialogFormVisible = false
          if (this.userId === this.tempPost.userId) {
            msg = '修改成功,部分信息重新登录后生效'
          }
          this.$message({
            message: msg,
            type: 'success',
            duration: 1 * 1000,
            onClose: () => {
              _vue.getList();
            }
          })
        })
      },
      removePost($index) {
        let _vue = this;
        this.$confirm('确定删除该帖子?', '提示', {
          confirmButtonText: '确定',
          showCancelButton: false,
          type: 'warning'
        }).then(() => {
          let user = _vue.list[$index];
          //user.deleteStatus = '2';
          _vue.api({
            url: "/postBase/updateDelPost",
            method: "post",
            params: user
          })
      }).then(() => {
          _vue.getList()
        }).catch(() => {
          _vue.$message.error("删除失败")
        })
      },
      recoverPost($index) {
        let _vue = this;
        this.$confirm('确定恢复该帖子?', '提示', {
          confirmButtonText: '确定',
          showCancelButton: true,
          type: 'warning'
        }).then(() => {
          let post = _vue.list[$index];
          //post.deleteStatus = '0';
          _vue.api({
            url: "/postBase/recoverPost",
            method: "post",
            params: post
          })
        }).then((res) => {
          _vue.getList()
          //_vue.$message.success("恢复成功")

        }).catch(() => {
          _vue.$message.error("恢复失败")
        })
      },
      setOnShelf($index) {
        let _vue = this;
        this.$confirm('是否确定更改帖子上架状态?', '提示', {
          confirmButtonText: '确定',
          showCancelButton: true,
          type: 'warning'
        }).then(() => {
          let post = _vue.list[$index];
          //post.deleteStatus = '0';
          _vue.api({
            url: "/postBase/updateOnShelf",
            method: "post",
            params: post
          })
        }).then((res) => {
          _vue.getList()
          _vue.$message.success("设置成功")
        }).catch((res) => {
          console.log(res)
          _vue.$message.error("设置失败")
        })
      },
      handleCheckAllChange(val) {
        this.checkedTitles = val? navigationTopTitles:[];
        this.isIndeterminate = false;
      },
      handleCheckedTitleChange(value) {
        let checkedCount = value.length;
        this.checkAll = checkedCount === this.navigationTopTitles.length;
        this.isIndeterminate = checkedCount > 0 && checkedCount < this.navigationTopTitles.length;
      },
      insertWxUserRes($index) {
        let _vue = this;
        _vue.list[$index].visible = false
        if(this.resEndTime==null){
          _vue.$message.error("请输入禁言结束时间");
        }
        else {
          this.resQuery.userId = _vue.list[$index].userId;
          this.resQuery.resEndTime = this.formatter(this.resEndTime, 'yyyy-MM-dd hh:mm:ss')
        }
        this.api({
          url: "/userRes/insertWxUserRes",
          method: "post",
          params:this.resQuery
        }).then(data => {
          console.log(this.resQuery)
          _vue.getList();
        }).catch(err => {
          console.log(err)
          _vue.getList();
        })
        },
      endWxUserRes($index) {
        let _vue = this;
        this.resQuery.userId = _vue.list[$index].userId;
        _vue.list[$index].fvisible = false
        this.api({
          url: "/userRes/updateDelWxUserRes",
          method: "post",
          params:this.resQuery
        }).then(data => {
          console.log(this.resQuery)
          _vue.getList();
        }).catch(err =>{
          console.log(err)
          _vue.getList();
        })
      }

    }
  }
</script>
