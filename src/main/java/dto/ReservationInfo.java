package dto;

import lombok.Data;

import java.math.BigInteger;
import java.sql.Date;

/**
 * @author 이승수
 * reservation_info 테이블 스키마를 갖는 DTO
 */
@Data
public class ReservationInfo implements NationCodeContainer{
    private BigInteger num;
    private String applicant;
    private int price;
    private Date departureDate;
    private String nationCode;
    private int progress;
}
