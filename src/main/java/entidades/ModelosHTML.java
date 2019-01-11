package entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ModelosHTML implements Serializable {
	
	
	private static final long serialVersionUID = 3746926833426506331L;
	

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name="mod_ID")
	private int modeloID;
	
	@Column (name="mod_Unidade", columnDefinition="varchar(10)") // texto
	private String mUnidade;
	
	@Column (name="mod_Identificacao", columnDefinition="varchar(90)") // texto
	private String mIdentificacao;
	
	@Column (name="mod_Conteudo", columnDefinition="varchar(max)") // texto
	private String mConteudo;
	
	// construtor padrao //
	public ModelosHTML () {
		
	}

	public int getModeloID() {
		return modeloID;
	}

	public void setModeloID(int modeloID) {
		this.modeloID = modeloID;
	}

	public String getmUnidade() {
		return mUnidade;
	}

	public void setmUnidade(String mUnidade) {
		this.mUnidade = mUnidade;
	}

	public String getmIdentificacao() {
		return mIdentificacao;
	}

	public void setmIdentificacao(String mIdentificacao) {
		this.mIdentificacao = mIdentificacao;
	}

	public String getmConteudo() {
		return mConteudo;
	}

	public void setmConteudo(String mConteudo) {
		this.mConteudo = mConteudo;
	}

	
}
