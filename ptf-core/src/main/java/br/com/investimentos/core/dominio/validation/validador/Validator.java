package br.com.investimentos.core.dominio.validation.validador;


public interface Validator {
	/**
	 * @param valor
	 * @throws ValidationException
	 */
	public void validate(Object valor) throws ValidationException;
}