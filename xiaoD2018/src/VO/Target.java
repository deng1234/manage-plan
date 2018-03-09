package VO;

import java.sql.Date;

/**
 * @author xiaoD
 * @date 2018年3月3日 下午3:00:45
 * Description: 
 */
public class Target {
	private int targetID;
	private String description;
	private String period;
	private Date postTime;
	public Target() {
		
	}
	public Target(String description, String period, Date postTime) {
		this.description = description;
		this.period = period;
		this.postTime = postTime;
	}
	public int getTargetID() {
		return targetID;
	}
	public void setTargetID(int targetID) {
		this.targetID = targetID;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public Date getPostTime() {
		return postTime;
	}
	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}
}
