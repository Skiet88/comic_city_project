<template>
  <div class="comic-card">

      <img :src="comicImage" alt="Comic Cover" class="comic-image" @click="selectComic"/>

    <div
        class="heart-icon"
        :class="{ 'active': isFavorite }"
        @click="toggleWishlist"
    >
      <font-awesome-icon icon="heart"/>
    </div>
    <div class="comic-info">
      <div class="title-price">
        <div  class="no-underline" @click="selectComic">
          <h3 class="book-title">{{ comic.name }}</h3>
        </div>
        <p class="price">{{ formatPrice(comic.price) }}</p>
      </div>
      <p class="author">{{ authorsList }}</p>
      <div class="star-rating">
        <span v-for="n in 5" :key="n" class="star">★</span>
      </div>

      <button class="add-btn" @click="handleAddToCart">
        + Add
        <font-awesome-icon icon="shopping-cart" class="icon"/>
      </button>

    </div>
  </div>
</template>

<script>
export default {
  name: 'ComicCard',
  props: {
    comic: Object,
    wishlist: {
      type: Array,
      default: () => []
    }
  },
  computed: {
    comicImage() {
      return this.comic.photo
          ? `data:image/jpeg;base64,${this.comic.photo}`
          : 'https://via.placeholder.com/150';
    },
    authorsList() {
      return this.comic.authors
          .map((author) => {
            const initial = author.name.firstName.charAt(0).toUpperCase();
            return `${initial}. ${author.name.lastName}`;
          })
          .join(', ');
    },
    isFavorite() {
      if (!Array.isArray(this.wishlist)) {
        return false;
      }
      const isInWishlist = this.wishlist.some((item) => item.sku === this.comic.sku);
      console.log(`Comic SKU: ${this.comic.sku} - Is in Wishlist: ${isInWishlist}`);
      return isInWishlist;
    }
  },
  methods: {
    formatPrice(price) {
      return `R${price.toFixed(2)}`;
    },
    handleAddToCart() {
      this.$emit('add-to-cart', this.comic.sku);
    },
    toggleWishlist() {
      this.$emit('toggle-wishlist', this.comic.sku);
    },

      selectComic() {
        this.$emit('comic-selected', this.comic.sku);
      }
  }
};
</script>
<style scoped>

.comic-card {
  position: relative;
  border: 1px solid #ddd;
  padding: 10px;
  text-align: left;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  width: 300px;
  height: 480px;
  box-sizing: border-box;
  transition: transform 0.3s ease, box-shadow 0.3s ease;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.comic-card:hover {
  transform: translateY(-10px);
  box-shadow: 0 6px 12px rgba(0, 0, 0, 0.2);
}

.comic-image {
  width: 100%;
  height: auto;
  border-radius: 8px;
}

.heart-icon {
  position: absolute;
  top: 8px;
  right: 8px;
  font-size: 30px;
  color: rgba(0, 0, 0, 0.38);
  background-color: white;
  border-radius: 50%;
  padding: 4px;
  box-sizing: content-box;
  cursor: pointer;
}


.heart-icon.active {
  color: red;
}

.heart-icon:hover {
  color: red;
}

.comic-info {
  margin-top: 10px;
  flex-grow: 1;
}

.title-price {
  display: flex;
  justify-content: space-between;
  align-items: baseline;
  flex-wrap: wrap;
}

.book-title {
  font-size: 20px;
  font-weight: bold;
  margin: 0;
  flex: 1 1 auto;
  flex-grow: 1;
  white-space: nowrap;
  max-width: 70%;
}

.price {
  font-size: 16px;
  margin-left: 10px;
  text-align: right;
  white-space: nowrap;
}

.author {
  font-size: 14px;
  margin: 8px 0;
}
.no-underline {
  text-decoration: none;
  color: inherit;
}
.star-rating {
  font-size: 16px;
  color: #af751c;
  margin: 8px 0;
}

.star {
  margin-right: 2px;
}

.add-btn {
  background-color: #fca311;
  color: white;
  border: none;
  padding: 12px 20px;
  cursor: pointer;
  border-radius: 6px;
  height: 40px;
  width: 168px;
  font-size: 1.1rem;
  font-weight: bold;
  transition: background-color 0.3s ease;
  align-self: center;
  margin-bottom: 20px;
  margin: 20px auto 0 auto;
  display: block;
}

.add-btn .icon {
  margin-left: 10px;
}

.add-btn:hover {
  background-color: #af751c;
}
</style>
