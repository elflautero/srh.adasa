package entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.locationtech.jts.geom.Geometry;


	@Entity
	public class Inter {
	

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name="inter_ID")
	private int interID;
	
	
	@Column (name="inter_Logadouro", columnDefinition="varchar (250)")
	private String interLogadouro;

	
	//private Point location;
	
	// Usado no SQLServer   
	/*@Column(name="end_Geom")
	private Geometry endGeom;
	*/

	//@Column(columnDefinition = "Geometry")
	private Geometry location;

	public int getInterID() {
		return interID;
	}

	public Geometry getLocation() {
		return location;
	}




	public void setLocation(Geometry location) {
		this.location = location;
	}




	public void setInterID(int interID) {
		this.interID = interID;
	}



	public String getInterLogadouro() {
		return interLogadouro;
	}


	public void setInterLogadouro(String interLogadouro) {
		this.interLogadouro = interLogadouro;
	}
	
	
	
}
