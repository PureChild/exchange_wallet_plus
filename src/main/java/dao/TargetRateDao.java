package dao;

import dto.TargetRate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static dao.sqls.Sqls.*;

/**
 * target_rate 테이블 접근 DAO
 * @author 이승수
 */
@Repository
public class TargetRateDao {
    private NamedParameterJdbcTemplate jdbc;
    private RowMapper<TargetRate> rowMapper = BeanPropertyRowMapper.newInstance(TargetRate.class);

    public TargetRateDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }

    /**
     * @param userId 고객 ID
     * @param pageNum 페이지네이션을 위한 페이지 번호
     * @param dataPerPage 한 페이지에 보여질 데이터 수
     * @return 목표 환율 목록
     */
    public List<TargetRate> selectTargetRatesById(String userId, int pageNum, int dataPerPage) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("start", (pageNum - 1) * dataPerPage);
        params.put("dataPerPage", dataPerPage);

        List<TargetRate> targetRates = jdbc.query(SELECT_TARGET_RATES_BY_ID + LIMIT, params, rowMapper);
        return targetRates;
    }

    /**
     * @param userId 고객 ID
     * @param nationCode 국가 코드
     * @return 해당 고객, 해당 국가의 목표 환율 정보
     */
    public TargetRate selectTargetRate(String userId, String nationCode) {
        Map<String, String> params = new HashMap<>();
        params.put("userId", userId);
        params.put("nationCode", nationCode);

        TargetRate targetRate = jdbc.queryForObject(SELECT_TARGET_RATE, params, rowMapper);

        return targetRate;
    }

    /**
     * @param targetRate 목표 환율 정보
     */
    public void insertTargetRate(TargetRate targetRate) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", targetRate.getId());
        params.put("nationCode", targetRate.getNationCode());
        params.put("rate", targetRate.getRate());

        jdbc.update(INSERT_TARGET_RATE, params);
    }

    /**
     * @param targetRate 목표 환율 정보
     */
    public void deleteTargetRate(TargetRate targetRate) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", targetRate.getId());
        params.put("nationCode", targetRate.getNationCode());

        jdbc.update(DELETE_TARGET_RATE, params);
    }

    /**
     * @param originNationCode 기존 국가 코드
     * @param targetRate 목표 환율 정보
     */
    public void updateTargetRate(String originNationCode, TargetRate targetRate) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", targetRate.getId());
        params.put("nationCode", targetRate.getNationCode());
        params.put("rate", targetRate.getRate());
        params.put("originNationCode", originNationCode);

        jdbc.update(UPDATE_TARGET_RATE, params);
    }

    /**
     * @param userId 고객 ID
     * @return 해당 고객이 설정한 목표 환율 정보 수
     */
    public int selectTotalCountById(String userId) {
        Map<String, String> param = Collections.singletonMap("userId", userId);
        return jdbc.queryForObject(SELECT_TOTAL_COUNT_OF_TARGET_RATES, param, Integer.class);
    }
}
