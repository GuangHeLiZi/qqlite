package com.uohe.core.cmd.sb;


import com.uohe.core.cmd.sb.entity.Cmd;
import com.uohe.dao.TEADao;
import com.uohe.daoimpl.TEADaoDao;
import com.uohe.entity.*;
import com.uohe.tool.Pack;
import com.uohe.utils.AllToByte;
import com.uohe.utils.ByteToAll;
import com.uohe.utils.UnPack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;



/*
 * 手表登录
 * 2021-3-17
 * by GuangHeLiZi
 * 更多请鉴
 * */
public class PackLogin {

    private Pack pack=new Pack();
    private com.uohe.core.cmd.sb.tool.Tlv tlv=new com.uohe.core.cmd.sb.tool.Tlv();
    private TEADao teaDao=new TEADaoDao();

    public byte[] part2Outer(byte[] hexUser,byte[] random16,byte[] publicKey,byte[] part2Core){
        pack.empty();
        pack.setHex("1F 41");
        pack.setHex("08 10");
        pack.setHex("00 01");
        pack.setBin(hexUser);//98 EB 9F 77  [2565578615]
        pack.setHex("03 07 00 00 00 00 02 00 00 00 00 00 00 00 00");
        pack.setHex("01 01");
        pack.setBin(random16);//randKey16 randomUtils.randomToByte(16) 登录到上线统一一下
        pack.setHex("01 02");
        pack.setShort((short)publicKey.length);
        pack.setBin(publicKey);
        pack.setBin(part2Core);
        pack.setHex("03");
        byte[] bin=pack.getAll();
        pack.empty();
        pack.setHex("02");
        pack.setShort((short)(bin.length+3));
        pack.setBin(bin);
        bin=pack.getAll();
        pack.empty();
        pack.setInt(bin.length+4);
        pack.setBin(bin);
        return pack.getAll();
    }


    public byte[] part2Core(byte[] packPart2Core,byte[] share_key){//key  79 87 8B 45 4A B0 1B CF A1 E5 44 39 81 6F EE 2E
        pack.empty();
        pack.setHex("00 09");
        pack.setShort((short)23);//tlv总数 //00 1B
        pack.setBin(packPart2Core);
        return teaDao.encrypt(pack.getAll(), share_key);
    }

