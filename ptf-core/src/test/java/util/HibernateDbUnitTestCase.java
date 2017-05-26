package util;

import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;

import br.com.investimentos.core.infraestrutura.HibernateUtil;  
  
public abstract class HibernateDbUnitTestCase extends DBTestCase {  
  
    private static SessionFactory sessionFactory;  
    protected Session session;  
  
    /** 
     * system properties initializing constructor. 
     */  
    public HibernateDbUnitTestCase() {  
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "org.hsqldb.jdbcDriver");  
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:hsqldb:mem:Plataforma");  
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "sa");  
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "");  
    }  
  
    /** 
     * Start the server. 
     * @throws Exception in case of startup failure. 
     */  
    @Before  
    public void setUp() throws Exception {  
        if (sessionFactory == null) {  
            sessionFactory = HibernateUtil.getSessionFactory();  
        }  
  
        session = sessionFactory.openSession();  
  
        super.setUp();  
    }  
  
    /** 
     * shutdown the server. 
     * @throws Exception in case of errors. 
     */  
    @After  
    public void tearDown() throws Exception {  
        session.close();  
        super.tearDown();  
    }  
  
    protected abstract IDataSet getDataSet() throws Exception;
  
    protected DatabaseOperation getSetUpOperation() throws Exception {  
        return DatabaseOperation.REFRESH;  
    }  
  
    protected DatabaseOperation getTearDownOperation() throws Exception {  
        return DatabaseOperation.NONE;  
    }  
}  