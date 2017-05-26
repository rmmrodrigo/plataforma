package br.com.investimentos.core.infraestrutura.aspecto;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class LogIntercepter implements MethodInterceptor{
	public Object invoke(MethodInvocation metodo) throws Throwable {
		System.out.println("---------------------------------"+metodo.getMethod().getDeclaringClass() + "." + metodo.getMethod().getName());
		
		Object retorno = metodo.proceed();

		return retorno;
	}
}