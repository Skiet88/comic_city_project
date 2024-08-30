<template>
  <div class="cart-summary" v-if="cartItems.length">
    <h2>Cart</h2>
    <div v-for="item in cartItems" :key="item.sku" class="cart-item">
      <!-- Remove Button -->
      <div class="remove-item" @click="removeItem(item.sku)">×</div>

      <!-- Item Image -->
      <div
          class="cart-item-image"
          :style="{ backgroundImage: `url(${getPhotoUrl(item.photo)})` }"
      ></div>

      <!-- Item Details -->
      <div class="cart-item-details">
        <p class="text-base">{{ item.name }}</p>
        <p class="text-sm">{{ item.price }} • {{ item.quantity }}</p>
      </div>
    </div>
    <div class="cart-footer">
      <div>
        <p class="text-sm">Total: {{ formatPrice(cartTotal) }}</p>
        <p class="text-sm">Items: {{ cartItems.length }}</p>
      </div>
      <div class="cart-footer-actions">
        <router-link to="/cart"><a href="#" class="view-cart">View Cart</a></router-link>
        <button class="checkout">Checkout</button>
      </div>
    </div>
    <Notification v-if="notification.message" :message="notification.message" />
  </div>
</template>

<script>
import { getCustomerCart, removeBookFromCart} from "@/services/cartService";
import Notification from "@/components/NotificationComponent.vue";

export default {
  name: 'CartSummary',
  components: { Notification },
  props: {
    cartId: {
      type: String,
      required: true
    }
  },
  data() {
    return {
      cart:'',
      cartItems: [],
      cartTotal: 0,
      notification: {
        message: '',
        duration: 3000
      }
    };
  },
  async mounted() {
    await this.fetchCart();
  },
  methods: {

    async fetchCart() {
      const userEmail=localStorage.getItem('userEmail');
      if (!userEmail) {
        console.error('User email not found. Please log in.');
        this.$router.push('/');
      }
      try {
        const response = await getCustomerCart(userEmail);
        this.cart = response.data;
        this.cartItems = this.cart.comicBooks;
        this.cartTotal = this.cartItems.reduce((total, item) => total + item.price * item.quantity, 0);

        // Emit the cart item count to the parent component
        this.$emit('update-cart-count', this.cartItems.length);
      } catch (error) {
        console.error('Error fetching cart items:', error);
      }
    },
    async removeItem(sku) {
      const isConfirmed = confirm('Are you sure you want to remove this item from the cart?');
      if (isConfirmed) {
        try {
          await removeBookFromCart(this.cart.cartId, sku);
          this.cartTotal=0
          await this.fetchCart(); // Refresh the cart list
          this.notification.message = 'Item removed successfully';
        } catch (error) {
          console.error('Error removing item from cart:', error);
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
/* Cart Summary styles */
.cart-summary {
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

.cart-item {
  display: flex;
  gap: 1rem;
  background-color: #2a3a4b;
  padding: 0.75rem;
  border-bottom: 1px solid #444;
  position: relative; /* Needed for positioning the remove button */
}

.cart-item:last-child {
  border-bottom: none;
}

.cart-item-image {
  background-size: cover;
  background-position: center;
  width: 70px;
  height: 70px;
  border-radius: 8px;
}

.cart-item-details {
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

.cart-footer {
  display: flex;
  justify-content: space-between;
  padding: 0.75rem 0;
}

.cart-footer-actions {
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
}

.view-cart {
  background-color: #2c3e50;
  color: white;
  text-align: center;
  font-weight: bold;
  padding: 0.5rem;
  border-radius: 8px;
  text-decoration: none;
}

.checkout {
  background-color: #3498db;
  color: white;
  font-weight: bold;
  padding: 0.5rem;
  border-radius: 8px;
  border: none;
  cursor: pointer;
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
