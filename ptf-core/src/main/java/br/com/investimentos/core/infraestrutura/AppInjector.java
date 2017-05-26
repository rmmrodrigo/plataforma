package br.com.investimentos.core.infraestrutura;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

import br.com.investimentos.core.apresentacao.fachada.CorretoraFachada;
import br.com.investimentos.core.infraestrutura.anotacao.Transacional;
import br.com.investimentos.core.infraestrutura.aspecto.TransacaoIntercepter;

public class AppInjector extends AbstractModule {
		 
	    @Override
	    protected void configure() {
	        //bind the service to implementation class
	        //bind(MessageService.class).to(EmailService.class);
	         
	        //bind MessageService to Facebook Message implementation
	        bind(CorretoraFachada.class);
	        bindInterceptor(Matchers.any(), Matchers.annotatedWith(Transacional.class), new TransacaoIntercepter());
	         
	    }
}