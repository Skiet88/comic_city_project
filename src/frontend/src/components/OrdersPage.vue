<template>
  <div class="orders-page">
    <div class="main-content">
      <div class="orders-header">
        <font-awesome-icon :icon="['fas', 'box-open']" />
        <h1>Orders</h1>
      </div>
      <table class="orders-table">
        <thead>
        <tr>
          <th>Order</th>
          <th>Date</th>
          <th>Items</th>
          <th>Total</th>
        </tr>
        </thead>
        <tbody>
        <tr>
          <td colspan="4" class="no-orders">
            <font-awesome-icon :icon="['fas', 'exclamation-circle']" />
            <span>No order has been made yet</span>
          </td>
        </tr>
        </tbody>
      </table>
      <button class="shop-button">Browse and shop for comics</button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'OrdersPage'
};
</script>

<style scoped>
.orders-page {
  display: flex;
  padding: 20px;
}

.main-content {
  width: 100%;
  padding-left: 20px;
}

.orders-header {
  display: flex;
  align-items: center;
  margin-bottom: 20px;
}

.orders-header i {
  font-size: 24px;
  margin-right: 10px;
}

.orders-header h1 {
  font-size: 24px;
  margin: 0;
}

.orders-table {
  width: 100%;
  border-collapse: collapse;
  margin-bottom: 20px;
}

.orders-table th, .orders-table td {
  border-bottom: 1px solid #ccc;
  padding: 10px;
  text-align: left;
}

.no-orders {
  text-align: center;
  padding: 20px;
}

.no-orders i {
  font-size: 24px;
  display: block;
  margin-bottom: 10px;
}

.shop-button {
  display: block;
  margin: 0 auto;
  padding: 10px 20px;
  background-color: #000;
  color: #fff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.shop-button:hover {
  background-color: #444;
}
</style>
