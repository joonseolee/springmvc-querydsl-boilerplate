package com.joonseolee.springmvcquerydslboilerplate.config.database;

import lombok.RequiredArgsConstructor;
import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@RequiredArgsConstructor
@Configuration
public class FlywayConfiguration {

    private static final String DB_MIGRATION_PATH = "db/migration";
    private static final String INITIAL_VERSION_NUMBER = "0";
    private final DataSource mainDataSource;

    @Bean
    public Flyway flyway() {
        var flyway = Flyway.configure()
                .dataSource(mainDataSource)
                .baselineOnMigrate(true)
                .locations(DB_MIGRATION_PATH)
                .baselineVersion(INITIAL_VERSION_NUMBER)
                .load();
        flyway.migrate();
        return flyway;
    }
}
