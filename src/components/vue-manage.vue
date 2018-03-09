<template>
	<div>
		<el-row>
			<el-col	:span="5" :offset="2">
				<el-select v-model="targetType" @change="getTargetType" placeholder="请选择目标周期">
					<el-option
						v-for="item in targetOptions"
						:key="item.value"
						:label="item.label"
						:value="item.value">
					</el-option>
				</el-select>
			</el-col>
			<el-col :span="5" :offset="1">
				<el-select v-if="showSubSelect" v-model="subSelectValue" @change="getTargetDetailData" placeholder="请选择">
					<el-option
						v-for="item in subSelectData"
						:key="item.targetID"
						:label="item.description"
						:value="item.targetID">
					</el-option>
				</el-select>
			</el-col>
		</el-row>
		<vueCard 
			:cardData="cardData" 
			:type="type"
			@deleteRecord="deleteRecord"
			@modifyRecord="modifyRecord"
			@addRecord="addRecord"
			:isSuccess="isDeleteTargetSuccess"
		></vueCard>
	</div>
</template>

<script>
	import vueInput from './content/common/vue-input.vue'
	import vueCard from './content/common/vue-card.vue'
	export default {
		name: "vue-manage",
		data() {
			return {
				targetType: "",
				type: "",
				subSelectValue: null,
				cardData: [],
				subSelectData: [],
				showSubSelect: false,
				isDeleteTargetSuccess: false,
				serverUrl: "http://127.0.0.1:8090/xiaoD2018/Server",
				targetOptions: [
					{value: "year", label: "总目标"},
					{value: "month", label: "月目标"},
					{value: "week", label: "周目标"}
				]
			}
		},
		methods: {
			messageAlert(title, msg) {
				this.$alert(msg, title, {
		      		confirmButtonText: '确定',
		        });
			},
			getTargetType() {
				this.$store.commit('saveTargetType', this.targetType);
				this.getCardData();
				if(this.targetType == "month" || this.targetType == "week") {
					this.subSelectData = this.cardData;
					this.showSubSelect = true;
				} else if(this.targetType == "year") {
					this.showSubSelect = false;
				}
				this.subSelectValue = null;
				this.type = this.targetType;
			},
			getTargetDetailData() {
				this.type = "detail";
				let options = {
					action: "getTargetDetailData",
					targetID: this.subSelectValue
				}
				this.$http.post(this.serverUrl, options).then(response => {
					if(response.body == "-1" || response.body == null) {
						this.messageAlert('警告','请求失败');
					} else {
						var result = response.body.split("#")[0];
						this.cardData = eval(result);
						this.$store.commit('saveDetailData', this.cardData);
					}
				}, response => {
					this.messageAlert('警告','服务器出现异常,请联系管理员');
				});
			},
			getCardData() {
				if(this.targetType == 'year') {
					if(this.$store.state.isGetYearData === true) {
						this.cardData = this.$store.state.yearData;
					} else {
						this.getTargetData();
					}
				} else if (this.targetType == "month") {
					if(this.$store.state.isGetMonthData === true) {
						this.cardData = this.$store.state.monthData;
					} else {
						this.getTargetData();
					}
				} else if (this.targetType == "week") {
					if(this.$store.state.isGetWeekData === true) {
						this.cardData = this.$store.state.weekData;
					} else {
						this.getTargetData();
					}
				}
			},
			getTargetData() {
				let options = {
					action: "listTargetByType",
					period: this.targetType
				}
				this.$http.post(this.serverUrl, options).then(response => {
					let result = response.body.split("#")[0];
					this.cardData = eval(result);
					if(result.length == 0) {
						this.messageAlert('提示','暂时没有记录');
					} else {
						if(this.targetType == "year") {
							this.$store.commit('saveYearData', this.cardData);
						} else if (this.targetType == "month") {
							this.subSelectData = this.cardData;
							this.$store.commit('saveMonthData', this.cardData);
						} else if (this.targetType == "week") {
							this.subSelectData = this.cardData;
							this.$store.commit('saveWeekData', this.cardData);
						}
					}
				}, response => {
					this.messageAlert('警告','服务器出现异常,请联系管理员');
				});
			},
			deleteRecord(recordID) {
				var action = "deleteTarget";
				if(this.type == 'detail') {
					action = "deleteTargetDetail";
				}
				let options = {
					action: action,
					recordID: recordID
				}
				this.$http.post(this.serverUrl, options).then(response => {
					if(response.body == "0") {
						this.messageAlert('提示','删除失败');
					} 
				}, response => {
					this.messageAlert('警告','服务器出现异常,请联系管理员');
				});
			},
			modifyRecord(index, description) {
				let action = "updateTarget";
				let recordID = this.$store.state.cardData[index].targetID;
				if(this.type == 'detail') {
					action = "updateTargetDetail";
					recordID = this.$store.state.cardData[index].detailID;
				}
				let options = {
					action: action,
					recordID: recordID,
					description: description
				}
				this.$http.post(this.serverUrl, options).then(response => {
					if(response.body == 0) {
						this.messageAlert('提示','修改失败');
					} else {
						switch(this.type) {
							case "year":
								this.$store.state.yearData[index].description = description;
								break;

							case "month":
								this.$store.state.monthData[index].description = description;
								break;

							case "week":
								this.$store.state.weekData[index].description = description;
								break;

							case "detail":
								this.$store.state.detailData[index].description = description;
								break;

							default:
								break;
						}
					}
				}, response => {
					this.messageAlert('警告','服务器出现异常,请联系管理员');
				});
			},
			addRecord(val) {
				let action = "addTarget";
				if(this.type == "detail") {
					action = "addTargetDetail";
				}
				let options = {
					action: action,
					description: val,
					period: this.type,
					targetID: this.subSelectValue
				}
				this.$http.post(this.serverUrl, options).then(response => {
					let result = Number(response.body.trim());
					if(response.body.trim() == -1) {
						this.messageAlert('提示','新增失败');
					} else {
						let record = {targetID: result, description: val};
						if(this.type == "detail") {
							let record = {detailID: result, description: val};
						}
						switch(this.type) {
							case "year":
								this.$store.state.yearData.push(record);
								break;

							case "month":
								this.$store.state.monthData.push(record);
								this.subSelectData = this.cardData;
								break;

							case "week":
								this.$store.state.weekData.push(record);
								this.subSelectData = this.cardData;
								break;

							case "detail":
								this.$store.state.detailData.push(record);
								break;

							default:
								break;
						}
					}
				}, response => {
					this.messageAlert('警告','服务器出现异常,请联系管理员');
				});
			}
		},
		mounted() {
			this.targetType = this.$store.state.targetType;
			this.getTargetType();
		},
		components: {
			vueInput,
			vueCard
		}
	}
</script>