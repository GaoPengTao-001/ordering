package com.example.ordering.config;

import com.example.ordering.util.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author MLH
 * @version 拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {
    private final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        logger.info("request请求地址path[{}] uri[{}]", request.getServletPath(), request.getRequestURI());
        String openId = SessionUtil.getObject("openId", String.class);
        if (openId != null) {
            return true;
        }
        response.sendError(403);
        return false;
    }

}
