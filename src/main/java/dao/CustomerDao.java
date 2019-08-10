package dao;

import dto.Customer;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

import static dao.sqls.Sqls.INSERT_CUSTOMER;
import static dao.sqls.Sqls.SELECT_EXISTS_CUSTOMER;

public class CustomerDao {
    private NamedParameterJdbcTemplate jdbc;
    private RowMapper<Customer> rowMapper = BeanPropertyRowMapper.newInstance(Customer.class);

    public CustomerDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }

    public void insertCustomer(Customer customer) {
        Map<String, Object> param = new HashMap<>();
        param.put("userId", customer.getId());
        param.put("userPw", customer.getPw());
        param.put("userName", customer.getName());
        param.put("userAccount", customer.getAccount());

        jdbc.update(INSERT_CUSTOMER, param);
    }

    public boolean selectExistsCustomer(Customer customer) {
        Map<String, Object> param = new HashMap<>();
        param.put("userId", customer.getId());
        param.put("userPw", customer.getPw());

        return jdbc.queryForObject(SELECT_EXISTS_CUSTOMER, param, Boolean.class);
    }
}
