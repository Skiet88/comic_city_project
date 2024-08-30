<template>
  <div class="container-fluid p-0 d-flex flex-column min-vh-100">
    <header class="bg-dark p-3 border-bottom">
      <div class="d-flex justify-content-between align-items-center">

        <div class="d-flex align-items-center">
          <img src="@/assets/Designer.png" alt="Student Space Solutions Logo" style="width: 150px;">
        </div>

        <div class="d-flex align-items-center">
          <a href="#" class="text-light me-3" aria-label="Home">Home</a>
          <a href="#" class="text-light me-3" aria-label="Contact Us">Contact Us</a>
          <div class="dropdown">
            <a class="d-flex align-items-center text-light text-decoration-none dropdown-toggle" href="#" id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false">
              <img src="path_to_user_image" alt="User Profile" style="width: 40px; height: 40px; border-radius: 50%;">
              <span class="ms-2">Me</span>
            </a>
            <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="dropdownUser1">
              <li><a class="dropdown-item" href="#" aria-label="Profile">Profile</a></li>
              <li><a class="dropdown-item" href="#" aria-label="Logout">Logout</a></li>
            </ul>
          </div>
        </div>
      </div>
    </header>
    <div class="row no-gutters flex-grow-1">
      <aside class="col-md-3 col-lg-2 bg-dark sidebar">
        <ul class="nav flex-column">
          <li class="nav-item">
            <router-link class="nav-link text-light" to="/comic-books">Comic Books</router-link>
          </li>
          <li class="nav-item">
            <router-link class="nav-link text-light" to="/add-new-book">Add Comic</router-link>
          </li>
        </ul>
      </aside>
      <main class="col-md-9 col-lg-10 content">
        <router-view></router-view>
      </main>
    </div>
  </div>
</template>

<script>
export default {
  name: 'MainLayout',
};
</script>

<style scoped>
html, body {
  height: 100%;
  margin: 0;
}

.container-fluid {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
}

header {
  z-index: 1;
}

.row {
  flex: 1;
  display: flex;
}

header {
  font-family: 'Comic Sans MS', sans-serif;
  font-weight: bold;
  font-size: 1.5rem;
  color: #fff;
}

header img {
  animation: bounce 5s infinite;
}

@keyframes bounce {
  0%, 100% { transform: translateY(0); }
  50% { transform: translateY(-10px); }
}




.sidebar {
  padding: 20px;
  height: 100vh;
  box-sizing: border-box;
}

.content {
  padding: 20px;
  flex: 1;
  max-height: 100vh;
  overflow-y: auto;
}

.nav-link:hover {
  text-decoration: underline;
}
.sidebar .nav-link {
  text-shadow: 2px 2px 0px #000, -2px -2px 0px #000, 2px -2px 0px #000, -2px 2px 0px #000;
  border: 2px solid #000;
}
.sidebar .nav-link {
  transition: transform 0.2s ease-in-out;
}
.sidebar .nav-link {
  font-family: 'Comic Sans MS', sans-serif;
  font-weight: bold;
  font-size: 1.2rem;
  text-transform: uppercase;
}
.sidebar .nav-link {
  background: #fff;
  border-radius: 10px;
  padding: 10px;
  margin-bottom: 10px;
  position: relative;
  color: #333;
  text-transform: uppercase;
  border: 2px solid #333;
}

.sidebar .nav-link::after {
  content: '';
  position: absolute;
  left: 20px;
  bottom: -10px;
  width: 0;
  height: 0;
  border-left: 10px solid transparent;
  border-right: 10px solid transparent;
  border-top: 10px solid #fff;
}




/*
.sidebar .nav-link:hover {
  transform: scale(1.1) rotate(-5deg);
}*/

</style>
