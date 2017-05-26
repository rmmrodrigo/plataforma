package br.com.investimentos.core.dominio.entidade;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="taxa_liquidacao")
public class TaxaLiquidacao extends Entidade{
	private static final long serialVersionUID = 3963063197550250664L;

	@Column(precision=10, scale=4)
	private BigDecimal operacaoNomal;
	
	@Column(precision=10, scale=4)
	private BigDecimal dayTrade;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(nullable=false)
	private Corretora corretora;

	public BigDecimal getOperacaoNomal() {
		return operacaoNomal;
	}

	public void setOperacaoNomal(BigDecimal operacaoNomal) {
		this.operacaoNomal = operacaoNomal;
	}

	public BigDecimal getDayTrade() {
		return dayTrade;
	}

	public void setDayTrade(BigDecimal dayTrade) {
		this.dayTrade = dayTrade;
	}

	public Corretora getCorretora() {
		return corretora;
	}

	public void setCorretora(Corretora corretora) {
		this.corretora = corretora;
	}
}