package controladores.principal;

import java.sql.Date;
import java.util.Properties;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;

import dao.EventDao;
import entidades.Event;
import entidades.HibernateUtil;

public class interMain {

	public static void main(String[] args) {
		
		EventDao evDao = new EventDao();
		
		Event ev = new Event();
		
			// funcionado com a mudação  do  dialeto mysql MySQL56InnoDBSpatialDialect  *mySQL 56 *
		
	        Point p = new GeometryFactory().createPoint(new Coordinate(26, 62));
	        p.setSRID(4674);
	        
	        ev.setLocation(p);
	        
	        ev.setData(Date.valueOf("2015-12-5"));
	        
	       // HibernateUtil.newUserName = "somde078_srh";
	       // HibernateUtil
	        
	      
	        
	        
	        
	        

	        evDao.saveEvent(ev);
	        
	        //Usuario atual
	      // System.out.println(System.getProperty("user.name"));
	       
	       // Descobrir usuario
	    //   System.out.println(System.getProperty("user.name"));
	       
	       // descobrir porpriedades do sistema
	     //  Properties pro = System.getProperties();
	     //  pro.list(System.out);
	       
	       
		/*
		
		GeometryFactory geoFac = new GeometryFactory();
		
		
		// Add road to event
				Coordinate[] coordinates = new Coordinate[5];
				coordinates[0] = new Coordinate(1, 2); // Starting point
				coordinates[1] = new Coordinate(2, 20);
				coordinates[2] = new Coordinate(20, 36);
				coordinates[3] = new Coordinate(36, 100);
				coordinates[4] = new Coordinate(1, 2); // Ending point
				LinearRing linear = new GeometryFactory().createLinearRing(coordinates);
				Polygon poly = new Polygon(linear, null, geoFac);
				
				//ev.setRoad(poly);
			
				ev.setLocation(new GeometryFactory().createPoint(new Coordinate(10, 5)));
			
				
				evDao.saveEvent(ev);*/
		
		
		
		
		
		

				/*
				Point point = geoFac.createPoint(new Coordinate(
						Double.parseDouble("-47"),
						Double.parseDouble("-16"
						)));
				
				//point.setSRID(4326); //point.setSRID(4674);
				
				ev.setLocation(point);*/
				
		
		
		
		/*

		Inter inter = new Inter();
		
		inter.setInterLogadouro("Quadra 10");
	
		GeometryFactory geoFac = new GeometryFactory();
		
		Point point = geoFac.createPoint(new Coordinate(
				Double.parseDouble("-47"),
				Double.parseDouble("-16"
				)));
		
		//point.setSRID(4326); //point.setSRID(4674);
		
		inter.setLocation(point);
		
		interDao i = new  interDao();
		
		i.salvaInterferencia(inter);
		
		*/
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	/* // org.locationtech.jts.geom.Coordinate;
	   // funcionado com a mudação  do  dialeto mysql MySQL56InnoDBSpatialDialect  *mySQL 56 *
		GeometryFactory factory = new GeometryFactory();
	
		ev.setLocation(factory.createPoint(new Coordinate(26, 62)));
		ev.getLocation().setSRID(4674);
		
		*/
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
}
