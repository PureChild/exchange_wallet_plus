package controller;

import dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.LoginService;

import javax.servlet.http.HttpSession;

/**
 * 로그인, 회원가입 컨트롤러 클래스
 * @author 이승수
 */
@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;

    /**
     * 관리자 로그인
     * @param httpSession
     * @param redirectAttributes 로그인 결과 메세지
     * @param admId 관리자 ID
     * @param admPassword 관리자 패스워드
     * @return 로그인 성공 : 신청내역, 실패 : 로그인 페이지
     */
    @PostMapping("/admin/loginConfirm")
    public String loginAdmin(HttpSession httpSession, RedirectAttributes redirectAttributes, @RequestParam String admId, @RequestParam String admPassword){
        String resultLoginCheck = loginService.checkAdminLoginInfo(admId, admPassword);
        redirectAttributes.addFlashAttribute("msg", resultLoginCheck);

        String returnUrl;
        if(resultLoginCheck.equals("loginOK")){
            httpSession.setAttribute("admin", "admin");
            returnUrl = "redirect:/admin/reservation/history/1";
        } else {
            returnUrl = "redirect:/admin/login";
        }
        return returnUrl;
    }

    /**
     * 관리자 로그아웃
     * @param httpSession
     * @return 관리자 로그인 페이지
     */
    @GetMapping("/admin/logout")
    public String logoutAdmin(HttpSession httpSession){
        httpSession.removeAttribute("admin");
        return "redirect:/admin/login";
    }

    /**
     * @param customer 회원가입 정보
     * @return 로그인 페이지
     */
    @PostMapping("/joinConfirm")
    public String join(@ModelAttribute Customer customer){
        loginService.addCustomer(customer);

        return "redirect:/login";
    }

    /**
     * 고객 로그인
     * @param httpSession
     * @param redirectAttributes 로그인 결과 메세지
     * @param customer 로그인 고객 정보
     * @return 로그인 성공 : 메인, 실패 : 로그인 페이지
     */
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

    /**
     * 고객 로그아웃
     * @param httpSession
     * @return 로그인 페이지
     */
    @GetMapping("/logout")
    public String logout(HttpSession httpSession){
        httpSession.removeAttribute("loginUser");
        return "redirect:/login";
    }
}
