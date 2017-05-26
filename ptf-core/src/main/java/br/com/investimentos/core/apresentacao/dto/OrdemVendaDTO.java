package br.com.investimentos.core.apresentacao.dto;

import java.math.BigDecimal;

public class OrdemVendaDTO extends OrdemCarteiraDTO{
	private static final long serialVersionUID = 5500643891668821883L;
	
	public OrdemVendaDTO(OrdemDTO ordemDTO) {
		super(ordemDTO);
	}

	@Override
	protected void calcularQuantidade(Double quantidadeVenda) {
		this.setQuantidade(this.getPercentual().divide(new BigDecimal(100)).multiply(new BigDecimal(quantidadeVenda)));
		this.percentual = this.getQuantidade().multiply(new BigDecimal(100).setScale(2)).divide(new BigDecimal(this.ordemDTO.getQuantidade()), BigDecimal.ROUND_HALF_UP);
	}
	
	protected void calcularCorretagemCarteira() {
		this.valorCorretagem = this.getPercentual().divide(new BigDecimal(100).setScale(2)).multiply(this.ordemDTO.getValorCorretagem());
	}

	protected void calcularEmolumentoCarteira() {
		this.valorEmolumento = this.getPercentual().divide(new BigDecimal(100).setScale(2)).multiply(this.ordemDTO.getValorEmolumento());
	}
	protected void calcularTaxaLiquidacaoCarteira() {
		this.valorTaxaLiquidacao = this.getPercentual().divide(new BigDecimal(100).setScale(2)).multiply(this.ordemDTO.getValorTaxaLiquidacao());
	}
}