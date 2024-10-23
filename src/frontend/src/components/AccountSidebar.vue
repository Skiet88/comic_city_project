
<template>
  <div class="account-sidebar">
    <h2>MY ACCOUNT</h2>
    <ul>
      <li :class="{ active: isActive('/') }" @click="goToPage('/')">Dashboard</li>
      <li :class="{ active: isActive('/orders') }" @click="goToPage('/orders')">Orders</li>
      <li :class="{ active: isActive('/addresses') }" @click="goToPage('/addresses')">Addresses</li>
      <li :class="{ active: isActive('/account-details') }" @click="goToPage('/account-details')">Account details</li>
      <li :class="{ active: isActive('/wishlist') }" @click="goToPage('/wishlist')">Wishlist</li>
      <li @click="goToPage('/logout')">LogOut</li>
    </ul>
  </div>
</template>

<script>
export default {
  name: 'AccountSidebar',
  methods: {
    goToPage(path) {
      this.$router.push(path);
    },
    isActive(route) {
      return this.$route.path === route;
    }
  }
};
</script>

<style scoped>
.account-sidebar {
  width: 250px;
  padding-right: 20px;
  border-right: 1px solid #ccc;
}

.account-sidebar h2 {
  font-weight: bold;
  margin-bottom: 10px;
}

.account-sidebar ul {
  list-style-type: none;
  padding: 0;
}

.account-sidebar li {
  margin-bottom: 10px;
  cursor: pointer;
  padding: 8px;
  transition: background-color 0.3s ease;
}

.account-sidebar li:hover {
  background-color: #e0e0e0;
}

.account-sidebar li.active {
  font-weight: bold;
  background-color: #d1e7dd; /* Active state background */
}

.account-sidebar li::after {
  content: "";
  display: block;
  border-bottom: 1px solid #000;
  margin: 5px 0;
}
</style>
