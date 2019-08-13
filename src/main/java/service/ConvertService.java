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

@Service
@PropertySource(value = "classpath:exchange.api.properties", encoding="UTF-8")
public class ConvertService {
    @Resource
    private Environment environment;

    public String convertNationCodeToName(String nationCode) {
        return environment.getProperty("nation.code." + nationCode);
    }

    public <T extends NationCodeContainer> List<String> convertNationCodeToName(List<T> nationCodeContainerList) {
        List<String> nationList = new ArrayList<>();
        for(T nationCodeContainer : nationCodeContainerList){
            nationList.add(environment.getProperty("nation.code." + nationCodeContainer.getNationCode()));
        }

        return nationList;
    }

    public List<String> convertProgressCodeToString(List<ReservationInfo> reservationInfoList) {
        List<String> progressList = new ArrayList<>();
        for(ReservationInfo reservation : reservationInfoList){
            progressList.add(environment.getProperty("progress." + reservation.getProgress()));
        }

        return progressList;
    }
}
