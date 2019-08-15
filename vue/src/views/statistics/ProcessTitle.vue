<template>
  <div class="container">
    <div class="title">
      <span>{{no.toString().padStart(2,'0')}}.</span>
      <span>{{content}}</span>
    </div>
    <div class="content">
      <el-progress class="left" :show-text="true" :text-inside="true"
                   :color="color" :stroke-width="16" :percentage="cur/max*100"
                   :format="format"></el-progress>
      <span>{{max}}</span>
    </div>
  </div>
</template>

<script>
  export default {
    name: "ProcessTitle",
    props: ['no', 'content', 'max', 'cur', 'color'],
    methods: {
      format(percentage) {
        return percentage === 100 ? '满' : `${percentage}%`;
      }
    }
  }
</script>

<style scoped>
  .container {
    height: 70px;
    border-bottom: 1px solid #DCDFE6;
    padding: 10px 20px;
  }

  .title {
    padding-bottom: 10px;
  }

  .content {
    display: flex;
  }

  .left {
    flex: 1;
    padding-right: 10px;
  }
</style>
