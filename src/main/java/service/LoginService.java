package service;

import dao.DaoFactory;
import dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

/**
 * @author 이승수
 * 로그인, 회원 가입 서비스
 */
@Service
@PropertySource(value = "classpath:application.properties")
public class LoginService {
    /** 설정 파일에 기록된 관리자 ID */
    @Value("${admin.login.id}")
    private String admId;
    /** 설정 파일에 기록된 관리자 비밀 번호 */
    @Value("${admin.login.password}")
    private String admPassword;
    @Autowired
    private DaoFactory daoFactory;

    /**
     * @param admId 관리자 ID
     * @param admPassword 관리자 비밀 번호
     * @return 로그인 성공 : loginOK, 실패 : 관리자 아이디와 비밀번호를 확인해주세요
     */
    public String checkAdminLoginInfo(String admId, String admPassword) {
        String returnMsg;
        if(this.admId.equals(admId) && this.admPassword.equals(admPassword)){
            returnMsg = "loginOK";
        } else {
            returnMsg = "관리자 아이디와 비밀번호를 확인해주세요";
        }
        return returnMsg;
    }

    /**
     * @param customer 고객 정보
     */
    public void addCustomer(Customer customer) {
        daoFactory.getCustomerDao().insertCustomer(customer);
    }

    /**
     * @param customer 고객 정보
     * @return 로그인 성공 : loginOK, 실패 : 아이디와 비밀번호를 확인해주세요
     */
    public String checkLoginInfo(Customer customer) {
        String returnMsg;
        if(daoFactory.getCustomerDao().selectExistsCustomer(customer)){
            returnMsg = "loginOK";
        } else {
            returnMsg = "아이디와 비밀번호를 확인해주세요";
        }
        return returnMsg;
    }
}
