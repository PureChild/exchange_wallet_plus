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

        model.put("applicationList", reservationInfoList);
        return "applicationHistory";
    }
}
