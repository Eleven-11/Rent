<template>
  <div class="app-container">
    <div class="filter-container">
      <!--搜索表单-->
      <el-form class="small-space" inline :model="listQuery">

        <el-form-item>模版名称:</el-form-item>
        <!--内容输入框-->
        <el-form-item>
          <el-input type="text" v-model="listQuery.title" placeholder="输入模版名称"/>
        </el-form-item>

        <!--搜索按钮-->
        <el-form-item>
          <el-button type="primary" class="el-icon-search" @click="getNoticeTemplateList">搜索</el-button>
        </el-form-item>

        <el-form-item>
          <el-button type="success" icon="plus" @click="showCreate">添加模版</el-button>
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
      <el-table-column align="center" label="id" prop="sysTempId" v-if="false"></el-table-column>
      <el-table-column align="center" prop="sysTempTitle" label="模版名称" width="150"></el-table-column>
      <el-table-column align="center" prop="sysTempContent" label="模版内容"  min-width="300"></el-table-column>
      <el-table-column align="center" prop="isGuide" label="模版类型" width="150" >
        <template slot-scope="scope">
          <el-tag type="success" v-if="scope.row.isGuide===1">引导语模版</el-tag>
          <el-tag type="primary" v-else>一般模版</el-tag>
        </template>
      </el-table-column>
      

      <el-table-column fixed="right" align="center" width="250" label="管理">
        <template slot-scope="scope">
          <el-tooltip v-if="scope.row.isGuide===1" content="取消引导语" placement="bottom">
            <el-button type="info" icon="el-icon-info" @click="cancelGuide(scope.$index)"></el-button>
          </el-tooltip>
          <el-tooltip v-else content="设置为引导语" placement="bottom">
            <el-button  type="success" icon="el-icon-info" @click="beGuide(scope.$index)"></el-button>
          </el-tooltip>
          <el-tooltip content="编辑模版" placement="bottom">
            <el-button type="warning" icon="el-icon-edit" @click="showUpdate(scope.$index)"></el-button>
          </el-tooltip>

          <el-button type="danger" icon="el-icon-delete" @click="showDelete(scope.$index)"></el-button>
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

    <!-- 创建修改弹窗 -->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible"
               @close="closeDialog" :close-on-click-modal="false" width="">
      
      <el-form class="small-space" :model="noticeTemplate" :rules="rules" ref="noticeTemplate" label-position="left" label-width="80px"
               style='width: 400px; margin-left:50px;'>
        <el-form-item label="模版名称" prop="sysTempTitle">
          <el-input type="text" v-model="noticeTemplate.sysTempTitle"></el-input>
        </el-form-item>
        <el-form-item label="模版内容" prop="sysTempContent">
          <el-input type="textarea" :rows="6" v-model="noticeTemplate.sysTempContent"></el-input>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="closeSysTemplate('noticeTemplate')">取 消</el-button>
        <el-button v-if="dialogStatus=='create'" type="success" @click="createSysTemplate('noticeTemplate')">创 建</el-button>
        <el-button type="primary" v-else @click="updateSysTemplate('noticeTemplate')">修 改</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import {mapGetters} from 'vuex'

  export default {
    name: "noticeTemplate",

    data() {
      return {
        rules:{
          sysTempTitle :[{required: true, message: '请输入模版名称', trigger: 'blur'}],
          sysTempContent :[{required: true, message: '请输入模版内容', trigger: 'blur'}]
        },
        totalCount: 0, //分页组件--数据总条数
        list: [],//表格的数据
        listLoading: false,//数据加载等待动画
        listQuery: {
          title:'',
          pageNum: 1,//页码
          pageRow: 50,//每页条数
        },
        roles: [],//角色列表
        dialogStatus: 'create',
        dialogFormVisible: false,
        dialogMessage:'',
        textMap: {
          update: '编辑',
          create: '新建'
        },
        //设置数据模版
        noticeTemplate: {
          sysTempId: '',
          sysTempTitle: '',
          sysTempContent: '',
          isGuide:''
        }
      }
    },
    created() {
      this.getNoticeTemplateList();
    },
    computed: {
      ...mapGetters([
        'sysTempId'
      ])
    },
    methods:{

      /**
       * 获取消息模版列表
       */
      getNoticeTemplateList() {
        this.listLoading = true;
        this.api({
          url: "/sysTemplate/getSysTemplateList",
          method: "get",
          params: this.listQuery
        }).then(data => {
          this.listLoading = false;
          this.list = data.list;
          this.totalCount = data.totalCount;
          this.listQuery.title = "";
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
        this.getNoticeTemplateList();
      },

      handleFilter() {
        //查询事件
        this.listQuery.pageNum = 1
        this.getNoticeTemplateList()
      },
      getIndex($index) {
        //表格序号
        return (this.listQuery.pageNum - 1) * this.listQuery.pageRow + $index + 1
      },
      /**
       * 创建模版 - 对话框
       */
      showCreate() {
        //显示新增对话框
        this.noticeTemplate.sysTempContent = "";
        this.noticeTemplate.sysTempTitle = "";
        this.dialogStatus = "create";
        this.dialogFormVisible = true;
      },
      /**
       * 修改模版 - 对话框
       */
      showUpdate($index) {
        let noticeTemplate = this.list[$index];
        this.noticeTemplate.sysTempTitle = noticeTemplate.sysTempTitle;
        this.noticeTemplate.sysTempContent = noticeTemplate.sysTempContent;
        this.noticeTemplate.sysTempId = noticeTemplate.sysTempId;
        this.noticeTemplate.isGuide = "";
        this.dialogStatus = "update";
        this.dialogFormVisible = true;
      },

      /**
       * 添加模版 - 功能
       */
      createSysTemplate(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.listLoading = true;
            this.api({
              url: "/sysTemplate/insertSysTemplate",
              method: "post",
              params: this.noticeTemplate
            }).then(() => {
              let msg = "创建成功";
              //隐藏面板
              this.dialogFormVisible = false;
              this.$message({message: msg, type: 'success', duration: 1 * 1000,
                onClose: () => {
                  //刷新列表
                  this.getNoticeTemplateList();
                }
              })
            })
          } else {
            let msg = "请填写必填项";
            this.$message({message: msg, type: 'warning', duration: 1 * 1000})
          }
        })
      },
      /**
       * 修改模版 - 功能
       */
      updateSysTemplate(formName){

        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.listLoading = true;
            this.api({
              url: "/sysTemplate/updateSysTemplate",
              method: "post",
              params:this.noticeTemplate
            }).then(() => {
              let msg = "修改成功";
              //隐藏面板
              this.dialogFormVisible = false;
              this.$message({message: msg, type: 'success', duration: 1 * 1000,
                onClose: () => {
                  //刷新列表
                  this.getNoticeTemplateList();
                }
              })
            })
          } else {
            let msg = "请填写必填项";
            this.$message({message: msg, type: 'warning', duration: 1 * 1000})
          }
        })
      },
      /**
       * 成为引导语
       */
      beGuide($index){
        let noticeTemplate = this.list[$index];
        this.noticeTemplate.sysTempTitle = "";
        this.noticeTemplate.sysTempContent = "";
        this.noticeTemplate.isGuide = 1;
        this.noticeTemplate.sysTempId = noticeTemplate.sysTempId;

        this.$confirm('此操作将设置该消息模版为引导语模版(如果已有消息模版将会直接替换),是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.api({
            url: "/sysTemplate/updateSysTemplate",
            method: "post",
            params:this.noticeTemplate
          }).then(data => {
            this.$message({
              type: 'success',
              message: '设置成功!'
            });
            this.getNoticeTemplateList();
          })
        }).catch(() => {

          this.$message({
            type: 'info',
            message: '已取消操作'
          });
        });
      },
      /**
       * 引导语取消
       */
      cancelGuide($index){
        let noticeTemplate = this.list[$index];
        this.noticeTemplate.sysTempTitle = "";
        this.noticeTemplate.sysTempContent = "";
        this.noticeTemplate.isGuide = 0;
        this.noticeTemplate.sysTempId = noticeTemplate.sysTempId;

        this.$confirm('此操作将停止继续使用该模版为引导语模版(如果无引导模版新用户登陆将不会收到该消息), 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.api({
            url: "/sysTemplate/updateSysTemplate",
            method: "post",
            params:this.noticeTemplate
          }).then(data => {
            this.$message({
              type: 'success',
              message: '取消成功!'
            });
            this.getNoticeTemplateList();
          })
        }).catch(() => {

          this.$message({
            type: 'info',
            message: '已取消操作'
          });
        });
      },
      /**
       * 删除条目
       */
      showDelete($index){
        let noticeTemplate = this.list[$index];
        this.noticeTemplate.sysTempId = noticeTemplate.sysTempId;

        this.$confirm('此操作将永久删除该文件,如需回复需联系管理员, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {

            this.api({
              url: "/sysTemplate/updateDelTemplate",
              method: "post",
              params:this.noticeTemplate
            }).then(data => {
              this.$message({
                type: 'success',
                message: '删除成功!'
              });
              this.getNoticeTemplateList();
            })

          }).catch(() => {
            this.$message({
              type: 'info',
              message: '已取消删除'
            });
          });
      },
      /**
       * 表单取消
       * @param formName
       */
      closeSysTemplate(formName){
        this.dialogFormVisible = false;
        // 点击取消 数据重置
        this.$refs[formName].clearValidate();
      },
      // 对话框关闭事件
      closeDialog(){
        // 点击关闭 数据重置
        this.$refs['noticeTemplate'].clearValidate();
      }
    }
  }
</script>
