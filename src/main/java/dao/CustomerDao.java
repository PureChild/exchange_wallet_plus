package dao;

import dto.Customer;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static dao.sqls.Sqls.INSERT_CUSTOMER;
import static dao.sqls.Sqls.SELECT_CUSTOMER_NAME;
import static dao.sqls.Sqls.SELECT_EXISTS_CUSTOMER;

/**
 * customer 테이블 접근 DAO
 * @author 이승수
 */
public class CustomerDao {
    private NamedParameterJdbcTemplate jdbc;
    private RowMapper<Customer> rowMapper = BeanPropertyRowMapper.newInstance(Customer.class);

    public CustomerDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }

    /**
     * 고객 정보 삽입
     * @param customer 삽입될 고객 정보
     */
    public void insertCustomer(Customer customer) {
        Map<String, Object> param = new HashMap<>();
        param.put("userId", customer.getId());
        param.put("userPw", customer.getPw());
        param.put("userName", customer.getName());
        param.put("userAccount", customer.getAccount());

        jdbc.update(INSERT_CUSTOMER, param);
    }

    /**
     * 고객 존재 여부 조회
     * @param customer 조회할 고객 정보
     * @return 있을 경우 true, 없을 경우 false
     */
    public boolean selectExistsCustomer(Customer customer) {
        Map<String, Object> param = new HashMap<>();
        param.put("userId", customer.getId());
        param.put("userPw", customer.getPw());

        return jdbc.queryForObject(SELECT_EXISTS_CUSTOMER, param, Boolean.class);
    }

    /**
     * 고객 이름 조회
     * @param userId 고객 ID
     * @return 고객 명
     */
    public String selectCustomerName(String userId) {
        Map<String,String> param = Collections.singletonMap("userId", userId);
        return jdbc.queryForObject(SELECT_CUSTOMER_NAME, param, String.class);
    }
}