    public byte[] packPart2Core(com.uohe.core.cmd.sb.entity.Tlv t){
        pack.empty();
        pack.setBin(tlv.tlv_t0018((byte[]) t.getTlv_t0018().get("hexUser")));
        pack.setBin(tlv.tlv_t0001((byte[]) t.getTlv_t0001().get("hexUser"), (byte[]) t.getTlv_t0001().get("hexTime")));
        //byte[] hexUser,byte[] hexTime,byte[] hexMd5Pass,byte[] hexTgtKey,byte[] hexGuid,byte[] hexAppId,byte[] hexTextUser,byte[] hexMd5UserPass
        pack.setBin(tlv.tlv_t0106((byte[]) t.getTlv_t0106().get("hexUser"), (byte[]) t.getTlv_t0106().get("hexTime"), (byte[]) t.getTlv_t0106().get("hexMd5Pass"),
                (byte[]) t.getTlv_t0106().get("hexTgtKey"), (byte[]) t.getTlv_t0106().get("hexGuid"), (byte[]) t.getTlv_t0106().get("hexAppId"),
                 (byte[]) t.getTlv_t0106().get("hexMd5UserPass")));

        //int mMiscBitmap,int mSubSigMap,int sub_appid_list
        pack.setBin(tlv.tlv_t0116((String) t.getTlv_t0116().get("mMiscBitmap"), (String) t.getTlv_t0116().get("mSubSigMap"), (String) t.getTlv_t0116().get("subAppIdList")));
        pack.setBin(tlv.tlv_t0100((byte[]) t.getTlv_t0100().get("hexAppId"), (int) t.getTlv_t0100().get("mainSigMap")));
        pack.setBin(tlv.tlv_t0107());
        pack.setBin(tlv.tlv_t0142((byte[]) t.getTlv_t0142().get("hexAppName")));

        //tlv144
        byte[] tlv109=tlv.tlv_t0109((byte[]) t.getTlv_t0109().get("hexMd5TextAndroidId"));
        //byte[] hexTextMobile,byte[] hexTextVer,byte[] hexTextISP,byte[] hexTextISPType
        byte[] tlv124=tlv.tlv_t0124((byte[]) t.getTlv_t0124().get("hexTextMobile"),(byte[]) t.getTlv_t0124().get("hexTextVer"),(byte[]) t.getTlv_t0124().get("hexTextISP"),(byte[]) t.getTlv_t0124().get("hexTextISPType"));
        //byte[] hexPhoneModel,byte[] hexGuid,byte[] hexPhoneBrand
        byte[] tlv128=tlv.tlv_t0128((byte[]) t.getTlv_t0128().get("hexPhoneModel"),(byte[]) t.getTlv_t0128().get("hexGuid"),(byte[]) t.getTlv_t0128().get("hexPhoneBrand"));
        byte[] tlv16e=tlv.tlv_t016E((byte[]) t.getTlv_t016E().get("hexPhoneModel"));
        //tlv144

        //byte[] tlv_t0109,byte[] tlv_t052D,byte[] tlv_t0124,byte[] tlv_t0128,byte[] tlv_t016E,byte[] hexTgtKey
        pack.setBin(tlv.tlv_t0144(tlv109, tlv124, tlv128, tlv16e, (byte[]) t.getTlv_t0144().get("hexTgtKey")));
        pack.setBin(tlv.tlv_t0145((byte[]) t.getTlv_t0145().get("hexGuid")));
        //byte[] apkVer,byte[] apkSig
        pack.setBin(tlv.tlv_t0147((byte[]) t.getTlv_t0147().get("apkVer"), (byte[]) t.getTlv_t0147().get("apkSig")));

        pack.setBin(tlv.tlv_t016A((byte[]) t.getTlv_t016A().get("tlv0019Token")));
        pack.setBin(tlv.tlv_t0154((int) t.getTlv_t0154().get("mRequestID")));
        //byte[] hexTextISP,byte[] hexTextISPType
        pack.setBin(tlv.tlv_t0141((byte[]) t.getTlv_t0141().get("hexTextISP"), (byte[]) t.getTlv_t0141().get("hexTextISPType")));
        pack.setBin(tlv.tlv_t0008());
        pack.setBin(tlv.tlv_t0187((byte[]) t.getTlv_t0187().get("hexMd5TextMac")));
        pack.setBin(tlv.tlv_t0188((byte[]) t.getTlv_t0188().get("hexMd5TextAndroidId")));
        pack.setBin(tlv.tlv_t0194((byte[]) t.getTlv_t0194().get("hexMd5TextImsI")));
        pack.setBin(tlv.tlv_t0191((String) t.getTlv_t0191().get("hexAuthType")));
        //byte[] hexMd5TextBssId,byte[] hexTextISPName
        pack.setBin(tlv.tlv_t0202((byte[]) t.getTlv_t0202().get("hexMd5TextBssId"), (byte[]) t.getTlv_t0202().get("hexTextISPName")));
        //int time,byte[] hexSdkVer
        pack.setBin(tlv.tlv_t0177((int) t.getTlv_t0177().get("time"), (byte[]) t.getTlv_t0177().get("hexSdkVer")));
        pack.setBin(tlv.tlv_t0516());
        pack.setBin(tlv.tlv_t0521());
        pack.setBin(tlv.tlv_t0318((byte[]) t.getTlv_t0318().get("tlv0318Token")));
        //byte[] hexTextImeI,byte[] hexTextMac
        return pack.getAll();
    }



    public byte[] part1Outer(int seq,int appid1,int appid2,byte[] tlv010A,byte[] part1Token,byte[] cmd_0,byte[] imei,byte[] ksId,byte[] imsi,byte[] appVersion){
        pack.empty();
        pack.setInt(seq);
        pack.setInt(appid1);
        pack.setInt(appid2);
        pack.setHex("01 00 00 00");
        pack.setHex("00 00 00 00");
        pack.setHex("00 00 00 00");
        if(tlv010A==null){//登录token tlv010A
            pack.setHex("00 00 00 04");
        }else {
            pack.setInt(tlv010A.length+4);
            pack.setBin(tlv010A);
        }
        pack.setInt((cmd_0.length + 4));
        pack.setBin(cmd_0);
        if(part1Token==null){//记录返回的getPart1Token
            pack.setHex("00 00 00 04");
        }else {
            pack.setInt(part1Token.length+4);
            pack.setBin(part1Token);
        }
        pack.setInt((imei.length+4));
        pack.setBin(imei);
        pack.setInt((ksId.length+4));//tlv_t0108 ksId
        pack.setBin(ksId);
        pack.setShort((short)(imsi.length+appVersion.length+2));//中国移动用户标识码 +apk版本带A
        String su="7C"+ ByteToAll.byteToHxe(imsi)+"7C"+ ByteToAll.byteToHxe(appVersion)+"00 00";
        pack.setBin(AllToByte.hexToByte(su));
        byte[] bin=pack.getAll();
        pack.empty();
        pack.setInt(bin.length+4);
        pack.setBin(bin);
        return pack.getAll();
    }



