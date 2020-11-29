<template>
  <v-container>
    <v-row justify="center">
      <v-col cols="8">
        <h1 class="title">
          Search a query
        </h1>
        <v-card elevation="2" class="mb-6">
          <v-card-title>
            <v-select
              :items="queryNames"
              v-model="selectedQueryName"
              label="Select query"
              dense
              solo
            />
          </v-card-title>
          <v-card-subtitle>
            {{ selectedQuery && selectedQuery.desc }}
          </v-card-subtitle>
          <v-card-text>
            <div
              v-for="(field, index) in selectedQuery && selectedQuery.requiredFields"
              :key="index"
            >
              <v-menu
                v-if="field === 'minRange'"
                v-model="showCalendarMinRange"
                :close-on-content-click="false"
                :nudge-right="40"
                transition="scale-transition"
                offset-y
                min-width="290px"
              >
                <template v-slot:activator="{on, attrs}">
                  <v-text-field
                    v-model="minRange"
                    label="Select min range"
                    prepend-icon="mdi-calendar"
                    readonly
                    v-bind="attrs"
                    v-on="on"
                  ></v-text-field>
                </template>
                <v-date-picker
                  v-model="minRange"
                  @input="showCalendarMinRange = false"
                ></v-date-picker>
              </v-menu>

              <v-menu
                v-if="field === 'maxRange'"
                v-model="showCalendarMaxRange"
                :close-on-content-click="false"
                :nudge-right="40"
                transition="scale-transition"
                offset-y
                min-width="290px"
              >
                <template v-slot:activator="{on, attrs}">
                  <v-text-field
                    v-model="maxRange"
                    label="Select max range"
                    prepend-icon="mdi-calendar"
                    readonly
                    v-bind="attrs"
                    v-on="on"
                  ></v-text-field>
                </template>
                <v-date-picker
                  v-model="maxRange"
                  @input="showCalendarMaxRange = false"
                ></v-date-picker>
              </v-menu>

              <v-menu
                v-if="field === 'day'"
                v-model="showCalendarDay"
                :close-on-content-click="false"
                :nudge-right="40"
                transition="scale-transition"
                offset-y
                min-width="290px"
              >
                <template v-slot:activator="{on, attrs}">
                  <v-text-field
                    v-model="day"
                    label="Select day"
                    prepend-icon="mdi-calendar"
                    readonly
                    v-bind="attrs"
                    v-on="on"
                  ></v-text-field>
                </template>
                <v-date-picker v-model="day" @input="showCalendarDay = false"></v-date-picker>
              </v-menu>

              <v-select
                v-if="field === 'type'"
                v-model="type"
                :items="eventTypes"
                label="Select event type"
                outlined
              ></v-select>

              <v-text-field
                v-if="field === 'minLatitude'"
                v-model="minLatitude"
                type="number"
                label="Type the min latitude"
                outlined
              ></v-text-field>

              <v-text-field
                v-if="field === 'maxLatitude'"
                v-model="maxLatitude"
                type="number"
                label="Type the max latitude"
                outlined
              ></v-text-field>

              <v-text-field
                v-if="field === 'minLongitude'"
                v-model="minLongitude"
                type="number"
                label="Type the min longitude"
                outlined
              ></v-text-field>

              <v-text-field
                v-if="field === 'maxLongitude'"
                v-model="maxLongitude"
                type="number"
                label="Type the max longitude"
                outlined
              ></v-text-field>

              <v-text-field
                v-if="field === 'numberOfPremises'"
                v-model="numberOfPremises"
                type="number"
                label="Type the number of premises"
                outlined
              ></v-text-field>

              <v-text-field
                v-if="field === 'zipCode'"
                v-model="zipCode"
                type="number"
                label="Type the zipcode"
                outlined
              ></v-text-field>

              <v-text-field
                v-if="field === 'street'"
                v-model="street"
                label="Type the street address"
                outlined
              ></v-text-field>
            </div>
          </v-card-text>
          <v-card-actions>
            <v-btn
              color="success"
              @click="onQuerySubmit()"
              :loading="submitting"
              :disabled="selectedQueryName === ''"
            >
              Run
            </v-btn>
          </v-card-actions>
        </v-card>
        <v-data-table v-if="items && items.length"
          :headers="headers"
          :items="items" 
          :items-per-page="10" 
          class="elevation-1" 
        />
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import axios from 'axios';
import {queries} from '../assets/queries.json';
import {eventTypes} from '../constants';

