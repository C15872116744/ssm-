package cmj.interceptor;

import cmj.domain.SysUser;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author cmj
 * @create 2020-04-26 15:34
 */
public class MyHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        System.out.println("我先执行");
        String requestURI = httpServletRequest.getRequestURI();
        System.out.println(requestURI);
        System.out.println(httpServletRequest.getMethod());
        if(!requestURI.contains("login") ){
            SysUser user_session = (SysUser)httpServletRequest.getSession().getAttribute("USER_SESSION");
            System.out.println(user_session);
            if(null==user_session){
                httpServletRequest.setAttribute("msg","请先登录");
//                httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/index.jsp");
                httpServletRequest.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(httpServletRequest,httpServletResponse);
                return false;
            }
        }

        return true;

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {


    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

}
