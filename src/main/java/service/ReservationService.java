package service;

import dao.DaoFactory;
import dto.ReservationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@PropertySource(value = "classpath:exchange.api.properties", encoding="UTF-8")
public class ReservationService {
    @Autowired
    private DaoFactory daoFactory;
    @Resource
    private Environment environment;


    public List<ReservationInfo> getAllReservationInfos() {
        return daoFactory.getReservationDao().selectAllReservationInfos();
    }

    public ReservationInfo getReservationInfoByNum(int reservationInfoNum) {
        return daoFactory.getReservationDao().selectReservationInfoByNum(reservationInfoNum);
    }

    public void updateReservationInfo(ReservationInfo reservationInfo) {
        daoFactory.getReservationDao().updateReservationInfo(reservationInfo);
    }

    public void deleteReservationInfo(int reservationInfoNum) {
        daoFactory.getReservationDao().deleteReservationInfo(reservationInfoNum);
    }

    public String convertNationCodeToName(String nationCode) {
        return environment.getProperty("nation.code." + nationCode);
    }
}
