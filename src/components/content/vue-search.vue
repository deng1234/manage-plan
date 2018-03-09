<template>
	<el-row>
		<el-col :span="18" :offset="3">
			<el-form :inline="true">
				<el-form-item>
					<el-input v-model="searchValue" class="searchInput"></el-input>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" @click="onSubmit">查询</el-button>
				</el-form-item>
			</el-form>
		</el-col>
	</el-row>
</template>
<script>
	export default {
		name: "vue-search",
		data() {
			return {
				searchValue: "",
			}
		},
		methods: {
			onSubmit() {
				this.$emit("getSearchData", this.searchValue);
			}
		}
	}
</script>
<style lang="less">
	.searchInput {
		width: 500px !important;
	}
</style>