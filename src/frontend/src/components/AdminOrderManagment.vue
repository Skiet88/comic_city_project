<template>
  <div class="table-container">
    <h2>Customer Orders</h2>

    <div class="filters card p-3 mb-4">
      <div class="row g-3">
        <div class="col-md-4">
          <input
              v-model="searchCustomerName"
              @input="fetchOrdersByCustomerName"
              type="text"
              class="form-control strong-border"
              placeholder="Search by Customer Name"
          />
        </div>

        <div class="col-md-3">
          <input
              v-model="startDate"
              type="date"
              class="form-control strong-border"
              placeholder="Start Date"
          />
        </div>
        <div class="col-md-3">
          <input
              v-model="endDate"
              type="date"
              class="form-control strong-border"
              placeholder="End Date"
          />
        </div>
        <div class="col-md-2">
          <button @click="fetchOrdersByDateRange" class="btn btn-primary w-100 strong-border">
            Filter by Date
          </button>
        </div>
      </div>
    </div>

    <p v-if="loading" class="loading-message">
      Loading...
      <span class="spinner-border spinner-border-sm"></span>
    </p>
    <p v-if="errorMsg" class="error-message">{{ errorMsg }}</p>

    <table v-if="!loading && customerOrders.length > 0">
      <thead>
      <tr>
        <th>Order ID</th>
        <th>Customer ID</th>
        <th>Order Date</th>
        <th>Order Total (R)</th>
        <th>Status</th>
        <th>Actions</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="order in customerOrders" :key="order.orderId">
        <td>{{ order.orderId }}</td>
        <td>{{ order.customer.customerId }}</td>
        <td>{{ order.orderDate }}</td>
        <td>{{ order.totalAmount.toFixed(2) }}</td>
        <td>{{ order.status }}</td>
        <td class="actions">
          <button disabled @click="editOrder(order.customerOrderId)">Edit</button>
          <button @click="confirmDelete(order.customerOrderId)" class="delete">Delete</button>
        </td>
      </tr>
      <tr v-if="customerOrders.length === 0">
        <td colspan="6" class="no-data">No orders available.</td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap';

export default {
  data() {
    return {
      customerOrders: [],
      searchCustomerName: '',
      startDate: '',
      endDate: '',
      loading: false,
      errorMsg: '',
    };
  },
  methods: {
    handleError(message) {
      this.errorMsg = message;
      this.loading = false;
      console.error(message);
    },

    fetchOrdersByCustomerName() {
      if (!this.searchCustomerName) {
        this.fetchAllOrders();
        return;
      }
      this.loading = true;
      fetch(`/api/comiccity/CustomerOrder/search/name/${this.searchCustomerName}`)
          .then((response) => response.json())
          .then((data) => {
            this.customerOrders = data;
            this.loading = false;
          })
          .catch(() => {
            this.handleError('Error fetching data by customer name');
          });
    },

    fetchOrdersByDateRange() {
      if (!this.startDate || !this.endDate) {
        this.handleError('Please enter both start and end dates');
        return;
      }
      if (new Date(this.startDate) > new Date(this.endDate)) {
        this.handleError('Start date cannot be later than end date');
        return;
      }
      this.loading = true;
      fetch(`/api/comiccity/CustomerOrder/search/dateRange?startDate=${this.startDate}&endDate=${this.endDate}`)
          .then((response) => response.json())
          .then((data) => {
            this.customerOrders = data;
            this.loading = false;
          })
          .catch(() => {
            this.handleError('Error fetching data by date range');
          });
    },

    fetchAllOrders() {
      this.loading = true;
      fetch(`/api/comiccity/CustomerOrder/getAll`)
          .then((response) => response.json())
          .then((data) => {
            this.customerOrders = data;
            console.log('Fetched Orders Data:', data);
            this.loading = false;
          })
          .catch(() => {
            this.handleError('Error fetching all orders');
          });
    },

    deleteOrder(orderId) {
      fetch(`/api/comiccity/CustomerOrder/delete/${orderId}`, {
        method: 'DELETE',
      })
          .then((response) => {
            if (!response.ok) {
              throw new Error('Network response was not ok');
            }
            this.customerOrders = this.customerOrders.filter((order) => order.customerOrderId !== orderId);
          })
          .catch(() => {
            this.handleError('Error deleting the order');
          });
    },
  },

  mounted() {
    this.fetchAllOrders();
  },
};
</script>

<style scoped>

.table-container {
  width: 100%;
  max-width: 1000px;
  margin: 0 auto;
  padding: 20px;
  background-color: #f9f9f9;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  border: 4px solid #333;
}

.table-container h2 {
  text-align: center;
  margin-bottom: 20px;
  font-family: 'Comic Sans MS', sans-serif;
  font-weight: bold;
  font-size: 2rem;
  color: #333;
}

table {
  width: 100%;
  border-collapse: collapse;
  margin-bottom: 20px;
  font-family: 'Comic Sans MS', sans-serif;
}

table th,
table td {
  padding: 12px;
  text-align: left;
  border-bottom: 2px solid #333;
}

table th {
  background-color: #f2f2f2;
  font-weight: bold;
  text-transform: uppercase;
  border-top: 4px solid #333;
}

table tr:nth-child(even) {
  background-color: #fff;
}

table tr:nth-child(odd) {
  background-color: #f9f9f9;
}

table tr:hover {
  background-color: #ffcc00;
  transform: scale(1.02);
}

.actions {
  display: flex;
  gap: 10px;
}

.actions button {
  padding: 8px 12px;
  border: 2px solid #333;
  border-radius: 4px;
  cursor: pointer;
  font-family: 'Comic Sans MS', sans-serif;
  font-weight: bold;
}

.actions button.edit {
  background-color: #007bff;
  color: white;
  border: 2px solid #333;
}

.actions button.delete {
  background-color: #dc3545;
  color: white;
  border: 2px solid #333;
}

.actions button:hover {
  background-color: #ffcc00;
  color: #333;
  border-color: #333;
}

.error-message,
.loading-message {
  text-align: center;
  margin-top: 20px;
  background-color: #ff0066;
  color: white;
  padding: 10px;
  border-radius: 8px;
  border: 2px solid #333;
  display: inline-block;
}

.no-data {
  text-align: center;
  color: #999;
  font-style: italic;
  font-family: 'Comic Sans MS', sans-serif;
  border: 2px dashed #333;
  padding: 10px;
  border-radius: 10px;
  background-color: #ffcc00;
}

.strong-border {
  border: 2px solid #333 !important;
  border-radius: 6px;
}

.spinner-border {
  width: 1.5rem;
  height: 1.5rem;
  border: 0.2em solid rgba(0, 0, 0, 0.1);
  border-right-color: transparent;
  border-radius: 50%;
  animation: spinner-border 0.75s linear infinite;
}

@keyframes spinner-border {
  0% { transform: rotate(0deg); }
  100% { transform: rotate(360deg); }
}
</style>
