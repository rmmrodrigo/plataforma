package br.com.investimentos.core.apresentacao.assembler;

import br.com.investimentos.core.apresentacao.dto.TaxaLiquidacaoDTO;
import br.com.investimentos.core.dominio.entidade.TaxaLiquidacao;

public class TaxaLiquidacaoAssembler {
	private TaxaLiquidacaoAssembler(){}
	
	public static TaxaLiquidacaoDTO toDTO(TaxaLiquidacao taxaLiquidacao){
		TaxaLiquidacaoDTO dto = new TaxaLiquidacaoDTO();
		
		dto.setId(taxaLiquidacao.getId());
		dto.setDayTrade(taxaLiquidacao.getDayTrade());
		dto.setOperacaoNomal(taxaLiquidacao.getOperacaoNomal());
		
		return dto;
	}
	
	public static TaxaLiquidacao fromDTO(TaxaLiquidacaoDTO dto){
		TaxaLiquidacao taxaLiquidacao = new TaxaLiquidacao();
		
		taxaLiquidacao.setId(dto.getId());
		taxaLiquidacao.setDayTrade(dto.getDayTrade());
		taxaLiquidacao.setOperacaoNomal(dto.getOperacaoNomal());
		
		return taxaLiquidacao;
	}
}