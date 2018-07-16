package com.example.demo;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "h2EntityManagerFactory", transactionManagerRef = "h2TransactionManager",
    basePackages = {"com.fullerton.oauth.admin.model.h2.dao"})
public class H2DataSourceConfig {

  @Primary
  @Bean(name = "h2DataSource")
  @ConfigurationProperties(prefix = "spring.h2.datasource")
  public DataSource h2DataSource() {
    return DataSourceBuilder.create().build();
  }

  @Bean(name = "h2EntityManagerFactory")
  public LocalContainerEntityManagerFactoryBean h2EntityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier("h2DataSource") DataSource dataSource) {
    return builder.dataSource(dataSource).packages("com.fullerton.oauth.admin.model.h2.vo").build();
  }

  @Bean(name = "h2TransactionManager")
  public PlatformTransactionManager h2TransactionManager(@Qualifier("h2EntityManagerFactory") EntityManagerFactory h2EntityManagerFactory) {
    return new JpaTransactionManager(h2EntityManagerFactory);
  }
}
