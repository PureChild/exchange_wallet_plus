package dao;

import dto.Customer;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class TestDao {
    private NamedParameterJdbcTemplate jdbc;
    private RowMapper<Customer> rowMapper = BeanPropertyRowMapper.newInstance(Customer.class);

    public TestDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<Customer> selectTest() {
        List<Customer> test = jdbc.query("SELECT * FROM customer", rowMapper);
        return test;
    }
}