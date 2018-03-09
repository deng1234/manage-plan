<template>
	<div class="vue-card">
		<el-row>
			<el-col :span="20" :offset="2">
				<el-card>
					<div v-for="(item, index) in cardData" :key="index" class="text item">
				    	{{index+1}}、{{item.description}} 
				    	<el-button plain class="right-btn" round type="primary" size="mini" @click="modifyBtn(index)">修改</el-button>
				    	<el-button plain class="right-btn" round type="primary" size="mini" @click="deleteBtn(index)">删除</el-button>
					</div>
				</el-card>
			</el-col>
		</el-row>
		<el-dialog title="修改计划" width="30%" :visible.sync="dialogFormVisible">
			<el-input type="textarea" v-model="dialogValue" auto-complete="off"></el-input>
			<div slot="footer" class="dialog-footer">
				<el-button @click="dialogFormVisible = false">取 消</el-button>
				<el-button type="primary" @click="onSubmit">确 定</el-button>
			</div>
		</el-dialog>
		<vueInput @getValue="getValue"></vueInput>
	</div>
</template>

<script>
	import vueInput from './vue-input.vue'
	export default {
		name: 'vue-day',
		data() {
			return {
				serverUrl: "http://127.0.0.1:8090/xiaoD2018/Server",
				dialogValue: "",
				addValue: "",
				currentIndex: 0,
				dialogFormVisible: false,
			}
		},
		methods: {
			messageAlert(title, msg) {
				this.$alert(msg, title, {
		      		confirmButtonText: '确定',
		        });
			},
			deleteBtn(index) {
				switch(this.type) {
					case 'year':
					case 'month':
					case 'week':
						this.$emit("deleteRecord", this.cardData[index].targetID);
						break;

					case 'detail':
						this.$emit("deleteRecord", this.cardData[index].detailID);
						break;

					case 'urgency':
					case 'adhere':
						this.$emit("deleteTask", this.cardData[index].taskID);
						break;

					case 'plan':
						this.$emit("deletePlanDetail", this.cardData[index].detailID);
						break;

					default:
					 	break;
				}
				this.cardData.splice(index, 1);
			},
			modifyBtn(index) {
				this.currentIndex = index;
				this.dialogValue = this.cardData[index].description;
				this.dialogFormVisible = true;
			},
			onSubmit() {
				this.dialogFormVisible = false;
				this.$store.commit("saveCardData", this.cardData);
				let description = this.dialogValue;
				switch(this.type) {
					case 'year':
					case 'month':
					case 'week':
					case 'detail':
						this.$emit("modifyRecord", this.currentIndex, description);
						break;

					case 'adhere':
					case 'urgency':
						this.$emit("modifyTask", this.currentIndex, description);
						break;

					case 'plan':
						this.$emit("modifyPlanDetail", this.currentIndex, description);
						break;

					default:
					 	break;
				}
			},
			getValue(val) {
				switch(this.type) {
					case 'year':
					case 'month':
					case 'week':
					case 'detail':
						this.$emit("addRecord", val);
						break;

					case 'adhere':
					case 'urgency':
						this.$emit("addTask", val);
						break;

					case 'plan':
						this.$emit("addPlanDetail", val);
						break;

					default:
					 	break;
				}
			}
		},
		components: {
			vueInput
		},
		props: ['cardData', 'type', 'isSuccess']
	}
</script>
<style>
	.vue-card {
		margin-top: 20px;
	}
	.right-btn {
		float: right;
		margin: 0 20px 0 0;
	}
	.text {
		font-size: 15px;
	}
	.item {
		padding: 18px 0;
		line-height: 30px;
	}
</style>