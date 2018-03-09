package VO;

import java.sql.Date;

/**
 * @author xiaoD
 * @date 2018年3月3日 下午6:14:56
 * Description: 
 */
public class Task {
	private int taskID;
	private String type;
	private String description;
	private Date postTime;
	private int status;
	public Task() {
		
	}
	public Task(String type, String description, Date postTime, int status) {
		this.type = type;
		this.description = description;
		this.postTime = postTime;
		this.status = status;
	}
	public int getTaskID() {
		return taskID;
	}
	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getPostTime() {
		return postTime;
	}
	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
