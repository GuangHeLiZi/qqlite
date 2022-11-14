package com.uohe.entity;

import com.uohe.utils.*;

import java.io.Serializable;

public class Token implements Serializable {



    AllToByte allToByte=new AllToByte();
    ByteToAll byteToAll=new ByteToAll();
    ByteUtils byteUtils=new ByteUtils();
    MD5Helper md5Helper=new MD5Helper();
    HexUtils hexUtils=new HexUtils();
    RandomUtils randomUtils=new RandomUtils();



    private String Token0143;//上线 最外层 TOKEN_002C
    private String Token010A;//上线 part1 TOKEN_004C
    private String sessionKey;//tlv0305 返回获取


    //目前只实现了常用登录，异地情况暂时搁置
    //private String skey;//tlv0102 qq.Cookies ＝ “uin=o” ＋ toUserText ＋ “; skey=” ＋ skey

    //private String clientKey;//tlv0103
    //private String tlv0105;//tlv0105 //验证码相关
    //private String Token0104;//tlv0104 //验证码相关
    //private String tlv011A;//tlv011A //验证码相关
    //private String superKey;//tlv016D
    //private String Token016A;//tlv016A
    //private String Token0133;//tlv0133
    //private String Token0134;//tlv0134
    //private String Token0106;//tlv0106
    //private String TGTKey;//tlv010C
    //private String Token010D;//tlv010D
    //private String Token010E;//tlv010E
    //private String Token0114;//tlv0114
    //private String vUrl;//tlv0192
    //private String tlv0512;//tlv0512 域名集
    //private String Token0163;//tlv0163
    //private String Token0136;//tlv0136


    //00 00 00这是常用登录到返回token
    //016A
    //0106
    //010C
    //010A
    //010D
    //0114
    //010E
    //0103
    //0133
    //0134
    //0528
    //0322
    //011D
    //011F
    //0138
    //011A
    //0522
    //0537
    //0550
    //0120
    //016D
    //0512
    //0305
    //0143
    //0118
    //0543
    //0163
    //0138
    //0130


    public String getToken0143() {
        return Token0143;
    }

    public byte[] toToken0143Byte() {
        return allToByte.hexToByte(Token0143);
    }

    public void setToken0143(String token0143) {
        Token0143 = token0143;
    }

    public String getToken010A() {
        return Token010A;
    }

    public byte[] toToken010AByte() {
        return allToByte.hexToByte(Token010A);
    }

    public void setToken010A(String token010A) {
        Token010A = token010A;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }




}
