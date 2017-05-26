package br.com.investimentos.core.dominio.validation.validador;

public class NumberMinValidator implements Validator{
	private final Number minValor;

	public NumberMinValidator(Number minValor){
		this.minValor = minValor;
	}
	
	public void validate(Object valor) throws ValidationException{
		NullValidator.NULL_VALIDATOR.validate(valor);

		Number valorNum = (Number) valor;
		
		if (valorNum.doubleValue() < minValor.doubleValue()){
			throw new ValidationException("numero.min", minValor);
		}
	}
}          