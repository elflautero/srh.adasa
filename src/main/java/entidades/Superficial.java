package entidades;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Superficial implements Serializable{

	private static final long serialVersionUID = -2898246100522194510L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name="sup_ID")
	private int sup_ID;
	
	//-- OneToOne superficial e interferência --//
			@OneToOne
			@JoinColumn (name = "intSupFK") // mudar o nome para sup_Inter_FK
			private Interferencia supInterFK;
	
	@Column (name="sup_Captacao", columnDefinition="varchar(15)")
	private String supCaptacao; // gravidade, bombeamento, outro
	
	@Column (name="sup_Local", columnDefinition="varchar(15)")
	private String supLocal; //-- () canal () rio () reservatório () lago natural () nascente
	
	@Column (name="sup_Bomba", columnDefinition="varchar(30)")
	private String supBomba; // marca
	
	@Column (name="sup_Potencia", columnDefinition="varchar(10)")
	private String supPotencia; // em cv - cavalos
	
	@Column (name="sup_Area", columnDefinition="varchar(10)")
	private String supArea; /// em ha - hectares
	
	@Column (name="sup_Data_Operacao")
	private LocalDate supDataOperacao;
	
	@Column (name="sup_Caesb", columnDefinition="varchar(3)")
	private String supCaesb;  // tem caesb () sim () não
	
	@Column (name="sup_Tempo", columnDefinition="varchar(4)")
	private String supTempo;  // tempo de captação (h/dia)
	
	public Superficial (){
		
	}
	
	
	//-- getters and setters --//

	public LocalDate getSupDataOperacao() {
		return supDataOperacao;
	}


	public void setSupDataOperacao(LocalDate supDataOperacao) {
		this.supDataOperacao = supDataOperacao;
	}


	public int getSup_ID() {
		return sup_ID;
	}


	public void setSup_ID(int sup_ID) {
		this.sup_ID = sup_ID;
	}


	public Interferencia getSupInterFK() {
		return supInterFK;
	}


	public void setSupInterFK(Interferencia supInterFK) {
		this.supInterFK = supInterFK;
	}


	public String getSupCaptacao() {
		return supCaptacao;
	}


	public void setSupCaptacao(String supCaptacao) {
		this.supCaptacao = supCaptacao;
	}


	public String getSupLocal() {
		return supLocal;
	}


	public void setSupLocal(String supLocal) {
		this.supLocal = supLocal;
	}


	public String getSupBomba() {
		return supBomba;
	}


	public void setSupBomba(String supBomba) {
		this.supBomba = supBomba;
	}


	public String getSupPotencia() {
		return supPotencia;
	}


	public void setSupPotencia(String supPotencia) {
		this.supPotencia = supPotencia;
	}


	public String getSupArea() {
		return supArea;
	}


	public void setSupArea(String supArea) {
		this.supArea = supArea;
	}


	public String getSupCaesb() {
		return supCaesb;
	}


	public void setSupCaesb(String supCaesb) {
		this.supCaesb = supCaesb;
	}


	public String getSupTempo() {
		return supTempo;
	}


	public void setSupTempo(String supTempo) {
		this.supTempo = supTempo;
	}
	
	
	
	
	
}
