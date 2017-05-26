package br.com.investimentos.core.dominio.validation.validador.invalidation;

import br.com.investimentos.core.dominio.validation.validador.invalidation.message.StringMessage;

public class FactoryMessage {
	public static Message message(MessageSeverity severity, String message){
		return new StringMessage(severity, message);
	}
	
	public static Message message(MessageSeverity severity, String message, Object... params){
		return new StringMessage(severity, message, params);
	}
}