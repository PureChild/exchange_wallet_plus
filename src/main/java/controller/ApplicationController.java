package controller;

import dto.ConfirmedExchangeInfo;
import dto.ReservationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import service.ConvertService;
import service.PaginationService;
import service.ReservationService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 환전 신청 관련 컨트롤러 클래스
 * @author 이승수
 */
@Controller
public class ApplicationController {
    @Autowired
    ReservationService reservationService;
    @Autowired
    ConvertService convertService;
    @Autowired
    PaginationService paginationService;

    /**
     * @param httpSession
     * @param model view에 반환할 data를 담을 ModelMap
     * @param pageNum 페이지네이션을 위한 페이지 번호
     * @return 신청 내역 페이지
     */
    @GetMapping("/application/history/{pageNum}")
    public String getApplications(HttpSession httpSession, ModelMap model, @PathVariable("pageNum") int pageNum){
        String userId = String.valueOf(httpSession.getAttribute("loginUser"));

        List<ReservationInfo> reservationInfoList = reservationService.getReservationInfosById(userId, pageNum);
        List<String> nationList = convertService.convertNationCodeToName(reservationInfoList);
        List<String> progressList = convertService.convertProgressCodeToString(reservationInfoList);

        int numberOfPages = paginationService.getNumberOfPagesForReservationInfos(userId);

        model.put("applicationList", reservationInfoList);
        model.put("nationList", nationList);
        model.put("progressList", progressList);
        model.put("numberOfPages", numberOfPages);
        model.put("nowPageNum", pageNum);

        return "applicationHistory";
    }

    /**
     * @param httpSession
     * @param reservationInfo 예약 정보
     * @return 신청 내역 페이지
     */
    @PostMapping("/make/reservation")
    public String makeReservation(HttpSession httpSession, @ModelAttribute("reservationInfo") ReservationInfo reservationInfo){
        reservationInfo.setApplicant(String.valueOf(httpSession.getAttribute("loginUser")));
        reservationService.makeReservation(reservationInfo);

        return "redirect:/application/history/1";
    }

    /**
     * @param httpSession
     * @param model view에 반환할 data를 담을 ModelMap
     * @param nationCode 국가 코드
     * @return 승인된 신청 결과 페이지
     */
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
