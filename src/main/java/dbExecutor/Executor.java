package dbExecutor;

import dataSets.*; // почему при import dataSets.UsersDataSet ошибка,а так ошибки нет dataSets.*?
import interfaces.DAOHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.SQLException;

public class Executor {
    private static final String hibernate_show_sql = "true";
    private static final String hibernate_hbm2ddl_auto = "create";
    private static final Logger logger = LogManager.getLogger(Executor.class.getName());

    private final SessionFactory sessionFactory;

    public Executor() {
        Configuration configuration = getMySqlConfiguration();//getH2Configuration();
        sessionFactory = createSessionFactory(configuration);
    }

    private Configuration getMySqlConfiguration() {
      logger.info("create MySqlConfiguration");

      Configuration configuration = new Configuration();
      configuration.addAnnotatedClass(UsersDataSet.class);
      configuration.addAnnotatedClass(SessionDataSet.class);

      configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
      configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
      configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/db_example?serverTimezone=UTC");
      configuration.setProperty("hibernate.connection.username", "root");
      configuration.setProperty("hibernate.connection.password", "1.3.3.3-,07333f");
      configuration.setProperty("hibernate.show_sql", hibernate_show_sql);
      configuration.setProperty("hibernate.hbm2ddl.auto", hibernate_hbm2ddl_auto);
      return configuration;
  }

    private Configuration getH2Configuration() {
      logger.info("create H2Configuration");

      Configuration configuration = new Configuration();
      configuration.addAnnotatedClass(UsersDataSet.class);
      configuration.addAnnotatedClass(SessionDataSet.class);

      configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
      configuration.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
      configuration.setProperty("hibernate.connection.url", "jdbc:h2:./h2db");
      configuration.setProperty("hibernate.connection.username", "tully");
      configuration.setProperty("hibernate.connection.password", "tully");
      configuration.setProperty("hibernate.show_sql", hibernate_show_sql);
      configuration.setProperty("hibernate.hbm2ddl.auto", hibernate_hbm2ddl_auto);

      return configuration;
  }

    private static SessionFactory createSessionFactory(Configuration configuration) {
      logger.info("createSessionFactory");
      StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
      builder.applySettings(configuration.getProperties());
      ServiceRegistry serviceRegistry = builder.build();
      return configuration.buildSessionFactory(serviceRegistry);
  }

    public <T> T execQuery(DAOHandler<T> handler) throws DBException {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            T value = handler.handle(session);

            transaction.commit();
            session.close();
            return value;
        } catch (HibernateException e) {
            logger.error(e.getMessage());
            throw new DBException(e);
        }
    }

  public void printConnectInfo() {
    try {
      SessionFactoryImpl sessionFactoryImpl = (SessionFactoryImpl) sessionFactory;
      Connection connection = sessionFactoryImpl.getConnectionProvider().getConnection();
      System.out.println("DB name: " + connection.getMetaData().getDatabaseProductName());
      System.out.println("DB version: " + connection.getMetaData().getDatabaseProductVersion());
      System.out.println("Driver: " + connection.getMetaData().getDriverName());
      System.out.println("Autocommit: " + connection.getAutoCommit());
    } catch (SQLException e) {
      logger.error(e.getMessage());
      e.printStackTrace();
    }
  }
}
