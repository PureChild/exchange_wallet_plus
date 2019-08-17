package service;

import dao.DaoFactory;
import dto.TargetRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 목표 환율 서비스
 * @author 이승수
 */
@Service
public class TargetRateService {
    @Autowired
    private DaoFactory daoFactory;
    @Autowired
    PaginationService paginationService;

    /**
     * @param userId 고객 ID
     * @param pageNum 페이지네이션을 위한 페이지 번호
     * @return 해당 고객, 해당 페이지의 목표 환율 목록
     */
    public List<TargetRate> getTargetRatesById(String userId, int pageNum) {
        int dataPerPage = paginationService.getDataPerPage();
        return daoFactory.getTargetRateDao().selectTargetRatesById(userId, pageNum, dataPerPage);
    }

    /**
     * @param userId 고객 ID
     * @param nationCode 국가 코드
     * @return 해당 고객, 해당 국가의 목표 환율 정보
     */
    public TargetRate getTargetRate(String userId, String nationCode) {
        return daoFactory.getTargetRateDao().selectTargetRate(userId, nationCode);
    }

    /**
     * @param targetRate 목표 환율 정보
     */
    public void addTargetRate(TargetRate targetRate) {
        daoFactory.getTargetRateDao().insertTargetRate(targetRate);
    }

    /**
     * @param targetRate 목표 환율 정보
     */
    public void deleteTargetRate(TargetRate targetRate) {
        daoFactory.getTargetRateDao().deleteTargetRate(targetRate);
    }

    /**
     * @param originNationCode 기존 국가 코드
     * @param targetRate 목표 환율 정보
     */
    public void updateTargetRate(String originNationCode, TargetRate targetRate) {
        daoFactory.getTargetRateDao().updateTargetRate(originNationCode, targetRate);
    }
}
