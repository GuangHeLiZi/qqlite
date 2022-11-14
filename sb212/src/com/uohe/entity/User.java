package com.uohe.entity;

import com.uohe.tool.Pack;
import com.uohe.tool.Protobuf;
import com.uohe.utils.*;

import java.io.Serializable;


/*
 * 用户全局配置
 * 2021-10-15
 * 用户初始化参数,使用时注意调用类型。*严格
 *
 * by GuangHeLiZi
 * 更多请鉴
 * */
public class User implements Serializable {

    AllToByte allToByte=new AllToByte();
    ByteToAll byteToAll=new ByteToAll();
    ByteUtils byteUtils=new ByteUtils();
    MD5Helper md5Helper=new MD5Helper();
    HexUtils hexUtils=new HexUtils();
    RandomUtils randomUtils=new RandomUtils();


    private String user="10000";
    private String pass;

    private Integer seq;//标识标准
    //private Integer random4;//全局随机4
    //private Integer random16;//全局随机16
    //private String time;

    private String auth;//验证码方式 82是滑块 01是四字
    private short login;//00 01 普通登录 00 02 假锁登录


    private byte[] ksId;//rand16 tlv0108



    //消息相关
    private byte[] SyncCookies;//实时数据更新时间戳上线后// qq.SyncCoookies ＝ ProtobufOfInt (“08”, TimeStamp ()) ＋ ProtobufOfInt (“10”, TimeStamp ()) ＋ ProtobufOfInt (“18”, 2698482287) ＋ ProtobufOfInt (“20”, 2661279345) ＋ ProtobufOfInt (“28”, 3976759562) ＋ ProtobufOfInt (“48”, 813968566) ＋ ProtobufOfInt (“58”, 1553502109) ＋ ProtobufOfInt (“60”, 56) ＋ ProtobufOfInt (“68”, TimeStamp ()) ＋ ProtobufOfInt (“70”, 0)






    public String getUser() {
        return user;
    }
    public String toUserHex() {
        Long l=Long.valueOf(user);
        return hexUtils.addZeroForNum(NumericConvertUtil.toOtherBaseString(l, 16),8);
    }
    public String toUserText() {
        return byteToAll.byteToHxe(allToByte.textToByte(user));
    }



    public void setUser(String user) {
        this.user = user;
    }



    public String toPassMd5() {
        return md5Helper.getMD5String(pass).toUpperCase();
    }


    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }


    public Integer getSeq() {
        return seq;
    }
    public String toSeqHex() {
        return NumericConvertUtil.toOtherBaseString(seq,16);
    }
    public synchronized void addSeq() {
        seq+=1;
        if(seq>99999){
            seq=62000;
        }else if(seq<10000){
            seq=11000;
        }
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }


    public byte[] getRandom4() {
        return randomUtils.randomToByte(4);
    }


    public byte[] getRandom16() {
        return randomUtils.randomToByte(16);
    }


    public String getTime() {
        return hexUtils.timeToHex(byteUtils.timeToText());
    }

    public byte[] toTimeByte() {
        return allToByte.hexToByte(hexUtils.timeToHex(byteUtils.timeToText()));
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public short getLogin() {
        return login;
    }

    public void setLogin(short login) {
        this.login = login;
    }

    public byte[] getKsId() {
        return ksId;
    }

    public void setKsId(byte[] ksId) {
        this.ksId = ksId;
    }



    //user+pass md5
    public byte[] getUP_Key() {
        return allToByte.hexToByte(md5Helper.getMD5String(byteUtils.byteMerger(byteUtils.byteMerger(allToByte.hexToByte(md5Helper.getMD5String(pass.getBytes())),
                 new byte[]{0, 0, 0, 0}), allToByte.hexToByte(toUserHex())
         )));
    }


    public byte[] getSyncCookies() {
        return SyncCookies;
    }

    public byte[] updateSyncCookies() {
        setSyncCookies();
        return getSyncCookies();
    }

    public void setSyncCookies() {
        Pack pack=new Pack();
        pack.empty();
        long time=byteToAll.byteToInt(allToByte.hexToByte(hexUtils.timeToHex(byteUtils.timeToText())));
        pack.setHex(Protobuf.int64(time, 1));
        pack.setHex(Protobuf.int64(time, 2));
        pack.setHex(Protobuf.int64(1938700000l+this.getSeq(), 3));
        pack.setHex(Protobuf.int64(569800000l+this.getSeq(), 4));
        pack.setHex(Protobuf.int64(4077800000l+this.getSeq(), 5));
        pack.setHex(Protobuf.int64(1929900000l+this.getSeq(), 11));
        pack.setHex(Protobuf.int64(13l, 12));
        pack.setHex(Protobuf.int64(time, 13));
        pack.setHex(Protobuf.int64(0l, 14));
        SyncCookies = pack.getAll();
    }



}
