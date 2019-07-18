<template>
  <div class="app-container">
    <div class="filter-container">
      <el-form>
        <el-form-item>
          <el-button type="primary" icon="plus" v-if="hasPerm('user:add')" @click="showCreate">添加
          </el-button>
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
      <el-table-column align="center" label="发帖人" prop="poster" style="width: 60px;"></el-table-column>
      <el-table-column align="center" label="发帖人头像" prop="posterAvatar" style="width: 100px;"></el-table-column>
      <el-table-column align="center" label="帖子Id" prop="postId" style="width: 60px;"></el-table-column>
      <el-table-column align="center" label="帖子创建时间" prop="createTime" style="width: 60px;"></el-table-column>
      <el-table-column align="center" label="地址" prop="address" style="width: 60px;"></el-table-column>
      <el-table-column align="center" label="帖子内容" prop="content" style="width: 60px;"></el-table-column>
      <el-table-column align="center" label="最低价" prop="minPrice" style="width: 60px;"></el-table-column>
      <el-table-column align="center" label="最高价" prop="maxPrice" style="width: 60px;"></el-table-column>
      <el-table-column align="center" label="联系方式" prop="phone" style="width: 120px;"></el-table-column>
      <el-table-column align="center" label="浏览量" prop="browse" style="width: 60px;"></el-table-column>
      <el-table-column align="center" label="评论数量" prop="comments" style="width: 60px;"></el-table-column>
      <el-table-column align="center" label="帖子点赞数量" prop="likes" style="width: 60px;"></el-table-column>
      <el-table-column align="center" label="中介费" prop="fee" style="width: 60px;"></el-table-column>
      <el-table-column align="center" label="近期活跃时间" prop="activeTime" style="width: 60px;"></el-table-column>
      <el-table-column align="center" label="管理" width="220">
        <template slot-scope="scope">
          <el-button type="primary" icon="edit" @click="showUpdate(scope.$index)">修改</el-button>
          <el-button type="danger" icon="delete" v-if="scope.row.userId!=userId "
                     @click="removeUser(scope.$index)">删除
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
      :page-sizes="[10, 20, 50, 100]"
      layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form class="small-space" :model="tempPost" label-position="left" label-width="80px"
               style='width: 300px; margin-left:50px;'>
        <el-form-item label="帖子内容">
          <el-input type="text" v-model="tempPost.content" placeholder="不填则表示不修改">
          </el-input>
        </el-form-item>
        <el-form-item label="联系方式">
          <el-input type="text" v-model="tempPost.phone" placeholder="不填则表示不修改">
          </el-input>
        </el-form-item>
        <el-form-item label="浏览量" >
          <el-input type="text" v-model="tempPost.browse" placeholder="不填则表示不修改">
          </el-input>
        </el-form-item>
        <el-form-item label="点赞量" >
          <el-input type="text" v-model="tempPost.likes" placeholder="不填则表示不修改">
          </el-input>
        </el-form-item>
        <el-form-item label="地址" >
          <el-input type="text" v-model="tempPost.address" placeholder="不填则表示不修改">
          </el-input>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button v-if="dialogStatus=='create'" type="success" @click="createUser">创 建</el-button>
        <el-button type="primary" v-else @click="updateUser">修 改</el-button>
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
          address: ''
        }
      }
    },
    created() {
      this.getList();
      if (this.hasPerm('user:add') || this.hasPerm('user:update')) {
        this.getAllRoles();
      }
    },
    computed: {
      ...mapGetters([
        'userId'
      ])
    },
    methods: {
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
          console.log( this.list)

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
          url: "/user/updateUser",
          method: "post",
          data: this.tempPost
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
            _vue.getList()
          }).catch(() => {
            _vue.$message.error("删除失败")
          })
        })
      },
    }
  }
</script>
