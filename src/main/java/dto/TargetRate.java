package dto;

import lombok.Data;

/**
 * target_rate 테이블 스키마를 갖는 DTO
 * @author 이승수
 */
@Data
public class TargetRate implements NationCodeContainer{
    private String nationCode;
    private String id;
    private double rate;
}
