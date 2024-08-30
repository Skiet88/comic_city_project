import axios from 'axios';

const API_URL = 'http://localhost:8080/comiccity/comic_book'; // Adjust the base URL as needed

export const createComicBook = (comicBook) => {
    return axios.post(`${API_URL}/create`, comicBook);
};

export const getComicBook = (sku) => {
    return axios.get(`${API_URL}/read/${sku}`);
};

export const updateComicBook = (comicBook) => {
    return axios.post(`${API_URL}/update`, comicBook);
};

export const deleteComicBook = (sku) => {
    return axios.delete(`${API_URL}/delete/${sku}`);
};

export const getAllComicBooks = () => {
    return axios.get(`${API_URL}/getAll`);
};