package com.joonseolee.springmvcquerydslboilerplate.config.database;

import com.joonseolee.springmvcquerydslboilerplate.config.DatabaseMainProperties;
import lombok.RequiredArgsConstructor;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Properties;

@RequiredArgsConstructor
@EnableJpaRepositories(
        basePackages = "com.joonseolee.springmvcquerydslboilerplate",
        entityManagerFactoryRef = "mainEntityManagerFactory",
        transactionManagerRef = "mainJpaTransactionManager"
)
@Configuration
public class DatabaseMainConfiguration {

    private static final String PACKAGE_DIRECTORY = "com.joonseolee.springmvcquerydslboilerplate";

    private final DatabaseMainProperties databaseMainProperties;
    private final Properties jpaCommonPropertiesConfiguration;

    @Bean
    public DataSource mainMasterDataSource() {
        var master = databaseMainProperties.getMaster();
        return DataSourceBuilder.create()
                .driverClassName(master.getDriverClassName())
                .url(master.getUrl())
                .username(master.getUsername())
                .password(master.getPassword())
                .build();
    }

    @Bean
    public DataSource mainSlaveDataSource() {
        var slave = databaseMainProperties.getSlaves().stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Not found slave datasource."));
        return DataSourceBuilder.create()
                .driverClassName(slave.getDriverClassName())
                .url(slave.getUrl())
                .username(slave.getUsername())
                .password(slave.getPassword())
                .build();
    }

    @Bean
    public DataSource mainRoutingDataSource(@Qualifier("mainMasterDataSource") DataSource mainMasterDataSource,
                                     @Qualifier("mainSlaveDataSource") DataSource mainSlaveDataSource) {
        var dynamicRoutingDataSource = new DynamicRoutingDataSource();
        var map = new HashMap<>();
        map.put(ReplicationType.MASTER, mainMasterDataSource);
        map.put(ReplicationType.SLAVE, mainSlaveDataSource);
        dynamicRoutingDataSource.setTargetDataSources(map);
        dynamicRoutingDataSource.setDefaultTargetDataSource(mainMasterDataSource);

        return dynamicRoutingDataSource;
    }

    @Bean
    public DataSource mainDataSource(@Qualifier("mainRoutingDataSource") DataSource mainRoutingDataSource) {
        return new LazyConnectionDataSourceProxy(mainRoutingDataSource);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean mainEntityManagerFactory(@Qualifier("mainDataSource") DataSource mainDataSource) {
        var entityFactory = new LocalContainerEntityManagerFactoryBean();
        entityFactory.setDataSource(mainDataSource);
        entityFactory.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityFactory.setPackagesToScan(PACKAGE_DIRECTORY);
        entityFactory.setJpaProperties(jpaCommonPropertiesConfiguration);
        return entityFactory;
    }

    @Bean
    public PlatformTransactionManager mainJpaTransactionManager(
            @Qualifier("mainEntityManagerFactory") EntityManagerFactory mainEntityManagerFactory) {
        var mainJpaTransactionManager = new JpaTransactionManager();
        mainJpaTransactionManager.setEntityManagerFactory(mainEntityManagerFactory);
        return mainJpaTransactionManager;
    }

    @Bean
    public TransactionTemplate mainTransactionTemplate(
            @Qualifier("mainJpaTransactionManager") PlatformTransactionManager mainJpaTransactionManager) {
        return new TransactionTemplate(mainJpaTransactionManager);
    }
}