export default {
  name: 'Search',
  data: () => ({
    queries,
    eventTypes,
    selectedQueryName: '',
    submitting: false,
    showCalendarMinRange: false,
    showCalendarMaxRange: false,
    showCalendarDay: false,
    minRange: '',
    maxRange: '',
    day: '',
    minLatitude: 0,
    minLongitude: 0,
    maxLatitude: 0,
    maxLongitude: 0,
    numberOfPremises: 0,
    type: '',
    street: '',
    zipCode: 0,
    headers: [],
    items: []
  }),
  computed: {
    queryNames() {
      return this.queries.map(q => q.name);
    },
    selectedQuery() {
      const query = this.queries.find(q => q.name === this.selectedQueryName);
      return query;
    }
  },
  methods: {
    onQuerySubmit() {
      this.submitting = true;
      
      // clear results table
      this.headers = [];
      this.items = [];
      
      const {requiredFields} = this.selectedQuery;
      let params = {};

      if (requiredFields) {
        params = requiredFields.reduce(
          (acc, cur) => ({
            ...acc,
            [cur]: this[cur]
          }),
          {}
        );

        const dateParamsToISO = Object.keys(params).reduce(
          (acc, cur) => ({
            ...acc,
            [cur]: ['minRange', 'maxRange', 'day'].includes(cur)
              ? params[cur] && new Date(params[cur]).toISOString()
              : params[cur]
          }),
          {}
        );

        params = {
          ...params,
          ...dateParamsToISO
        };
      }

      axios
        .post(
          'http://localhost:8080/select',
          {
            query: this.selectedQuery.id,
            ...params
          },
          {
            headers: {
              Authorization: `Bearer ${localStorage.getItem('jwt')}`
            }
          }
        )
        .then(({data}) => {
          if (!data || (!data.length && !typeof data === 'object') || (Array.isArray(data) && data.length === 0)) {
            return;
          }

          if(typeof data === 'object' && !data.length) {
            data = [data];
          }

          this.headers = Object.keys(data[0]).reduce(
            (acc, cur) => [...acc, {text: this.formatTableField(cur), value: cur}],
            []
          );
          this.items = Object.values(data);
        })
        .catch(err => {
          if (err.response && err.response.status === 401) {
            localStorage.removeItem('jwt');
            this.$store.commit('setLoggedin', false);
            this.$router.push('login');
          }
        })
        .finally(() => {
          this.submitting = false;
        });
    },
    formatTableField(str) {
      str = str.replace(/[A-Z]/g, letter => ` ${letter.toLowerCase()}`);
      return str.charAt(0).toUpperCase() + str.slice(1);
    }
  },
  mounted() {
    if (!localStorage.getItem('jwt')) {
      this.$router.push('login');
    }
  },
  watch: {
    selectedQuery: function() {
      // clear results table
      this.headers = [];
      this.items = [];

      // reset input fields
      this.showCalendarMinRange = false;
      this.showCalendarMaxRange = false;
      this.showCalendarDay = false;
      this.minRange = '';
      this.maxRange = '';
      this.day = '';
      this.minLatitude = 0;
      this.minLongitude = 0;
      this.maxLatitude = 0;
      this.maxLongitude = 0;
      this.numberOfPremises = 0;
      this.type = '';
      this.street = '';
      this.zipCode = 0;
    }
  }
};
</script>
