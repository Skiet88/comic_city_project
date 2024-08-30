
<template>
  <div class="app-container">
    <header class="header">
      <div class="header-content">
        <div class="header-logo">
          <svg viewBox="0 0 48 48" fill="none" xmlns="http://www.w3.org/2000/svg">
            <g clip-path="url(#clip0_6_535)">
              <path
                  fill-rule="evenodd"
                  clip-rule="evenodd"
                  d="M47.2426 24L24 47.2426L0.757355 24L24 0.757355L47.2426 24ZM12.2426 21H35.7574L24 9.24264L12.2426 21Z"
                  fill="currentColor"
              ></path>
            </g>
            <defs>
              <clipPath id="clip0_6_535"><rect width="48" height="48" fill="white"></rect></clipPath>
            </defs>
          </svg>
          <h2 class="header-title">Comic Books</h2>
        </div>
        <div class="header-links">
          <a class="header-link" href="#">New Arrivals</a>
          <a class="header-link" href="#">Best Sellers</a>
          <a class="header-link" href="#">Genres</a>
          <a class="header-link" href="#">Series</a>
        </div>
      </div>
      <div class="header-actions">
        <label class="search-label">
          <div class="search-container">
            <div class="search-icon">
              <svg xmlns="http://www.w3.org/2000/svg" width="24px" height="24px" fill="currentColor" viewBox="0 0 256 256">
                <path
                    d="M229.66,218.34l-50.07-50.06a88.11,88.11,0,1,0-11.31,11.31l50.06,50.07a8,8,0,0,0,11.32-11.32ZM40,112a72,72,0,1,1,72,72A72.08,72.08,0,0,1,40,112Z"
                ></path>
              </svg>
            </div>
            <input
                placeholder="Search"
                class="search-input"
                v-model="searchQuery"
            />
          </div>
        </label>
        <button class="member-button">
          <span class="member-text">Become a member</span>
        </button>
        <div
            class="profile-image"
            :style="{ backgroundImage: 'url(' + profileImageUrl + ')' }"
        ></div>
      </div>
    </header>
    <div class="main-content">
      <div class="hero-container">
        <div
            class="hero-image"
            :style="{ backgroundImage: 'linear-gradient(0deg, rgba(0, 0, 0, 0.4) 0%, rgba(0, 0, 0, 0) 25%), url(' + heroImageUrl + ')' }"
        >
          <div class="hero-indicators">
            <div class="indicator active"></div>
            <div class="indicator"></div>
            <div class="indicator"></div>
            <div class="indicator"></div>
            <div class="indicator"></div>
          </div>
        </div>
      </div>
      <div class="tabs-container">
        <div class="tabs">
          <a v-for="(tab, index) in tabs" :key="index" class="tab" :class="{'active': tab.active}" href="#">
            <p class="tab-title">{{ tab.name }}</p>
          </a>
        </div>
      </div>
      <div class="comics-grid">
        <div v-for="(comic, index) in comics" :key="index" class="comic">
          <div
              class="comic-image"
              :style="{ backgroundImage: 'url(' + comic.imageUrl + ')' }"
          ></div>
          <div class="comic-info">
            <p class="comic-title">{{ comic.title }}</p>
            <p class="comic-rating">{{ comic.rating }} ({{ comic.reviews }} reviews)</p>
            <p class="comic-price">{{ comic.price }}</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      searchQuery: '',
      profileImageUrl: 'https://cdn.usegalileo.ai/stability/a4af8261-6629-4d5b-96aa-823a4f07007a.png',
      heroImageUrl: 'https://cdn.usegalileo.ai/sdxl10/b6c3916f-2657-484c-a2ab-565baabd0f73.png',
      tabs: [
        { name: 'New Arrivals', active: true },
        { name: 'Best Sellers', active: false },
        { name: 'Genres', active: false },
        { name: 'Series', active: false },
        { name: 'Free Comics', active: false },
        { name: 'Featured', active: false },
        { name: 'Gifts', active: false },
      ],
      comics: [
        { title: 'Batman: The Killing Joke', rating: '4.9', reviews: '500+', price: '$19.99', imageUrl: 'https://cdn.usegalileo.ai/stability/d88c1395-30a9-44c6-a393-f4cff3667386.png' },
        { title: 'Watchmen', rating: '4.8', reviews: '400+', price: '$24.99', imageUrl: 'https://cdn.usegalileo.ai/stability/9bee44f5-c49b-4c1b-8061-e244038c21de.png' },
        // Add more comic data as needed
      ],
    };
  },
};
</script>

