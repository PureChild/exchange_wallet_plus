package dto;

import lombok.Data;

/**
 * @author 이승수
 * customer 테이블 스키마를 갖는 DTO
 */
@Data
public class Customer {
    private String id;
    private String pw;
    private String name;
    private String account;
}
