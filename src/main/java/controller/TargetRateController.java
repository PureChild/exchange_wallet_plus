package controller;

import dto.TargetRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import service.ConvertService;
import service.PaginationService;
import service.TargetRateService;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

/**
 * 목표 환율 관련 컨트롤러 클래스
 * @author 이승수
 */
@Controller
public class TargetRateController {
    @Autowired
    TargetRateService targetRateService;
    @Autowired
    ConvertService convertService;
    @Autowired
    PaginationService paginationService;

    /**
     * @param httpSession
     * @param model view에 반환할 data를 담을 ModelMap
     * @param pageNum 페이지네이션을 위한 페이지 번호
     * @return 목표 환율 리스트
     */
    @GetMapping("/exchange/rates/{pageNum}")
    public String getTargetRates(HttpSession httpSession, ModelMap model, @PathVariable("pageNum") int pageNum){
        String userId = String.valueOf(httpSession.getAttribute("loginUser"));

        List<TargetRate> targetRates = targetRateService.getTargetRatesById(userId, pageNum);
        List<String> nationList = convertService.convertNationCodeToName(targetRates);

        int numberOfPages = paginationService.getNumberOfPagesForTargetRates(userId);

        model.put("targetRates", targetRates);
        model.put("nationList", nationList);
        model.put("numberOfPages", numberOfPages);
        model.put("nowPageNum", pageNum);

        return "targetRateList";
    }

    /**
     * @param httpSession
     * @param model view에 반환할 data를 담을 ModelMap
     * @param nationCode 국가 코드
     * @return 목표 환율 상세 페이지
     */
    @GetMapping("/exchange/rate/{nationCode}")
    public String getDetailTargetRate(HttpSession httpSession, ModelMap model, @PathVariable("nationCode") String nationCode){
        String userId = String.valueOf(httpSession.getAttribute("loginUser"));
        TargetRate targetRate = targetRateService.getTargetRate(userId, nationCode);
        String nation = convertService.convertNationCodeToName(nationCode);

        model.put("targetRateInfo", targetRate);
        model.put("nation", nation);

        return "detailTargetRate";
    }

    /**
     * @param httpSession
     * @param model view에 반환할 data를 담을 ModelMap
     * @param mode insert : 신규, update : 수정
     * @param nationCode 국가 코드
     * @return 목표 환율 작성 페이지
     */
    @GetMapping({"/{mode}/exchange/rate", "/{mode}/rate/nation/{nationCode}"})
    public String getInsertTargetRatePage(HttpSession httpSession, ModelMap model
                                            , @PathVariable("mode") String mode
                                            , @PathVariable("nationCode") Optional<String> nationCode){
        String userId = String.valueOf(httpSession.getAttribute("loginUser"));
        model.put("mode", mode);

        if(nationCode.isPresent()){
            model.put("nationCode", nationCode.get());
            model.put("targetRate", targetRateService.getTargetRate(userId, nationCode.get()));
        } else {
            model.put("nationCode", "");
        }

        return "newTargetRate";
    }

    /**
     * @param httpSession
     * @param targetRate 목표 환율
     * @return 목표 환율 리스트
     */
    @PostMapping("/insert/exchange/rate")
    public String addTargetExchangeRate(HttpSession httpSession, @ModelAttribute("targetRate") TargetRate targetRate){
        String userId = String.valueOf(httpSession.getAttribute("loginUser"));
        targetRate.setId(userId);

        targetRateService.addTargetRate(targetRate);

        return "redirect:/exchange/rates/1";
    }

    /**
     * @param httpSession
     * @param originNationCode 기존 국가 코드
     * @param targetRate 목표 환율
     * @return 목표 환율 리스트
     */
    @PostMapping("/update/exchange/rate")
    public String updateTargetExchangeRate(HttpSession httpSession
                                            , @RequestParam("originNationCode") String originNationCode
                                            , @ModelAttribute("targetRate") TargetRate targetRate){
        String userId = String.valueOf(httpSession.getAttribute("loginUser"));
        targetRate.setId(userId);

        targetRateService.updateTargetRate(originNationCode, targetRate);

        return "redirect:/exchange/rates/1";
    }

    /**
     * @param httpSession
     * @param nationCode 국가 코드
     * @return 목표 환율 리스트
     */
    @GetMapping("/delete/exchange/rate/{nationCode}")
    public String deleteTargetRate(HttpSession httpSession, @PathVariable("nationCode") String nationCode){
        String userId = String.valueOf(httpSession.getAttribute("loginUser"));

        TargetRate targetRate = new TargetRate();
        targetRate.setId(userId);
        targetRate.setNationCode(nationCode);

        targetRateService.deleteTargetRate(targetRate);

        return "redirect:/exchange/rates/1";
    }
}
