package dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class DaoFactory {
    @Autowired
    private DataSource dataSource;

    public TestDao testDao() {
        return new TestDao(dataSource);
    }
}
