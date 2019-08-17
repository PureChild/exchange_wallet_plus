package dto;

import lombok.Data;

/**
 * customer 테이블 스키마를 갖는 DTO
 * @author 이승수
 */
@Data
public class Customer {
    private String id;
    private String pw;
    private String name;
    private String account;
}
