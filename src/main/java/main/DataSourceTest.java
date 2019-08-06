package main;

import config.ApplicationConfig;

import dto.Customer;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;

public class DataSourceTest {

    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        DataSource ds = ac.getBean(DataSource.class);
        Connection conn = null;
        try {
            conn = ds.getConnection();
            if(conn != null)
                System.out.println("접속 성공");


            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
            dataSource.setUrl("jdbc:mysql://49.236.137.225:3306/exchange_wallet?serverTimezone=Asia/Seoul");
            dataSource.setUsername("root");
            dataSource.setPassword("P@ssw0rd");
            NamedParameterJdbcTemplate jdbc = new NamedParameterJdbcTemplate(dataSource);
            RowMapper<Customer> rowMapper = BeanPropertyRowMapper.newInstance(Customer.class);

            System.out.println(jdbc.query("SELECT * FROM customer", rowMapper));
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(conn != null) {
                try {
                    conn.close();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}