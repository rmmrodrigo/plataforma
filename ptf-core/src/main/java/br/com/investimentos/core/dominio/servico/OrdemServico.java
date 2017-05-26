package br.com.investimentos.core.dominio.servico;

import java.math.BigDecimal;
import java.util.List;

import br.com.investimentos.core.apresentacao.dto.OrdemDTO;

public class OrdemServico {
	public static BigDecimal getValorTotalOrdens(List<? extends OrdemDTO> ordensDto) {
		BigDecimal valorTotalOrdem = BigDecimal.ZERO;

		for (OrdemDTO ordemDTO : ordensDto) {
			valorTotalOrdem = valorTotalOrdem.add(ordemDTO.getValorTotalOrdem());
		}
		return valorTotalOrdem;
	}
	
	public static Integer getQuantidadeTotalOrdens(List<? extends OrdemDTO> ordensDto) {
		Integer qtdTotalOrdem = 0;

		for (OrdemDTO ordemDTO : ordensDto) {
			qtdTotalOrdem += ordemDTO.getQuantidade();
		}
		return qtdTotalOrdem;
	}
}