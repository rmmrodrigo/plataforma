package br.com.investimentos.core.aplicacao;

import java.util.Date;

public class DiaServico {
	public static long quantidadeDias(Date dataInicio){
		return DiaServico.quantidadeDias(dataInicio, new Date());
	}
	
	public static long quantidadeDias(Date dataInicio, Date dataFim){
		long q = dataFim.getTime()-dataInicio.getTime();
		
		return q/1000/60/60/24;
	}
}