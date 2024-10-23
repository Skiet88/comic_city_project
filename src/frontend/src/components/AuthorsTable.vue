
<template>
  <div class="table-container">
    <div class="d-flex align-items-center justify-content-between mb-3">
      <h2 class="me-3  p-7">Authors Management</h2>
      <button @click="AddNewAuthor" class="btn btn-success"><b>+</b>New Author</button>
    </div>


    <div class="filters card p-3 mb-4">
      <div class="row g-3">

        <div class="col-md-4">
          <input
              v-model="searchName"
              @input="fetchAuthorsByName"
              type="text"
              class="form-control strong-border"
              placeholder="Search by Name"
          />
        </div>

      </div>
    </div>


    <p v-if="loading" class="loading-message">Loading...</p>
    <p v-if="errorMsg" class="error-message">{{ errorMsg }}</p>


    <table>
      <thead>
      <tr>
        <th>Author ID</th>
        <th>First Names</th>
        <th>Last Name</th>
        <th>Action</th>

      </tr>
      </thead>
      <tbody>
      <tr v-for="author in authors" :key="author.authorID">
        <td>{{ author.authorID }}</td>
        <td>{{ formatAuthorName(author) }}</td>
        <td>{{ author.name.lastName || ''}}</td>
        <td class="actions">
          <button @click="editAuthor(author.authorID)">Edit</button>
          <button @click="confirmDelete(author.authorID)" class="delete">Delete</button>
        </td>
      </tr>
      <tr v-if="authors.length === 0">
        <td colspan="8" class="no-data">No Authors available.</td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>


import {deleteAuthor, getAllAuthors, searchAuthorsByName} from "@/services/AuthorService";


export default {

  data() {
    return {
      authors: [],
      searchName: '',
      loading: false,
      errorMsg: '',
    };
  },
  methods: {
    async fetchAuthorsByName() {
      if (!this.searchName) {
        await this.fetchAllAuthors();
        return;
      }
      this.loading = true;
      try {
        const response = await searchAuthorsByName(this.searchName);
        this.authors = response.data;
        this.loading = false;
      } catch (error) {
        this.errorMsg = 'Error fetching data by name';
      } finally {
        this.loading = false;
      }
    },


    async fetchAllAuthors() {
      this.loading = true;
      try {
        const response = await getAllAuthors();
        this.authors =response.data.map(author => ({
          authorID: author.authorID,
          name: {
            firstName: author.name.firstName,
            middleName: author.name.middleName || '',
            lastName: author.name.lastName
          },
          fullName: `${author.authorID} - ${author.name.firstName} ${author.name.middleName || ''} ${author.name.lastName}`.trim(),
        }));
        console.log('Authors:', this.authors)
        this.loading = false;
      } catch (error) {
        console.error('Error fetching genres:', error);
      }
    },

    editAuthor(AuthorID) {
      this.$router.push({ name: 'EditAuthor', params: { id: AuthorID } });
    },

    async confirmDelete(authorId) {
      try {
        const confirmed = confirm('Are you sure you want to delete this comic book?');
        if (confirmed) {
          await deleteAuthor(authorId);
          this.authors = this.authors.filter((author) => author.authorID !== authorId);
        }
      } catch (error) {
        console.error('Error deleting publisher book:', error);
      }
    },

    deleteAuthor(authorId) {
      fetch(`api/comiccity/author/delete/${authorId}`, {
        method: 'DELETE',
      })
          .then((response) => {
            if (!response.ok) {
              throw new Error('Network response was not ok');
            }
            this.authors = this.authors.filter((author) => author.authorID !== authorId);
          })
          .catch((error) => {
            this.errorMsg = 'Error deleting the author';
            console.error('There was a problem with the delete operation:', error);
          });
    },

    formatAuthorName(author) {
      if (!author || !author.name) return '';
      const firstName = author?.name?.firstName || '';
      const middleName = author?.name?.middleName || '';
      return `${firstName} ${middleName}`.trim();

    },
    AddNewAuthor() {
      this.$router.push({ name: 'AddNewAuthor' });
    }
  },
  mounted() {
    this.fetchAllAuthors();
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