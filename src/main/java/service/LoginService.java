package service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@PropertySource(value = "classpath:application.properties")
public class LoginService {
    @Value("${admin.login.id}")
    private String admId;
    @Value("${admin.login.password}")
    private String admPassword;

    public String[] adminLoginCheck(String admId, String admPassword) {
        String returnPage, returnMsg;
        if(this.admId.equals(admId) && this.admPassword.equals(admPassword)){
            returnPage = "redirect:/admin/reservation/history";
            returnMsg = "";
        } else {
            returnPage = "admin/login";
            returnMsg = "관리자 아이디와 비밀번호를 확인해주세요";
        }
        return new String[]{returnPage, returnMsg};
    }
}
