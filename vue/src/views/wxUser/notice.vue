<template>
  <div class="app-container">
    <div class="filter-container">
      <!--搜索表单-->
      <el-form class="small-space" inline :model="listQuery">

        <el-form-item>接收人：</el-form-item>
        <!--内容输入框-->
        <el-form-item>
          <el-input type="text" v-model="listQuery.targetNickName" placeholder="输入接收人名称"/>
        </el-form-item>

        <el-form-item>消息类型：</el-form-item>
        <el-form-item>
          <el-select v-model="listQuery.group" placeholder="全部消息">
            <el-option
              v-for="item in optionGroup"
              :key="item.value"
              :label="item.label"
              :value="item.value"
              :disabled="item.disabled">
            </el-option>
          </el-select>
        </el-form-item>
        <!--搜索按钮-->
        <el-form-item>
          <el-button type="primary" class="el-icon-search" @click="getList">搜索</el-button>
        </el-form-item>

        <el-form-item>
          <el-button type="success" icon="plus" @click="showCreate">发送消息</el-button>
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
      <!--<el-table-column align="center" label="sysInformationId" prop="sysInformationId" v-if="false"></el-table-column>-->
      <!--<el-table-column align="center" label="targetId" prop="targetId" v-if="false"></el-table-column>-->
      <el-table-column align="center" prop="targetNickName" label="接收人" width="200">

        <template slot-scope="scope">
          <p v-if="scope.row.targetNickName == null">所有</p>
          <p v-else>{{scope.row.targetNickName}}</p>
        </template>

      </el-table-column>
      <el-table-column align="center" prop="content" label="内容"  min-width="300"></el-table-column>
      <el-table-column align="center" prop="createTime" label="发送时间" width="200" ></el-table-column>

      <el-table-column fixed="right" align="center" width="100" label="管理" v-if="hasPerm('article:update')">
        <template slot-scope="scope">
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
               @close="closeDialog" :close-on-click-modal="false">

      <el-form class="small-space" :model="sysInformation" :rules="rules" ref="sysInformation" label-position="left" label-width="80px"
               style='width: 400px; margin-left:50px;'>
        <el-form-item label="模版名称" prop="targetNickName">
          <el-input type="text" v-model="sysInformation.targetNickName"></el-input>
        </el-form-item>
        <el-form-item label="模版内容" prop="content">
          <el-input type="textarea" :rows="6" v-model="sysInformation.content"></el-input>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="closeForm('sysInformation')">取 消</el-button>
        <el-button v-if="dialogStatus=='create'" type="success" @click="createSysTemplate('sysInformation')">创 建</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import {mapGetters} from 'vuex'

  export default {
    name: "sysInformation",

    data() {
      return {
        rules:{
          targetNickName :[{required: true, message: '请输入模版名称', trigger: 'blur'}],
          content :[{required: true, message: '请输入模版内容', trigger: 'blur'}]
        },
        optionGroup: [{
          value: 1,
          label: '系统消息'
        }, {
          value: 2,
          label: '非系统消息'
        },{
          value: 0,
          label: '全部消息'
        }],
        totalCount: 0, //分页组件--数据总条数
        list: [],//表格的数据
        listLoading: false,//数据加载等待动画
        listQuery: {
          group:0,  //0全 1群 2非群
          targetNickName:'',  //接收人名称
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
        sysInformation: {
          sysInformationId: '',
          targetId:'',
          targetNickName: '',
          content: '',
          createTime:''
        }
      }
    },
    created() {
      this.getList();
    },
    computed: {
      ...mapGetters([
        'sysInformationId'
      ])
    },
    methods:{

      /**
       * 获取消息模版列表
       */
      getList() {
        this.listLoading = true;
        this.api({
          url: "/backInfor/list",
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
       * 创建模版 - 对话框
       */
      showCreate() {
        //显示新增对话框
        this.sysInformation.content = "";
        this.sysInformation.targetNickName = "";
        this.dialogStatus = "create";
        this.dialogFormVisible = true;
      },

      /**
       * 添加模版 - 功能
       */
      createOperation(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.listLoading = true;
            this.api({
              url: "/sysTemplate/insertSysTemplate",
              method: "post",
              params: this.sysInformation
            }).then(() => {
              let msg = "创建成功";
              //隐藏面板
              this.dialogFormVisible = false;
              this.$message({message: msg, type: 'success', duration: 1 * 1000,
                onClose: () => {
                  //刷新列表
                  this.getList();
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
       * 删除条目
       */
      showDelete($index){
        let sysInformation = this.list[$index];
        this.sysInformation.sysInformationId = sysInformation.sysInformationId;

        this.$confirm('此操作将永久删除该文件,如需回复需联系管理员, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {

          this.api({
            url: "/sysTemplate/updateDelTemplate",
            method: "post",
            params:this.sysInformation
          }).then(data => {
            this.$message({
              type: 'success',
              message: '删除成功!'
            });
            this.getList();
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
      closeForm(formName){
        this.dialogFormVisible = false;
        // 点击取消 数据重置
        this.$refs[formName].clearValidate();
      },
      // 对话框关闭事件
      closeDialog(){
        // 点击关闭 数据重置
        this.$refs['sysInformation'].clearValidate();
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
