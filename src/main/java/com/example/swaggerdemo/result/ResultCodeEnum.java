package com.example.swaggerdemo.result;

/***


 *
 *
 */
public enum ResultCodeEnum
{
    //=================系统==============
    UNKNOW_EXCEPTION(1000,"系统未知异常"),
    VAILD_EXCEPTION(1001,"参数格式校验失败"),
    TO_MANY_REQUEST(1002,"请求流量过大，请稍后再试"),
    READ_TIME_OUT_EXCEPTION(1004,"远程调用服务超时，请重新再试"),


    //=================身份认证=============
    USER_NOT_LOGIN(2001, "用户未登录"),
    USER_ACCOUNT_EXPIRED(2002, "账号已过期"),
    USER_CREDENTIALS_ERROR(2003, "用户或密码错误"),
    USER_CREDENTIALS_EXPIRED(2004, "密码过期"),
    USER_ACCOUNT_DISABLE(2005, "账号不可用"),
    USER_ACCOUNT_LOCKED(2006, "账号被锁定"),
    USER_ACCOUNT_NOT_EXIST(2007, "账号不存在"),
    USER_ACCOUNT_ALREADY_EXIST(2008, "账号已存在"),
    USER_ACCOUNT_USE_BY_OTHERS(2009, "账号下线"),
    CAPTCHA_VERIFY_EXCEPTION(2010,"图形验证错误"),
    SMS_CODE_EXCEPTION(2011,"短信验证码获取频率太高，请稍后再试"),
    USER_THIRD_PARTY_GRANT_FAILED(2012,"获得第三方授权失败，请重试"),


  //===================权限管理==================
    PERMISSION_DENY(3001,"权限不够，请联系管理员");

    private int code;
    private String msg;
    ResultCodeEnum(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
