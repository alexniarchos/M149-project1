<template>
  <v-container>
    <v-snackbar
      v-model="snackbar"
      top
      :color="snackbarColor"
    >
      {{snackbarText}}
    </v-snackbar>
    <v-row justify="center">
      <v-col cols="8">
        <v-card elevation="2" class="mb-6">
          <v-card-title>
            Insert a new incident
          </v-card-title>
          <v-card-subtitle>
            Fill the following details to submit a new incident
          </v-card-subtitle>
          <v-card-text>
            <v-select
              v-model="fields['type']"
              :items="eventTypes"
              label="Select event type"
              outlined
            />
            <v-row>
              <v-col v-for="(fieldType, index) in filteredFieldTypes" :key="index" cols="6">
                <v-select
                  v-if="fieldTypes[fieldType] === fieldTypes.status"
                  v-model="fields[fieldType]"
                  :items="eventStatusTypes"
                  label="Select event status"
                  outlined
                />

                <v-select
                  v-if="fieldTypes[fieldType] === fieldTypes.treeLocation"
                  v-model="fields[fieldType]"
                  :items="treeLocationTypes"
                  label="Select tree location"
                  outlined
                />

                <v-menu
                  v-if="fieldTypes[fieldType] === fieldTypes.completionDate"
                  v-model="showCalendar"
                  :close-on-content-click="false"
                  :nudge-right="40"
                  transition="scale-transition"
                  offset-y
                  min-width="290px"
                >
                  <template v-slot:activator="{on, attrs}">
                    <v-text-field
                      v-model="fields[fieldType]"
                      label="Select completion date"
                      prepend-icon="mdi-calendar"
                      readonly
                      v-bind="attrs"
                      v-on="on"
                    />
                  </template>
                  <v-date-picker
                    v-model="fields[fieldType]"
                    @input="showCalendar = false"
                  ></v-date-picker>
                </v-menu>

                <v-text-field
                  v-if="fieldTypes[fieldType] === 'Number'"
                  v-model="fields[fieldType]"
                  type="number"
                  :label="`Type the ${fieldType}`"
                  outlined
                />

                <v-text-field
                  v-if="fieldTypes[fieldType] === 'String'"
                  v-model="fields[fieldType]"
                  :label="`Type the ${fieldType}`"
                  outlined
                />
              </v-col>
            </v-row>
          </v-card-text>
          <v-card-actions>
            <v-btn color="success" @click="onInsertSubmit()" :loading="submitting">
              Submit
            </v-btn>

            <v-btn v-if="showClear" color="error" @click="onClear()">
              Clear
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import axios from 'axios';
import {
  eventTypes,
  eventStatusTypes,
  treeLocationTypes,
  fieldTypes,
  fieldTypesOrdered
} from '../constants';

export default {
  name: 'Insert',
  data: () => ({
    fields: {},
    eventTypes,
    eventStatusTypes,
    treeLocationTypes,
    fieldTypes,
    submitting: false,
    showCalendar: false,
    snackbar: false,
    snackbarColor: '',
    snackbarText: ''
  }),
  computed: {
    filteredFieldTypes() {
      if (!this.fields.type) {
        return;
      }
      const {requiredFields} = this.eventTypes.find(e => e.value.includes(this.fields.type));

      return fieldTypesOrdered.reduce(
        (acc, cur) => requiredFields.includes(cur) ? [...acc, cur] : acc,
        []
      );
    },
    showClear() {
      return !!this.fields.type;
    },
    selectedType() {
      return this.fields.type;
    }
  },
  methods: {
    onInsertSubmit() {
      this.submitting = true;

      // format date to ISO string
      const completionDate =
        this.fields.completionDate && new Date(this.fields.completionDate).toISOString();

      const stringFields = Object.keys(this.fields).reduce((acc, cur) => ({...acc, [cur]: this.fields[cur].length === 0 ? null : this.fields[cur]}), {});

      const fields = {
        ...this.fields,
        ...stringFields,
        completionDate
      };

      axios
        .post('http://localhost:8080/insert', fields, {
          headers: {
            Authorization: `Bearer ${localStorage.getItem('jwt')}`
          }
        })
        .then(() => {
          this.snackbarColor = 'success';
          this.snackbarText = 'Inserted incident successfully';
          this.snackbar = true;
        })
        .catch(err => {
          this.snackbarColor = 'error';
          this.snackbarText = 'Oops something went wrong';
          this.snackbar = true;

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
    onClear() {
      this.fields = {};
    }
  },
  mounted() {
    if (!localStorage.getItem('jwt')) {
      this.$router.push('login');
    }
  },
  watch: {
    selectedType: function() {
      const {type} = this.fields;

      this.fields = {
        type
      };
    }
  }
};
</script>
