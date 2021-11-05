package com.example.ordering.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author MLH
 * @version 拦截器
 */
@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {
    // 需要验证openid的请求
    String[] pathPatterns = {"/client/saveOrderMenu", "/client/queryUser"};
    // 不需要验证openid的请求
    String[] excludePathPatterns = {"/client/queryMenu", "/client/getOpenId"};

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 可添加多个，/**是对所有的请求都做拦截
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns(pathPatterns)
                .excludePathPatterns(excludePathPatterns);
    }

}
