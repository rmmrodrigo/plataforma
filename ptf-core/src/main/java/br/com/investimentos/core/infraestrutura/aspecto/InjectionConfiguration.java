package br.com.investimentos.core.infraestrutura.aspecto;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.matcher.Matchers;

import br.com.investimentos.core.dominio.DomainStore;
import br.com.investimentos.core.infraestrutura.HibernateUtil;
import br.com.investimentos.core.infraestrutura.anotacao.Transacional;

public class InjectionConfiguration extends AbstractModule{
	@Override
	protected void configure() {
		bind(DomainStore.class).to(HibernateUtil.class).in(Singleton.class);
		
		bindListener(Matchers.any(), new Log4JListener());
		
		bindInterceptor(Matchers.inSubpackage("br.com.investimento.aplicacao"), Matchers.any(), new LogIntercepter());
		bindInterceptor(Matchers.inSubpackage("br.com.investimento.apresentacao"), Matchers.any(), new LogIntercepter());
		bindInterceptor(Matchers.inSubpackage("br.com.investimento.dominio"), Matchers.any(), new LogIntercepter());
		bindInterceptor(Matchers.inSubpackage("br.com.investimento.infraestrutura.cotacao"), Matchers.any(), new LogIntercepter());
		bindInterceptor(Matchers.any(), Matchers.annotatedWith(Transacional.class), new TransacaoIntercepter());
	}
}