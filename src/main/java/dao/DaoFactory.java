package dao;

import dto.ConfirmedExchangeInfo;
import dto.Customer;
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

    public ReservationDao getReservationDao(){
        return new ReservationDao(dataSource);
    }

    public ConfirmedExchangeDao getConfirmedExchangeDao() {
        return  new ConfirmedExchangeDao(dataSource);
    }

    public CustomerDao getCustomerDao() {
        return new CustomerDao(dataSource);
    }
}
