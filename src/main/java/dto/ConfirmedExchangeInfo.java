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
    private BigInteger reservationNum;
    private Date exchangeDate;
    private String exchangeCode;
}
