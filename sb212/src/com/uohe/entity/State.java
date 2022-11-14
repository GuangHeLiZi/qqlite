package com.uohe.entity;

import java.io.Serializable;


/*
 * 登录状态
 * 2021-10-15
 * *严格
 *
 * by GuangHeLiZi
 * 更多请鉴
 * */
public class State implements Serializable {

    /**
     * hd
     * 登录hk状态
     *
     * 00 04 02 //有验证码 00 04 09 登录失败 00 04 00 00 00 00登录成功
     */
    public static final String LOGIN_STATE_PASS ="000000";//通行
    public static final String LOGIN_STATE_WRONG ="000001";//账号密码错误
    public static final String LOGIN_STATE_HK ="000002";//hk
    public static final String LOGIN_STATE_06 ="000006";//登录失败
    public static final String LOGIN_STATE_08 ="000008";//登录失败
    public static final String LOGIN_STATE_DJ ="000028";//冻结
    public static final String LOGIN_STATE_B4 ="0000B4";//包错误
    public static final String LOGIN_STATE_CC ="0000CC";//环境变化 设备锁
    public static final String LOGIN_STATE_4Z ="0000EB";//4字 版本过低
    public static final String LOGIN_STATE_STOP ="0000ED";//禁止登录 安全风险
    public static final String LOGIN_STATE_VERIFY ="0000EF";//认证
    public static final String LOGIN_STATE_DB ="0000DB";//未注册q

}
