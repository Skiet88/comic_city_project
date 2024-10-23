<template>
  <div class="create-customer-container">
    <h1 class="title">Create a Customer Account</h1>

    <SpinnerComponent :visible="loading" />
    <form @submit.prevent="handleSubmit">
      <label for="firstName">First Name:</label>
      <input id="firstName" v-model="customer.firstName" placeholder="First Name" required/>

      <label for="middleName">Middle Name:</label>
      <input id="middleName" v-model="customer.middleName" placeholder="Middle Name"/>

      <label for="lastName">Last Name:</label>
      <input id="lastName" v-model="customer.lastName" placeholder="Last Name" required/>

      <label for="email">Email:</label>
      <input id="email" type="email"  v-model="customer.contact.email" placeholder="Email" required/>

      <label for="mobile">Mobile:</label>
      <input id="mobile" type="tel" pattern="\d{10}" v-model="customer.contact.mobile" placeholder="Mobile"/>

      <label for="billingAddress">Billing Address:</label>
      <input id="billingAddress" v-model="customer.contact.billingAddress" placeholder="Billing Address"/>

      <label for="shippingAddress">Shipping Address:</label>
      <input id="shippingAddress" v-model="customer.contact.shippingAddress" placeholder="Shipping Address"/>

      <label for="password">Password:</label>
      <input id="password" type="password" v-model="customer.password" placeholder="Password" required/>

      <button type="submit" :disabled="!isValidForm">Create Account</button>
    </form>
    <p v-if="errorMessage" class="error-message">{{ errorMessage }}</p>
  </div>
</template>

<script>
import CustomerService from '@/services/CustomerService';
import {assignCartToCustomer} from "@/services/cartService";
import {assignWishListToCustomer} from "@/services/wishlistService";
import SpinnerComponent from "@/components/SpinnerComponent.vue";
import * as ContactService from "@/services/ContactService";

export default {
  components:{
    SpinnerComponent,
  },
  data() {
    return {  loading: false,
      customer: {
        firstName: '',
        middleName: '',
        lastName: '',
        password: '',
        contact: {
          email: '',
          mobile: '',
          billingAddress: '',
          shippingAddress: ''
        }
      },
      errorMessage: ''
    };
  },
  computed: {
    isValidForm() {
      return this.customer.firstName && this.customer.lastName && this.customer.contact.email && this.customer.password;
    }
  },
  methods: {
    async handleSubmit() {
      if (!this.isValidForm) {
        this.errorMessage = 'Please fill out all required fields.';
        return;
      }
      const contactData = {
        email: this.customer.contact.email,
        mobile: this.customer.contact.mobile
      };

      this.loading = true
      const customerData = {
        name: {
          firstName: this.customer.firstName,
          middleName: this.customer.middleName || '',
          lastName: this.customer.lastName
        },
        password: this.customer.password,
        contact: {
          email: contactData.email,
          mobile: contactData.mobile

        }
      };


      console.log('Customer Data Being Submitted:', customerData);
      try {
        console.log('Customer data being sent:', customerData);

        const savedContact = await ContactService.createContact(contactData);
        console.log('Contact saved successfully:', savedContact);

        const createdCustomer = await CustomerService.createCustomer(customerData);
        console.log('Customer saved successfully:', createdCustomer);

        const customerId = createdCustomer.userId;

        const response2 = await assignCartToCustomer(customerId);
        console.log('Cart assigned successfully', response2);

        const response3=await assignWishListToCustomer(customerId);
        console.log('WishList assigned successfully',response3)

        alert('Account created successfully!');
        this.$router.push('/login');
      } catch (error) {
        console.error('Error saving customer:', error.response ? error.response.data : error);
        this.errorMessage = 'Failed to create account.';
      }
      finally {
        this.loading = false;
      }
    }
  },
};
</script>

<style scoped>


button[disabled] {
  background-color: #ccc;
  cursor: not-allowed;
}
.create-customer-container {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
  background-color: #fff;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.title {
  font-size: 24px;
  margin-bottom: 20px;
}

form {
  display: flex;
  flex-direction: column;
}

label {
  margin: 10px 0 5px;
}

input {
  padding: 10px;
  margin-bottom: 15px;
  border-radius: 4px;
  border: 1px solid #ddd;
}

button {
  background-color: #007bff;
  color: white;
  padding: 10px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

button:disabled {
  background-color: #6c757d;
}

.error-message {
  color: red;
  margin-top: 15px;
}
</style>
