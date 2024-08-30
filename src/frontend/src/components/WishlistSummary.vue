<template>
  <div class="wishlist-summary" v-if="wishlistItems.length">
    <h2>Wishlist</h2>
    <div v-for="item in wishlistItems" :key="item.sku" class="wishlist-item">
      <!-- Remove Button -->
      <div class="remove-item" @click="removeItem(item.sku)">×</div>

      <!-- Item Image -->
      <div
          class="wishlist-item-image"
          :style="{ backgroundImage: `url(${getPhotoUrl(item.photo)})` }"
      ></div>

      <!-- Item Details -->
      <div class="wishlist-item-details">
        <p class="text-base">{{ item.name }}</p>
        <p class="text-sm">{{ item.price }}</p>
      </div>
    </div>
    <div class="wishlist-footer">
      <div class="wishlist-footer-actions">
        <p class="text-sm">Total: {{ formatPrice(wishListTotal) }}</p>
        <p class="text-sm">Items: {{ wishlistItems.length }}</p>
        <router-link to="/wishList" ><a href="#" class="view-wishlist">View Wishlist</a></router-link>
      </div>
    </div>
    <Notification v-if="notification.message" :message="notification.message" />
  </div>
</template>

<script>
import {getCustomerWishList,  removeBookFromWishList} from "@/services/wishlistService";
import Notification from "@/components/NotificationComponent.vue";

export default {
  name: 'WishlistSummary',
  components: { Notification },
  props: {
    wishListId: {
      type: String,
      required: true
    }
  },
  data() {
    return {
      wishList:'',
      wishlistItems: [],
      wishListTotal:0,
      notification: {
        message: '',
        duration: 3000
      }
    };
  },
  async mounted() {
    await this.fetchWishlist();
  },
  methods: {
    async fetchWishlist() {
      const userEmail = localStorage.getItem('userEmail'); //
      if (!userEmail) {
        console.error('User email not found. Please log in.');
        this.$router.push('/');
      }
      try {
        const response = await getCustomerWishList(userEmail);
        this.wishList=response.data
        this.wishlistItems = this.wishList.comicBooks; // Assuming the API returns the wishlist items
        this.wishListTotal = this.wishlistItems.reduce((total, item) => total + item.price , 0);
        this.$emit('update-wishList-count', this.wishlistItems.length);
      } catch (error) {
        console.error('Error fetching wishlist items:', error);
      }
    },
    async removeItem(sku) {
      const isConfirmed = confirm('Are you sure you want to remove this item from your wishlist?');
      if (isConfirmed) {
        try {
          await removeBookFromWishList(this.wishList.wishListId, sku);
          await this.fetchWishlist(); // Refresh the wishlist
          this.notification.message = 'Item removed successfully';
        } catch (error) {
          console.error('Error removing item from wishlist:', error);
        }
      }
    },
    getPhotoUrl(photo) {
      return `data:image/jpeg;base64,${photo}`;
    },
    formatPrice(price) {
      // Format the price as a string with the currency symbol
      return `R${price.toFixed(2)}`;
    }
  }
};
</script>

<style scoped>
/* Wishlist Summary styles */
.wishlist-summary {
  display: block;
  position: absolute;
  right: 0;
  top: 100%;
  width: 300px;
  background-color: #1e2a38;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.3);
  padding: 1rem;
  z-index: 1000;

}

.wishlist-item {
  display: flex;
  gap: 1rem;
  background-color: #2a3a4b;
  padding: 0.75rem;
  border-bottom: 1px solid #444;
  position: relative; /* Needed for positioning the remove button */
}

.wishlist-item:last-child {
  border-bottom: none;
}

.wishlist-item-image {
  background-size: cover;
  background-position: center;
  width: 70px;
  height: 70px;
  border-radius: 8px;
}

.wishlist-item-details {
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.text-sm {
  color: #a0b3c2;
}

.text-base {
  color: #e0e0e0;
}

.wishlist-footer {
  display: flex;
  justify-content: space-between;
  padding: 0.75rem 0;
}

.wishlist-footer-actions {
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
}

.view-wishlist {
  background-color: #2c3e50;
  color: white;
  text-align: center;
  font-weight: bold;
  padding: 0.5rem;
  border-radius: 8px;
  text-decoration: none;
}

/* Remove Button styles */
.remove-item {
  position: absolute;
  top: -8px;
  right: -8px;
  background-color: red;
  color: white;
  border-radius: 50%;
  width: 20px;
  height: 20px;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 16px;
  cursor: pointer;
}
</style>
