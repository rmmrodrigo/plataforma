package br.com.investimentos.core.dominio.validation.validador.invalidation;


public interface Message {
	MessageSeverity getSeverity();
	String getMessage();
}