<template>
  <div style="background-color: #F2f2f2;padding-bottom: 50px">
    <el-row class="header-container">
      <div class="header-title">帖子统计</div>
    </el-row>
    <el-row class="panel">
      <el-col :span="12" class="panel-tab">
        <div>
          <el-tabs v-model="likeActiveName" tab-position="top"
                   style="background-color: #fff;padding: 0 10px 0;border-radius: 10px;border: 1px solid rgba(215, 215, 215, 1) ;">
            <el-tab-pane name="first" label="浏览量排名" style="min-height: 380px" :disabled="true"></el-tab-pane>
            <el-tab-pane label="总计" name="likeSum" style="min-height: 380px">
              <ProcessTitle v-for="(item,index) in likeTotalList" :key="index" color="#67C23A" :no="index+1" :max="likeTotalMax" :cur="item.likeNum" :content="item.content"></ProcessTitle>
            </el-tab-pane>

            <el-tab-pane label="周排行" name="week" style="min-height: 380px">
              <ProcessTitle v-for="(item,index) in likeWeekList" :key="index" color="#ff6666" :no="index+1" :max="likeWeekMax" :cur="item.likeNum" :content="item.content"></ProcessTitle>
            </el-tab-pane>

            <el-tab-pane label="月排行" name="month" style="min-height: 380px">
              <ProcessTitle v-for="(item,index) in likeMonthList" :key="index" color="#ff6666" :no="index+1" :max="likeMonthMax" :cur="item.likeNum" :content="item.content"></ProcessTitle>
            </el-tab-pane>
          </el-tabs>
        </div>
      </el-col>

      <el-col :span="12" class="panel-tab">
        <div>
          <el-tabs v-model="commentActiveName" tab-position="top"
                   style="background-color: #fff;padding: 0 10px 0;border-radius: 10px;border: 1px solid rgba(215, 215, 215, 1) ;">
            <el-tab-pane name="first" label="评论排行" style="min-height: 380px" :disabled="true"></el-tab-pane>
            <el-tab-pane label="总计" name="commentSum" style="min-height: 380px">
              <ProcessTitle v-for="(item,index) in commentTotalList" :key="index" color="#ff6666" :no="index+1" :max="commentTotalMax" :cur="item.commentNum" :content="item.content"></ProcessTitle>
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
          <el-tabs v-model="collectActiveName" tab-position="top"
                   style="background-color: #fff;padding: 0 10px 0;border-radius: 10px;border: 1px solid rgba(215, 215, 215, 1) ;">
            <el-tab-pane name="first" label="收藏排行" style="min-height: 380px" :disabled="true"></el-tab-pane>
            <el-tab-pane label="总计" name="collectSum" style="min-height: 380px">
              <div class="sum_panel_container">
                <div class="sum_panel">
                  <ProcessTitle v-for="(item,index) in collectionTotalLeftList" :key="index" color="#ff6666" :no="index+1" :max="collectionTotalMax" :cur="item.collectNum" :content="item.content"></ProcessTitle>
                </div>
                <div class="sum_panel">
                  <ProcessTitle v-for="(item,index) in collectionTotalRightList" :key="index" color="#ff6666" :no="index+6" :max="collectionTotalMax" :cur="item.collectNum" :content="item.content"></ProcessTitle>
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
          <el-tabs v-model="priceActiveName" tab-position="top" @tab-click="clickTab"
                   style="background-color: #fff;padding: 0 10px 0;border-radius: 10px;border: 1px solid rgba(215, 215, 215, 1) ;">
            <el-tab-pane name="first" label="价位统计" style="min-height: 380px" :disabled="true"></el-tab-pane>
            <el-tab-pane label="总计" name="priceSum" style="min-height: 380px" :lazy="true">
              <div  id="allPriceEchart" style="height: 380px;"></div>
            </el-tab-pane>
            <el-tab-pane label="周统计" name="priceWeek" style="min-height: 380px" :lazy="true">
              <div  id="weekPriceEchart" style="height: 380px;"></div>
            </el-tab-pane>

            <el-tab-pane label="月统计" name="priceMonth" style="min-height: 380px" :lazy="true">
              <div  id="monthPriceEchart" style="height: 380px;"></div>
            </el-tab-pane>
          </el-tabs>
        </div>
      </el-col>
    </el-row>

  </div>
