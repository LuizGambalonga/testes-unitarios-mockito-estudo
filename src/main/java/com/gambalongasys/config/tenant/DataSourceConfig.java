//package com.gambalongasys.config.tenant;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.jdbc.datasource.AbstractDataSource;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//import java.sql.Connection;
//import java.sql.SQLException;
//
//@Configuration
//@EnableTransactionManagement
//public class DataSourceConfig {
//
//    @Autowired
//    @Lazy
//    private DataSource dataSource;
//
//    @Bean
//    public DataSource dataSource() {
//        return new AbstractDataSource() {
//
//            @Override
//            public Connection getConnection() throws SQLException {
//                String tenant = TenantContext.getTenant();
//                return dataSource.getConnection();
//            }
//
//            @Override
//            public Connection getConnection(String username, String password) throws SQLException {
//                return null;
//            }
//        };
//    }
//}