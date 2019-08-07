package controller;

import dto.ReservationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import service.ReservationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;
import java.util.List;

@RequestMapping("/admin")
@Controller
public class AdminController {
    @Autowired
    ReservationService reservationService;

    @GetMapping("/reservation/history")
    public String getAllReservationInfos(ModelMap model){
        List<ReservationInfo> reservationInfoList = reservationService.getAllReservationInfos();

        model.put("reservationList", reservationInfoList);
        return "adminHistory";
    }

    @GetMapping("/reservation/{reservationInfoNum}")
    public String getReservationInfoByNum(ModelMap model, @PathVariable("reservationInfoNum") int num){
        ReservationInfo reservationInfo = reservationService.getReservationInfoByNum(num);

        model.put("reservationInfo", reservationInfo);
        return "adminReservationInfo";
    }

    @GetMapping("/update/reservation/{reservationInfoNum}")
    public String getUpdateReservationInfoPage(ModelMap model, @PathVariable("reservationInfoNum") int num){
        ReservationInfo reservationInfo = reservationService.getReservationInfoByNum(num);

        model.put("reservationInfo", reservationInfo);
        return "adminUpdateReservationInfo";
    }

    @PostMapping("/update/reservation")
    public String updateReservationInfo(HttpServletRequest request){
        ReservationInfo reservationInfo = new ReservationInfo();
        reservationInfo.setNum(new BigInteger(request.getParameter("reservationNum")));
        reservationInfo.setNationCode(request.getParameter("nationCode"));
        reservationInfo.setPrice(Integer.valueOf(request.getParameter("price")));

        reservationService.updateReservationInfo(reservationInfo);

        return "redirect:/admin/reservation/history";
    }

    @GetMapping("/delete/reservation/{reservationInfoNum}")
    public String deleteReservationInfo(ModelMap model, @PathVariable("reservationInfoNum") int num){
        reservationService.deleteReservationInfo(num);

        return "redirect:/admin/reservation/history";
    }
}