    public byte[] outer(byte[] part1Outer,byte[] part2Outer,byte[] hexTextUser){
        pack.empty();
        pack.setBin(part1Outer);
        pack.setBin(part2Outer);
        byte[] bin=teaDao.encrypt(pack.getAll(), new byte[16]);
        pack.empty();
        pack.setHex("00 00 00 08");
        pack.setHex("02");
        pack.setHex("00 00 00 04");//token
        pack.setHex("00");
        pack.setInt( hexTextUser.length + 4);
        pack.setBin(hexTextUser);
        pack.setBin(bin);
        bin=pack.getAll();
        pack.empty();
        pack.setInt(bin.length+4);
        pack.setBin(bin);
        return pack.getAll();
    }




    public byte[] packLogin(Map initUser){
        User u= (User) initUser.get("user");
        App app= (App) initUser.get("app");
        Android android= (Android) initUser.get("android");
        Ecdh ecdh= (Ecdh) initUser.get("ecdh");
        UnPackLogin unPackLogin= (UnPackLogin) initUser.get("unPackLogin");
        com.uohe.core.cmd.sb.entity.Tlv t= (com.uohe.core.cmd.sb.entity.Tlv) initUser.get("tlv");
        u.addSeq();//自增
        PackLogin packLogin=new PackLogin();
        try {

            byte[] packPart2Core=packLogin.packPart2Core(t);
            byte[] part2Core=packLogin.part2Core(packPart2Core, AllToByte.hexToByte(ecdh.getShare_key()));
            byte[] random16=u.getKsId();
            byte[] puKey= AllToByte.hexToByte(ecdh.getPublic_key());
            byte[] hexUser= AllToByte.hexToByte(u.toUserHex());//"98 EB 9F 77"
            byte[] part2Outer=packLogin.part2Outer(hexUser,random16,puKey,part2Core);

            byte[] cmd_0= AllToByte.textToByte(Cmd.SB_1);
            byte[] imsi = AllToByte.textToByte(android.getImsi());
            byte[] appVersion = AllToByte.textToByte(app.getAppVersion());
            byte[] tlv_t010A;
            if(unPackLogin.getToken()==null){
                tlv_t010A=null;
            }else {
                tlv_t010A=(byte[])unPackLogin.getToken().get("tlv_t010A");
            }
            byte[] part1Outer = packLogin.part1Outer(u.getSeq(), app.getAppid1(), app.getAppid2(),tlv_t010A , unPackLogin.getPart1Token(), cmd_0, AllToByte.textToByte(android.getImei()), random16, imsi, appVersion);

            byte[] outer=packLogin.outer(part1Outer,part2Outer, AllToByte.textToByte(u.getUser()));

            return outer;

        }catch (Exception e){

            return null;
        }


    }



