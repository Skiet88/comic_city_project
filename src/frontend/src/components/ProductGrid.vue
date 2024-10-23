<template>
  <div class="product-grid">
    <h2>{{ title }}</h2>
    <div class="grid">
      <ComicCard
          v-for="comic in comics"
          :key="comic.id"
          :comic="comic"
          :wishlist="wishlist"
          @comic-selected="handleComicSelected"
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
    handleComicSelected(sku) {
      this.$router.push({ name: 'view-item', params: { sku } });
    },
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
  justify-content: center;
  flex-wrap: wrap;
  gap: 10px;
}
</style>