<template>
  <div class="table-container">
    <div class="d-flex align-items-center justify-content-between mb-3">
      <h2 class="me-3 p-7">Genre Management</h2>
      <button @click="addGenre" class="btn btn-success strong-border">
        <b>+</b> Add New Genre
      </button>
    </div>


    <div class="filters card p-3 mb-5 position-relative">
      <div class="row g-3">
        <div class="col-md-6 position-relative">

          <div v-if="errorMessage" class="text-danger position-absolute error-message">
            {{ errorMessage }}
          </div>
          <input
              v-model="newGenre"
              @input="clearErrorMessage"
              :class="['form-control', 'strong-border', errorMessage ? 'is-invalid' : '']"
              type="text"
              placeholder="Enter new genre"
          />
        </div>
        <div class="col-md-6 d-flex align-items-center">
          <button @click="addGenre" class="btn btn-success strong-border">
            Add Genre
          </button>
        </div>
      </div>
    </div>


    <div class="row">
      <div class="col-md-12">
        <h4>Available Genres</h4>

        <div v-if="genres.length === 0" class="alert alert-warning">
          No genres yet
        </div>
        <table v-else class="table table-bordered">
          <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Actions</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="genre in genres" :key="genre.id">
            <td>{{ genre.id }}</td>
            <td>{{ genre.name }}</td>
            <td class="actions">
              <button @click="deleteGenre(genre.id)" class="btn btn-danger btn-sm strong-border">
                Delete
              </button>
            </td>
          </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
import { create, deleteGenre, getAllGenre } from '@/services/GenreService';

export default {
  data() {
    return {
      genres: [],
      newGenre: '',
      errorMessage: '',
    };
  },
  mounted() {
    this.loadGenres();
  },
  methods: {
    async loadGenres() {
      try {
        const response = await getAllGenre();
        this.genres = response.data;
      } catch (error) {
        console.error('Error fetching genres:', error);
      }
    },

    async addGenre() {
      this.errorMessage = '';

      if (this.newGenre.trim() === '') {
        this.errorMessage = 'Genre name cannot be empty!';
        return;
      }
      this.newGenre = this.newGenre.toUpperCase();

      const genreExists = this.genres.some(
          genre => genre.name.toLowerCase() === this.newGenre.toLowerCase()
      );
      if (genreExists) {
        this.errorMessage = 'This genre already exists!';
        return;
      }

      try {
        const genreData = { name: this.newGenre };
        const response = await create(genreData);
        this.genres.push(response.data);
        this.newGenre = '';
      } catch (error) {
        console.error('Error adding genre:', error);
      }
    },

    async deleteGenre(id) {
      if (!confirm('Are you sure you want to delete this genre?')) return;

      try {
        await deleteGenre(id);
        this.genres = this.genres.filter((genre) => genre.id !== id);
      } catch (error) {
        console.error('Error deleting genre:', error);
      }
    },


    clearErrorMessage() {
      this.errorMessage = '';
    }
  },
};
</script>

<style scoped>

.table-container {
  width: 100%;
  max-width: 1000px;
  margin: 0 auto;
  padding: 20px;
  background-color: #f9f9f9;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  border: 4px solid #333;
}

.table-container h2 {
  text-align: center;
  margin-bottom: 20px;
  font-family: 'Comic Sans MS', sans-serif;
  font-weight: bold;
  font-size: 2rem;
  color: #333;
}

table {
  width: 100%;
  border-collapse: collapse;
  margin-bottom: 20px;
  font-family: 'Comic Sans MS', sans-serif;
}

table th,
table td {
  padding: 12px;
  text-align: left;
  border-bottom: 2px solid #333;
}

table th {
  background-color: #f2f2f2;
  font-weight: bold;
  text-transform: uppercase;
  border-top: 4px solid #333;
}

table tr:nth-child(even) {
  background-color: #fff;
}

table tr:nth-child(odd) {
  background-color: #f9f9f9;
}

table tr:hover {
  background-color: #ffcc00;
  transform: scale(1.02);
}

.actions {
  display: flex;
  gap: 10px;
}

.actions button {
  padding: 8px 12px;
  border: 2px solid #333;
  border-radius: 4px;
  cursor: pointer;
  font-family: 'Comic Sans MS', sans-serif;
  font-weight: bold;
}

.actions button.delete {
  background-color: #dc3545;
  color: white;
}

.actions button:hover {
  background-color: #ffcc00;
  color: #333;
  border-color: #333;
}

.strong-border {
  border: 2px solid #333 !important;
  border-radius: 6px;
}

.alert {
  font-family: 'Comic Sans MS', sans-serif;
  font-size: 1.1rem;
  color: #333;
}


.error-message {
  top: -20px;
  font-size: 0.9rem;
  color: red;
}

.loading-message {
  text-align: center;
  margin-top: 20px;
  background-color: #ff0066;
  color: white;
  padding: 10px;
  border-radius: 8px;
  border: 2px solid #333;
  display: inline-block;
}
</style>
