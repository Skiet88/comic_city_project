<template>
  <div class="container mt-5">

    <div class="d-flex align-items-center mb-4">
      <button @click="goBack" class="btn btn-link p-0 me-3">

        &larr;
      </button>
      <h1 class="page-title">Edit Comic Book</h1>
    </div>

    <form @submit.prevent="handleSubmit">
      <div class="row">
        <div class="col-md-6 mb-3">
          <label class="form-label">SKU</label>
          <input v-model="comicBook.sku" type="text" class="form-control" placeholder="Enter SKU" disabled />
        </div>
        <div class="col-md-6 mb-3">
          <label class="form-label">Name</label>
          <input v-model="comicBook.name" type="text" class="form-control" placeholder="Enter name" />
        </div>
        <div class="col-md-6 mb-3">
          <label class="form-label">Genres</label>
          <input v-model="displayGenres" type="text" class="form-control" disabled />
        </div>
        <div class="col-md-6 mb-3">
          <label class="form-label">ISBN</label>
          <input v-model="comicBook.isbn" type="text" class="form-control" placeholder="Enter ISBN" />
        </div>
        <div class="col-md-6 mb-3">
          <label class="form-label">Weight</label>
          <input v-model="comicBook.weight" type="text" class="form-control" placeholder="Enter weight" />
        </div>
        <div class="col-md-6 mb-3">
          <label class="form-label">Price</label>
          <input v-model="comicBook.price" type="text" class="form-control" placeholder="Enter price" />
        </div>
        <div class="col-md-6 mb-3">
          <label class="form-label">Release Date</label>
          <input v-model="comicBook.releaseDate" type="date" class="form-control" placeholder="Enter release date" />
        </div>
        <div class="col-md-6 mb-3">
          <label class="form-label">Quantity</label>
          <input v-model="comicBook.quantity" type="text" class="form-control" placeholder="Enter Quantity" />
        </div>
        <div class="col-12 mb-3">
          <label class="form-label">Description</label>
          <textarea v-model="comicBook.description" class="form-control" rows="4" placeholder="Enter description"></textarea>
        </div>
      </div>

      <h3 class="mt-4">Authors Details</h3>
      <div class="row">
        <div class="col-12">
          <table class="table table-bordered mt-3">
            <thead class="table-light">
            <tr>
              <th>Name</th>
              <th>Surname</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="author in comicBook.authors" :key="author.authorID">
              <td>{{ formatAuthorName(author) }}</td>
              <td>{{ author.name.lastName }}</td>
            </tr>
            </tbody>
          </table>
        </div>
      </div>

      <h3 class="mt-4">Publisher Details</h3>
      <div class="row">
        <div class="col-md-6 mb-3">
          <label class="form-label">Name</label>
          <input v-model="comicBook.publisher.name" type="text" class="form-control" placeholder="Enter name" />
        </div>
        <div class="col-md-6 mb-3">
          <label class="form-label">Year Found</label>
          <input v-model="comicBook.publisher.yearFounded" type="text" class="form-control" placeholder="Enter year found" />
        </div>
      </div>

      <div class="mb-3">
        <label class="form-label">Upload Photo</label>
        <input type="file" class="form-control" @change="handleFileUpload" />
        <div v-if="photoUrl" class="mt-3">
          <img :src="photoUrl" alt="Comic Book Photo" class="img-thumbnail" />
        </div>
      </div>


      <div v-if="successMessage" class="alert alert-success alert-dismissible fade show" role="alert">
        {{ successMessage }}
        <button type="button" class="close" aria-label="Close" @click="successMessage = ''">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>


      <div class="d-flex justify-content-between mt-4">
        <button type="button" class="btn btn-secondary" @click="handleCancel">Cancel</button>
        <button type="submit" class="btn btn-primary">Save</button>
      </div>
    </form>
  </div>
</template>

