import axios from 'axios';

const API_URL = 'http://localhost:8080/comiccity/Publisher';

export const createPublisher = (publisher) => {
    return axios.post(`${API_URL}/create`, publisher);
};

export const getPublisher = (id) => {
    return axios.get(`${API_URL}/read/${id}`);
};

export const updatePublisher = (publisher) => {
    return axios.post(`${API_URL}/update`, publisher);
};

export const deletePublisher = (publisherId) => {
    return axios.delete(`${API_URL}/delete/${publisherId}`);
};

export const getAllPublishers = () => {
    return axios.get(`${API_URL}/getall`);
};
export const findPublishersByName = (name) => {
    return axios.get(`${API_URL}/findByName/${name}`);
};

