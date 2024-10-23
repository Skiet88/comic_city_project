import axios from 'axios';

const API_URL = 'http://localhost:8080/comiccity/author';

export const createAuthor = (author) => {
    return axios.post(`${API_URL}/create`, author);
};

export const getAuthor = (id) => {
    return axios.get(`${API_URL}/read/${id}`);
};

export const updateAuthor = (author) => {
    return axios.post(`${API_URL}/update`, author);
};

export const deleteAuthor = (authorID) => {
    return axios.post(`${API_URL}/delete/${authorID}`);
};

export const getAllAuthors = () => {
    return axios.get(`${API_URL}/getAll`);
};

export const searchAuthorsByName = (name) => {
    return axios.get(`${API_URL}/searchByName/${name}`);
};
