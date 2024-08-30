<template>
  <div class="table-container">
    <h2>Comic Book Inventory</h2>


    <p v-if="loading" class="loading-message">Loading...</p>


    <table v-if="!loading && !errorMsg">
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
        <td>{{ book.price.toFixed(2) }}</td>
        <td>{{ book.genres.join(', ') }}</td> <!-- Display genres separated by commas -->
        <td>{{ book.quantity }}</td>
        <td class="actions">
          <button @click="editBook(book.sku)">Edit</button>
          <button @click="confirmDelete(book.sku)" class="delete">Delete</button>
        </td>
      </tr>
      <tr v-if="books.length === 0">
        <td colspan="8" class="no-data">No books available.</td>
      </tr>
      </tbody>
    </table>


    <p v-if="errorMsg" class="error-message">{{ errorMsg }}</p>
  </div>
</template>

<script>
export default {
  data() {
    return {
      comicBook: {
        sku: '',
        name: '',
        genres: new Set(),
        isbn: '',
        weight: '',
        price: '',
        releaseDate: '',
        description: '',
        authors: [],
        publisher: {
          name: '',
          yearFound: '',
        },
        photo: null,
      },
      books: [],
      errorMsg: '',
      loading: true,
    };
  },
  mounted() {

    fetch("api/comiccity/comic_book/getAll")
        .then((response) => {
          if (!response.ok) {
            throw new Error('Network response was not ok');
          }
          return response.json();
        })
        .then((data) => {
          this.books = data;
          this.loading = false;
        })
        .catch((error) => {
          this.errorMsg = 'Error fetching data';
          this.loading = false;
          console.error('There was a problem with the fetch operation:', error);
        });

  },
  methods: {
    editBook(id) {
        this.$router.push(`/edit-book/${id}`); // Navigate to EditBook.vue with the book ID

    },
    confirmDelete(bookId) {
      const confirmed = confirm('Are you sure you want to delete this book?');
      if (confirmed) {
        this.deleteComic(bookId);
      }
    },
    deleteComic(bookId) {
      fetch(`api/comiccity/comic_book/delete/${bookId}`, {
        method: 'DELETE',
      })
          .then((response) => {
            if (!response.ok) {
              throw new Error('Network response was not ok');
            }
            this.books = this.books.filter(book => book.sku !== bookId); // Update the books array
          })
          .catch((error) => {
            this.errorMsg = 'Error deleting the book'; // Set error message
            console.error('There was a problem with the delete operation:', error);
          });
    },


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
}

.table-container h2 {
  text-align: center;
  margin-bottom: 20px;
}

table {
  width: 100%;
  border-collapse: collapse;
  margin-bottom: 20px;
}

table th,
table td {
  padding: 12px;
  text-align: left;
  border-bottom: 1px solid #ddd;
}

table th {
  background-color: #f2f2f2;
  font-weight: bold;
}

.actions {
  display: flex;
  gap: 10px;
}

.actions button {
  padding: 8px 12px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.actions button:hover {
  opacity: 0.9;
}

.actions button.edit {
  background-color: #007bff;
  color: white;
}

.actions button.delete {
  background-color: #dc3545;
  color: white;
}

.error-message {
  color: #dc3545;
  text-align: center;
  margin-top: 20px;
}

.loading-message {
  text-align: center;
  color: #007bff;
  margin-top: 20px;
}

.no-data {
  text-align: center;
  color: #999;
  font-style: italic;
}

.table-container h2 {
  font-family: 'Comic Sans MS', sans-serif;
  font-weight: bold;
  font-size: 2rem;
  text-align: center;
  margin-bottom: 20px;
  color: #333;
}

.table-container {
  width: 100%;
  max-width: 1000px;
  margin: 20px auto;
  padding: 20px;

  border-radius: 10px;
  box-shadow: 0 4px 15px rgba(0, 0, 0, 0.2);
  border: 4px solid #333; /* Bold border to mimic comic panels */
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
  border-bottom: 2px solid #333; /* Thicker border for a comic feel */
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
  transform: scale(1.02); /* Slight zoom effect on hover */
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
  background-color: #ffcc00; /* Yellow on hover for both buttons */
  color: #333;
  border-color: #333;
}
.error-message,
.loading-message {
  font-family: 'Comic Sans MS', sans-serif;
  font-weight: bold;
  text-align: center;
  margin-top: 20px;
  background-color: #ff0066; /* Hot pink background */
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
  border: 2px dashed #333; /* Dashed border for a comic feel */
  padding: 10px;
  border-radius: 10px;
  background-color: #ffcc00;
}

</style>
