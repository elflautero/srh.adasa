package dao;

import org.hibernate.Session;

import entidades.Event;
import entidades.HibernateUtil;


public class EventDao {
	
		
	public void saveEvent (Event ev ) {
			
			Session s = HibernateUtil.getSessionFactory().openSession();
			s.beginTransaction();
			s.save(ev);
			s.getTransaction().commit();
			s.close();
			
		}

}
