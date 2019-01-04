package entidades;


import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Ato implements Serializable {
	
	
	private static final long serialVersionUID = -804426930461307857L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name="ato_ID")
	private int atoID;
	
		//-- vistoria --//
		@ManyToOne (fetch = FetchType.LAZY) 
		@JoinColumn (name = "ato_Vistoria_FK")
		private Vistoria atoVistoriaFK;
	
	@Column (name="ato_Tipo", columnDefinition="varchar(40)")  // auto de infração de advertência, relatório de vistoria
	private String atoTipo;
	
	@Column (name="ato_Identificacao", columnDefinition="varchar(20)") // 15/2018
	private String atoIdentificacao;
	
	@Column (name="ato_SEI", columnDefinition="varchar(20)") // 3561241
	private String atoSEI;
	
	@Column (name="ato_Caracaterizacao", columnDefinition="varchar(2000)") // texto
	private String atoCaracterizacao;
	
	@Column (name="ato_Data_Fiscalizacao")
	private LocalDate atoDataFiscalizacao;
	
	@Column (name="ato_Data_Criacao")
	private LocalDate atoDataCriacao;
	
	@Column (name="ato_Atualizacao")
	private LocalDateTime atoAtualizacao;
	
	// construtor padrão //

	public Ato () {
		
	}

	public int getAtoID() {
		return atoID;
	}

	public void setAtoID(int atoID) {
		this.atoID = atoID;
	}

	public Vistoria getAtoVistoriaFK() {
		return atoVistoriaFK;
	}

	public void setAtoVistoriaFK(Vistoria atoVistoriaFK) {
		this.atoVistoriaFK = atoVistoriaFK;
	}

	public String getAtoTipo() {
		return atoTipo;
	}

	public void setAtoTipo(String atoTipo) {
		this.atoTipo = atoTipo;
	}

	public String getAtoIdentificacao() {
		return atoIdentificacao;
	}

	public void setAtoIdentificacao(String atoIdentificacao) {
		this.atoIdentificacao = atoIdentificacao;
	}

	public String getAtoSEI() {
		return atoSEI;
	}

	public void setAtoSEI(String atoSEI) {
		this.atoSEI = atoSEI;
	}

	public String getAtoCaracterizacao() {
		return atoCaracterizacao;
	}

	public void setAtoCaracterizacao(String atoCaracterizacao) {
		this.atoCaracterizacao = atoCaracterizacao;
	}

	public LocalDate getAtoDataFiscalizacao() {
		return atoDataFiscalizacao;
	}

	public void setAtoDataFiscalizacao(LocalDate atoDataFiscalizacao) {
		this.atoDataFiscalizacao = atoDataFiscalizacao;
	}

	public LocalDate getAtoDataCriacao() {
		return atoDataCriacao;
	}

	public void setAtoDataCriacao(LocalDate atoDataCriacao) {
		this.atoDataCriacao = atoDataCriacao;
	}

	public LocalDateTime getAtoAtualizacao() {
		return atoAtualizacao;
	}

	public void setAtoAtualizacao(LocalDateTime atoAtualizacao) {
		this.atoAtualizacao = atoAtualizacao;
	}
	
	

}
