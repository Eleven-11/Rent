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
            <el-tab-pane name="first" label="浏览器排名" style="min-height: 380px" disabled="false"></el-tab-pane>
            <el-tab-pane label="总计" name="likeSum" style="min-height: 380px">总计</el-tab-pane>
            <el-tab-pane label="周排行" name="week" style="min-height: 380px">周排行</el-tab-pane>
            <el-tab-pane label="月排行" name="month" style="min-height: 380px">月排行</el-tab-pane>
          </el-tabs>
        </div>
      </el-col>
      <el-col :span="12" class="panel-tab">
        <div>
          <el-tabs v-model="commentActiveName" :tab-position="top"
                   style="background-color: #fff;padding: 0 10px 0;border-radius: 10px;border: 1px solid rgba(215, 215, 215, 1) ;">
            <el-tab-pane name="first" label="评论排行" style="min-height: 380px" disabled="false"></el-tab-pane>
            <el-tab-pane label="总计" name="commentSum" style="min-height: 380px">总计</el-tab-pane>
            <el-tab-pane label="周排行" name="commentWeek" style="min-height: 380px">周排行</el-tab-pane>
            <el-tab-pane label="月排行" name="commentMonth" style="min-height: 380px">月排行</el-tab-pane>
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
            <el-tab-pane label="总计" name="collectSum" style="min-height: 380px">总计</el-tab-pane>
            <el-tab-pane label="周排行" name="collectWeek" style="min-height: 380px">周排行</el-tab-pane>
            <el-tab-pane label="月排行" name="collectMonth" style="min-height: 380px">月排行</el-tab-pane>
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
            <el-tab-pane label="周统计" name="priceWeek" style="min-height: 380px">周排行</el-tab-pane>
            <el-tab-pane label="月统计" name="priceMonth" style="min-height: 380px">月排行</el-tab-pane>
          </el-tabs>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  export default {
    name: "postStatistics",
    data() {
      return {
        likeActiveName: 'likeSum',
        commentActiveName: 'commentSum',
        priceActiveName: 'priceSum',
        collectActiveName: "collectSum"
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

</style>
