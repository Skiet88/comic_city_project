<template>
  <div id="cart-checkout">
    <NavBar />
    <h1>Checkout</h1>
    <CartSummary :cart="cartItems" cart-id=""/>

    <div class="checkout-form">
      <h2>Shipping Information</h2>
      <form @submit.prevent="placeOrder">
        <div class="form-group">
          <label for="shippingName">Full Name</label>
          <input type="text" id="shippingName" v-model="shippingName" required />
        </div>

        <div class="form-group">
          <label for="shippingAddress">Address</label>
          <input type="text" id="shippingAddress" v-model="shippingAddress" required />
        </div>

        <div class="form-group">
          <label for="shippingCity">City</label>
          <input type="text" id="shippingCity" v-model="shippingCity" required />
        </div>

        <div class="form-group">
          <label for="shippingPostalCode">Postal Code</label>
          <input type="text" id="shippingPostalCode" v-model="shippingPostalCode" required />
        </div>

        <h2>Payment Information</h2>
        <div class="form-group">
          <label for="paymentMethod">Payment Method</label>
          <select id="paymentMethod" v-model="paymentMethod" required>
            <option value="creditCard">Credit Card</option>
            <option value="paypal">PayPal</option>
          </select>
        </div>

        <div v-if="paymentMethod === 'creditCard'">
          <div class="form-group">
            <label for="cardNumber">Card Number</label>
            <input type="text" id="cardNumber" v-model="cardNumber" required />
          </div>

          <div class="form-group">
            <label for="cardExpiration">Expiration Date</label>
            <input type="text" id="cardExpiration" v-model="cardExpiration" required />
          </div>

          <div class="form-group">
            <label for="cardCVC">CVC</label>
            <input type="text" id="cardCVC" v-model="cardCVC" required />
          </div>
        </div>

        <button type="submit" class="submit-button">Place Order</button>
      </form>
    </div>

    <FooterSection />
  </div>
</template>

<script>
import NavBar from '@/components/NavBar.vue';
import CartSummary from '@/components/CartSummary.vue';
import FooterSection from '@/components/FooterSection.vue';
import { createOrder } from '@/services/orderService';

import {getCustomerCart} from "@/services/cartService";
import {jwtDecode} from "jwt-decode"; // Import the service for creating orders

import CustomerService from "@/services/CustomerService";
import {getCustomerCart} from "@/services/cartService";


export default {
  name: 'CartCheckout',
  components: {
    NavBar,
    CartSummary,
    FooterSection
  },
  data() {
    return {
      cartItems: [],
      shippingName: '',
      shippingAddress: '',
      shippingCity: '',
      shippingPostalCode: '',
      paymentMethod: 'creditCard',
      cardNumber: '',
      cardExpiration: '',
      cardCVC: '',
      error: null,
      customer: null
    };
  },
  created() {
    this.cartItems = this.$route.params.cartItems || [];
  },
  methods: {
    // checkAuthStatus() {
    //   const token = localStorage.getItem('authToken');
    //   if (token) {
    //     const decodedToken = jwtDecode(token);
    //     this.isAuthenticated = true;
    //
    //     this.customer = CustomerService.fetchCustomerByEmail(decodedToken.sub);
    //
    //   }
    //
    // },
    checkAuthStatus() {
      const token = localStorage.getItem('authToken');
      if (token) {
        const decodedToken = jwtDecode(token);
        this.isAuthenticated = true;


        fetch(`/api/comiccity/Customer/getByEmail/${decodedToken.sub}`, {
          method: 'GET',
          headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json',
          }
        })
            .then(response => {
              if (!response.ok) {
                throw new Error('Failed to fetch user details');
              }
              return response.json();
            })
            .then(data => {
              this.customer = data;
              alert(this.customer);
            })
            .catch(error => {
              console.error('Error fetching user details:', error);
            });
      }
    },
    async placeOrder() {
      const token = localStorage.getItem('authToken');
      if (token) {
        const decodedToken = jwtDecode(token);
        this.isAuthenticated = true;


      const response = await getCustomerCart(decodedToken.sub);
      this.cart = response.data;
      this.cartItems = this.cart.comicBooks || [];
      const orderData = {
        orderId: null,
        orderDate: new Date().toISOString().split('T')[0],
        comicBooks: this.cartItems,
        totalAmount: this.calculateTotalAmount(),
        customer: this.customer,
        status: null,
      }

      console.log("Hi",orderData);

      try {
        const response = await createOrder(orderData);
        alert('Order placed successfully!');
        console.log('Order placed with data:', response);
      } catch (error) {
        alert('Failed to place order.');
        console.error('Error placing order:', error);
      }
      }
    },

    calculateTotalAmount() {
      return this.cartItems.reduce((total, item) => total + (item.price * item.quantity), 0).toFixed(2);
    }
  },
  mounted() {
    this.checkAuthStatus();
  },
};
</script>


<style scoped>
#cart-checkout {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
  font-family: 'Roboto', sans-serif;
  background-color: #f9f9f9;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

h1 {
  text-align: center;
  font-size: 2.5em;
  margin-bottom: 20px;
  color: #333;
}

.checkout-form {
  background-color: #fff;
  padding: 20px;
  border-radius: 8px;
  margin-top: 20px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

h2 {
  font-size: 1.8em;
  margin-bottom: 10px;
  color: #444;
}

.form-group {
  margin-bottom: 15px;
}

.form-group label {
  display: block;
  margin-bottom: 5px;
  font-weight: bold;
  color: #666;
}

.form-group input,
.form-group select {
  width: 100%;
  padding: 10px;
  font-size: 1em;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}

.form-group input:focus,
.form-group select:focus {
  border-color: #007bff;
  outline: none;
}

.submit-button {
  display: inline-block;
  padding: 10px 20px;
  font-size: 1.2em;
  color: #fff;
  background-color: #007bff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s ease;
  margin-top: 20px;
}

.submit-button:hover {
  background-color: #0056b3;
}

@media (max-width: 768px) {
  #cart-checkout {
    padding: 10px;
  }

  h1 {
    font-size: 2em;
  }

  .checkout-form {
    padding: 15px;
  }

  .submit-button {
    width: 100%;
  }
}
</style>
