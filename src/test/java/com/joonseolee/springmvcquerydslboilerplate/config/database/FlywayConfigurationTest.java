//package com.joonseolee.springmvcquerydslboilerplate.config;
//
//import org.flywaydb.core.Flyway;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.ApplicationContext;
//
//
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.is;
//import static org.hamcrest.Matchers.notNullValue;
//
//@SpringBootTest
//class FlywayConfigurationTest {
//
//    @Autowired
//    private ApplicationContext context;
//
//    @Test
//    void whenGivenFlywayBean_thenHavingTheSameOptions() {
//        Flyway flywayBean = context.getBean(Flyway.class);
//
//        assertThat(flywayBean, notNullValue());
//        assertThat(flywayBean.baseline().baselineVersion, is("0"));
//        assertThat(flywayBean.getConfiguration().getLocations()[0].getPath(), is("db/migration"));
//    }
//}