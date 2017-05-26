package br.com.investimentos.core.dominio.entidade;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.investimentos.core.dominio.entidade.enuns.TipoOperacao;

@Entity
@Table(name="ordem")
public class Ordem extends Entidade{
	private static final long serialVersionUID = -9217426737911334204L;

	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	@Column(nullable=false)
	private Integer quantidade;
	
	@Column(nullable=false)
	private BigDecimal valor;
	
	@Column(nullable=false)
	private BigDecimal custodia;
	
	@Column(nullable=false)
	@Enumerated(EnumType.STRING)
	private TipoOperacao tipoOperacao;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(nullable=false)
	private Papel papel;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(nullable=false)
	private Corretora corretora;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(nullable=false)
	private Corretagem corretagem;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(nullable=false)
	private Emolumento emolumento;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(nullable=false)
	private TaxaLiquidacao taxaLiquidacao;

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="ordemSaida")
	private List<OrdemSaida> ordensSaida = new ArrayList<OrdemSaida>();
	
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getCustodia() {
		return custodia;
	}

	public void setCustodia(BigDecimal custodia) {
		this.custodia = custodia;
	}

	public TipoOperacao getTipoOperacao() {
		return tipoOperacao;
	}

	public void setTipoOperacao(TipoOperacao tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}

	public Papel getAcao() {
		return papel;
	}

	public void setAcao(Papel papel) {
		this.papel = papel;
	}

	public Corretora getCorretora() {
		return corretora;
	}

	public void setCorretora(Corretora corretora) {
		this.corretora = corretora;
	}

	public Corretagem getCorretagem() {
		return corretagem;
	}

	public void setCorretagem(Corretagem corretagem) {
		this.corretagem = corretagem;
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

	public Papel getPapel() {
		return papel;
	}

	public void setPapel(Papel papel) {
		this.papel = papel;
	}

	public List<OrdemSaida> getOrdensSaida() {
		return ordensSaida;
	}

	public void setOrdensSaida(List<OrdemSaida> ordensSaida) {
		this.ordensSaida = ordensSaida;
	}
}