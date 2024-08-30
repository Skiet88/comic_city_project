<template>
  <div :class="themeClass">
    <NavBar/>

    <!-- Shopping Cart -->
    <div class="shopping-cart dark-theme">
      <h2>Your Shopping Cart</h2>
      <div v-if="cartItems.length">
        <table class="cart-table">
          <thead>
          <tr>
            <th>Product</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Subtotal</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="item in cartItems" :key="item.id">
            <td>
              <div class="product-info">
                <img :src="getPhotoUrl(item.photo)" class="product-image"/>
                <div class="product-name">{{ item.name }}</div>
              </div>
            </td>
            <td>{{ formatCurrency(item.price) }}</td>
            <td>
              <div class="quantity-controls">
                <button @click="decreaseQuantity(item)">-</button>
                <input type="number" v-model="item.quantity" min="1" class="quantity-input"/>
                <button @click="increaseQuantity(item)">+</button>
              </div>
            </td>
            <td>{{ formatCurrency(item.price * item.quantity) }}</td>
          </tr>
          </tbody>
        </table>

        <div class="cart-summary">
          <div class="cart-totals">
            <p><strong>Subtotal:</strong> {{ formatCurrency(cartTotal) }}</p>
            <p><strong>Shipping:</strong></p>
            <div>
              <label><input type="radio" value="Collection" v-model="shippingOption"> Collection from Comic Warehouse
                Bedfordview Johannesburg</label><br>
              <label><input type="radio" value="Courier" v-model="shippingOption"> Nationwide Courier: R149.00</label>
            </div>
            <p><strong>Total:</strong> {{ formatCurrency(cartTotal + shippingCost) }}</p>
          </div>
          <div class="cart-actions">
            <button class="checkout-button">Checkout</button>
          </div>
        </div>
      </div>
      <div v-else>
        <p>Your cart is empty.</p>
      </div>
    </div>
  </div>
</template>

<script>
import { getCustomerCart} from "@/services/cartService";
import NavBar from "@/components/NavBar.vue";

export default {
  components: {
    NavBar,

  },
  data() {
    return {
      cartItems: [],
      shippingOption: 'Collection',
      shippingCost: 0,
      isCartVisible: false,
      isWishlistVisible: false,
      cartItemCount: 0,
      wishListItemCount: 0,
      cart: '',
      themeClass: 'dark-theme'
    };
  },
  computed: {
    cartTotal() {
      return this.cartItems.reduce((total, item) => total + (item.price * item.quantity), 0);
    },
    totalWithShipping() {
      return this.cartTotal + this.shippingCost;
    }
  },
  watch: {
    shippingOption(newVal) {
      this.shippingCost = newVal === 'Courier' ? 149 : 0;
    }
  },
  methods: {
    formatCurrency(amount) {
      return 'R ' + amount.toFixed(2);
    },
    async fetchCart() {
      const userEmail = localStorage.getItem('userEmail');
      if (!userEmail) {
        console.error('User email not found. Please log in.');
        this.$router.push('/');
      }
      try {
        const response = await getCustomerCart(userEmail);
        this.cart = response.data ||{comicBooks: []};
        this.cartItems = this.cart.comicBooks;

      } catch (error) {
        console.error('Error fetching cart items:', error);
      }
    },

  increaseQuantity(item) {
    item.quantity++;
  },
  decreaseQuantity(item) {
    if (item.quantity > 1) {
      item.quantity--;
    }
  },
  getPhotoUrl(photo) {
    return `data:image/jpeg;base64,${photo}`;
  },

  toggleTheme() {
    this.themeClass = this.themeClass === 'dark-theme' ? 'light-theme' : 'dark-theme';
  },
}
,
created()
{
  this.fetchCart();
}
}
;
</script>

<style scoped>

/* Shopping Cart Styles */
.shopping-cart {
  padding: 20px;
  background-color: #2c2c2c; /* Darker background for contrast */
  color: #f0f0f0; /* Lighter text color for readability */
  max-width: 1000px;
  margin: 0 auto;
  border-radius: 8px;
  margin-top: 120px;
}

.cart-table {
  width: 100%;
  margin-bottom: 20px;
  border-collapse: collapse;
}

.cart-table th, .cart-table td {
  padding: 15px;
  border-bottom: 1px solid #444; /* Darker border for table rows */
  text-align: left;
}

.product-info {
  display: flex;
  align-items: center;
}

.product-image {
  width: 70px;
  height: 100px;
  margin-right: 10px;
}

.quantity-controls {
  display: flex;
  align-items: center;
}

.quantity-controls button {
  background-color: #ff5722; /* Orange button background */
  color: white;
  border: none;
  padding: 5px;
  cursor: pointer;
}

.quantity-controls input {
  width: 50px;
  text-align: center;
  margin: 0 5px;
}

.cart-summary {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.cart-totals p {
  margin: 10px 0;
}

.checkout-button {
  background-color: #ff5722; /* Orange button background */
  color: white;
  padding: 10px 20px;
  border: none;
  cursor: pointer;
  font-size: 1rem;
}

.checkout-button:hover {
  background-color: #e64a19; /* Darker orange for hover effect */
}

/* Dark Theme Adjustments */
.dark-theme .shopping-cart {
  background-color: #1b1b1b;
  color: white;
}

.dark-theme .quantity-controls button,
.dark-theme .checkout-button {
  background-color: #ff5722;
}

.dark-theme .quantity-controls button:hover,
.dark-theme .checkout-button:hover {
  background-color: #e64a19;
}

.dark-theme .cart-table th,
.dark-theme .cart-table td {
  border-bottom: 1px solid #333;
}

.dark-theme .cart-summary {
  color: #f0f0f0;
}

.dark-theme {
  background-color: #1b1b1b;
  color: white;
}

.dark-theme .navbar {
  background-color: #333;
  color: white;
}

.dark-theme .top-section {
  background-color: #444;
}

.dark-theme .middle-section {
  background-color: #333;
}

.dark-theme .bottom-section {
  background-color: #444;
}
</style>
