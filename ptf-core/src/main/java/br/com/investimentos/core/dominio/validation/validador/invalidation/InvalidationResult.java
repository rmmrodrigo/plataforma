package br.com.investimentos.core.dominio.validation.validador.invalidation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.com.investimentos.core.dominio.validation.validador.invalidation.exception.InvalidationException;



public class InvalidationResult implements Iterable<Message>{
	private List<Message> messages = new ArrayList<Message>();
	
	public boolean hasMessage() throws InvalidationException{
		return !messages.isEmpty();
	}

	public Iterator<Message> iterator() {
		return this.messages.iterator();
	}

	public void addReason(Message message){
		this.messages.add(message);
	}
	
	public List<Message> getMessages() {
		return messages;
	}
	
	public List<Message> getMessages(MessageSeverity severity){
		List<Message> lista = new ArrayList<Message>();
		
		for(Message message : messages){
			if(message.getSeverity() == severity){
				lista.add(message);
			}
		}
		
		return lista;
	}
}