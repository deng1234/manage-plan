<template>
	<el-row class="nav">
		<el-col>
			<h2>{{title}}</h2>
			<el-menu default-active="1" :router="isRouter" @select="transform">
				<el-menu-item index="1" route="/year">
					<i class="el-icon-menu"></i>
					<span slot="title">总目标</span>
				</el-menu-item>
				<el-menu-item index="2" route="/month">
					<i class="el-icon-bell"></i>
					<span slot="title">月目标</span>
				</el-menu-item>
				<el-menu-item index="3" route="/week">
					<i class="el-icon-tickets"></i>
					<span slot="title">周目标</span>
				</el-menu-item>
				<el-menu-item index="4" route="/urgency">
					<i class="el-icon-time"></i>
					<span slot="title">迫在眉睫</span>
				</el-menu-item>
				<el-menu-item index="5" route="/adhere">
					<i class="el-icon-star-on"></i>
					<span slot="title">持之以恒</span>
				</el-menu-item>
				<el-menu-item index="6" route="/plan">
					<i class="el-icon-edit-outline"></i>
					<span slot="title">明日计划</span>
				</el-menu-item>
				<el-menu-item index="7" route="/conclusion">
					<i class="el-icon-refresh"></i>
					<span slot="title">今日总结</span>
				</el-menu-item>
				<el-menu-item index="8" route="/return">
					<i class="el-icon-back"></i>
					<span slot="title">追忆往昔</span>
				</el-menu-item>
				<el-menu-item index="9" route="/manage">
					<i class="el-icon-back"></i>
					<span slot="title">目标制定</span>
				</el-menu-item>
			</el-menu>
		</el-col>
	</el-row>
</template>

<script>
	export default {
		name: "vue-nav",
		data() {
			return {
				title: "菜单栏",
				isRouter: true,
			}
		},
		methods: {
			transform(index) {
				if(index == 1 || index == 2) {
					
				} else {
					
				}
			}
		}
	}
</script>
<style lang="less">
	.nav {
		h2 {
			font-size: 24px;
			font-family: monospace;
			padding-left: 25px;
		}
	}
</style>