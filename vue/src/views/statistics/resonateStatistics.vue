<template>
  <div style="background-color: #F2f2f2;padding-bottom: 50px">
    <el-row class="header-container">
      <div class="header-title">关注统计</div>
    </el-row>
    <el-row>
      <el-col>
        <div class="panel">
          <el-tabs v-model="activeName" tab-position="top"
                   style="background-color: #fff;padding: 0 10px 0;border-radius: 10px;border: 1px solid rgba(215, 215, 215, 1) ;">
            <el-tab-pane name="first" label="用户关注排名" style="min-height: 380px" :disabled="true"></el-tab-pane>
            <!--<el-tab-pane label="总计" name="sum" style="min-height: 380px"></el-tab-pane>-->

            <el-tab-pane label="周排行" name="sum" style="min-height: 380px">
              <div class="sum_panel_container">
                <div class="sum_panel">
                  <ProcessTitle v-for="(item,index) in weekLeftList" :key="index" color="#ff6666" :no="index+1" :max="weekMax" :cur="item.fans" :content="item.name">
                  </ProcessTitle>
                </div>
                <div class="sum_panel">
                  <ProcessTitle v-for="(item,index) in weekRightList" :key="index" color="#ff6666" :no="index+6" :max="weekMax" :cur="item.fans" :content="item.name">
                  </ProcessTitle>
                </div>
              </div>
            </el-tab-pane>

            <el-tab-pane label="月排行" name="month" style="min-height: 380px">
              <div class="sum_panel_container">
                <div class="sum_panel">
                  <ProcessTitle v-for="(item,index) in monthLeftList" :key="index" color="#ff6666" :no="index+1" :max="monthMax" :cur="item.fans" :content="item.name">
                  </ProcessTitle>
                </div>
                <div class="sum_panel">
                  <ProcessTitle v-for="(item,index) in monthRightList" :key="index" color="#ff6666" :no="index+6" :max="monthMax" :cur="item.fans" :content="item.name">
                  </ProcessTitle>
                </div>
              </div>
            </el-tab-pane>
          </el-tabs>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import ProcessTitle from './ProcessTitle';

  export default {
    name: "resonateStatistics",
    data() {
      return {
        activeName: 'sum',
        weekMax:1,
        weekLeftList:[],
        weekRightList:[],
        monthMax:1,
        monthLeftList:[],
        monthRightList:[]
      }
    },
    components: {
      ProcessTitle
    },
    created() {
      this.getWeekData();
      this.getMonthData();
    },

    methods:{

      //周数据查询
      getWeekData(){
        this.weekMax = 1;
        this.api({
          url: "/statistics/userFollowByWeek",
          method: "get",
          // params:this.man
        }).then(data => {
          let size = data.length;
          size = size > 10 ? 10 : size;
          if (size > 1){
            this.weekMax = data[0].fans;
          }
          //补足显示
          if (size < 10 ){
            for (let i = size ; i < 10 ; i ++){
              data.push({name:"暂无数据",fans:0,follower:"-1"});
            }
          }
          this.weekLeftList = data.slice(0,5);
          this.weekRightList = data.slice(5,10);
        })
      },
      //月数据查询
      getMonthData(){
        this.monthMax = 1;
        this.api({
          url: "/statistics/userFollowByMonth",
          method: "get",
          // params:this.man
        }).then(data => {
          let size = data.length;
          size = size > 10 ? 10 : size;
          if (size > 1){
            this.monthMax = data[0].fans;
          }
          //补足显示
          if (size < 10 ){
            for (let i = size ; i < 10 ; i ++){
                data.push({name:"暂无数据",fans:0,follower:"-1"});
            }
          }
          this.monthLeftList = data.slice(0,5);
          this.monthRightList = data.slice(5,10);
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

