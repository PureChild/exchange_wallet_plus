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
}
