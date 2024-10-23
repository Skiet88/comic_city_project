
<template>
  <div class="table-container">
    <div class="d-flex align-items-center justify-content-between mb-3">
      <h2 class="me-3  p-7">Publishers Management</h2>
      <button @click="AddNewPublisher" class="btn btn-success"><b>+</b>New Publisher</button>
    </div>


    <div class="filters card p-3 mb-4">
      <div class="row g-3">

        <div class="col-md-4">
          <input
              v-model="searchName"
              @input="fetchPublishersByName"
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
        <th>Publisher ID</th>
        <th>Name</th>
        <th>Year Found</th>
        <th>Action</th>

      </tr>
      </thead>
      <tbody>
      <tr v-for="publisher in publishers" :key="publisher.publisherId">
        <td>{{ publisher.publisherId }}</td>
        <td>{{ publisher.name }}</td>
        <td>{{ publisher.yearFounded }}</td>
        <td class="actions">
          <button @click="editPublisher(publisher.publisherId)">Edit</button>
          <button @click="confirmDelete(publisher.publisherId)" class="delete">Delete</button>
        </td>
      </tr>
      <tr v-if="publishers.length === 0">
        <td colspan="8" class="no-data">No Publishers available.</td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>

import {deletePublisher, findPublishersByName, getAllPublishers} from "@/services/PublisherService";


export default {

  data() {
    return {
      publishers: [],
      searchName: '',
      loading: false,
      errorMsg: '',
    };
  },
  methods: {
    async fetchPublishersByName() {
      if (!this.searchName) {
        await this.fetchAllPublishers();
        return;
      }
      this.loading = true;
      try {
        const response = await findPublishersByName(this.searchName);
        this.publishers = response.data;
        this.loading = false;
      } catch (error) {
        this.errorMsg = 'Error fetching data by name';
        console.error(error);
      } finally {
        this.loading = false;
        }

    },


    async fetchAllPublishers() {
      this.loading = true;
      try {
        const response = await getAllPublishers();
        this.publishers = response.data.map(publisher => ({
          publisherId: publisher.publisherId,
          name: publisher.name,
          yearFounded: publisher.yearFounded,
          fullName: `${publisher.publisherId} - ${publisher.name} ${publisher.yearFounded}`,
        }));
        this.loading = false;
      } catch (error) {
        console.error('Error fetching publishers:', error);
      }
    },

    editPublisher(publisherID) {
      this.$router.push({ name: 'EditPublisher', params: { id: publisherID } });

    },

    async confirmDelete(publisherId) {
      try {
        const confirmed = confirm('Are you sure you want to delete this comic book?');
        if (confirmed) {
          await deletePublisher(publisherId);
          this.publishers = this.publishers.filter((publisher) => publisher.publisherId !== publisherId);
        }
      } catch (error) {
        console.error('Error deleting publisher book:', error);
      }
    },

  

    AddNewPublisher() {
      this.$router.push({ name: 'AddNewPublisher' });
    }
  },
  mounted() {
    this.fetchAllPublishers();
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


</style>