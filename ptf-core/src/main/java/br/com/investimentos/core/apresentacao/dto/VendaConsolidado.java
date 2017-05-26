package br.com.investimentos.core.apresentacao.dto;

import java.math.BigDecimal;

public class VendaConsolidado extends OrdemConsolidadoDTO{
	private static final long serialVersionUID = -1116133706102607226L;

	private OrdemDTO ordemVenda;
	
	public VendaConsolidado(OrdemDTO ordemVenda){
		this.ordemVenda = ordemVenda;
	}
	
	public void calcularVenda(BigDecimal valorTotalOrdensDia){
		this.ordemVenda.calcularOrdem(valorTotalOrdensDia);
	}
	
	public OrdemDTO getOrdemVenda() {
		return ordemVenda;
	}
	
	public BigDecimal getLucro(){
		return this.getOrdemVenda().getValorTotalOrdem().subtract(this.getOrdemVenda().getTotalTaxas()).subtract(this.getValorTotal()).subtract(this.getTotalTaxas());
	}
	
	public BigDecimal getPercentual(){
		BigDecimal result = this.getValorTotal().add(this.getTotalTaxas()).add(this.getOrdemVenda().getTotalTaxas());
		return new BigDecimal(100).multiply(this.getLucro()).divide(result, BigDecimal.ROUND_HALF_UP);
	}
}