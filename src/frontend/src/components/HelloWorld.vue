<template>

  <head>
    <link rel="preconnect" href="https://fonts.gstatic.com/" crossorigin="" />
    <link
        rel="stylesheet"
        as="style"
        onload="this.rel='stylesheet'"
        href="https://fonts.googleapis.com/css2?display=swap&amp;family=Noto+Sans%3Awght%40400%3B500%3B700%3B900&amp;family=Work+Sans%3Awght%40400%3B500%3B700%3B900"
    />

    <title>Galileo Design</title>
    <link rel="icon" type="image/x-icon" href="data:image/x-icon;base64," />


  </head>
  <body >
  <div class="relative flex size-full min-h-screen flex-col bg-[#221e11] dark group/design-root overflow-x-hidden" style='font-family: "Work Sans", "Noto Sans", sans-serif;'>
    <div class="layout-container flex h-full grow flex-col">
      <div class="gap-1 px-6 flex flex-1 justify-center py-5">
        <div class="layout-content-container flex flex-col w-90">
          <div class="flex h-full min-h-[700px] flex-col justify-between bg-[#221e11] p-4">
            <div class="flex flex-col gap-4">
              <div class="flex flex-col gap-2">
                <div class="flex items-center gap-3 px-3 py-2">
                  <div class="text-white" data-icon="House" data-size="24px" data-weight="regular">
                    <svg xmlns="http://www.w3.org/2000/svg" width="24px" height="24px" fill="currentColor" viewBox="0 0 256 256">
                      <path
                          d="M218.83,103.77l-80-75.48a1.14,1.14,0,0,1-.11-.11,16,16,0,0,0-21.53,0l-.11.11L37.17,103.77A16,16,0,0,0,32,115.55V208a16,16,0,0,0,16,16H96a16,16,0,0,0,16-16V160h32v48a16,16,0,0,0,16,16h48a16,16,0,0,0,16-16V115.55A16,16,0,0,0,218.83,103.77ZM208,208H160V160a16,16,0,0,0-16-16H112a16,16,0,0,0-16,16v48H48V115.55l.11-.1L128,40l79.9,75.43.11.1Z"
                      ></path>
                    </svg>
                  </div>
                  <p class="text-white text-sm font-medium leading-normal">Dashboard</p>
                </div>
                <div class="flex items-center gap-3 px-3 py-2">
                  <div class="text-white" data-icon="ShoppingCart" data-size="24px" data-weight="regular">
                    <svg xmlns="http://www.w3.org/2000/svg" width="24px" height="24px" fill="currentColor" viewBox="0 0 256 256">
                      <path
                          d="M222.14,58.87A8,8,0,0,0,216,56H54.68L49.79,29.14A16,16,0,0,0,34.05,16H16a8,8,0,0,0,0,16h18L59.56,172.29a24,24,0,0,0,5.33,11.27,28,28,0,1,0,44.4,8.44h45.42A27.75,27.75,0,0,0,152,204a28,28,0,1,0,28-28H83.17a8,8,0,0,1-7.87-6.57L72.13,152h116a24,24,0,0,0,23.61-19.71l12.16-66.86A8,8,0,0,0,222.14,58.87ZM96,204a12,12,0,1,1-12-12A12,12,0,0,1,96,204Zm96,0a12,12,0,1,1-12-12A12,12,0,0,1,192,204Zm4-74.57A8,8,0,0,1,188.1,136H69.22L57.59,72H206.41Z"
                      ></path>
                    </svg>
                  </div>
                  <p class="text-white text-sm font-medium leading-normal">Orders</p>
                </div>
                <div class="flex items-center gap-3 px-3 py-2 rounded-xl bg-[#473f24]">
                  <div class="text-white" data-icon="Table" data-size="24px" data-weight="fill">
                    <svg xmlns="http://www.w3.org/2000/svg" width="24px" height="24px" fill="currentColor" viewBox="0 0 256 256">
                      <path
                          d="M224,48H32a8,8,0,0,0-8,8V192a16,16,0,0,0,16,16H216a16,16,0,0,0,16-16V56A8,8,0,0,0,224,48ZM40,112H80v32H40Zm56,0H216v32H96ZM40,160H80v32H40Zm176,32H96V160H216v32Z"
                      ></path>
                    </svg>
                  </div>
                  <p class="text-white text-sm font-medium leading-normal">Products</p>
                </div>
                <div class="flex items-center gap-3 px-3 py-2">
                  <div class="text-white" data-icon="Users" data-size="24px" data-weight="regular">
                    <svg xmlns="http://www.w3.org/2000/svg" width="24px" height="24px" fill="currentColor" viewBox="0 0 256 256">
                      <path
                          d="M117.25,157.92a60,60,0,1,0-66.5,0A95.83,95.83,0,0,0,3.53,195.63a8,8,0,1,0,13.4,8.74,80,80,0,0,1,134.14,0,8,8,0,0,0,13.4-8.74A95.83,95.83,0,0,0,117.25,157.92ZM40,108a44,44,0,1,1,44,44A44.05,44.05,0,0,1,40,108Zm210.14,98.7a8,8,0,0,1-11.07-2.33A79.83,79.83,0,0,0,172,168a8,8,0,0,1,0-16,44,44,0,1,0-16.34-84.87,8,8,0,1,1-5.94-14.85,60,60,0,0,1,55.53,105.64,95.83,95.83,0,0,1,47.22,37.71A8,8,0,0,1,250.14,206.7Z"
                      ></path>
                    </svg>
                  </div>
                  <p class="text-white text-sm font-medium leading-normal">Customers</p>
                </div>
                <div class="flex items-center gap-3 px-3 py-2">
                  <div class="text-white" data-icon="Megaphone" data-size="24px" data-weight="regular">
                    <svg xmlns="http://www.w3.org/2000/svg" width="24px" height="24px" fill="currentColor" viewBox="0 0 256 256">
                      <path
                          d="M240,120a48.05,48.05,0,0,0-48-48H152.2c-2.91-.17-53.62-3.74-101.91-44.24A16,16,0,0,0,24,40V200a16,16,0,0,0,26.29,12.25c37.77-31.68,77-40.76,93.71-43.3v31.72A16,16,0,0,0,151.12,214l11,7.33A16,16,0,0,0,186.5,212l11.77-44.36A48.07,48.07,0,0,0,240,120ZM40,199.93V40h0c42.81,35.91,86.63,45,104,47.24v65.48C126.65,155,82.84,164.07,40,199.93Zm131,8,0,.11-11-7.33V168h21.6ZM192,152H160V88h32a32,32,0,1,1,0,64Z"
                      ></path>
                    </svg>
                  </div>
                  <p class="text-white text-sm font-medium leading-normal">Marketing</p>
                </div>
              </div>
            </div>
            <div id="app">
            <button
                @click="navigateToAddComicBook"
                class="custom-button" >
              <span class="truncate">Add Comic Book</span>
            </button>

            </div>
          </div>

        </div>
        <div class="layout-content-container flex flex-col max-w-[960px] flex-1">
          <div class="flex flex-wrap justify-between gap-3 p-4"><p class="text-white tracking-light text-[32px] font-bold leading-tight min-w-72">Comic books</p></div>
          <div class="px-4 py-3">
            <label class="flex flex-col min-w-40 h-12 w-full">
              <div class="flex w-full flex-1 items-stretch rounded-xl h-full">
                <div
                    class="text-[#c8bb93] flex border-none bg-[#473f24] items-center justify-center pl-4 rounded-l-xl border-r-0"
                    data-icon="MagnifyingGlass"
                    data-size="24px"
                    data-weight="regular"
                >
                  <svg xmlns="http://www.w3.org/2000/svg" width="24px" height="24px" fill="currentColor" viewBox="0 0 256 256">
                    <path
                        d="M229.66,218.34l-50.07-50.06a88.11,88.11,0,1,0-11.31,11.31l50.06,50.07a8,8,0,0,0,11.32-11.32ZM40,112a72,72,0,1,1,72,72A72.08,72.08,0,0,1,40,112Z"
                    ></path>
                  </svg>
                </div>
                <input
                    placeholder="Search comic books by title"
                    class="form-input flex w-full min-w-0 flex-1 resize-none overflow-hidden rounded-xl text-white focus:outline-0 focus:ring-0 border-none bg-[#473f24] focus:border-none h-full placeholder:text-[#c8bb93] px-4 rounded-l-none border-l-0 pl-2 text-base font-normal leading-normal"
                    value=""
                />
              </div>
            </label>
          </div>
          <div class="px-4 py-3 @container">
            <div class="flex overflow-hidden rounded-xl border border-[#655a34] bg-[#221e11]">
              <div v-if="errorMsg" class="error-message">
                {{ errorMsg }}
              </div>

              <!-- Table -->
              <table class="flex-1">
                <thead>
                <tr class="bg-[#322d1a]">
                  <th class="px-4 py-3 text-left text-white w-[400px] text-sm font-medium leading-normal">Title</th>
                  <th class="px-4 py-3 text-left text-white w-[400px] text-sm font-medium leading-normal">Book_ID</th>
                  <th class="px-4 py-3 text-left text-white w-[400px] text-sm font-medium leading-normal">ISBN</th>
                  <th class="px-4 py-3 text-left text-white w-[400px] text-sm font-medium leading-normal">Published Date</th>
                  <th class="px-4 py-3 text-left text-white w-[400px] text-sm font-medium leading-normal">Price</th>
                  <th class="px-4 py-3 text-left text-white w-[400px] text-sm font-medium leading-normal">Genre</th>
                  <th class="px-4 py-3 text-left text-white w-[400px] text-sm font-medium leading-normal">Quantity</th>
                  <th class="px-4 py-3 text-left text-white w-60 text-[#c8bb93] text-sm font-medium leading-normal">Actions</th>
                </tr>
                </thead>
                <tbody>
                <!-- Iterate over the comics data to generate table rows -->
                <tr v-for="comic in comics" :key="comic.id" class="border-t border-t-[#655a34]">
                  <td class="h-[72px] px-4 py-2 w-[400px] text-white text-sm font-normal leading-normal">{{ comic.name }}</td>
                  <td class="h-[72px] px-4 py-2 w-[400px] text-[#c8bb93] text-sm font-normal leading-normal">{{ comic.sku }}</td>
                  <td class="h-[72px] px-4 py-2 w-[400px] text-[#c8bb93] text-sm font-normal leading-normal">{{ comic.isbn }}</td>
                  <td class="h-[72px] px-4 py-2 w-[400px] text-[#c8bb93] text-sm font-normal leading-normal">{{ comic.releaseDate }}</td>
                  <td class="h-[72px] px-4 py-2 w-[400px] text-[#c8bb93] text-sm font-normal leading-normal">{{ comic.price }}</td>
                  <td class="h-[72px] px-4 py-2 w-[400px] text-[#c8bb93] text-sm font-normal leading-normal">{{ comic.genre }}</td>
                  <td class="h-[72px] px-4 py-2 w-[400px] text-[#c8bb93] text-sm font-normal leading-normal">{{ comic.quantity }}</td>

                  <td class="h-[72px] px-4 py-2 w-60 text-[#c8bb93] text-sm font-bold leading-normal tracking-[0.015em]">
                    <span @click="editComic(comic)" class="cursor-pointer text-blue-500 hover:underline mr-4">Edit</span>
                    <span @click="confirmDelete(comic.sku)" class="cursor-pointer text-red-500 hover:underline">Delete</span>
                  </td>
                </tr>
                </tbody>
              </table>
              <!-- Modal for Editing Comic -->
              <div v-if="showEditModal" class="modal">
                <div class="modal-content">
                  <span @click="closeEditModal" class="close">&times;</span>
                  <h2>Edit Comic</h2>
                  <form @submit.prevent="saveEdit" class="edit-form">
                    <div>
                      <label for="name">Title:</label>
                      <input v-model="editComicData.name" id="name" type="text" required />
                    </div>

                    <!-- Single Description Field -->
                    <div class="description-field">
                      <label for="description">Description:</label>
                      <textarea v-model="editComicData.description" id="description" required></textarea>
                    </div>

                    <div>
                      <label for="weight">Weight:</label>
                      <input v-model.number="editComicData.weight" id="weight" type="number" step="0.01" required />
                    </div>

                    <div>
                      <label for="genre">Genre:</label>
                      <input v-model="editComicData.genre" id="genre" type="text" required />
                    </div>

                    <div>
                      <label for="releaseDate">Published Date:</label>
                      <input v-model="editComicData.releaseDate" id="releaseDate" type="date" required />
                    </div>

                    <div>
                      <label for="price">Price:</label>
                      <input v-model.number="editComicData.price" id="price" type="number" step="0.01" required />
                    </div>

                    <div>
                      <label for="quantity">Quantity:</label>
                      <input v-model.number="editComicData.quantity" id="quantity" type="number" required />
                    </div>

                    <div>
                      <label for="ISBN">ISBN:</label>
                      <input v-model="editComicData.isbn" id="ISBN" type="text" />
                    </div>

                    <div>
                      <label for="publisher">Publisher:</label>
                      <input v-model="editComicData.publisher.name" id="publisher" type="text" />
                    </div>

                    <div>
                      <h3>Selected Authors:</h3>
                      <ul>
                        <li v-for="author in authors" :key="author.authorID">
                          {{ author.name.firstName }}
                        </li>
                      </ul>
                    </div>

                    <div>
                      <label for="authors">Authors:</label>
                      <select v-model="editComicData.authors" id="authors" multiple>
                        <option v-for="author in authors" :key="author.authorID" :value="author.authorID">
                          {{ author.authorID }}
                        </option>
                      </select>
                    </div>

                    <div>
                      <label for="photo">Photo:</label>
                      <input type="file" @change="handleFileUpload" />
                    </div>

                    <button type="submit">Save</button>
                  </form>


                </div>
              </div>


            </div>
            </div>

          </div>
        </div>
      </div>
    </div>

  </body>


