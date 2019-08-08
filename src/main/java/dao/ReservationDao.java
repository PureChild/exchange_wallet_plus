package dao;

import dto.ReservationInfo;
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

    public ReservationInfo selectReservationInfoByNum(BigInteger reservationInfoNum) {
        Map<String, BigInteger> param = Collections.singletonMap("num", reservationInfoNum);
        ReservationInfo reservationInfo = jdbc.queryForObject(SELECT_RESERVATION_INFO_BY_NUM, param, rowMapper);
        return reservationInfo;
    }

    public void updateReservationInfo(ReservationInfo reservationInfo) {
        Map<String, Object> param = new HashMap<>();
        param.put("num", reservationInfo.getNum());
        param.put("nationCode", reservationInfo.getNationCode());
        param.put("price", reservationInfo.getPrice());

        jdbc.update(UPDATE_RESERVATION_INFO, param);
    }

    public void deleteReservationInfo(BigInteger reservationInfoNum) {
        Map<String, BigInteger> param = Collections.singletonMap("num", reservationInfoNum);
        jdbc.update(DELETE_RESERVATION_INFO, param);
    }

    public void updateReservationProgress(BigInteger reservationNum) {
        Map<String, BigInteger> param = Collections.singletonMap("num", reservationNum);
        jdbc.update(UPDATE_RESERVATION_PROGRESS, param);
    }
}
