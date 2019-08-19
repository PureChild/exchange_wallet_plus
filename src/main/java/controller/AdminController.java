package controller;

import dto.ConfirmedExchangeInfo;
import dto.Customer;
import dto.ReservationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import service.ConvertService;
import service.CustomerService;
import service.PaginationService;
import service.ReservationService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

/**
 * 관리자 기능 컨트롤러 클래스
 * @author 이승수
 */
@RequestMapping("/admin")
@Controller
public class AdminController {
    @Autowired
    ReservationService reservationService;
    @Autowired
    CustomerService customerService;
    @Autowired
    ConvertService convertService;
    @Autowired
    PaginationService paginationService;

    /**
     * 신청 내역을 조회
     * @param model view에 반환할 data를 담을 ModelMap
     * @param pageNum 페이지네이션을 위한 페이지 번호
     * @return 신청 내역 페이지
     */
    @GetMapping("/reservation/history/{pageNum}")
    public String getReservationInfos(ModelMap model, @PathVariable("pageNum") int pageNum){
        List<ReservationInfo> reservationInfoList = reservationService.getReservationInfos(pageNum);
        List<Customer> customerList = customerService.getNames(reservationInfoList);
        List<String> nationList = convertService.convertNationCodeToName(reservationInfoList);

        int numberOfPages = paginationService.getNumberOfPagesForAdmin();

        model.put("reservationList", reservationInfoList);
        model.put("nationList", nationList);
        model.put("customerList", customerList);
        model.put("numberOfPages", numberOfPages);
        model.put("nowPageNum", pageNum);

        return "admin/reservationHistory";
    }

    /**
     * 예약 번호를 통한 상세 정보 조회
     * @param model view에 반환할 data를 담을 ModelMap
     * @param num 예약 번호
     * @return 예약 상세 페이지
     */
    @GetMapping("/reservation/{reservationInfoNum}")
    public String getReservationInfoByNum(ModelMap model, @PathVariable("reservationInfoNum") BigInteger num){
        ReservationInfo reservationInfo = reservationService.getReservationInfoByNum(num);
        String nation = convertService.convertNationCodeToName(reservationInfo.getNationCode());

        model.put("reservationInfo", reservationInfo);
        model.put("nation", nation);
        return "admin/reservationInfo";
    }

    /**
     * 예약 정보 수정 페이지 요청
     * @param model view에 반환할 data를 담을 ModelMap
     * @param num 예약 번호
     * @return 예약 수정 페이지
     */
    @PostMapping("/update/reservation/{reservationInfoNum}")
    public String getUpdateReservationInfoPage(ModelMap model, @PathVariable("reservationInfoNum") BigInteger num){
        ReservationInfo reservationInfo = reservationService.getReservationInfoByNum(num);

        model.put("reservationInfo", reservationInfo);
        return "admin/updateReservationInfo";
    }

    /**
     * 예약 정보 수정
     * @param reservationInfo
     * @return 수정된 예약의 상세 정보 페이지
     */
    @PostMapping("/update/reservation")
    public String updateReservationInfo(@ModelAttribute ReservationInfo reservationInfo){
        reservationService.updateReservationInfo(reservationInfo);

        return "redirect:/admin/reservation/" + reservationInfo.getNum();
    }

    /**
     * 예약 정보 삭제
     * @param model view에 반환할 data를 담을 ModelMap
     * @param num 예약 번호
     * @return 신청 내역 페이지
     */
    @PostMapping("/delete/reservation/{reservationInfoNum}")
    public String deleteReservationInfo(ModelMap model, @PathVariable("reservationInfoNum") BigInteger num){
        reservationService.deleteReservationInfo(num);

        return "redirect:/admin/reservation/history/1";
    }

    /**
     * 환전 일자 설정
     * @param exchangeDate 환전 일자
     * @param num 예약 번호
     * @return 신청 내역 페이지
     */
    @PostMapping("/confirm/exchange/{reservationInfoNum}")
    public String setExchangeDate(@RequestParam("exchangeDate") Date exchangeDate, @PathVariable("reservationInfoNum") BigInteger num){
        ConfirmedExchangeInfo exchangeInfo = new ConfirmedExchangeInfo();
        exchangeInfo.setExchangeDate(exchangeDate);
        exchangeInfo.setReservationNum(num);

        reservationService.confirmExchangeReservation(exchangeInfo);

        return "redirect:/admin/reservation/history/1";
    }

    /**
     * 환전 코드 조회 페이지 요청
     * @param model view에 반환할 data를 담을 ModelMap
     * @param exchangeCode 환전 코드
     * @return 환전 코드 조회 페이지
     */
    @GetMapping("/lookup/{exchangeCode}")
    public String getLookupPage(ModelMap model, @PathVariable("exchangeCode") String exchangeCode){
        Optional<ReservationInfo> reservationInfo = reservationService.getReservationInfoByExchangeCode(exchangeCode);

        if(reservationInfo.isPresent()){
            model.put("result", reservationInfo.get());

            String nation = convertService.convertNationCodeToName(reservationInfo.get().getNationCode());
            model.put("nation", nation);
        } else {
            model.put("result", "not found");
        }

        return "admin/lookup";
    }

    /**
     * 예약 완료 페이지 요청
     * @param num 예약 번호
     * @return 환전 완료 페이지
     */
    @GetMapping("/close/exchange/{reservationInfoNum}")
    public String getFinishPage(@PathVariable("reservationInfoNum") BigInteger num){
        reservationService.closeExchangeReservation(num);

        return "admin/closeReservation";
    }
}
