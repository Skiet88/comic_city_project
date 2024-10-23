<template>
  <div :class="themeClass">
    <NavBar />

    <!-- Wishlist -->
    <div class="wishlist">
      <h2>Your Wishlist</h2>
      <div v-if="wishlistItems.length">
        <table class="wishlist-table">
          <thead>
          <tr>
            <th>Product</th>
            <th>Price</th>
            <th>In Stock</th>
            <th>Action</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="item in wishlistItems" :key="item.id">
            <td>
              <div class="product-info">
                <img :src="getPhotoUrl(item.photo)" class="product-image" />
                <div class="product-name">{{ item.name }}</div>
              </div>
            </td>
            <td>{{ formatCurrency(item.price) }}</td>
            <td>{{ item.inStock ? 'Yes' : 'No' }}</td>
            <td>
              <button @click="remove(item)">Remove</button>
              <button
                  :disabled="!item.inStock"
                  @click="addToCart(item)"
              >
                Add to Cart
              </button>
            </td>
          </tr>
          </tbody>
        </table>
      </div>
      <div v-else>
        <p>Your wishlist is empty.</p>
      </div>
    </div>
  </div>
</template>

<script>
import {getCustomerWishList, removeBookFromWishList} from "@/services/wishlistService";
import {addBookToCart, getCustomerCart} from "@/services/cartService";

import NavBar from "@/components/NavBar.vue";
import {jwtDecode} from "jwt-decode";

export default {
  components: {
    NavBar,

  },
  data() {
    return {
      userEmail:'',
      wishlistItems: [],
      cartItemCount: 0,
      wishListItemCount: 0,
      wishList:'',
      isCartVisible: false,
      isWishlistVisible: false,
      themeClass: 'dark-theme'
    };
  },
  methods: {


    formatCurrency(amount) {
      return 'R ' + amount.toFixed(2);
    },
    async fetchWishlist() {

      const token = localStorage.getItem('authToken');
      if (token) {
        const decodedToken = jwtDecode(token);
        this.isAuthenticated = true;

        this.userEmail = decodedToken.sub;
        try {
          const response = await getCustomerWishList(this.userEmail);
          this.wishList = response.data;

          if (this.wishList.comicBooks.length > 0) {
            const randomIndex = Math.floor(Math.random() * this.wishList.comicBooks.length);
            this.wishList.comicBooks.forEach((item, index) => {
              item.inStock = index === randomIndex;
            });
          }

          this.wishlistItems = this.wishList.comicBooks;
        } catch (error) {
          console.error("Error fetching wishlist:", error);
        }
      }
    },
    async remove(item) {
      const isConfirmed = confirm('Are you sure you want to remove this item from your wishlist?');
      if (isConfirmed) {
        try {
          await removeBookFromWishList(this.wishList.wishListId, item.sku);
          await this.fetchWishlist(); // Refresh the wishlist
         alert('Item removed successfully');
        } catch (error) {
          console.error('Error removing item from wishlist:', error);
          this.notification.message = 'Failed to remove item.';
        }
      }
    },
    async addToCart(item) {

      try {
        const response=await getCustomerCart(this.userEmail);
        this.cart = response.data;
        this.cartItems=this.cart.comicBooks||[];
        await addBookToCart(this.cart.cartId, item.sku);
        alert(`${item.name} has been added to your cart!`);

      } catch (error) {
        alert('Failed to add comic to cart.');
      }

    },
    getPhotoUrl(photo) {
      return `data:image/jpeg;base64,${photo}`;
    },

    toggleTheme() {
      this.themeClass = this.themeClass === 'dark-theme' ? 'light-theme' : 'dark-theme';
    }
  },
  created() {
    this.fetchWishlist();
  }
};
</script>

<style scoped>

/* Wishlist Styles */
.wishlist {
  padding: 150px;
  background: linear-gradient(135deg, #1e1e1e, #3d0d0d); /* Dark gradient background */
  border-radius: 12px;
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.6);
  max-width: 1200px;
  margin: 20px auto;
  color: #f5f5f5; /* Light text color */
}

/* Heading */
h2 {
  font-size: 30px;
  color: #ff5722; /* Bright fire-orange color */
  margin-bottom: 20px;
  font-family: 'Roboto', sans-serif;
  font-weight: bold;
}

/* Table Styles */
.wishlist-table {
  width: 100%;
  border-collapse: collapse;
  margin-bottom: 20px;
}

.wishlist-table th {
  background: #212121; /* Dark background for headers */
  padding: 15px;
  font-weight: bold;
  color: #ff5722; /* Fire-orange color */
  border-bottom: 2px solid #ff5722; /* Fire-orange border */
}

.wishlist-table td {
  padding: 15px;
  border-bottom: 1px solid #333; /* Darker border color */
  vertical-align: middle;
}

/* Product Info Styles */
.product-info {
  display: flex;
  align-items: center;
}

.product-image {
  width: 100px;
  height: 100px;
  margin-right: 15px;
  border-radius: 8px;
  object-fit: cover;
  border: 3px solid #ff5722; /* Fire-orange border around image */
}

.product-name {
  font-size: 20px;
  color: #ff5722; /* Fire-orange color */
  font-weight: 500;
  max-width: 320px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

/* Button Styles */
button {
  background: #ff5722; /* Fire-orange background */
  color: white;
  padding: 10px 20px;
  border: none;
  border-radius: 6px;
  cursor: pointer;
  margin-right: 10px;
  font-size: 18px;
  transition: background-color 0.3s;
}

button:hover {
  background: #e64a19; /* Darker fire-orange on hover */
}

button:disabled {
  background-color: #777;
  cursor: not-allowed;
}

/* Empty Wishlist Message */
p {
  font-size: 18px;
  color: #f5f5f5;
}

/* Dark Theme */
.dark-theme .wishlist {
  background: linear-gradient(135deg, #1b1b1b, #2c2c2c); /* Darker gradient for dark theme */
}

.dark-theme .wishlist-table th,
.dark-theme .wishlist-table td {
  border-bottom: 1px solid #333;
}

.dark-theme .product-info .product-name {
  color: #ff5722;
}

.dark-theme .wishlist-table td button {
  background-color: #ff5722;
}

.dark-theme .wishlist-table td button:hover {
  background-color: #e64a19;
}
</style>
