package br.com.investimentos.core.dominio.entidade;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="corretora")
public class Corretora extends Entidade{
	private static final long serialVersionUID = 594226813744704638L;
	
	@Column(nullable=false)
	private String nome;
	
	@Column
	private BigDecimal custodia;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="corretora")
	private List<Corretagem> corretagens = new ArrayList<Corretagem>(0);
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="corretora")
	private Emolumento emolumento;
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="corretora")
	private TaxaLiquidacao taxaLiquidacao;
	
	//@OneToMany(fetch=FetchType.LAZY, mappedBy="corretora")
	//private List<Acao> operacoes = new ArrayList<Acao>(0);

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getCustodia() {
		return custodia;
	}

	public void setCustodia(BigDecimal custodia) {
		this.custodia = custodia;
	}

	public List<Corretagem> getCorretagens() {
		return corretagens;
	}

	public void setCorretagens(List<Corretagem> corretagens) {
		this.corretagens = corretagens;
	}

	public Emolumento getEmolumento() {
		return emolumento;
	}

	public void setEmolumento(Emolumento emolumento) {
		this.emolumento = emolumento;
	}

	public TaxaLiquidacao getTaxaLiquidacao() {
		return taxaLiquidacao;
	}

	public void setTaxaLiquidacao(TaxaLiquidacao taxaLiquidacao) {
		this.taxaLiquidacao = taxaLiquidacao;
	}
}