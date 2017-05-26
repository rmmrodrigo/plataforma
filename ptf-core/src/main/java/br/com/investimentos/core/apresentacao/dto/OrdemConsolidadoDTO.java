package br.com.investimentos.core.apresentacao.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrdemConsolidadoDTO implements Serializable{
	private static final long serialVersionUID = 5500643891668821883L;
	private PapelDTO papelDTO;
	private CorretoraDTO corretoraDTO;
	
	private BigDecimal quantidade = new BigDecimal(0);
	private BigDecimal valorCorretagem = new BigDecimal(0);
	private BigDecimal valorEmolumento = new BigDecimal(0);
	private BigDecimal valorTaxaLiquidacao = new BigDecimal(0);
	private BigDecimal valor = new BigDecimal(0);
	private BigDecimal valorTotal = new BigDecimal(0);
	
	private BigDecimal cotacaoAtual = new BigDecimal(0);
	private Date menorData;
	private Long quantidadeDias;
	private String classe;
	
	private List<OrdemCarteiraDTO> ordensCarteira = new ArrayList<OrdemCarteiraDTO>();
	
	public void adicionarOrdem(OrdemCarteiraDTO ordemCarteiraDto){
		this.valorCorretagem = this.valorCorretagem.add(ordemCarteiraDto.getValorCorretagem());
		this.valorEmolumento = this.valorEmolumento.add(ordemCarteiraDto.getValorEmolumento());
		this.valorTaxaLiquidacao = this.valorTaxaLiquidacao.add(ordemCarteiraDto.getValorTaxaLiquidacao());
		this.quantidade = this.quantidade.add(ordemCarteiraDto.getQuantidade());
		this.valorTotal = valorTotal.add(ordemCarteiraDto.getValorTotalOrdem());
		this.valor = this.valorTotal.divide(this.quantidade, BigDecimal.ROUND_HALF_UP);
		
		if(this.getMenorData() == null || ordemCarteiraDto.getData().before(this.getMenorData())){
			this.menorData = ordemCarteiraDto.getData();
		}
		
		ordensCarteira.add(ordemCarteiraDto);
	}
	
	public BigDecimal getTotalTaxas(){
		return valorCorretagem.add(this.valorEmolumento).add(this.valorTaxaLiquidacao);
	}
	
	public BigDecimal getTotalMercado(){
		return cotacaoAtual.multiply(this.quantidade);
	}
	public BigDecimal getLucro(){
		BigDecimal result = this.getTotalTaxas().multiply(getTotalMercado()).divide(this.valorTotal, BigDecimal.ROUND_HALF_UP);
		return getTotalMercado().subtract(this.valorTotal).subtract(this.getTotalTaxas()).subtract(result);
	}
	
	public BigDecimal getPercentual(){
		BigDecimal valor1 = this.getTotalTaxas().multiply(this.getTotalMercado()).divide(this.valorTotal, BigDecimal.ROUND_HALF_UP);
		BigDecimal result = this.valorTotal.add(this.getTotalTaxas()).add(valor1);
		return new BigDecimal(100).multiply(this.getLucro()).divide(result, BigDecimal.ROUND_HALF_UP);
	}
	
	public void setCotacaoAtual(BigDecimal cotacaoAtual) {
		if(this.cotacaoAtual.compareTo(cotacaoAtual)==0){
			this.classe = "";
		}else if(cotacaoAtual.compareTo(this.cotacaoAtual)==-1){
			this.classe = "baixa";
		}else{
			this.classe = "alta";
		}
		
		this.cotacaoAtual = cotacaoAtual;
	}
	
	public String getClasse() {
		return classe;
	}

	public void setPapelDTO(PapelDTO papelDTO) {
		this.papelDTO = papelDTO;
	}
	
	public PapelDTO getPapelDTO() {
		return papelDTO;
	}

	public CorretoraDTO getCorretoraDTO() {
		return corretoraDTO;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
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

	public BigDecimal getValor() {
		return valor;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public BigDecimal getCotacaoAtual() {
		return cotacaoAtual;
	}
	
	public void setQuantidadeDias(Long quantidadeDias) {
		this.quantidadeDias = quantidadeDias;
	}

	public Long getQuantidadeDias() {
		return quantidadeDias;
	}
	
	public Date getMenorData() {
		return menorData;
	}
}