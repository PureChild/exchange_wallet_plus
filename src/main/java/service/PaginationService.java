package service;

import dao.DaoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 이승수
 * 페이지네이션 서비스
 */
@Service
public class PaginationService {
    /** 한 페이지에 보여질 데이터 수 */
    private final int DATA_PER_PAGE = 7;
    @Autowired
    private DaoFactory daoFactory;

    /**
     * @return 한 페이지에 보여질 데이터 수
     * @see #DATA_PER_PAGE
     */
    public int getDataPerPage() {
        return DATA_PER_PAGE;
    }

    /**
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
     * @param userId 고객 ID
     * @return 해당 고객의 예약 정보 페이지 수
     * @see #getNumberOfPages(int)
     */
    public int getNumberOfPagesForReservationInfos(String userId){
        int totalCntOfReservations = daoFactory.getReservationDao().selectTotalCountById(userId);
        return getNumberOfPages(totalCntOfReservations);
    }

    /**
     * @param userId 고객 ID
     * @return 해당 고객의 목표 환율 정보 페이지 수
     * @see #getNumberOfPages(int)
     */
    public int getNumberOfPagesForTargetRates(String userId) {
        int totalCntOfTargetRates = daoFactory.getTargetRateDao().selectTotalCountById(userId);
        return getNumberOfPages(totalCntOfTargetRates);
    }

    /**
     * @return 관리자 신청 내역의 페이지 수
     * @see #getNumberOfPages(int)
     */
    public int getNumberOfPagesForAdmin() {
        int totalCntOfReservations = daoFactory.getReservationDao().selectTotalCount();
        return getNumberOfPages(totalCntOfReservations);
    }
}
