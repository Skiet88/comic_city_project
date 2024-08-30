<template>
  <div class="create-account-container">
    <h1 class="title">Create an Account</h1>
    <form @submit.prevent="createAccount">
      <label for="firstName">First Name:</label>
      <input id="firstName" v-model="firstName" placeholder="First Name" required />

      <label for="middleName">Middle Name:</label>
      <input id="middleName" v-model="middleName" placeholder="Middle Name" />

      <label for="lastName">Last Name:</label>
      <input id="lastName" v-model="lastName" placeholder="Last Name" required />

      <label for="email">Email:</label>
      <input id="email" type="email" v-model="email" placeholder="Email" required />

      <label for="password">Password:</label>
      <input id="password" type="password" v-model="password" placeholder="Password" required />

      <label for="mobile">Mobile:</label>
      <input id="mobile" v-model="mobile" placeholder="Mobile" />

      <button type="submit" :disabled="!isValidForm">Create Account</button>
    </form>
    <p v-if="errorMessage" class="error-message">{{ errorMessage }}</p>
  </div>
</template>

<script>
export default {
  data() {
    return {
      firstName: '',
      middleName: '',
      lastName: '',
      email: '',
      password: '',
      mobile: '',
      errorMessage: ''
    };
  },
  computed: {
    isValidForm() {
      return this.firstName && this.lastName && this.email && this.password;
    }
  },
  methods: {
    async createAccount() {
      if (!this.isValidForm) {
        this.errorMessage = 'Please fill out all required fields.';
        return;
      }

      try {
        const response = await fetch('/api/comiccity/Customer/create', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },

          body: JSON.stringify({
            customerId: 1,
            name: {
              firstName: this.firstName,
              middleName: this.middleName,
              lastName: this.lastName
            },
            password: this.password,
            contact: {
            email: this.email,
            mobile: this.mobile
              }
          })
        });

        if (response.ok) {
          alert('Account created successfully!');
          // Redirect or clear form
        } else {
          const errorText = await response.text();
          this.errorMessage = `Error: ${errorText}`;
        }
      } catch (error) {
        this.errorMessage = 'An error occurred while creating your account.';
        console.error('Error:', error);
      }
    }
  }
};
</script>

<style scoped>
.create-account-container {
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
