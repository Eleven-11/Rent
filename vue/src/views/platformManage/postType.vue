 <template>
  <div class="app-container">
    <div class="filter-container">
      <el-form>
        <el-form-item>
          <el-button type="primary" icon="plus" @click="showCreate">添加
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
      <el-table-column align="center" label="id" prop="postTypeId" v-if="false" style="width: 60px;"></el-table-column>
      <el-table-column align="center" label="名称" prop="postTypeName"  width="220" style="width: 60px;"></el-table-column>
      <el-table-column label="图片" min-width="20%"  align="center">
        <!-- 图片的显示 -->
        <template   slot-scope="scope" >
          <img :src="scope.row.postTypeImg"  min-width="70" height="70"/>
        </template>
      </el-table-column>
      <el-table-column align="center" label="管理" width="220">
        <template slot-scope="scope">
          <el-button type="primary" icon="edit" @click="updatePostType(scope.$index)">修改</el-button>
          <el-button type="danger" icon="delete" v-if="scope.row.postTypeId!=postTypeId "
                     @click="deletePostType(scope.$index)">删除
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
<el-dialog v-model="newPostType" :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form class="small-space" :model="postType" label-position="left" label-width="80px"
               style='width: 300px; margin-left:50px;'>
        <el-form-item label="类型名称" required v-if="dialogStatus=='create'">
          <el-input type="text" v-model="postType.postTypeName">
          </el-input>
        </el-form-item>
        <el-form-item label="创建时间" required v-if="dialogStatus=='create'">
          <div class="block">
            <el-date-picker
              v-model="postType.postTypeCreateTime"
              type="datetime"
              placeholder="选择日期时间">
            </el-date-picker>
          </div>
        </el-form-item>
        <el-form-item label="类型图标" required v-if="dialogStatus=='create'">
        <el-upload
          class="avatar-uploader"
          :action="api/file/upload"
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload">
          <img v-if="imageUrl" :src="imageUrl" class="avatar">
          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button v-if="dialogStatus=='create'" type="success" @click="insertPostType">创 建</el-button>
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
          create: '新建'
        },
        postType: {
          postTypeId: '',
          postTypeName: '',
          postTypeImg: '',
          postTypeCreateTime:''
        },
        newPostType:{
          postTypeImg:'',
          postTypeCreateTime:'',
          postTypeName:''
        },
        imageUrl:''
      }
    },
    created() {
      this.getPostTypeList();
      /*if (this.hasPerm('user:add') || this.hasPerm('user:update')) {
        this.getAllRoles();
      }*/
    },
    computed: {
      ...mapGetters([
        'postTypeId'
      ])
    },
    methods: {
      getPostTypeList() {
        this.listLoading = true;
        this.api({
          url: "/postType/getPostTypelist",
          method: "get",
          params: this.dialogFormVisible
        }).then(data => {
          this.listLoading = false;
          this.list = data.list;
          this.totalCount = data.totalCount;
        })
      },
      handleAvatarSuccess(res, file) {
        this.imageUrl = URL.createObjectURL(file.raw);
        this.dialogFormVisible.imageUrl = this.imageUrl;
        console.log(this.dialogFormVisible.imageUrl);
        //this.imageUrl = URL.createObjectURL(file.raw);
        //console.log(this.imageUrl)
        // this.tempUser.categoriesImg = this.imageUrl;
        // alert(this.tempUser.categoriesImg);
      },
      beforeAvatarUpload(file) {
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
      insertPostType() {
        let newPostType = this.newPostType;
        newPostType.postTypeCreateTime = this.formatter(this.postType.postTypeCreateTime, 'yyyy-MM-dd hh:mm:ss');
        console.log(newPostType.postTypeCreateTime);
        newPostType.postTypeImg = this.imageUrl;
        console.log(newPostType.postTypeImg);
        newPostType.postTypeName = this.postType.postTypeName;
        console.log(newPostType.postTypeName)

        this.listLoading = true;
        this.api({
          url: "/postType/insertPostType",
          method: "post",
          params: newPostType
        }).then(() => {
          console.log("插入成功！")
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
        this.postType.postTypeId = "";
        this.postType.postTypeImg = "";
        this.postType.postTypeName = "";
        this.dialogStatus = "create"
        this.dialogFormVisible = true
      },
      showUpdate($index) {
        let postType = this.list[$index];
        this.postType.postTypeName = postType.postTypeName;
        this.postType.postTypeImg = postType.postTypeImg;
        this.postType.postTypeId = postType.postTypeId
        this.dialogStatus = "update"
        this.dialogFormVisible = true
      }
    }
  }
  </script>
