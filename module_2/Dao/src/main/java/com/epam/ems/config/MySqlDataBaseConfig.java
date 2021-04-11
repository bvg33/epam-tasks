package com.epam.ems.config;

import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Properties;

import static org.apache.commons.dbcp.BasicDataSourceFactory.createDataSource;

@Configuration
public class MySqlDataBaseConfig {

    @Bean
    @Qualifier(value = "mySql")
    public DataSource mysqlDataSource() throws Exception {
        Properties props = new Properties();
        props.load(MySqlDataBaseConfig.class.getClassLoader().getResourceAsStream("connection.properties"));
        DataSource ds = createDataSource(props);
        return ds;
    }
}
