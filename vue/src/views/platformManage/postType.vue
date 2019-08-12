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
      <el-table-column fixed="left" align="center" label="序号" width="80">
        <template slot-scope="scope">
          <span v-text="getIndex(scope.$index)"> </span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="id" prop="postTypeId" v-if="false"></el-table-column>
      <el-table-column align="center" prop="postTypeName" label="类型名称" width="200"></el-table-column>
      <el-table-column align="center" prop="postTypeImg" label="类型图片" width="500">
        <template slot-scope="scope">
          <img :src="scope.row.postTypeImg" style="width: 200px; height: 100px;"/>
        </template>
      </el-table-column>
      <el-table-column align="center" label="排序时间" prop="sortTime" width="100" v-if="false"></el-table-column>
      <el-table-column fixed="right" align="center" width="400" label="管理" v-if="true">
        <template slot-scope="scope">
          <el-tooltip content="编辑" placement="bottom">
            <el-button type="warning" icon="el-icon-edit" @click="showUpdate(scope.$index)"></el-button>
          </el-tooltip>

          <el-button type="danger" icon="el-icon-delete" @click="showDelete(scope.$index)"></el-button>
          <el-button type="primary" icon="up" @click="sortPostType(scope.$index-1,scope.$index)"  v-if="(scope.$index)!=0">↑</el-button>
          <el-button type="primary" icon="down" @click="sortPostType(scope.$index,scope.$index+1)" v-if="(scope.$index)!=list.length-1">↓</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog v-model="newPostType" :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form class="small-space" :model="postType" label-position="left" label-width="80px"
               style='width: 300px; margin-left:50px;'>
        <el-form-item label="类型标题">
          <el-input type="text" v-model="newPostType.postTypeName">
          </el-input>
        </el-form-item>
        <el-form-item label="类型图片" v-model="newPostType.postTypeImg">
          <el-upload
            class="avatar-uploader"
            action="api/file/upload"
            :show-file-list="true"
            :before-upload="beforeUpload"
            :on-success="handleAvatarSuccess">
            <img :src="postTypeImg" class="avatar">
            <i  class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button v-if="dialogStatus=='update'" type="success" @click="updatePostType">修 改</el-button>
        <el-button v-if="dialogStatus=='create'" type="success" @click="insertPostType">创 建</el-button>
      </div>
    </el-dialog>
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
        textMap: {
          update: '编辑',
          create: '新建'
        },
        listQuery: {
          title: '',
          pageNum: 1,//页码
          pageRow: 50,//每页条数
        },
        postType: {
          postTypeId: '',
          postTypeName: '',
          postTypeImg: ''
        },
        newPostType: {
          postTypeId: '',
          postTypeName: '',
          postTypeImg: ''
        },
        dialogFormVisible: false,
        dialogStatus: 'create',

      }
    },
    created() {
      this.getPostTypelist();
    },
    computed: {
      ...mapGetters([
        'postTypeId'
      ])
    },
    methods: {
      getPostTypelist() {
        this.listLoading = true;
        this.api({
          url: "/postType/getPostTypelist",
          method: "get",
          params: this.listQuery
        }).then(data => {
          this.listLoading = false;
          this.list = data.list;
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
      getIndex($index) {
        //表格序号
        return (this.listQuery.pageNum - 1) * this.listQuery.pageRow + $index + 1
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
      updatePostType(formName) {

        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.listLoading = true;
            this.api({
              url: "/postType/updateDelPostType",
              method: "post",
              params: this.postType
            }).then(() => {
              let msg = "修改成功";
              //隐藏面板
              this.dialogFormVisible = false;
              this.$message({
                message: msg, type: 'success', duration: 1 * 1000,
                // onClose: () => {
                //   //刷新列表
                //   this.getPostTypelist();
                // }
              })
              this.getPostTypelist()
            })
          } else {
            let msg = "请填写必填项";
            this.$message({message: msg, type: 'warning', duration: 1 * 1000})
          }
        })
      },
      /**
       * 删除条目
       */
      showDelete($index) {
        let postType = this.list[$index];
        this.postType.postTypeId = postType.postTypeId;

        this.$confirm('此操作将永久删除该文件,如需回复需联系管理员, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {

          this.api({
            url: "/postType/updateDelPostType",
            method: "post",
            params: this.postType
          }).then(data => {
            this.$message({
              type: 'success',
              message: '删除成功!'
            });
            this.getPostTypelist()
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
      }
    },
  }
</script>

<style scoped>

</style>
