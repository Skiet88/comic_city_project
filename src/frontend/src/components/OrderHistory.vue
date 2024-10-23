<template>
  <div id="order-history">
    <NavBar />
    <h1>My Orders</h1>
    <div v-if="orders.length > 0" class="orders-container">
      <div v-for="order in orders" :key="order.orderId" class="order-item">
        <h2>Order #{{ order.orderId }}</h2>
        <p><strong>Date:</strong> {{ order.orderDate }}</p>
        <p><strong>Total Amount:</strong> ${{ order.totalAmount }}</p>
        <p><strong>Status:</strong> {{ order.status }}</p>

        <h3>Items:</h3>
        <ul>
          <li v-for="item in order.comicBooks" :key="item.bookId">
            {{ item.title }} - {{ item.quantity }} x ${{ item.price }}
          </li>
        </ul>
      </div>
    </div>
    <div v-else>
      <p>You haven't placed any orders yet.</p>
    </div>
    <FooterSection />
  </div>
</template>

<script>
import NavBar from '@/components/NavBar.vue';
import FooterSection from '@/components/FooterSection.vue';
import { getCustomerOrders } from '@/services/orderService';
import {jwtDecode} from "jwt-decode";


export default {
  name: 'OrderHistory',
  components: {
    NavBar,
    FooterSection
  },
  data() {
    return {
      orders: [],
      customer: null
    };
  },
  created() {
    this.fetchOrders();
  },
  methods: {
    async fetchOrders() {

        const token = localStorage.getItem('authToken');
        if (token) {
          const decodedToken = jwtDecode(token);
          this.isAuthenticated = true;

          const response = await getCustomerOrders(decodedToken.sub);
          this.orders = response.data
      }
    }
  }
};
</script>

<style scoped>
#order-history {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
  font-family: 'Roboto', sans-serif;
}

h1 {
  text-align: center;
  margin-bottom: 20px;
  color: #333;
}

.orders-container {
  background-color: #f9f9f9;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

.order-item {
  margin-bottom: 20px;
  padding: 15px;
  background-color: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

.order-item h2 {
  font-size: 1.5em;
  margin-bottom: 10px;
  color: #444;
}

ul {
  list-style-type: none;
  padding-left: 0;
}

li {
  margin-bottom: 5px;
  color: #555;
}

p {
  margin-bottom: 10px;
}
</style>
