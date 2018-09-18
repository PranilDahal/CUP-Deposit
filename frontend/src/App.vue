<template>
  <div id="App">
    <Header></Header>

    <Footer></Footer>
</div>
</template>

<script>
import Header from './components/Header'
import Footer from './components/Footer'

export default {
  name: 'App',
  components: {
    Header,
    Footer
  }
}
</script>

<style>
@import './assets/css/generic-classes.css';
@import './assets/css/header.css';
@import './assets/css/footer.css';
</style>