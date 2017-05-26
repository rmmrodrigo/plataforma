package br.com.investimentos.core.dominio.validation.validador.invalidation.exception;

import br.com.investimentos.core.dominio.validation.validador.invalidation.AbstractInvalidationException;
import br.com.investimentos.core.dominio.validation.validador.invalidation.InvalidationResult;


public class InvalidationException extends AbstractInvalidationException{
	private static final long serialVersionUID = 4687579621748262904L;
	
	public InvalidationException(InvalidationResult result){
		super("A validação falhou. Verifique as mensagens de erro.", result);
	}
}