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

import VO.Plan;
import VO.Task;

/**
 * @author xiaoD
 * Description: 
 */
public class ManageTask {
	private SessionFactory sfactory = new Configuration().configure().buildSessionFactory();
	
	/**
	 * 
	 * @author xiaoD 
	 * Description: 根据需要列出所有结果
	 */
	public String listTaskByType(String type){
      Session session = sfactory.openSession();
      Transaction tx = null;
      String result = "";
      try{
         tx = session.beginTransaction();
         String hql = "FROM Task WHERE type = :type";
         Query query = session.createQuery(hql);
         query.setParameter("type", type);
         List tasks = query.list();
         result = "[";
         for (Iterator iterator = tasks.iterator(); iterator.hasNext();){
            Task task = (Task) iterator.next(); 
            result += "{taskID:" + task.getTaskID() + ",";
            result += "description:'" + task.getDescription() + "'},";
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
	public Integer addTask(String type, String description, Date postTime, int status){
		Session session = this.sfactory.openSession();
   	Transaction tx = null;
   	Integer taskID = null;
   	try {
   		tx = session.beginTransaction();
   		Task task = new Task(type, description, postTime, status);
   		taskID = (Integer) session.save(task);
      	session.getTransaction().commit();
   	} catch(HibernateException e) {
   		if (tx!=null) 
   			tx.rollback();
   		taskID = -1;
         e.printStackTrace(); 
   	}finally {
         session.close(); 
         this.sfactory.close();
      }
      return taskID;
   }
	
	
	/**
	 * 
	 * @author xiaoD
	 * Description: 修改描述
	 */
	public int updateTaskDescription(int taskID, String description) {
		Session session = this.sfactory.openSession();
   	Transaction tx = null;
   	int result = 0;
   	try{
   		tx = session.beginTransaction();
         Task task = (Task) session.get(Task.class, taskID); 
         task.setDescription(description);
         session.update(task);
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
	public int deleteTask(int taskID) {
		int result = 0;
		Session session = sfactory.openSession();
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         Task task = (Task) session.get(VO.Task.class, taskID); 
         session.delete(task); 
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
