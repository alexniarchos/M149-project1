<template>
  <v-container>
    <v-row justify="center">
      <v-col cols="5">
        <div class="text-h4 d-flex justify-center">
          Signup
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

          <div v-if="errorMessage" style="color: red;">{{errorMessage}}</div>

          <v-btn
            :disabled="!isFormValid"
            color="success"
            class="mr-4"
            @click="onSubmit()"
          >
            Signup
          </v-btn>
        </v-form>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import axios from 'axios';

export default {
  name: "Signup",
  data: () => ({
    username: '',
    password: '',
    isFormValid: false,
    showPassword: false,
    errorMessage: '',
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
      this.$refs.form.validate();

      this.$nextTick(() => {
        if (this.isFormValid) {
          axios.post('http://localhost:8080/auth/signup', {
            username: this.username,
            password: this.password
          }).then(() => {
            this.$router.push('search');
          }).catch(() => {
            this.errorMessage = 'Invalid credentials';
          }).finally(() => {
            this.submitting = false;
          });
        }
      });
    }
  }
};
</script>
