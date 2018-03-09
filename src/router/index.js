import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

// lazy-load
const routes = [
	{
		path: '/',
		component: resolve => require(['../components/content/vue-year.vue'], resolve)
	},
	{
		path: '/year',
		component: resolve => require(['../components/content/vue-year.vue'], resolve)
	},
	{
		path: '/month',
		component: resolve => require(['../components/content/vue-month.vue'], resolve)
	},
	{
		path: '/week',
		component: resolve => require(['../components/content/vue-week.vue'], resolve)
	},
	{
		path: '/urgency',
		component: resolve => require(['../components/content/vue-urgency.vue'], resolve)
	},
	{
		path: '/adhere',
		component: resolve => require(['../components/content/vue-adhere.vue'], resolve)
	},
	{
		path: '/plan',
		component: resolve => require(['../components/content/vue-plan.vue'], resolve)
	},
	{
		path: '/conclusion',
		component: resolve => require(['../components/content/vue-conclusion.vue'], resolve)
	},
	{
		path: '/return',
		component: resolve => require(['../components/content/vue-return.vue'], resolve)
	},
	{
		path: '/manage',
		component: resolve => require(['../components/vue-manage.vue'], resolve)
	}
]


export default new VueRouter({
 	base: __dirname,
	routes
})