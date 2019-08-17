package dto;

import lombok.Data;

/**
 * customer 테이블 스키마를 갖는 DTO
 * @author 이승수
 */
@Data
public class Customer {
    /** 고객 ID */
    private String id;
    /** 고객 비밀번호 */
    private String pw;
    /** 고객 이름 */
    private String name;
    /** 고객 계좌 */
    private String account;
}
