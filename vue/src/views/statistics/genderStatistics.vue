<template>
  <div style="background-color: #F2f2f2;padding-bottom: 50px">
    <el-row class="header-container">
      <div class="header-title">性别统计</div>
    </el-row>
    <el-row class="panel">
      <el-col :span="8" class="panel-tab">
        <div class="panel-container">
          <div class="panel-title">全部</div>
          <div class="panel-num">{{all}}</div>
        </div>
      </el-col>
      <el-col :span="8" class="panel-tab">
        <div class="panel-container" style="background-color: #f073aa;">
          <div class="panel-title">女</div>
          <div class="panel-num">{{female}}</div>
        </div>
      </el-col>
      <el-col :span="8" class="panel-tab">
        <div class="panel-container" style="background-color: #7ba7ff;">
          <div class="panel-title">男</div>
          <div class="panel-num">{{man}}</div>
        </div>
      </el-col>
    </el-row>
    <el-row class="panel">
      <el-col>
        <div class="sum-gender">
          <div class="statistics-title">总性别统计</div>
          <div style="text-align: center">
              <div id="totalEchart" style="height: 240px;"></div>
          </div>
        </div>
      </el-col>
    </el-row>
    <el-row class="panel">
      <el-col>
        <div class="month-gender">
          <div class="statistics-title">月性别统计</div>
          <div style="text-align: center">
            <div id="monthEchart" style="height: 350px;"></div>
          </div>
        </div>
      </el-col>
    </el-row>
  </div>

</template>

<script>
  import echarts from 'echarts'
  export default {
    name: "genderStatistics",
    data () {
      return {
        all: 2000,
        female: 1000,
        man: 1000,
        total:{
          man:[320],
          women:[291]
        },
        month:{
          man:[99,19,29,39,19,28,91,19,19,28,61,16],
          women:[22,32,19,32,49,29,39,10,49,29,39,40],
          month:["Jan", "Feb", "Mar", "Apr", "May", "Jun",
            "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"]
        }
      }
    },

    mounted(){
      this.$nextTick(function() {
        this.getTotalEchart('totalEchart')
        this.getMonthEchart('monthEchart')
      })
    },

    methods: {

      getTotalData(){
        this.api({
          url: "/statistics/genderByAll",
          method: "get",
          params:this.noticeTemplate
        }).then(data => {

        })
      },
      getMonthData(){
        this.api({
          url: "/statistics/genderByMonth",
          method: "post",
          params:this.noticeTemplate
        }).then(data => {

        })
      },

      getTotalEchart(id) {
        this.charts = echarts.init(document.getElementById(id))
        this.charts.setOption({
          show: true,
          backgroundColor: '#ffffff',
          tooltip: {
            trigger: 'axis',
            position: function (point, params, dom, rect, size) {
              return [point[0], '26%']; // 固定在顶部
            },
            confine: true ,
            axisPointer: {
              type: 'shadow',
              shadowStyle:{shadowOffseY: '20px'}
            }
          },
          legend: {
            bottom:'10%',
            itemWidth: 10,
            itemHeight: 10,
            data: ['男', '女']
          },
          grid: {
            show:true,
            borderColor: '#ffffff',
            backgroundColor: '#ffffff',
            top: '7%',
            left: '4%',
            right: '6%',
            containLabel: true
          },
          xAxis: {
            type: 'value',
            axisTick:{show: false},
            axisLine: {show: false},
            splitLine:{
              lineStyle:{type: 'dashed'}
            },
            boundaryGap: [0, 0.01]
          },
          yAxis: {
            type: 'category',
            boundaryGap: false,
            axisLine: {
              show: true,
              lineStyle:{color: "#ccc"}
            },
            axisLabel:{
              show: true,
              color:'#333'
            },
            data: ['全部']
          },
          series: [
            {
              name: '男',
              type: 'bar',
              barWidth: '28px',
              color: '#7ba7ff',
              data: this.total.women
            }, {
              name: '女',
              type: 'bar',
              barWidth: '28px',
              barGap: '6%',
              color: '#f073aa',
              data: this.total.women
            }
          ]
        })
      },
      // get month echart
      getMonthEchart(id) {
        this.charts = echarts.init(document.getElementById(id))
        this.charts.setOption({
          show: true,
          backgroundColor: '#ffffff',
          tooltip: {
            trigger: 'axis',
            // position: function (point, params, dom, rect, size) {
            //   return [point[0], '50%'];
            // },
            confine: true ,
            axisPointer: {
              type: 'shadow',
              shadowStyle:{
                shadowOffseY: '20px'
              }
            }
          },
          legend: {
            bottom:'8%',
            itemWidth: 10,
            itemHeight: 10,
            data: ['男', '女']
          },
          grid: {
            show:true,
            borderColor: '#ffffff',
            backgroundColor: '#ffffff',
            top: '10%',
            left: '4%',
            right: '6%',
            containLabel: true
          },
          xAxis: {
            offset:8,
            type: 'value',
            axisTick:{show: false},
            axisLine: {show: false},
            splitLine:{
              lineStyle:{
                type: 'dashed',
              }
            },
            boundaryGap: [0, 0.01]
          },
          yAxis: {
            type: 'category',
            boundaryGap: false,
            axisTick: {
              interval: 0,
            },
            axisLine: {
              show: true,
              symbol:'Array',
              symbolSize:[1,22],
              lineStyle:{
                height: '300px',
                color: "#ccc"
              }
            },
            axisLabel:{
              show: true,
              color:'#333'
            },
            data: this.month.month
          },
          series: [
            {
              name: '男',
              type: 'bar',
              barWidth: 5,
              color: '#7ba7ff',
              data: this.month.man
            }, {
              name: '女',
              type: 'bar',
              barGap: '1%',
              barWidth: 5,
              color: '#f073aa',
              data: this.month.women
            }
          ]
        })
      }


    }
  }

</script>

<style scoped>
  .gender-bar{
    /*width: 930px;*/
    /*height: 240px;*/
    background-color: #FFF;
    margin-left: 45px;
    margin-right: 45px;
  }

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

  .panel-container {

    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    height: 150px;
    background-color: #FFCC66;
    border-radius: 10px;
    color: #FFF;
  }

  .panel-title {
    font-size: 20px;
    padding: 0 0 10px 0;
    font-weight: bold;
  }

  .panel-num {
    font-size: 36px;

  }

  .sum-gender {
    min-height: 250px;
    background-color: #fff;
    border: 1px solid rgba(215, 215, 215, 1);
  }

  .month-gender {
    min-height: 390px;
    background-color: #fff;
    border: 1px solid rgba(215, 215, 215, 1);
  }

  .statistics-title {
    font-size: 16px;
    font-weight: bold;
    color: #515151;
    padding: 20px 0 0 30px;
  }
</style>
