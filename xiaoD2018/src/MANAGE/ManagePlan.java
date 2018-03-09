package MANAGE;

import java.sql.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import VO.Plan;
import VO.PlanDetail;
import VO.Target;
import VO.Task;

/**
 * @author xiaoD
 * Description: 
 */
public class ManagePlan {
	private SessionFactory sfactory = new Configuration().configure().buildSessionFactory();
	
	/**
	 * 
	 * @author xiaoD
	 * Description: 获取所有计划
	 */
	public String listPlanByPage(int startID, int pageSize) {
		Session session = sfactory.openSession();
      Transaction tx = null;
      String result = "";
      try{
         tx = session.beginTransaction();
         String hql = "FROM Plan";
         Query query = session.createQuery(hql);
         query.setFirstResult(startID);
         List plans = query.list();
         if( pageSize > plans.size() ){
         	pageSize = plans.size();
         }
         result = "[";
         int i = 0;
         for (Iterator iterator = plans.iterator(); i < pageSize; iterator.hasNext()){
            Plan plan = (Plan) iterator.next(); 
            result += "{planID:" + plan.getPlanID() + ",";
            result += "score:" + plan.getScore() + ",";
            result += "summary:'" + plan.getSummary() + "',";
            result += "planTime:'" + plan.getPlanTime() + "'},";
            i++;
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
	 * Description: 获取总记录数量
	 */
	public int getTotalPage(String searchValue) {
		Session session = sfactory.openSession();
      Transaction tx = null;
      int result = 0;
      try{
         tx = session.beginTransaction();
         String hql = "FROM Plan WHERE summary like :summary";
         Query query = session.createQuery(hql);
         query.setParameter("summary", "%"+searchValue+"%");
         result = query.list().size();
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
	 * Description: 获取搜索内容
	 */
	public String listSearchData(int startID, String searchValue, int pageSize) {
		Session session = sfactory.openSession();
      Transaction tx = null;
      String result = "";
      try{
         tx = session.beginTransaction();
         String hql = "FROM Plan WHERE summary like :summary";
         Query query = session.createQuery(hql);
         query.setFirstResult(startID);
         query.setParameter("summary", "%"+searchValue+"%");
         List plans = query.list();
         if( pageSize > plans.size() ){
         	pageSize = plans.size();
         }
         result = "[";
         int i = 0;
         for (Iterator iterator = plans.iterator(); i < pageSize; iterator.hasNext()){
            Plan plan = (Plan) iterator.next(); 
            result += "{planID:" + plan.getPlanID() + ",";
            result += "score:" + plan.getScore() + ",";
            result += "summary:'" + plan.getSummary() + "',";
            result += "planTime:'" + plan.getPlanTime() + "'},";
            i++;
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
	 * Description: 获得最后的planID
	 */
	public int getLastPlanID() {
		int planID = -1;
		Session session = sfactory.openSession();
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         String hql = "FROM Plan WHERE score =0 ORDER BY planID desc LIMIT 1";
         Query query = session.createQuery(hql);
         List plans = query.list();
         for (Iterator iterator = plans.iterator(); iterator.hasNext();){
         	Plan plan = (Plan) iterator.next(); 
         	planID = plan.getPlanID();
         }
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) 
         	tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
		return planID;
	}
	
	/**
	 * 
	 * @author xiaoD
	 * Description:增加目标
	 */
	public Integer addPlan(Date planTime, float score, String summary){
		Session session = this.sfactory.openSession();
   	Transaction tx = null;
   	Integer planID = -1;
   	try {
   		tx = session.beginTransaction();
      	Plan plan = new Plan(planTime, score, summary);
      	planID = (Integer) session.save(plan);
      	session.getTransaction().commit();
   	} catch(HibernateException e) {
   		if (tx!=null) 
   			tx.rollback();
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
	public int updatePlan(int planID, float score, String summary) {
		Session session = this.sfactory.openSession();
   	Transaction tx = null;
   	int result = -1;
   	try{
         tx = session.beginTransaction();
         String hql = "UPDATE Plan set score = :score, summary = :summary "  + 
               "WHERE planID = :planID";
         Query query = session.createQuery(hql);
         query.setParameter("score", score);
         query.setParameter("summary", summary);
         query.setParameter("planID", planID);
         result = query.executeUpdate();
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

}

