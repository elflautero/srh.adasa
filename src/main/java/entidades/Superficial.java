package entidades;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Superficial implements Serializable{

	private static final long serialVersionUID = -2898246100522194510L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name="sup_ID")
	private int sup_ID;
	
	//-- OneToOne superficial e interferência --//
			@OneToOne
			@JoinColumn (name = "sup_Interferencia_FK") // mudar o nome para sup_Inter_FK
			private Interferencia supInterFK;
			
				@ManyToOne (fetch = FetchType.LAZY) 
				@JoinColumn (name = "sup_Forma_Captacao_FK")
				private FormaCaptacao supFormaCaptacaoFK; // Bombeamento Gravidade
				
					@ManyToOne (fetch = FetchType.LAZY) 
					@JoinColumn (name = "sup_Local_Captacao_FK")
					private LocalCaptacao supLocalCaptacaoFK; //-- () canal () rio () reservatório () lago natural () nascente
					
						@ManyToOne (fetch = FetchType.LAZY) 
						@JoinColumn (name = "sup_Metodo_Irrigacao_FK")
						private MetodoIrrigacao supMetodoIrrigacaoFK; //-- () canal () rio () reservatório () lago natural () nascente
						
		
	@Column (name="sup_Marca_Bomba", columnDefinition="varchar(30)")
	private String supMarcaBomba; // marca
	
	@Column (name="sup_Potencia_Bomba", columnDefinition="varchar(10)")
	private String supPotenciaBomba; // em cv - cavalos
	
	@Column (name="sup_Area_Irrigada", columnDefinition="varchar(10)")
	private String supAreaIrrigada;
	
	@Column (name="sup_Area_Contribuicao", columnDefinition="varchar(10)")
	private String supAreaContribuicao;
	
	@Column (name="sup_Area_Propriedade", columnDefinition="varchar(10)")
	private String supAreaPropriedade;
	
	@Basic
	@Column (name="sup_Data_Operacao")
	private java.sql.Date supDataOperacao;
	
	@Column (name="sup_Caesb", columnDefinition="varchar(3)")
	private String supCaesb;  // tem caesb () sim () não
	
	@Column (name="sup_Barramento", columnDefinition="varchar(3)")
	private String supBarramento; 
	
	@Column (name="sup_Corpo_Hidrico", columnDefinition="varchar (50)")
	private String supCorpoHidrico;
	
	
	
	public Superficial (){
		
	}
	
	//-- getters and setters --//

	public int getSup_ID() {
		return sup_ID;
	}

	public void setSup_ID(int sup_ID) {
		this.sup_ID = sup_ID;
	}

	public java.sql.Date getSupDataOperacao() {
		return supDataOperacao;
	}


	public void setSupDataOperacao(java.sql.Date supDataOperacao) {
		this.supDataOperacao = supDataOperacao;
	}

	public Interferencia getSupInterFK() {
		return supInterFK;
	}

	public void setSupInterFK(Interferencia supInterFK) {
		this.supInterFK = supInterFK;
	}

	public String getSupCaesb() {
		return supCaesb;
	}

	public void setSupCaesb(String supCaesb) {
		this.supCaesb = supCaesb;
	}

	

	public FormaCaptacao getSupFormaCaptacaoFK() {
		return supFormaCaptacaoFK;
	}

	public void setSupFormaCaptacaoFK(FormaCaptacao supFormaCaptacaoFK) {
		this.supFormaCaptacaoFK = supFormaCaptacaoFK;
	}

	public LocalCaptacao getSupLocalCaptacaoFK() {
		return supLocalCaptacaoFK;
	}

	public void setSupLocalCaptacaoFK(LocalCaptacao supLocalCaptacaoFK) {
		this.supLocalCaptacaoFK = supLocalCaptacaoFK;
	}

	public String getSupMarcaBomba() {
		return supMarcaBomba;
	}

	public void setSupMarcaBomba(String supMarcaBomba) {
		this.supMarcaBomba = supMarcaBomba;
	}

	public String getSupPotenciaBomba() {
		return supPotenciaBomba;
	}

	public void setSupPotenciaBomba(String supPotenciaBomba) {
		this.supPotenciaBomba = supPotenciaBomba;
	}

	public String getSupAreaIrrigada() {
		return supAreaIrrigada;
	}

	public void setSupAreaIrrigada(String supAreaIrrigada) {
		this.supAreaIrrigada = supAreaIrrigada;
	}

	public String getSupAreaContribuicao() {
		return supAreaContribuicao;
	}

	public void setSupAreaContribuicao(String supAreaContribuicao) {
		this.supAreaContribuicao = supAreaContribuicao;
	}

	public String getSupAreaPropriedade() {
		return supAreaPropriedade;
	}

	public void setSupAreaPropriedade(String supAreaPropriedade) {
		this.supAreaPropriedade = supAreaPropriedade;
	}

	public String getSupBarramento() {
		return supBarramento;
	}

	public void setSupBarramento(String supBarramento) {
		this.supBarramento = supBarramento;
	}

	public MetodoIrrigacao getSupMetodoIrrigacaoFK() {
		return supMetodoIrrigacaoFK;
	}

	public void setSupMetodoIrrigacaoFK(MetodoIrrigacao supMetodoIrrigacaoFK) {
		this.supMetodoIrrigacaoFK = supMetodoIrrigacaoFK;
	}

	public String getSupCorpoHidrico() {
		return supCorpoHidrico;
	}

	public void setSupCorpoHidrico(String supCorpoHidrico) {
		this.supCorpoHidrico = supCorpoHidrico;
	}
	
	
	
	
}
