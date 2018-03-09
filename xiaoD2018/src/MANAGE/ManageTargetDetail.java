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

import VO.Target;
import VO.TargetDetail;

/**
 * @author xiaoD
 * Description: 
 */
public class ManageTargetDetail {
	private SessionFactory sfactory = new Configuration().configure().buildSessionFactory();
	
	/**
	 * 
	 * @author xiaoD
	 * Description:获取细节信息
	 */
	public String getDetail(int targetID) {
		Session session = sfactory.openSession();
      Transaction tx = null;
      String result = "";
      try{
         tx = session.beginTransaction();
         String hql = "FROM TargetDetail WHERE targetID = :targetID";
         Query query = session.createQuery(hql);
         query.setParameter("targetID", targetID);
         List targetDetails = query.list();
         result = "[";
         for (Iterator iterator = targetDetails.iterator(); iterator.hasNext();){
            TargetDetail targetDetail = (TargetDetail) iterator.next();
            result += "{detailID:'" + targetDetail.getDetailID() + "',";
   			result += "description:'" + targetDetail.getDescription() + "'},";
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
	public Integer addTargetDetail(int targetID, String description, Date postTime){
		Session session = this.sfactory.openSession();
   	Transaction tx = null;
   	Integer detailID = null;
   	try {
   		tx = session.beginTransaction();
      	TargetDetail targetDetail = new TargetDetail(targetID, description, postTime);
      	detailID = (Integer) session.save(targetDetail);
      	session.getTransaction().commit();
   	} catch(HibernateException e) {
   		if (tx!=null) 
   			tx.rollback();
   		detailID = -1;
         e.printStackTrace(); 
   	}finally {
         session.close(); 
         this.sfactory.close();
      }
      return detailID;
   }
	
	
	/**
	 * 
	 * @author xiaoD
	 * Description: 修改
	 */
	public int updateTargetDetail(int detailID, String description) {
		Session session = this.sfactory.openSession();
   	Transaction tx = null;
   	int result = 0;
   	try{
         tx = session.beginTransaction();
         TargetDetail targetDetail = (TargetDetail) session.get(TargetDetail.class, detailID); 
         targetDetail.setDescription(description);
         session.update(targetDetail);
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
         TargetDetail targetDetail = (TargetDetail) session.get(TargetDetail.class, detailID); 
         session.delete(targetDetail); 
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