</template>

<script >

export default {
  name: 'HelloWorld',
  data() {
    return {
      msg: 'Loading...',
      displayMessage: '',
      bookId: '',
      comics: [],
      authors: [],
      errorMsg: '',
      showEditModal: false,
      editComicData: {
        authors: [],
      },
      showAddBook: false
    };
  },

  computed: {
    selectedAuthors() {
      // Filter the authors list to get only the selected authors
      return this.authors.filter(author =>
          this.editComicData.authors.includes(author.authorID)
      );
    }
  },
  methods: {
    navigateToAddComicBook() {
      this.$router.push({ name: 'AddBook' });
    },
    confirmDelete(bookId) {
      const confirmed = confirm('Are you sure you want to delete this book?');
      if (confirmed) {
        this.deleteComic(bookId);
      }
    },
    deleteComic(bookId) {

      let bookIdStr = Number(bookId);
      fetch(`api/comiccity/comic_book/delete/${bookIdStr}`, {
        method: 'DELETE',
      })
          .then((response) => {
            if (!response.ok) {
              throw new Error('Network response was not ok');
            }
            this.comics = this.comics.filter(comic => comic.sku !== bookId);
          })
          .catch((error) => {
            this.errorMsg = 'Error deleting the book'; // Set error message
            console.error('There was a problem with the delete operation:', error);
          });
    },

    editComic(comic) {
      this.editComicData = { ...comic }; // Populate the edit form with comic data
      this.showEditModal = true; // Show the edit modal
    },
    closeEditModal() {
      this.showEditModal = false; // Hide the edit modal
    },
    saveEdit() {
      fetch(`api/comiccity/comic_book/update/${this.editComicData}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(this.editComicData),
      })
          .then((response) => {
            if (!response.ok) {
              throw new Error('Network response was not ok');
            }
            return response.json();
          })
          .then((updatedComic) => {
            this.comics = this.comics.map(comic => comic.sku === updatedComic.sku ? updatedComic : comic);
            this.closeEditModal(); // Hide the modal after saving
          })
          .catch((error) => {
            this.errorMsg = 'Error updating the book'; // Set error message
            console.error('There was a problem with the update operation:', error);
          });
    },
    created() {
      // Fetch authors when the component is created
      this.fetchAuthors();
    }
  },
  mounted() {
    fetch("api/comiccity/comic_book/getAll")
        .then((response) => {
          if (!response.ok) {
            throw new Error('Network response was not ok');
          }
          return response.json(); // Parse the JSON from the response
        })
        .then((data) => {
          this.comics = data;
          //this.msg = JSON.stringify(data); // You can format this as needed
        })
        .catch((error) => {
          this.msg = 'Error fetching data';
          console.error('There was a problem with the fetch operation:', error);
        });

  fetch("api/comiccity/author/getAll")
.then((response) => {
  if (!response.ok) {
    throw new Error('Network response was not ok');
  }
  return response.json(); // Parse the JSON from the response
})
    .then((data) => {
      this.authors = data;
      //this.msg = JSON.stringify(data); // You can format this as needed
    })
    .catch((error) => {
      this.msg = 'Error fetching data';
      console.error('There was a problem with the fetch operation:', error);
    });
}
};
</script>

<style scoped>
@container(max-width:120px){.table-e58dec9c-dd47-4c5d-a708-bfee1bde362f-column-120{display: none;}}
@container(max-width:240px){.table-e58dec9c-dd47-4c5d-a708-bfee1bde362f-column-240{display: none;}}
@container(max-width:360px){.table-e58dec9c-dd47-4c5d-a708-bfee1bde362f-column-360{display: none;}}
@container(max-width:480px){.table-e58dec9c-dd47-4c5d-a708-bfee1bde362f-column-480{display: none;}}
@container(max-width:600px){.table-e58dec9c-dd47-4c5d-a708-bfee1bde362f-column-600{display: none;}}
@container(max-width:720px){.table-e58dec9c-dd47-4c5d-a708-bfee1bde362f-column-720{display: none;}}


.error-message {
  color: red;
  margin-bottom: 10px;
}

/* Table and row styling */
table {
  width: 100%;
  border-collapse: collapse;
}

/* Actions column styling */
.actions span {
  margin-right: 10px;
  cursor: pointer;
  font-weight: bold;
}

.actions span:hover {
  text-decoration: underline;
}

/* Modal styling */
.modal {
  position: relative;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  align-items: center;
  justify-content: center;
}

.modal-content {
  background: white;
  padding: 20px;
  border-radius: 4px;
  width: 400px;
  position: relative;
}

.close {
  position: absolute;
  top: 10px;
  right: 10px;
  cursor: pointer
}
/* Specific Styling for the Edit Form */
.edit-form {
  max-width: 1200px; /* Decrease max-width to prevent form from being too wide */
  max-height: 80vh; /* Limit the form height to 80% of the viewport height */
  margin: 0 auto;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  background-color: #f9f9f9;
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
  overflow-y: auto; /* Add scroll if content exceeds the form height */
}

/* Flexbox Container for Form Sections */
.edit-form > div {
  flex: 1 1 calc(25% - 20px); /* Each section takes up roughly one-fourth of the width */
}

/* Ensure Description Field Starts on Its Own Row */
.edit-form > .description-field {
  flex: 1 1 100%; /* Make description span the full width */
}

/* Button Styling */
.edit-form button[type="submit"] {
  flex: 1 1 100%; /* Button spans the full width */
}

/* Form Element Styling */
.edit-form label {
  display: block;
  font-weight: bold;
  margin-bottom: 5px;
}

.edit-form input[type="text"],
.edit-form input[type="number"],
.edit-form input[type="date"],
.edit-form select,
.edit-form textarea {
  width: 100%;
  padding: 10px;
  margin-bottom: 10px; /* Reduce margin to save space */
  border-radius: 4px;
  border: 1px solid #ccc;
  box-sizing: border-box;
}

/* Textarea Specific Styling */
.edit-form textarea {
  height: 72px; /* Adjust height to fit better within the form */
  resize: vertical;
}

.edit-form button[type="submit"] {
  padding: 10px 20px;
  font-size: 16px;
  color: #fff;
  background-color: #007BFF;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.edit-form button[type="submit"]:hover {
  background-color: #0056b3;
}

.edit-form input[type="file"] {
  margin-top: 10px;
}

.edit-form ul {
  list-style-type: none;
  padding: 0;
}

.edit-form li {
  padding: 5px 0;
}
.custom-button {
  display: flex;
  min-width: 84px;
  max-width: 480px;
  cursor: pointer;
  align-items: center;
  justify-content: center;
  overflow: hidden;
  border-radius: 12px; /* Rounded corners, equivalent to `rounded-xl` */
  height: 40px; /* Equivalent to `h-10` */
  padding: 0 16px; /* Equivalent to `px-4` */
  background-color: #e6b619;
  color: #221e11;
  font-size: 0.875rem; /* Equivalent to `text-sm` */
  font-weight: bold;
  line-height: 1.5; /* Equivalent to `leading-normal` */
  letter-spacing: 0.015em;
}

</style>