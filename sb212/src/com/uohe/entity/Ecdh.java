package com.uohe.entity;

import com.uohe.utils.*;

import java.io.Serializable;


/*
 * key 类
 * 2021-10-15
 * *严格
 *
 * by GuangHeLiZi
 * 更多请鉴
 * */
public class Ecdh implements Serializable {

    AllToByte allToByte=new AllToByte();
    ByteToAll byteToAll=new ByteToAll();
    ByteUtils byteUtils=new ByteUtils();
    MD5Helper md5Helper=new MD5Helper();
    HexUtils hexUtils=new HexUtils();
    RandomUtils randomUtils=new RandomUtils();


    //ECDH
    public static final String SHARE_KEY = "9C 7C 1E 9A 6E 27 FD 8A F0 CF 3F 9F B6 39 08 52";
    public static final String PUBLIC_KEY ="04 33 01 A2 E3 17 49 B2 0C CA 65 20 CD C4 CC A5 F4 C9 2A 7A 01 08 E6 22 B5 A1 CA 7E CF 25 F5 71 74 C2 A0 00 43 E9 2C C6 89 2C B6 C0 68 C4 DB 0A E4 ED 80 A7 2A 74 40 7B C5 A9 88 BB 9A 79 02 D8 59";
    public static final String TGT_KEY ="3C 96 74 D0 70 6D B3 35 4E E3 A3 79 C1 C8 8B 2A";


    private String share_key;
    private String public_key;
    private String tgt_key;
    private String session_key;//tlv03 05
    private String token_key;//tlv01 0E

    public String getShare_key() {
        return share_key;
    }

    public void setShare_key(String share_key) {
        this.share_key = share_key;
    }

    public String getPublic_key() {
        return public_key;
    }

    public void setPublic_key(String public_key) {
        this.public_key = public_key;
    }

    public String getTgt_key() {
        return tgt_key;
    }

    public void setTgt_key(String tgt_key) {
        this.tgt_key = tgt_key;
    }

    public String getSession_key() {
        return session_key;
    }

    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }

    public String getToken_key() {
        return token_key;
    }

    public void setToken_key(String token_key) {
        this.token_key = token_key;
    }


}
