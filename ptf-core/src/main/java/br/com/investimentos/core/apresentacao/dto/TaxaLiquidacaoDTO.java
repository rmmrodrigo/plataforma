package br.com.investimentos.core.apresentacao.dto;

import java.math.BigDecimal;

public class TaxaLiquidacaoDTO extends AbstractDTO{
	private static final long serialVersionUID = 642531458183834349L;
	
	private BigDecimal operacaoNomal;
	private BigDecimal dayTrade;
	
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
}