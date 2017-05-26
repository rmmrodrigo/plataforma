package br.com.investimentos.core.apresentacao.assembler;

import java.util.ArrayList;
import java.util.List;

import br.com.investimentos.core.apresentacao.dto.CorretoraDTO;
import br.com.investimentos.core.dominio.entidade.Corretagem;
import br.com.investimentos.core.dominio.entidade.Corretora;

public class CorretoraAssembler {
	private CorretoraAssembler(){}
	
	public static CorretoraDTO toDTO(Corretora corretora){
		CorretoraDTO dto = new CorretoraDTO();
		
		dto.setId(corretora.getId());
		dto.setCustodia(corretora.getCustodia());
		dto.setCorretagens(CorretagemAssembler.toDTO(corretora.getCorretagens()));
		dto.setEmolumentoDTO(EmolumentoAssembler.toDTO(corretora.getEmolumento()));
		dto.setTaxaLiquidacaoDTO(TaxaLiquidacaoAssembler.toDTO(corretora.getTaxaLiquidacao()));
		dto.setNome(corretora.getNome());
		
		dto.setCorretagens(CorretagemAssembler.toDTO(corretora.getCorretagens()));
		
		return dto;
	}
	
	public static List<CorretoraDTO> toDTO(List<Corretora> corretoras) {
		List<CorretoraDTO> dtos = new ArrayList<CorretoraDTO>(corretoras.size());
		
		for(Corretora corretora : corretoras){
			dtos.add(toDTO(corretora));
		}
		
		return dtos;
	}

	public static Corretora fromDTO(CorretoraDTO dto){
		Corretora corretora = new Corretora();
		
		corretora.setId(dto.getId());
		corretora.setNome(dto.getNome());
		corretora.setCustodia(dto.getCustodia());
		
		if(dto.getCorretagens() != null){
			corretora.setCorretagens(CorretagemAssembler.fromDTO(dto.getCorretagens()));
		}
		if(dto.getEmolumentoDTO() != null){
			corretora.setEmolumento(EmolumentoAssembler.fromDTO(dto.getEmolumentoDTO()));
			corretora.getEmolumento().setCorretora(corretora);
		}
		
		if(dto.getTaxaLiquidacaoDTO() != null){
			corretora.setTaxaLiquidacao(TaxaLiquidacaoAssembler.fromDTO(dto.getTaxaLiquidacaoDTO()));
			corretora.getTaxaLiquidacao().setCorretora(corretora);
		}
		
		for(Corretagem corretagem:corretora.getCorretagens()){
			corretagem.setCorretora(corretora);
		}
		
		
		return corretora;
	}
}