package controller;

import dto.TargetRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.ConvertService;
import service.TargetRateService;

import java.util.List;

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

    @PostMapping("/insert/exchange/rate")
    public String addTargetExchangeRate(@ModelAttribute("targetRate") TargetRate targetRate){
        String id = "tester";
        targetRate.setId(id);

        targetRateService.addTargetRate(targetRate);

        return "redirect:/exchange/rates";
    }
}
