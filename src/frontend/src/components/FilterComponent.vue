<template>
  <div class="filter-container">
    <h3>Filter By</h3>

    <div class="filter-group">
      <h4>Price</h4>
      <div class="price-slider-container">
        <input
            type="range"
            min="0"
            max="50000"
            step="10"
            v-model="maxPrice"
            @input="updatePriceRange"
            class="price-slider"
        />
        <div class="price-range">
          <span>Min: R0</span> - <span>{{ formatCurrency(maxPrice) }}</span>
        </div>
      </div>
    </div>

    <div class="filter-group">
      <h4>Genre</h4>
      <select v-model="selectedGenre">
        <option value="">All</option>
        <option v-for="genre in genres" :key="genre" :value="genre.name">{{ genre.name }}</option>
      </select>
    </div>

    <div class="filter-group">
      <h4>Release Date</h4>
      <div class="date-range">
        <div class="date-range-item">
          <label for="release-date-from">From:</label>
          <input type="date" id="release-date-from" v-model="selectedReleaseDateFrom" />
        </div>
        <div class="date-range-item">
          <label for="release-date-to">To:</label>
          <input type="date" id="release-date-to" v-model="selectedReleaseDateTo" />
        </div>
      </div>
    </div>

    <div class="filter-group">
      <h4>Publisher</h4>
      <select v-model="selectedPublisher">
        <option value="">All</option>
        <option v-for="publisher in publishers" :key="publisher" :value="publisher.name">{{ publisher.name }}</option>
      </select>
    </div>

    <button @click="applyFilters">Apply Filters</button>
  </div>
</template>

<script>
import { getAllGenre } from "@/services/GenreService";
import { getAllPublishers } from "@/services/PublisherService";

export default {
  data() {
    return {
      maxPrice: 500000,
      selectedGenre: '',
      selectedReleaseDateFrom: '',
      selectedReleaseDateTo: '',
      selectedPublisher: '',
      genres: [],
      publishers: [],
    };
  },
  mounted() {
    this.loadGenres();
    this.fetchPublishers();
  },

  methods: {
    updatePriceRange() {
    },

    async loadGenres() {
      try {
        const response = await getAllGenre();
        this.genres = response.data;
      } catch (error) {
        console.error("Error fetching genres:", error);
      }
    },

    async fetchPublishers() {
      try {
        const response = await getAllPublishers();
        this.publishers = response.data;
      } catch (error) {
        console.error("Error fetching publishers:", error);
      }
    },

    applyFilters() {
      this.$emit("filter", {
        minPrice: 0,
        maxPrice: this.maxPrice,
        genre: this.selectedGenre ? this.selectedGenre : null,
        releaseDateFrom: this.selectedReleaseDateFrom || null,
        releaseDateTo: this.selectedReleaseDateTo || null,
        publisher: this.selectedPublisher ? this.selectedPublisher : null,
      });
    },

    formatCurrency(value) {
      return `R${value.toLocaleString()}`;
    }
  }
};
</script>

<style scoped>
.filter-container {
  padding: 10px;
  border: 1px solid #ccc;
  margin-bottom: 20px;
  width: 250px;
  margin-top: 20px;
  margin-left: 10px;
  border-radius: 10px;
}

.filter-group {
  margin-bottom: 20px;
}
.filter-group select{
  padding: 10px;
  font-size: 16px;
  width: 95%;
  height: 40px;
  border-radius: 4px;
  border: 1px solid #ccc;
}

.price-slider-container {
  position: relative;
  height: 60px;
}

.price-slider {
  width: 100%;
}

.price-range {
  margin-top: 10px;
  text-align: center;
}

.date-range {
  display: flex;
  flex-direction: column;

}

.date-range-item {
  margin-bottom: 10px;

}

.date-range label {
  margin-right: 5px;
}

.date-range input {
  margin-top: 5px;
  margin-left: 5px;
  padding: 10px;
  font-size: 16px;
  width: 200px;
}

button {
  background-color: #007bff;
  color: #fff;
  border: none;
  padding: 15px;
  border-radius: 6px;
  cursor: pointer;
  font-size: 16px;
  width: 56%;
}

button:hover {
  background-color: #0056b3;
}
</style>
