package controller;

import dto.ConfirmedExchangeInfo;
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
import java.math.BigInteger;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RequestMapping("/admin")
@Controller
public class AdminController {
    @Autowired
    ReservationService reservationService;

    @GetMapping("/reservation/history")
    public String getReservationInfos(ModelMap model){
        List<ReservationInfo> reservationInfoList = reservationService.getReservationInfos();

        model.put("reservationList", reservationInfoList);
        return "admin/reservationHistory";
    }

    @GetMapping("/reservation/{reservationInfoNum}")
    public String getReservationInfoByNum(ModelMap model, @PathVariable("reservationInfoNum") BigInteger num){
        ReservationInfo reservationInfo = reservationService.getReservationInfoByNum(num);
        String nation = reservationService.convertNationCodeToName(reservationInfo.getNationCode());

        model.put("reservationInfo", reservationInfo);
        model.put("nation", nation);
        return "admin/reservationInfo";
    }

    @GetMapping("/update/reservation/{reservationInfoNum}")
    public String getUpdateReservationInfoPage(ModelMap model, @PathVariable("reservationInfoNum") BigInteger num){
        ReservationInfo reservationInfo = reservationService.getReservationInfoByNum(num);

        model.put("reservationInfo", reservationInfo);
        return "admin/updateReservationInfo";
    }

    @PostMapping("/update/reservation")
    public String updateReservationInfo(HttpServletRequest request){
        ReservationInfo reservationInfo = new ReservationInfo();
        BigInteger reservationNum = new BigInteger(request.getParameter("reservationNum"));
        reservationInfo.setNum(reservationNum);
        reservationInfo.setNationCode(request.getParameter("nationCode"));
        reservationInfo.setPrice(Integer.valueOf(request.getParameter("price")));

        reservationService.updateReservationInfo(reservationInfo);

        return "redirect:/admin/reservation/" + reservationNum;
    }

    @GetMapping("/delete/reservation/{reservationInfoNum}")
    public String deleteReservationInfo(ModelMap model, @PathVariable("reservationInfoNum") BigInteger num){
        reservationService.deleteReservationInfo(num);

        return "redirect:/admin/reservation/history";
    }

    @PostMapping("/confirm/exchange/{reservationInfoNum}")
    public String setExchangeDate(HttpServletRequest request, @PathVariable("reservationInfoNum") BigInteger num){
        ConfirmedExchangeInfo exchangeInfo = new ConfirmedExchangeInfo();
        exchangeInfo.setExchangeDate(Date.valueOf(request.getParameter("exchangeDate")));
        exchangeInfo.setReservationNum(num);

        reservationService.confirmExchangeReservation(exchangeInfo);

        return "redirect:/admin/reservation/history";
    }

    @GetMapping("/lookup/{exchangeCode}")
    public String getLookupPage(ModelMap model, @PathVariable("exchangeCode") String exchangeCode){
        Optional<ReservationInfo> reservationInfo = reservationService.getReservationInfoByExchangeCode(exchangeCode);

        if(reservationInfo.isPresent()){
            model.put("result", reservationInfo.get());

            String nation = reservationService.convertNationCodeToName(reservationInfo.get().getNationCode());
            model.put("nation", nation);
        } else {
            model.put("result", "not found");
        }

        return "admin/lookup";
    }

    @GetMapping("/close/exchange/{reservationInfoNum}")
    public String getFinishPage(@PathVariable("reservationInfoNum") BigInteger num){
        reservationService.closeExchangeReservation(num);

        return "admin/closeReservation";
    }
}
