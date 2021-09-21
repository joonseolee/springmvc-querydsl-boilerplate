package com.joonseolee.springmvcquerydslboilerplate.config.swagger;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@RequiredArgsConstructor
@Configuration
public class SpringFoxConfiguration {

    private final SpringFoxProperties springFoxProperties;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant(springFoxProperties.getAntPattern()))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                springFoxProperties.getTitle(),
                springFoxProperties.getDescription(),
                springFoxProperties.getVersion(),
                Strings.EMPTY,
                new Contact(springFoxProperties.getContactName(),
                        springFoxProperties.getContactUrl(),
                       springFoxProperties.getContactEmail()),
                Strings.EMPTY,
                Strings.EMPTY,
                Collections.emptyList());
    }
}
