import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    isLoggedin: false
  },
  mutations: {
    setLoggedin(state, value) {
      state.isLoggedin = value;
    }
  },
  actions: {},
  modules: {}
});
