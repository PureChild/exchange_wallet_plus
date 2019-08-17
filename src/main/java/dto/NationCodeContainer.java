package dto;

/**
 * 국가 코드를 갖는 DTO를 위한 인터페이스
 * @author 이승수
 */
public interface NationCodeContainer {
    /**
     * 국가 코드 반환
     * @return 국가 코드
     * @implSpec return : 국가 코드
     */
    public String getNationCode();
}
