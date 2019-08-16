package controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

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
    public ModelAndView handleBindException(Exception exception, HttpServletRequest request) {
        return showErrorPage(exception, request, "잘못된 요청입니다.");
    }

    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView handleNumberFormatException(Exception exception, HttpServletRequest request) {
        return showErrorPage(exception, request, "숫자 형식이 이상합니다.");
    }
}
