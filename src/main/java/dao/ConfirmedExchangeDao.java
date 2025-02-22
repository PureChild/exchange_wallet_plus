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

/**
 * confirmed_reservation_info 테이블 접근 DAO
 * @author 이승수
 */
public class ConfirmedExchangeDao {
    private NamedParameterJdbcTemplate jdbc;
    private RowMapper<ConfirmedExchangeInfo> rowMapper = BeanPropertyRowMapper.newInstance(ConfirmedExchangeInfo.class);

    public ConfirmedExchangeDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }

    /**
     * 환전일이 확정 된 예약 정보 추가
     * @param exchangeInfo 삽입할 환율 정보
     */
    public void insertConfirmedReservationInfo(ConfirmedExchangeInfo exchangeInfo) {
        Map<String, Object> params = new HashMap<>();
        params.put("reservationNum", exchangeInfo.getReservationNum());
        params.put("exchangeDate", exchangeInfo.getExchangeDate());
        params.put("exchangeCode", exchangeInfo.getExchangeCode());

        jdbc.update(INSERT_CONFIRM_EXCHANGE_INFO, params);
    }

    /**
     * 환전일이 확정된 예약 정보 조회
     * @param reservationInfoNum 예약 번호
     * @return 조회된 신청 정보
     */
    public ConfirmedExchangeInfo selectConfirmedExchangeInfo(BigInteger reservationInfoNum) {
        Map<String, BigInteger> param = Collections.singletonMap("reservationNum", reservationInfoNum);
        return jdbc.queryForObject(SELECT_CONFIRMED_EXCHANGE_INFO, param, rowMapper);
    }
}
