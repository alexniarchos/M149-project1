<template>
  <v-app>
    <v-app-bar app color="primary" dark>
      <router-link to="/">
        <div class="text-h6 logo">
          311 Incidents
        </div>
      </router-link>

      <v-spacer></v-spacer>

      <template v-if="!$store.state.isLoggedin">
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
      </template>
      <template v-else>
        <router-link to="/search">
          <v-btn text>
            Search
          </v-btn>
        </router-link>
        <div class="mx-2">
          or
        </div>
        <router-link to="/insert">
          <v-btn text>
            Insert
          </v-btn>
        </router-link>
        <v-btn dark class="ml-6" @click="onLogout()">
          Logout
        </v-btn>
      </template>
    </v-app-bar>

    <v-main>
      <router-view></router-view>
    </v-main>
  </v-app>
</template>

<script>
  export default {
    name: "App",
    methods: {
      onLogout() {
        localStorage.removeItem('jwt');
        this.$store.commit('setLoggedin', false);
        this.$router.push('Login');
      }
    },
    mounted() {
      this.$store.commit('setLoggedin', !!localStorage.getItem('jwt'));
    }
  };
</script>

<style lang="scss">
  .logo {
    text-decoration: none;
    color: white;
  }
</style>
