package br.com.investimentos.core.dominio.validation.validador;



import java.util.Date;


public class DateIntervalValidator implements Validator{
	private final Date minDate;
	private final Date maxDate;

	/**
	 * Informa a data inicio e data fim em que a data de validacao deve estar.
	 * 
	 * @param minDate - data inicio
	 * @param maxDate - data fim
	 */
	public DateIntervalValidator(Date minDate, Date maxDate){
		this.minDate = minDate;
		this.maxDate = maxDate;
	}
	
	public void validate(Object date) throws ValidationException{
		NullValidator.NULL_VALIDATOR.validate(date);

		Date data = (Date) date;
		
		if (data.before(minDate)){
			throw new ValidationException("date.outofrange.min", minDate);
		}

		if (data.after(maxDate) ){
			throw new ValidationException("date.outofrange.max", maxDate);
		}
	}
}          