package service;

import dao.DaoFactory;
import dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private DaoFactory daoFactory;

    public String checkAdminLoginInfo(String admId, String admPassword) {
        String returnMsg;
        if(this.admId.equals(admId) && this.admPassword.equals(admPassword)){
            returnMsg = "loginOK";
        } else {
            returnMsg = "관리자 아이디와 비밀번호를 확인해주세요";
        }
        return returnMsg;
    }

    public void addCustomer(Customer customer) {
        daoFactory.getCustomerDao().insertCustomer(customer);
    }

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
