package br.com.investimento.apresentacao.assembler;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import br.com.investimentos.core.apresentacao.assembler.EmolumentoAssembler;
import br.com.investimentos.core.apresentacao.dto.EmolumentoDTO;
import br.com.investimentos.core.dominio.entidade.Emolumento;

public class EmolumentoAssemblerTest {
	@Test
	public void toDTO(){
		Emolumento emolumento = new Emolumento();
		
		emolumento.setId(5L);
		emolumento.setDayTrade(new BigDecimal(189));
		emolumento.setOperacaoNomal(new BigDecimal(598));
		
		EmolumentoDTO dto = EmolumentoAssembler.toDTO(emolumento);
		
		Assert.assertEquals(5l, dto.getId().longValue());
		Assert.assertEquals(189d, dto.getDayTrade().doubleValue(), 0d);
		Assert.assertEquals(598d, dto.getOperacaoNomal().doubleValue(), 0d);
	}
	
	@Test
	public void fromDTO(){
		EmolumentoDTO dto = new EmolumentoDTO();
		
		dto.setId(95L);
		dto.setDayTrade(new BigDecimal(652));
		dto.setOperacaoNomal(new BigDecimal(21));
		
		Emolumento emolumento = EmolumentoAssembler.fromDTO(dto);
		
		Assert.assertEquals(95l, emolumento.getId().longValue());
		Assert.assertEquals(652d, emolumento.getDayTrade().doubleValue(), 0d);
		Assert.assertEquals(21d, emolumento.getOperacaoNomal().doubleValue(), 0d);
	}
}