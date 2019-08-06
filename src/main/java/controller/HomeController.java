package controller;

import dao.DaoFactory;
import dao.TestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @Autowired
    DaoFactory daoFactory;

    @RequestMapping("/")
    public String showHome(){
        return "home";
    }

    @GetMapping("/test")
    public String getTestPage(ModelMap model){
        model.addAttribute("testData", "test");
        return "testPage";
    }
}
