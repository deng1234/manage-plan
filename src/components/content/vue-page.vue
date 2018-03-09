<template>
	<el-row>
		<el-col :span="20" :offset="2">
			<el-pagination
				class="right-page"
				@current-change="pageChange"
				:page-size="pageSize"
				layout="prev, pager, next"
				:total="totalRecord">
			</el-pagination>
		</el-col>
	</el-row>
</template>

<script>
	export default {
		name: "vue-page",
		data() {
			return {
				pageSize: 21,
			}
		},
		methods: {
			pageChange(page) {
				this.$emit("getPage", page);
			}
		},
		props: ['totalRecord']
	}
</script>
<style>
	.right-page {
		margin-right: 20px;
		float: right;
	}
</style>