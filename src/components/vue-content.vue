<template>
	<div>
		<el-row>
			<el-col>
				<router-view></router-view>
			</el-col>
		</el-row>
	</div>
</template>

<script>
	import vueYear from './content/vue-year.vue'
	export default {
		name: "vue-content",
		data() {
			return {

			}
		},
		components: {
			vueYear,
		}
	}
</script>
<style lang="less">
	.page-block {
		margin: 50px 0 0 400px;
	}
</style>