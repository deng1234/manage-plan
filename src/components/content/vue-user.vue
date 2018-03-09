<template>
	<el-row>
		<el-table :data="userData">
			<el-table-column label="用户 ID" prop="userid"></el-table-column>
			<el-table-column label="电话号码" prop="username"></el-table-column>
			<el-table-column label="注册时间" prop="registetime"></el-table-column>
			<el-table-column label="登录次数" prop="login_times"></el-table-column>
			<el-table-column label="领书数量" prop="getBookNumber"></el-table-column>
			<el-table-column label="捐书数量" prop="donationBookNumber"></el-table-column>
			<el-table-column label="操作">
				<template slot-scope="scope">
					<el-button 
						@click.native.prevent="donateBook"
						type="text"
						size="small">
						捐书
					</el-button>
					<el-button 
						@click.native.prevent="getBook"
						type="text"
						size="small">
						领书
					</el-button>
					<el-button 
						@click.native.prevent="sendMsg"
						type="text"
						size="small">
						发送短信
					</el-button>
				</template>
			</el-table-column>
		</el-table>
	</el-row>
</template>
<script>
	export default {
		name: "vue-user",
		data() {
			return {
				adminUrl: "http://www.boyalibrary.com/server/adminServer.php",
				page: 1,
				userData: [
				]
			}
		},
		methods: {
			donateBook() {

			},
			getBook() {

			},
			sendMsg() {
				
			},
			messageAlert(title, msg) {
				this.$alert(msg, title, {
		      		confirmButtonText: '确定',
		        });
			},
			getAllUser() {
				let options = {
					action: "getAllUser",
					page: this.page
				}
				this.$http.post(this.adminUrl, options).then(response => {
					let result = JSON.parse(response.body.trim());
					var _this = this;
					if(result.errCode == 0) {
						result.data.map(function(currentValue,index,arr) {
							arr[index].book_id = (_this.page - 1) * 21 + index + 1;
						})
						this.userData = result.data;
					} else {
						this.messageAlert("提示", result.data);
					}
				}, response => {
					this.messageAlert('警告','服务器出现异常,请联系管理员');
				});
			}
		},
		mounted() {
			this.getAllUser();
		}
	}
</script>
<style lang="less">

</style>