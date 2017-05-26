package br.com.investimentos.core.apresentacao.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.investimentos.core.aplicacao.DiaServico;
import br.com.investimentos.core.apresentacao.assembler.CorretoraAssembler;
import br.com.investimentos.core.apresentacao.assembler.OrdemAssembler;
import br.com.investimentos.core.apresentacao.assembler.PapelAssembler;
import br.com.investimentos.core.dominio.entidade.Corretora;
import br.com.investimentos.core.dominio.entidade.Ordem;
import br.com.investimentos.core.dominio.entidade.enuns.TipoOperacao;
import br.com.investimentos.core.dominio.repositorio.OrdemRepositorio;
import br.com.investimentos.core.dominio.servico.OrdemServico;

public class Venda implements Serializable{
	private static final long serialVersionUID = 4020065497018888299L;

	private List<VendaConsolidado> vendasConsolidado = new ArrayList<VendaConsolidado>();
	private CorretoraDTO corretoraDTO;

	private OrdemRepositorio ordemRepositorio;

	public Venda(OrdemRepositorio ordemRepositorio, CorretoraDTO corretoraDTO){
		this.ordemRepositorio = ordemRepositorio;
		this.corretoraDTO = corretoraDTO;
	}

	public void montarVenda(){
		Corretora corretora = CorretoraAssembler.fromDTO(corretoraDTO);
		List<OrdemDTO> ordensVenda = OrdemAssembler.toDTO(this.ordemRepositorio.recuperar(TipoOperacao.VENDA, corretora));

		for (OrdemDTO ordemDTO : ordensVenda) {
			List<OrdemDTO> ordensCompra = OrdemAssembler.toDTO(
					this.ordemRepositorio.recuperar(PapelAssembler.fromDTO(ordemDTO.getPapelDTO()), TipoOperacao.COMPRA, ordemDTO.getData(), corretora));

			Integer totalOrdem = OrdemServico.getQuantidadeTotalOrdens(ordensCompra);

			VendaConsolidado vendaConsolidado = new VendaConsolidado(ordemDTO);
			vendaConsolidado.setPapelDTO(ordemDTO.getPapelDTO());
			vendaConsolidado.calcularVenda(OrdemServico.getValorTotalOrdens(OrdemAssembler.toDTO(this.ordemRepositorio.recuperarPorData(ordemDTO.getData(), corretora))));
			
			for (OrdemDTO ordemDto : ordensCompra) {
				List<Ordem> ordensData = this.ordemRepositorio.recuperarPorData(ordemDto.getData(), corretora);

				BigDecimal valorTotalOrdensDia = OrdemServico.getValorTotalOrdens(OrdemAssembler.toDTO(ordensData));
				
				OrdemVendaDTO ordemVendaDto = new OrdemVendaDTO(ordemDto);
				ordemVendaDto.calcular(ordemDTO.getQuantidade().doubleValue(), totalOrdem, valorTotalOrdensDia);

				vendaConsolidado.adicionarOrdem(ordemVendaDto);
			}
			vendaConsolidado.setQuantidadeDias(DiaServico.quantidadeDias(vendaConsolidado.getMenorData(), ordemDTO.getData()));
			vendasConsolidado.add(vendaConsolidado);
		}
	}
	
	public List<VendaConsolidado> getVendasConsolidado() {
		return vendasConsolidado;
	}
}