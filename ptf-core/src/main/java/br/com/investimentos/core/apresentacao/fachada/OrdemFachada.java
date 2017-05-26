package br.com.investimentos.core.apresentacao.fachada;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import br.com.investimentos.core.apresentacao.assembler.CorretoraAssembler;
import br.com.investimentos.core.apresentacao.assembler.OrdemAssembler;
import br.com.investimentos.core.apresentacao.assembler.PapelAssembler;
import br.com.investimentos.core.apresentacao.dto.CorretoraDTO;
import br.com.investimentos.core.apresentacao.dto.OrdemDTO;
import br.com.investimentos.core.apresentacao.dto.PapelDTO;
import br.com.investimentos.core.dominio.DomainStore;
import br.com.investimentos.core.dominio.entidade.Corretora;
import br.com.investimentos.core.dominio.entidade.Ordem;
import br.com.investimentos.core.dominio.entidade.Papel;
import br.com.investimentos.core.dominio.repositorio.OrdemRepositorio;
import br.com.investimentos.core.dominio.repositorio.PapelRepositorio;
import br.com.investimentos.core.dominio.servico.CorretagemServico;
import br.com.investimentos.core.dominio.validation.validador.BeanValidator;
import br.com.investimentos.core.dominio.validation.validador.CompositeValidator;
import br.com.investimentos.core.dominio.validation.validador.NullValidator;
import br.com.investimentos.core.dominio.validation.validador.NumberMinValidator;
import br.com.investimentos.core.dominio.validation.validador.ValidationException;
import br.com.investimentos.core.infraestrutura.HibernateUtil;
import br.com.investimentos.core.infraestrutura.anotacao.Transacional;

public class OrdemFachada implements Serializable{
	private static final long serialVersionUID = 1257385759853388370L;

	private DomainStore domainStore = new HibernateUtil();
	private OrdemRepositorio ordemRepositorio = new OrdemRepositorio();
	private PapelRepositorio papelRepositorio = new PapelRepositorio();

	public List<OrdemDTO> recuperarOrdens(){
		List<Date> datas = ordemRepositorio.recuperarDatas();
		List<Corretora> corretoras = (List<Corretora>) domainStore.consultarTodos(Corretora.class);

		List<OrdemDTO> ordens = new ArrayList<OrdemDTO>();
		for(Corretora corretora : corretoras){
			for (Date data : datas) {
				List<OrdemDTO> ordensDto = OrdemAssembler.toDTO(ordemRepositorio.recuperarPorData(data, corretora));
				ordens.addAll(calcularOrdens(ordensDto));
			}
		}

		ordens.sort(new Comparator<OrdemDTO>() {
			public int compare(OrdemDTO o1, OrdemDTO o2) {
				return o1.getPapelDTO().getCodigo().compareTo(o2.getPapelDTO().getCodigo());
			}
		});

		return ordens;
	}

	private List<? extends OrdemDTO> calcularOrdens(List<? extends OrdemDTO> ordensDto) {
		BigDecimal valorTotalOrdensDia = getValorTotalOrdens(ordensDto);

		for (OrdemDTO ordemDTO : ordensDto) {
			ordemDTO.calcularOrdem(valorTotalOrdensDia);
		}

		return ordensDto;
	}

	private BigDecimal getValorTotalOrdens(List<? extends OrdemDTO> ordensDto) {
		BigDecimal totalOrdem = BigDecimal.ZERO;

		for (OrdemDTO ordemDTO : ordensDto) {
			totalOrdem = totalOrdem.add(ordemDTO.getValorTotalOrdem());
		}
		return totalOrdem;
	}

	@Transacional
	public void salvar(OrdemDTO ordemDTO) throws ValidationException{
		CompositeValidator c = new CompositeValidator();
		c.add(new NullValidator());
		c.add(new NumberMinValidator(1));

		BeanValidator validator = new BeanValidator();
		validator.addPropertyValidator("Ativo", ordemDTO.getPapelDTO());
		if(ordemDTO.getPapelDTO() != null){
			validator.addPropertyValidator("Ativo", ordemDTO.getPapelDTO().getCodigo());
		}
		validator.addPropertyValidator("Operação", ordemDTO.getTipoOperacao());
		validator.addPropertyValidator("Quantidade", ordemDTO.getQuantidade(), c);
		validator.addPropertyValidator("Valor", ordemDTO.getValor());
		validator.addPropertyValidator("Data", ordemDTO.getData());
		validator.addPropertyValidator("Corretora", ordemDTO.getCorretoraDTO());
		validator.validate(ordemDTO);

		List<Ordem> ordens = ordemRepositorio.recuperarPorData(ordemDTO.getData(), CorretoraAssembler.fromDTO(ordemDTO.getCorretoraDTO()));

		List<OrdemDTO> ordensDto = OrdemAssembler.toDTO(ordemRepositorio.recuperarPorData(ordemDTO.getData(), CorretoraAssembler.fromDTO(ordemDTO.getCorretoraDTO())));
		BigDecimal totalOrdem = getValorTotalOrdens(ordensDto);
		totalOrdem = totalOrdem.add(ordemDTO.getValorTotalOrdem());

		ordemDTO.setCorretoraDTO(CorretoraAssembler.toDTO((Corretora) domainStore.consultarPorId(Corretora.class, ordemDTO.getCorretoraDTO().getId())));
		ordemDTO.setCorretagemDTO(CorretagemServico.getCorretagem(totalOrdem, ordemDTO.getCorretoraDTO().getCorretagens()));
		ordemDTO.setEmolumentoDTO(ordemDTO.getCorretoraDTO().getEmolumentoDTO());
		ordemDTO.setTaxaLiquidacaoDTO(ordemDTO.getCorretoraDTO().getTaxaLiquidacaoDTO());

		Ordem ordem = OrdemAssembler.fromDTO(ordemDTO);

		for (Ordem ord : ordens) {
			if((ord.getId() != null && ordem.getId() != null) && (ord.getId().longValue() == ordem.getId().longValue())){
				HibernateUtil.evic(ord);
				continue;
			}

			ord.setCorretagem(ordem.getCorretagem());
			domainStore.alterar(ord);
		}

		if(ordem.getId() == null || ordem.getId().longValue() == 0){
			domainStore.salvar(ordem);
		}else{
			domainStore.alterar(ordem);
		}
	}

	public List<PapelDTO> recuperarPapeis(String codigo) {
		List<Papel> papeis = papelRepositorio.recuperarPapel(codigo);

		return PapelAssembler.toDTO(papeis);
	}


	public OrdemDTO consultarPorId(Long id) {
		Ordem ordem = (Ordem) domainStore.consultarPorId(Ordem.class, id);
		return OrdemAssembler.toDTO(ordem);
	}

	/*@Transacional
	public void excluir(OperacaoAcaoDTO operacaoAcaoDTO){
		OperacaoAcao operacaoAcao = OperacaoAcaoAssembler.fromDTO(operacaoAcaoDTO);

		domainStore.excluir(operacaoAcao);
	}*/
}