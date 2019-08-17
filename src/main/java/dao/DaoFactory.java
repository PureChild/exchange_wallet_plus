package dao;

import dto.ConfirmedExchangeInfo;
import dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * DAO 클래스 객체 반환 클래스
 * @author 이승수
 */
@Component
public class DaoFactory {
    @Autowired
    private DataSource dataSource;

    public ReservationDao getReservationDao(){
        return new ReservationDao(dataSource);
    }

    public ConfirmedExchangeDao getConfirmedExchangeDao() {
        return  new ConfirmedExchangeDao(dataSource);
    }

    public CustomerDao getCustomerDao() {
        return new CustomerDao(dataSource);
    }

    public TargetRateDao getTargetRateDao() {
        return new TargetRateDao(dataSource);
    }
}
