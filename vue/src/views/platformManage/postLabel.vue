<template>
  <div class="app-container">
    <div class="filter-container">
      <el-form>
        <el-form-item>
          <el-button type="primary" icon="plus" @click="showCreate">添加
          </el-button>

          <el-upload
            class="upload-demo"
            ref="upload"
            action="http://localhost:8080/file/importLabel"
            :on-preview="handlePreview"
            :on-remove="handleRemove"
            :file-list="fileList"
            accept=".xlsx"
            :auto-upload="false">
            <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
            <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">导入帖子标签</el-button>
            <div slot="tip" class="el-upload__tip">只能上传xlsx文件，且不超过500kb</div>
          </el-upload>
        </el-form-item>

      </el-form>
    </div>
    <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect" >
      <el-submenu  v-for="item in MenuParent":key="item.postLabelId" :index="item.postLabelId" >
        <template slot="title">{{item.labelContent}}</template>
        <el-menu-item :index="child.postLabelId" v-for="child in item.children":key="child.postLabelId"  v-if="child.children!=null">{{child.labelContent}}</el-menu-item>
        <el-menu-item :index="item.postLabelId" v-for="(child,index) in item.children":key="child.postLabelId"  v-if="child.children==null&&index<1">{{item.labelContent}}</el-menu-item>

      </el-submenu>
    </el-menu>
    <div class="line"></div>
      <el-table :data="tableData" v-loading.body="listLoading" element-loading-text="拼命加载中" border fit
                  highlight-current-row>
          <el-table-column align="center" label="序号" width="80">
            <template slot-scope="scope">
              <span v-text="getIndex(scope.$index)"> </span>
            </template>
          </el-table-column>
          <el-table-column align="center" label="id" prop="postLabelId" v-if="false" style="width: 60px;"></el-table-column>
          <el-table-column align="center" label="标签名" prop="labelContent"  width="220" style="width: 60px;"></el-table-column>
          <el-table-column label="图片" min-width="20%"  align="center">
            <!-- 图片的显示 -->
            <template   slot-scope="scope" >
              <img :src="scope.row.labelImg"  min-width="70" height="70"/>
            </template>
          </el-table-column>
          <el-table-column align="center" label="管理" width="220">
            <template slot-scope="scope">
              <el-button type="primary" icon="edit" @click="updatePostType()">修改</el-button>
              <el-button type="danger" icon="delete" v-if="scope.row.postLabelId!=postLabelId "
                         @click="isDeleteopen(scope.row.postLabelId)">删除
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
    <el-dialog v-model="newPostLabel" :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
          <el-form class="small-space" :model="postLabel" label-position="left" label-width="80px"
                   style='width: 300px; margin-left:50px;'>
            <el-form-item label="标签名称" required v-if="dialogStatus=='create'">
              <el-input type="text" v-model="postLabel.labelContent">
              </el-input>
            </el-form-item>
            <el-form-item label="上级标签" required v-if="dialogStatus=='create'">
              <el-input type="text" v-model="postLabel.parentContent">
              </el-input>
            </el-form-item>
            <el-form-item label="标签图标" required v-if="dialogStatus=='create'">
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
            fileList:[], //上传的文件
            totalCount: 0, //分页组件--数据总条数
            tableData: [],//表格的数据
            listLoading: false,//数据加载等待动画
            ParentId:{
              labelParentId:0
            },
            listQuery: {
              pageNum: 1,//页码
              pageRow: 50,//每页条数
            },
            MenuParent:[],
            roles: [],//角色列表
            dialogStatus: 'create',
            dialogFormVisible: false,
            textMap: {
              update: '编辑',
              create: '新建'
            },
            postLabel: {
              postLabelId: '',
              labelContent: '',
              labelImg: '',
              parentContent:''

            },
            newPostLabel:{
              postLabelId:'',
              contents:'',
              labelImg:'',
              parentContent:''
            },
            imageUrl:''
          }
        },
        created() {
          this.getPostTypeList();
          this.loadParentMenu();
          /*if (this.hasPerm('user:add') || this.hasPerm('user:update')) {
            this.getAllRoles();
          }*/
        },
        computed: {
          ...mapGetters([
            'postLabelId'
          ])
        },
        methods: {
          //获取标签列表
          getPostTypeList() {
            this.listLoading = true;
            this.api({
              url: "/postLabel/getPostLabelList",
              method: "get",
            }).then(data => {
              console.log("获取了数据");
              console.log(data)
              this.listLoading = false;
               this.tableData = data.list;
               this.totalCount = data.totalCount;
            })
          },
          //获取菜单
          loadParentMenu(){
            this.listloading = false;
            this.api({
              url: "/postLabel/getPostLabelList",
              method: "get",
              // params: this.ParentId
            }).then(data=>{
              var list = data.list;
              function listToTree(postLabelId,labelParentId,list){
                function exists(list, parentId){
                  for(var i=0; i<list.length; i++){

                    if (list[i].postLabelId == parentId){return true;}
                  }
                  console.log("222")
                  return false;
                }
                var nodes = [];
                // get the top level nodes
                for(var i=0; i<list.length; i++){
                  var row = list[i];
                  if (!exists(list, row.labelParentId)){
                    nodes.push(row);
                  }
                }

                var toDo = [];
                for(var i=0; i<nodes.length; i++){
                  toDo.push(nodes[i]);
                }
                while(toDo.length){
                  var node = toDo.shift();    // the parent node
                  // get the children nodes
                  for(var i=0; i<list.length; i++){
                    var row = list[i];
                    if (row.labelParentId == node.postLabelId){
                      //var child = {id:row.id,text:row.name};
                      if (node.children){
                        node.children.push(row);
                      } else {
                        node.children = [row];
                      }
                      toDo.push(row);
                    }
                  }
                }
                return nodes;
              }
              // console.log(JSON.stringify(listToTree("postLabelId","labelParentId",list)));
              var list = listToTree("postLabelId","labelParentId",list);
              this.MenuParent = list;
              console.log(this.MenuParent);
            })
          },
          //批量导入标签
          submitUpload() {
              this.$refs.upload.submit();
          },
          handleRemove(file, fileList) {
            console.log(file, fileList);
          },
          handlePreview(file) {
            console.log("上传前数据处理")
            console.log(file);
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
            const isLt2M = file.size / 1024 / 1024 < 2;


            if (!isLt2M) {
              this.$message.error('上传头像图片大小不能超过 2MB!');
            }
            return  isLt2M;
          },
          handleSelect(key, keyPath){
            console.log("菜单监听启动");
            this.ParentId.labelParentId=key;
            console.log(this.ParentId.labelParentId);
            this.api({
              url:"/postLabel/getPostLabelList",
              method:"get",
              params:this.ParentId
            }).then(data => {
              console.log("获取了数据");
              console.log(data)
              this.listLoading = false;
              this.tableData = data.list;
              this.totalCount = data.totalCount;
            })
          },
          insertPostType() {
            let newPostLabel = this.newPostLabel;
            newPostLabel.labelImg = this.imageUrl;
            console.log(newPostLabel.labelImg);
            newPostLabel.contents = this.postLabel.labelContent;
            newPostLabel.parentContent = this.postLabel.parentContent;
            this.listLoading = true;
            console.log(newPostLabel);

            this.api({
              url: "/postLabel/insertPostLabel",
              method: "post",
              params: newPostLabel
            })
            this.dialogFormVisible = false;

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
            this.postLabel.postLabelId = "";
            this.postLabel.labelImg = "";
            this.postLabel.labelContent = "";
            this.dialogStatus = "create"
            this.dialogFormVisible = true
          },

          showUpdate($index) {
            let postLabel = this.list[$index];
            this.postLabel.labelContent = postLabel.labelContent;
            this.postLabel.labelImg = postLabel.labelImg;
            this.postLabel.postLabelId = postLabel.postLabelId
            this.dialogStatus = "update"
            this.dialogFormVisible = true
          },
          //确认框
          isDeleteopen(labelId) {
            this.$confirm('是否删除该标签?', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              this.deletePostLabel(labelId);
              this.$message({
                type: 'success',
                message: '删除成功!'
              });
            }).catch(() => {
              this.$message({
                type: 'info',
                message: '已取消删除'
              });
            });
          }
          ,
          //删除方法
          deletePostLabel(index){
            this.postLabel.postLabelId = index
            console.log(index);
            this.api({
              url:"/postLabel/updateDelPostLabel",
              method:"get",
              params:this.postLabel
            })
          }
        }
      }
      </script>
