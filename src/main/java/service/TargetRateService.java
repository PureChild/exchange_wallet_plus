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
     * 고객 목표 환율 목록 조회
     * @param userId 고객 ID
     * @param pageNum 페이지네이션을 위한 페이지 번호
     * @return 해당 고객, 해당 페이지의 목표 환율 목록
     */
    public List<TargetRate> getTargetRatesById(String userId, int pageNum) {
        int dataPerPage = paginationService.DATA_PER_PAGE;
        return daoFactory.getTargetRateDao().selectTargetRatesById(userId, pageNum, dataPerPage);
    }

    /**
     * 목표 환율 상세 조회
     * @param userId 고객 ID
     * @param nationCode 국가 코드
     * @return 해당 고객, 해당 국가의 목표 환율 정보
     */
    public TargetRate getTargetRate(String userId, String nationCode) {
        return daoFactory.getTargetRateDao().selectTargetRate(userId, nationCode);
    }

    /**
     * 목표 환율 추가
     * @param targetRate 목표 환율 정보
     */
    public void addTargetRate(TargetRate targetRate) {
        daoFactory.getTargetRateDao().insertTargetRate(targetRate);
    }

    /**
     * 목표 환율 삭제
     * @param targetRate 목표 환율 정보
     */
    public void deleteTargetRate(TargetRate targetRate) {
        daoFactory.getTargetRateDao().deleteTargetRate(targetRate);
    }

    /**
     * 목표 환율 수정
     * @param originNationCode 기존 국가 코드
     * @param targetRate 목표 환율 정보
     */
    public void updateTargetRate(String originNationCode, TargetRate targetRate) {
        daoFactory.getTargetRateDao().updateTargetRate(originNationCode, targetRate);
    }
}
