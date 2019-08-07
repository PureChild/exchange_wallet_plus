package controller;

import dto.ReservationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import service.ReservationService;

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    ReservationService reservationService;

    @GetMapping("/admin/reservation/history")
    public String getReservationInfo(ModelMap model){
        List<ReservationInfo> reservationInfoList = reservationService.getReservationInfos();

        model.put("reservationList", reservationInfoList);
        return "adminHistory";
    }
}
