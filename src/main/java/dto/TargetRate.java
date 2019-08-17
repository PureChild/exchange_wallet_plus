package dto;

import lombok.Data;

/**
 * @author 이승수
 * target_rate 테이블 스키마를 갖는 DTO
 */
@Data
public class TargetRate implements NationCodeContainer{
    private String nationCode;
    private String id;
    private double rate;
}
