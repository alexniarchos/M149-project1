import Vue from "vue";
import VueRouter from "vue-router";
import Login from '../views/Login.vue';
import Signup from '../views/Signup.vue';
import Search from '../views/Search.vue';
import Insert from '../views/Insert.vue';

Vue.use(VueRouter);

const routes = [
  {
    path: "/login",
    alias: ["/"],
    name: "Login",
    component: Login
  },
  {
    path: "/signup",
    name: "Signup",
    component: Signup
  },
  {
    path: "/search",
    name: "Search",
    component: Search
  },
  {
    path: "/insert",
    name: "Insert",
    component: Insert
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

export default router;
