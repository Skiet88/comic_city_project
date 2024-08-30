import axios from 'axios';

class ComicBookService {
    constructor() {
        this.apiUrl = 'http://localhost:8080/comic_book'; // Base URL for the API
    }

    // Create a new comic book
    createComicBook(comicBook) {
        return axios.post(`${this.apiUrl}/create`, comicBook)
            .then(response => response.data)
            .catch(error => {
                console.error('Error creating comic book:', error);
                throw error;
            });
    }

    // Fetch a comic book by SKU
    fetchComicBook(sku) {
        return axios.get(`${this.apiUrl}/read/${sku}`)
            .then(response => response.data)
            .catch(error => {
                console.error('Error fetching comic book:', error);
                throw error;
            });
    }

    // Update an existing comic book
    updateComicBook(comicBook) {
        return axios.post(`${this.apiUrl}/update`, comicBook)
            .then(response => response.data)
            .catch(error => {
                console.error('Error updating comic book:', error);
                throw error;
            });
    }

    // Delete a comic book by SKU
    deleteComicBook(sku) {
        return axios.delete(`${this.apiUrl}/delete/${sku}`)
            .then(response => response.data)
            .catch(error => {
                console.error('Error deleting comic book:', error);
                throw error;
            });
    }

    // Fetch all comic books
    fetchAllComicBooks() {
        return axios.get(`${this.apiUrl}/getAll`)
            .then(response => response.data)
            .catch(error => {
                console.error('Error fetching comic books:', error);
                throw error;
            });
    }
}

// Export an instance of the service class
export default new ComicBookService();
