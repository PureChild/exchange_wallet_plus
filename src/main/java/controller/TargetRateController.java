package controller;

import dto.TargetRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.ConvertService;
import service.TargetRateService;

import java.util.List;
import java.util.Optional;

@Controller
public class TargetRateController {
    @Autowired
    TargetRateService targetRateService;
    @Autowired
    ConvertService convertService;

    @GetMapping("/exchange/rates")
    public String getTargetRates(ModelMap model){
        String id = "tester";
        List<TargetRate> targetRates = targetRateService.getTargetRates(id);
        List<String> nationList = convertService.convertNationCodeToName(targetRates);

        model.put("targetRates", targetRates);
        model.put("nationList", nationList);

        return "targetRateList";
    }

    @GetMapping("/exchange/rate/{nationCode}")
    public String getDetailTargetRate(ModelMap model, @PathVariable("nationCode") String nationCode){
        String id = "tester";
        TargetRate targetRate = targetRateService.getTargetRate(id, nationCode);
        String nation = convertService.convertNationCodeToName(nationCode);

        model.put("targetRateInfo", targetRate);
        model.put("nation", nation);

        return "detailTargetRate";
    }

    @GetMapping({"/{mode}/exchange/rate", "/{mode}/rate/nation/{nationCode}"})
    public String getInsertTargetRatePage(ModelMap model
                                            , @PathVariable("mode") String mode
                                            , @PathVariable("nationCode") Optional<String> nationCode){
        String id = "tester";
        if(nationCode.isPresent()){
            model.put("nationCode", nationCode.get());
            model.put("targetRate", targetRateService.getTargetRate(id, nationCode.get()));
        } else {
            model.put("nationCode", "");
        }

        return "newTargetRate";
    }

    @PostMapping("/insert/exchange/rate")
    public String addTargetExchangeRate(@ModelAttribute("targetRate") TargetRate targetRate){
        String id = "tester";
        targetRate.setId(id);

        targetRateService.addTargetRate(targetRate);

        return "redirect:/exchange/rates";
    }

    @PostMapping("/update/exchange/rate")
    public String updateTargetExchangeRate(@RequestParam("originNationCode") String originNationCode
                                            , @ModelAttribute("targetRate") TargetRate targetRate){
        String id = "tester";
        targetRate.setId(id);

        targetRateService.updateTargetRate(originNationCode, targetRate);

        return "redirect:/exchange/rates";
    }

    @GetMapping("/delete/exchange/rate/{nationCode}")
    public String deleteTargetRate(@PathVariable("nationCode") String nationCode){
        String id = "tester";

        TargetRate targetRate = new TargetRate();
        targetRate.setId(id);
        targetRate.setNationCode(nationCode);

        targetRateService.deleteTargetRate(targetRate);

        return "redirect:/exchange/rates";
    }
}