    public UnPackLogin unPackLogin(UnPackLogin unPackLogin){//理想化解析
        UnPack unPack=new UnPack();
        unPack.setData(unPackLogin.getData());
        unPack.getInt();//包长
        unPack.getInt();//包类型
        unPack.getByte();//包加密方式
        unPack.getByte();//0
        unPack.getBin(unPack.getInt()-4);//Len textUser

        unPack.setData(teaDao.decrypt(unPack.getAll(), new byte[16]));//outer

        byte[] part1Outer=unPack.getBin(unPack.getInt()-4);//Len part1Outer
        byte[] part2Outer=unPack.getBin(unPack.getInt()-4);//Len part2Outer

        //part1Outer
        unPack.setData(part1Outer);
        unPack.getInt();//用来配备发送的数据,seq相等就是此数据的返回包
        unPack.getInt();//0
        unPack.getInt();//04
        unPack.getBin(unPack.getInt()-4);//用来配备发送的数据,cmd相等就是此数据的返回包
        byte[] part1Token=unPack.getBin(unPack.getInt()-4);//token 记录一下，接下来的发送改成这里的
        unPack.getInt();

        //part2Outer
        unPack.setData(part2Outer);
        unPack.getByte();//2
        byte[] lenData=unPack.getBin(unPack.getShort()-4);//len 去掉了02 len 03

        unPack.setData(lenData);
        unPack.getShort();//指令
        unPack.getShort();
        unPack.getShort();
        unPack.getBin(4);//hexUser
        byte[] loginState=unPack.getBin(3);//认证方式 需要根据这里的指示去做登录验证
        //目前发现的 把这几种进行单独解析,如果都不是的情况下做出日志记录
        //00 00 00 正常登录 tlv 01 19 需要skey解析  tlv 01 61没用
        //00 00 01 账号密码错误 01 46 里面是错误提示 05 08没用
        //00 00 02 滑块认证 01 04 token的东西    01 92 滑块链接       05 46 不太重要
        //00 00 28 账号冻结
        //00 00 EB 四字 的登录后会提示版本过低，目前无解
        //00 00 ED 禁止登录暗杀名单
        //00 00 EF 设备认证关掉设备锁或2进行设备认证
        //00 00 CC 没有任何提示正常设备的应该对应一直加载登录确没有反应的情况,一般是突然性更换了系统的一些参数导致的

        //part2Core
        unPack.setData(teaDao.decrypt(unPack.getAll(), unPackLogin.getShare_key()));
        unPack.getShort();//9
        unPack.getByte();//loginState 的一部分

        if(Arrays.equals(loginState,new byte[3])){//正常登录
            unPack.getShort();//tlv总数
            unPack.getShort();//tlv_t0119
            byte[] tlv_v0119=unPack.getBin(unPack.getShort());
            unPack.setData(teaDao.decrypt(tlv_v0119, unPackLogin.getTgt_key()));
            short size=unPack.getShort();//解密后tlv总数 取出关键token
            unPackLogin.setPart1Token(part1Token);
            unPackLogin.setLoginState(loginState);
            unPackLogin.setToken(unTlv(size,unPack.getAll()));
        }else if(Arrays.equals(loginState,new byte[]{0,0,1})){//账号密码错误
            short size=unPack.getShort();//tlv总数
            unPackLogin.setPart1Token(part1Token);
            unPackLogin.setLoginState(loginState);
            unPackLogin.setToken(unTlv(size,unPack.getAll()));
        }else if(Arrays.equals(loginState,new byte[]{0,0,2})){//hk
            short size=unPack.getShort();//tlv总数
            unPackLogin.setPart1Token(part1Token);
            unPackLogin.setLoginState(loginState);
            unPackLogin.setToken(unTlv(size,unPack.getAll()));
        }else if(Arrays.equals(loginState,new byte[]{0,0,40})){//账号冻结
            short size=unPack.getShort();//tlv总数
            unPackLogin.setPart1Token(part1Token);
            unPackLogin.setLoginState(loginState);
            unPackLogin.setToken(unTlv(size,unPack.getAll()));
        }else if(Arrays.equals(loginState,new byte[]{0, 0, -52})){//环境变化
            short size=unPack.getShort();//tlv总数
            unPackLogin.setPart1Token(part1Token);
            unPackLogin.setLoginState(loginState);
            unPackLogin.setToken(unTlv(size,unPack.getAll()));
        }else if(Arrays.equals(loginState,new byte[]{0,0,-21})){//四字
            short size=unPack.getShort();//tlv总数
            unPackLogin.setPart1Token(part1Token);
            unPackLogin.setLoginState(loginState);
            unPackLogin.setToken(unTlv(size,unPack.getAll()));
        }else if(Arrays.equals(loginState,new byte[]{0,0,-19})){//禁止登录
            short size=unPack.getShort();//tlv总数
            unPackLogin.setPart1Token(part1Token);
            unPackLogin.setLoginState(loginState);
            unPackLogin.setToken(unTlv(size,unPack.getAll()));
        }else if(Arrays.equals(loginState,(new byte[]{0,0,-17}))){//设备锁
            short size=unPack.getShort();//tlv总数
            unPackLogin.setPart1Token(part1Token);
            unPackLogin.setLoginState(loginState);
            unPackLogin.setToken(unTlv(size,unPack.getAll()));
        }else {//记录其它状态
            short size=unPack.getShort();//tlv总数
            unPackLogin.setPart1Token(part1Token);
            unPackLogin.setLoginState(loginState);
            unPackLogin.setToken(unTlv(size,unPack.getAll()));
        }

        return unPackLogin;

    }


    public Map unTlv(short size,byte[] data){
        UnPack unPack=new UnPack();
        unPack.setData(data);
        Map map=new HashMap();
        for (int i = 0; i < size; i++) {
            byte[] tlvCmd = unPack.getBin(2);//头
            short len = unPack.getShort();//取长度
            map.put("tlv_t" + ByteToAll.byteToHxe(tlvCmd), unPack.getBin(len));
        }
        return map;
    }





}
