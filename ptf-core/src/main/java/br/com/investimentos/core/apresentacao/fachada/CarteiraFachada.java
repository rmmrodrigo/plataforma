package br.com.investimentos.core.apresentacao.fachada;

import java.io.Serializable;

import com.google.inject.Inject;

import br.com.investimentos.core.apresentacao.dto.Carteira;
import br.com.investimentos.core.apresentacao.dto.CorretoraDTO;
import br.com.investimentos.core.dominio.DomainStore;
import br.com.investimentos.core.dominio.repositorio.OrdemRepositorio;

public class CarteiraFachada implements Serializable{
	private static final long serialVersionUID = 4974850999940876890L;
	@Inject
	private DomainStore domainStore;
	private OrdemRepositorio ordemRepositorio = new OrdemRepositorio();

	public Carteira recuperarCarteira(CorretoraDTO corretoraDTO) {
		Carteira carteira = new Carteira(ordemRepositorio, corretoraDTO);
		carteira.montarCarteira();
		return carteira;
	}
}