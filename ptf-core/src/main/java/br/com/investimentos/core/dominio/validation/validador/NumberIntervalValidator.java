package br.com.investimentos.core.dominio.validation.validador;

public class NumberIntervalValidator implements Validator{
	private final Number minValor;
	private final Number maxValor;

	public NumberIntervalValidator(Number minValor, Number maxValor){
		this.minValor = minValor;
		this.maxValor = maxValor;
	}
	
	public void validate(Object valor) throws ValidationException{
		NullValidator.NULL_VALIDATOR.validate(valor);

		Number valorNum = (Number) valor;
		
		if (valorNum.doubleValue() < minValor.doubleValue()){
			throw new ValidationException("date.outofrange.min", minValor);
		}

		if (valorNum.doubleValue() > maxValor.doubleValue()){
			throw new ValidationException("date.outofrange.max", maxValor);
		}
	}
}          