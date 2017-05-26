package br.com.investimentos.core.dominio.repositorio;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.investimentos.core.dominio.DomainStore;
import br.com.investimentos.core.dominio.entidade.Papel;
import br.com.investimentos.core.infraestrutura.HibernateUtil;

public class PapelRepositorio {
	private DomainStore domainStore = new HibernateUtil();

	public List<Papel> recuperarPapel(String codigo){
		try{
			HibernateUtil.openSession();
			CriteriaBuilder cb = HibernateUtil.getSession().getCriteriaBuilder();
			CriteriaQuery<Papel> query = cb.createQuery(Papel.class);
			Root<Papel> root = query.from(Papel.class);
			query.select(root);
			//query.where(cb.equal(root.get( "codigo" ), codigo));
			query.where(cb.like(root.get("codigo").as(String.class), codigo+"%"));
			return HibernateUtil.getSession().createQuery(query).getResultList();
		}finally {
			HibernateUtil.closeSession();
		}

		/*Criteria criteria = domainStore.criarCriterio(Papel.class);
		criteria.add(Restrictions.ilike("codigo", codigo, MatchMode.START));
		return (List<Papel>) criteria.list();*/
	}	
}