package controller;

import dto.ReservationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import service.ReservationService;

import java.util.List;

@Controller
public class ApplicationController {
    @Autowired
    ReservationService reservationService;

    @RequestMapping("/application/history")
    public String getApplications(ModelMap model){
        String userId = "tester";
        List<ReservationInfo> reservationInfoList = reservationService.getReservationInfosById(userId);
        List<String> nationList = reservationService.convertNationCodeToName(reservationInfoList);
        List<String> progressList = reservationService.convertProgressCodeToString(reservationInfoList);

        model.put("applicationList", reservationInfoList);
        model.put("nationList", nationList);
        model.put("progressList", progressList);
        return "applicationHistory";
    }
}
