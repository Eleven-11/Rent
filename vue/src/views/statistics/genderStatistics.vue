<template>
  <div class="app-container">
    <div class="div-body">
      <div class="gender-bar">
        <div id="main" style="width: 930px;height: 240px;"></div>
      </div>
    </div>
  </div>
</template>

<script>
  import echarts from 'echarts'

    export default {
        name: "genderStatistics",
        data () {
          return {
            charts: '',
            opinion:['直接访问','邮件营销','联盟广告','视频广告','搜索引擎'],
            opinionData:[
              {value:335, name:'直接访问'},
              {value:310, name:'邮件营销'},
              {value:234, name:'联盟广告'},
              {value:135, name:'视频广告'},
              {value:1548, name:'搜索引擎'}
            ]
          }
        },

        mounted(){
          this.$nextTick(function() {
            this.getEchartData1('main')
          })
        },

        methods: {
          getEchartData1(id) {
              this.charts = echarts.init(document.getElementById(id))
              this.charts.setOption({
                show: true,
                backgroundColor: '#ffffff',
                title: {
                  top: '20px',
                  left: '3%',
                  text: '总性别统计'
                },
                tooltip: {
                  trigger: 'axis',
                  position: function (point, params, dom, rect, size) {
                    // 固定在顶部
                    return [point[0], '50%'];
                  },
                  confine: true ,
                  axisPointer: {
                    type: 'shadow',
                    shadowStyle:{
                      shadowOffseY: '20px'
                      // shadowBlur: 1
                    }
                  }
                },
                legend: {
                  bottom:10,
                  itemWidth: 10,
                  itemHeight: 10,
                  data: ['男', '女']
                },
                grid: {
                  show:true,
                  borderColor: '#ffffff',
                  backgroundColor: '#ffffff',
                  height: '140px',
                  top: '60px',
                  left: '5%',
                  right: '4%',
                  // bottom: '3%',
                  containLabel: true
                },
                xAxis: {
                  type: 'value',
                  axisTick:{
                    show: false
                  },
                  axisLine: {
                    show: false
                  },
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
                  axisLine: {
                    show: true,
                    lineStyle:{
                      color: "#999999"
                    }
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
                    color: '#6699ff',
                    data: [18203]

                  },
                  {
                    name: '女',
                    type: 'bar',
                    barWidth: '28px',
                    barGap: '14%',
                    color: '#ff6699',
                    data: [19325]

                  }
                ]
              })
            }
        }
    }

</script>

<style scoped>
  .div-body{
    background-color: #d3dce6;
  }

  .gender-bar{
    width: 1000px;
    height: 250px;
    background-color: #FFF;
    margin-left: 45px;
    margin-right: 45px;
  }

  #u7_div {
    border-width:0px;
    position:absolute;
    left:0px;
    top:0px;
    width:320px;
    height:150px;
    background:inherit;
    background-color:rgba(102, 153, 255, 1);
    border:none;
    border-radius:10px;
    -moz-box-shadow:none;
    -webkit-box-shadow:none;
    box-shadow:none;
  }
</style>
