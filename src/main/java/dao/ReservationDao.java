package dao;

import dto.ReservationInfo;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReservationDao {
    private NamedParameterJdbcTemplate jdbc;
    private RowMapper<ReservationInfo> rowMapper = BeanPropertyRowMapper.newInstance(ReservationInfo.class);

    public ReservationDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<ReservationInfo> selectAllReservationInfos() {
        List<ReservationInfo> reservationInfos = jdbc.query("SELECT * FROM reservation_info", rowMapper);
        return reservationInfos;
    }

    public ReservationInfo selectReservationInfoByNum(int reservationInfoNum) {
        Map<String, Integer> param = Collections.singletonMap("num", reservationInfoNum);
        ReservationInfo reservationInfo = jdbc.queryForObject("SELECT * FROM reservation_info WHERE num = :num", param, rowMapper);
        return reservationInfo;
    }

    public void updateReservationInfo(ReservationInfo reservationInfo) {
        Map<String, Object> param = new HashMap<>();
        param.put("num", reservationInfo.getNum());
        param.put("nationCode", reservationInfo.getNationCode());
        param.put("price", reservationInfo.getPrice());

        jdbc.update("UPDATE reservation_info SET nation_code = :nationCode, price = :price WHERE num = :num", param);
    }

    public void deleteReservationInfo(int reservationInfoNum) {
        Map<String, Integer> param = Collections.singletonMap("num", reservationInfoNum);
        jdbc.update("DELETE FROM reservation_info WHERE num = :num", param);
    }
}
