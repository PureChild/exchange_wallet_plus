package dto;

import lombok.Data;

import java.math.BigInteger;
import java.sql.Date;

@Data
public class ConfirmedExchangeInfo {
    private BigInteger reservationNum;
    private Date exchangeDate;
    private String exchangeCode;
}
