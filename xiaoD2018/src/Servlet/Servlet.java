package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Thread.State;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import MANAGE.ManagePlan;
import MANAGE.ManagePlanDetail;
import MANAGE.ManageTarget;
import MANAGE.ManageTargetDetail;
import MANAGE.ManageTask;

/**
 * @author xiaoD
 * Description: 
 */
public class Servlet extends HttpServlet{
	private int pageSize = 21;
	public void init() {
		
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		this.doGet(req, res);
	}
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		req.setCharacterEncoding("utf8");
		res.setCharacterEncoding("UTF-8");
		res.setHeader("Access-Control-Allow-Origin", "*");
		res.setHeader("Access-Control-Allow-Methods", "POST,GET");
		res.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		String action = req.getParameter("action");
		if(!(action==null || "".equals(action))) {
			switch(action) {
				case "listTargetByType":
					this.listTargetByType(req, res, out);
					break;
				
				case "listTargetDetail":
					this.listTargetDetail(req, res, out);
					break;
				
				case "updateTarget":
					this.updateTarget(req, res, out);
					break;
				
				case "addTarget":
					this.addTarget(req, res, out);
					break;
				
				case "deleteTarget":
					this.deleteTarget(req, res, out);
					break;
				
				case "listTaskByType":
					this.listTaskByType(req, res, out);
					break;
				
				case "deleteTask":
					this.deleteTask(req, res, out);
					break;
				
				case "updateTask":
					this.updateTask(req, res, out);
					break;
				
				case "addTask":
					this.addTask(req, res, out);
					break;
					
				case "addPlanDetail":
					this.addPlanDetail(req, res, out);
					break;
					
				case "listPlanDetail":
					this.listPlanDetail(req, res, out);
					break;					
				
				
				case "deletePlanDetail":
					this.deletePlanDetail(req, res, out);
					break;
				
					
				case "modifyPlanDetail":
					this.modifyPlanDetail(req, res, out);
					break;
					
				
				case "modifyPlan":
					this.modifyPlan(req, res, out);
					break;
					
				case "listPlanByPage":
					this.listPlanByPage(req, res, out);
					break;
				
				case "getTotalRecord":
					this.getTotalRecord(req, res, out);
					break;
				
				case "getPlanDetailByPlanID":
					this.getPlanDetailByPlanID(req, res, out);
					break;
					
				case "getTargetDetailData":
					this.getTargetDetailData(req, res, out);
					break;
				
				case "deleteTargetDetail":
					this.deleteTargetDetail(req, res, out);
					break;
					
				case "updateTargetDetail":
					this.updateTargetDetail(req, res, out);
					break;
				
				case "addTargetDetail":
					this.addTargetDetail(req, res, out);
					break;
				
				case "listSearchData":
					this.listSearchData(req, res, out);
					break;
					
				default:
					break;
			}
		} else {
			out.print(-1);
		}
	}
	public void destory() {
		
	}
	
	/**
	 * 
	 * @author xiaoD
	 * Description: 列出目标记录
	 */
	private void listTargetByType(HttpServletRequest req, HttpServletResponse res, PrintWriter out) {
		String period = req.getParameter("period");
		ManageTarget mt = new ManageTarget();
		String result = mt.listTargetByType(period);
		out.print(result+"#-");
	}
	
	
	/**
	 * 
	 * @author xiaoD
	 * Description: 得到目标细节
	 */
	private void listTargetDetail(HttpServletRequest req, HttpServletResponse res, PrintWriter out) {
		String targetID = req.getParameter("targetID");
		int _targetID = Integer.parseInt(targetID);
		ManageTargetDetail mtd = new ManageTargetDetail();
		String result = mtd.getDetail(_targetID);
		out.print(result+"#-");
	}
	
	/**
	 * 
	 * @author xiaoD
	 * Description: 修改目标
	 */
	private void updateTarget(HttpServletRequest req, HttpServletResponse res, PrintWriter out) {
		String recordID = req.getParameter("recordID");
		int _recordID = Integer.parseInt(recordID);
		String description = req.getParameter("description");
		ManageTarget mt = new ManageTarget();
		int result = mt.updateTarget(_recordID, description);
		out.print(result);
	}
	
	/**
	 * 
	 * @author xiaoD
	 * Description: 新增目标
	 */
	private void addTarget(HttpServletRequest req, HttpServletResponse res, PrintWriter out) {
		ManageTarget mt = new ManageTarget();
		String period = req.getParameter("period");
		String description = req.getParameter("description");
		int targetID = mt.addTarget(description, period, new java.sql.Date(new Date().getTime()));
		out.print(targetID);
	}
	
	/**
	 * 
	 * @author xiaoD
	 * Description: 删除目标记录
	 */
	private void deleteTarget(HttpServletRequest req, HttpServletResponse res, PrintWriter out) {
		String recordID = req.getParameter("recordID");
		int _recordID = Integer.parseInt(recordID);
		ManageTarget mt = new ManageTarget();
		int result = mt.deleteTarget(_recordID);
		out.print(result);
	}
	
	
	/**
	 * 
	 * @author xiaoD
	 * Description:列出任务
	 */
	private void listTaskByType(HttpServletRequest req, HttpServletResponse res, PrintWriter out) {
		String type = req.getParameter("type");
		ManageTask mt = new ManageTask();
		String result = mt.listTaskByType(type);
		out.print(result+"#-");
	}
	
	
	/**
	 * 
	 * @author xiaoD
	 * Description: 删除任务
	 */
	private void deleteTask(HttpServletRequest req, HttpServletResponse res, PrintWriter out) {
		String taskID = req.getParameter("taskID");
		int _taskID = Integer.parseInt(taskID);
		ManageTask mt = new ManageTask();
		int result = mt.deleteTask(_taskID);
		out.print(result);
	}
	
	
	/**
	 * 
	 * @author xiaoD
	 * Description: 修改
	 */
	private void updateTask(HttpServletRequest req, HttpServletResponse res, PrintWriter out) {
		String taskID = req.getParameter("taskID");
		int _taskID = Integer.parseInt(taskID);
		String description = req.getParameter("description");
		ManageTask mt = new ManageTask();
		int result = mt.updateTaskDescription(_taskID, description);
		out.print(result);
	}
	
	/**
	 * 
	 * @author xiaoD
	 * Description: 新增任务
	 */
	private void addTask(HttpServletRequest req, HttpServletResponse res, PrintWriter out) {
		ManageTask mt = new ManageTask();
		String type = req.getParameter("type");
		String description = req.getParameter("description");
		int taskID = mt.addTask(type, description, new java.sql.Date(new Date().getTime()), 0);
		out.print(taskID);
	}
	/**
	 * 
	 * @author xiaoD
	 * Description: 获取计划
	 */
	private void listPlanDetail(HttpServletRequest req, HttpServletResponse res, PrintWriter out) {
		ManagePlan mp = new ManagePlan();
		int planID = mp.getLastPlanID();
		if(planID == -1) {
			out.print("[]#-");
		} else {
			ManagePlanDetail mpd = new ManagePlanDetail();
			String result = mpd.getPlanDetail(planID);
			out.print(result+"#-");
		}
	}
	
	/**
	 * 
	 * @author xiaoD
	 * Description: 增加计划细节
	 */
	private void addPlanDetail(HttpServletRequest req, HttpServletResponse res, PrintWriter out) {
		String description = req.getParameter("description");
		ManagePlan mp = new ManagePlan();
		int planID = mp.getLastPlanID();
		if(planID == -1) {
			planID = mp.addPlan(new java.sql.Date(new Date().getTime()), 0, "总结");
		} 
		ManagePlanDetail mpd = new ManagePlanDetail();
		int detailID = mpd.addPlanDetail(planID, description, new java.sql.Date(new Date().getTime()));
		out.print(detailID);
	}
	
	
	/**
	 * 
	 * @author xiaoD
	 * Description: 删除计划细节
	 */
	private void deletePlanDetail(HttpServletRequest req, HttpServletResponse res, PrintWriter out) {
		String detailID = req.getParameter("detailID");
		int _detailID = Integer.parseInt(detailID);
		ManagePlanDetail mpd = new ManagePlanDetail();
		int result = mpd.deleteTargetDetail(_detailID);
		out.print(result);
	}
	
	
	/**
	 * 
	 * @author xiaoD
	 * Description: 修改计划细节
	 */
	private void modifyPlanDetail(HttpServletRequest req, HttpServletResponse res, PrintWriter out) {
		String detailID = req.getParameter("detailID");
		int _detailID = Integer.parseInt(detailID);
		String description = req.getParameter("description");
		ManagePlanDetail mp = new ManagePlanDetail();
		int result = mp.updatePlanDetail(_detailID, description);
		out.print(result);
	}
	
	
	/**
	 * 
	 * @author xiaoD
	 * Description: 修改计划
	 */
	private void modifyPlan(HttpServletRequest req, HttpServletResponse res, PrintWriter out) {
		String score = req.getParameter("score");
		float _score = Float.parseFloat(score);
		String summary = req.getParameter("summary");
		ManagePlan mp = new ManagePlan();
		int planID = mp.getLastPlanID();
		int result = mp.updatePlan(planID, _score, summary);
	}
	
	/**
	 * 
	 * @author xiaoD
	 * Description: 获取总页数
	 */
	private void getTotalRecord(HttpServletRequest req, HttpServletResponse res, PrintWriter out) {
		String searchValue = req.getParameter("searchValue");
		ManagePlan mp = new ManagePlan();
		int totalRecord = mp.getTotalPage(searchValue);
		out.print(totalRecord);
	}
	
	/**
	 * 
	 * @author xiaoD
	 * Description: 列出所有计划
	 */
	private void listPlanByPage(HttpServletRequest req, HttpServletResponse res, PrintWriter out) {
		String page = req.getParameter("page");
		int _page = Integer.parseInt(page);
		int startID = (_page - 1) * this.pageSize;
		ManagePlan mp = new ManagePlan();
		String result = mp.listPlanByPage(startID, this.pageSize);
		out.print(result+"#-");
	}
	
	/**
	 * 
	 * @author xiaoD
	 * Description: 根据计划ID获取
	 */
	private void getPlanDetailByPlanID(HttpServletRequest req, HttpServletResponse res, PrintWriter out) {
		String planID = req.getParameter("planID");
		int _planID = Integer.parseInt(planID);
		ManagePlanDetail mpd = new ManagePlanDetail();
		String result = mpd.getPlanDetail(_planID);
		out.print(result+"#-");
	}
	
	
	/**
	 * 
	 * @author xiaoD
	 * Description: 根据目标targetID获取
	 */
	private void getTargetDetailData(HttpServletRequest req, HttpServletResponse res, PrintWriter out) {
		String targetID = req.getParameter("targetID");
		int _targetID = Integer.parseInt(targetID);
		ManageTargetDetail mtd = new ManageTargetDetail();
		String result = mtd.getDetail(_targetID);
		out.print(result+"#-");
	}
	
	/**
	 * 
	 * @author xiaoD
	 * Description: 删除目标记录
	 */
	private void deleteTargetDetail(HttpServletRequest req, HttpServletResponse res, PrintWriter out) {
		String recordID = req.getParameter("recordID");
		int _recordID = Integer.parseInt(recordID);
		ManageTargetDetail mtd = new ManageTargetDetail();
		int result = mtd.deleteTargetDetail(_recordID);
		out.print(result);
	}
	
	/**
	 * 
	 * @author xiaoD
	 * Description: 修改目标细节
	 */
	private void updateTargetDetail(HttpServletRequest req, HttpServletResponse res, PrintWriter out) {
		String recordID = req.getParameter("recordID");
		int _recordID = Integer.parseInt(recordID);
		String description = req.getParameter("description");
		ManageTargetDetail mtd = new ManageTargetDetail();
		int result = mtd.updateTargetDetail(_recordID, description);
		out.print(result);
	}
	
	
	/**
	 * 
	 * @author xiaoD
	 * Description: 增加目标细节
	 */
	private void addTargetDetail(HttpServletRequest req, HttpServletResponse res, PrintWriter out) {
		ManageTargetDetail  mtd = new ManageTargetDetail(); 
		String description = req.getParameter("description");
		String targetID = req.getParameter("targetID");
		int _targetID = Integer.parseInt(targetID);
		System.out.println(_targetID);
		int result = mtd.addTargetDetail(_targetID, description, new java.sql.Date(new Date().getTime()));
		out.print(result);
	}
	
	
	/**
	 * 
	 * @author xiaoD
	 * Description: 获取搜索内容
	 */
	private void listSearchData(HttpServletRequest req, HttpServletResponse res, PrintWriter out) {
		String page = req.getParameter("page");
		int _page = Integer.parseInt(page);
		int startID = (_page - 1) * this.pageSize;
		ManagePlan mp = new ManagePlan();
		String searchValue = req.getParameter("searchValue");
		String result = mp.listSearchData(startID, searchValue, this.pageSize);
	}
}

