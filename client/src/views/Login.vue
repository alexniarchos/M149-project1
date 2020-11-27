<template>
  <v-container>
    <v-row justify="center">
      <v-col cols="5">
        <div class="text-h4 d-flex justify-center">
          Login
        </div>
        <v-form
          ref="form"
          v-model="isFormValid"
          lazy-validation
        >
          <v-text-field
            v-model="username"
            :rules="usernameRules"
            label="Username"
            required
          ></v-text-field>

          <v-text-field
            v-model="password"
            :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
            :type="showPassword ? 'text' : 'password'"
            :rules="passwordRules"
            label="Password"
            counter
            required
            @click:append="showPassword = !showPassword"
          ></v-text-field>

          <v-btn
            :loading="submitting"
            :disabled="!isFormValid"
            color="success"
            class="mr-4"
            @click="onSubmit()"
          >
            Login
          </v-btn>
        </v-form>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import axios from 'axios';

export default {
  name: "Login",
  data: () => ({
    username: '',
    password: '',
    isFormValid: false,
    showPassword: false,
    wrongCredentials: false,
    submitting: false,
    usernameRules: [
      v => !!v || 'Username is required'
    ],
    passwordRules: [
      value => !!value || 'Password is required.',
      v => v.length >= 8 || 'Min 8 characters'
    ]
  }),
  methods: {
    onSubmit () {
      this.$store.commit('setLoggedin', true);

      this.$refs.form.validate();

      if (this.isFormValid) {
        this.submitting = true;
        axios.post('http://localhost:8080/auth/login', {
          username: this.username,
          password: this.password
        }).then(({data}) => {
          localStorage.setItem('jwt', data.token);
          this.$router.push('/');
          this.$store.commit('setLoggedin', true);
        }).catch(err => {
          console.error(err);
          this.errorMessage = 'Invalid credentials';
        }).finally(() => {
          this.submitting = false;
        });
      }
    }
  },
  mounted() {
    if (localStorage.getItem('jwt')) {
      this.$router.push('search');
    }
  }
};
</script>
