package com.example.ordering.module;

import java.util.List;

/**
 * <p>
 * 支付渠道
 * </p>
 *
 * @param <T> 结果类型
 * @author gaopengtao
 * @since 2020-12-22
 */
public class WeiXinResult<T> {

    private String code;

    private String message;

    List<T> obj;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<T> getObj() {
        return obj;
    }

    public void setObj(List<T> obj) {
        this.obj = obj;
    }

    @Override
    public String toString() {
        return "PayCoreResult{"
                + "code='" + code + '\''
                + ", message='" + message + '\''
                + ", obj=" + obj
                + '}';
    }
}
