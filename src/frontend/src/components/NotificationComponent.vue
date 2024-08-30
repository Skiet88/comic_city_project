<template>
  <div v-if="visible" class="notification">{{ message }}</div>
</template>

<script>
export default {
  props: {
    message: String,
    duration: {
      type: Number,
      default: 3000
    }
  },
  data() {
    return {
      visible: true
    };
  },
  mounted() {
    setTimeout(() => {
      this.visible = false;
    }, this.duration);
  }
};
</script>

<style scoped>
.notification {
  position: fixed;
  bottom: 20px;
  right: 20px;
  background-color: #444;
  color: #fff;
  padding: 1rem;
  border-radius: 5px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3);
}
</style>
