package controller.advice;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 예외 처리 클래스
 * @author 이승수
 */
@ControllerAdvice(annotations = Controller.class)
public class CommonExceptionAdvice {
    /**
     * @param msg 에러메세지
     * @return 에러페이지
     */
    private ModelAndView showErrorPage(String msg) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/errorPage");
        mav.addObject("errorMsg", msg);
        return mav;
    }

    /**
     * BindException 핸들링
     * @return 에러페이지
     */
    @ExceptionHandler(BindException.class)
    public ModelAndView handleBindException() {
        return showErrorPage("잘못된 요청입니다.");
    }

    /**
     * NumberFormatException 핸들링
     * @return 에러페이지
     */
    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView handleNumberFormatException() {
        return showErrorPage("숫자 형식이 이상합니다.");
    }

    /**
     * DB관련 Exception 핸들링
     * @return 에러페이지
     */
    @ExceptionHandler({SQLException.class, SQLIntegrityConstraintViolationException.class})
    public ModelAndView handleSQLException(Exception exception) {
        String msg;
        if(exception.getCause().toString().contains("link failure")){
            msg = "데이터베이스 연결에 실패했습니다.";
        } else if(exception.getCause().toString().contains("Duplicate")){
            msg = "이미 존재하는 정보입니다.";
        } else {
            msg = "";
        }
        return showErrorPage(msg);
    }
}
