<!--//sethu-->





<template>
  <div class="billing-address-page">
    <div class="main-content">
      <div class="billing-header">
        <i class="fas fa-map-marker-alt"></i>
        <h1>Billing address</h1>
      </div>


      <form @submit.prevent="saveBillingAddress">
        <div class="form-group">
          <label for="payment-method">Payment Method</label>
          <select id="payment-method" v-model="paymentMethod" required>
            <option disabled value="">Select a payment method</option>
            <option value="credit-card">Credit Card</option>
            <option value="paypal">PayPal</option>
          </select>
        </div>

        <div class="form-group">
          <label for="street-address">Street Address</label>
          <input type="text" id="street-address" v-model="streetAddress" required />
        </div>

        <div class="form-group">
          <label for="suburb">Suburb</label>
          <input type="text" id="suburb" v-model="suburb" required />
        </div>

        <div class="form-group">
          <label for="city">City</label>
          <input type="text" id="city" v-model="city" required />
        </div>

        <div class="form-group">
          <label for="postal-code">Postal Code</label>
          <input type="text" id="postal-code" v-model="postalCode" required />
        </div>

        <div class="button-group">
          <button type="submit" class="save-button">SAVE</button>
          <button type="button" class="cancel-button" @click="cancel">CANCEL</button>
        </div>
      </form>


      <div v-if="successMessage" class="success-message">
        {{ successMessage }}
      </div>
      <div v-if="errorMessage" class="error-message">
        {{ errorMessage }}
      </div>


      <div v-if="billingAddresses.length">
        <h2>Your Billing Addresses</h2>
        <ul>
          <li v-for="address in billingAddresses" :key="address.id">
            {{ address.streetAddress }}, {{ address.suburb }}, {{ address.city }}, {{ address.postalCode }} - {{ address.paymentMethod }}
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>


<script>
import axios from 'axios';

export default {
  name: 'BillingAddressPage',
  data() {
    return {
      paymentMethod: '',
      streetAddress: '',
      suburb: '',
      city: '',
      postalCode: '',
      billingAddresses: [],
      successMessage: '',
      errorMessage: ''
    };
  },
  methods: {
    saveBillingAddress() {
      const billingAddress = {
        paymentMethod: this.paymentMethod,
        streetAddress: this.streetAddress,
        suburb: this.suburb,
        city: this.city,
        postalCode: this.postalCode
      };

      axios.post('/billing_address/create', billingAddress)
          .then(() => {
            this.successMessage = 'Billing address saved successfully!';
            this.errorMessage = '';
            this.fetchBillingAddresses();
            this.resetForm();
          })
          .catch(error => {
            this.successMessage = '';
            this.errorMessage = 'There was an error saving the billing address: ' + error.response?.data?.message || error.message;
          });
    },
    fetchBillingAddresses() {
      axios.get('/billing_address/getall')
          .then(response => {
            this.billingAddresses = response.data;
          })
          .catch(error => {
            this.errorMessage = 'There was an error fetching the billing addresses: ' + error.response?.data?.message || error.message;
          });
    },
    cancel() {
      this.resetForm();
    },
    resetForm() {
      this.paymentMethod = '';
      this.streetAddress = '';
      this.suburb = '';
      this.city = '';
      this.postalCode = '';
    }
  },
  mounted() {
    this.fetchBillingAddresses();
  }
};
</script>

<style scoped>
.billing-address-page {
  display: flex;
  padding: 20px;
}

.main-content {
  width: 75%;
  padding-left: 20px;
}

.billing-header {
  display: flex;
  align-items: center;
  margin-bottom: 20px;
}

.billing-header i {
  font-size: 24px;
  margin-right: 10px;
}

.billing-header h1 {
  font-size: 24px;
  margin: 0;
}

.form-group {
  margin-bottom: 15px;
}

.form-group label {
  display: block;
  margin-bottom: 5px;
  font-weight: bold;
}

.form-group input,
.form-group select {
  width: 100%;
  padding: 8px;
  box-sizing: border-box;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.button-group {
  display: flex;
  gap: 10px;
}

.save-button {
  padding: 10px 20px;
  background-color: #0000FF;
  color: #fff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.cancel-button {
  padding: 10px 20px;
  background-color: #000;
  color: #fff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.save-button:hover {
  background-color: #000099;
}

.cancel-button:hover {
  background-color: #333;
}

.success-message {
  color: green;
}

.error-message {
  color: red;
}
</style>
