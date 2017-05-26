package br.com.investimentos.core.dominio.validation.validador;

import java.util.ArrayList;
import java.util.List;

public class AgrupadorValidator{
	private List<Object> valores = new ArrayList<Object>();
	private List<Validator> validadores = new ArrayList<Validator>();

	public AgrupadorValidator(Validator validador){
		this.validadores.add(validador);
	}

	public AgrupadorValidator adicionarValidador(Validator validador){
		this.validadores.add(validador);
		return this;
	}
	
	
	public AgrupadorValidator adicionarValor(Object valor){
		valores.add(valor);
		return this;
	}

	public void validate() throws ValidationException {
		for(Validator validator : this.validadores){
			for(Object valor : valores){
				validator.validate(valor);
			}
		}
	}
}
