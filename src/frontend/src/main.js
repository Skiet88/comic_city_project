
import { createApp } from 'vue'
import router from '@/router/router';
import App from './App.vue'
import { library } from '@fortawesome/fontawesome-svg-core'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import { faShoppingCart, faUser,faChevronUp, faSearch, faHeart, faSun } from '@fortawesome/free-solid-svg-icons'
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap';

// Add the icons you need to the library
library.add(faShoppingCart, faUser,faChevronUp, faSearch, faHeart, faSun)


createApp(App).use(router).component('font-awesome-icon', FontAwesomeIcon).mount('#app');
