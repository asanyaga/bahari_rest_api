package io.upepo.baharirestapi.config;


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
        basePackages = "io.upepo.baharirestapi.repository",
        entityManagerFactoryRef = "restAPIEntityManagerFactory",
        transactionManagerRef = "restAPITransactionManager")
public class RestAPIDataSourceConfig {
    @Primary
    @Bean(name = "restAPIDataSource")
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource userDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "restAPIEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean
    restAPIEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("restAPIDataSource") DataSource dataSource
    ) {
        return
                builder
                        .dataSource(dataSource)
                        .packages("io.upepo.baharirestapi.model")
                        .persistenceUnit("rest")
                        .build();
    }
    @Bean(name = "restAPITransactionManager")
    @Primary
    public PlatformTransactionManager restAPITransactionManager(
            @Qualifier("restAPIEntityManagerFactory") EntityManagerFactory
                    restAPIEntityManagerFactory
    ) {
        return new JpaTransactionManager(restAPIEntityManagerFactory);
    }
}


