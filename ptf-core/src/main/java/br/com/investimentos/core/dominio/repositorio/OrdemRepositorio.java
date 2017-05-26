package br.com.investimentos.core.dominio.repositorio;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.com.investimentos.core.dominio.DomainStore;
import br.com.investimentos.core.dominio.entidade.Corretora;
import br.com.investimentos.core.dominio.entidade.Ordem;
import br.com.investimentos.core.dominio.entidade.Papel;
import br.com.investimentos.core.dominio.entidade.enuns.TipoOperacao;
import br.com.investimentos.core.infraestrutura.HibernateUtil;

public class OrdemRepositorio {
	private DomainStore domainStore = new HibernateUtil();

	public List<Date> recuperarDatas(){
		/*Criteria criteria = domainStore.criarCriterio(Ordem.class);
		criteria.setProjection(Projections.groupProperty("data"));
		return criteria.list();*/
		
		return null;
	}

	public List<Ordem> recuperarPorData(Date data, Corretora corretora){
		/*Criteria criteria = domainStore.criarCriterio(Ordem.class);
		
		criteria.add(Restrictions.eq("corretora", corretora));
		criteria.add(Restrictions.eq("data", data));

		return criteria.list();*/
		
		return null;
	}

	public List<Ordem> recuperarSemSaida(Papel papel, TipoOperacao tipoOperacao, Corretora corretora){
		/*Criteria criteria = domainStore.criarCriterio(Ordem.class);
		//criteria.createAlias("ordemSaida", "saida", JoinType.LEFT_OUTER_JOIN);

		criteria.add(Restrictions.eq("corretora", corretora));
		criteria.add(Restrictions.eq("papel", papel));
		criteria.add(Restrictions.eq("tipoOperacao", tipoOperacao));
		criteria.add(Restrictions.gt("quantidade", 0));
		/*criteria.add(Restrictions.or(
						Restrictions.isNull("saida.quantidade"),
						Restrictions.gtProperty("quantidade", "saida.quantidade"))
				);*
		criteria.addOrder(Order.asc("data"));

		return criteria.list();*/
		
		return null;
	}

	public List<Ordem> recuperar(Papel papel, TipoOperacao tipoOperacao, Corretora corretora) {
		return recuperar(papel, tipoOperacao, null, corretora);
	}
	
	public List<Ordem> recuperar(TipoOperacao tipoOperacao, Corretora corretora) {
		return recuperar(null, tipoOperacao, corretora);
	}
	
	public List<Ordem> recuperar(Papel papel, TipoOperacao tipoOperacao, Date menorQue, Corretora corretora) {
		/*Criteria criteria = domainStore.criarCriterio(Ordem.class);
		if(papel != null){
			criteria.add(Restrictions.eq("papel", papel));
		}
		if(menorQue != null){
			criteria.add(Restrictions.le("data", menorQue));
		}
		criteria.add(Restrictions.eq("tipoOperacao", tipoOperacao));
		criteria.add(Restrictions.eq("corretora", corretora));
		criteria.addOrder(Order.asc("data"));

		return criteria.list();*/
		
		return null;
	}

	public List<Papel> recuperarPapeis(Corretora corretora) {
		/*Criteria criteria = domainStore.criarCriterio(Ordem.class);

		criteria.setProjection(Projections.distinct(Projections.property("papel")));
		criteria.add(Restrictions.eq("corretora", corretora));
		
		return criteria.list();*/
		
		return null;
	}
}