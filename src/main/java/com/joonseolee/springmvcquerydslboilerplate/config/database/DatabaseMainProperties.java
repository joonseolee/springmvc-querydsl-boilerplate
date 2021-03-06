package com.joonseolee.springmvcquerydslboilerplate.config.database;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Validated
@Component
@ConfigurationProperties(prefix = "datasource.main")
public final class DatabaseMainProperties {

    @NotNull
    private DatabaseConfigurationProperties master;
    @Valid
    @NotNull
    private List<DatabaseConfigurationProperties> slaves;

    @Getter
    @Setter
    @Component
    public static final class DatabaseConfigurationProperties {
        @NotEmpty
        private String driverClassName;
        @NotEmpty
        private String url;
        @NotEmpty
        private String username;
        @NotEmpty
        private String password;
    }
}
