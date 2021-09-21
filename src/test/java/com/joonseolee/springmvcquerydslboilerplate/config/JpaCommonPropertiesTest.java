package com.joonseolee.springmvcquerydslboilerplate.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@SpringBootTest(classes = JpaCommonProperties.class)
@EnableConfigurationProperties(JpaCommonProperties.class)
class JpaCommonPropertiesTest {

    @Autowired
    private JpaCommonProperties jpaCommonProperties;

    @Test
    void whenLoadJpaProperties_thenReturnObjectMappedValues() {

        assertThat(jpaCommonProperties.getDdlAuto(), is("none"));
        assertThat(jpaCommonProperties.getShowSql(), is("true"));
        assertThat(jpaCommonProperties.getFormatSql(), is("true"));

    }
}