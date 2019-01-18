package entidades;

import java.io.Serializable;
import java.time.LocalDate;

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
public class Subterranea implements Serializable {
	
	private static final long serialVersionUID = 8669422759075749625L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name="sub_ID")
	private int subID;
	
		//-- OneToOne subterrâneo e interferência --//
		@OneToOne 
		@JoinColumn (name = "sup_Interferencia_FK")  // mudar o nome para sub_Inter_FK
		private Interferencia subInterFK;
	
	@Column (name="sub_Tipo_Captacao", columnDefinition="varchar (10)")
	private String subTipoCaptacao;

	@Column (name="sub_Caesb", columnDefinition="varchar(3)")
	private String subCaesb;  // tem caesb () sim () não
	
	
	@Column (name="sub_Estatico", columnDefinition="varchar(5)")
	private String subEstatico;  // em metros
	
	@Column (name="sub_Dinamico", columnDefinition="varchar(5)")
	private String subDinamico;  // em metros
	
	@Column (name="sub_Vazao",columnDefinition="varchar(5)")
	private String subVazao;  // em l/h - litros por hora
	
	@Column (name="sub_Profundidade",columnDefinition="varchar(5)")
	private String subProfundidade;  // em metros
	
	@Basic
	@Column (name="sub_Data_Operacao")
	private java.sql.Date subDataOperacao;
	
	@ManyToOne (fetch = FetchType.EAGER) 
	@JoinColumn (name = "sub_Subsistema_FK")
	private SubSistema subSubSistemaFK;
	
	
	public Subterranea () {
		
	}


	//-- getters and setters --//
	
	

	public SubSistema getSubSubSistemaFK() {
		return subSubSistemaFK;
	}

	public Interferencia getSubInterFK() {
		return subInterFK;
	}


	public void setSubInterFK(Interferencia subInterFK) {
		this.subInterFK = subInterFK;
	}


	public java.sql.Date getSubDataOperacao() {
		return subDataOperacao;
	}


	public void setSubDataOperacao(java.sql.Date subDataOperacao) {
		this.subDataOperacao = subDataOperacao;
	}


	public void setSubSubSistemaFK(SubSistema subSubSistemaFK) {
		this.subSubSistemaFK = subSubSistemaFK;
	}


	public int getSubID() {
		return subID;
	}

	public void setSubID(int subID) {
		this.subID = subID;
	}

	public Interferencia getSubInterSubFK() {
		return subInterFK;
	}

	public void setSubInterSubFK(Interferencia subInterSub) {
		this.subInterFK = subInterSub;
	}

	public String getSubTipoCaptacao() {
		return subTipoCaptacao;
	}

	public void setSubTipoCaptacao(String subTipoCaptacao) {
		this.subTipoCaptacao = subTipoCaptacao;
	}

	public String getSubCaesb() {
		return subCaesb;
	}

	public void setSubCaesb(String subCaesb) {
		this.subCaesb = subCaesb;
	}

	public String getSubEstatico() {
		return subEstatico;
	}

	public void setSubEstatico(String subEstatico) {
		this.subEstatico = subEstatico;
	}

	public String getSubDinamico() {
		return subDinamico;
	}

	public void setSubDinamico(String subDinamico) {
		this.subDinamico = subDinamico;
	}

	public String getSubVazao() {
		return subVazao;
	}

	public void setSubVazao(String subVazao) {
		this.subVazao = subVazao;
	}

	public String getSubProfundidade() {
		return subProfundidade;
	}

	public void setSubProfundidade(String subProfundidade) {
		this.subProfundidade = subProfundidade;
	}

	

	
	

}
