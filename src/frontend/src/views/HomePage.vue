<template>
  <div id="home-page">
    <NavBar />
    <HeroSection/>
    <ProductGrid title="New Arrivals" :comics="newArrivals" :wishlist="wishlist" @add-to-cart="addToCart" @toggle-wishlist="toggleWishlist" />
    <ProductGrid title="Hot Picks" :comics="hotPicks" :wishlist="wishlist" @add-to-cart="addToCart" @toggle-wishlist="toggleWishlist" />
    <FooterSection />
  </div>
</template>

<script>
import NavBar from '@/components/NavBar.vue';
import HeroSection from '@/components/HeroSection.vue';
import ProductGrid from '@/components/ProductGrid.vue';
import FooterSection from '@/components/FooterSection.vue';
import { getAllComicBooks } from '@/services/comicBookService';
import {addBookToCart, getCustomerCart} from '@/services/cartService';
import {addBookToWishList, getCustomerWishList} from '@/services/wishlistService';

export default {
  name: 'HomePage',
  components: {
    NavBar,
    HeroSection,
    ProductGrid,
    FooterSection
  },
  data() {
    return {
      newArrivals: [],
      hotPicks: [],
      wishlistItems: [],
      cartItems:[],
      cart:'',
      wishList:'',
      loading: true,
      error: null,
    };
  },
  created() {
    this.fetchComicBooks();
  },
  methods: {
    async fetchComicBooks() {
      try {
        const response = await getAllComicBooks();
        const allComics = response.data;

        // Split comics between new arrivals and hot picks
        this.newArrivals = allComics.slice(0, 5);
        this.hotPicks = allComics.slice(5, 10);

        this.loading = false;
      } catch (error) {
        this.error = 'Failed to load comic books.';
        this.loading = false;
      }
    },
    async addToCart(sku) {
      const userEmail=localStorage.getItem('userEmail');

      if (!userEmail) {
        console.error('No user email found. Please log in.');
        return;
      }

      try {
        const response=await getCustomerCart(userEmail);
        this.cart = response.data;
        this.cartItems=this.cart.comicBooks||[];
        await addBookToCart(this.cart.cartId, sku); // Send the correct SKU to the API
        alert('Comic added to cart!');
      } catch (error) {
        alert('Failed to add comic to cart.');
      }
    },
    async toggleWishlist(sku) {
      const userEmail=localStorage.getItem('userEmail');

      if (!userEmail) {
        console.error('No user email found. Please log in.');
        return;
      }

      try {
        // Call wishlist service to toggle the comic in the wishlist
        const response=await getCustomerWishList(userEmail);
        this.wishList = response.data;
        this.wishlistItems=this.wishList.comicBooks||[];
        await addBookToWishList (this.wishList.wishListId, sku);
        alert('Comic added to WishList!');
      } catch (error) {
        alert('Failed to update wishlist.');
      }
    }
  }
};
</script>

<style scoped>
/* Styles scoped to HomePage */
body {
  margin: 0;
  font-family: 'Roboto', sans-serif;
}
</style>
