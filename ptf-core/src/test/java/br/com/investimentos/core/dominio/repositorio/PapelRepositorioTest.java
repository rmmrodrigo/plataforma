package br.com.investimentos.core.dominio.repositorio;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.junit.Assert;
import org.junit.Test;

import br.com.investimentos.core.dominio.repositorio.PapelRepositorio;
import util.HibernateDbUnitTestCase;

public class PapelRepositorioTest extends HibernateDbUnitTestCase {
	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("data/Papel.xml"));
	}
	
	 @Test
	 public void testRecuperarPapel(){
		 PapelRepositorio repositorio = new PapelRepositorio();
		 Assert.assertNotNull(repositorio.recuperarPapel("PETR3"));
	 }
}