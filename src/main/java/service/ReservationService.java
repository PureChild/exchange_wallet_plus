package service;

import dao.DaoFactory;
import dto.ConfirmedExchangeInfo;
import dto.ReservationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    @Autowired
    private DaoFactory daoFactory;
    @Autowired
    PaginationService paginationService;

    public List<ReservationInfo> getReservationInfos(int pageNum) {
        int dataPerPage = paginationService.getDataPerPage();
        return daoFactory.getReservationDao().selectReservationInfos(pageNum, dataPerPage);
    }

    public List<ReservationInfo> getReservationInfosById(String userId, int pageNum) {
        int dataPerPage = paginationService.getDataPerPage();
        return daoFactory.getReservationDao().selectReservationInfosById(userId, pageNum, dataPerPage);
    }

    public ReservationInfo getReservationInfoByNum(BigInteger reservationInfoNum) {
        return daoFactory.getReservationDao().selectReservationInfoByNum(reservationInfoNum);
    }

    public void updateReservationInfo(ReservationInfo reservationInfo) {
        daoFactory.getReservationDao().updateReservationInfo(reservationInfo);
    }

    public void deleteReservationInfo(BigInteger reservationInfoNum) {
        daoFactory.getReservationDao().deleteReservationInfo(reservationInfoNum);
    }

    public void confirmExchangeReservation(ConfirmedExchangeInfo exchangeInfo) {
        String hexCode = exchangeInfo.getReservationNum().toString(16);
        String dateCode = exchangeInfo.getExchangeDate().toString().replaceAll("-", "");

        // 환전 코드 10자리로
        String exchangeCode = dateCode + hexCode;
        if(exchangeCode.length() >= 10){
            exchangeCode = exchangeCode.substring(exchangeCode.length() - 10);
        } else {
            for(int i = exchangeCode.length(); i < 10; i++){
                exchangeCode = "0" + exchangeCode;
            }
        }

        exchangeInfo.setExchangeCode(exchangeCode);

        daoFactory.getConfirmedExchangeDao().insertConfirmedReservationInfo(exchangeInfo);
        // progress 0: 예약 신청, 1: 관리자 예약 확정, 2: 사용자 환전 완료
        // progress 컬럼 업데이트
        daoFactory.getReservationDao().updateReservationProgress(exchangeInfo.getReservationNum());
    }

    public Optional<ReservationInfo> getReservationInfoByExchangeCode(String exchangeCode) {
        ReservationInfo reservationInfo = daoFactory.getReservationDao().selectReservationInfoByExchangeCode(exchangeCode);

        return Optional.ofNullable(reservationInfo);
    }

    public void closeExchangeReservation(BigInteger reservationInfoNum) {
        daoFactory.getReservationDao().updateReservationProgress(reservationInfoNum);
    }

    public void makeReservation(ReservationInfo reservationInfo) {
        daoFactory.getReservationDao().insertReservationInfo(reservationInfo);
    }

    public ReservationInfo getReservationInfoByIdAndNationCode(String id, String nationCode) {
        return daoFactory.getReservationDao().selectReservationInfoByIdAndNationCode(id, nationCode);
    }

    public ConfirmedExchangeInfo getConfirmedExchangeInfoByNum(BigInteger reservationInfoNum) {
        return daoFactory.getConfirmedExchangeDao().selectConfirmedExchangeInfo(reservationInfoNum);
    }
}