package controller;

import dto.ReservationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.ConvertService;
import service.ReservationService;

import java.util.List;

@Controller
public class ApplicationController {
    @Autowired
    ReservationService reservationService;
    @Autowired
    ConvertService convertService;

    @GetMapping("/application/history")
    public String getApplications(ModelMap model){
        String userId = "tester";
        List<ReservationInfo> reservationInfoList = reservationService.getReservationInfosById(userId);
        List<String> nationList = convertService.convertNationCodeToName(reservationInfoList);
        List<String> progressList = convertService.convertProgressCodeToString(reservationInfoList);

        model.put("applicationList", reservationInfoList);
        model.put("nationList", nationList);
        model.put("progressList", progressList);
        return "applicationHistory";
    }

    @PostMapping("/make/reservation")
    public String makeReservation(@ModelAttribute("reservationInfo") ReservationInfo reservationInfo){
        reservationService.makeReservation(reservationInfo);

        return "redirect:/application/history";
    }
}
