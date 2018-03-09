import Vue from 'vue';
import App from './app.vue';
import router from './router';
import ElementUI  from './element';
import VueResource from 'vue-resource';
import store from './store';

Vue.use(VueResource);
Vue.http.options.emulateHTTP = true;
Vue.http.options.emulateJSON = true;


new Vue({
	router,
	store,
	render: h=>h(App)
}).$mount("#app");