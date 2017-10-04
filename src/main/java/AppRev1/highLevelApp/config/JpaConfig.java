package AppRev1.highLevelApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"AppRev1.highLevelApp.persistence.repository"})
public class JpaConfig {

    @Bean(name = "entityManagerFactory")
    LocalContainerEntityManagerFactoryBean getEmf(){
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("rs5");
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());

        return em;
    }
    @Bean
    public Properties additionalProperties() {
        Properties props = new Properties();
        props.setProperty("hibernate.hbm2ddl.auto", "update");
        props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
//        props.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        return props;
    }

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("org.postgresql.Driver");
//        dataSource.setUrl("jdbc:postgresql://localhost:5432/db");
//        dataSource.setUsername( "admin" );
//        dataSource.setPassword( "4251" );
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/testdb");
        dataSource.setUsername("user");
        dataSource.setPassword("4251");
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);

        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }
}

