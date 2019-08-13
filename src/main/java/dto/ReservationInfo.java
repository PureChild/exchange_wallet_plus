package dto;

import lombok.Data;

import java.math.BigInteger;
import java.sql.Date;

@Data
public class ReservationInfo implements NationCodeContainer{
    private BigInteger num;
    private String applicant;
    private int price;
    private Date departureDate;
    private String nationCode;
    private int progress;
}
