package com.joonseolee.springmvcquerydslboilerplate.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@SpringBootTest
class DatabaseMainPropertiesTest {

    @Autowired
    private DatabaseMainProperties databaseMainProperties;

    @Test
    void whenLoadDatabaseMainProperties_thenReturnObjectMappedValues() {
        var master = databaseMainProperties.getMaster();
        var slaves = databaseMainProperties.getSlaves();

        assertThat(master.getUrl(), is("jdbc:mysql://localhost:3306/demo?serverTimezone=Asia/Seoul"));
        assertThat(slaves.get(0).getUrl(), is("jdbc:mysql://localhost:3307/demo?serverTimezone=Asia/Seoul"));
        assertThat(master.getDriverClassName(), is("com.mysql.cj.jdbc.Driver"));
        assertThat(slaves.get(0).getDriverClassName(), is("com.mysql.cj.jdbc.Driver"));
    }
}