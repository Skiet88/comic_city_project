<template>
  <div id="view_item">
    <ProductComponent/>
  </div>
</template>

<script>
import ProductComponent from "@/components/productComponent.vue";
export default {
  name: 'viewItem',
  components: {
    ProductComponent
  }
}
</script>
