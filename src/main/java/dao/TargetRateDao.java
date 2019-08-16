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

@Repository
public class TargetRateDao {
    private NamedParameterJdbcTemplate jdbc;
    private RowMapper<TargetRate> rowMapper = BeanPropertyRowMapper.newInstance(TargetRate.class);

    public TargetRateDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<TargetRate> selectTargetRatesById(String userId, int pageNum, int dataPerPage) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("start", (pageNum - 1) * dataPerPage);
        params.put("dataPerPage", dataPerPage);

        List<TargetRate> targetRates = jdbc.query(SELECT_TARGET_RATES_BY_ID + LIMIT, params, rowMapper);
        return targetRates;
    }

    public TargetRate selectTargetRate(String userId, String nationCode) {
        Map<String, String> params = new HashMap<>();
        params.put("userId", userId);
        params.put("nationCode", nationCode);

        TargetRate targetRate = jdbc.queryForObject(SELECT_TARGET_RATE, params, rowMapper);

        return targetRate;
    }

    public void insertTargetRate(TargetRate targetRate) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", targetRate.getId());
        params.put("nationCode", targetRate.getNationCode());
        params.put("rate", targetRate.getRate());

        jdbc.update(INSERT_TARGET_RATE, params);
    }

    public void deleteTargetRate(TargetRate targetRate) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", targetRate.getId());
        params.put("nationCode", targetRate.getNationCode());

        jdbc.update(DELETE_TARGET_RATE, params);
    }

    public void updateTargetRate(String originNationCode, TargetRate targetRate) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", targetRate.getId());
        params.put("nationCode", targetRate.getNationCode());
        params.put("rate", targetRate.getRate());
        params.put("originNationCode", originNationCode);

        jdbc.update(UPDATE_TARGET_RATE, params);
    }

    public int selectTotalCount(String userId) {
        Map<String, String> param = Collections.singletonMap("userId", userId);
        return jdbc.queryForObject(SELECT_TOTAL_COUNT_OF_TARGET_RATES, param, Integer.class);
    }
}
