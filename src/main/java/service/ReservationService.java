package service;

import dao.DaoFactory;
import dao.ReservationDao;
import dto.ReservationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    @Autowired
    DaoFactory daoFactory;

    public List<ReservationInfo> getReservationInfos() {
        return daoFactory.getReservationDao().selectReservationInfos();
    }
}
