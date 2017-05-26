package br.com.investimentos.core.apresentacao.assembler;

import java.util.ArrayList;
import java.util.List;

import br.com.investimentos.core.apresentacao.dto.OrdemDTO;
import br.com.investimentos.core.dominio.DomainStore;
import br.com.investimentos.core.dominio.entidade.Corretora;
import br.com.investimentos.core.dominio.entidade.Ordem;
import br.com.investimentos.core.dominio.entidade.Papel;
import br.com.investimentos.core.infraestrutura.HibernateUtil;

public final class OrdemAssembler {
	private static DomainStore domainStore = new HibernateUtil();
	
	private OrdemAssembler(){}
	
	public static OrdemDTO toDTO(Ordem ordem){
		OrdemDTO dto = new OrdemDTO();
		
		dto.setId(ordem.getId());
		dto.setCorretoraDTO(CorretoraAssembler.toDTO(ordem.getCorretora()));
		dto.setValor(ordem.getValor());
		//dto.setCustodia(acao.getCustodia());
		dto.setData(ordem.getData());
		dto.setQuantidade(ordem.getQuantidade());
		dto.setTipoOperacao(ordem.getTipoOperacao());
		dto.setPapelDTO(PapelAssembler.toDTO(ordem.getPapel()));
		dto.setCorretagemDTO(CorretagemAssembler.toDTO(ordem.getCorretagem()));
		dto.setEmolumentoDTO(EmolumentoAssembler.toDTO(ordem.getEmolumento()));
		dto.setTaxaLiquidacaoDTO(TaxaLiquidacaoAssembler.toDTO(ordem.getTaxaLiquidacao()));
		
		dto.setCorretoraDTO(CorretoraAssembler.toDTO(ordem.getCorretora()));
		
		return dto;
	}
	
	public static Ordem fromDTO(OrdemDTO dto){
		Ordem ordem = new Ordem();
		
		ordem.setCorretora(CorretoraAssembler.fromDTO(dto.getCorretoraDTO()));
		ordem.setValor(dto.getValor());
		ordem.setData(dto.getData());
		ordem.setId(dto.getId());
		ordem.setQuantidade(dto.getQuantidade());

		ordem.setTipoOperacao(dto.getTipoOperacao());
		
		Corretora corretora = (Corretora) domainStore.consultarPorId(Corretora.class, dto.getCorretoraDTO().getId());
		ordem.setCorretora(corretora);
		ordem.setCorretagem(CorretagemAssembler.fromDTO(dto.getCorretagemDTO()));
		ordem.setCustodia(corretora.getCustodia());
		ordem.setEmolumento(EmolumentoAssembler.fromDTO(dto.getEmolumentoDTO()));
		ordem.setTaxaLiquidacao(TaxaLiquidacaoAssembler.fromDTO(dto.getTaxaLiquidacaoDTO()));
		
		ordem.setPapel((Papel) domainStore.consultarPorId(Papel.class, dto.getPapelDTO().getCodigo()));
		
		return ordem;
	}
	
	public static List<Ordem> fromDTO(List<OrdemDTO> dtos){
		List<Ordem> acoes = new ArrayList<Ordem>(dtos.size());
		
		for(OrdemDTO ordemDTO : dtos){
			acoes.add(fromDTO(ordemDTO));
		}
		
		return acoes;
	}
	
	public static List<OrdemDTO> toDTO(List<Ordem> ordens){
		List<OrdemDTO> acoesDTO = new ArrayList<OrdemDTO>(ordens.size());
		
		for(Ordem ordem : ordens){
			acoesDTO.add(toDTO(ordem));
		}
		
		return acoesDTO;
	}

}