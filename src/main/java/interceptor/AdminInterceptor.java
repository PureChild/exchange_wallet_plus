package interceptor;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author 이승수
 * 관리자 페이지 접근 인터셉터
 */
public class AdminInterceptor extends HandlerInterceptorAdapter{
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HttpSession httpSession = request.getSession();
        Object loginUser = httpSession.getAttribute("admin");

        if(loginUser != "admin"){
            response.sendRedirect("/admin/login");
        }
    }
}
