package dao;

import dto.ReservationInfo;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.math.BigInteger;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static dao.sqls.Sqls.*;

public class ReservationDao {
    private NamedParameterJdbcTemplate jdbc;
    private RowMapper<ReservationInfo> rowMapper = BeanPropertyRowMapper.newInstance(ReservationInfo.class);

    public ReservationDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<ReservationInfo> selectReservationInfos() {
        List<ReservationInfo> reservationInfos = jdbc.query(SELECT_RESERVATION_INFOS_BEFORE_CONFIRM, rowMapper);
        return reservationInfos;
    }

    public List<ReservationInfo> selectReservationInfosById(String userId) {
        Map<String, String> param = Collections.singletonMap("userId", userId);
        List<ReservationInfo> reservationInfos = jdbc.query(SELECT_RESERVATION_INFOS_BY_ID, param, rowMapper);
        return reservationInfos;
    }

    public ReservationInfo selectReservationInfoByNum(BigInteger reservationInfoNum) {
        Map<String, BigInteger> param = Collections.singletonMap("num", reservationInfoNum);
        ReservationInfo reservationInfo = jdbc.queryForObject(SELECT_RESERVATION_INFO_BY_NUM, param, rowMapper);
        return reservationInfo;
    }

    public void updateReservationInfo(ReservationInfo reservationInfo) {
        Map<String, Object> params = new HashMap<>();
        params.put("num", reservationInfo.getNum());
        params.put("nationCode", reservationInfo.getNationCode());
        params.put("price", reservationInfo.getPrice());

        jdbc.update(UPDATE_RESERVATION_INFO, params);
    }

    public void deleteReservationInfo(BigInteger reservationInfoNum) {
        Map<String, BigInteger> param = Collections.singletonMap("num", reservationInfoNum);
        jdbc.update(DELETE_RESERVATION_INFO, param);
    }

    public void updateReservationProgress(BigInteger reservationNum) {
        Map<String, BigInteger> param = Collections.singletonMap("num", reservationNum);
        jdbc.update(UPDATE_RESERVATION_PROGRESS, param);
    }

    public ReservationInfo selectReservationInfoByExchangeCode(String exchangeCode) {
        Map<String, String> param = Collections.singletonMap("exchangeCode", exchangeCode);
        ReservationInfo reservationInfo = null;
        try {
            reservationInfo = jdbc.queryForObject(SELECT_RESERVATION_INFO_BY_CODE, param, rowMapper);
        } catch (EmptyResultDataAccessException e) {

        }
        return reservationInfo;
    }

    public void insertReservationInfo(ReservationInfo reservationInfo) {
        Map<String, Object> params = new HashMap<>();
        params.put("nationCode", reservationInfo.getNationCode());
        params.put("price", reservationInfo.getPrice());
        params.put("departureDate", reservationInfo.getDepartureDate());
        params.put("userId", "tester");

        jdbc.update(INSERT_RESERVATION_INFO, params);
    }

    public ReservationInfo selectReservationInfoByIdAndNationCode(String id, String nationCode) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", id);
        params.put("nationCode", nationCode);

        ReservationInfo reservationInfo = jdbc.queryForObject(SELECT_RESERVATION_INFO_BY_ID_AND_NATION_CODE, params, rowMapper);
        return reservationInfo;
    }
}
