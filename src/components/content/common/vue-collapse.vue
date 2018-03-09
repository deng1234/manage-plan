<template>
	<el-row>
		<el-col :span="20" :offset="2">
			<el-collapse v-model="activeIndex" @change="getTargetDetail" accordion>
				<el-collapse-item v-for="(item, index) in collapseData" :title="item.description" :name="index">
					<div v-for="(detail, index) in details">{{index+1}}、{{detail.description}}</div>
				</el-collapse-item>
			</el-collapse>
		</el-col>
	</el-row>
</template>

<script>
	export default {
		name: "vue-collapse",
		data() {
			return {
				serverUrl: "http://127.0.0.1:8090/xiaoD2018/Server",
				activeIndex: null,
				isFirst: false,
				lastIndex: null,
				details: []
			}
		},
		methods: {
			messageAlert(title, msg) {
				this.$alert(msg, title, {
		      		confirmButtonText: '确定',
		        });
			},
			getTargetDetail() {
				if(this.activeIndex == null) {
					this.activeIndex = this.lastIndex;
				}
				this.lastIndex = this.activeIndex;
				var targetID = this.collapseData[this.activeIndex].targetID;
				let options = {
						action: "listTargetDetail",
						targetID: targetID
					}
					this.$http.post(this.serverUrl, options).then(response => {
						var result = response.body.split("#")[0];
						this.details = eval(result);
					}, response => {
						this.messageAlert('警告','服务器出现异常,请联系管理员');
				});
			}
		},
		props: ['collapseData']
	}
</script>