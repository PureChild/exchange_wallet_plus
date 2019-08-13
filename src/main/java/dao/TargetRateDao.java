package dao;

import dto.TargetRate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static dao.sqls.Sqls.SELECT_ALL_TARGET_RATES;

@Repository
public class TargetRateDao {
    private NamedParameterJdbcTemplate jdbc;
    private RowMapper<TargetRate> rowMapper = BeanPropertyRowMapper.newInstance(TargetRate.class);

    public TargetRateDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<TargetRate> selectAllTargetRates(String userId) {
        Map<String, String> param = Collections.singletonMap("userId", userId);
        List<TargetRate> targetRates = jdbc.query(SELECT_ALL_TARGET_RATES, param, rowMapper);
        return targetRates;
    }
}
