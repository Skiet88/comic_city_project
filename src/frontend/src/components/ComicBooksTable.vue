
<template>
  <div class="table-container">
    <div class="d-flex align-items-center justify-content-between mb-3">
      <h2 class="me-3  p-7">Comic Books Management</h2>
      <button @click="AddNewBook" class="btn btn-success"><b>+</b>Add New</button>
    </div>


    <div class="filters card p-3 mb-4">
      <div class="row g-3">

        <div class="col-md-4">
          <input
              v-model="searchName"
              @input="fetchBooksByName"
              type="text"
              class="form-control strong-border"
              placeholder="Search by Name"
          />
        </div>


        <div class="col-md-3">
          <input
              v-model="startDate"
              type="date"
              class="form-control strong-border"
              placeholder="Start Date"
          />
        </div>
        <div class="col-md-3">
          <input
              v-model="endDate"
              type="date"
              class="form-control strong-border"
              placeholder="End Date"
          />
        </div>
        <div class="col-md-2">
          <button @click="fetchBooksByReleaseDate" class="btn btn-primary w-100 strong-border">
            Filter by Date
          </button>
        </div>


        <div class="col-md-12">
          <div class="form-check form-check-inline" v-for="genre in genres" :key="genre">
            <input
                class="form-check-input strong-border"
                type="checkbox"
                :value="genre"
                v-model="selectedGenres"
            />
            <label class="form-check-label">{{ genre }}</label>
          </div>

          <button @click="fetchBooksByGenres" class="btn btn-secondary mt-2 me-5 strong-border">
            Filter by Genres
          </button>

          <button @click="toGenres" class="btn btn-success mt-2 strong-border">
            Add New Genre
          </button>
        </div>

      </div>
    </div>


    <p v-if="loadingBooks || loadingGenres" class="loading-message">Loading...</p>
    <p v-if="errorMsg" class="error-message">{{ errorMsg }}</p>


    <table >
      <thead>
      <tr>
        <th>Title</th>
        <th>Book ID</th>
        <th>ISBN</th>
        <th>Published Date</th>
        <th>Price (R)</th>
        <th>Genre</th>
        <th>Quantity</th>
        <th>Actions</th>
      </tr>
      </thead>
      <tbody>

      <tr v-for="book in books" :key="book.sku">
        <td>{{ book.name }}</td>
        <td>{{ book.sku }}</td>
        <td>{{ book.isbn }}</td>
        <td>{{ book.releaseDate }}</td>
        <td>{{ book.price ? book.price.toFixed(2) : 'N/A' }}</td>
        <td>{{ book.genres.map(genre => genre.name).join(', ') }}</td>
        <td>{{ book.quantity }}</td>
        <td class="actions">
          <button @click="editBook(book.sku)">Edit</button>
          <button @click="deleteComic(book.sku)" class="delete">Delete</button>
        </td>
      </tr>


      <tr v-if="books.length === 0">
        <td colspan="8" class="no-data">No books available.</td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap';
import { getAllGenre } from "@/services/GenreService";
import {
  deleteComicBook,
  getComicBooksByGenres,
  getComicBooksByReleaseDates,
  searchComicBooksByName
} from "@/services/comicBookService";


export default {
  data() {
    return {
      books: [],
      searchName: '',
      startDate: '',
      endDate: '',
      selectedGenres: [],
      loadingBooks: false,
      loadingGenres: false,
      errorMsg: '',
      genres: [],
    };
  },

  methods: {
    fetchAllBooks() {
      this.loadingBooks = true;
      fetch(`/api/comiccity/comic_book/getAll`)
          .then((response) => response.json())
          .then((data) => {
            this.books = data;
            console.log("Books: ", this.books)
            this.loadingBooks = false;
          })
          .catch((error) => {
            this.errorMsg = 'Error fetching all books';
            this.loadingBooks = false;
            console.error(error);
          });
    },

    async fetchGenres() {
      this.loadingGenres = true;
      try {
        const response = await getAllGenre();
        this.genres = response.data.map(genre => genre.name);
      } catch (error) {
        console.error('Error fetching genres:', error);
        this.errorMsg = 'Failed to load genres.';
      } finally {
        this.loadingGenres = false;
      }
    },

    async fetchBooksByName() {
      if (!this.searchName) {
        this.fetchAllBooks();
        return;
      }
      this.loadingBooks = true;
      try {
        const response = await searchComicBooksByName(this.searchName);
        this.books = response.data;
      } catch (error) {
        this.loadingBooks = false;
        console.error('Error searching by name:', error);
      }finally {
        this.loadingGenres = false;
      }
    },

    async fetchBooksByReleaseDate() {
      if (!this.startDate || !this.endDate) {
        this.errorMsg = 'Please enter both start and end dates';
        return;
      }
      if (new Date(this.startDate) > new Date(this.endDate)) {
        this.errorMsg('Start date cannot be later than end date');
        return;
      }
      this.loadingBooks = true;

      try {
        if (this.startDate && this.endDate) {
          const response = await getComicBooksByReleaseDates(this.startDate, this.endDate);
          this.books = response.data;
          this.loadingBooks = true;
        } else {
          alert('Please enter both start and end dates');
        }
      } catch (error) {
        console.error('Error filtering by release dates:', error);
      }finally {
        this.loadingGenres = false;
      }

    },

    async fetchBooksByGenres() {
      if (this.selectedGenres.length === 0) {
        this.fetchAllBooks();
        return;
      }
      this.loadingBooks = true;


      const genreNames = this.selectedGenres.map(genre => genre);
      const genresParam = genreNames.join(',');

      try {
        const response = await getComicBooksByGenres(genresParam);
        this.books = response.data;
      } catch (error) {
        this.errorMsg = 'Error fetching data by genre';
        console.error(error);
      } finally {
        this.loadingBooks = false;
      }
    },


    editBook(BookID) {
      this.$router.push({ name: 'EditBook', params: { id: BookID } });
    },

    toGenres() {
      this.$router.push({ name: 'genres' });
    },

    async deleteComic(bookId) {
      try {
        const confirmed = confirm('Are you sure you want to delete this comic book?');
        if (confirmed) {
          await deleteComicBook(bookId);
          this.fetchAllBooks();
        }
      } catch (error) {
        console.error('Error deleting comic book:', error);
      }
    },

    AddNewBook() {
      this.$router.push({ name: 'AddNewBook' });
    },

  },

  mounted() {
    this.fetchAllBooks();
    this.fetchGenres();
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

.actions button.edit {
  background-color: #007bff;
  color: white;
  border: 2px solid #333;
}

.actions button.delete {
  background-color: #dc3545;
  color: white;
  border: 2px solid #333;
}

.actions button:hover {
  background-color: #ffcc00;
  color: #333;
  border-color: #333;
}

.error-message,
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

.no-data {
  text-align: center;
  color: #999;
  font-style: italic;
  font-family: 'Comic Sans MS', sans-serif;
  border: 2px dashed #333;
  padding: 10px;
  border-radius: 10px;
  background-color: #ffcc00;
}


.strong-border {
  border: 2px solid #333 !important;
  border-radius: 6px;
}

.form-check-input.strong-border {
  border: 2px solid #333 !important;
}
</style>
