package util;

import java.io.IOException;
import java.sql.SQLException;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.ext.hsqldb.HsqldbDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;
import org.junit.Before;
import org.junit.BeforeClass;

import br.com.investimentos.core.infraestrutura.HibernateUtil;

public class AbstractDbUnitJpaTest {
	private static IDatabaseConnection connection;
	protected static Session session;
	
	@BeforeClass
	public static void initEntityManager() throws HibernateException, DatabaseUnitException {
		session = HibernateUtil.getSessionFactory().openSession();
		
		connection = new DatabaseConnection(((SessionImpl)session).connection());
		connection.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new HsqldbDataTypeFactory());
	}
	
	@Before
	public void cleanDB() throws IOException, DataSetException, DatabaseUnitException, SQLException {
		DatabaseOperation.CLEAN_INSERT.execute(connection, new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("data/Papel.xml")));
	}
}
