package entidades;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.vividsolutions.jts.geom.Geometry;


@Entity
public class Interferencia implements Serializable {
	
	private static final long serialVersionUID = -1830876095445773286L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name="inter_ID")
	private int interID;
	
	//-- RELACIONAMENTO ENDEREÇO --//
	@ManyToOne  (fetch = FetchType.LAZY) 
	@JoinColumn (name = "inter_Endereco_FK")
	private Endereco interEnderecoFK;
	
	
	//-- RELACIONAMENTO SUBTERRÂNEA --//
			@OneToOne (cascade = CascadeType.ALL, mappedBy = "subInterFK")
			//@Fetch(FetchMode.JOIN)
			//
			private Subterranea intSubFK;
			
			public Subterranea getIntSubFK() {
				return intSubFK;
			}

			public void setIntSubFK(Subterranea intSubFK) {
				this.intSubFK = intSubFK;
			}


			//-- RELACIONAMENTO SUPERFICIAL --//
			@OneToOne (cascade = CascadeType.ALL, mappedBy = "supInterFK")
			//@Fetch(FetchMode.JOIN)
			private Superficial intSupFK;
		
			public Superficial getIntSupFK() {
				return intSupFK;
			}

			public void setIntSupFK(Superficial intSupFK) {
				this.intSupFK = intSupFK;
			}


	@ManyToOne (fetch = FetchType.LAZY) 
	@JoinColumn (name = "inter_Tipo_Interferencia_FK")
	private TipoInterferencia interTipoInterferenciaFK;
	
		@ManyToOne (fetch = FetchType.LAZY) 
		@JoinColumn (name = "inter_Bacia_FK")
		private BaciasHidrograficas interBaciaFK;
	
			@ManyToOne (fetch = FetchType.LAZY) 
			@JoinColumn (name = "inter_UH_FK")
			private UnidadeHidrografica interUHFK;

	@Column (columnDefinition="varchar (50)")
	private String inter_Corpo_Hidrico;
	
	@Column (name="inter_DD_Latitude")
	private Double interDDLatitude;
	
	@Column (name="inter_DD_Longitude")
	private Double interDDLongitude;
	
	@Column(name="inter_Geom")
	private Geometry interGeom;
	
	@Column (name="inter_Logadouro", columnDefinition="varchar (250)")
	private String interLogadouro;
	
	@Basic
	@Column (name="int_Atualizacao")
	private java.sql.Timestamp intAtualizacao;
	

		//CONSTRUTOR PADRÃO
		public Interferencia () {
			
		}

		public int getInterID() {
			return interID;
		}

		public void setInterID(int interID) {
			this.interID = interID;
		}

		public Endereco getInterEnderecoFK() {
			return interEnderecoFK;
		}

		public void setInterEnderecoFK(Endereco interEnderecoFK) {
			this.interEnderecoFK = interEnderecoFK;
		}

		
		public TipoInterferencia getInterTipoInterferenciaFK() {
			return interTipoInterferenciaFK;
		}

		public void setInterTipoInterferenciaFK(TipoInterferencia interTipoInterferencia) {
			this.interTipoInterferenciaFK = interTipoInterferencia;
		}

		public BaciasHidrograficas getInterBaciaFK() {
			return interBaciaFK;
		}

		public void setInterBaciaFK(BaciasHidrograficas interBaciasHidrograficas) {
			this.interBaciaFK = interBaciasHidrograficas;
		}

		public UnidadeHidrografica getInterUHFK() {
			return interUHFK;
		}

		public void setInterUHFK(UnidadeHidrografica interUH) {
			this.interUHFK = interUH;
		}

		public String getInter_Corpo_Hidrico() {
			return inter_Corpo_Hidrico;
		}

		public void setInter_Corpo_Hidrico(String inter_Corpo_Hidrico) {
			this.inter_Corpo_Hidrico = inter_Corpo_Hidrico;
		}

		public Double getInterDDLatitude() {
			return interDDLatitude;
		}

		public void setInterDDLatitude(Double interDDLatitude) {
			this.interDDLatitude = interDDLatitude;
		}

		public Double getInterDDLongitude() {
			return interDDLongitude;
		}

		public void setInterDDLongitude(Double interDDLongitude) {
			this.interDDLongitude = interDDLongitude;
		}

		
		public String getInterLogadouro() {
			return interLogadouro;
		}

		public void setInterLogadouro(String interLogadouro) {
			this.interLogadouro = interLogadouro;
		}
		
		
		public java.sql.Timestamp getIntAtualizacao() {
			return intAtualizacao;
		}

		public void setIntAtualizacao(java.sql.Timestamp intAtualizacao) {
			this.intAtualizacao = intAtualizacao;
		}

		public Geometry getInterGeom() {
			return interGeom;
		}

		public void setInterGeom(Geometry interGeom) {
			this.interGeom = interGeom;
		}

		
	
		

}