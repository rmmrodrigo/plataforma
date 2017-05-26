package br.com.investimentos.core.dominio.validation.validador.invalidation;

import java.util.Iterator;

public abstract class AbstractInvalidationException extends RuntimeException implements Iterable<Message>{
	private static final long serialVersionUID = 1135856834437478365L;
	private InvalidationResult result;
	
	protected AbstractInvalidationException(String mensagem, InvalidationResult result){
		super(mensagem);
		this.result = result;
	}
	
	public InvalidationResult getValidationResult() {
		return result;
	}

	public Iterator<Message> iterator() {
		return this.result.iterator();
	}
}