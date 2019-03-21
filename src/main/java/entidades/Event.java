package entidades;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


import org.locationtech.jts.geom.Point;


@Entity(name="Event")
public class Event implements java.io.Serializable {

	
	private static final long serialVersionUID = -3070690918550913643L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	//@Column(columnDefinition = "geometry(Point,4326)")
	
	@Basic
	@Column (name="event_data")
	private java.sql.Date data;
	
	public java.sql.Date getData() {
		return data;
	}

	public void setData(java.sql.Date data) {
		this.data = data;
	}

	@Column(columnDefinition = "Geometry")
	private Point location;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	
}