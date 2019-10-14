<template>
  <div class="app-container">
    <div class="filter-container">
      <el-form>
        <el-form-item>
          <el-button v-if="hasPerm('banner:add')" type="primary" icon="plus" @click="showCreate">添加
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
      <el-table-column align="center" label="id" prop="advId" v-if="false"></el-table-column>
      <el-table-column align="center" prop="advTitle" label="广告栏标题" width="200"></el-table-column>
      <el-table-column align="center" prop="advImg" label="广告栏图片" width="500">
        <template slot-scope="scope">
          <img :src="scope.row.advImg" style="width: 200px; height: 100px;"/>
        </template>
      </el-table-column>
      <el-table-column align="center" label="排序时间" prop="sortTime" width="100" v-if="false"></el-table-column>
      <el-table-column align="center" width="518" label="管理" v-if="true">
        <template slot-scope="scope">
          <el-tooltip v-if="hasPerm('banner:update')" content="编辑" placement="bottom">
            <el-button type="warning" icon="el-icon-edit" @click="showUpdate(scope.$index)"></el-button>
          </el-tooltip>

          <el-tooltip content="删除" placement="bottom">
            <el-button v-if="hasPerm('banner:del')" type="danger" icon="el-icon-delete"
                       @click="showDelete(scope.$index)"></el-button>
          </el-tooltip>
          <el-tooltip content="上移" placement="bottom">
            <el-button type="primary" icon="up" @click="sortAdvImg(scope.$index-1,scope.$index)"
                       v-if="(scope.$index)!=0 && hasPerm('banner:sort')">↑
            </el-button>
          </el-tooltip>
          <el-tooltip content="下移" placement="bottom">
            <el-button type="primary" icon="down" @click="sortAdvImg(scope.$index,scope.$index+1)"
                       v-if="(scope.$index)!=list.length-1 && hasPerm('banner:sort')">↓
            </el-button>
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

    <el-dialog v-model="newAdvBanner" :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form class="small-space" :model="advBanner" label-position="left" label-width="80px"
               style='width: 300px; margin-left:50px;'>
        <el-form-item label="广告标题">
          <el-input type="text" v-model="newAdvBanner.advTitle">
          </el-input>
        </el-form-item>
        <el-form-item label="广告图片" v-model="newAdvBanner.advImg">
          <el-upload
            class="avatar-uploader"
            action="api/file/upload"
            limit="1"
            :before-upload="beforeUpload"
            :on-success="handleAvatarSuccess">
            <img :src="advImg" style="width:200px " class="avatar">
            <i class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
          <span style="color:red">图片大小不可超过2MB</span>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button v-if="dialogStatus=='update'" type="success" @click="updateAdvBanner">修 改</el-button>
        <el-button v-if="dialogStatus=='create'" type="success" @click="insertAdvImg">创 建</el-button>
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
          pageNum: 1,//页码
          pageRow: 50,//每页条数
        },
        advBanner: {
          advId: '',
          advTitle: '',
          advImg: ''
        },
        newAdvBanner: {
          advId: '',
          advTitle: '',
          advImg: ''
        },
        dialogFormVisible: false,
        dialogStatus: 'create',
      }
    },
    created() {
      this.getAdvImgList();
    },
    computed: {
      ...mapGetters([
        'advId'
      ])
    },
    methods: {
      getAdvImgList() {
        this.listLoading = true;
        this.api({
          url: "/advBanner/getAdvImgList",
          method: "get",
          params: this.listQuery
        }).then(data => {
          this.listLoading = false;
          this.list = data.list;
          this.totalCount = data.totalCount;
          this.listQuery.title = "";
        })
      },
      insertAdvImg() {
        this.dialogFormVisible = false
        // this.listLoading = true;
        this.api({
          url: "/advBanner/insertAdvImg",
          method: "post",
          params: this.newAdvBanner
        }).then(data => {
          // this.dialogFormVisible = false
          this.getAdvImgList()
        })
      },
      getIndex($index) {
        //表格序号
        return (this.listQuery.pageNum - 1) * this.listQuery.pageRow + $index + 1
      },
      beforeUpload(file) {
        const extension = file.type === 'image/jpeg'
        const extension2 = file.type === 'image/jpg'
        const extension3 = file.type === 'image/png'

        if (!extension && !extension2 && !extension3) {
          this.$message.error('上传头像图片要为jpg、png格式!');
        }
        // const isSize = file.width<=654&&file.height<=270;
        const isLt2M = file.size / 1024 / 1024 < 2;

        /*if (!isJPG) {
          this.$message.error('上传头像图片要为jpg、png格式!');
        }*/
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 2MB!');
        }
        return isJPG & isLt2M;
      },
      handleAvatarSuccess(res, file) {
        this.advImg = URL.createObjectURL(file.raw);
        this.newAdvBanner.advImg = res;
        console.log(res);
        //this.imageUrl = URL.createObjectURL(file.raw);
        //console.log(this.imageUrl)
        // this.tempUser.categoriesImg = this.imageUrl;
        // alert(this.tempUser.categoriesImg);
      },
      showCreate() {
        //显示新增对话框
        this.newAdvBanner.advTitle = "";
        this.newAdvBanner.advImg = "";
        this.dialogStatus = "create";
        this.dialogFormVisible = true;
      },
      updateAdvBanner(formName) {

        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.listLoading = true;
            this.api({
              url: "/advBanner/updateDelAdvImg",
              method: "post",
              params: this.advBanner
            }).then(() => {
              let msg = "修改成功";
              //隐藏面板
              this.dialogFormVisible = false;
              this.$message({
                message: msg, type: 'success', duration: 1 * 1000,
                onClose: () => {
                  //刷新列表
                  this.getAdvImgList();
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
      showDelete($index) {
        let advBanner = this.list[$index];
        this.advBanner.advId = advBanner.advId;

        this.$confirm('此操作将永久删除该文件,如需回复需联系管理员, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {

          this.api({
            url: "/advBanner/updateDelAdvImg",
            method: "post",
            params: this.advBanner
          }).then(data => {
            this.$message({
              type: 'success',
              message: '删除成功!'
            });
            this.getAdvImgList();
          })

        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      },
      showUpdate($index) {
        let advBanner = this.list[$index];
        this.newAdvBanner.advId = advBanner.advId;
        this.newAdvBanner.advTitle = advBanner.advTitle;
        this.newAdvBanner.advImg = advBanner.advImg;
        this.dialogStatus = "update";
        this.dialogFormVisible = true;


      },
      updateAdvBanner() {
        let advBanner = this;
        this.api({
          url: "/advBanner/updateAdvImg",
          method: "post",
          params: this.newAdvBanner
        }).then(() => {
          console.log("修改成功")
        })
      },
      /*广告栏图片排序*/
      sortAdvImg($formerIndex, $laterIndex) {
        let formerAdv = this.list[$formerIndex];
        let laterAdv = this.list[$laterIndex];
        this.api({
          url: "/advBanner/sortAdvImg",
          method: "post",
          params: {
            formerAdvId: formerAdv.advId,
            laterAdvId: laterAdv.advId,
            formerSortTime: this.formatter(formerAdv.sortTime, 'yyyy-MM-dd hh:mm:ss'),
            laterSortTime: this.formatter(laterAdv.sortTime, 'yyyy-MM-dd hh:mm:ss')
          }
        }).then(() => {
          this.getAdvImgList()
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
        this.getAdvImgList();
      },

      handleFilter() {
        //查询事件
        this.listQuery.pageNum = 1
        this.getAdvImgList()
      },
      getIndex($index) {
        //表格序号
        return (this.listQuery.pageNum - 1) * this.listQuery.pageRow + $index + 1
      }
    },
  }
</script>

<style scoped>

</style>
