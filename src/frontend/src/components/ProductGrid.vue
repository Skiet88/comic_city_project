<template>
  <div class="product-grid">
    <h2>{{ title }}</h2>
    <div class="grid">
      <ComicCard
          v-for="comic in comics"
          :key="comic.id"
          :comic="comic"
          :wishlist="wishlist"
          @add-to-cart="handleAddToCart"  @toggle-wishlist="handleToggleWishlist"/>
    </div>
  </div>
</template>

<script>
import ComicCard from './ComicCard.vue';

export default {
  name: 'ProductGrid',
  components: {
    ComicCard
  },
  props: {
    title: String,
    comics: Array,
    wishlist:Array
  },
  methods: {
    handleAddToCart(sku) {
      this.$emit('add-to-cart', sku); // Emit the SKU to the parent component
    },
    handleToggleWishlist(sku) {
      this.$emit('toggle-wishlist', sku);
    }
  }
};
</script>
<style scoped>
.product-grid {
  margin: 20px;
}
.grid {
  display: flex;
  justify-content: center; /* Centers the items */
  flex-wrap: wrap;
  gap: 10px; /* Adjust this value to control spacing */
}
</style>