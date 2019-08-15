package controller;

import dto.ConfirmedExchangeInfo;
import dto.ReservationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import service.ConvertService;
import service.ReservationService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ApplicationController {
    @Autowired
    ReservationService reservationService;
    @Autowired
    ConvertService convertService;

    @GetMapping("/application/history")
    public String getApplications(HttpSession httpSession, ModelMap model){
        String userId = String.valueOf(httpSession.getAttribute("loginUser"));
        List<ReservationInfo> reservationInfoList = reservationService.getReservationInfosById(userId);
        List<String> nationList = convertService.convertNationCodeToName(reservationInfoList);
        List<String> progressList = convertService.convertProgressCodeToString(reservationInfoList);

        model.put("applicationList", reservationInfoList);
        model.put("nationList", nationList);
        model.put("progressList", progressList);
        return "applicationHistory";
    }

    @PostMapping("/make/reservation")
    public String makeReservation(HttpSession httpSession, @ModelAttribute("reservationInfo") ReservationInfo reservationInfo){
        reservationInfo.setApplicant(String.valueOf(httpSession.getAttribute("loginUser")));
        reservationService.makeReservation(reservationInfo);

        return "redirect:/application/history";
    }

    @GetMapping("/application/result/{nationCode}")
    public String getApplicationResult(HttpSession httpSession, ModelMap model, @PathVariable("nationCode") String nationCode){
        String userId = String.valueOf(httpSession.getAttribute("loginUser"));
        ReservationInfo reservationInfo = reservationService.getReservationInfoByIdAndNationCode(userId, nationCode);
        ConfirmedExchangeInfo confirmedExchangeInfo = reservationService.getConfirmedExchangeInfoByNum(reservationInfo.getNum());
        String nation = convertService.convertNationCodeToName(nationCode);

        model.put("reservationInfo", reservationInfo);
        model.put("confirmedExchangeInfo", confirmedExchangeInfo);
        model.put("nation", nation);

        return "applicationResult";
    }
}
