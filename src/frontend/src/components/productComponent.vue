<template>
  <div class="view_item_page-container">
    <nav-bar/>
    <header class="page-header">
    </header>
    <main class="product-page">
      <div class="product-top">
        <div class="product-image">
          <img src="@/assets/item1.png" alt="product Image" />
        </div>
        <div class="product-description">
          <h2>{{ productComponent.name }}</h2>
          <p><strong>Price:</strong>R{{ productComponent.price }}</p>
          <p>{{ productComponent.description }}</p>
          <div class="add-to-wishList">
            <img
                src="@/assets/favourate2png.png"
                alt="Image of heart"
                @mouseover="onHover"
                @mouseleave="onLeave"
                @click="onClick"
            />
          </div>
          <!-- start of the form -->
          <form @submit.prevent="addToCart">
            <div class="form-group">
              <label for="dropDown">Add {{ productComponent.name }}</label>
              <select id="dropDown" v-model="selectedSize">
                <option
                    v-for="dropDown in dropDowns"
                    :key="dropDown"
                    :value="dropDown"
                >{{ dropDown }}</option
                >
              </select>
            </div>

            <div class="form-group">
              <label for="quantity">Quantity</label>
              <input
                  type="number"
                  id="quantity"
                  v-model="quantity"
                  min="1"
              />
            </div>

            <div class="form-action">
              <button type="submit">Add to cart</button>
            </div>
          </form>
          <!-- end of form-->

          <div class="in-stock">
            <p>Availability: {{ productComponent.avilability }}</p>
          </div>

          <!-- Tabs section -->
          <div class="product-tabs">
            <ul>
              <li
                  :class="{ active: activeTab === 'description' }"
                  @click="activeTab = 'description'"
              >
                Description
              </li>

              <li
                  :class="{ active: activeTab === 'details' }"
                  @click="activeTab = 'details'"
              >
                Details
              </li>

              <li
                  :class="{ active: activeTab === 'ratings' }"
                  @click="activeTab = 'ratings'"
              >
                Ratings
              </li>
            </ul>
            <div v-if="activeTab === 'description'">
              <p>{{ productComponent.description }}</p>
            </div>

            <div v-if="activeTab === 'details'">
              <table>
                <tr v-for="(value, key) in productDetails" :key="key">
                  <td>{{ key }}</td>
                  <td>{{ value }}</td>
                </tr>
              </table>
            </div>

            <div v-if="activeTab === 'ratings'">
              <p>Customer Ratings: {{ productComponent.ratings }} / 5</p>
            </div>
          </div>
          <!-- End of Tabs section -->
        </div>
      </div>

      <div class="Related-items">
        <h3>Related Items</h3>
        <div class="gallery">
          <img v-for="image in galleryImages" :src="image.src" :alt="image.alt" :key="image.src">
        </div>
      </div>
    </main>
    <footer class="page-footer">
      <footer-section/>
    </footer>
  </div>
</template>

<script>
import NavBar from '@/components/NavBar.vue';
import FooterSection from '@/components/FooterSection.vue';

export default {
  name: 'productComponent',
  components: {
    NavBar,
    FooterSection,
  },
  data() {
    return {
      productComponent: {
        imgaeurl: ' ',
        name: 'Heroes in crisis ',
        description: 'This is a description of a product',
        price: 99.99,
        avilability: 'in stock', // this will consume a service
        ratings: '* * * *',
        activeTab: 'description',
      },
      favourateunfilled: require('@/assets/favourate2png.png'),
      favourateFilledIn: require('@/assets/favourateFilledIn.png'),
      iFilled: false,

      galleryImages: [
        { src: require('@/assets/ComicBookCover1.jpeg'), alt: 'Image 1' },
        { src: require('@/assets/ComicBookCover2.jpeg'), alt: 'Image 2' },
        { src: require('@/assets/ComicBookCover3.jpeg'), alt: 'Image 3' },
        { src: require('@/assets/ComicBookCover4.jpeg'), alt: 'Image 4' },
      ],
    };
  },
};
</script>
<style scoped>
.view_item_page-container{
  display: flex;
  flex-direction: column;
  max-height: 100vh;
}

.page-header{
  background-color: black;
  padding: 10px;
  text-align: center;
}

.page-footer{
  background-color: black;
  padding: 20px;
  text-align: center;
  margin-top: auto;
}

.product-page {
  display: flex;
  flex-direction: column;
  align-items: center;
  flex-grow: 1;
  padding: 40px;
}

.product-top {
  display: flex;
  justify-content: center;
  gap: 20px;
  width: 100%;
  max-width: 1200px;
}

.product-image img{
  max-width: 300px;
  border-radius: 8px;
}

.add-to-wishList img{
  width: 20px;
  cursor: pointer;
  transition: transform 0.2s ease-in-out;
}

.add-to-wishList img:hover{
  transform: scale(1.1);
}

.product-description{
  max-width: 500px;
  min-width: 300px;
}

.add-to-wishList{

}

/* The form starts */

.form-group{
  margin-bottom: 20px;
}

label{
  display: block;
  margin-bottom: 5px;
  font-weight: bold;
}

select, input[type="number"]{
  width: 100%;
  padding: 8px;
  margin-bottom: 10px;
  border: 1px solid grey;
  border-radius: 4px;
  box-sizing: border-box;
}

.form-actions{
  display: flex;
  justify-content: flex-start;
  align-items: center;
  gap: 10px;
}

.in-stock p{
  margin-top: 20px;
  font-size: 16px;
}

.product-tabs{
  margin-top: 20px;
}

.product-tabs ul{
  display: flex;
  list-style: none;
  padding: 0;
  margin-bottom: 20px;
}

.product-tabs ul li{
  margin-right: 15px;
  padding: 10px 15px;
  background-color: #f5f5f5;
  cursor: pointer;
  border-radius: 4px;
}

.product-tabs ul li.active:hover {
  background-color: sandybrown;
  color: white;
}

.product-tabs div{
  padding: 20px;
  border: 1px solid grey;
  border-radius: 4px;
}

.product-tabs table{
  width: 100%;
  border-collapse: collapse;
  border-color: black;
}

.product-tabs table td{
  padding: 10px;
  border: 1px solid #ddd;
}

.product-tabs table td:first-child{
  font-weight: bold;
}

button{
  padding: 10px 20px;
  border: none;
  border-radius: 4px;
  background-color: grey;
  color: white;
  font-size: 16px;
}

button:hover{
  background-color: sandybrown;
}

/* The form ends */

.product-description h2{
  margin-bottom: 10px;
  font-size: 24px;
}

.product-description p {
  margin-bottom: 8px;
  line-height: 1.6;
}

.Related-items {
  margin-top: 40px;
  text-align: center;
}

.Related-items ul{
  list-style: none;
  padding: 0;
}

.Related-items li{
  margin-bottom: 8px;
}

.gallery {
  display: flex;
  justify-content: center;
  gap: 15px;
  margin-top: 20px;
}

.gallery img {
  width: 100px;
  height: auto;
  border-radius: 5px;
  cursor: pointer;
  transition: transform 0.3s ease-in-out;
}

.gallery img:hover {
  transform: scale(1.1);
}
</style>