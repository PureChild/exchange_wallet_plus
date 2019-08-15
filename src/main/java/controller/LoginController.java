package controller;

import dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
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
    public String loginAdmin(HttpSession httpSession, RedirectAttributes redirectAttributes, @RequestParam String admId, @RequestParam String admPassword){
        String resultLoginCheck = loginService.checkAdminLoginInfo(admId, admPassword);
        redirectAttributes.addFlashAttribute("msg", resultLoginCheck);

        String returnUrl;
        if(resultLoginCheck.equals("loginOK")){
            httpSession.setAttribute("admin", "admin");
            returnUrl = "redirect:/admin/reservation/history";
        } else {
            returnUrl = "redirect:/admin/login";
        }
        return returnUrl;
    }

    @GetMapping("/admin/logout")
    public String logoutAdmin(HttpSession httpSession){
        httpSession.removeAttribute("admin");
        return "redirect:/admin/login";
    }

    @PostMapping("/joinConfirm")
    public String join(@ModelAttribute Customer customer){
        loginService.addCustomer(customer);

        return "redirect:/login";
    }

    @PostMapping("/loginConfirm")
    public String login(HttpSession httpSession, RedirectAttributes redirectAttributes, @ModelAttribute Customer customer){
        String resultLoginCheck = loginService.checkLoginInfo(customer);
        redirectAttributes.addFlashAttribute("msg", resultLoginCheck);

        String returnUrl;
        if(resultLoginCheck.equals("loginOK")){
            httpSession.setAttribute("loginUser", customer.getId());
            returnUrl = "redirect:/";
        } else {
            returnUrl = "redirect:/login";
        }
        return returnUrl;
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession){
        httpSession.removeAttribute("loginUser");
        return "redirect:/login";
    }
}
