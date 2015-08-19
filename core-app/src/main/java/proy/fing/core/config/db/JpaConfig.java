package proy.fing.core.config.db;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

//@Configuration
//@EnableJpaRepositories(basePackages = {"proy.fing.core.dao.postgres"})
//@EnableTransactionManagement
public class JpaConfig {
	
//	@Value("${database.url}")
//	private String jdbcUrl;
//	
//	@Value("${database.driver}")
//	private String driver;
//	
//	@Value("${database.password}")
//	private String password;
//	
//	@Value("${database.username}")
//	private String username;
//	
//	@Value("${hibernate.dialect}")
//	private String dialect;
//	
//	@Value("${hibernate.hbm2ddl.auto}")
//	private String hbm2ddl;
//	
//	@Value("${hibernate.ejb.naming_strategy}")
//	private String namingStrategy;
//	
//	@Value("${hibernate.show_sql}")
//	private String showSql;
//	
//	@Value("${hibernate.format_sql}")
//	private String formatSql;
//	
//	@Bean(destroyMethod = "close")
//    public DataSource getDataSource() {
//        HikariConfig dataSourceConfig = new HikariConfig();
//        dataSourceConfig.setDriverClassName(this.driver);
//        dataSourceConfig.setJdbcUrl(this.jdbcUrl);
//        dataSourceConfig.setUsername(this.username);
//        dataSourceConfig.setPassword(this.password);
// 
//        return new HikariDataSource(dataSourceConfig);
//    }
//	
//	@Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//		
//        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
//        entityManagerFactoryBean.setDataSource(getDataSource());
//        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//        entityManagerFactoryBean.setPackagesToScan("proy.fing.core.model");
// 
//        Properties jpaProperties = new Properties();
//     
//        //Configures the used database dialect. This allows Hibernate to create SQL
//        //that is optimized for the used database.
//        jpaProperties.put("hibernate.dialect", this.dialect);
// 
//        //Specifies the action that is invoked to the database when the Hibernate
//        //SessionFactory is created or closed.
//        jpaProperties.put("hibernate.hbm2ddl.auto", 
//               this.hbm2ddl
//        );
// 
//        //Configures the naming strategy that is used when Hibernate creates
//        //new database objects and schema elements
//        jpaProperties.put("hibernate.ejb.naming_strategy", 
//                this.namingStrategy
//        );
// 
//        //If the value of this property is true, Hibernate writes all SQL
//        //statements to the console.
//        jpaProperties.put("hibernate.show_sql", 
//                this.showSql
//        );
// 
//        //If the value of this property is true, Hibernate will format the SQL
//        //that is written to the console.
//        jpaProperties.put("hibernate.format_sql", 
//                this.formatSql
//        );
// 
//        entityManagerFactoryBean.setJpaProperties(jpaProperties);
// 
//        return entityManagerFactoryBean;
//    }
//	
//	@Bean
//    public JpaTransactionManager transactionManager() {
//        JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory((EntityManagerFactory) entityManagerFactory());
//        return transactionManager;
//    }
}
