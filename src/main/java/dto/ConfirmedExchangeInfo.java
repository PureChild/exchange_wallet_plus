package dto;

import lombok.Data;

import java.math.BigInteger;
import java.sql.Date;

/**
 * confirmed_exchange_info 테이블 스키마를 갖는 DTO
 * @author 이승수
 */
@Data
public class ConfirmedExchangeInfo {
    /** 예약 번호 */
    private BigInteger reservationNum;
    /** 환전 일자 */
    private Date exchangeDate;
    /** 환전 코드 */
    private String exchangeCode;
}
