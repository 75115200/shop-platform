package com.shop.shopping.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.base.user.entity.BaseUser;
import com.shop.common.base.BaseResult;
import com.shop.common.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.shop.common.constant.Constant.USER_SESSION;

@Component
public class UserAuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        Pattern pattern = Pattern.compile("/user/.*");
        Matcher matcher = pattern.matcher(requestURI);
        if (matcher.find()) {
            BaseUser user = (BaseUser) request.getSession().getAttribute(USER_SESSION);
            if (user != null) {
                return true;
            }

            String header = request.getHeader(HttpHeaders.ACCEPT);
            if (header.contains("application/json")) {
                String json = JsonUtil.objToJson(BaseResult.fail("请先登录"));
                response.setHeader("Content-Type", "application/json;charset=UTF8");
                response.getWriter().write(json);
            } else {
                response.sendRedirect("/public/home.html");
            }

            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
