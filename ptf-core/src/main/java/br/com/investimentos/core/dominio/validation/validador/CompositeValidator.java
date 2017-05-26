package br.com.investimentos.core.dominio.validation.validador;


import java.util.ArrayList;
import java.util.List;



public class CompositeValidator implements Validator{
	private final List<Validator> validators = new ArrayList<Validator>();

	/**
	 * Adiciona os validadores que serao utilizados.
	 * 
	 * @param validator - Validador
	 * @return CompositeValidator com a propriedade e validador adicionado.
	 */
	public CompositeValidator add(Validator validator){
		validators.add(validator);
		return this;
	}
	
	public void validate(Object valor) throws ValidationException {
		for(Validator validator : validators){
			validator.validate(valor);
		}
	}
}