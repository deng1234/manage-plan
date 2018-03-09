<template>
	<vueCard 
		@addTask="addTask"
		@modifyTask="modifyTask"
		@deleteTask="deleteTask" 
		:cardData="adhereData" 
		:type="type"></vueCard>
</template>

<script>
	import vueCard from './common/vue-card.vue';
	export default {
		name: "vue-adhere",
		data() {
			return {
				type: "adhere",
				serverUrl: "http://127.0.0.1:8090/xiaoD2018/Server",
				adhereData: [],
			}
		},
		methods: {
			messageAlert(title, msg) {
				this.$alert(msg, title, {
		      		confirmButtonText: '确定',
		        });
			},
			getAdhereData() {
				let options = {
					action: "listTaskByType",
					type: "adhere"
				}
				this.$http.post(this.serverUrl, options).then(response => {
					let result = response.body.split("#")[0];
					this.adhereData = eval(result);
					if(this.adhereData.length == 0) {
						this.messageAlert('提示','目前没有记录,请添加');
					} else {
						this.$store.commit('saveAdhereData', this.adhereData);
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
					if(response.body == 0 ) {
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
					if(response.body == 0 ) {
						this.messageAlert('提示','修改失败');
					} else {
						this.$store.state.adhereData[index].description = description;
					}
				}, response => {
					this.messageAlert('警告','服务器出现异常,请联系管理员');
				});
			},
			addTask(val) {
				let options = {
					action: "addTask",
					description: val,
					type: "adhere",
				}
				this.$http.post(this.serverUrl, options).then(response => {
					let result = Number(response.body.trim());
					if(response.body.trim() == -1) {
						this.messageAlert('提示','新增失败');
					} else {
						let task = {taskID: result, description: val};
						this.$store.state.adhereData.push(task);
						this.adhereData = this.$store.state.adhereData;
					}
				}, response => {
					this.messageAlert('警告','服务器出现异常,请联系管理员');
				});
			}
		},
		mounted() {
			if(this.$store.state.isGetAdhereData == false) {
				this.getAdhereData();
			} else {
				this.adhereData = this.$store.state.adhereData;
			}
		},
		components: {
			vueCard
		}
	}
</script>