package MANAGE;

import java.sql.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import VO.PlanDetail;
import VO.TargetDetail;
import VO.Task;

/**
 * @author xiaoD
 * Description: 
 */
public class ManagePlanDetail {
	private SessionFactory sfactory = new Configuration().configure().buildSessionFactory();
	
	public String getPlanDetail(int planID) {
		Session session = sfactory.openSession();
      Transaction tx = null;
      String result = "";
      try{
         tx = session.beginTransaction();
         String hql = "FROM PlanDetail WHERE planID = :planID";
         Query query = session.createQuery(hql);
         query.setParameter("planID", planID);
         List planDetails = query.list();
         result = "[";
         for (Iterator iterator = planDetails.iterator(); iterator.hasNext();){
            PlanDetail planDetail = (PlanDetail) iterator.next(); 
            result += "{detailID:" + planDetail.getDetailID() + ",";
            result += "description:'" + planDetail.getDescription() + "'},";
         }
         result += "]";
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) 
         	tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
      return result;
	}
	
	/**
	 * 
	 * @author xiaoD
	 * Description: 增加目标细节
	 */
	public Integer addPlanDetail(int planID, String description, Date postTime){
		Session session = this.sfactory.openSession();
   	Transaction tx = null;
   	Integer detailID = null;
   	try {
   		tx = session.beginTransaction();
      	PlanDetail planDetail = new PlanDetail(planID, description, postTime);
      	planID = (Integer) session.save(planDetail);
      	session.getTransaction().commit();
   	} catch(HibernateException e) {
   		if (tx!=null) 
   			tx.rollback();
   		planID = -1;
         e.printStackTrace(); 
   	}finally {
         session.close(); 
         this.sfactory.close();
      }
      return planID;
   }
	
	
	/**
	 * 
	 * @author xiaoD
	 * Description: 修改
	 */
	public int updatePlanDetail(int detailID, String description) {
		Session session = this.sfactory.openSession();
   	Transaction tx = null;
   	int result = 0;
   	try{
         tx = session.beginTransaction();
         PlanDetail planDetail = (PlanDetail) session.get(PlanDetail.class, detailID); 
         planDetail.setDescription(description);
         session.update(planDetail);
         tx.commit();
         result = 1;
      }catch (HibernateException e) {
         if (tx!=null) 
         	tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
   	return result;
	}
	
	/**
	 * 
	 * @author xiaoD
	 * Description: 删除
	 */
	public int deleteTargetDetail(int detailID) {
		int result = 0;
		Session session = sfactory.openSession();
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         PlanDetail planDetail = (PlanDetail) session.get(PlanDetail.class, detailID); 
         session.delete(planDetail); 
         tx.commit();
         result = 1;
      }catch (HibernateException e) {
         if (tx!=null) 
         	tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
		return result;
	}
}
