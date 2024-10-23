
<template>
  <div class="addresses-page">
    <div class="main-content">
      <div class="addresses-header">
        <i class="fas fa-map-marker-alt"></i>
        <h1>Addresses</h1>
      </div>
      <p>The following addresses will be used on the checkout page by default</p>
      <div class="addresses">
        <div class="address-box">
          <h2>Billing address</h2>
          <div v-if="billingAddress">
            <p>{{ billingAddress.streetAddress }}, {{ billingAddress.suburb }}, {{ billingAddress.city }}, {{ billingAddress.postalCode }}</p>
            <p>Payment Method: {{ billingAddress.paymentMethod }}</p>
          </div>
          <div v-else>
            <p>You have not set up this type of address yet</p>
            <button class="add-address-button" @click="goToBillingAddress">ADD ADDRESS</button>
          </div>
        </div>
        <div class="address-box">
          <h2>Shipping address</h2>
          <p>You have not set up this type of address yet</p>
          <button class="add-address-button" @click="goToShippingAddress">ADD ADDRESS</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'AddressesPage',
  data() {
    return {
      billingAddress: null
    };
  },
  methods: {
    goToBillingAddress() {
      this.$router.push('/billing-address');
    },
    goToShippingAddress() {
      this.$router.push('/shipping-address');
    },
    fetchBillingAddress() {
      axios.get('/billing_address/get')
          .then(response => {
            this.billingAddress = response.data;
          })
          .catch(error => {
            console.error('Error fetching billing address:', error);
          });
    }
  },
  mounted() {
    this.fetchBillingAddress();
  }
};
</script>

<style scoped>
.addresses-page {
  display: flex;
  padding: 20px;
}

.main-content {
  width: 75%;
  padding-left: 20px;
}

.addresses-header {
  display: flex;
  align-items: center;
  margin-bottom: 20px;
}

.addresses-header i {
  font-size: 24px;
  margin-right: 10px;
}

.addresses-header h1 {
  font-size: 24px;
  margin: 0;
}

.addresses {
  display: flex;
  justify-content: space-between;
  margin-top: 20px;
}

.address-box {
  width: 45%;
  border: 1px solid #ccc;
  padding: 20px;
  text-align: center;
}

.address-box h2 {
  font-size: 18px;
  margin-bottom: 10px;
}

.address-box p {
  margin-bottom: 20px;
}

.add-address-button {
  padding: 10px 20px;
  background-color: #ccc;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.add-address-button:hover {
  background-color: #bbb;
}
</style>
