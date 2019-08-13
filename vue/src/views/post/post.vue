<template>
  <div class="app-container">
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
      <el-table-column align="center" label="帖子创建时间" prop="createTime" width="220"></el-table-column>
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
      <el-table-column align="center" label="上架状态" prop="isLowerShelf" width="220" ></el-table-column>
      <el-table-column align="center" label="管理" width="220">
        <template slot-scope="scope">
        <el-button type="primary" icon="delete"  size="mini" v-if="list[scope.$index].isLowerShelf == '上架'"
                     @click="setOnShelf(scope.$index)">下架
        </el-button>
          <el-button type="primary" icon="delete"  size="mini" v-if="list[scope.$index].isLowerShelf == '下架'"
                     @click="setOnShelf(scope.$index)">上架
          </el-button>
        <el-button type="primary"  size="mini" icon="edit" @click="showUpdate(scope.$index)">修改</el-button>
        <el-button type="danger" icon="delete" size="mini" v-if="list[scope.$index].isDel == '未删除'"
                   @click="removePost(scope.$index)">删除
        </el-button>
        <el-button type="success" size="mini" icon="delete" v-else
                   @click="recoverPost(scope.$index)">恢复
        </el-button>
      </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="listQuery.pageNum"
      :page-size="listQuery.pageRow"
      :total="totalCount"
      :page-sizes="[5, 10, 20, 50, 100]"
      layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form class="small-space" :model="tempPost" label-position="left" label-width="80px"
               style='width: 300px; margin-left:50px;'>
        <el-form-item label="浏览量" >
          <el-input type="text" v-model="tempPost.devBrowse" placeholder="不填则表示不修改">
          </el-input>
        </el-form-item>
        <el-form-item label="点赞量" >
          <el-input type="text" v-model="tempPost.devLike" placeholder="不填则表示不修改">
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
        listQuery: {
          pageNum: 1,//页码
          pageRow: 50,//每页条数
        },
        roles: [],//角色列表
        dialogStatus: 'create',
        dialogFormVisible: false,
        textMap: {
          update: '编辑',
          create: '新建用户'
        },
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
    },
    computed: {
      ...mapGetters([
        'userId'
      ])
    },
    methods: {
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


      getAllRoles() {
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
        this.api({
          url: "/postBase/getPostBaseList",
          method: "get",
          params: this.listQuery
        }).then(data => {
          this.listLoading = false;
          this.list = data.list;
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
          vue.$message.success("恢复成功")

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

    }
  }
</script>