</template>

<script>
  import ProcessTitle from './ProcessTitle';
  import echarts from 'echarts'
  export default {
    name: "postStatistics",
    components:{
      ProcessTitle
    },
    data() {
      return {
        listQuery: {
          type: ''
        },
        likeActiveName: 'likeSum',
        commentActiveName: 'commentSum',
        priceActiveName: 'priceSum',
        collectActiveName: "collectSum",

        likeTotalMax: 1,
        likeWeekMax: 1,
        likeMonthMax: 1,
        likeTotalList:[],
        likeWeekList:[],
        likeMonthList:[],

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
        collectionMonthRightList:[],

        allPriceList:[],
        weekPriceList:[],
        monthPriceList:[],

        xName:['0-10', '10-20', '20-30', '30-40', '40-50', '50-60', '60-70'
          , '70-80', '80-90', '90-100', '以上'],

        weekData:[15,10,19,30,40],

      }
    },
    created(){
      this.getLikeData();
      this.getCommentData();
      this.getCollectionData();
      this.getAllData();
    },
    mounted(){

    },
    methods: {
      getLikeData() {

        this.likeTotalMax = 1;
        this.api({
          url: "/statistics/postLikeAll",
          method: "get",
        }).then(data => {
          let size = data.length;
          size = size > 10 ? 10 : size;
          if (size > 1) {
            this.likeTotalMax = data[0].likeNum;
          }
          //补足显示
          if (size < 10) {
            for (let i = size; i < 10; i++) {
              data.push({content: "暂无数据", likeNum: 0, postId: "-1"});
            }
          }
          this.likeTotalList = data.slice(0, 10);
        })

        this.likeWeekMax = 1;
        this.api({
          url: "/statistics/postLikeByWeek",
          method: "get",
        }).then(data => {
          let size = data.length;
          size = size > 10 ? 10 : size;
          if (size > 1) {
            this.likeWeekMax = data[0].likeNum;
          }
          //补足显示
          if (size < 10) {
            for (let i = size; i < 10; i++) {
              data.push({content: "暂无数据", likeNum: 0, postId: "-1"});
            }
          }
          this.likeWeekList = data.slice(0, 10);
        })

        this.likeMonthMax = 1;
        this.api({
          url: "/statistics/postLikeByMonth",
          method: "get",
        }).then(data => {
          let size = data.length;
          size = size > 10 ? 10 : size;
          if (size > 1) {
            this.likeMonthMax = data[0].likeNum;
          }
          //补足显示
          if (size < 10) {
            for (let i = size; i < 10; i++) {
              data.push({content: "暂无数据", likeNum: 0, postId: "-1"});
            }
          }
          this.likeMonthList = data.slice(0, 10);
        })
      },
      getCommentData(){
        this.commentTotalMax = 1;
        this.api({
          url: "/statistics/postCommentAll",
          method: "get",
        }).then(data => {
          let size = data.length;
          size = size > 10 ? 10 : size;
          if (size > 1) {
            this.commentTotalMax = data[0].commentNum;
          }
          //补足显示
          if (size < 10) {
            for (let i = size; i < 10; i++) {
              data.push({content: "暂无数据", commentNum: 0, postId: "-1"});
            }
          }
          this.commentTotalList = data.slice(0, 10);
        })


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

      getCollectionData() {
        this.collectionTotalMax = 1;
        this.api({
          url: "/statistics/postCollectAll",
          method: "get",
        }).then(data => {
          let size = data.length;
          size = size > 10 ? 10 : size;
          if (size > 1) {
            this.collectionTotalMax = data[0].collectNum;
          }
          //补足显示
          if (size < 10) {
            for (let i = size; i < 10; i++) {
              data.push({content: "暂无数据", collectNum: 0, postId: "-1"});
            }
          }
          this.collectionTotalLeftList = data.slice(0, 5);
          this.collectionTotalRightList = data.slice(5, 10);
        })

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
          this.collectionWeekLeftList = data.slice(0, 5);
          this.collectionWeekRightList = data.slice(5, 10);
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
          this.collectionMonthLeftList = data.slice(0, 5);
          this.collectionMonthRightList = data.slice(5, 10);
        })
      },

      getAllData(){
        let _this = this;
        _this.listQuery.type = '';
        this.api({
          url: "/statistics/priceLineData",
          method: "get",
          params:_this.listQuery
        }).then(data => {
          _this.allPriceList = data.list;
          this.dataToEchart('allPriceEchart',this.allPriceList);
        })

      },
      getWeekData(){
        let _this = this;
        _this.listQuery.type = 'week';
        this.api({
          url: "/statistics/priceLineData",
          method: "get",
          params:_this.listQuery
        }).then(data => {
          _this.weekPriceList = data.list;
          this.dataToEchart('weekPriceEchart',this.weekPriceList);
        })
      },
      getMonthData(){
        let _this = this;
        _this.listQuery.type = 'month';
        this.api({
          url: "/statistics/priceLineData",
          method: "get",
          params:_this.listQuery
        }).then(data => {
          _this.monthPriceList = data.list;
          this.dataToEchart('monthPriceEchart',this.monthPriceList);
        })
      },
      //处理数据变成图像
      dataToEchart(id,data){
        let nameList = [];
        let minList = [];
        let maxList = [];
        let min = 0;
        let max = 0;
        for (let i = 0 ; i < data.length; i++){
          nameList.push(data[i].name.substring(data[i].name.indexOf('.')+1));
          minList.push(data[i].minNum);
          maxList.push(data[i].maxNum);
          min = min < data[i].minNum ? min : data[i].minNum;
          min = min < data[i].maxNum ? min : data[i].maxNum;
          max = max > data[i].minNum ? max : data[i].minNum;
          max = max > data[i].maxNum ? max : data[i].maxNum;
        }
        this.getValueEchart(id,nameList,minList,maxList,min,max)
      },
      //id,names,values
      getValueEchart(id,names,minData,maxData,min,max) {
        let myChart = echarts.init(document.getElementById(id));
        let option = {
          show: true,
          backgroundColor: '#ffffff',
          grid: {
            show:true,
            borderColor: '#ffffff',
            backgroundColor: '#ffffff',
            top: '6%',
            left: '3%',
            right: '3%',
            bottom:'8%',
            containLabel: true
          },
          tooltip: {
            trigger: 'axis',
            axisPointer:{
              type:'none'
            }
          },
          xAxis: {
            type: 'category',
            data: names,
            boundaryGap: true,
            axisLine: {
              show: true,
              lineStyle:{width:1, color: "#ccc"}
            },
            axisTick:{alignWithLabel:true},
            axisLabel:{color:'#111',},
            splitLine:{show:false}
          },
          yAxis: {
            type: 'value',
            boundaryGap: false,
            axisLine: {
              show: false,
              lineStyle:{color: "#ccc"}
            },
            axisTick:{show: false, },
            splitLine:{
              lineStyle:{type: 'dashed'}
            },
            axisLabel:{show: true, color:'#111'},
            min:min,
            max:max
          },
          series: [{
            name:'最低价格',
            type: 'line',
            symbol:'circle',
            symbolSize: 9,
            data: minData,
            itemStyle:{
              color:'#1890ff',
              borderWidth:1,
              borderColor:'#fff',
            },
            animation:false,
            emphasis:{
              itemStyle:{
                shadowBlur:4,
                shadowColor :'rgba(0, 20, 255, 0.9)',
                opacity:1
              }
            }
          },{
            name:'最高价格',
            type: 'line',
            symbol:'circle',
            symbolSize: 9,
            data: maxData,
            itemStyle:{
              color:'#ff6666',
              borderWidth:1,
              borderColor:'#fff',
            },
            animation:false,
            emphasis:{
              itemStyle:{
                shadowBlur:4,
                shadowColor :'rgba(255, 20, 0, 0.9)',
                opacity:1
              }
            }
          }]
        }
        myChart.setOption(option);
      },
      clickTab($event){
        let id = event.target.getAttribute('id');
        console.log(id);
        if (id == "tab-priceWeek"){
          console.log('111')
          this.getWeekData();
        }else if (id == "tab-priceMonth"){
          console.log('2222')
          this.getMonthData();
        }
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
