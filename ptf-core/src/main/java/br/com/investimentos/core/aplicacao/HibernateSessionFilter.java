package br.com.investimentos.core.aplicacao;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import br.com.investimentos.core.infraestrutura.HibernateUtil;

public class HibernateSessionFilter implements Filter {


	/**
	 * Não implementado
	 * */
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try{
			request.setCharacterEncoding("UTF-8");  
			HibernateUtil.openSession();
			chain.doFilter(request, response);
		}finally{
			HibernateUtil.closeSession();
		}
	}

	/**
	 * Não implementado
	 * */
	public void destroy() {
	}
}