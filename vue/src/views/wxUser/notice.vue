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
            <el-option v-for="item in optionGroup" :key="item.value" :label="item.label"
              :value="item.value" :disabled="item.disabled">
            </el-option>
          </el-select>
        </el-form-item>
        <!--搜索按钮-->
        <el-form-item>
          <el-button type="primary" class="el-icon-search" @click="getList">搜索</el-button>
        </el-form-item>

        <el-form-item>
          <el-button v-if="hasPerm('notice:send')" type="success" icon="plus" @click="showSend">发送消息</el-button>
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

      <el-table-column align="center" prop="targetNickName" label="接收人" width="200">

        <template slot-scope="scope">
          <p v-if="scope.row.targetNickName == null">所有</p>
          <p v-else>{{scope.row.targetNickName}}</p>
        </template>

      </el-table-column>

      <el-table-column align="center" prop="content" show-overflow-tooltip label="内容"  min-width="300"></el-table-column>
      <el-table-column align="center" prop="createTime" label="发送时间" width="200" ></el-table-column>

      <el-table-column fixed="right" align="center" width="100" label="管理">
        <template slot-scope="scope">
          <el-button v-if="hasPerm('notice:del')" type="danger" icon="el-icon-delete" @click="showDelete(scope.$index)"></el-button>
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
        <el-form-item label="是否群发" prop="hasGroup">
          <el-switch
            v-model="sysInformation.hasGroup"
            active-color="#13ce66"
            inactive-color="#ff4949" @change="selectGroup($event)">
          </el-switch>
        </el-form-item>

        <el-form-item v-if="!sysInformation.hasGroup" label="发送人">
          <el-autocomplete v-model="queryUser.nickName" :fetch-suggestions="querySearchAsync"
            placeholder="请输入内容" @select="handleSelect" :trigger-on-focus="false">
            <i class="el-icon-edit el-input__icon" slot="suffix">
            </i>
          </el-autocomplete>
        </el-form-item>

        <el-form-item v-if="false" label="发送人ID" prop="targetId">
          <el-input placeholder="不可输入内容" v-model="sysInformation.targetId" :disabled="true">
          </el-input>
        </el-form-item>

        <el-form-item label="选择模版">
          <el-select v-model="sysTempName" placeholder="全部消息"
                    @change="selectTemp($event)">
            <el-option v-for="item in optionTemplate" :key="item.sysTempId" :label="item.sysTempTitle"
                       :value="item" :disabled="item.disabled">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="消息内容" prop="content">
<!--          <span style="color:red">图片大小不可超过2MB</span>-->
          <el-input type="textarea" :rows="6" v-model="sysInformation.content" placeholder="请限制在800字以内"></el-input>
        </el-form-item>

      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="closeForm('sysInformation')">取 消</el-button>
        <el-button type="success" @click="sendSysTemplate('sysInformation')">发 送</el-button>
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
          targetNickName :[{required: true, message: '请选择发送人', trigger: 'blur'}],
          content :[{required: true, message: '请输入少于800字的模版内容', trigger: 'blur'}]
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
        optionTemplate:[{
          isGuide: '',
          sysTempContent: '',
          sysTempId: '',
          sysTempTitle: '',
        }],
        sysTempName:'',
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
        dialogStatus: 'send',
        dialogFormVisible: false,
        dialogMessage:'',
        textMap: {
          send: '发送消息'
        },
        //设置数据模版
        sysInformation: {
          templateId:'',
          sysInformationId: '',
          targetId:'',
          targetNickName: '',
          content: '',
          hasGroup:true,  //是否群发
          createTime:''
        },
        queryUser: {
          nickName:''
        },
      }
    },
    created() {
      this.getList();
      //模版下拉框
      this.getTemplateList();
    },
    computed: {
      ...mapGetters([
        'sysInformationId'
      ])
    },
    methods:{

      /**
       * 获取模版列表
       */
      getTemplateList(){
        this.api({
          url: "/sysTemplate/getTemplateList",
          method: "get",
          params: '',
        }).then(data => {
          this.optionTemplate = data.list
        })
      },
      /**
       * 模版选中后触发
       */
      selectTemp($event){
        this.sysTempName = $event.sysTempTitle;
        this.sysInformation.content = $event.sysTempContent;
      },
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
       * 发送消息 - 对话框
       */
      showSend() {
        //显示新增对话框
        this.sysInformation.content = "";
        this.sysInformation.targetNickName = "";
        this.sysInformation.hasGroup = true;
        this.sysInformation.targetId = "system_info";
        this.sysTempName = "";
        this.dialogStatus = "send";
        this.dialogFormVisible = true;
      },
      /**
       * 选择是否群发
       */
      selectGroup($event){
        if ($event){
          this.sysInformation.targetId = "system_info";
        }else{
          this.sysInformation.targetId = "";
        }
      },
      /**
       * 后台查询角色
       */
      querySearchAsync(queryString, cb){

        if (queryString && queryString.length <= 50) {
          this.queryUser.nickName = queryString;
          this.sysInformation.targetId = '';
          this.api({
            url: "/bkWxUser/queryUser",
            method: "get",
            params: this.queryUser
          }).then(res => {
            var result = [];
            if (res.list && res.list.length > 0){
              result = res.list;
            }else{
              result.push({
                wxUserId: '',
                value: '未搜索到结果！'
              })
            }
            clearTimeout(this.timeout);
            this.timeout = setTimeout(() => {
              cb(result);
            }, 3000 * Math.random());
          }).catch(_ => {});
        }
      },
      /**
       * 选中查询的角色
       */
      handleSelect(item){
        console.log(item)
        this.sysInformation.targetId = item.wxUserId;
      },
      /**
       * 发送消息 - 功能
       */
      sendSysTemplate(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.listLoading = true;
            this.api({
              url: "/backInfor/send",
              method: "post",
              params: this.sysInformation
            }).then(() => {
              let msg = "发送成功";
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
            url: "/backInfor/del",
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
      }
    }
  }
</script>
