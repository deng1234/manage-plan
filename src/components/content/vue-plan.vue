<template>
	<vueCard 
		:cardData="planData"  
		:type="type" 
		@deletePlanDetail="deletePlanDetail"
		@modifyPlanDetail="modifyPlanDetail"
		@addPlanDetail="addPlanDetail">
	</vueCard>
</template>

<script>
	import vueCard from './common/vue-card.vue';
	export default {
		name: "vue-plan",
		data() {
			return {
				type: "plan",
				serverUrl: "http://127.0.0.1:8090/xiaoD2018/Server",
				planData: []
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
			addPlanDetail(val) {
				let options = {
					action: "addPlanDetail",
					description: val
				}
				this.$http.post(this.serverUrl, options).then(response => {
					let detailID = Number(response.body.trim());
					if(response.body.trim() == -1) {
						this.messageAlert('提示','新增失败');
					} else {
						let plan = {detailID: detailID, description: val};
						this.$store.state.planData.push(plan);
						this.planData = this.$store.state.planData;
					}
				}, response => {
					this.messageAlert('警告','服务器出现异常,请联系管理员');
				});
			},
			deletePlanDetail(detailID) {
				let options = {
					action: "deletePlanDetail",
					detailID: detailID
				}
				this.$http.post(this.serverUrl, options).then(response => {
					if(response.body == 0 ) {
						this.messageAlert('提示','删除失败');
					} 
				}, response => {
					this.messageAlert('警告','服务器出现异常,请联系管理员');
				});
			},
			modifyPlanDetail(index, description) {
				let detailID = this.$store.state.cardData[index].detailID;
				let options = {
					action: "modifyPlanDetail",
					detailID: detailID,
					description: description
				}
				this.$http.post(this.serverUrl, options).then(response => {
					if(response.body == 0 ) {
						this.messageAlert('提示','修改失败');
					} else {
						this.$store.state.planData[index].description = description;
					}
				}, response => {
					this.messageAlert('警告','服务器出现异常,请联系管理员');
				});
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
			vueCard
		}
	}
</script>