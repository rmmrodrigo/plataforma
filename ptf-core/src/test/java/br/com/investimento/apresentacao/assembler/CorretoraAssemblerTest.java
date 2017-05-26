package br.com.investimento.apresentacao.assembler;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import br.com.investimentos.core.apresentacao.assembler.CorretoraAssembler;
import br.com.investimentos.core.apresentacao.dto.CorretagemDTO;
import br.com.investimentos.core.apresentacao.dto.CorretoraDTO;
import br.com.investimentos.core.apresentacao.dto.EmolumentoDTO;
import br.com.investimentos.core.dominio.entidade.Corretagem;
import br.com.investimentos.core.dominio.entidade.Corretora;
import br.com.investimentos.core.dominio.entidade.Emolumento;
import br.com.investimentos.core.dominio.entidade.TaxaLiquidacao;

public class CorretoraAssemblerTest {
	@Test
	public void toDTO(){
		Corretora corretora = new Corretora();
		corretora.setId(96L);
		corretora.setCustodia(new BigDecimal(15));
		corretora.setNome("Corretora");
		
		Emolumento emolumento = new Emolumento();
		emolumento.setId(5L);
		emolumento.setDayTrade(new BigDecimal(189));
		emolumento.setOperacaoNomal(new BigDecimal(598));
		corretora.setEmolumento(emolumento);
		
		Corretagem corretagem = new Corretagem();
		corretagem.setId(5L);
		corretagem.setFixo(new BigDecimal(125));
		corretagem.setTaxa(new BigDecimal(201));
		corretagem.setValorFinal(new BigDecimal(365.6));
		corretagem.setValorInicial(new BigDecimal(1.2));
		corretora.getCorretagens().add(corretagem);
		
		TaxaLiquidacao txl = new TaxaLiquidacao();
		txl.setId(1L);
		txl.setCorretora(corretora);
		txl.setDayTrade(new BigDecimal(4.9));
		txl.setOperacaoNomal(new BigDecimal(7.25));
		corretora.setTaxaLiquidacao(txl);
		
		CorretoraDTO dto = CorretoraAssembler.toDTO(corretora);
		
		Assert.assertEquals(96l, dto.getId().longValue());
		Assert.assertEquals(15d, dto.getCustodia().doubleValue(), 0d);
		Assert.assertEquals("Corretora", dto.getNome());
		
		Assert.assertEquals(5l, dto.getEmolumentoDTO().getId().longValue());
		Assert.assertEquals(189d, dto.getEmolumentoDTO().getDayTrade().doubleValue(), 0d);
		Assert.assertEquals(598d, dto.getEmolumentoDTO().getOperacaoNomal().doubleValue(), 0d);
		
		Assert.assertEquals(1L, dto.getTaxaLiquidacaoDTO().getId().longValue());
		Assert.assertEquals(4.9d, dto.getTaxaLiquidacaoDTO().getDayTrade().doubleValue(), 0d);
		Assert.assertEquals(7.25d, dto.getTaxaLiquidacaoDTO().getOperacaoNomal().doubleValue(), 0d);
		
		for(CorretagemDTO corretagemDTO : dto.getCorretagens()){
			Assert.assertEquals(5l, corretagemDTO.getId().longValue());
			Assert.assertEquals(125d, corretagemDTO.getFixo().doubleValue(), 0d);
			Assert.assertEquals(201d, corretagemDTO.getTaxa().doubleValue(), 0d);
			Assert.assertEquals(365.6d, corretagemDTO.getValorFinal().doubleValue(), 0d);
			Assert.assertEquals(1.2d, corretagemDTO.getValorInicial().doubleValue(), 0d);
		}
	}
	
	@Test
	public void fromDTO(){
		CorretoraDTO dto = new CorretoraDTO();
		dto.setCorretagens(new ArrayList<CorretagemDTO>());
		
		dto.setId(652L);
		dto.setCustodia(new BigDecimal(56));
		dto.setNome("Corretora DTO");

		EmolumentoDTO emolumentoDTO = new EmolumentoDTO();
		emolumentoDTO.setId(95L);
		emolumentoDTO.setDayTrade(new BigDecimal(652));
		emolumentoDTO.setOperacaoNomal(new BigDecimal(21));
		dto.setEmolumentoDTO(emolumentoDTO);
		
		CorretagemDTO corretagemDTO = new CorretagemDTO();
		
		corretagemDTO.setId(25L);
		corretagemDTO.setFixo(new BigDecimal(958));
		corretagemDTO.setTaxa(new BigDecimal(36.9));
		corretagemDTO.setValorFinal(new BigDecimal(0.36));
		corretagemDTO.setValorInicial(new BigDecimal(965));
		dto.getCorretagens().add(corretagemDTO);
		
		Corretora corretora = CorretoraAssembler.fromDTO(dto);
		
		Assert.assertEquals(652l, corretora.getId().longValue());
		Assert.assertEquals(56d, corretora.getCustodia().doubleValue(), 0d);
		Assert.assertEquals("Corretora DTO", corretora.getNome());
		
		Assert.assertEquals(95l, corretora.getEmolumento().getId().longValue());
		Assert.assertEquals(652d, corretora.getEmolumento().getDayTrade().doubleValue(), 0d);
		Assert.assertEquals(21d, corretora.getEmolumento().getOperacaoNomal().doubleValue(), 0d);
		
		for(Corretagem corretagem : corretora.getCorretagens()){
			Assert.assertEquals(25l, corretagem.getId().longValue());
			Assert.assertEquals(958d, corretagem.getFixo().doubleValue(), 0d);
			Assert.assertEquals(36.9d, corretagem.getTaxa().doubleValue(), 0d);
			Assert.assertEquals(0.36d, corretagem.getValorFinal().doubleValue(), 0d);
			Assert.assertEquals(965d, corretagem.getValorInicial().doubleValue(), 0d);
		}
	}
}