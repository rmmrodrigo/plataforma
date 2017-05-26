package br.com.investimentos.core.infraestrutura.aspecto;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import br.com.investimentos.core.infraestrutura.HibernateUtil;

public class TransacaoIntercepter implements MethodInterceptor{
	private Logger logger = LogManager.getLogger(TransacaoIntercepter.class);

	public Object invoke(MethodInvocation metodo) throws Throwable {
		Object retorno = null;

		if(HibernateUtil.getSession().getTransaction().getStatus() == TransactionStatus.NOT_ACTIVE)
			HibernateUtil.getSession().beginTransaction();
		try{
			retorno = metodo.proceed();
			HibernateUtil.getSession().getTransaction().commit();
		}catch(Throwable e){
			logger.error("Erro ao realizar operacao: "+metodo.getMethod().getName(), e);
			HibernateUtil.getSession().getTransaction().rollback();
			throw e;
		}

		return retorno;
	}
}