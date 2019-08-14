package service;

import dao.DaoFactory;
import dto.TargetRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TargetRateService {
    @Autowired
    private DaoFactory daoFactory;

    public List<TargetRate> getTargetRates(String userId) {
        return daoFactory.getTargetRateDao().selectAllTargetRates(userId);
    }

    public TargetRate getTargetRate(String userId, String nationCode) {
        return daoFactory.getTargetRateDao().selectTargetRate(userId, nationCode);
    }

    public void addTargetRate(TargetRate targetRate) {
        daoFactory.getTargetRateDao().insertTargetRate(targetRate);
    }

    public void deleteTargetRate(TargetRate targetRate) {
        daoFactory.getTargetRateDao().deleteTargetRate(targetRate);
    }
}
