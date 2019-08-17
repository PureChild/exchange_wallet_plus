package service;

import dto.NationCodeContainer;
import dto.ReservationInfo;
import dto.TargetRate;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 국가코드 ↔ 국가명 변환 서비스
 * @author 이승수
 */
@Service
@PropertySource(value = "classpath:exchange.api.properties", encoding="UTF-8")
public class ConvertService {
    /** 설정 파일(국가코드-국가명 매핑)에서 읽어온 환경 변수 */
    @Resource
    private Environment environment;

    /**
     * @param nationCode 국가 코드
     * @return 국가명
     */
    public String convertNationCodeToName(String nationCode) {
        return environment.getProperty("nation.code." + nationCode);
    }

    /**
     * @param nationCodeContainerList 국가 코드 리스트
     * @param <T> NationCodeContainer를 상속 받는 DTO
     *           @see dto.NationCodeContainer
     * @return 국가명 리스트
     */
    public <T extends NationCodeContainer> List<String> convertNationCodeToName(List<T> nationCodeContainerList) {
        List<String> nationList = new ArrayList<>();
        for(T nationCodeContainer : nationCodeContainerList){
            nationList.add(environment.getProperty("nation.code." + nationCodeContainer.getNationCode()));
        }

        return nationList;
    }

    /**
     * @param reservationInfoList 진행 상황 코드 리스트
     *                            @see dto.ReservationInfo
     * @return 국가명 리스트
     */
    public List<String> convertProgressCodeToString(List<ReservationInfo> reservationInfoList) {
        List<String> progressList = new ArrayList<>();
        for(ReservationInfo reservation : reservationInfoList){
            progressList.add(environment.getProperty("progress." + reservation.getProgress()));
        }

        return progressList;
    }
}
