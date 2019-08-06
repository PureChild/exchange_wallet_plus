package controller;

import dao.DaoFactory;
import dao.TestDao;
import dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
//    TestDao testDao;
    DaoFactory daoFactory;

    @RequestMapping("/")
    public String showHome(){
        return "home";
    }

    @GetMapping("/test")
    public String getTestPage(ModelMap model){
        List<Customer> testList = daoFactory.testDao().selectTest();
//        List<Customer> testList = testDao.selectTest();
        model.addAttribute("testData", testList);
        return "testPage";
    }
}