<script>
import BookService from "@/service/BookService";

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
          yearFounded: '',
        },
        photo: null,
      },
      displayGenres: '', // A string for displaying genres
      photoUrl: null, // Holds the URL for the photo preview
      successMessage: '', // Holds the success message
    };
  },
  mounted() {
    const bookId = this.$route.params.id; // Get the book ID from the route
    console.log('Received Book ID:', bookId);
    this.fetchComicBook(bookId);
  },
  methods: {
    async fetchComicBook(bookId) {
      try {
        const data = await BookService.readComic(bookId);
        this.comicBook = data;
        this.displayGenres = Array.from(data.genres).join(', '); // Convert the Set to a comma-separated string
        if (this.comicBook.photo) {
          this.photoUrl = `data:image/jpeg;base64,${this.comicBook.photo}`;
        }
      } catch (error) {
        console.error('Error fetching comic book:', error);
      }
    },
    goBack() {
      this.$router.go(-1);
    },
    handleFileUpload(event) {
      const file = event.target.files[0];
      if (file) {
        const reader = new FileReader();
        reader.onload = (e) => {
          this.photoUrl = e.target.result;
        };
        reader.readAsDataURL(file);
        this.comicBook.photo = file;
      }
    },
    handleCancel() {
      if (confirm("Are you sure you want to cancel? All unsaved changes will be lost.")) {
        this.$router.go(-1); // Navigate back to the previous page
      }
    },
    async handleSubmit() {
      try {
        if (this.comicBook.photo && this.comicBook.photo instanceof File) {
          const reader = new FileReader();
          reader.onload = async () => {
            this.comicBook.photo = reader.result.split(',')[1]; // Strip out the data URL part
            await BookService.updateComicBook(this.comicBook);
          };
          reader.readAsDataURL(this.comicBook.photo);
        } else {
          await BookService.updateComicBook(this.comicBook);
        }
        this.successMessage = "Comic book saved successfully!";
        console.log('Comic book updated successfully:', this.comicBook);
        this.$router.push(`/comic-books`);
      } catch (error) {
        console.error('Error updating comic book:', error);
        this.successMessage = "Failed to update comic book.";
        this.$router.push(`/comic-books`);
      }
    },
    formatAuthorName(author) {
      const firstName = author.name.firstName;
      const middleName = author.name.middleName ? author.name.middleName.charAt(0) + '.' : '';
      return `${firstName} ${middleName}`;
    }
  },
};
</script>

<style scoped>
.container {
  max-width: 800px;
}

.page-title {
  font-size: 1.75rem;
  margin: 0;
}

h3 {
  font-family: 'Comic Sans MS', sans-serif;
  font-weight: bold;
  font-size: 1.75rem;
  color: #333;
  text-transform: uppercase;
  text-shadow: 2px 2px 0px #000, -2px -2px 0px #000;
  border-bottom: 4px solid #333;
  padding-bottom: 10px;
  margin-top: 20px;
}

.btn-link {
  font-size: 1.5rem;
  color: #333;
  text-decoration: none;
  font-family: 'Comic Sans MS', sans-serif;
}

.btn-link:hover {
  text-decoration: underline;
  color: #007bff; /* Optional: Add a hover color */
}

.img-thumbnail {
  max-width: 100%;
  height: auto;
  margin-top: 10px;
}

.btn {
  padding: 10px 20px;
}
.container {
  max-width: 800px;
  margin: 20px auto;
  padding: 20px;

  border-radius: 10px;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.4);

  font-family: 'Comic Sans MS', sans-serif;
}
.page-title {
  font-family: 'Comic Sans MS', sans-serif;
  font-weight: bold;
  font-size: 2rem; /* Slightly larger for the main page title */
  color: #333;
  text-transform: uppercase;
  text-shadow: 2px 2px 0px #000, -2px -2px 0px #000;
  border-bottom: 4px solid #333;
  padding-bottom: 10px;
  margin: 0;
}


.form-label {
  font-weight: bold;
  font-size: 1.1rem;
  color: #333;
  text-transform: uppercase;
  margin-bottom: 5px;
}

.form-control {
  border: 2px solid #333;
  border-radius: 5px;
  padding: 10px;
  font-family: 'Comic Sans MS', sans-serif;
  font-size: 1rem;
}

textarea.form-control {
  resize: vertical;
}
.table-container {
  width: 100%;
  max-width: 1000px;
  margin: 20px auto;
  padding: 20px;
  background-color: #f9f9f9;
  border-radius: 10px;
  box-shadow: 0 4px 15px rgba(0, 0, 0, 0.2);
  border: 4px solid #333; /* Bold border to mimic comic panels */
  font-family: 'Comic Sans MS', sans-serif;
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
  background-color: #f2f2f2; /* Light gray background for the header */
  font-weight: bold;
  text-transform: uppercase;
  border-top: 4px solid #333;
}

table tr:nth-child(even) {
  background-color: #fff; /* White background for even rows */
}

table tr:nth-child(odd) {
  background-color: #f9f9f9; /* Light gray background for odd rows */
}

table tr:hover {
  background-color: #ffcc00; /* Comic book yellow on hover */
  transform: scale(1.02); /* Slight zoom effect on hover */
}

.btn {
  padding: 10px 20px;
  border: 2px solid #333;
  border-radius: 5px;
  font-family: 'Comic Sans MS', sans-serif;
  font-weight: bold;
  cursor: pointer;
  transition: background-color 0.3s, transform 0.2s;
}

.btn-success {
  background-color: #28a745;
  color: white;
}

.btn-danger {
  background-color: #dc3545;
  color: white;
}

.btn-secondary {
  background-color: #6c757d;
  color: white;
}

.btn-primary {
  background-color: #007bff;
  color: white;
}

.btn:hover {
  background-color: #ffcc00;
  color: #333;
  transform: scale(1.05);
}
.img-thumbnail {
  border: 4px solid #333;
  border-radius: 10px;
  max-width: 100%;
  height: auto;
  margin-top: 10px;
}
.alert {
  font-family: 'Comic Sans MS', sans-serif;
  font-size: 1rem;
  border: 2px solid #333;
  border-radius: 5px;
  padding: 10px;
}


</style>
