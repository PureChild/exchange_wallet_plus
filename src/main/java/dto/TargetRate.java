package dto;

import lombok.Data;

@Data
public class TargetRate implements NationCodeContainer{
    private String nationCode;
    private String id;
    private double rate;
}
