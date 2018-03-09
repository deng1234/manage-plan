package VO;

import java.sql.Date;

/**
 * @author xiaoD
 * @date 2018年3月3日 下午6:13:54
 * Description: 
 */
public class TargetDetail {
	private int detailID;
	private int targetID;
	private String description;
	private Date postTime;
	public TargetDetail() {
		
	}
	public TargetDetail(int targetID, String description, Date postTime) {
		this.targetID = targetID;
		this.description = description;
		this.postTime = postTime;
	}
	public int getDetailID() {
		return detailID;
	}
	public void setDetailID(int detailID) {
		this.detailID = detailID;
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
	public Date getPostTime() {
		return postTime;
	}
	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}
	
}
