package br.com.investimentos.core.dominio.repositorio;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.investimentos.core.dominio.DomainStore;
import br.com.investimentos.core.dominio.entidade.Usuario;
import br.com.investimentos.core.infraestrutura.HibernateUtil;

public class UsuarioRepositorio {
	private DomainStore domainStore = new HibernateUtil();

	public Usuario recuperarUsuario(String usuario, String senha){
		/*Criteria criteria = domainStore.criarCriterio(Usuario.class);
		
		criteria.add(Restrictions.eq("usuario", usuario));
		criteria.add(Restrictions.eq("senha", senha));
		
		return (Usuario) criteria.uniqueResult();*/
		
		return null;
	}
}