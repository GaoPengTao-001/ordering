package com.example.ordering.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author MLH
 * @version session工具类
 */
public class SessionUtil {

    /**
     * 设置session
     *
     * @param key 存储键
     * @param obj 存储值
     */
    public static void saveObject(String key, Object obj) {
        HttpSession session = getSession();
        if (null != session) {
            session.setAttribute(key, obj);
        }
    }

    /**
     * 从session中取出对象
     *
     * @param key   存储键
     * @param clazz 结果类型
     * @return session 中的对象
     */
    public static <T> T getObject(String key, Class<T> clazz) {
        HttpSession session = getSession();
        if (null != session && null != clazz) {
            Object val = session.getAttribute(key);
            if (val != null && (clazz.isInstance(val) || clazz.isAssignableFrom(val.getClass()))) {
                return clazz.cast(val);
            }

        }
        return null;
    }

    /**
     * 获取session相应
     *
     * @return session对象
     */
    public static HttpSession getSession() {
        HttpServletRequest request = getRequest();
        if (null != request) {
            return request.getSession();
        }
        return null;
    }

    /**
     * 获取上下文请求
     *
     * @return 请求对象
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        if (null != servletRequestAttributes) {
            return servletRequestAttributes.getRequest();
        }
        return null;
    }
}
