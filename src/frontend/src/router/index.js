import { createRouter, createWebHistory } from 'vue-router';


import ComicBooksTable from '@/components/ComicBooksTable.vue';
import AddNewBook from "@/components/AddNewBook.vue";
import EditBook from "@/components/EditBook.vue";

const routes = [
    {
        path: '/',

        children: [
            {
                path: '',
                redirect: 'comic-books',
            },
            {
                path: 'comic-books',
                component: ComicBooksTable,
            },
            {
                path: 'add-new-book',
                component: AddNewBook,
            },
            {
                path: 'edit-book/:id', // Dynamic route for editing a book
                component: EditBook,

            },
        ],
    },
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;
