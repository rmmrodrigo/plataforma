package br.com.investimento.apresentacao.assembler;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import br.com.investimentos.core.apresentacao.assembler.CorretagemAssembler;
import br.com.investimentos.core.apresentacao.dto.CorretagemDTO;
import br.com.investimentos.core.dominio.entidade.Corretagem;

//TODO teste data inicio e fim
public class CorretagemAssemblerTest {
	@Test
	public void toDTO(){
		Corretagem corretagem = new Corretagem();
		
		corretagem.setId(5L);
		corretagem.setFixo(new BigDecimal(125));
		corretagem.setTaxa(new BigDecimal(201));
		corretagem.setValorFinal(new BigDecimal(365.6));
		corretagem.setValorInicial(new BigDecimal(1.2));
		
		CorretagemDTO dto = CorretagemAssembler.toDTO(corretagem);
		
		Assert.assertEquals(5l, dto.getId().longValue());
		Assert.assertEquals(125d, dto.getFixo().doubleValue(), 0d);
		Assert.assertEquals(201d, dto.getTaxa().doubleValue(), 0d);
		Assert.assertEquals(365.6d, dto.getValorFinal().doubleValue(), 0d);
		Assert.assertEquals(1.2d, dto.getValorInicial().doubleValue(), 0d);
	}
	
	@Test
	public void fromDTO(){
		CorretagemDTO dto = new CorretagemDTO();
		
		dto.setId(25L);
		dto.setFixo(new BigDecimal(958));
		dto.setTaxa(new BigDecimal(36.9));
		dto.setValorFinal(new BigDecimal(0.36));
		dto.setValorInicial(new BigDecimal(965));
		
		Corretagem corretagem = CorretagemAssembler.fromDTO(dto);
		
		Assert.assertEquals(25l, corretagem.getId().longValue());
		Assert.assertEquals(958d, corretagem.getFixo().doubleValue(), 0d);
		Assert.assertEquals(36.9d, corretagem.getTaxa().doubleValue(), 0d);
		Assert.assertEquals(0.36d, corretagem.getValorFinal().doubleValue(), 0d);
		Assert.assertEquals(965d, corretagem.getValorInicial().doubleValue(), 0d);
	}
}