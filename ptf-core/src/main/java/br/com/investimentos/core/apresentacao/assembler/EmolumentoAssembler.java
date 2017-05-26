package br.com.investimentos.core.apresentacao.assembler;

import br.com.investimentos.core.apresentacao.dto.EmolumentoDTO;
import br.com.investimentos.core.dominio.entidade.Emolumento;

public class EmolumentoAssembler {
	private EmolumentoAssembler(){}
	
	public static EmolumentoDTO toDTO(Emolumento emolumento){
		EmolumentoDTO dto = new EmolumentoDTO();
		
		dto.setId(emolumento.getId());
		dto.setDayTrade(emolumento.getDayTrade());
		dto.setOperacaoNomal(emolumento.getOperacaoNomal());
		
		return dto;
	}
	
	public static Emolumento fromDTO(EmolumentoDTO dto){
		Emolumento emolumento = new Emolumento();
		
		emolumento.setId(dto.getId());
		emolumento.setDayTrade(dto.getDayTrade());
		emolumento.setOperacaoNomal(dto.getOperacaoNomal());
		
		return emolumento;
	}
}