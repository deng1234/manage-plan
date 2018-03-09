<template>
	<div class="input-area">
		<el-row>
			<el-col :span="20" :offset="2">
				<el-input
					type="textarea"
					:rows="rows"
					placeholder="请输入内容"
					v-model="inputValue">
				</el-input>
			</el-col>
		</el-row>
		<el-row class="submit-btn">
			<el-col :offset="19" :span="3">
				<el-button round type="primary" @click="setValue">提交</el-button>
			</el-col>
		</el-row>
	</div>
</template>

<script>
	export default {
		name: "vue-input",
		data() {
			return {
				inputValue: "",
				rows: 7,
			}
		},
		methods: {
			setValue() {
				this.$emit("getValue", this.inputValue);
				this.inputValue = "";
			}
		}
	}
</script>
<style>
	.input-area {
		margin-top: 30px;
	}
	.submit-btn {
		margin-top: 30px;
	}
</style>