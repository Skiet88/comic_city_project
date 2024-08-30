<template>
  <div class="hero" :style="{ backgroundImage: 'url(https://images.squarespace-cdn.com/content/v1/51b3dc8ee4b051b96ceb10de/2c5a9d72-7dd6-4623-b1b8-f6fd69e43777/heres-a-collection-of-deadpool-wolverine-comic-book-variant-covers.jpg?format=2500w)' }">
    <div class="overlay"></div>
    <div class="hero-text">
      <h1>The Rise of Dracula!!!</h1>
      <button class="hero-btn">New. Read Now!</button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'HeroSection',
};
</script>

<style scoped>
.hero {
  height: 300px;
  background-size: cover;
  background-position: center;
  position: relative;
  color: white;
  overflow: hidden;
  animation: scaleAnimation 15s ease-in-out infinite;
}

.overlay {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5); /* Darkens the image */
  z-index: 1;
}

.hero-text {
  position: absolute;
  bottom: 20px;
  left: 20px;
  z-index: 2;
}

.hero-btn {
  background-color: #e63946;
  color: white;
  border: none;
  padding: 10px 20px;
  cursor: pointer;
  font-size: 1rem;
  z-index: 2;
}

@keyframes scaleAnimation {
  0%, 100% {
    transform: scale(1);
  }
  50% {
    transform: scale(1.01);
  }
}
</style>
