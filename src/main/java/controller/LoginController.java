package controller;

import dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.LoginService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/admin/loginConfirm")
    public String loginAdmin(HttpSession session, @RequestParam String admId, @RequestParam String admPassword){
        String[] resultLoginCheck = loginService.checkAdminLoginInfo(admId, admPassword);
        session.setAttribute("msg", resultLoginCheck[1]);
        return resultLoginCheck[0];
    }

    @PostMapping("/joinConfirm")
    public String join(@ModelAttribute Customer customer){
        loginService.addCustomer(customer);

        return "redirect:/login";
    }

    @PostMapping("/loginConfirm")
    public String login(HttpSession session, @ModelAttribute Customer customer){
        String[] resultLoginCheck = loginService.checkLoginInfo(customer);
        session.setAttribute("msg", resultLoginCheck[1]);

        return resultLoginCheck[0];
    }
}
