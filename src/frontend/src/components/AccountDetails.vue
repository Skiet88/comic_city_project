<template>
  <div class="account-details-page">
    <div class="main-content">
      <div class="account-details-header">
        <i class="fas fa-user"></i>
        <h1>Account details</h1>
      </div>
      <form @submit.prevent="saveAccountDetails">
        <div class="form-group">
          <label for="first-name">First Name <span class="required">*</span></label>
          <input type="text" id="first-name" v-model="firstName" />
        </div>
        <div class="form-group">
          <label for="last-name">Last Name <span class="required">*</span></label>
          <input type="text" id="last-name" v-model="lastName" />
        </div>
        <div class="form-group">
          <label for="display-name">Display Name <span class="required">*</span></label>
          <input type="text" id="display-name" v-model="displayName" />
        </div>
        <div class="form-group">
          <label for="email-address">Email Address <span class="required">*</span></label>
          <input type="email" id="email-address" v-model="emailAddress" />
        </div>
        <div class="form-group">
          <label for="cellphone-number">Cellphone Number <span class="required">*</span></label>
          <input type="text" id="cellphone-number" v-model="cellphoneNumber" />
        </div>
        <div class="button-group">
          <button type="submit" class="save-button">SAVE</button>
          <button type="button" class="cancel-button" @click="cancel">CANCEL</button>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  name: 'AccountDetailsPage',
  data() {
    return {
      firstName: '',
      lastName: '',
      displayName: '',
      emailAddress: '',
      cellphoneNumber: ''
    };
  },
  methods: {
    saveAccountDetails() {

      console.log('Account details saved:', {
        firstName: this.firstName,
        lastName: this.lastName,
        displayName: this.displayName,
        emailAddress: this.emailAddress,
        cellphoneNumber: this.cellphoneNumber
      });
    },
    cancel() {
      this.firstName = '';
      this.lastName = '';
      this.displayName = '';
      this.emailAddress = '';
      this.cellphoneNumber = '';
    }
  }
};
</script>

<style scoped>
.account-details-page {
  padding: 20px;
}

.main-content {
  padding-left: 20px;
}

.account-details-header {
  display: flex;
  align-items: center;
  margin-bottom: 20px;
}

.account-details-header i {
  font-size: 24px;
  margin-right: 10px;
}

.account-details-header h1 {
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

.form-group .required {
  color: red;
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
