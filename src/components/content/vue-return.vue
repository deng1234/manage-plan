<template>
	<div>
		<vueSearch @getSearchData="getSearchData"></vueSearch>
		<el-row>
			<el-col :span="20" :offset="2">
				<el-table
					@expand-change="getPlanDetailByPlanID"
					:data="tableData"
					ref="myTable"
					style="width: 100%">
					<el-table-column type="expand">
						<template slot-scope="props">
							<el-form label-position="left" inline class="table-expand">
								<el-form-item :label=" '记录'+(index+1) "  v-for="(item, index) in planDetails">
									<span>{{item.description}}</span>
								</el-form-item>
								<el-form-item label="总结">
									<span>{{ props.row.summary }}</span>
								</el-form-item>
							</el-form>
						</template>
					</el-table-column>
					<el-table-column
						label="计划 ID"
						prop="planID">
					</el-table-column>
					<el-table-column
						label="得分"
						prop="score">
					</el-table-column>
					<el-table-column
						label="时间"
						prop="planTime">
					</el-table-column>
				</el-table>
			</el-col>
		</el-row>
		<vuePage 
			:totalRecord="totalRecord" 
			class="page-block" 
			@getPage="getPage"></vuePage>
	</div>
</template>

<script>
	import vuePage from './vue-page.vue'
	import vueSearch from './vue-search.vue'
	export default {
		name: "vue-return",
		data() {
			return {
				page: 1,
				serverUrl: "http://127.0.0.1:8090/xiaoD2018/Server",
				planDetails: [],
				tableData: [],
				lastRow: [],
				searchValue: "",
				totalRecord: 0,
			}
		},
		methods: {
			messageAlert(title, msg) {
				this.$alert(msg, title, {
		      		confirmButtonText: '确定',
		        });
			},
			getPlanDetailByPlanID(row, expandedRows) {
				if(this.lastRow.length == 0 || this.lastRow == row) {
					this.lastRow = row;
				} else {
					this.$refs.myTable.toggleRowExpansion(this.lastRow, false);
					this.lastRow = row;
				}
				let options = {
					action: "getPlanDetailByPlanID",
					planID: row.planID,
				}
				this.$http.post(this.serverUrl, options).then(response => {
					let result = response.body.split("#")[0];
					this.planDetails = eval(result);
					if(this.planDetails.length == 0) {
						this.messageAlert('提示','目前没有记录,请添加');
					} else {
						this.planDetails = eval(result);
					}
				}, response => {
					this.messageAlert('警告','服务器出现异常,请联系管理员');
				});
			},
			getTableData() {
				this.getTotalRecord();
				let options = {
					action: "listPlanByPage",
					page: this.page,
				}
				this.$http.post(this.serverUrl, options).then(response => {
					let result = response.body.split("#")[0];
					this.tableData = eval(result);
					if(this.tableData.length == 0) {
						this.messageAlert('提示','目前没有记录,请添加');
					} 
				}, response => {
					this.messageAlert('警告','服务器出现异常,请联系管理员');
				});
			},
			getPage(page) {
				this.page = page;
				this.getTableData();
			},
			getSearchData(val) {
				this.searchValue = val;
				this.getTotalRecord();
				let options = {
					action: "listSearchData",
					page: this.page,
					searchValue: this.searchValue
				}
				this.$http.post(this.serverUrl, options).then(response => {
					let result = response.body.split("#")[0];
					this.tableData = eval(result);
					if(this.tableData.length == 0) {
						this.messageAlert('提示','目前没有记录');
					}
				}, response => {
					this.messageAlert('警告','服务器出现异常,请联系管理员');
				});
			},
			getTotalRecord() {
				let options = {
					action: "getTotalRecord",
					searchValue: this.searchValue,
				}
				this.$http.post(this.serverUrl, options).then(response => {
					this.totalRecord = Number(response.body.trim());
					if(this.totalRecord == 0) {
						this.messageAlert('提示','目前没有记录');
					}
				}, response => {
					this.messageAlert('警告','服务器出现异常,请联系管理员');
				});
			}
		},
		mounted() {
			this.getTableData();
		},
		components: {
			vueSearch,
			vuePage
		}
	}
</script>

<style>
	.table-expand {
    	font-size: 0;
  	}
  	.table-expand label {
    	width: 60px;
    	color: #99a9bf;
  	}
  	.table-expand .el-form-item {
	    margin-right: 0;
	    margin-bottom: 0;
    	width: 100%;
  	}
</style>
