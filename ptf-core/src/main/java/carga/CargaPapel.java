package carga;

import java.math.BigDecimal;

import br.com.investimentos.core.dominio.DomainStore;
import br.com.investimentos.core.dominio.entidade.Corretagem;
import br.com.investimentos.core.dominio.entidade.Corretora;
import br.com.investimentos.core.dominio.entidade.Emolumento;
import br.com.investimentos.core.dominio.entidade.Papel;
import br.com.investimentos.core.dominio.entidade.TaxaLiquidacao;
import br.com.investimentos.core.infraestrutura.HibernateUtil;

public class CargaPapel {
	public static void main(String[] args) {
		HibernateUtil.openSession();
		HibernateUtil.getSession().beginTransaction();

		DomainStore d = new HibernateUtil();

		Papel papel = new Papel();
		papel.setCodigo("VALE5");
		papel.setDescricao("Vale do Rio Doce");
		d.salvar(papel);

		papel = new Papel();
		papel.setCodigo("GGBR4");
		papel.setDescricao("Gerdal");
		d.salvar(papel);

		Corretora corretora = new Corretora();
		corretora.setNome("Citi");
		corretora.setCustodia(new BigDecimal(0));
		corretora.setEmolumento(new Emolumento());
		corretora.getEmolumento().setCorretora(corretora);
		corretora.getEmolumento().setDayTrade(new BigDecimal(0.0070));
		corretora.getEmolumento().setOperacaoNomal(new BigDecimal(0.0070));
		corretora.setTaxaLiquidacao(new TaxaLiquidacao());
		corretora.getTaxaLiquidacao().setCorretora(corretora);
		corretora.getTaxaLiquidacao().setDayTrade(new BigDecimal(0.0180));
		corretora.getTaxaLiquidacao().setOperacaoNomal(new BigDecimal(1));

		Corretagem c = new Corretagem();
		c.setAtiva(Boolean.TRUE);
		c.setCorretora(corretora);
		c.setValorInicial(new BigDecimal(0));
		c.setValorFinal(new BigDecimal(135.07));
		c.setTaxa(new BigDecimal(1));
		c.setFixo(new BigDecimal(2.70));
		corretora.getCorretagens().add(c);
		
		c = new Corretagem();
		c.setAtiva(Boolean.TRUE);
		c.setCorretora(corretora);
		c.setValorInicial(new BigDecimal(135.08));
		c.setValorFinal(new BigDecimal(498.62));
		c.setTaxa(new BigDecimal(2));
		c.setFixo(new BigDecimal(0));
		corretora.getCorretagens().add(c);
		
		c = new Corretagem();
		c.setAtiva(Boolean.TRUE);
		c.setCorretora(corretora);
		c.setValorInicial(new BigDecimal(498.63));
		c.setValorFinal(new BigDecimal(1514.69));
		c.setTaxa(new BigDecimal(1.5));
		c.setFixo(new BigDecimal(2.49));
		corretora.getCorretagens().add(c);
		
		c = new Corretagem();
		c.setAtiva(Boolean.TRUE);
		c.setCorretora(corretora);
		c.setValorInicial(new BigDecimal(1514.70));
		c.setValorFinal(new BigDecimal(3029.38));
		c.setTaxa(new BigDecimal(1));
		c.setFixo(new BigDecimal(10.06));
		corretora.getCorretagens().add(c);
		
		c = new Corretagem();
		c.setAtiva(Boolean.TRUE);
		c.setCorretora(corretora);
		c.setValorInicial(new BigDecimal(3029.38));
		c.setValorFinal(null);
		c.setTaxa(new BigDecimal(0.5));
		c.setFixo(new BigDecimal(25.21));
		corretora.getCorretagens().add(c);
		d.salvar(corretora);

		
		/*Ordem o = (Ordem) d.consultarPorId(Ordem.class, 301L);
		d.excluir(o);*/
		
		HibernateUtil.getSession().getTransaction().commit();
		HibernateUtil.closeSession();
		
		System.out.println("FIM---------------------");
	}
}