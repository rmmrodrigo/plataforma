package br.com.investimentos.core.dominio.validation.validador;

import br.com.investimentos.core.dominio.validation.validador.invalidation.InvalidationResult;

public class ValidationException extends Exception{
	private static final long serialVersionUID = 1L;
	private InvalidationResult invalidation;
	private Object[] params;
	
	public ValidationException(InvalidationResult invalidation) {
		this.invalidation = invalidation;
	}
	
	public ValidationException(String message) {
		super(message);
	}
	
	public ValidationException(String message, Object... params) {
		//TODO adicionar metodo estatico para substituir os parametros na mensagem
		this(message);
		this.params = params;
	}
	
	public InvalidationResult getInvalidation() {
		return invalidation;
	}
}