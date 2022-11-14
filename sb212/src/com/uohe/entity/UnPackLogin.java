package com.uohe.entity;

import java.io.Serializable;
import java.util.Map;

/*
 * 处理登录返回的token并保存
 * 2021-10-15
 *方便后续参数的维护
 * by GuangHeLiZi
 * 更多请鉴
 * */
public class UnPackLogin implements Serializable{

    private byte[] data;
    private byte[] share_key;
    private byte[] tgt_key;
    private byte[] part1Token;
    private byte[] loginState;
    private Map token;

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public byte[] getShare_key() {
        return share_key;
    }

    public void setShare_key(byte[] share_key) {
        this.share_key = share_key;
    }

    public byte[] getTgt_key() {
        return tgt_key;
    }

    public void setTgt_key(byte[] tgt_key) {
        this.tgt_key = tgt_key;
    }

    public byte[] getPart1Token() {
        return part1Token;
    }

    public void setPart1Token(byte[] part1Token) {
        this.part1Token = part1Token;
    }

    public byte[] getLoginState() {
        return loginState;
    }

    public void setLoginState(byte[] loginState) {
        this.loginState = loginState;
    }

    public Map getToken() {
        return token;
    }

    public void setToken(Map token) {
        this.token = token;
    }
}
