package com.joonseolee.springmvcquerydslboilerplate.config.database;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "datasource.jpa")
public final class JpaCommonProperties {

    private String ddlAuto;
    private String showSql;
    private String formatSql;
    private String databasePlatform;
}
