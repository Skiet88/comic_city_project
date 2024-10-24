
<template>
  <div class="container mt-5">
    <div class="d-flex align-items-center mb-4">
      <button @click="goBack" class="btn btn-link p-0 me-3">&larr;</button>
      <h1 class="page-title">Edit Author</h1>
    </div>

    <form @submit.prevent="handleSubmit">
      <h3 class="mt-4">Author Details</h3>
      <div class="row">
        <div class="col-6">
          <div class="mb-3">
            <label for="firstName" class="form-label">Author ID</label>
            <input
                v-model="author.authorID"
                type="text"
                id="firstName"
                class="form-control"
                required
            />
          </div>
        </div>
        <div class="col-6">
          <div class="mb-3">
            <label for="lastName" class="form-label">Last Name</label>
            <input
                v-model="author.name.lastName"
                type="text"
                id="lastName"
                class="form-control"
                required
            />
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-6">
          <div class="mb-3">
            <label for="firstName" class="form-label">First Name</label>
            <input
                v-model="author.name.firstName"
                type="text"
                id="firstName"
                class="form-control"
                required
            />
          </div>
        </div>
        <div class="col-6">
          <div class="mb-3">
            <label for="lastName" class="form-label">Middle Name</label>
            <input
                v-model="author.name.middleName"
                type="text"
                id="lastName"
                class="form-control"
                :placeholder="author.name.middleName ? '' : '*None'"
                required
            />
          </div>
        </div>
      </div>

      <div v-if="successMessage" class="alert alert-success alert-dismissible fade show" role="alert">
        {{ successMessage }}
        <button type="button" class="close" aria-label="Close" @click="successMessage = ''">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>

      <div v-if="errorMessage" class="alert alert-danger alert-dismissible fade show" role="alert">
        {{ errorMessage }}
        <button type="button" class="close" aria-label="Close" @click="errorMessage = ''">
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
import { getAuthor, updateAuthor } from "@/services/AuthorService";

export default {
  data() {
    return {
      author: {
        name: {
          firstName: '',
          middleName: '',
          lastName: '',
        },
      },
      successMessage: '',
      errorMessage: '',
    };
  },
  mounted() {
    const authorID = this.$route.params.id;
    console.log('Received Author ID:', authorID);
    this.fetchAuthor(authorID);
  },
  methods: {
    async fetchAuthor(authorID) {
      try {
        const response = await getAuthor(authorID);
        const data = response.data;

        if (data) {

          this.author = {
            authorID: data.authorID,
            name: {
              firstName: data.name.firstName || '',
              middleName: data.name.middleName || '',
              lastName: data.name.lastName || '',
            },
          };
        } else {
          console.error('No data received from API');
        }
      } catch (error) {
        console.error('Error fetching author details:', error);
        this.errorMessage = "Failed to load author details.";
      }
    },
    goBack() {
      this.$router.go(-1);
    },
    handleCancel() {
      if (confirm("Are you sure you want to cancel? All unsaved changes will be lost.")) {
        this.$router.go(-1);
      }
    },
    async handleSubmit() {
      try {
        await updateAuthor(this.author);
        this.successMessage = "Author updated successfully!";
        console.log('Author updated successfully:', this.author);
        this.$router.push({ name: 'Authors'});
      } catch (error) {
        console.error('Error updating author:', error);
        this.errorMessage = "Failed to update author.";
      }
    },
  },
};
</script>

<style scoped>

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
  font-size: 2rem;
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
}

.form-control {
  border: 2px solid #333;
  border-radius: 5px;
  padding: 10px;
  font-family: 'Comic Sans MS', sans-serif;
  font-size: 1rem;
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
</style>
