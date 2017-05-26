package br.com.investimentos.core.infraestrutura;

import java.io.Serializable;
import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;

import br.com.investimentos.core.dominio.DomainStore;

public class HibernateUtil implements DomainStore{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6972418839033386795L;
	private static SessionFactory factory;
	private static Session session;



	public static SessionFactory getSessionFactory(){
		if(factory == null){
			Configuration configuration = new Configuration();
			configuration.configure();
			factory = configuration.buildSessionFactory();
		}
		
		return factory;  
	}

	public static void openSession(){
		if(session == null || !session.isOpen()){
			session = getSessionFactory().openSession();
		}
	}

	public static Session getSession(){
		return session;
	}

	public static void evic(Object object){
		getSession().evict(object);
	}

	public static void closeSession() {
		synchronized (HibernateUtil.class) {
			if(getSession() != null && getSession().isOpen()){
				getSession().close();
			}
		}
	}

	public Object salvar(Object object) {
		return getSession().save(object);
	}

	public void salvarAtualizar(Object object) {
		getSession().saveOrUpdate(object);
	}

	public void alterar(Object object) {
		getSession().update(object);
	}

	public void excluir(Object object) {
		getSession().delete(object);
	}

	public List<?> consultar(Criteria criteria) {
		return criteria.list();
	}

	public Object consultarPorId(Class<?> classe, Object id) {
		return getSession().get(classe, (Serializable) id);
	}

	public <T> HibernateCriteriaBuilder criarCriterio(Class<T> classe) {
		HibernateCriteriaBuilder hc = session.unwrap(HibernateCriteriaBuilder.class);
		CriteriaQuery<T> query = hc.createQuery(classe);
		Root<T> root = query.from(classe);
        query.select(root);
        return hc;
		
		
		
		/*CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<T> query = cb.createQuery(classe);
		Root<T> root = query.from(classe);
        query.select(root);
        //query.select( root ).where( cb.eq( root.get( "codigo" ), curso.getCodigo() ) );
		return query;*/
	}

	public List<?> consultarTodos(Class<?> classe) {
		/*CriteriaQuery<?> cQuery = this.criarCriterio(classe);
		Query query = getSession().createQuery(cQuery);
		return query.getResultList();*/
		
		return null;
	}
}