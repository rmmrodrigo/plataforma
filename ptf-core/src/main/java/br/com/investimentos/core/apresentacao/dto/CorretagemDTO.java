package br.com.investimentos.core.apresentacao.dto;

import java.math.BigDecimal;

public class CorretagemDTO extends AbstractDTO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7900657939839154516L;
	
	private BigDecimal valorInicial;
	private BigDecimal valorFinal;
	private BigDecimal taxa;
	private BigDecimal fixo;
	private Boolean ativa;
	
	public BigDecimal getValorInicial() {
		return valorInicial;
	}
	public void setValorInicial(BigDecimal valorInicial) {
		this.valorInicial = valorInicial;
	}
	public BigDecimal getValorFinal() {
		return valorFinal;
	}
	public void setValorFinal(BigDecimal valorFinal) {
		this.valorFinal = valorFinal;
	}
	public BigDecimal getTaxa() {
		return taxa;
	}
	public void setTaxa(BigDecimal taxa) {
		this.taxa = taxa;
	}
	public BigDecimal getFixo() {
		return fixo;
	}
	public void setFixo(BigDecimal fixo) {
		this.fixo = fixo;
	}
	public Boolean getAtiva() {
		return ativa;
	}
	public void setAtiva(Boolean ativa) {
		this.ativa = ativa;
	}
}