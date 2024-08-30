import axios from 'axios';

const API_URL = 'http://localhost:8080/comiccity/wishList'; // Adjust the base URL as needed

export const createWishList = (wishList) => {
    return axios.post(`${API_URL}/create`, wishList);
};

export const getWishList = (wishListId) => {
    return axios.get(`${API_URL}/read/${wishListId}`);
};

export const updateWishList = (wishList) => {
    return axios.post(`${API_URL}/update`,wishList);
};

export const deleteWishList = (wishListId) => {
    return axios.delete(`${API_URL}/delete/${wishListId}`);
};

export const getall = () => {
    return axios.get(`${API_URL}/getall`);
};
export const removeBookFromWishList = (wishListId, sku) => {
    return axios.post(`${API_URL}/${wishListId}/removeComicBook/${sku}`);
};
export const addBookToWishList = (wishListId, sku) => {
    return axios.post(`${API_URL}/${wishListId}/addComicBook/${sku}`);
};
export const getCustomerWishList = (email) => {
    return axios.get(`${API_URL}/getCustomerWishList/${email}`);
};