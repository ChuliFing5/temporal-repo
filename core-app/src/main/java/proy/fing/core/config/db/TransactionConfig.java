package proy.fing.core.config.db;

import java.beans.PropertyVetoException;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

@Configuration
@EnableTransactionManagement(mode = AdviceMode.PROXY, proxyTargetClass = true)
public class TransactionConfig
    implements TransactionManagementConfigurer {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionConfig.class);

    @Autowired
    private DatabaseConfig database;

    @Bean(name = "transactionManager")
    public PlatformTransactionManager getHibernateTransactionManager() throws PropertyVetoException {
        LocalSessionFactoryBean factory = this.database.getLocalSessionFactoryBean();
        SessionFactory sessionFactory = factory.getObject();
        HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
        return transactionManager;
    }

    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        try {
            return this.getHibernateTransactionManager();
        } catch (PropertyVetoException e) {
            LOGGER.error("Error configuring transaction manager");
            throw new RuntimeException(e);
        }
    }



}
