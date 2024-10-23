import axios from 'axios';

const API_URL = 'http://localhost:8080/comiccity/Contact';

export const createContact = (contact) => {
    return axios.post(`${API_URL}/create`, contact);
};

export const getContact = (id) => {
    return axios.get(`${API_URL}/read/${id}`);
};

export const updateContact = (contact) => {
    return axios.post(`${API_URL}/update`, contact);
};

export const deleteContact = (id) => {
    return axios.delete(`${API_URL}/delete/${id}`);
};

export const getAllContacts = () => {
    return axios.get(`${API_URL}/getAll`);
};