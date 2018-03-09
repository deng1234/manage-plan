<template>
	<div>
		<el-row>
			<el-col :span="20" :offset="2">
				<el-card class="box-card" :allow-half="allowHalf">
					<div v-for="(item, index) in planData" :key="index" class="text item">
				    	{{item.description}}
				    	<el-rate 
							v-model="scores[index]"
						    :colors="['#99A9BF', '#F7BA2A', '#FF9900']">
						</el-rate>
					</div>
				</el-card>
			</el-col>
		</el-row>
		<vueInput v-if="showConclusion" @getValue="getValue"></vueInput>
	</div>
</template>
	
<script>

	import vueInput from './common/vue-input.vue';
	export default {
		name: 'vue-conclusion',
		data() {
			return {
				allowHalf: true,
				showConclusion: false,
				serverUrl: "http://127.0.0.1:8090/xiaoD2018/Server",
				planData: [
					
				],
				scores: []
			}
		},
		methods: {
			messageAlert(title, msg) {
				this.$alert(msg, title, {
		      		confirmButtonText: '确定',
		        });
			},
			getPlanData() {
				let options = {
					action: "listPlanDetail",
				}
				this.$http.post(this.serverUrl, options).then(response => {
					let result = response.body.split("#")[0];
					this.planData = eval(result);
					if(this.planData.length == 0) {
						this.messageAlert('提示','目前没有记录,请添加');
					} else {
						this.$store.commit('savePlanData', this.planData);
					}
				}, response => {
					this.messageAlert('警告','服务器出现异常,请联系管理员');
				});
			},
			getValue(val) {
				let totalScore = this.scores.reduce(function(total, currentValue) {
					return total + currentValue;
				});
				let averageValue = totalScore / this.scores.length;
				let options = {
					action: "modifyPlan",
					summary: val,
					score: averageValue,
				}
				this.$http.post(this.serverUrl, options).then(response => {
					var result = Number(response.body.trim());
					if(response.body.trim() == -1) {
						this.messageAlert('提示','提交失败');
					} else {
						this.messageAlert('提示','评价成功');
						this.$store.state.isGetPlanData = false;
						this.$store.state.planData = [];
					}
				}, response => {
					this.messageAlert('警告','服务器出现异常,请联系管理员');
				})
			}
		},
		watch: {
			scores(val) {
				if(val.indexOf(0) < 0) {
					this.showConclusion = true;
				}
			}
		},
		mounted() {
			if(this.$store.state.isGetPlanData == false) {
				this.getPlanData();
			} else {
				this.planData = this.$store.state.planData;
			}
		},
		components: {
			vueInput
		}
	}
</script>
<style>
	.tag {
		float: right;
		margin-right: 20px;
		cursor: pointer;
	}
	.text {
		font-size: 14px;
	}
	.item {
		padding: 18px 0;
		line-height: 30px;
	}
	.box-card {
		margin-top: 20px;
	}
</style>