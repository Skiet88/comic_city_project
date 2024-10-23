<template>
  <div class="view_item_page-container">
    <nav-bar/>
    <header class="page-header"></header>
    <main class="product-page">
      <div class="product-top">
        <div class="product-image"  >
          <img :src="comicImage" alt="Comic Cover"/>
        </div>
        <div class="product-description">
          <h2>{{ comic.name }}</h2>
          <p><strong>Price:</strong> R{{ comic.price }}</p>

          <div class="heart-icon" :class="{ 'active': isFavorite }" @click="toggleWishlist">
            <font-awesome-icon icon="heart"/>
          </div>

          <form @submit.prevent="addToCart">
            <div class="form-group">
              <label for="quantity">Quantity</label>
              <input type="number" id="quantity" v-model="quantity" min="1"/>
            </div>
            <div class="form-action">
              <button type="submit">Add to cart</button>
            </div>
          </form>

          <div class="in-stock">
            <p>Availability: {{ comic.availability }}</p>
          </div>

          <div class="product-tabs">
            <ul>
              <li :class="{ active: activeTab === 'description' }" @click="activeTab = 'description'">Description</li>
              <li :class="{ active: activeTab === 'details' }" @click="activeTab = 'details'">Details</li>
              <li :class="{ active: activeTab === 'ratings' }" @click="activeTab = 'ratings'">Ratings</li>
            </ul>
            <div v-if="activeTab === 'description'">
              <p>{{ comic.description }}</p>
            </div>
            <div v-if="activeTab === 'details'">
              <table>
                <tr v-for="(value, key) in comic" :key="key">
                  <td>{{ key }}</td>
                  <td>{{ value }}</td>
                </tr>
              </table>
            </div>
            <div v-if="activeTab === 'ratings'">
              <p>Customer Ratings: {{ comic.ratings }} / 5</p>
            </div>
          </div>
        </div>
      </div>
      <div class="Related-items">
        <h3>Related Items</h3>
        <div class="gallery">
          <img v-for="image in galleryImages" :src="image.src" :alt="image.alt" :key="image.src">
        </div>
      </div>
    </main>
    <footer class="page-footer">
      <footer-section/>
    </footer>
  </div>
</template>


<script>
import NavBar from '@/components/NavBar.vue';
import FooterSection from '@/components/FooterSection.vue';
import {addBookToCart, getCustomerCart} from "@/services/cartService";
import {getComicBook} from "@/services/comicBookService";

export default {
  name: 'ViewItem',
  components: {
    NavBar,
    FooterSection,
  },
  props: {
    wishlist: {
      type: Array,
      default: () => []
    }
  },
  data() {
    return {

      comic: '',
      quantity: 1,
      activeTab: 'description',
      sku:'',

      galleryImages: [
        { src: require('@/assets/ComicBookCover1.jpeg'), alt: 'Image 1' },
        { src: require('@/assets/ComicBookCover2.jpeg'), alt: 'Image 2' },
        { src: require('@/assets/ComicBookCover3.jpeg'), alt: 'Image 3' },
        { src: require('@/assets/ComicBookCover4.jpeg'), alt: 'Image 4' },
      ],
    };
  },
  created() {
    this.fetchComicDetails();
  },
  computed: {
    isFavorite() {
      if (this.comic && this.comic.sku) {
        const isInWishlist = this.wishlist.some((item) => item.sku === this.comic.sku);
        console.log(`Comic SKU: ${this.comic.sku} - Is in Wishlist: ${isInWishlist}`);
        return isInWishlist;
      }
      return false;
    },
    comicImage() {
      return this.comic.photo
          ? `data:image/jpeg;base64,${this.comic.photo}`
          : 'https://via.placeholder.com/150';
    },
  },

  methods: {
    async fetchComicDetails() {
      this.sku = this.$route.params.sku;
      console.log(this.sku)
      try {
        const response = await getComicBook(this.sku);
        this.comic = response.data;
      } catch (error) {
        console.error('Failed to fetch comic details:', error);
      }
    },
    async addToCart() {
      const userEmail = localStorage.getItem('userEmail');

      if (userEmail === null) {
        console.error('No user email found. Please log in.');
        return;
      }

      try {
        const response = await getCustomerCart(userEmail);
        this.cartItems = response.data.comicBooks || [];
        await addBookToCart(response.data.cartId, this.sku);
        alert('Comic added to cart!');
      } catch (error) {
        alert('Failed to add comic to cart.');
      }
    },
    toggleWishlist() {
      this.$emit('toggle-wishlist', this.comic.sku);
    },

    authorsList() {
      return this.comic.authors
          .map((author) => {
            const initial = author.name.firstName.charAt(0).toUpperCase();
            return `${initial}. ${author.name.lastName}`;
          })
          .join(', ');
    },
  }
}
</script>

