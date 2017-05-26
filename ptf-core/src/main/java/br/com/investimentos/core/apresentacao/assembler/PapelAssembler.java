package br.com.investimentos.core.apresentacao.assembler;

import java.util.ArrayList;
import java.util.List;

import br.com.investimentos.core.apresentacao.dto.PapelDTO;
import br.com.investimentos.core.dominio.entidade.Papel;

public final class PapelAssembler {
	private PapelAssembler(){}
	
	public static PapelDTO toDTO(Papel papel){
		PapelDTO dto = new PapelDTO();
		
		dto.setCodigo(papel.getCodigo());
		dto.setDescricao(papel.getDescricao());
		
		return dto;
	}
	
	public static List<PapelDTO> toDTO(List<Papel> papeis) {
		List<PapelDTO> papeisDTO = new ArrayList<PapelDTO>(papeis.size());
		
		for(Papel papel : papeis){
			papeisDTO.add(toDTO(papel));
		}
		
		return papeisDTO;
	}

	public static Papel fromDTO(PapelDTO dto){
		Papel papel = new Papel();
		
		papel.setCodigo(dto.getCodigo());
		papel.setDescricao(dto.getDescricao());
		
		return papel;
	}
}