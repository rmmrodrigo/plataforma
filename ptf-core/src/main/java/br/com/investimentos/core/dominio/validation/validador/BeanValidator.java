package br.com.investimentos.core.dominio.validation.validador;


import java.util.ArrayList;
import java.util.List;

import br.com.investimentos.core.dominio.validation.validador.invalidation.InvalidationResult;
import br.com.investimentos.core.dominio.validation.validador.invalidation.MessageSeverity;
import br.com.investimentos.core.dominio.validation.validador.invalidation.message.StringMessage;



public class BeanValidator implements Validator {
	private final List<ValidatorProperty> validators = new ArrayList<ValidatorProperty>();

	public BeanValidator addPropertyValidator(String campo, Object valor, Validator validator){
		validators.add(new ValidatorProperty(campo, valor, validator));
		return this;
	}
	
	public BeanValidator addPropertyValidator(String campo, Object valor){
		validators.add(new ValidatorProperty(campo, valor, new NullValidator()));
		return this;
	}


	public void validate (Object bean) throws ValidationException{
		InvalidationResult invalidation = new InvalidationResult();
		
		for(ValidatorProperty validator:this.validators){
			try{
				validator.validator.validate(validator.valor);
			}catch(ValidationException v){
				invalidation.addReason(new StringMessage(MessageSeverity.ERROR, v.getMessage(), validator.campo));
			}
		}
		
		if(invalidation.hasMessage()){
			throw new ValidationException(invalidation);
		}
	}
	
	private class ValidatorProperty{
		private String campo;
		private Object valor;
		private Validator validator;

		public ValidatorProperty(String campo, Object valor, Validator validator) {
			this.campo = campo;
			this.valor = valor;
			this.validator = validator;
		}
	}
}