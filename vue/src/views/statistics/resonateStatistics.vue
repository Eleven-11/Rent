<template>
  <div style="background-color: #F2f2f2;padding-bottom: 50px">
    <el-row class="header-container">
      <div class="header-title">关注统计</div>
    </el-row>
    <el-row>
      <el-col>
        <div class="panel">
          <el-tabs  v-model="activeName" :tab-position="top"
                    style="background-color: #fff;padding: 0 10px 0;border-radius: 10px;border: 1px solid rgba(215, 215, 215, 1) ;">
            <el-tab-pane name="first" label="用户关注排名" style="min-height: 380px" disabled="false"></el-tab-pane>
            <el-tab-pane label="总计" name="sum" style="min-height: 380px">总计</el-tab-pane>
            <el-tab-pane label="周排行" name="week" style="min-height: 380px">周排行</el-tab-pane>
            <el-tab-pane label="月排行" name="month" style="min-height: 380px">月排行</el-tab-pane>
          </el-tabs>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  export default {
    name: "resonateStatistics",
    data() {
      return {
        activeName: 'sum'
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
</style>

