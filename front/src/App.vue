<template>
  <a-row type="flex">
    <a-col flex="100px">
      100px
      <a-space style="width: 100%">
        <a-button type="primary" loading>Loading</a-button>
        <a-button type="primary" size="small" loading>Loading</a-button>
      </a-space>
    </a-col>
    <a-col flex="auto">auto</a-col>
  </a-row>
</template>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
