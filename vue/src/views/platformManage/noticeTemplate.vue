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
      <el-table-column align="center" label="id" prop="sysTempId" v-if="false" style="width: 60px;"></el-table-column>
      <el-table-column align="center" prop="sysTempTitle" label="模版名称" style="width: 60px;"></el-table-column>
      <el-table-column align="center" prop="sysTempContent" label="模版内容" style="width: 60px;"></el-table-column>
      <el-table-column align="center" prop="isGuide" label="引导语" style="width: 60px;"></el-table-column>

      <el-table-column align="center" label="管理" width="200" v-if="hasPerm('article:update')">
        <template slot-scope="scope">
          <el-button type="primary" icon="edit" @click="showUpdate(scope.$index)">修改</el-button>
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
      
      <el-form class="small-space" :model="noticeTemplate" label-position="left" label-width="80px"
               style='width: 300px; margin-left:50px;'>
        <el-form-item label="模版名称">
          <el-input type="text" v-model="noticeTemplate.sysTempTitle"></el-input>
        </el-form-item>
        <el-form-item label="模版内容">
          <el-input type="textarea" v-model="noticeTemplate.sysTempContent"></el-input>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button v-if="dialogStatus=='create'" type="success" @click="createSysTemplate">创 建</el-button>
        <el-button type="primary" v-else @click="updateSysTemplate">修 改</el-button>
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
        //设置数据模版
        noticeTemplate: {
          sysTempId: '',
          sysTempTitle: '',
          sysTempContent: '',
          isGuide:''
        },
        newNoticeTemplate:{
          sysTempContent:'',
          isGuide:'',
          sysTempTitle:''
        },
        imageUrl:''
      }
    },
    created() {
      this.getNoticeTemplateList();
      /*if (this.hasPerm('user:add') || this.hasPerm('user:update')) {
        this.getAllRoles();
      }*/
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
      getNoticeTemplateList()
      {
        this.listLoading = true;
        this.api({
          url: "/sysTemplate/getSysTemplateList",
          method: "get",
          params: this.listQuery
        }).then(data => {
          this.listLoading = false;
          this.list = data.list;
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
      /**
       * 创建模版对话框
       */
      showCreate() {
        //显示新增对话框
        this.noticeTemplate.sysTempId = "";
        this.noticeTemplate.sysTempContent = "";
        this.noticeTemplate.sysTempTitle = "";
        this.dialogStatus = "create"
        this.dialogFormVisible = true
      },
      /**
       * 修改模版对话框
       */
      showUpdate($index) {
        let noticeTemplate = this.list[$index];
        this.noticeTemplate.sysTempTitle = noticeTemplate.sysTempTitle;
        this.noticeTemplate.sysTempContent = noticeTemplate.sysTempContent;
        this.noticeTemplate.sysTempId = noticeTemplate.sysTempId
        this.dialogStatus = "update"
        this.dialogFormVisible = true
      },

      /**
       * 添加系统模版
       */
      createSysTemplate()
      {
        let newNoticeTemplate = this.newNoticeTemplate;
        // newNoticeTemplate.isGuide = this.noticeTemplate.isGuide;
        // console.log(newNoticeTemplate.isGuide);
        newNoticeTemplate.sysTempContent = this.sysTempContent;
        console.log(newNoticeTemplate.sysTempContent);
        newNoticeTemplate.sysTempTitle = this.noticeTemplate.sysTempTitle;
        console.log(newNoticeTemplate.sysTempTitle)

        this.listLoading = true;
        this.api({
          url: "/sysTemplate/insertSysTemplate",
          method: "post",
          params:newNoticeTemplate
        }).then(() => {
          console.log("创建模版成功！")
        })
      },
    }
  }
</script>

<style scoped>

</style>
