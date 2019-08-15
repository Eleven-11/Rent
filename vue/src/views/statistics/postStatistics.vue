<template>
  <div style="background-color: #F2f2f2;padding-bottom: 50px">
    <el-row class="header-container">
      <div class="header-title">帖子统计</div>
    </el-row>
    <el-row class="panel">
      <el-col :span="12" class="panel-tab">
        <div>
          <el-tabs v-model="likeActiveName" :tab-position="top"
                   style="background-color: #fff;padding: 0 10px 0;border-radius: 10px;border: 1px solid rgba(215, 215, 215, 1) ;">
            <el-tab-pane name="first" label="浏览量排名" style="min-height: 380px" disabled="false"></el-tab-pane>
            <el-tab-pane label="总计" name="likeSum" style="min-height: 380px">
              <ProcessTitle v-for="(item,index) in browseWeekList" :key="index" color="#67C23A" no="2" max="10000" cur="8000" content="用户"></ProcessTitle>
            </el-tab-pane>

            <el-tab-pane label="周排行" name="week" style="min-height: 380px">
              <ProcessTitle v-for="(item,index) in browseWeekList" :key="index" color="#ff6666" :no="index+1" :max="browseWeekMax" :cur="item.likeNum" :content="item.content"></ProcessTitle>
            </el-tab-pane>

            <el-tab-pane label="月排行" name="month" style="min-height: 380px">
              <ProcessTitle v-for="(item,index) in browseMonthList" :key="index" color="#ff6666" :no="index+1" :max="browseMonthMax" :cur="item.likeNum" :content="item.content"></ProcessTitle>
            </el-tab-pane>
          </el-tabs>
        </div>
      </el-col>

      <el-col :span="12" class="panel-tab">
        <div>
          <el-tabs v-model="commentActiveName" :tab-position="top"
                   style="background-color: #fff;padding: 0 10px 0;border-radius: 10px;border: 1px solid rgba(215, 215, 215, 1) ;">
            <el-tab-pane name="first" label="评论排行" style="min-height: 380px" disabled="false"></el-tab-pane>
            <el-tab-pane label="总计" name="commentSum" style="min-height: 380px">
              <ProcessTitle v-for="(item,index) in commentWeekList" :key="index" color="#ff6666" :no="index+1" :max="commentWeekMax" :cur="item.commentNum" :content="item.content"></ProcessTitle>
            </el-tab-pane>

            <el-tab-pane label="周排行" name="commentWeek" style="min-height: 380px">
              <ProcessTitle v-for="(item,index) in commentWeekList" :key="index" color="#ff6666" :no="index+1" :max="commentWeekMax" :cur="item.commentNum" :content="item.content"></ProcessTitle>
            </el-tab-pane>

            <el-tab-pane label="月排行" name="commentMonth" style="min-height: 380px">
              <ProcessTitle v-for="(item,index) in commentMonthList" :key="index" color="#ff6666" :no="index+1" :max="commentMonthMax" :cur="item.commentNum" :content="item.content"></ProcessTitle>
            </el-tab-pane>
          </el-tabs>
        </div>
      </el-col>
    </el-row>

    <el-row class="panel">
      <el-col>
        <div>
          <el-tabs v-model="collectActiveName" :tab-position="top"
                   style="background-color: #fff;padding: 0 10px 0;border-radius: 10px;border: 1px solid rgba(215, 215, 215, 1) ;">
            <el-tab-pane name="first" label="收藏排行" style="min-height: 380px" disabled="false"></el-tab-pane>
            <el-tab-pane label="总计" name="collectSum" style="min-height: 380px">
              <div class="sum_panel_container">
                <div class="sum_panel">
                  <ProcessTitle v-for="(item,index) in collectionMonthLeftList" :key="index" color="#ff6666" :no="index+1" :max="collectionMonthMax" :cur="item.collectNum" :content="item.content"></ProcessTitle>
                </div>
                <div class="sum_panel">
                  <ProcessTitle v-for="(item,index) in collectionMonthRightList" :key="index" color="#ff6666" :no="index+6" :max="collectionMonthMax" :cur="item.collectNum" :content="item.content"></ProcessTitle>
                </div>
              </div>
            </el-tab-pane>

            <el-tab-pane label="周统计" name="priceWeek" style="min-height: 380px">
              <div class="sum_panel_container">
                <div class="sum_panel">
                  <ProcessTitle v-for="(item,index) in collectionWeekLeftList" :key="index" color="#ff6666" :no="index+1" :max="collectionWeekMax" :cur="item.collectNum" :content="item.content">
                  </ProcessTitle>
                </div>
                <div class="sum_panel">
                  <ProcessTitle v-for="(item,index) in collectionWeekRightList" :key="index" color="#ff6666" :no="index+6" :max="collectionWeekMax" :cur="item.collectNum" :content="item.content">
                  </ProcessTitle>
                </div>
              </div>
            </el-tab-pane>

            <el-tab-pane label="月统计" name="priceMonth" style="min-height: 380px">
              <div class="sum_panel_container">
                <div class="sum_panel">
                  <ProcessTitle v-for="(item,index) in collectionMonthLeftList" :key="index" color="#ff6666" :no="index+1" :max="collectionMonthMax" :cur="item.collectNum" :content="item.content"></ProcessTitle>
                </div>
                <div class="sum_panel">
                  <ProcessTitle v-for="(item,index) in collectionMonthRightList" :key="index" color="#ff6666" :no="index+6" :max="collectionMonthMax" :cur="item.collectNum" :content="item.content"></ProcessTitle>
                </div>
              </div>
            </el-tab-pane>

          </el-tabs>
        </div>
      </el-col>
    </el-row>

    <el-row class="panel">
      <el-col>
        <div>
          <el-tabs v-model="priceActiveName" :tab-position="top"
                   style="background-color: #fff;padding: 0 10px 0;border-radius: 10px;border: 1px solid rgba(215, 215, 215, 1) ;">
            <el-tab-pane name="first" label="价位统计" style="min-height: 380px" disabled="false"></el-tab-pane>
            <el-tab-pane label="总计" name="priceSum" style="min-height: 380px">总计</el-tab-pane>
            <el-tab-pane label="周统计" name="priceWeek" style="min-height: 380px"></el-tab-pane>
            <el-tab-pane label="月统计" name="priceMonth" style="min-height: 380px"></el-tab-pane>
          </el-tabs>
        </div>
      </el-col>
    </el-row>

  </div>
