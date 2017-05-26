package br.com.investimentos.core.dominio.servico;

import java.math.BigDecimal;
import java.util.List;

import br.com.investimentos.core.apresentacao.dto.CorretagemDTO;

public class CorretagemServico {
	public static CorretagemDTO getCorretagem(BigDecimal valor, List<CorretagemDTO> corretagens){
		for (CorretagemDTO dto : corretagens) {
			if (dto.getValorInicial().compareTo(valor) <= 0 && (dto.getValorFinal() == null || dto.getValorFinal().compareTo(valor) >= 0)) {
				return dto;
			}
		}
		
		return null;//TODO exception
	}
}