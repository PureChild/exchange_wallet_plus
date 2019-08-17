package controller.advice;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

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

    @ExceptionHandler({SQLException.class, SQLIntegrityConstraintViolationException.class})
    public ModelAndView handleSQLException(Exception exception, HttpServletRequest request) {
        String msg;
        if(exception.getCause().toString().contains("link failure")){
            msg = "데이터베이스 연결에 실패했습니다.";
        } else if(exception.getCause().toString().contains("Duplicate")){
            msg = "이미 존재하는 정보입니다.";
        } else {
            msg = "";
        }
        return showErrorPage(exception, request, msg);
    }
}
