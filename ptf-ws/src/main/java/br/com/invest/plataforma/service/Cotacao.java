package br.com.invest.plataforma.service;

import java.io.IOException;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.inject.Inject;

import br.com.investimentos.core.dominio.servico.CotacaoServico;
import yahoofinance.Stock;

@Path("cotacao")
public class Cotacao {

    @Inject
    private CotacaoServico cotacaoServico;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Stock> getCotacoes() throws IOException{
    	return cotacaoServico.getCotacoes();
    }
}