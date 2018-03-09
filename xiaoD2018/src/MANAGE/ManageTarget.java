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
import VO.Task;

/**
 * @author xiaoD
 * Description: 
 */
public class ManageTarget {
	
	private SessionFactory sfactory = new Configuration().configure().buildSessionFactory();
	
	/**
	 * 
	 * @author xiaoD
	 * Description: 列出记录
	 */
	public String listTargetByType(String period) {
      Session session = sfactory.openSession();
      Transaction tx = null;
      String result = "";
      try{
         tx = session.beginTransaction();
         String hql = "FROM Target WHERE period = :period";
         Query query = session.createQuery(hql);
         query.setParameter("period", period);
         List targets = query.list();
         result = "[";
         for (Iterator iterator = targets.iterator(); iterator.hasNext();){
            Target target = (Target) iterator.next();
            result += "{targetID:" + target.getTargetID() + ",";
   			result += "description:'" + target.getDescription() + "'},";
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
	 * Description:增加目标
	 */
	public Integer addTarget(String description, String period, Date postTime){
		Session session = this.sfactory.openSession();
   	Transaction tx = null;
   	Integer targetID = -1;
   	try {
   		tx = session.beginTransaction();
      	Target target = new Target(description, period, postTime);
      	targetID = (Integer) session.save(target);
      	session.getTransaction().commit();
   	} catch(HibernateException e) {
   		if (tx!=null) 
   			tx.rollback();
         e.printStackTrace(); 
   	}finally {
         session.close(); 
         this.sfactory.close();
      }
      return targetID;
   }
	
	
	/**
	 * 
	 * @author xiaoD
	 * Description: 修改
	 */
	public int updateTarget(int targetID, String description) {
		Session session = this.sfactory.openSession();
   	Transaction tx = null;
   	int result = 0;
   	try{
         tx = session.beginTransaction();
         Target target = (Target) session.get(VO.Target.class, targetID); 
         target.setDescription(description);
         session.update(target);
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
	 * Description: 删除目标
	 */
	public int deleteTarget(int targetID) {
		int result = 0;
		Session session = sfactory.openSession();
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         Target target = (Target) session.get(Target.class, targetID); 
         session.delete(target); 
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
