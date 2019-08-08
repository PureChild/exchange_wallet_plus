package service;

import dao.DaoFactory;
import dto.ConfirmedExchangeInfo;
import dto.ReservationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;

@Service
@PropertySource(value = "classpath:exchange.api.properties", encoding="UTF-8")
public class ReservationService {
    @Autowired
    private DaoFactory daoFactory;
    @Resource
    private Environment environment;


    public List<ReservationInfo> getReservationInfos() {
        return daoFactory.getReservationDao().selectReservationInfos();
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

    public String convertNationCodeToName(String nationCode) {
        return environment.getProperty("nation.code." + nationCode);
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

    public Object getReservationInfoByExchangeCode(String exchangeCode) {
        ReservationInfo reservationInfo = daoFactory.getReservationDao().selectReservationInfoByExchangeCode(exchangeCode);

        System.out.println("서비스에서 " + reservationInfo);

        Object result = reservationInfo;
        if(result == null){
            result = "해당 정보가 존재하지 않습니다.";
        }
        return result;
    }
}
