package dao;

import dto.Customer;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;

public class TestDao {
    private NamedParameterJdbcTemplate jdbc;
    private RowMapper<Customer> rowMapper = BeanPropertyRowMapper.newInstance(Customer.class);

    public TestDao(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Customer> selectCategories() {
        return jdbc.query("SELECT * FROM customer", rowMapper);
    }
}
