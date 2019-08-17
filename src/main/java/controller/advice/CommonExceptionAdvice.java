package controller.advice;

import com.mysql.cj.exceptions.CJCommunicationsException;
import com.mysql.cj.jdbc.exceptions.CommunicationsException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.net.ConnectException;
import java.sql.SQLException;

@ControllerAdvice(annotations = Controller.class)
public class CommonExceptionAdvice {
    private ModelAndView showErrorPage(Exception e, HttpServletRequest request, String msg) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/errorPage");
//        mav.addObject("url", request.getRequestURL());
//        mav.addObject("exception", e);
        mav.addObject("errorMsg", msg);
        return mav;
    }

    @ExceptionHandler(BindException.class)
    public ModelAndView handleBindException(BindException exception, HttpServletRequest request) {
        return showErrorPage(exception, request, "잘못된 요청입니다.");
    }

    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView handleNumberFormatException(NumberFormatException exception, HttpServletRequest request) {
        return showErrorPage(exception, request, "숫자 형식이 이상합니다.");
    }

    @ExceptionHandler(SQLException.class)
    public ModelAndView handleSQLException(SQLException exception, HttpServletRequest request) {
        String msg;
        if(exception.getCause().toString().contains("")){
            msg = "데이터베이스 연결에 실패했습니다.";
        } else {
            msg = "";
        }
        return showErrorPage(exception, request, msg);
    }
}
