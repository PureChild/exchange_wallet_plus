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
        Map<String, Object> params = new HashMap<>();
        params.put("reservationNum", exchangeInfo.getReservationNum());
        params.put("exchangeDate", exchangeInfo.getExchangeDate());
        params.put("exchangeCode", exchangeInfo.getExchangeCode());

        jdbc.update(INSERT_CONFIRM_EXCHANGE_INFO, params);
    }

    public ConfirmedExchangeInfo selectConfirmedExchangeInfo(BigInteger reservationInfoNum) {
        Map<String, BigInteger> param = Collections.singletonMap("reservationNum", reservationInfoNum);
        return jdbc.queryForObject(SELECT_CONFIRMED_EXCHANGE_INFO, param, rowMapper);
    }
}
