<template>
  <div class="app-container">
    <div class="filter-container">
      <el-form>
        <el-form-item>
      请选择模块：
      <el-select v-model="selector" style="width: 400px;" filterable placeholder="请选择模块" @change="selectTemp($event)">
        <el-option
          v-for="item in options"
          :key="item.navigationId"
          :label="item.navigationTitle"
          :value="item.navigationId">
        </el-option>
      </el-select>
        </el-form-item>
      </el-form>
    </div>
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
    <el-table-column align="center" label="发布时间" prop="createTime" width="220"></el-table-column>
    <el-table-column align="center" label="地址" show-overflow-tooltip prop="address"  width="120"></el-table-column>
    <el-table-column align="center" label="帖子内容" show-overflow-tooltip prop="content" width="200" ></el-table-column>
    <el-table-column align="center" label="最低价" prop="minPrice" ></el-table-column>
    <el-table-column align="center" label="最高价" prop="maxPrice" ></el-table-column>
    <el-table-column align="center" label="联系方式" prop="phone" width="160"></el-table-column>
    <el-table-column align="center" label="浏览量" prop="browse" ></el-table-column>
    <el-table-column align="center" label="评论数量" prop="comments" ></el-table-column>
    <el-table-column align="center" label="点赞数量" prop="likes" ></el-table-column>
    <el-table-column align="center" label="中介费" prop="fee" ></el-table-column>
    <el-table-column align="center" label="发帖人id" prop="userId" v-if="false"></el-table-column>
    <el-table-column align="center" label="置顶模块id" prop="navigationId" v-if="false"></el-table-column>
    <el-table-column align="center" label="置顶模块" prop="navigationTitle"></el-table-column>
    <el-table-column align="center" label="主键" prop="topPostId" v-if="false"></el-table-column>
    <el-table-column align="center" label="近期活跃时间" prop="activeTime" width="220" ></el-table-column>
    <el-table-column  align="center" width="400" label="管理" v-if="true">
      <template slot-scope="scope">
        <el-button type="danger" icon="el-icon-delete" @click="showDelete(scope.$index)"></el-button>
        <el-button type="primary" icon="up" v-show="list[scope.$index-1].navigationId === list[scope.$index].navigationId" @click="sortNavigationTop(scope.$index-1,scope.$index)" v-if="(scope.$index)!=0">↑</el-button>
        <el-button type="primary" icon="down" v-show="list[scope.$index].navigationId === list[scope.$index+1].navigationId" @click="sortNavigationTop(scope.$index,scope.$index+1)" v-if="(scope.$index)!=list.length-1">↓</el-button>
      </template>
    </el-table-column>
  </el-table>

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
        navigationTop:{
          topPostId:'',//navigationTop表主键
          navigationId:''//模块id
        },
        listQuery: {
          pageNum: 1,//页码
          pageRow: 50,//每页条数
        },
        dialogFormVisible: false,
        dialogStatus: 'create',
        selector:'',
        options:[{
          navigationId:'',
          navigationTitle:''
        }],

      }
    },
    created() {
      this.getNavigationTopList();
      this.getNavigationTitle();
    },
    computed: {
      ...mapGetters([
        'navigationId'
      ])
    },
    methods: {
      selectTemp($event){
        console.log($event)
        this.listQuery.navigationId = $event
        this.getNavigationTopList()
      },
      getNavigationTopList() {
        this.listLoading = true;
        this.api({
          url: "/navigationTop/getNavigationTopList",
          method: "get",
          params: this.listQuery
        }).then(data => {
          console.log(data)
          this.listLoading = false;
          this.list = data;
          for (var i =0 ;i<this.list.length; i++){
            this.list[i].createTime = this.formatter(this.list[i].createTime,'yyyy-MM-dd hh:mm:ss')
            this.list[i].activeTime = this.formatter(this.list[i].activeTime,'yyyy-MM-dd hh:mm:ss')
          }
          this.totalCount = data.totalCount;
          this.listQuery.title = "";
        })
      },
      getNavigationTitle() {
        this.api({
          url: "/navigationTop/getNavigationTitle",
          method: "get"
        }).then(data => {
          this.options = data.info;
          this.options[0] = ''
        })
      },
      getIndex($index) {
        //表格序号
        return (this.listQuery.pageNum - 1) * this.listQuery.pageRow + $index + 1
      },
      /**
       * 删除条目
       */
      showDelete($index) {
        let navigationTop = this.list[$index];
        this.navigationTop.topPostId = navigationTop.topPostId;
        this.navigationTop.navigationId = navigationTop.navigationId;

        this.$confirm('此操作将永久删除该文件,如需回复需联系管理员, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {

          this.api({
            url: "/navigationTop/updateDelNav",
            method: "post",
            params: this.navigationTop
          }).then(data => {
            this.getNavigationTopList();
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
      /*置顶帖子排序*/
      sortNavigationTop($formerIndex, $laterIndex) {
        let formerNav = this.list[$formerIndex];
        let laterNav = this.list[$laterIndex];
        this.api({
          url: "/navigationTop/sortNavigationTop",
          method: "post",
          params: {
            formerNavId: formerNav.topPostId,
            laterNavId:laterNav.topPostId,
            formerSortTime: this.formatter(formerNav.sortTime, 'yyyy-MM-dd hh:mm:ss'),
            laterSortTime: this.formatter(laterNav.sortTime, 'yyyy-MM-dd hh:mm:ss')
          }
        }).then(() => {
          this.getNavigationTopList()
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
    }
  }
</script>

<style scoped>

</style>
