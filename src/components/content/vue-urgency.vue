<template>
	<div>
		<vueCard 
			@addTask="addTask"
			@modifyTask="modifyTask"
			@deleteTask="deleteTask"
			:cardData="urgencyData" 
			:type="type">
		</vueCard>
	</div>
</template>
<script>
	import vueCard from './common/vue-card.vue';
	export default {
		name: 'vue-urgency',
		data() {
			return {
				type: "urgency",
				serverUrl: "http://127.0.0.1:8090/xiaoD2018/Server",
				urgencyData: [],
			}
		},
		methods: {
			messageAlert(title, msg) {
				this.$alert(msg, title, {
		      		confirmButtonText: '确定',
		        });
			},
			getUrgencyData() {
				let options = {
					action: "listTaskByType",
					type: "urgency"
				}
				this.$http.post(this.serverUrl, options).then(response => {
					let result = response.body.split("#")[0];
					this.urgencyData = eval(result);
					if(this.urgencyData.length == 0) {
						this.messageAlert('提示','目前没有记录，请添加');
					} else {
						this.$store.commit('saveUrgencyData', this.urgencyData);
					}
				}, response => {
					this.messageAlert('警告','服务器出现异常,请联系管理员');
				});
			},
			deleteTask(taskID) {
				let options = {
					action: "deleteTask",
					taskID: taskID
				}
				this.$http.post(this.serverUrl, options).then(response => {
					if(response.body.trim() == 0) {
						this.messageAlert('提示','删除失败');
					} 
				}, response => {
					this.messageAlert('警告','服务器出现异常,请联系管理员');
				});
			},
			modifyTask(index, description) {
				let taskID = this.$store.state.cardData[index].taskID;
				let options = {
					action: "updateTask",
					taskID: taskID,
					description: description
				}
				this.$http.post(this.serverUrl, options).then(response => {
					if(response.body.trim() == 0) {
						this.messageAlert('提示','修改失败');
					} else {
						this.$store.state.urgencyData[index].description = description;
					}
				}, response => {
					this.messageAlert('警告','服务器出现异常,请联系管理员');
				});
			},
			addTask(val) {
				let options = {
					action: "addTask",
					description: val,
					type: "urgency",
				}
				this.$http.post(this.serverUrl, options).then(response => {
					let result = Number(response.body.trim());
					if(response.body.trim() == -1) {
						this.messageAlert('提示','新增失败');
					} else {
						let task = {taskID: result, description: val};
						this.$store.state.urgencyData.push(task);
						this.urgencyData = this.$store.state.urgencyData;
					}
				}, response => {
					this.messageAlert('警告','服务器出现异常,请联系管理员');
				});
			}
		},
		mounted() {
			if(this.$store.state.isGetUrgencyData == false) {
				this.getUrgencyData();
			} else {
				this.urgencyData = this.$store.state.urgencyData;
			}
		},
		components: {
			vueCard,
		}
	}
</script>