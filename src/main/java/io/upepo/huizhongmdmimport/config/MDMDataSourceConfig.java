package io.upepo.huizhongmdmimport.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;


@Configuration
@PropertySource({"classpath:application.properties"})
@EnableJpaRepositories(
        basePackages = " io.upepo.huizhongmdmimport.repository;",
        entityManagerFactoryRef = "EntityManagerFactory",
        transactionManagerRef = "TransactionManager")
public class MDMDataSourceConfig {
    @Bean(name="mdmDataSource")
    @ConfigurationProperties(prefix="mdm.datasource")
    public DataSource userDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean
    mdmEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("mdmDataSource") DataSource dataSource
    ) {
        return
                builder
                        .dataSource(dataSource)
                        .packages("io.upepo.huizhongmdmimport.model")
                        .persistenceUnit("mdm")
                        .build();
    }
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("entityManagerFactory") EntityManagerFactory
                    entityManagerFactory
    ) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}

