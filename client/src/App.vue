<template>
  <v-app>
    <v-app-bar app color="primary" dark>
      <router-link to="/">
        <div class="text-h6 logo">
          311 Incidents
        </div>
      </router-link>

      <v-spacer></v-spacer>

      <router-link to="/login">
        <v-btn text>
          Login
        </v-btn>
      </router-link>
      <div class="mx-2">
        or
      </div>
      <router-link to="/signup">
        <v-btn text>
          Signup
        </v-btn>
      </router-link>
    </v-app-bar>

    <v-main>
      <router-view></router-view>
    </v-main>
  </v-app>
</template>

<script>
  export default {
    name: "App"
  };
</script>

<style lang="scss">
  .logo {
    text-decoration: none;
    color: white;
  }
</style>
