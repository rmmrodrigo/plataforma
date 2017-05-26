package br.com.investimentos.core.apresentacao.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class OrdemCarteiraDTO implements Serializable{
	private static final long serialVersionUID = 5500643891668821883L;

	protected OrdemDTO ordemDTO;
	protected BigDecimal valorCorretagem;
	protected BigDecimal valorEmolumento;
	protected BigDecimal valorTaxaLiquidacao;
	protected BigDecimal percentual;
	private BigDecimal quantidade;
	
	public OrdemCarteiraDTO(OrdemDTO ordemDTO){
		this.ordemDTO = ordemDTO;
	}
	
	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValorCorretagem() {
		return valorCorretagem;
	}

	public BigDecimal getValorEmolumento() {
		return valorEmolumento;
	}

	public BigDecimal getValorTaxaLiquidacao() {
		return valorTaxaLiquidacao;
	}

	public BigDecimal getPercentual() {
		return percentual;
	}
	
	
	
	public Date getData() {
		return ordemDTO.getData();
	}

	public void calcular(Double razaoCompraVenda, Integer qtdTotalOrdem, BigDecimal valorTotalOrdensDia){
		this.ordemDTO.calcularOrdem(valorTotalOrdensDia);
		
		this.calcularPercentualCarteira(qtdTotalOrdem);
		this.calcularQuantidade(razaoCompraVenda);
		this.calcularCorretagemCarteira();
		this.calcularEmolumentoCarteira();
		this.calcularTaxaLiquidacaoCarteira();
	}

	protected void calcularQuantidade(Double razaoCompraVenda){
		this.quantidade = this.getPercentual().multiply(new BigDecimal(razaoCompraVenda)).divide(new BigDecimal(100));
	}
	
	protected void calcularPercentualCarteira(Integer quantidadeTotal){
		this.percentual = new BigDecimal(this.ordemDTO.getQuantidade()).multiply(new BigDecimal(100).setScale(2)).
					divide(new BigDecimal(quantidadeTotal), BigDecimal.ROUND_HALF_UP);
	}
	
	protected void calcularCorretagemCarteira() {
		this.valorCorretagem = this.ordemDTO.getValorCorretagem().divide(new BigDecimal(this.ordemDTO.getQuantidade()), BigDecimal.ROUND_HALF_UP).multiply(quantidade);
	}

	protected void calcularEmolumentoCarteira() {
		this.valorEmolumento = this.ordemDTO.getValorEmolumento().divide(new BigDecimal(this.ordemDTO.getQuantidade()), BigDecimal.ROUND_HALF_UP).multiply(quantidade);
	}
	protected void calcularTaxaLiquidacaoCarteira() {
		this.valorTaxaLiquidacao = this.ordemDTO.getValorTaxaLiquidacao().divide(new BigDecimal(this.ordemDTO.getQuantidade()), BigDecimal.ROUND_HALF_UP).multiply(quantidade);
	}
	
	public BigDecimal getValorTotalOrdem(){
		return this.quantidade.multiply(this.ordemDTO.getValor());
	}	
}