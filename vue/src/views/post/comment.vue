<template>
  <div class="app-container">
    <div class="filter-container">
      <el-form>
        <el-form-item>
          <div class="demo-input-suffix" style="float: left">
            评论人昵称：
            <el-input placeholder="请输入内容" style="width: 400px;" v-model="commentStarter"></el-input>
          </div>
          <div class="block" style="float: left">
            <div class="demo-input-suffix" style="float: left">
              关键词：
              <el-input placeholder="请输入查询关键词" style="width: 400px;" v-model="commentContent"></el-input>
            </div>
            <el-button type="primary" icon="plus" v-if="true" @click="getPostCommentList">查询</el-button>
          </div>
        </el-form-item>
      </el-form>
    </div>
    <el-table :data="list" v-loading.body="listLoading" element-loading-text="拼命加载中" border fit
              highlight-current-row>
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
          <img :src="scope.row.receiveImg == null ? 'http://img.since0592.com/static/1565848267.png':  scope.row.receiveImg" style="width: 60px; height: 60px;"/>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="content" label="内容" show-overflow-tooltip width="200"></el-table-column>
      <el-table-column align="center" label="评论时间" prop="createTime" width="155" v-if="true"></el-table-column>
      <el-table-column align="center" width="200" label="管理" v-if="true">
        <template slot-scope="scope">
          <el-tooltip content="删除" placement="bottom">
          <el-button type="danger" icon="el-icon-delete" plain @click="showDelete(scope.$index)"></el-button>
          </el-tooltip>
          <el-tooltip content="解禁" placement="bottom">
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
            <el-button  slot="reference" type="success" v-if="list[scope.$index].ifRes == 1" icon="el-icon-circle-check-outline" plain @click="list[scope.$index].fvisible = true"></el-button>
          </el-popover>
          </el-tooltip>
          <el-tooltip content="禁言" placement="bottom">
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
            <el-button slot="reference" type="danger" icon="el-icon-circle-close-outline" plain v-if="list[scope.$index].ifRes == 0" @click="list[scope.$index].visible = true"></el-button>
          </el-popover>
          </el-tooltip>
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
    name: "banner",
    data() {
      return {
        totalCount: 0, //分页组件--数据总条数
        list: [],//表格的数据
        listLoading: false,//数据加载等待动画
        listQuery: {
          pageNum: 1,//页码
          pageRow: 10,//每页条数

        },
        comment:{
          commentId:''
      },
        visible:false,
        resEndTime:'',
        resQuery:{
          userId:'',
          resEndTime:''
        },
        fvisible:false,
        commentContent:'',
        commentStarter:''
      }
    },
    created() {
      this.getPostCommentList();
    },
    computed: {
      ...mapGetters([
        'commentId'
      ])
    },
    methods: {
      getPostCommentList() {
        this.listLoading = true;
        this.listQuery.commentContent = this.commentContent;
        this.listQuery.commentStarter = this.commentStarter;
        console.log(this.listQuery)
        this.api({
          url: "/postComment/getCommentList",
          method: "get",
          params: this.listQuery
        }).then(data => {
          console.log(data)
          this.listLoading = false;
          this.list = data.list;
          for (var i =0 ;i<this.list.length; i++){
            this.list[i].createTime =  this.formatter(this.list[i].createTime, 'yyyy-MM-dd hh:mm:ss')
            if(this.list[i].receiveNickname =='null' && this.list[i].receiveImg =='null')
            {
              this.list[i].receiveNickname ='';
              this.list[i].receiveImg ='';
            }
          }
          this.totalCount = data.totalCount;
          this.listQuery.title = "";
        })
      },
      insertPostType() {
        this.listLoading = true;
        this.api({
          url: "/postType/insertPostType",
          method: "post",
          params: this.newPostType
        }).then(data => {
          this.dialogFormVisible = false
          this.getPostTypelist()
        })
      },

      beforeUpload(file) {
        const isJPG = file.type === 'image/jpeg';
        const isLt2M = file.size / 1024 / 1024 < 2;

        if (!isJPG) {
          this.$message.error('上传头像图片只能是 JPG 格式!');
        }
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 2MB!');
        }
        return isJPG && isLt2M;
      },
      handleAvatarSuccess(res, file) {
        this.postTypeImg = URL.createObjectURL(file.raw);
        this.newPostType.postTypeImg = res;
      },
      showCreate() {
        //显示新增对话框
        this.newPostType.postTypeName = "";
        this.newPostType.postTypeImg = "";
        this.dialogStatus = "create";
        this.dialogFormVisible = true;
      },
      /**
       * 删除条目
       */
      showDelete($index) {
        let comment = this.list[$index];
        this.comment.commentId = comment.commentId;

        this.$confirm('此操作将永久删除该文件,如需回复需联系管理员, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {

          this.api({
            url: "/postComment/updateDelCommentById",
            method: "post",
            params: this.comment
          }).then(data => {
            this.$message({
              type: 'success',
              message: '删除成功!'
            });
            this.getPostCommentList()
          })

        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      },
      showUpdate($index) {
        let postType= this.list[$index];
        this.newPostType.postTypeId = postType.postTypeId;
        this.newPostType.postTypeName = postType.postTypeName;
        this.newPostType.postTypeImg = postType.postTypeImg;
        this.dialogStatus = "update";
        this.dialogFormVisible = true;


      },
      updatePostType() {
        let postType = this;
        this.api({
          url: "/postType/updatePostType",
          method: "post",
          params: this.newPostType
        }).then(() => {
          this.getPostTypelist()
        })
      },
      /*帖子类型排序*/
      sortPostType($formerIndex, $laterIndex) {
        let formerPostType = this.list[$formerIndex];
        let laterPostType = this.list[$laterIndex];
        this.api({
          url: "/postType/sortPostType",
          method: "post",
          params: {
            formerTypeId: formerPostType.postTypeId,
            laterTypeId:laterPostType.postTypeId,
            formerSortTime: this.formatter(formerPostType.sortTime, 'yyyy-MM-dd hh:mm:ss'),
            laterSortTime: this.formatter(laterPostType.sortTime, 'yyyy-MM-dd hh:mm:ss')
          }
        }).then(() => {
          this.getPostTypelist()
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
      },
      handleSizeChange(val) {
        //改变每页数量
        this.listQuery.pageRow = val
        this.handleFilter();
      },
      handleCurrentChange(val) {
        //改变页码
        this.listQuery.pageNum = val
        this.getPostCommentList();
      },

      handleFilter() {
        //查询事件
        this.listQuery.pageNum = 1
        this.getPostCommentList()
      },
      getIndex($index) {
        //表格序号
        return (this.listQuery.pageNum - 1) * this.listQuery.pageRow + $index + 1
      },
      insertWxUserRes($index) {
        let _vue = this;
        _vue.list[$index].visible = false
        if(this.resEndTime==null){
          _vue.$message.error("未输入禁言结束时间!");
        }
        else {
          this.resQuery.userId = _vue.list[$index].startId;
          this.resQuery.resEndTime = this.formatter(this.resEndTime, 'yyyy-MM-dd hh:mm:ss')
        }
        this.api({
          url: "/userRes/insertWxUserRes",
          method: "post",
          params:this.resQuery
        }).then(data => {
          console.log(this.resQuery)
          _vue.getPostCommentList();
        }).catch(err => {
          console.log(err)
          _vue.getPostCommentList();
        })
      },
      endWxUserRes($index) {
        let _vue = this;
        this.resQuery.userId = _vue.list[$index].startId;
        _vue.list[$index].fvisible = false
        this.api({
          url: "/userRes/updateDelWxUserRes",
          method: "post",
          params:this.resQuery
        }).then(data => {
          console.log(this.resQuery)
          _vue.getPostCommentList();
        }).catch(err =>{
          console.log(err)
          _vue.getPostCommentList();
        })
      }
    },

  }
</script>

<style scoped>

</style>
