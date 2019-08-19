package service;

import dao.DaoFactory;
import dto.Customer;
import dto.ReservationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Customer 관련 서비스
 * @author 이승수
 */
@Service
public class CustomerService {
    @Autowired
    DaoFactory daoFactory;

    /**
     * 예약 정보에 해당하는 고객명 리스트 조회
     * @param reservationInfoList 예약 리스트
     * @return 고객 이름 리스트
     */
    public List<Customer> getNames(List<ReservationInfo> reservationInfoList) {
        List<Customer> customerList = new ArrayList<>();

        for(ReservationInfo reservationInfo : reservationInfoList){
            Customer customer = new Customer();
            customer.setName(daoFactory.getCustomerDao().selectCustomerName(reservationInfo.getApplicant()));

            customerList.add(customer);
        }
        return customerList;
    }
}
