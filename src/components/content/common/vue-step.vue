<template>
	<el-row>
		<el-col :span="20" :offset="2">
			<el-steps direction="vertical" space="100px" :active="active">
	    		<el-step v-for="(item, index) in steps" :title="'目标' + (index + 1)" :description="item.description"></el-step>
	  		</el-steps>
		</el-col>
	</el-row>
</template>
<script>
	export default {
		name: "vue-step",
		data() {
			return {
				active: 100,
			}
		},
		props: ['steps']
	}
</script>