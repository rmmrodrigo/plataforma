package br.com.investimentos.core.apresentacao.fachada;

import java.io.Serializable;
import java.util.List;

import br.com.investimentos.core.apresentacao.assembler.CorretoraAssembler;
import br.com.investimentos.core.apresentacao.dto.CorretoraDTO;
import br.com.investimentos.core.dominio.DomainStore;
import br.com.investimentos.core.dominio.entidade.Corretora;
import br.com.investimentos.core.dominio.validation.validador.BeanValidator;
import br.com.investimentos.core.dominio.validation.validador.EmptyValidator;
import br.com.investimentos.core.dominio.validation.validador.ValidationException;
import br.com.investimentos.core.infraestrutura.HibernateUtil;
import br.com.investimentos.core.infraestrutura.anotacao.Transacional;

public class CorretoraFachada implements Serializable{
	private static final long serialVersionUID = -6813490719362268516L;
	
	private DomainStore domainStore = new HibernateUtil();

	public List<CorretoraDTO> recuperarCorretoras(){
		List<Corretora> corretoras = (List<Corretora>) domainStore.consultarTodos(Corretora.class);
		return CorretoraAssembler.toDTO(corretoras);
	}
	
	@Transacional
	public void salvar(CorretoraDTO corretoraDTO) throws ValidationException{
		BeanValidator validator = new BeanValidator();
		validator.addPropertyValidator("Nome", corretoraDTO.getNome());
		validator.addPropertyValidator("Custodia", corretoraDTO.getCustodia());
		validator.addPropertyValidator("DayTrade", corretoraDTO.getEmolumentoDTO().getDayTrade());
		validator.addPropertyValidator("Normal", corretoraDTO.getEmolumentoDTO().getOperacaoNomal());
		validator.addPropertyValidator("DayTrade", corretoraDTO.getTaxaLiquidacaoDTO().getDayTrade());
		validator.addPropertyValidator("Normal", corretoraDTO.getTaxaLiquidacaoDTO().getOperacaoNomal());
		validator.addPropertyValidator("Corretagens", corretoraDTO.getCorretagens(), new EmptyValidator());
		validator.validate(corretoraDTO);
		
		Corretora corretora = CorretoraAssembler.fromDTO(corretoraDTO);
		
		if(corretora.getId() == null || corretora.getId().longValue() == 0){
			corretora.getEmolumento().setId(null);
			corretora.getTaxaLiquidacao().setId(null);
			domainStore.salvar(corretora);
		}else{
			domainStore.alterar(corretora);
		}
	}
	
	public CorretoraDTO consultar(Long id){
		Corretora corretora = (Corretora) domainStore.consultarPorId(Corretora.class, id);
		return CorretoraAssembler.toDTO(corretora);
	}
}