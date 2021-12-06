import Vue from 'vue'
import router from 'home'

const app = new Vue({
    el: '#app',
    data: {
        username: '',
        password: ''
    },
    methods: {
        getFormValues (submitEvent) {
            this.username = submitEvent.target.elements.username.value
            this.password = submitEvent.target.elements.password.value
        }
    },
    router,
})