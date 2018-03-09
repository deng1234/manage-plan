<template>
	<el-row class="title">
		<el-col :span="8" :offset="8">
			<h3>{{title}}</h3>
		</el-col>
	</el-row>
</template>

<script>
	export default {
		name: "vue-header",
		data() {
			return {
				title: "xiaoD·2018之学习生活"
			}
		}
	}
</script>
<style lang="less">
	.title {
		font-size: 30px;
		font-weight: 600;
		font-family: monospace;
		text-align: center;
		// text-shadow: 10px 10px 1px gray;
		padding-top: 50px;
	}
</style>