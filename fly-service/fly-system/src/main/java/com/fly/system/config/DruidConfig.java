package com.fly.system.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean("master")
    public DataSource masterDataSource() {
        return new DruidDataSource();
    }



    @Value("{}")

    public DataSource slaveDataSource(){
        DruidDataSource slaveDataSource=new DruidDataSource();
        slaveDataSource.setUrl();

        return slaveDataSource;
    }


}