</template>

<script>
  import ProcessTitle from './ProcessTitle';

  export default {
    name: "postStatistics",
    components:{
      ProcessTitle
    },
    data() {
      return {
        likeActiveName: 'likeSum',
        commentActiveName: 'commentSum',
        priceActiveName: 'priceSum',
        collectActiveName: "collectSum",

        browseTotalMax: 1,
        browseWeekMax: 1,
        browseMonthMax: 1,
        browseTotalList:[],
        browseWeekList:[],
        browseMonthList:[],

        commentTotalMax: 1,
        commentWeekMax: 1,
        commentMonthMax: 1,

        commentTotalList:[],
        commentWeekList:[],
        commentMonthList:[],

        collectionTotalMax: 1,
        collectionWeekMax: 1,
        collectionMonthMax: 1,
        collectionTotalLeftList:[],
        collectionWeekLeftList:[],
        collectionMonthLeftList:[],
        collectionTotalRightList:[],
        collectionWeekRightList:[],
        collectionMonthRightList:[]

      }
    },
    created(){
      this.getLikeData();
      this.getCommentData();
      this.getCollectionData();
    },

    methods: {
      getLikeData() {
        this.browseWeekMax = 1;
        this.api({
          url: "/statistics/postLikeByWeek",
          method: "get",
        }).then(data => {
          let size = data.length;
          size = size > 10 ? 10 : size;
          if (size > 1) {
            this.browseWeekMax = data[0].likeNum;
          }
          //补足显示
          if (size < 10) {
            for (let i = size; i < 10; i++) {
              data.push({content: "暂无数据", likeNum: 0, postId: "-1"});
            }
          }
          this.browseWeekList = data.slice(0, 10);
        })

        this.browseMonthMax = 1;
        this.api({
          url: "/statistics/postLikeByMonth",
          method: "get",
        }).then(data => {
          let size = data.length;
          size = size > 10 ? 10 : size;
          if (size > 1) {
            this.browseMonthMax = data[0].likeNum;
          }
          //补足显示
          if (size < 10) {
            for (let i = size; i < 10; i++) {
              data.push({content: "暂无数据", likeNum: 0, postId: "-1"});
            }
          }
          this.browseMonthList = data.slice(0, 10);
        })
      },
      getCommentData(){
        this.commentWeekMax = 1;
        this.api({
          url: "/statistics/postCommentByWeek",
          method: "get",
        }).then(data => {
          let size = data.length;
          size = size > 10 ? 10 : size;
          if (size > 1) {
            this.commentWeekMax = data[0].commentNum;
          }
          //补足显示
          if (size < 10) {
            for (let i = size; i < 10; i++) {
              data.push({content: "暂无数据", commentNum: 0, postId: "-1"});
            }
          }
          this.commentWeekList = data.slice(0, 10);
        })

        this.commentMonthMax = 1;
        this.api({
          url: "/statistics/postCommentByMonth",
          method: "get",
        }).then(data => {
          let size = data.length;
          size = size > 10 ? 10 : size;
          if (size > 1) {
            this.commentMonthMax = data[0].commentNum;
          }
          //补足显示
          if (size < 10) {
            for (let i = size; i < 10; i++) {
              data.push({content: "暂无数据", commentNum: 0, postId: "-1"});
            }
          }
          this.commentMonthList = data.slice(0, 10);
        })
      },

      getCollectionData(){
        this.collectionWeekMax = 1;
        this.api({
          url: "/statistics/postCollectByWeek",
          method: "get",
        }).then(data => {
          let size = data.length;
          size = size > 10 ? 10 : size;
          if (size > 1) {
            this.collectionWeekMax = data[0].collectNum;
          }
          //补足显示
          if (size < 10) {
            for (let i = size; i < 10; i++) {
              data.push({content: "暂无数据", collectNum: 0, postId: "-1"});
            }
          }
          this.collectionWeekLeftList = data.slice(0,5);
          this.collectionWeekRightList = data.slice(5,10);
        })

        this.collectionMonthMax = 1;
        this.api({
          url: "/statistics/postCollectByMonth",
          method: "get",
        }).then(data => {
          let size = data.length;
          size = size > 10 ? 10 : size;
          if (size > 1) {
            this.collectionMonthMax = data[0].collectNum;
          }
          //补足显示
          if (size < 10) {
            for (let i = size; i < 10; i++) {
              data.push({content: "暂无数据", collectNum: 0, postId: "-1"});
            }
          }
          this.collectionMonthLeftList = data.slice(0,5);
          this.collectionMonthRightList = data.slice(5,10);
        })
      }

    }
  }
