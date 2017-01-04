package cn.seu.weme.common.filter;

import com.google.common.base.Strings;
import net.sf.json.JSONObject;
import org.apache.commons.io.IOUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LCN on 2016-12-17.
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o) throws Exception {

        String token = "";


        if ("POST".equalsIgnoreCase(request.getMethod())) {
            try {
                InputStream is = null;
                String contentStr = "";
                is = request.getInputStream();
                contentStr = IOUtils.toString(is, "utf-8");
                JSONObject jsonObject = JSONObject.fromObject(contentStr);
                token = jsonObject.getString("token");
            } catch (Exception e) {
                return false;
            }

        }

        if (Strings.isNullOrEmpty(token)) {
            return true;
        }

        if (true)
            return true;

        String url = request.getRequestURL().toString();

        //校验用户访问是否是公开资源 地址
        List<String> open_urls = new ArrayList<String>();
        open_urls.add("loginV2");
        open_urls.add("registerV2");
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
