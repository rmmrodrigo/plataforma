package br.com.investimentos.core.dominio.validation.validador.invalidation;

import br.com.investimentos.core.dominio.validation.validador.invalidation.exception.InvalidationException;

public interface Invalidator<T> {
	/**
	 * @param t
	 * @throws InvalidationException
	 */
	public void validate(T t) throws InvalidationException;
}