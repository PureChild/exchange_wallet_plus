package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.LoginService;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/admin/loginConfirm")
    public String adminLogin(HttpSession session, @RequestParam String admId, @RequestParam String admPassword){
        String[] resultLoginCheck = loginService.adminLoginCheck(admId, admPassword);
        session.setAttribute("msg", resultLoginCheck[1]);
        return resultLoginCheck[0];
    }
}
