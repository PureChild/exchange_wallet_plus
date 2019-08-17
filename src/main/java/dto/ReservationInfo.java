package dto;

import lombok.Data;

import java.math.BigInteger;
import java.sql.Date;

/**
 * reservation_info 테이블 스키마를 갖는 DTO
 * @author 이승수
 */
@Data
public class ReservationInfo implements NationCodeContainer{
    /** 예약 번호 */
    private BigInteger num;
    /** 신청자 ID */
    private String applicant;
    /** 환전 금액 */
    private int price;
    /** 여행 출발 일자 */
    private Date departureDate;
    /** 국가 코드 */
    private String nationCode;
    /** 예약 진행 상황(0: 예약 신청, 1: 관리자 예약 확정, 2: 사용자 환전 완료) */
    private int progress;
}
