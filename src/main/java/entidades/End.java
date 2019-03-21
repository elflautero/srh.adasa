package entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


public class End {
	
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name="end_ID")
	private int interID;
	
	
	@Column (name="end_Rua", columnDefinition="varchar (250)")
	private String interLogadouro;

	
	

}
