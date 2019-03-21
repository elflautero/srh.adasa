package dao;

import org.hibernate.Session;

import entidades.HibernateUtil;
import entidades.Inter;


public class interDao {
	
	public void salvaInterferencia (Inter interferencia) {
		
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		s.save(interferencia);
		s.getTransaction().commit();
		s.close();
		
	}

}
