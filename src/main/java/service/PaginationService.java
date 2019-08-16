package service;

import dao.DaoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaginationService {
    private final int DATA_PER_PAGE = 7;
    @Autowired
    private DaoFactory daoFactory;

    public int getDataPerPage() {
        return DATA_PER_PAGE;
    }

    private int getNumberOfPages(String userId, int totalCntOfReservations) {
        int numberOfPages = totalCntOfReservations / DATA_PER_PAGE;
        if(totalCntOfReservations % DATA_PER_PAGE != 0){
            numberOfPages++;
        }

        return numberOfPages;
    }

    public int getNumberOfPagesForReservationInfos(String userId){
        int totalCntOfReservations = daoFactory.getReservationDao().selectTotalCount(userId);
        return getNumberOfPages(userId, totalCntOfReservations);
    }

    public int getNumberOfPagesForTargetRates(String userId) {
        int totalCntOfTargetRates = daoFactory.getTargetRateDao().selectTotalCount(userId);
        return getNumberOfPages(userId, totalCntOfTargetRates);
    }
}
