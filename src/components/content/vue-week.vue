<template>
	<vueCollapse :collapseData="weekData"></vueCollapse>
</template>
<script>

	import vueCollapse from './common/vue-collapse.vue'
	export default {
		name: 'vue-week',
		data() {
			return {
				serverUrl: "http://127.0.0.1:8090/xiaoD2018/Server",
				weekData: []
			}
		},
		methods: {
			messageAlert(title, msg) {
				this.$alert(msg, title, {
		      		confirmButtonText: '确定',
		        });
			},
			getWeekData() {
				let options = {
					action: "listTargetByType",
					period: "week"
				}
				this.$http.post(this.serverUrl, options).then(response => {
					let result = response.body.split("#")[0];
					this.weekData = eval(result);
					if(this.weekData.length === 0) {
						this.messageAlert('提示','暂时没有记录，请在目标制定中添加');
					} else {
						this.$store.commit('saveWeekData', this.weekData);
					}
				}, response => {
					this.messageAlert('警告','服务器出现异常,请联系管理员');
				});
			}
		},
		mounted() {
			if(this.$store.state.isGetWeekData === false) {
				this.getWeekData();
			} else {
				this.weekData = this.$store.state.weekData;
			}
		},
		components: {
			vueCollapse
		}
	}
</script>