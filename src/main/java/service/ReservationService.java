package service;

import dao.DaoFactory;
import dto.ConfirmedExchangeInfo;
import dto.ReservationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

/**
 * @author 이승수
 * 예약 처리 서비스
 */
@Service
public class ReservationService {
    @Autowired
    private DaoFactory daoFactory;
    @Autowired
    PaginationService paginationService;

    /**
     * @param pageNum 페이지네이션을 위한 페이지 번호
     * @return 해당 페이지의 예약 목록
     */
    public List<ReservationInfo> getReservationInfos(int pageNum) {
        int dataPerPage = paginationService.getDataPerPage();
        return daoFactory.getReservationDao().selectReservationInfos(pageNum, dataPerPage);
    }

    /**
     * @param userId 고객 ID
     * @param pageNum 페이지네이션을 위한 페이지 번호
     * @return 해당 고객, 해당 페이지의 예약 목록
     */
    public List<ReservationInfo> getReservationInfosById(String userId, int pageNum) {
        int dataPerPage = paginationService.getDataPerPage();
        return daoFactory.getReservationDao().selectReservationInfosById(userId, pageNum, dataPerPage);
    }

    /**
     * @param reservationInfoNum 예약 번호
     * @return 해당 예약 정보
     */
    public ReservationInfo getReservationInfoByNum(BigInteger reservationInfoNum) {
        return daoFactory.getReservationDao().selectReservationInfoByNum(reservationInfoNum);
    }

    /**
     * @param reservationInfo 예약 번호
     */
    public void updateReservationInfo(ReservationInfo reservationInfo) {
        daoFactory.getReservationDao().updateReservationInfo(reservationInfo);
    }

    /**
     * @param reservationInfoNum 예약 번호
     */
    public void deleteReservationInfo(BigInteger reservationInfoNum) {
        daoFactory.getReservationDao().deleteReservationInfo(reservationInfoNum);
    }

    /**
     * 환전일 확정. progress 0 → 1
     * @see dao.ReservationDao#updateReservationProgress(BigInteger)
     * @param exchangeInfo 환전 정보
     *                     @see dto.ConfirmedExchangeInfo
     */
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
        daoFactory.getReservationDao().updateReservationProgress(exchangeInfo.getReservationNum());
    }

    /**
     * @param exchangeCode 환전 코드
     * @return 예약 정보(nullable)
     */
    public Optional<ReservationInfo> getReservationInfoByExchangeCode(String exchangeCode) {
        ReservationInfo reservationInfo = daoFactory.getReservationDao().selectReservationInfoByExchangeCode(exchangeCode);

        return Optional.ofNullable(reservationInfo);
    }

    /**
     * 환전 종료. progress 1 → 2
     * @see dao.ReservationDao#updateReservationProgress(BigInteger)
     * @param reservationInfoNum 예약 번호
     */
    public void closeExchangeReservation(BigInteger reservationInfoNum) {
        daoFactory.getReservationDao().updateReservationProgress(reservationInfoNum);
    }

    /**
     * @param reservationInfo 예약 정보
     */
    public void makeReservation(ReservationInfo reservationInfo) {
        daoFactory.getReservationDao().insertReservationInfo(reservationInfo);
    }

    /**
     * @param userId 고객 ID
     * @param nationCode 국가 코드
     * @return 예약 정보
     */
    public ReservationInfo getReservationInfoByIdAndNationCode(String userId, String nationCode) {
        return daoFactory.getReservationDao().selectReservationInfoByIdAndNationCode(userId, nationCode);
    }

    /**
     * @param reservationInfoNum 예약 번호
     * @return 확정된 예약 정보
     */
    public ConfirmedExchangeInfo getConfirmedExchangeInfoByNum(BigInteger reservationInfoNum) {
        return daoFactory.getConfirmedExchangeDao().selectConfirmedExchangeInfo(reservationInfoNum);
    }
}