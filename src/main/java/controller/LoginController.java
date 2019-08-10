package controller;

import dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.LoginService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/admin/loginConfirm")
    public String loginAdmin(RedirectAttributes redirectAttributes, @RequestParam String admId, @RequestParam String admPassword){
        String resultLoginCheck = loginService.checkAdminLoginInfo(admId, admPassword);
        redirectAttributes.addFlashAttribute("msg", resultLoginCheck);

        String returnUrl;
        if(resultLoginCheck.equals("loginOK")){
            returnUrl = "redirect:/admin/reservation/history";
        } else {
            returnUrl = "redirect:/admin/login";
        }
        return returnUrl;
    }

    @PostMapping("/joinConfirm")
    public String join(@ModelAttribute Customer customer){
        loginService.addCustomer(customer);

        return "redirect:/login";
    }

    @PostMapping("/loginConfirm")
    public String login(RedirectAttributes redirectAttributes, @ModelAttribute Customer customer){
        String resultLoginCheck = loginService.checkLoginInfo(customer);
        redirectAttributes.addFlashAttribute("msg", resultLoginCheck);

        String returnUrl;
        if(resultLoginCheck.equals("loginOK")){
            returnUrl = "redirect:/";
        } else {
            returnUrl = "redirect:/login";
        }
        return returnUrl;
    }
}
