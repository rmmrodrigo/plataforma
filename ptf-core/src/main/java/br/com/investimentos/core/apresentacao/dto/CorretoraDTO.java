package br.com.investimentos.core.apresentacao.dto;

import java.math.BigDecimal;
import java.util.List;

public class CorretoraDTO extends AbstractDTO{
	private static final long serialVersionUID = -2177364126947045653L;
	private String nome;
	private BigDecimal custodia;
	private EmolumentoDTO emolumentoDTO;
	private TaxaLiquidacaoDTO taxaLiquidacaoDTO;
	private List<CorretagemDTO> corretagens;

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
	public List<CorretagemDTO> getCorretagens() {
		return corretagens;
	}
	public void setCorretagens(List<CorretagemDTO> corretagens) {
		this.corretagens = corretagens;
	}
}