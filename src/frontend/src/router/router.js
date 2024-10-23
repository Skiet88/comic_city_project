import { createRouter, createWebHistory } from 'vue-router';
import HomePage from '@/views/HomePage.vue';
//import viewItem from "@/views/viewItem.vue";
import ShoppingCart from "@/components/ShoppingCart.vue";
import ShoppingWishList from "@/components/ShoppingWishList.vue";
import ComicBooksTable from '@/components/ComicBooksTable.vue';
import LoginPage from "@/components/LoginPage.vue";
import EditBook from "@/components/EditBook.vue";
import CreateAccount from "@/components/CreateAccount.vue";

import AddNewBook from "@/components/AddNewBook.vue";
import AccountSidebar from "@/components/AccountSidebar.vue";
import AccountDetails from "@/components/AccountDetails.vue";
import AddressesPage from "@/components/AddressesPage.vue";
import WishListPage from "@/components/WishListPage.vue";
import ViewItem from "@/views/viewItem.vue";
import MainLayout from "@/components/MainLayout.vue";
import AdminOrderManagment from "@/components/AdminOrderManagment.vue";
import PublishersTable from "@/components/PublishersTable.vue";
import AuthorsTable from "@/components/AuthorsTable.vue";
import EditAuthor from "@/components/EditAuthor.vue";
import AddNewAuthor from "@/components/AddNewAuthor.vue";
import AddNewPublisher from "@/components/AddNewPublisher.vue";
import EditPublisher from "@/components/EditPublisher.vue";
import GenreManagment from "@/components/GenreManagment.vue";
import axios from "axios";
import OrderHistory from "@/components/OrderHistory.vue";

const routes = [
    // Public routes (available to all users)
    { path: '/', name: 'HomePage', component: HomePage },
    { path: '/login', name: 'LoginPage', component: LoginPage },
    { path: '/signUp', name: 'CreateAccount', component: CreateAccount },
    { path: '/comic/:sku', name: 'view-item', component: ViewItem, props: true }, // View Comic Item
    { path: '/cart', name: 'ShoppingCart', component: ShoppingCart }, // Shopping cart
    { path: '/wishList', name: 'ShoppingWishList', component: ShoppingWishList },
    // Admin routes (already grouped under admin layout)
    {
        path: '/admin-layout',
        component: MainLayout,
        children: [
            {
                path: '',
                redirect: '/admin-layout/comic-books'
            },
            { path: 'comic-books', name: 'ComicBooks', component: ComicBooksTable },
            { path: 'add-new-book', name: 'AddNewBook', component: AddNewBook },
            { path: 'edit-book/:id', name: 'EditBook', component: EditBook, props: true },
            { path: 'view-orders', name: 'AdminOrdersManagement', component: AdminOrderManagment },
            { path: 'publishers', name: 'publishers', component: PublishersTable },
            { path: 'add-new-publisher', name: 'AddNewPublisher', component: AddNewPublisher },
            { path: 'edit-publisher/:id', name: 'EditPublisher', component: EditPublisher, props: true },
            { path: 'authors', name: 'Authors', component: AuthorsTable },
            { path: 'add-new-author', name: 'AddNewAuthor', component: AddNewAuthor },
            { path: 'edit-author/:id', name: 'EditAuthor', component: EditAuthor, props: true },
            { path: 'genres', name: 'genres', component: GenreManagment },
        ],
    },

    // Customer routes grouped (no layout component needed)
    {
        path: '/customer',
        children: [
            { path: '', redirect: 'account' }, // Redirect to account as default
            { path: 'account', name: 'Account', component: AccountSidebar }, // Customer account
            { path: '/order-history', name: 'OrderHistory', component: OrderHistory }, // Order history
            { path: 'addresses', name: 'Addresses', component: AddressesPage }, // Manage addresses
            { path: 'account-details', name: 'AccountDetails', component: AccountDetails }, // Account details
            { path: 'wishlist', name: 'WishListPage', component: WishListPage }, // Wish list
            // Shopping wish list
            {
                path: 'cartcheckout',
                name: 'CartCheckout',
                component: () => import('@/components/CartCheckout.vue'),
                props: route => ({ cartItems: route.params.cartItems }),
            }, // Cart checkout
        ],
    },
];


const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes,
});

// Helper function to check if a token is expired
function isTokenExpired(token) {
    try {
        const payload = JSON.parse(atob(token.split('.')[1]));
        return payload.exp * 1000 < Date.now();
    } catch (error) {
        console.error("Invalid token format:", error);
        return true; // Expire the token if there’s an issue
    }
}

// Global before guard to check authentication and role-based access
router.beforeEach(async (to, from, next) => {
    const requiresAuth = to.meta.requiresAuth;
    const requiredRole = to.meta.role;
    const token = localStorage.getItem('authToken');

    // If the route requires authentication

    if (requiresAuth) {
        if (!token || isTokenExpired(token)) {
            // Redirect to login if no valid token
            return next({ name: 'LoginPage' });
        }

        // Decode the token and extract the user's role
        const payload = JSON.parse(atob(token.split('.')[1]));
        const userRole = payload.role;

        // Check if the user has the correct role to access the page
        if (requiredRole && userRole !== requiredRole) {
            // If the user does not have the required role, redirect to home or another page
            return next({ name: 'HomePage' });
        }
    }

    next(); // Allow the navigation
});

// Export the router instance
export default router;

//Axios global configuration to include JWT token in every request
axios.interceptors.request.use((config) => {
    const publicPaths = ['/Contact/create', '/Customer/create' ,'/wishList/assignWishListToCustomer/**',
        '/cart/assignCartToCustomer/',"/comic_book/getAll"]; // List of public endpoints

    // Check if the request URL is not a public route
    if (!publicPaths.includes(config.url)) {
        const token = localStorage.getItem('authToken');
        if (token) {
            config.headers['Authorization'] = `Bearer ${token}`;
        }
    }

    return config;
}, (error) => {
    return Promise.reject(error);
});
axios.interceptors.response.use(
    response => response,
    error => {
        if (error.response && error.response.status === 401 || error.response.status === 403) {
            // Handle token expiration
            localStorage.removeItem('authToken');
            router.push({ name: 'LoginPage' });
        }
        return Promise.reject(error);
    }
);

