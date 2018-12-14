package entidades;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Endereco implements Serializable{

	private static final long serialVersionUID = -8620638555874838035L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name="end_ID")  // @Column (name="dem_documento", columnDefinition="varchar(80)")
	private int endID; 
	
	@Column (name="end_Logadouro", columnDefinition="varchar(95)")
	private String endLogadouro;
	
	@Column (name="end_CEP", columnDefinition="varchar(20)")
	private String endCEP;
	
	@Column (name="end_Cidade", columnDefinition="varchar(20)")
	private String endCidade;
	
	@Column (name="end_UF", columnDefinition="varchar(2)")
	private String endUF;
	
	@Column (name="end_DD_Latitude")
	private Double endDDLatitude;
	
	@Column (name="end_DD_Longitude")
	private Double endDDLongitude;
	
	@Column (name="end_Atualizacao")
	private LocalDateTime endAtualizacao;
	
	@ManyToOne (fetch = FetchType.EAGER) 
	@JoinColumn (name="end_RA_FK")
	private RA endRAFK;
	
	//-- Lista de enderecos vinculados --//
	@OneToMany (mappedBy = "demEnderecoFK", cascade = CascadeType.MERGE,
			fetch = FetchType.EAGER, targetEntity = Demanda.class)
	@Fetch(FetchMode.SUBSELECT) 
	private List<Demanda> demandas = new ArrayList<Demanda>();
	
	
		//-- Lista de interferencias vinculadas --//
		@OneToMany (mappedBy = "interEnderecoFK", cascade = CascadeType.MERGE,
				fetch = FetchType.LAZY, targetEntity = Interferencia.class)
		@Fetch(FetchMode.SUBSELECT) 
		private List<Interferencia> interferencias = new ArrayList<Interferencia>();
		/*
				//-- Lista de usuários vinculados --//
				@OneToMany (mappedBy = "usEndCodigoFK", cascade = CascadeType.MERGE,
						fetch = FetchType.LAZY, targetEntity = Usuario.class)
				@Fetch(FetchMode.SUBSELECT)
				private List<Usuario> usuarios = new ArrayList<Usuario>();
		
						//-- Lista de fiscais vinculados --//
						@OneToMany (mappedBy = "fis_End_Codigo", cascade = CascadeType.MERGE,
								fetch = FetchType.LAZY, targetEntity = Fiscal.class)
						@Fetch(FetchMode.SUBSELECT) 
						private List<Fiscal> fiscais = new ArrayList<Fiscal>();
				
								//-- Lista de vistorias vinculados --//
								@OneToMany (mappedBy = "visEndCodigoFK", cascade = CascadeType.MERGE,
										fetch = FetchType.LAZY, targetEntity = Vistoria.class)
								@Fetch(FetchMode.SUBSELECT) 
								private List<Vistoria> vistorias = new ArrayList<Vistoria>();
		
				*/
	
	//-- Construtor padrão -- //
	public Endereco () {
		
	}

	public int getEndID() {
		return endID;
	}


	public void setEndID(int endID) {
		this.endID = endID;
	}


	public String getEndLogadouro() {
		return endLogadouro;
	}


	public void setEndLogadouro(String endLogadouro) {
		this.endLogadouro = endLogadouro;
	}


	public RA getEndRAFK() {
		return endRAFK;
	}

	public void setEndRAFK(RA endRAFK) {
		this.endRAFK = endRAFK;
	}


	public String getEndCEP() {
		return endCEP;
	}


	public void setEndCEP(String endCEP) {
		this.endCEP = endCEP;
	}


	public String getEndCidade() {
		return endCidade;
	}


	public void setEndCidade(String endCidade) {
		this.endCidade = endCidade;
	}


	public String getEndUF() {
		return endUF;
	}


	public void setEndUF(String endUF) {
		this.endUF = endUF;
	}


	public Double getEndDDLatitude() {
		return endDDLatitude;
	}


	public void setEndDDLatitude(Double endLatitude) {
		this.endDDLatitude = endLatitude;
	}


	public Double getEndDDLongitude() {
		return endDDLongitude;
	}


	public void setEndDDLongitude(Double endLongitude) {
		this.endDDLongitude = endLongitude;
	}


	public List<Demanda> getDemandas() {
		return demandas;
	}


	public void setDemandas(List<Demanda> demandas) {
		this.demandas = demandas;
	}

	public LocalDateTime getEndAtualizacao() {
		return endAtualizacao;
	}

	public void setEndAtualizacao(LocalDateTime endAtualizacao) {
		this.endAtualizacao = endAtualizacao;
	}
	
	
	/*
	//-- Construtor --//
	public Endereco (EnderecoTabela endTab) {
		
		this.Cod_Endereco = endTab.getCod_Endereco();
		this.Desc_Endereco = endTab.getDesc_Endereco();
		this.RA_Endereco = endTab.getRA_Endereco();
		this.CEP_Endereco = endTab.getCEP_Endereco();
		this.Cid_Endereco = endTab.getCid_Endereco();
		this.UF_Endereco = endTab.getUF_Endereco();
		
		this.Lat_Endereco = endTab.getLat_Endereco();
		this.Lon_Endereco = endTab.getLon_Endereco();
	}
	
	*/
	
	// -- GETTERS AND SETTERS - //
	
	
	
	
	
	/*
		//-- get e set OneToMany List Interferencia --//
			public List<Interferencia> getListInterferencias() {
				return interferencias;
			}
	
			public void setListInterferencias(List<Interferencia> interferencias) {
				this.interferencias = interferencias;
			}
	
				//-- get e set OneToMany List Usuarios --//
				public List<Usuario> getListUsuarios() {
					return usuarios;
				}
				
				public void setListUsuarios(List<Usuario> usuarios) {
					this.usuarios = usuarios;
				}
				
					//-- get e set OneToMany List Fiscais --//
					public List<Fiscal> getListFiscais() {
						return fiscais;
					}
					
					public void setListFiscais(List<Fiscal> fiscais) {
						this.fiscais = fiscais;
					}
					
						//-- get e set OneToMany List Vistorias --//
						public List<Vistoria> getListVistorias() {
							return vistorias;
						}
						
						public void setListVistorias(List<Vistoria> vistorias) {
							this.vistorias = vistorias;
						}
						*/
				
		
	
	
}