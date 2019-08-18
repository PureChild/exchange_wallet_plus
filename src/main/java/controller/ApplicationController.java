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
     * 신청 내역 조회
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
     * 예약 신청
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
     * 확정된 환전 결과 조회
     * @param httpSession
     * @param model view에 반환할 data를 담을 ModelMap
     * @param reservationNum 예약 번호
     * @return 승인된 신청 결과 페이지
     */
    @GetMapping("/application/result/{reservationNum}")
    public String getApplicationResult(HttpSession httpSession, ModelMap model, @PathVariable("reservationNum") String reservationNum){
        String userId = String.valueOf(httpSession.getAttribute("loginUser"));
        ReservationInfo reservationInfo = reservationService.getReservationInfoByIdAndNum(userId, reservationNum);
        ConfirmedExchangeInfo confirmedExchangeInfo = reservationService.getConfirmedExchangeInfoByNum(reservationInfo.getNum());
        String nation = convertService.convertNationCodeToName(reservationInfo.getNationCode());

        model.put("reservationInfo", reservationInfo);
        model.put("confirmedExchangeInfo", confirmedExchangeInfo);
        model.put("nation", nation);

        return "applicationResult";
    }
}
