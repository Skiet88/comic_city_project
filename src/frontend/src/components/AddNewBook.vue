<template>
  <div class="container mt-5">
    <h2>Add New Comic Book</h2>
    <form @submit.prevent="handleSubmit">
      <div class="row">

        <div class="col-md-6 mb-3">
          <label class="form-label">SKU</label>
          <input v-model="comicBook.sku" type="text" class="form-control" placeholder="Auto Generated" disabled />
        </div>
        <div class="col-md-6 mb-3">
          <label class="form-label">Name</label>
          <input v-model="comicBook.name" type="text" class="form-control" placeholder="Enter name" />
        </div>
        <div class="col-md-6 mb-3">
          <label class="form-label">Genres</label>
          <multiselect
              v-model="selectedGenres"
              :options="allGenres"
              :multiple="true"
              :taggable="true"
              placeholder="Select genres"
              label="name"
              track-by="name"
              @input="updateGenres">
          </multiselect>
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
          <input v-model="comicBook.releaseDate" type="date" class="form-control" />
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


      <h3 class="mt-4">Author Details</h3>
      <div class="row" v-for="(author, index) in comicBook.authors" :key="index">
        <div class="col-md-4 mb-3">
          <label class="form-label">First Name</label>
          <input v-model="author.firstName" type="text" class="form-control" placeholder="Enter first name" />
        </div>
        <div class="col-md-4 mb-3">
          <label class="form-label">Middle Name</label>
          <input v-model="author.middleName" type="text" class="form-control" placeholder="Enter middle name" />
        </div>
        <div class="col-md-4 mb-3 d-flex align-items-end">
          <div class="w-100">
            <label class="form-label">Surname</label>
            <input v-model="author.lastName" type="text" class="form-control" placeholder="Enter surname" />
          </div>
          <button
              type="button"
              class="btn btn-danger ms-2 mb-2"
              @click="removeAuthor(index)"
              :disabled="comicBook.authors.length === 1"
          >
            Remove
          </button>
        </div>
      </div>


      <div class="row">
        <div class="col-12 mb-3">
          <button type="button" class="btn btn-success" @click="addAuthor">+ Add Author</button>
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


      <div class="d-flex justify-content-between mt-4">
        <button type="button" class="btn btn-secondary" @click="handleCancel">Cancel</button>
        <button type="submit" class="btn btn-primary">Save</button>
      </div>
    </form>
  </div>
</template>

<script>
import Multiselect from "vue-multiselect";
import 'vue-multiselect/dist/vue-multiselect.css';
import BookService from "@/service/BookService";

export default {
  components: {
    Multiselect,
  },
  data() {
    return {
      comicBook: {
        sku: null,
        name: '',
        genres: [],
        isbn: '',
        weight: '',
        price: '',
        quantity: '',
        releaseDate: '',
        description: '',
        authors: [],
        publisher: {
          name: '',
          yearFounded: ''
        },
        photo: null,
      },
      selectedGenres: [],
      allGenres: [
        { name: "ACTION" },
        { name: "FANTASY" },
        { name: "MYSTERY" },
        { name: "SCI_FI" }
      ],
      photoUrl: null,
    };
  },
  watch: {
    selectedGenres(newVal) {
      this.comicBook.genres = newVal.map(genre => genre.name);
      console.log('Genres updated via watcher:', this.comicBook.genres);
    }
  },
  methods: {
    updateGenres() {
      this.comicBook.genres = this.selectedGenres.map(genre => genre.name);
    },
    addAuthor() {
      this.comicBook.authors.push({
        firstName: '',
        middleName: '',
        lastName: '',
      });
    },
    removeAuthor(index) {
      if (this.comicBook.authors.length > 1) {
        this.comicBook.authors.splice(index, 1);
      }
    },
    handleFileUpload(event) {
      const file = event.target.files[0];
      if (file) {
        const reader = new FileReader();
        reader.onload = (e) => {
          this.photoUrl = e.target.result;
          this.comicBook.photo = e.target.result.split(',')[1];
        };
        reader.readAsDataURL(file);
      }
    },
    handleCancel() {
      console.log('Form canceled');
    },
    validateForm() {
      if (!this.comicBook.name) {
        alert('Name is required.');
        return false;
      }
      if (!this.comicBook.price || isNaN(this.comicBook.price)) {
        alert('Price is required and must be a number.');
        return false;
      }
      if (this.comicBook.authors.length === 0) {
        alert('At least one author is required.');
        return false;
      }
      return true;
    },
    async handleSubmit() {

      if (!this.validateForm()) return;

      const comicBookData = {
        ...this.comicBook,
        authors: this.comicBook.authors.map(author => ({
          name: {
            firstName: author.firstName,
            middleName: author.middleName || '',
            lastName: author.lastName
          }
        })),
        publisher: {
          ...this.comicBook.publisher,
          yearFounded: parseInt(this.comicBook.publisher.yearFounded, 10)
        },
        weight: parseFloat(this.comicBook.weight),
        price: parseFloat(this.comicBook.price),
        releaseDate: new Date(this.comicBook.releaseDate).toISOString().split('T')[0],
        genres: this.comicBook.genres,
        photo: this.comicBook.photo
      };


      console.log('ComicBook Data Being Submitted:', comicBookData);

      try {
        // Submit comic book data
        let response = await BookService.createComicBook(comicBookData);


        console.log('Comic book saved successfully:', response);
        this.$router.push(`/comic-books`);


        alert('Comic book saved successfully!');
      } catch (error) {
        console.error('Error saving comic book:', error);
        alert('Failed to save comic book');
      }
    }
  },
};
</script>

<style scoped>

.container {
  max-width: 800px;
}

.btn {
  padding: 10px 20px;
}

.ms-2 {
  margin-left: 0.5rem;
}

.mb-2 {
  margin-bottom: 0.5rem;
}

h2 {
  font-family: 'Comic Sans MS', sans-serif;
  font-weight: bold;
  font-size: 2rem; /* Slightly larger than h3 */
  color: #333;
  text-transform: uppercase;
  margin-top: 20px;
  text-align: center;
  text-shadow: 2px 2px 0px #000, -2px -2px 0px #000;
  border-bottom: 4px solid #333;
  padding-bottom: 10px;
}

.container {
  max-width: 800px;
  margin: 20px auto;
  padding: 20px;

  border-radius: 10px;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.4);

  font-family: 'Comic Sans MS', sans-serif;
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
h3 {
  font-family: 'Comic Sans MS', sans-serif;
  font-weight: bold;
  font-size: 1.5rem;
  color: #333;
  text-transform: uppercase;
  margin-top: 20px;
  text-align: center;
  text-shadow: 2px 2px 0px #000, -2px -2px 0px #000;
  border-bottom: 4px solid #333;
  padding-bottom: 10px;
}

.multiselect {
  border: 2px solid #333;
  border-radius: 5px;
  padding: 5px;
  font-family: 'Comic Sans MS', sans-serif;
}

.multiselect__tags {
  background-color: #fff;
}

.multiselect__tag {
  background-color: #ffcc00;
  border: 2px solid #333;
  font-family: 'Comic Sans MS', sans-serif;
  color: #333;
}

.multiselect__tag-icon {
  color: #333;
}
.img-thumbnail {
  border: 4px solid #333;
  border-radius: 10px;
  margin-top: 10px;
}

.mt-4 {
  margin-top: 1.5rem;
}

.d-flex {
  display: flex;
}

.justify-content-between {
  justify-content: space-between;
}

.align-items-end {
  align-items: flex-end;
}


</style>
