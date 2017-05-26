package br.com.investimentos.core.dominio;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;

public interface DomainStore extends Serializable{
	public Object salvar(Object object);
	public void alterar(Object object);
	public void excluir(Object object);
	public List<?> consultar(Criteria criteria);
	public List<?> consultarTodos(Class<?> classe);
	public Object consultarPorId(Class<?> classe, Object id);
	public <T> HibernateCriteriaBuilder criarCriterio(Class<T> classe);
}