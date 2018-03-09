package VO;

import java.sql.Date;

/**
 * @author xiaoD
 * @date 2018年3月3日 下午6:12:42
 * Description: 
 */
public class PlanDetail {
	private int detailID;
	private int planID;
	private String description;
	private Date postTime;
	public PlanDetail() {
		
	}
	public PlanDetail(int planID, String description, Date postTime) {
		this.planID = planID;
		this.description = description;
		this.postTime = postTime;
	}
	public int getDetailID() {
		return detailID;
	}
	public void setDetailID(int detailID) {
		this.detailID = detailID;
	}
	public int getPlanID() {
		return planID;
	}
	public void setPlanID(int planID) {
		this.planID = planID;
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
}
