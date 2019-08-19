package service;

import dao.DaoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 페이지네이션 서비스
 * @author 이승수
 */
@Service
public class PaginationService {
    /** 한 페이지에 보여질 데이터 수 */
    public static final int DATA_PER_PAGE = 7;
    @Autowired
    private DaoFactory daoFactory;

    /**
     * 페이지 수 조회
     * @param totalCnt 페이지네이션을 적용할 데이터 수
     * @return 페이지 끝번호
     */
    private int getNumberOfPages(int totalCnt) {
        int numberOfPages = totalCnt / DATA_PER_PAGE;
        if(totalCnt % DATA_PER_PAGE != 0){
            numberOfPages++;
        }

        return numberOfPages;
    }

    /**
     * 고객 신청내역 페이지 페이지 수 조회
     * @param userId 고객 ID
     * @return 해당 고객의 예약 정보 페이지 수
     * @see #getNumberOfPages(int)
     */
    public int getNumberOfPagesForReservationInfos(String userId){
        int totalCntOfReservations = daoFactory.getReservationDao().selectTotalCountById(userId);
        return getNumberOfPages(totalCntOfReservations);
    }

    /**
     * 고객 목표 환율 페이지 페이지 수 조회
     * @param userId 고객 ID
     * @return 해당 고객의 목표 환율 정보 페이지 수
     * @see #getNumberOfPages(int)
     */
    public int getNumberOfPagesForTargetRates(String userId) {
        int totalCntOfTargetRates = daoFactory.getTargetRateDao().selectTotalCountById(userId);
        return getNumberOfPages(totalCntOfTargetRates);
    }

    /**
     * 관리자 신청내역 페이지 페이지 수 조회
     * @return 관리자 신청 내역의 페이지 수
     * @see #getNumberOfPages(int)
     */
    public int getNumberOfPagesForAdmin() {
        int totalCntOfReservations = daoFactory.getReservationDao().selectTotalCount();
        return getNumberOfPages(totalCntOfReservations);
    }
}
