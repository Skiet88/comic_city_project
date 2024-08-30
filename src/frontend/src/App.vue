<template>
  <div id="app">

    <Layout>
      <router-view></router-view>
    </Layout>

   


  </div>
</template>

<script>

import Layout from "@/components/MainLayout.vue";

export default {
  name: 'App',
  components: {
    Layout
  }
}

</script>

<style>
/* Global styles */
body {
  margin: 0;
  font-family: 'Roboto', sans-serif;
}
</style>
