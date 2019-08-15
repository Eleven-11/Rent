<template>
  <div style="background-color: #F2f2f2;padding-bottom: 50px">

    <el-row class="header-container">
      <div class="header-title">
        地区统计
      </div>
    </el-row>

    <el-row>
      <el-col>
        <div class="panel">
          <el-tabs  v-model="activeName" tab-position="top" @tab-click="clickTab"
                    style="background-color: #fff;padding: 0 10px 0;border-radius: 10px;border: 1px solid rgba(215, 215, 215, 1) ;">
            <el-tab-pane name="first" label="浏览量排名" style="min-height: 380px" :disabled="true"></el-tab-pane>
            <el-tab-pane label="总计" name="sum" style="min-height: 380px" :lazy="true">
              <div ref="c_sum" id="regionTotalEchart" style="height: 380px;"></div>
            </el-tab-pane>

            <el-tab-pane label="周统计" name="week" style="min-height: 380px" :lazy="true">
              <div  ref="c_week" id="regionWeekEchart" style="height: 380px;"></div>
            </el-tab-pane>

            <el-tab-pane label="月统计" name="month" style="min-height: 380px" :lazy="true">
              <div ref="c_month" id="regionMonthEchart" style="height: 380px;"></div>
            </el-tab-pane>
          </el-tabs>
        </div>
      </el-col>
    </el-row>

    <el-row class="panel" style="padding: 50px 100px 0;">
      <el-col>
        <div class=" sum-gender">
          <div class="statistics-title">地区浏览量年报表</div>
          <div style="text-align: center">报表数据内容</div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import echarts from 'echarts'

  export default {
    name: "regionStatistics",
    data(){
      return {
        // chartTotal:echarts.init(document.getElementById("regionWeekEchart")),
        // chartWeek:echarts.init(document.getElementById("regionWeekEchart")),
        // chartMonth:echarts.init(document.getElementById("regionMonthEchart")),
        activeName:'sum',
        tabItem:'tab-sum',
        chartMap:[],
        regionTotalNameList:[],
        regionTotalValueList:[],
        regionWeekNameList:[],
        regionWeekValueList:[],
        regionMonthNameList:[],
        regionMonthValueList:[]
      }
    },

    created(){
      this.getAllData();
      // this.getMonthData();
    },
    // mounted(){
    //   this.getAllData();
    //   this.getMonthData();
    // },
    methods: {
      getAllData(){
        let _this = this;
        _this.regionWeekNameList = [];
        _this.regionWeekValueList = [];
        this.api({
          url: "/statistics/postRegionByWeek",
          method: "get",
          params:this.man
        }).then(data => {
          console.log(data);
          for (var i = 0 ; i < data.length; i ++){
            _this.regionWeekNameList.push(data[i].region);
            _this.regionWeekValueList.push(data[i].browse);
          }
          _this.getRegionEchart('regionTotalEchart',_this.regionWeekNameList,_this.regionWeekValueList);
         })
      },

      getWeekData(){
        let _this = this;
        _this.regionWeekNameList = [];
        _this.regionWeekValueList = [];
        this.api({
          url: "/statistics/postRegionByWeek",
          method: "get",
          params:this.man
        }).then(data => {
          console.log(data);
          for (var i = 0 ; i < data.length; i ++){
            _this.regionWeekNameList.push(data[i].region);
            _this.regionWeekValueList.push(data[i].browse);
          }
          _this.getRegionEchart('regionWeekEchart',_this.regionWeekNameList,_this.regionWeekValueList);
        })
      },

      getMonthData(){
        let _this = this;
        _this.regionMonthNameList = [];
        _this.regionMonthValueList = [];
        _this.api({
          url: "/statistics/postRegionByMonth",
          method: "get",
          params:this.man
        }).then(data => {
          console.log(data);
          for (var i = 0 ; i < data.length; i ++){
            _this.regionMonthNameList.push(data[i].region);
            _this.regionMonthValueList.push(data[i].browse);
          }
          _this.getRegionEchart('regionMonthEchart',_this.regionMonthNameList,_this.regionMonthValueList);
        })
      },
      getRegionEchart(id,names,values) {
        // this.charts = echarts.init(document.getElementById(id))
        let myChart = echarts.init(document.getElementById(id));
        this.chartMap[id] = myChart;
        let option = {
          show: true,
          backgroundColor: '#ffffff',
          tooltip: {
            trigger: 'axis',
            confine: true ,
            axisPointer: {
              type: 'shadow',
              shadowStyle:{shadowOffseY: '20px'}
            }
          },
          grid: {
            show:true,
            borderColor: '#ffffff',
            backgroundColor: '#ffffff',
            top: '10%',
            left: '2%',
            right: '6%',
            containLabel: true
          },
          xAxis: {
            offset:24,
            type: 'value',
            axisTick:{show: false},
            axisLine: {show: false,},
            splitLine:{
              lineStyle:{ type: 'dashed',}
            },
          },
          yAxis: {
            type: 'category',
            boundaryGap: false,
            axisTick: {interval: 0,},
            axisLine: {
              show: true,
              symbol:'Array',
              symbolSize:[1,44],
              lineStyle:{color: "#ccc"}
            },
            axisLabel:{
              show: true,
              color:'#333'
            },
            data: names
          },
          series: [{
            name: '男',
            type: 'bar',
            barWidth: 26,
            color: '#46a1ff',
            data: values
          }]
        }
        myChart.setOption(option);
      },

      getRegionEchart(id,names,values) {
        let myChart = echarts.init(document.getElementById(id));
        let option = {
          show: true,
          backgroundColor: '#ffffff',
          tooltip: {
            trigger: 'axis',
            confine: true ,
            axisPointer: {
              type: 'shadow',
              shadowStyle:{shadowOffseY: '20px'}
            }
          },
          grid: {
            show:true,
            borderColor: '#ffffff',
            backgroundColor: '#ffffff',
            top: '10%',
            left: '2%',
            right: '6%',
            containLabel: true
          },
          xAxis: {
            offset:24,
            type: 'value',
            axisTick:{show: false},
            axisLine: {show: false,},
            splitLine:{
              lineStyle:{ type: 'dashed',}
            },
          },
          yAxis: {
            type: 'category',
            boundaryGap: false,
            axisTick: {interval: 0,},
            axisLine: {
              show: true,
              symbol:'Array',
              symbolSize:[1,44],
              lineStyle:{color: "#ccc"}
            },
            axisLabel:{
              show: true,
              color:'#333'
            },
            data: names
          },
          series: [{
            name: '男',
            type: 'bar',
            barWidth: 26,
            color: '#46a1ff',
            data: values
          }]
        }
        myChart.setOption(option);
      },
      clickTab($event){
        let _this = this;
        let id = event.target.getAttribute('id');
        this.tabItem = id;
       if (id === "tab-week"){
          _this.getWeekData();
        }else if (id === "tab-month"){
          _this.getMonthData();
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

  .sum-gender {
    min-height: 250px;
    background-color: #fff;
    border: 1px solid rgba(215, 215, 215, 1);
    border-radius: 5px;
  }


  .statistics-title {
    font-size: 16px;
    font-weight: bold;
    color: #515151;
    padding: 20px 0 0 30px;
  }
</style>


