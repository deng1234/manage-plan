<template>
	<vueStep :steps="yearData"></vueStep>
</template>

<script>
	import vueStep from './common/vue-step.vue';
	export default {
		name: 'vue-year',
		data() {
			return {
				serverUrl: "http://127.0.0.1:8090/xiaoD2018/Server",
				yearData:[]
			}
		},
		methods: {
			messageAlert(title, msg) {
				this.$alert(msg, title, {
		      		confirmButtonText: '确定',
		        });
			},
			getYearData() {
				let options = {
					action: "listTargetByType",
					period: "year"
				}
				this.$http.post(this.serverUrl, options).then(response => {
					let result = response.body.split("#")[0];
					this.yearData = eval(result);
					if(this.yearData.length === 0) {
						this.messageAlert('提示','暂时没有记录，请在目标制定中添加');
					} else {
						this.$store.commit('saveYearData', this.yearData);
					}
				}, response => {
					this.messageAlert('警告','服务器出现异常,请联系管理员');
				});
			}
		},
		mounted() {
			if(this.$store.state.isGetYearData === false) {
				this.getYearData();
			} else {
				this.yearData = this.$store.state.yearData;
			}
		},
		components: {
			vueStep
		}
	}
</script>