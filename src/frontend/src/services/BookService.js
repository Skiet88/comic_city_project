class ComicBookService {
    constructor() {
        this.apiUrl = '/api/comiccity/comic_book'; // Base URL for the API, assuming you're using a proxy
    }


    async request(url, options) {
        try {
            const response = await fetch(url, options);
            if (!response.ok) {
                throw new Error(`Network response was not ok: ${response.statusText}`);
            }
            return options.method === 'DELETE' ? response.text() : response.json();
        } catch (error) {
            console.error('Fetch error:', error);
            throw error;
        }
    }

    // Delete a comic book by ID
    async deleteComic(bookId) {
        const url = `${this.apiUrl}/delete/${bookId}`;
        await this.request(url, { method: 'DELETE' });
        return bookId; // Return the book ID so the caller can update state
    }

    // Read a comic book by ID
    async readComic(bookId) {
        const url = `${this.apiUrl}/read/${bookId}`;
        return await this.request(url, { method: 'GET' });
    }

    // Create a new comic book
    async createComicBook(comicBook) {
        const url = `${this.apiUrl}/create`;
        return await this.request(url, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(comicBook),
        });
    }

    // Update an existing comic book
    async updateComicBook(comicBook) {
        const url = `${this.apiUrl}/update`;
        return await this.request(url, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(comicBook),
        });
    }

    // Fetch all comic books
    async fetchAllComicBooks() {
        const url = `${this.apiUrl}/getAll`;
        return await this.request(url, { method: 'GET' });
    }
}

// Export an instance of the service class for reuse
export default new ComicBookService();
