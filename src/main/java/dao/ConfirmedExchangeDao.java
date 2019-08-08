package dao;

import dto.ConfirmedExchangeInfo;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.math.BigInteger;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static dao.sqls.Sqls.*;

public class ConfirmedExchangeDao {
    private NamedParameterJdbcTemplate jdbc;
    private RowMapper<ConfirmedExchangeInfo> rowMapper = BeanPropertyRowMapper.newInstance(ConfirmedExchangeInfo.class);

    public ConfirmedExchangeDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }

    public void insertConfirmedReservationInfo(ConfirmedExchangeInfo exchangeInfo) {
        Map<String, Object> param = new HashMap<>();
        param.put("reservationNum", exchangeInfo.getReservationNum());
        param.put("exchangeDate", exchangeInfo.getExchangeDate());
        param.put("exchangeCode", exchangeInfo.getExchangeCode());

        jdbc.update(INSERT_CONFIRM_EXCHANGE_INFO, param);
    }
}
