package br.com.investimentos.core.dominio.validation.validador;
import java.util.Collection;


public class EmptyValidator implements Validator{
	public static final Validator EMPTY_VALIDATOR = new EmptyValidator();
	
	public void validate(Object valor) throws ValidationException {
		if(valor.getClass() == String.class){
			if(valor == null || ((String)valor).trim().isEmpty()){
				throw new ValidationException("emptyValidator.validate");
			}
		}
		
		if(((Collection)valor).isEmpty()){
			throw new ValidationException("emptyValidator.validate");
		}
	}
}