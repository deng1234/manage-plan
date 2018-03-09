<template>
	<vueCollapse :collapseData="monthData"></vueCollapse>
</template>

<script>
	import vueCollapse from './common/vue-collapse.vue'
	export default {
		name: 'vue-month',
		data() {
			return {
				serverUrl: "http://127.0.0.1:8090/xiaoD2018/Server",
				monthData: []
			}
		},
		methods: {
			messageAlert(title, msg) {
				this.$alert(msg, title, {
		      		confirmButtonText: '确定',
		        });
			},
			getMonthData() {
				let options = {
					action: "listTargetByType",
					period: "month"
				}
				this.$http.post(this.serverUrl, options).then(response => {
					let result = response.body.split("#")[0];
					this.monthData = eval(result);
					if(this.monthData.length === 0) {
						this.messageAlert('提示','暂时没有记录，请在目标制定中添加');
					} else {
						this.$store.commit('saveMonthData', this.monthData);
					}
				}, response => {
					this.messageAlert('警告','服务器出现异常,请联系管理员');
				});
			}
		},
		mounted() {
			if(this.$store.state.isGetMonthData === false) {
				this.getMonthData();
			} else {
				this.monthData = this.$store.state.monthData;
			}
		},
		components: {
			vueCollapse
		}
	}
</script>