<template>
  <div class="content-blocks">
    <div class="block" @click="goToPage('/orders')">
      <font-awesome-icon :icon="['fas', 'box-open']" />
      <p>Orders</p>
    </div>
    <div class="block" @click="goToPage('/addresses')">
      <font-awesome-icon :icon="['fas', 'map-location-dot']" />
      <p>Addresses</p>
    </div>
    <div class="block" @click="goToPage('/account-details')">
      <font-awesome-icon :icon="['fas', 'address-card']" />
      <p>Account Details</p>
    </div>
    <div class="block" @click="goToPage('/wishlist')">
      <font-awesome-icon :icon="['fas', 'heart']" />
      <p>Wishlist</p>
    </div>
    <div class="block" @click="goToPage('/logout')">
      <font-awesome-icon :icon="['fas', 'right-from-bracket']" />
      <p>Log out</p>
    </div>
  </div>
</template>

<script>
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
import { library } from '@fortawesome/fontawesome-svg-core';
import { fas } from '@fortawesome/free-solid-svg-icons';

library.add(fas);

export default {
  name: 'ContentBlocks',
  components: {
    FontAwesomeIcon
  },
  methods: {
    goToPage(path) {
      this.$router.push(path);
    }
  }
};
</script>

<style scoped>
.content-blocks {
  width: 75%;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-around;
  padding: 20px;
  background-color: #fff;
}

.block {
  width: 30%;
  height: 150px;
  background-color: #f9f9f9;
  margin-bottom: 20px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  border: 1px solid #ccc;
  border-radius: 10px;
  text-align: center;
  transition: background-color 0.3s, transform 0.3s;
  cursor: pointer;
}

.block:hover {
  background-color: #e9e9e9;
  transform: scale(1.05);
}

.block .fa-icon {
  font-size: 30px;
  margin-bottom: 10px;
}

.block p {
  font-size: 1rem;
  color: #333;
}
</style>
