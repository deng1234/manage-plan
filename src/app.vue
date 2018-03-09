<template>
	<el-container>
		<el-header>
			<vueHeader></vueHeader>
		</el-header>
		<el-container class="main">
			<el-aside width="200px">
				<vueNav></vueNav>
			</el-aside>
			<el-main>
				<vueContent></vueContent>
			</el-main>
		</el-container>
	</el-container>
</template>

<script>
	import vueHeader from './components/vue-header.vue';
	import vueNav from './components/vue-nav.vue';
	import vueContent from './components/vue-content.vue';

	export default{
		name: "app",
		components: {
			vueHeader,
			vueNav,
			vueContent
		}
	}
</script>
<style lang="less">
	.main {
		margin: 120px 100px 0 100px;
	}
</style>