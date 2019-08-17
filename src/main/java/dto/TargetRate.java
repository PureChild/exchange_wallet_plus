package dto;

import lombok.Data;

/**
 * target_rate 테이블 스키마를 갖는 DTO
 * @author 이승수
 */
@Data
public class TargetRate implements NationCodeContainer{
    /** 국가 코드 */
    private String nationCode;
    /** 고객 ID */
    private String id;
    /** 목표 환율 */
    private double rate;
}