<style scoped>
.app-container {
  font-family: "Plus Jakarta Sans", "Noto Sans", sans-serif;
  background-color: #F8F9FB;
  overflow-x: hidden;
}

.header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  border-bottom: 1px solid #E4E9F1;
  padding: 0.75rem 2.5rem;
}

.header-content {
  display: flex;
  align-items: center;
  gap: 2rem;
}

.header-logo {
  display: flex;
  align-items: center;
  gap: 1rem;
  color: #141C24;
}

.header-title {
  font-size: 1.125rem;
  font-weight: bold;
  line-height: 1.5;
  letter-spacing: -0.015em;
}

.header-links {
  display: flex;
  align-items: center;
  gap: 2.25rem;
}

.header-link {
  font-size: 0.875rem;
  font-weight: bold;
  line-height: normal;
  color: #141C24;
  text-decoration: none;
}

.header-actions {
  display: flex;
  align-items: center;
  gap: 2rem;
}

.search-label {
  display: flex;
  min-width: 10rem;
  height: 2.5rem;
  max-width: 16rem;
}

.search-container {
  display: flex;
  flex: 1;
  align-items: center;
  border-radius: 1rem;
  background-color: #E4E9F1;
}

.search-icon {
  display: flex;
  align-items: center;
  justify-content: center;
  padding-left: 1rem;
  border-radius: 1rem 0 0 1rem;
  border-right: 0;
  color: #3F5374;
}

.search-input {
  flex: 1;
  border-radius: 0 1rem 1rem 0;
  background-color: #E4E9F1;
  border: none;
  padding: 0 0.5rem;
  color: #141C24;
  font-size: 1rem;
  font-weight: normal;
  line-height: normal;
  placeholder-color: #3F5374;
}

.member-button {
  display: flex;
  min-width: 5.25rem;
  max-width: 30rem;
  align-items: center;
  justify-content: center;
  border-radius: 1rem;
  height: 2.5rem;
  padding: 0 1rem;
  background-color: #E4E9F1;
  color: #141C24;
  font-size: 0.875rem;
  font-weight: bold;
  line-height: normal;
  letter-spacing: 0.015em;
  cursor: pointer;
  text-decoration: none;
}

.profile-image {
  width: 2.5rem;
  height: 2.5rem;
  border-radius: 50%;
  background-size: cover;
  background-position: center;
}

.main-content {
  padding: 0 2.5rem;
}

.hero-container {
  position: relative;
  margin-bottom: 2.5rem;
}

.hero-image {
  height: 20rem;
  background-size: cover;
  background-position: center;
  border-radius: 1rem;
  position: relative;
}

.hero-indicators {
  display: flex;
  position: absolute;
  bottom: 1rem;
  left: 50%;
  transform: translateX(-50%);
  gap: 0.5rem;
}

.indicator {
  width: 0.75rem;
  height: 0.75rem;
  border-radius: 50%;
  background-color: #B0B0B0;
}

.indicator.active {
  background-color: #FFFFFF;
}

.tabs-container {
  margin-bottom: 2rem;
}

.tabs {
  display: flex;
  gap: 1.5rem;
}

.tab {
  padding: 0.5rem 1rem;
  border-radius: 1rem;
  font-size: 1rem;
  color: #141C24;
  text-decoration: none;
}

.tab.active {
  background-color: #E4E9F1;
}

.tab-title {
  margin: 0;
}

.comics-grid {
  display: grid;
  gap: 1.5rem;
  grid-template-columns: repeat(auto-fill, minmax(15rem, 1fr));
}

.comic {
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
}

.comic-image {
  height: 15rem;
  border-radius: 1rem;
  background-size: cover;
  background-position: center;
}

.comic-info {
  display: flex;
  flex-direction: column;
  gap: 0.25rem;
}

.comic-title {
  font-size: 1rem;
  font-weight: bold;
  color: #141C24;
}

.comic-rating {
  font-size: 0.875rem;
  color: #3F5374;
}

.comic-price {
  font-size: 1rem;
  font-weight: bold;
  color: #141C24;
}
</style>
