package cn.seu.weme.common.filter;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LCN on 2016-12-17.
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o) throws Exception {

        //校验用户身份是否合法
        HttpSession session = request.getSession();

        //用户访问的url
        String url = request.getRequestURI();

        if (true)
            return true;

        //校验用户访问是否是公开资源 地址
        List<String> open_urls = new ArrayList<String>();
        open_urls.add("login");
        open_urls.add("register");
        open_urls.add("logout");
        open_urls.add("submitEmailInfo");
        open_urls.add("checkVerificationCode");


        for (String open_url : open_urls) {
            if (url.indexOf(open_url) >= 0) {
                //如果访问的是公开 地址则放行
                return true;
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
