package proy.fing.core.config.db;

import java.beans.PropertyVetoException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

import com.mchange.v2.c3p0.PooledDataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DatabaseConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseConfig.class);

    private static final String DESTROY_METHOD_NAME = "destroy";

    @Value("${hibernate.connection.url}")
    private String jdbcUrl;

    @Value("${hibernate.connection.username}")
    private String username;

    @Value("${hibernate.connection.password}")
    private String password;

    @Value("${hibernate.dialect}")
    private String dialect;

    @Value("${hibernate.query.factory_class}")
    private String queryFactoryClass;

    @Value("${hibernate.bytecode.use_reflection_optimizer}")
    private String bytecodeUseReflectionOptimizer;

    @Value("${hibernate.show_sql}")
    private String showSql;

    @Value("${hibernate.format_sql}")
    private String formatSql;

    @Value("${hibernate.generate_statistics}")
    private String generateStatistics;

    @Value("${hibernate.connection.autocommit}")
    private String hibernateConnectionAutocommit;

    @Value("${hibernate.hbm2ddl.auto}")
    private String ddlAuto;

    @Value("${hibernate.connection.isolation}")
    private String hibernateConnectionIsolation;
    
    @Value("${hibernate.default_schema}")
    private String defaultSchema;

    // HIKARI VALUES

    @Value("${core-app.datasource.hikari.maximumPoolSize}")
    private int maximumPoolSize;

    @Value("${core-app.datasource.hikari.extraProperties}")
    private String jdbcExtraParams;

    @Value("${core-app.datasource.hikari.host}")
    private String host;

    @Value("${core-app.datasource.hikari.connectionTimeout}")
    private Long connectionTimeout;

    @Bean(name = "dataSource", destroyMethod = DESTROY_METHOD_NAME)
    public SelfDestroyPooledDataSource getMainDataSource() throws PropertyVetoException {
        HikariConfig config = new HikariConfig();

        config.setConnectionTimeout(this.connectionTimeout);
        config.setUsername(this.username);
        config.setPassword(this.password);
        config.setMaximumPoolSize(this.maximumPoolSize);
        config.addDataSourceProperty("serverName", this.host);
        config.setTransactionIsolation("TRANSACTION_READ_COMMITTED");
        config.setJdbcUrl(this.jdbcUrl + this.jdbcExtraParams);

        config.setPoolName("core-app-pool");
        config.setRegisterMbeans(true);

        HikariDataSource ds = new HikariDataSource(config);
        SelfDestroyPooledDataSource selfDestroyPooledDataSource = adapt(ds);
        return selfDestroyPooledDataSource;

    }



    @Bean(name = "sessionFactory")
    public LocalSessionFactoryBean getLocalSessionFactoryBean() throws PropertyVetoException {

        String[] packagesToScan = new String[] {"proy.fing.core.model"};

        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.dialect", this.dialect);
        hibernateProperties.setProperty("hibernate.query.factory_class", this.queryFactoryClass);
        hibernateProperties.setProperty("hibernate.bytecode.use_reflection_optimizer", this.bytecodeUseReflectionOptimizer);
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", this.ddlAuto);
        hibernateProperties.setProperty("hibernate.show_sql", this.showSql);
        hibernateProperties.setProperty("hibernate.format_sql", this.formatSql);
        hibernateProperties.setProperty("hibernate.generate_statistics", this.generateStatistics);
        hibernateProperties.setProperty("hibernate.connection.autocommit", this.hibernateConnectionAutocommit);
        hibernateProperties.setProperty("hibernate.connection.isolation", this.hibernateConnectionIsolation);
        hibernateProperties.setProperty("hibernate.default_schema", this.defaultSchema);


        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setPackagesToScan(packagesToScan);
        sessionFactory.setDataSource(this.getMainDataSource());
        sessionFactory.setHibernateProperties(hibernateProperties);
        return sessionFactory;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor getPersistenceExceptionTranslationPostProcessor() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    /**
     * Creates a proxy of PooledDataSource, which contains a 'destroy' method that closes the dataSource and unregisters the JDBC driver
     */
    private static SelfDestroyPooledDataSource adapt(HikariDataSource dataSource) {
        InvocationHandler handler = new SelfDestroyInvocationHandler(dataSource);
        ClassLoader classLoader = SelfDestroyPooledDataSource.class.getClassLoader();
        return (SelfDestroyPooledDataSource) Proxy.newProxyInstance(classLoader,
            new Class[] {SelfDestroyPooledDataSource.class}, handler);
    }

    private static final class SelfDestroyInvocationHandler
        implements InvocationHandler {

        private final HikariDataSource dataSource;

        private SelfDestroyInvocationHandler(HikariDataSource dataSource) {
            this.dataSource = dataSource;
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.getName().equals(DESTROY_METHOD_NAME)) {
                this.closeAndUnregisterDrivers();
                return null;
            } else {
                try {
                    return method.invoke(this.dataSource, args);
                } catch (InvocationTargetException e) {
                    throw e.getTargetException();
                }
            }
        }

        private void closeAndUnregisterDrivers() throws SQLException {
            this.dataSource.close();
            Enumeration<Driver> drivers = DriverManager.getDrivers();
            while (drivers.hasMoreElements()) {
                Driver driver = drivers.nextElement();
                DriverManager.deregisterDriver(driver);
            }
            LOGGER.info("JDBC drivers successfully unregistered");
        }
    }

    // Make 'destroy' method available to Spring
    public interface SelfDestroyPooledDataSource
        extends PooledDataSource {

        void destroy();
    }
}
