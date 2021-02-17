package cn.edu.zju.rushrushrush.roadmonitorbackend.interceptor;

import cn.edu.zju.rushrushrush.roadmonitorbackend.pojo.UserPojo;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String url = httpServletRequest.getRequestURI();
        if(url.contains("/session")) {
            return true;
        }

        HttpSession session = httpServletRequest.getSession();
        UserPojo user = (UserPojo) session.getAttribute("USER_SESSION");
        if(user != null) {
            return true;
        }

//        httpServletRequest.setAttribute("msg", "您还没登录，请先登录");
//        httpServletRequest.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(httpServletRequest, httpServletResponse);

        String XRequested =httpServletRequest.getHeader("X-Requested-With");

        if("XMLHttpRequest".equals(XRequested)){
            httpServletResponse.getWriter().write("login first");
        }else{
            httpServletResponse.sendRedirect("/session");
        }


        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}