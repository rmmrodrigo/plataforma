package br.com.investimentos.core.dominio.validation.validador;

public class NullValidator implements Validator{
	public static final Validator NULL_VALIDATOR = new NullValidator();

	public void validate(Object valor) throws ValidationException{
		if(valor == null){
			throw new ValidationException("notNullValidator.validate");
		}
	}
}