package br.com.investimentos.core.apresentacao.assembler;

import java.util.ArrayList;
import java.util.List;

import br.com.investimentos.core.apresentacao.dto.CorretagemDTO;
import br.com.investimentos.core.dominio.entidade.Corretagem;

public final class CorretagemAssembler {
	private CorretagemAssembler(){}
	
	public static CorretagemDTO toDTO(Corretagem corretagem){
		CorretagemDTO dto = new CorretagemDTO();
		
		dto.setId(corretagem.getId());
		dto.setFixo(corretagem.getFixo());
		dto.setTaxa(corretagem.getTaxa());
		dto.setValorFinal(corretagem.getValorFinal());
		dto.setValorInicial(corretagem.getValorInicial());
		dto.setAtiva(corretagem.getAtiva());
		
		return dto;
	}
	
	public static List<CorretagemDTO> toDTO(List<Corretagem> corretagens){
		List<CorretagemDTO> dtos = new ArrayList<CorretagemDTO>(corretagens.size());
		
		for(Corretagem corretagem : corretagens){
			dtos.add(toDTO(corretagem));
		}
		
		return dtos;
	}
	
	public static Corretagem fromDTO(CorretagemDTO dto){
		Corretagem corretagem = new Corretagem();
		
		corretagem.setId(dto.getId());
		corretagem.setFixo(dto.getFixo());
		corretagem.setTaxa(dto.getTaxa());
		corretagem.setValorFinal(dto.getValorFinal());
		corretagem.setValorInicial(dto.getValorInicial());
		corretagem.setAtiva(dto.getAtiva());
		
		return corretagem;
	}

	public static List<Corretagem> fromDTO(List<CorretagemDTO> dtos) {
		List<Corretagem> corretagens = new ArrayList<Corretagem>(dtos.size());
		
		for(CorretagemDTO dto : dtos){
			corretagens.add(fromDTO(dto));
		}
		
		return corretagens;
	}
}