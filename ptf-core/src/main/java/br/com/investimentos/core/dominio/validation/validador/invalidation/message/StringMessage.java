package br.com.investimentos.core.dominio.validation.validador.invalidation.message;

import java.text.MessageFormat;
import java.util.Properties;

import br.com.investimentos.core.dominio.validation.validador.invalidation.Message;
import br.com.investimentos.core.dominio.validation.validador.invalidation.MessageSeverity;



public class StringMessage implements Message{
	private static Properties PROPERTIES = null;
	
	private MessageSeverity severity;
	private String message;
	
	static{
		if(PROPERTIES == null){
			PROPERTIES = new Properties();
			
			PROPERTIES.put("ckeckValidator.validate", "Valor Invalido. O valor [{0}] nao corresponde a expressao [{1}]");
			PROPERTIES.put("ckeckValidator.scriptException", "Expressao Invalida");
			PROPERTIES.put("dateIntervalValidator.mim.validate", "Data minima fora do intervalo");
			PROPERTIES.put("dateIntervalValidator.max.validate", "Data maxima fora do intervalo");
			PROPERTIES.put("emptyValidator.validate", "{0}.Valor Vazio");
			PROPERTIES.put("notEmptyValidator.validate", "{0}.Valor Vazio");
			PROPERTIES.put("nullValidator.validate", "{0}. Valor nao Nulo");
			PROPERTIES.put("notNullValidator.validate", "{0}.Valor Nulo");
			PROPERTIES.put("builPreCondition.indexOutOfBound", "Parametro nao encontrado");
			PROPERTIES.put("numero.min", "{0}. Valor deve ser maior que {1}");
		}
	}
	
	
	public StringMessage(MessageSeverity severity, String message) {
		this.severity = severity;
		this.message = PROPERTIES.getProperty(message);
	}
	
	public StringMessage(MessageSeverity severity, String message, Object... params) {
		this(severity, message);
		this.message = MessageFormat.format(this.message, params);
	}

	public MessageSeverity getSeverity() {
		return severity;
	}

	public String getMessage() {
		return message;
	}
}