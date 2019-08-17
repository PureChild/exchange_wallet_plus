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

    /**
     * @return ReservationDao
     * @see dao.ReservationDao
     */
    public ReservationDao getReservationDao(){
        return new ReservationDao(dataSource);
    }

    /**
     * @return ConfirmedExchangeDao
     * @see dao.ConfirmedExchangeDao
     */
    public ConfirmedExchangeDao getConfirmedExchangeDao() {
        return  new ConfirmedExchangeDao(dataSource);
    }

    /**
     * @return CustomerDao
     * @see dao.CustomerDao
     */
    public CustomerDao getCustomerDao() {
        return new CustomerDao(dataSource);
    }

    /**
     * @return TargetRateDao
     * @see dao.TargetRateDao
     */
    public TargetRateDao getTargetRateDao() {
        return new TargetRateDao(dataSource);
    }
}
