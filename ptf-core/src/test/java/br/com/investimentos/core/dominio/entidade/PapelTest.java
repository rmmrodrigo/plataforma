package br.com.investimentos.core.dominio.entidade;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Test;

import util.AbstractDbUnitJpaTest;  
  
public class PapelTest extends AbstractDbUnitJpaTest {  
  
  
    protected IDataSet getDataSet() throws Exception {  
        return new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("data/Papel.xml"));
    }  
  
    @Test  
    public void a() {  
        Transaction trans = session.beginTransaction();
        CriteriaQuery<Papel> query = session.getCriteriaBuilder().createQuery(Papel.class);
        query.select(query.from(Papel.class));
        Query q = session.createQuery(query);
        Assert.assertEquals(3, q.getResultList().size());  
        trans.commit();  
    }  
  
    @Test  
    public void b() {  
        Transaction trans = session.beginTransaction();  
        Assert.assertEquals("Petrobras 4", ((Papel) session.get(Papel.class, "PETR4")).getDescricao());  
        trans.commit();  
    }  
  
}  