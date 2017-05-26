package br.com.investimentos.core.dominio.entidade;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="ordem_saida")
public class OrdemSaida extends Entidade{
	private static final long serialVersionUID = -9217426737911334204L;

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(nullable=false)
	private Ordem ordemEntrada;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(nullable=false)
	private Ordem ordemSaida;

	public Ordem getOrdemEntrada() {
		return ordemEntrada;
	}

	public void setOrdemEntrada(Ordem ordemEntrada) {
		this.ordemEntrada = ordemEntrada;
	}

	public Ordem getOrdemSaida() {
		return ordemSaida;
	}

	public void setOrdemSaida(Ordem ordemSaida) {
		this.ordemSaida = ordemSaida;
	}
}