<style scoped>
.view_item_page-container {
  display: flex;
  flex-direction: column;
  max-height: 100vh;
}

.page-header {
  background-color: black;
  padding: 10px;
  text-align: center;
}

.page-footer {
  background-color: black;
  padding: 20px;
  text-align: center;
  margin-top: auto;
}

.product-page {
  display: grid;
  flex-direction: column;
  align-items: center;
  flex-grow: 1;
  padding: 40px;
}

.product-top {
  display: flex;
  justify-content: center;
  gap: 20px;
  width: 100%;
  max-width: 1200px;
}

.product-image img {
  max-width: 300px;
  border-radius: 8px;
}

.product-description {
  max-width: 500px;
  min-width: 300px;
}

.product-description h2 {
  margin-bottom: 10px;
  font-size: 24px;
}

.product-description p {
  margin-bottom: 8px;
  line-height: 1.6;
}


.form-group {
  margin-bottom: 20px;
}

label {
  display: block;
  margin-bottom: 5px;
  font-weight: bold;
}

select,
input[type="number"] {
  width: 100%;
  padding: 8px;
  margin-bottom: 10px;
  border: 1px solid grey;
  border-radius: 4px;
  box-sizing: border-box;
}

.form-actions {
  display: flex;
  justify-content: flex-start;
  align-items: center;
  gap: 10px;
}

.in-stock p {
  margin-top: 20px;
  font-size: 16px;
}

.product-tabs {
  margin-top: 20px;
}

.product-tabs ul {
  display: flex;
  list-style: none;
  padding: 0;
  margin-bottom: 20px;
}

.product-tabs ul li {
  margin-right: 15px;
  padding: 10px 15px;
  background-color: #f5f5f5;
  cursor: pointer;
  border-radius: 4px;
}

.product-tabs ul li.active:hover {
  background-color: sandybrown;
  color: white;
}

.product-tabs div {
  padding: 20px;

  border-radius: 4px;
}

.product-tabs table {
  width: 100%;
  border-collapse: collapse;

}

.product-tabs table td {
  padding: 10px;
  border: 1px solid #ddd;
}

.product-tabs table td:first-child {
  font-weight: bold;
}

button {
  display: inline-block;
  background-color: black;
  color: white;
  padding: 10px 20px;
  border-radius: 4px;
  cursor: pointer;
}

button:hover {
  background-color: orange;
}

.Related-items {
  margin-top: 40px;
  text-align: center;
  width: 100%;
  max-width: 1200px;
}

.Related-items h3 {
  margin-bottom: 20px;
  font-size: 24px;
}

.gallery {
  display: flex;
  gap: 20px;
  justify-content: center;
}

.gallery img {
  max-width: 150px;
  border-radius: 8px;
  cursor: pointer;
}

.heart-icon {
  position: relative;
  cursor: pointer;
  color: grey;
  font-size: 24px;
  transition: color 0.3s ease;
}

.heart-icon:hover {
  color: red;
}
</style>