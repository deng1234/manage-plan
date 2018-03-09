package VO;

import java.sql.Date;

/**
 * @author xiaoD
 * @date 2018年3月3日 下午6:11:27
 * Description: 
 */
public class Plan {
	private int planID;
	private Date planTime;
	private float score;
	private String summary;
	public Plan() {
		
	}
	public Plan(Date planTime, float score, String summary) {
		this.planTime = planTime;
		this.score = score;
		this.summary = summary;
	}
	public int getPlanID() {
		return planID;
	}
	public void setPlanID(int planID) {
		this.planID = planID;
	}
	public Date getPlanTime() {
		return planTime;
	}
	public void setPlanTime(Date planTime) {
		this.planTime = planTime;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
}