</script>

<style scoped>
  .header-container {
    height: 60px;
    background-color: #797979;
    padding-left: 100px;
  }

  .header-title::before {
    display: inline-block;
    content: '';
    width: 6px;
    height: 20px;
    background-color: #0079FE;
    line-height: 60px;
    vertical-align: text-bottom;
    margin-right: 8px;
  }

  .header-title {
    color: #FFF;
    font-size: 18px;
    line-height: 60px;
    font-weight: bold;
    display: inline-block;
  }

  .panel {
    padding: 50px 100px 0;

  }

  .panel-tab {
    padding: 0 10px;

  }

  .panel-tab:first-child {
    padding-left: 0;
    padding-right: 20px;
  }

  .panel-tab:last-child {
    padding-right: 0px;
    padding-left: 20px;
  }

  .sum_panel_container{
    display: flex;
    justify-content: center;
    align-items: center;
    flex-flow: wrap;
    position: relative;
  }
  .sum_panel{
    flex: 1;
    width: 50%;

  }
  .sum_panel:first-child{
    padding: 0 40px 0 20px;
  }
  .sum_panel:last-child{
    padding: 0 20px 0 40px;
  }
  .sum_panel:first-child:before{
    content: '';
    display: block;
    position: absolute;
    height: 100%;
    width: 1px;
    background-color: #DCDFE6;
    left: 50%;
    transform: translateX(-50%);
  }
</style>
