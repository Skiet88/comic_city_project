<template>
  <div class="shipping-address-page">
    <div class="main-content">
      <div class="shipping-header">
        <font-awesome-icon :icon="['fas', 'map-marker-alt']" />
        <h1>Shipping Address</h1>
      </div>
      <form @submit.prevent="saveShippingAddress">
        <div class="form-group">
          <label for="street-address">Street Address</label>
          <input type="text" id="street-address" v-model="streetAddress" />
        </div>
        <div class="form-group">
          <label for="suburb">Suburb</label>
          <input type="text" id="suburb" v-model="suburb" />
        </div>
        <div class="form-group">
          <label for="city">City</label>
          <input type="text" id="city" v-model="city" />
        </div>
        <div class="form-group">
          <label for="postal-code">Postal Code</label>
          <input type="text" id="postal-code" v-model="postalCode" />
        </div>
        <div class="button-group">
          <button type="submit" class="save-button">SAVE</button>
          <button type="button" class="cancel-button" @click="cancel">CANCEL</button>
        </div>
      </form>

      <!-- Display fetched shipping addresses -->
      <div v-if="shippingAddresses.length">
        <h2>Your Shipping Addresses</h2>
        <ul>
          <li v-for="address in shippingAddresses" :key="address.id">
            {{ address.streetAddress }}, {{ address.suburb }}, {{ address.city }}, {{ address.postalCode }}
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'ShippingAddressPage',
  data() {
    return {
      streetAddress: '',
      suburb: '',
      city: '',
      postalCode: '',
      shippingAddresses: []
    };
  },
  methods: {
    saveShippingAddress() {

      const shippingAddress = {
        streetAddress: this.streetAddress,
        suburb: this.suburb,
        city: this.city,
        postalCode: this.postalCode
      };

      axios.post('/shipping_address/create', shippingAddress)
          .then(response => {
            console.log('Shipping address saved:', response.data);
            this.fetchShippingAddresses(); // Fetch the latest shipping addresses after saving
          })
          .catch(error => {
            console.error('There was an error saving the shipping address:', error);
          });


      this.streetAddress = '';
      this.suburb = '';
      this.city = '';
      this.postalCode = '';
    },
    fetchShippingAddresses() {
      axios.get('/shipping_address/getall')
          .then(response => {
            this.shippingAddresses = response.data;
            console.log('Fetched shipping addresses:', this.shippingAddresses);
          })
          .catch(error => {
            console.error('There was an error fetching the shipping addresses:', error);
          });
    },
    cancel() {

      this.streetAddress = '';
      this.suburb = '';
      this.city = '';
      this.postalCode = '';
    }
  },
  mounted() {
    this.fetchShippingAddresses();
  }
};
</script>

<style scoped>
.shipping-address-page {
  display: flex;
  padding: 20px;
}

.main-content {
  width: 100%;
  padding-left: 20px;
}

.shipping-header {
  display: flex;
  align-items: center;
  margin-bottom: 20px;
}

.shipping-header i {
  font-size: 24px;
  margin-right: 10px;
}

.shipping-header h1 {
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

.form-group input {
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
</style>
