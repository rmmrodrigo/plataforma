package br.com.investimentos.core.apresentacao.dto;

import java.math.BigDecimal;
import java.util.Date;

import br.com.investimentos.core.dominio.entidade.enuns.TipoOperacao;

public class OrdemDTO extends AbstractDTO{
	private static final long serialVersionUID = 5228004119173724784L;
	private Integer quantidade;
	private Date data;
	private BigDecimal valor;
	private TipoOperacao tipoOperacao;
	private CorretagemDTO corretagemDTO;
	private CorretoraDTO corretoraDTO;
	private EmolumentoDTO emolumentoDTO;
	private TaxaLiquidacaoDTO taxaLiquidacaoDTO;
	private PapelDTO papelDTO;
	
	private BigDecimal valorCorretagem;
	private BigDecimal valorEmolumento;
	private BigDecimal valorTaxaLiquidacao;
	private BigDecimal percentual;
	
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public CorretagemDTO getCorretagemDTO() {
		return corretagemDTO;
	}
	public void setCorretagemDTO(CorretagemDTO corretagemDTO) {
		this.corretagemDTO = corretagemDTO;
	}
	public CorretoraDTO getCorretoraDTO() {
		return corretoraDTO;
	}
	public void setCorretoraDTO(CorretoraDTO corretoraDTO) {
		this.corretoraDTO = corretoraDTO;
	}
	public PapelDTO getPapelDTO() {
		return papelDTO;
	}
	public void setPapelDTO(PapelDTO papelDTO) {
		this.papelDTO = papelDTO;
	}
	
	public EmolumentoDTO getEmolumentoDTO() {
		return emolumentoDTO;
	}
	public void setEmolumentoDTO(EmolumentoDTO emolumentoDTO) {
		this.emolumentoDTO = emolumentoDTO;
	}
	public TaxaLiquidacaoDTO getTaxaLiquidacaoDTO() {
		return taxaLiquidacaoDTO;
	}
	public void setTaxaLiquidacaoDTO(TaxaLiquidacaoDTO taxaLiquidacaoDTO) {
		this.taxaLiquidacaoDTO = taxaLiquidacaoDTO;
	}
	
	public BigDecimal getValorTotalOrdem(){
		return new BigDecimal(this.quantidade).multiply(this.valor);
	}
	
	public BigDecimal getValorCorretagem() {
		return valorCorretagem;
	}
	
	public BigDecimal getValorEmolumento(){
		return this.valorEmolumento;
	}
	
	
	public BigDecimal getValorTaxaLiquidacao(){
		return this.valorTaxaLiquidacao;
	}
	
	public BigDecimal getTotalTaxas(){
		return getValorCorretagem().add(this.getValorEmolumento()).add(this.getValorTaxaLiquidacao());
	}
	
	public BigDecimal getValorFinal(){
		return this.getValorTotalOrdem().add(this.getValorCorretagem()).add(this.getValorEmolumento()).add(this.getValorTaxaLiquidacao());
	}
	
	public TipoOperacao getTipoOperacao() {
		return tipoOperacao;
	}
	public void setTipoOperacao(TipoOperacao tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}
	public BigDecimal getPercentual() {
		return percentual;
	}
	
	public void calcularOrdem(BigDecimal valorTotalOrdensDia){
		this.calcularPercentual(valorTotalOrdensDia);
		this.calcularCorretagem(valorTotalOrdensDia);
		this.calcularEmolumento(valorTotalOrdensDia);
		this.calcularTaxaLiquidacao(valorTotalOrdensDia);
	}
	
	private void calcularPercentual(BigDecimal totalOrdem){
		this.percentual = this.getValorTotalOrdem().multiply(new BigDecimal(100)).divide(totalOrdem, BigDecimal.ROUND_HALF_UP);
	}
	
	private void calcularCorretagem(BigDecimal totalOrdem) {
		BigDecimal variavel = this.getCorretagemDTO().getTaxa();
		
		if(variavel == null || variavel.equals(BigDecimal.ZERO)){
			variavel = BigDecimal.ZERO;
		}else{
			variavel = variavel.divide(new BigDecimal(100)).multiply(totalOrdem);
		}
		
		this.valorCorretagem = this.getCorretagemDTO().getFixo().add(variavel);
		
		this.valorCorretagem = getPercentual().divide(new BigDecimal(100)).multiply(this.valorCorretagem);
	}

	private void calcularEmolumento(BigDecimal totalOrdem) {
		this.valorEmolumento = totalOrdem.multiply(this.getEmolumentoDTO().getOperacaoNomal().divide(new BigDecimal(100)));
		
		this.valorEmolumento = getPercentual().divide(new BigDecimal(100)).multiply(this.valorEmolumento); 
	}
	private void calcularTaxaLiquidacao(BigDecimal totalOrdem) {
		this.valorTaxaLiquidacao = totalOrdem.multiply(this.getTaxaLiquidacaoDTO().getOperacaoNomal().divide(new BigDecimal(100)));
		this.valorTaxaLiquidacao = getPercentual().divide(new BigDecimal(100)).multiply(this.valorTaxaLiquidacao);
	}
}