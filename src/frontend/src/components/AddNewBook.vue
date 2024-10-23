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
          <label class="form-label">Name <span style="color: red;">*</span></label>
          <input v-model="comicBook.name" type="text" class="form-control" placeholder="Enter name" required/>
        </div>
        <div class="col-md-6 mb-3">
          <label class="form-label">Genres</label>
          <multiselect
              v-model="selectedGenres"
              :options="genres"
              :multiple="true"
              :taggable="true"
              placeholder="Select genres"
              label="name"
              track-by="name"
              @input="updateGenres"
              required>
          >
          </multiselect>
        </div>
        <div class="col-md-6 mb-3">
          <label class="form-label">ISBN</label>
          <input v-model="comicBook.isbn" type="text" class="form-control" placeholder="Enter ISBN" required/>
        </div>
        <div class="col-md-6 mb-3">
          <label class="form-label">Weight</label>
          <input v-model="comicBook.weight" type="number" class="form-control" placeholder="Enter weight" required/>
        </div>
        <div class="col-md-6 mb-3">
          <label class="form-label">Price</label>
          <input v-model="comicBook.price" type="number" class="form-control" placeholder="Enter price" required/>
        </div>
        <div class="col-md-6 mb-3">
          <label class="form-label">Release Date</label>
          <input v-model="comicBook.releaseDate" type="date" class="form-control" required/>
        </div>
        <div class="col-md-6 mb-3">
          <label class="form-label">Quantity</label>
          <input v-model="comicBook.quantity" type="number" class="form-control" placeholder="Enter Quantity" required/>
        </div>
        <div class="col-12 mb-3">
          <label class="form-label">Description</label>
          <textarea v-model="comicBook.description" class="form-control" rows="4" placeholder="Enter description" required></textarea>
        </div>
      </div>


      <h3 class="mt-4">Author Details</h3>
      <div class="row mb-3">
        <div class="col-md-11">
          <multiselect
              v-model="comicBook.authors"
              :options="allAuthors"
              :multiple="true"
              :taggable="true"
              placeholder="Select authors"
              label="fullName"
              track-by="authorID"
             >
          </multiselect>
        </div>
      </div>

      <h3 class="mt-4">Publisher Details</h3>
      <div class="row mb-3">
        <div class="col-md-11 ">
          <multiselect
              v-model="comicBook.publisher"
              :options="allPublishers"
              placeholder="Select publisher"
              label="fullName"
              track-by="publisherId"
              @input="updatePublisher">
          </multiselect>
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
import {createComicBook} from "@/services/comicBookService";
import {getAllAuthors} from "@/services/AuthorService";
import {getAllPublishers} from "@/services/PublisherService";
import {getAllGenre} from "@/services/GenreService";

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
      genres: [],
      photoUrl: null,
      selectedAuthors: [],
      allAuthors: [],
      selectedPublisher: null,
      allPublishers: [],
    };
  },
  mounted() {
    this.fetchAuthors();
    this.fetchPublishers();
    this.fetchGenres();
  },
  watch: {
    selectedGenres(newVal) {
      this.comicBook.genres = newVal.map(genre => ({
        id: genre.id,
        name: genre.name,
      }));
      console.log('Genres updated via watcher:', this.comicBook.genres);
    }

  },
  methods: {
    async fetchAuthors() {
      try {
        const response = await getAllAuthors();
        this.allAuthors = response.data.map(author => ({
          authorID: author.authorID,
          name: {
            firstName: author.name.firstName,
            middleName: author.name.middleName || '',
            lastName: author.name.lastName
          },
          fullName: `${author.authorID} - ${author.name.firstName} ${author.name.middleName || ''} ${author.name.lastName}`.trim(),
        }));
      } catch (error) {
        console.error('Error fetching authors:', error);
      }
    },
    async fetchGenres() {
      this.loadingGenres = true;
      try {
        const response = await getAllGenre();
        this.genres = response.data.map(genre => ({
          id: genre.id,
          name: genre.name,
        }));
      } catch (error) {
        console.error('Error fetching genres:', error);
        this.errorMsg = 'Failed to load genres.';
      } finally {
        this.loadingGenres = false;
      }
    },
    async fetchPublishers() {
      try {
        const response = await getAllPublishers();
        this.allPublishers = response.data.map(publisher => ({
          publisherId: publisher.publisherId,
          name: publisher.name,
          yearFounded: publisher.yearFounded,
          fullName: `${publisher.publisherId} - ${publisher.name} ${publisher.yearFounded}`,
        }));
      } catch (error) {
        console.error('Error fetching publishers:', error);
      }
    },

    updatePublisher() {
      this.comicBook.publisher = this.selectedPublisher;
    },
    updateGenres() {
      this.comicBook.genres = this.selectedGenres.map(genre => genre.name);
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
      if (confirm("Are you sure you want to cancel? All unsaved changes will be lost.")) {
        this.$router.go(-1);
      }
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
      if (this.selectedGenres.length === 0) {
        alert('At least one genre is required.');
        return false;
      }
      return true;
    },
    async handleSubmit() {

      if (!this.validateForm()) return;

      const comicBookData = {
        ...this.comicBook,
        authors: this.comicBook.authors.map(author => ({
          authorID: author.authorID,
          name: {
            firstName: author.name.firstName,
            middleName: author.name.middleName || '',
            lastName: author.name.lastName
          }
        })),
        publisher: {
          publisherId: this.comicBook.publisher.publisherId,
          name: this.comicBook.publisher.name,
          yearFounded : this.comicBook.publisher.yearFounded
        },
        weight: parseFloat(this.comicBook.weight),
        price: parseFloat(this.comicBook.price),
        releaseDate: new Date(this.comicBook.releaseDate).toISOString().split('T')[0],
        genres: this.comicBook.genres,
        photo: this.comicBook.photo
      };

      console.log('ComicBook Data Being Submitted:', comicBookData);

      try {

        let response = await createComicBook(comicBookData);


        console.log('Comic book saved successfully:', response);
        this.$router.go(-1);


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
