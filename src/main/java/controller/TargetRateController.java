package controller;

import dto.TargetRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.ConvertService;
import service.TargetRateService;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
public class TargetRateController {
    @Autowired
    TargetRateService targetRateService;
    @Autowired
    ConvertService convertService;

    @GetMapping("/exchange/rates")
    public String getTargetRates(HttpSession httpSession, ModelMap model){
        String userId = String.valueOf(httpSession.getAttribute("loginUser"));
        List<TargetRate> targetRates = targetRateService.getTargetRates(userId);
        List<String> nationList = convertService.convertNationCodeToName(targetRates);

        model.put("targetRates", targetRates);
        model.put("nationList", nationList);

        return "targetRateList";
    }

    @GetMapping("/exchange/rate/{nationCode}")
    public String getDetailTargetRate(HttpSession httpSession, ModelMap model, @PathVariable("nationCode") String nationCode){
        String userId = String.valueOf(httpSession.getAttribute("loginUser"));
        TargetRate targetRate = targetRateService.getTargetRate(userId, nationCode);
        String nation = convertService.convertNationCodeToName(nationCode);

        model.put("targetRateInfo", targetRate);
        model.put("nation", nation);

        return "detailTargetRate";
    }

    @GetMapping({"/{mode}/exchange/rate", "/{mode}/rate/nation/{nationCode}"})
    public String getInsertTargetRatePage(HttpSession httpSession, ModelMap model
                                            , @PathVariable("mode") String mode
                                            , @PathVariable("nationCode") Optional<String> nationCode){
        String userId = String.valueOf(httpSession.getAttribute("loginUser"));
        if(nationCode.isPresent()){
            model.put("nationCode", nationCode.get());
            model.put("targetRate", targetRateService.getTargetRate(userId, nationCode.get()));
        } else {
            model.put("nationCode", "");
        }

        return "newTargetRate";
    }

    @PostMapping("/insert/exchange/rate")
    public String addTargetExchangeRate(HttpSession httpSession, @ModelAttribute("targetRate") TargetRate targetRate){
        String userId = String.valueOf(httpSession.getAttribute("loginUser"));
        targetRate.setId(userId);

        targetRateService.addTargetRate(targetRate);

        return "redirect:/exchange/rates";
    }

    @PostMapping("/update/exchange/rate")
    public String updateTargetExchangeRate(HttpSession httpSession
                                            , @RequestParam("originNationCode") String originNationCode
                                            , @ModelAttribute("targetRate") TargetRate targetRate){
        String userId = String.valueOf(httpSession.getAttribute("loginUser"));
        targetRate.setId(userId);

        targetRateService.updateTargetRate(originNationCode, targetRate);

        return "redirect:/exchange/rates";
    }

    @GetMapping("/delete/exchange/rate/{nationCode}")
    public String deleteTargetRate(HttpSession httpSession, @PathVariable("nationCode") String nationCode){
        String userId = String.valueOf(httpSession.getAttribute("loginUser"));

        TargetRate targetRate = new TargetRate();
        targetRate.setId(userId);
        targetRate.setNationCode(nationCode);

        targetRateService.deleteTargetRate(targetRate);

        return "redirect:/exchange/rates";
    }
}
