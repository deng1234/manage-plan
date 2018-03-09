import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

const state = {
	isGetYearData: false,
	yearData: [],
	isGetMonthData: false,
	monthData: [],
	isGetWeekData: false,
	weekData: [],
	cardData: [],
	isGetUrgencyData: false,
	urgencyData: [],
	isGetAdhereData: false,
	adhereData: [],
	isGetPlanData: false,
	planData: [],
	targetType: "year",
	detailData: [],
}

const mutations = {
	saveYearData(state, yearData) {
		state.yearData = yearData;
		state.isGetYearData = true;
	},
	saveMonthData(state, monthData) {
		state.monthData = monthData;
		state.isGetMonthData = true;
	},
	saveWeekData(state, weekData) {
		state.weekData = weekData;
		state.isGetWeekData = true;
	},
	saveTargetType(state, targetType) {
		state.targetType = targetType;
	},
	saveCardData(state, cardData) {
		state.cardData = cardData;
	},
	saveUrgencyData(state, urgencyData) {
		state.urgencyData = urgencyData;
		state.isGetUrgencyData = true;
	},
	saveAdhereData(state, adhereData) {
		state.adhereData = adhereData;
		state.isGetAdhereData = true;
	},
	savePlanData(state, planData) {
		state.planData = planData;
		state.isGetPlanData = true;
	},
	saveDetailData(state, detailData) {
		state.detailData = detailData;
	}
}

export default new Vuex.Store({
	state,
	mutations
});