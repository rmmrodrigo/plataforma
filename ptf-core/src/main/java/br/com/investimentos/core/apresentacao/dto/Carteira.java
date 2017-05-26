package br.com.investimentos.core.apresentacao.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.investimentos.core.apresentacao.assembler.CorretoraAssembler;
import br.com.investimentos.core.apresentacao.assembler.OrdemAssembler;
import br.com.investimentos.core.apresentacao.assembler.PapelAssembler;
import br.com.investimentos.core.dominio.entidade.Corretora;
import br.com.investimentos.core.dominio.entidade.Ordem;
import br.com.investimentos.core.dominio.entidade.Papel;
import br.com.investimentos.core.dominio.entidade.enuns.TipoOperacao;
import br.com.investimentos.core.dominio.repositorio.OrdemRepositorio;
import br.com.investimentos.core.dominio.servico.OrdemServico;

public class Carteira implements Serializable{
	private static final long serialVersionUID = 3778962269712559426L;

	private CorretoraDTO corretoraDTO;
	private OrdemRepositorio ordemRepositorio;
	
	private List<OrdemConsolidadoDTO> ordensConsolidado = new ArrayList<OrdemConsolidadoDTO>();
	
	public Carteira(OrdemRepositorio ordemRepositorio, CorretoraDTO corretoraDTO){
		this.ordemRepositorio = ordemRepositorio;
		this.corretoraDTO = corretoraDTO;
	}
	
	public CorretoraDTO getCorretoraDTO() {
		return corretoraDTO;
	}
	
	public List<OrdemConsolidadoDTO> getOrdensConsolidado() {
		return ordensConsolidado;
	}
	
	public void montarCarteira(){
		List<Papel> papeis = this.ordemRepositorio.recuperarPapeis(CorretoraAssembler.fromDTO(this.corretoraDTO));
		Corretora corretora = CorretoraAssembler.fromDTO(corretoraDTO);
		
		for(Papel papel:papeis){
			List<OrdemDTO> ordensCompra = OrdemAssembler.toDTO(this.ordemRepositorio.recuperar(papel, TipoOperacao.COMPRA, corretora));
			List<OrdemDTO> ordensVenda = OrdemAssembler.toDTO(this.ordemRepositorio.recuperar(papel, TipoOperacao.VENDA, corretora));
			
			Double qtdTotalCompras = 0D;
			for (OrdemDTO ordemDTO : ordensCompra) {
				qtdTotalCompras += ordemDTO.getQuantidade();
			}
			
			Double qtdTotalVendas = 0D;
			for (OrdemDTO ordemDTO : ordensVenda) {
				qtdTotalVendas += ordemDTO.getQuantidade();
			}
			
			Double razaoCompraVenda = qtdTotalCompras-qtdTotalVendas;
			
			Integer totalOrdem = OrdemServico.getQuantidadeTotalOrdens(ordensCompra);
			
			OrdemConsolidadoDTO ordemConsolidadoDTO = new OrdemConsolidadoDTO();
			ordemConsolidadoDTO.setPapelDTO(PapelAssembler.toDTO(papel));
			
			for (OrdemDTO ordemDto : ordensCompra) {
				List<Ordem> ordensData = this.ordemRepositorio.recuperarPorData(ordemDto.getData(), corretora);
				BigDecimal valorTotalOrdens = OrdemServico.getValorTotalOrdens(OrdemAssembler.toDTO(ordensData));//TODO fazer cache
				
				OrdemCarteiraDTO ordemCarteiraDto = new OrdemCarteiraDTO(ordemDto);
				ordemCarteiraDto.calcular(razaoCompraVenda, totalOrdem, valorTotalOrdens);
				
				ordemConsolidadoDTO.adicionarOrdem(ordemCarteiraDto);
			}
			
			ordensConsolidado.add(ordemConsolidadoDTO);
		}
	}
}