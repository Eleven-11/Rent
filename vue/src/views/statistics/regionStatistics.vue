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
          <el-tabs  v-model="activeName" :tab-position="top"
                    style="background-color: #fff;padding: 0 10px 0;border-radius: 10px;border: 1px solid rgba(215, 215, 215, 1) ;">
            <el-tab-pane name="first" label="浏览量排名" style="min-height: 380px" disabled="false"></el-tab-pane>
            <el-tab-pane label="总计" name="sum" style="min-height: 380px">
              <div id="regionEchart" style="height: 380px;"></div>
            </el-tab-pane>
            <el-tab-pane label="周统计" name="week" style="min-height: 380px">周排行</el-tab-pane>
            <el-tab-pane label="月统计" name="month" style="min-height: 380px">月排行</el-tab-pane>
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
        activeName:'sum'
      }
    },

    mounted(){
      this.$nextTick(function() {
        this.getRegionEchart('regionEchart')
      })
    },

    methods: {
      getRegionEchart(id) {
        this.charts = echarts.init(document.getElementById(id))
        this.charts.setOption({
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
            data: ["湖里区","集美区","思明区","海沧区","同安区","翔安区"]
          },
          series: [{
              name: '男',
              type: 'bar',
              barWidth: 26,
              color: '#46a1ff',
              data: [99,19,29,39,19,28]
            }
          ]
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


