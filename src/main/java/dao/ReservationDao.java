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

/**
 * @author 이승수
 * reservation_info 테이블 접근 DAO
 */
public class ReservationDao {
    private NamedParameterJdbcTemplate jdbc;
    private RowMapper<ReservationInfo> rowMapper = BeanPropertyRowMapper.newInstance(ReservationInfo.class);

    public ReservationDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }

    /**
     * @param pageNum 페이지네이션을 위한 페이지 번호
     * @param dataPerPage 한 페이지에 보여질 데이터 수
     * @return 해당 페이지만큼의 신청중 상태(progress = 0)의 예약 목록
     */
    public List<ReservationInfo> selectReservationInfos(int pageNum, int dataPerPage) {
        Map<String, Object> params = new HashMap<>();
        params.put("start", (pageNum - 1) * dataPerPage);
        params.put("dataPerPage", dataPerPage);

        List<ReservationInfo> reservationInfos = jdbc.query(SELECT_RESERVATION_INFOS_BEFORE_CONFIRM + LIMIT, params, rowMapper);
        return reservationInfos;
    }

    /**
     * @param userId 고객 ID
     * @param pageNum 페이지네이션을 위한 페이지 번호
     * @param dataPerPage 한 페이지에 보여질 데이터 수
     * @return 해당 고객의, 해당 페이지만큼의 예약 목록
     */
    public List<ReservationInfo> selectReservationInfosById(String userId, int pageNum, int dataPerPage) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("start", (pageNum - 1) * dataPerPage);
        params.put("dataPerPage", dataPerPage);

        List<ReservationInfo> reservationInfos = jdbc.query(SELECT_RESERVATION_INFOS_BY_ID + LIMIT, params, rowMapper);
        return reservationInfos;
    }

    /**
     * @param reservationInfoNum 예약 번호
     * @return 해당 예약 정보
     */
    public ReservationInfo selectReservationInfoByNum(BigInteger reservationInfoNum) {
        Map<String, BigInteger> param = Collections.singletonMap("num", reservationInfoNum);
        ReservationInfo reservationInfo = jdbc.queryForObject(SELECT_RESERVATION_INFO_BY_NUM, param, rowMapper);
        return reservationInfo;
    }

    /**
     * @param reservationInfo 예약 번호
     */
    public void updateReservationInfo(ReservationInfo reservationInfo) {
        Map<String, Object> params = new HashMap<>();
        params.put("num", reservationInfo.getNum());
        params.put("nationCode", reservationInfo.getNationCode());
        params.put("price", reservationInfo.getPrice());

        jdbc.update(UPDATE_RESERVATION_INFO, params);
    }

    /**
     * @param reservationInfoNum 예약 번호
     */
    public void deleteReservationInfo(BigInteger reservationInfoNum) {
        Map<String, BigInteger> param = Collections.singletonMap("num", reservationInfoNum);
        jdbc.update(DELETE_RESERVATION_INFO, param);
    }

    /**
     * @param reservationNum 예약 번호
     */
    public void updateReservationProgress(BigInteger reservationNum) {
        Map<String, BigInteger> param = Collections.singletonMap("num", reservationNum);
        jdbc.update(UPDATE_RESERVATION_PROGRESS, param);
    }

    /**
     * @param exchangeCode 환전 코드
     * @return 해당 예약 정보
     */
    public ReservationInfo selectReservationInfoByExchangeCode(String exchangeCode) {
        Map<String, String> param = Collections.singletonMap("exchangeCode", exchangeCode);
        ReservationInfo reservationInfo = null;
        try {
            reservationInfo = jdbc.queryForObject(SELECT_RESERVATION_INFO_BY_CODE, param, rowMapper);
        } catch (EmptyResultDataAccessException e) {

        }
        return reservationInfo;
    }

    /**
     * @param reservationInfo 예약 정보
     */
    public void insertReservationInfo(ReservationInfo reservationInfo) {
        Map<String, Object> params = new HashMap<>();
        params.put("nationCode", reservationInfo.getNationCode());
        params.put("price", reservationInfo.getPrice());
        params.put("departureDate", reservationInfo.getDepartureDate());
        params.put("userId", reservationInfo.getApplicant());

        jdbc.update(INSERT_RESERVATION_INFO, params);
    }

    /**
     * @param userId 고객 ID
     * @param nationCode 국가 코드
     * @return 해당 고객, 해당 국가의 예약 정보
     */
    public ReservationInfo selectReservationInfoByIdAndNationCode(String userId, String nationCode) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("nationCode", nationCode);

        ReservationInfo reservationInfo = jdbc.queryForObject(SELECT_RESERVATION_INFO_BY_ID_AND_NATION_CODE, params, rowMapper);
        return reservationInfo;
    }

    /**
     * @param userId 고객 ID
     * @return 해당 고객의 예약 수
     */
    public int selectTotalCountById(String userId) {
        Map<String, String> param = Collections.singletonMap("userId", userId);
        return jdbc.queryForObject(SELECT_TOTAL_COUNT_OF_RESERVATIONS_BY_ID, param, Integer.class);
    }

    /**
     * @return 전체 신청중(progress = 0) 예약 수
     */
    public int selectTotalCount() {
        return jdbc.queryForObject(SELECT_TOTAL_COUNT_OF_RESERVATIONS_BEFORE_CONFIRM, Collections.emptyMap(), Integer.class);
    }
}
