package com.uohe.core.cmd.sb.init;


import com.uohe.core.entity.GetTcpNetty;
import com.uohe.entity.*;
import com.uohe.utils.*;

import java.util.*;

/**
 * 手表
 * 全局数据都放在这里，开闭原则
 */
public class UserConf0 {


    public Map UserConf0(String user,String password,byte[] tlv0019Token,byte[] tlv0318Token) {

        String validIpRegex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";




        /**
         * 登录服务器
         */
        MySocket mySocket1=new MySocket();
        GetTcpNetty getTcpNetty1=new GetTcpNetty();
//        MySocket mySocket2=new MySocket();
//        MySocket mySocket3=new MySocket();
        mySocket1.link("msfwifi.3g.qq.com",8080);



//        mySocket2.link("msfxg.3g.qq.com",8080);
//        mySocket3.link("msfxgv6.3g.qq.com",8080);




        /**
         * 控制登录方式
         */
        User u=new User();
        u.setUser(user);
        u.setPass(password);
        u.setSeq(67205);//包自增
        u.setAuth("00");//验证码方式 82是滑块 01是四字 00是扫码
        u.setLogin((short) 1);////00 01 普通登录 00 02 假锁登录
        u.setKsId(RandomUtils.randomToByte(16));



        /**
         * 控制应用的信息
         */
        App app=new App();
        app.setAppid1(537066612);
        app.setAppid2(537066612);
        app.setmMiscBitmap("F7 FF 7C 00");
        app.setmSubSigMap("01 04 00 00");
//        app.setSubAppIdList("5F 5E 10 E2");
//        app.setMainSigMap(34869472);

        app.setApkVer("32 2E 31 2E 32");//apk版本[8.8.38]147
        app.setApkSig("A6 B7 45 BF 24 A2 C2 77 52 77 16 F6 F3 6E B6 8D");//apk序列版本版本签名147
        app.setTime(1559564731);//177 版本发布时间61 69 9B 1C   1634310940
        app.setHexSdkVer("36 2E 30 2E 30 2E 32 33 36 35");//177[6.0.0.2487] 发布版本
        app.setAppVersion("Atestrevision");//





        //设备认证测试
        //1同时修改手机品牌手机型号和imei为常用设备配置结果失败>一般是突然性更换了系统的一些参数导致的
        //2
        //控制登录到设备信息
        Android android=new Android();
        android.setImei("865166023432688");//865166023432688 //865166023140588  //g 865190785652486
        android.setMac("00:81:10:9a:e3:97");//00:81:10:9a:e3:97 //00:81:10:9a:e3:97 //g 58:2A:BF:5C:3A:38
        android.setImsi("460000613232591");//460000613232591  //460047467515977
        android.setBssid("00:81:97:9a:db:e3");//00:81:97:9a:db:e3 //g 00:81:97:9a:db:e3
        android.setAndroidId("0178c7b34876f7d1");//安卓设备id 0178c7b34876f7d1  2135f6c4a6e550a
        //                android.setGuid(md5Helper.getMD5String(android.getImei() + android.getMac()));//也可以固定 //安卓id 字节 + mac字节 的MD5被我证明guid是这个公式
        android.setGuid(MD5Helper.getMD5String(android.getAndroidId() + android.getMac()));//也可以固定 //安卓id 字节 + mac字节 的MD5被我证明guid是这个公式
        android.setSystem("android");//手机系统
        android.setSystemVer("5.1.1");//手机系统版本5.1.1
        android.setIsp("China Mobile GSM");//网络服务提供者
        android.setIspLink("wifi");//网络服务提供者类型
        android.setPhoneModel("LIO-AN00");//手机型号//LIO-AN00 //g MX4
        android.setPhoneBrand("HUAWEI");//手机品牌//HUAWEI
        android.setCompile("N9500ZHS2BRC2");//编译序列 N9500ZHS2BRC2
        android.setUnknown1("unknown");//
        android.setCore("Linux version 4.0.9 (builder@ubuntu) (gcc version 4.8.5 (Ubuntu 4.8.5-4ubuntu8~14.04.2) ) #1 SMP PREEMPT Tue Dec 1 10:23:47 CST 2020");//
        android.setRel("REL");//
        android.setSoFingerprinting("asus/android_x86/x86:5.1.1/LMY48Z/N9500ZHS2BRC2:user/release-keys");//
        android.setAndroidGuid("f21daf2f-c606-4f8a-88b8-cb6305a89c05");//
        android.setUnknown2("no message");//
        android.setIspName("\"yuyu\"");



        //密钥磋商
        Ecdh ecdh=new Ecdh();
        ecdh.setShare_key("30 01 AB F0 56 C9 E2 20 3D ED 55 15 A6 8E C2 69");
        ecdh.setTgt_key("8D B0 91 13 26 11 A2 C2 51 13 55 2D D9 A6 12 CF");
        ecdh.setPublic_key("03 2E 21 8E 75 F4 49 B0 A4 38 4D C3 38 2A DE 29 FA 1C 99 7B F6 11 AF 2E 46");

        //token认证状态,key
        UnPackLogin unPackLogin=new UnPackLogin();



        List fileServer= Collections.synchronizedList(new ArrayList());//图片服务器



        com.uohe.core.cmd.sb.entity.Tlv t=new com.uohe.core.cmd.sb.entity.Tlv();
        Map<String,Object> tlv_t0018=new HashMap<>();
        Map<String,Object> tlv_t0001=new HashMap<>();
        Map<String,Object> tlv_t0106=new HashMap<>();
        Map<String,Object> tlv_t0116=new HashMap<>();
        Map<String,Object> tlv_t0100=new HashMap<>();
        Map<String,Object> tlv_t0107=new HashMap<>();
        Map<String,Object> tlv_t0108=new HashMap<>();
        Map<String,Object> tlv_t0142=new HashMap<>();
        Map<String,Object> tlv_t0109=new HashMap<>();
        Map<String,Object> tlv_t052D=new HashMap<>();
        Map<String,Object> tlv_t0124=new HashMap<>();
        Map<String,Object> tlv_t0128=new HashMap<>();
        Map<String,Object> tlv_t016E=new HashMap<>();
        Map<String,Object> tlv_t0144=new HashMap<>();
        Map<String,Object> tlv_t0145=new HashMap<>();
        Map<String,Object> tlv_t0147=new HashMap<>();
        Map<String,Object> tlv_t0154=new HashMap<>();
        Map<String,Object> tlv_t0141=new HashMap<>();
        Map<String,Object> tlv_t0008=new HashMap<>();
        Map<String,Object> tlv_t0511=new HashMap<>();
        Map<String,Object> tlv_t0187=new HashMap<>();
        Map<String,Object> tlv_t0188=new HashMap<>();
        Map<String,Object> tlv_t0194=new HashMap<>();
        Map<String,Object> tlv_t0191=new HashMap<>();
        Map<String,Object> tlv_t0202=new HashMap<>();
        Map<String,Object> tlv_t0177=new HashMap<>();
        Map<String,Object> tlv_t0516=new HashMap<>();
        Map<String,Object> tlv_t0521=new HashMap<>();
        Map<String,Object> tlv_t0525=new HashMap<>();
        Map<String,Object> tlv_t0544=new HashMap<>();
        Map<String,Object> tlv_t0545=new HashMap<>();
        Map<String,Object> tlv_t0548=new HashMap<>();
        Map<String,Object> tlv_t0192=new HashMap<>();
        Map<String,Object> tlv_t0193=new HashMap<>();
        Map<String,Object> tlv_t0104=new HashMap<>();
        byte[] hexUser= AllToByte.hexToByte(u.toUserHex());//"98 EB 9F 77"
        byte[] hexTime=AllToByte.hexToByte(ByteToAll.byteToHxe(u.toTimeByte()));//"61 88 E1 A5"
        byte[] hexMd5Pass=AllToByte.hexToByte(u.toPassMd5());//"32 30 1A 8A B0 F0 0F 77 80 81 59 0E 20 82 29 14"
        byte[] hexTgtKey=AllToByte.hexToByte(ecdh.getTgt_key());//"8D B0 91 13 26 11 A2 C2 51 13 55 2D D9 A6 12 CF"
        byte[] hexGuid=AllToByte.hexToByte(android.getGuid());//"8D 39 38 60 36 BA 9E F5 8F 3B 85 85 28 99 DD 80"
        byte[] hexAppId=AllToByte.intToByte(app.getAppid1());//allToByte.hexToByte("20 03 80 E0");
        byte[] hexTextUser=AllToByte.hexToByte(u.toUserText());//"32 35 36 35 35 37 38 36 31 35"
        byte[] hexMd5UserPass=AllToByte.hexToByte(ByteToAll.byteToHxe(u.getUP_Key()));//"A2 FB 3B 67 67 37 57 B6 3A F2 73 39 CF 8E 48 0F"
        String mMiscBitmap=app.getmMiscBitmap();//"F7 FF 7C 00";
        String mSubSigMap=app.getmSubSigMap();//"01 04 00 01";
        String subAppIdList=app.getSubAppIdList();//"5F 5E 10 E2";
//        int mainSigMap=app.getMainSigMap();//34869472;//02 14 10 E0 //_main_sigmap 34869472
        byte[] ksId=u.getKsId();//randomUtils.randomToByte(16);
//        byte[] hexAppName=AllToByte.hexToByte(app.getPackageFullName());//com.tencent.mobileqq 63 6F 6D 2E 74 65 6E 63 65 6E 74 2E 6D 6F 62 69 6C 65 71 71
        byte[] hexMd5TextAndroidId = AllToByte.hexToByte(MD5Helper.getMD5String(AllToByte.textToByte(android.getAndroidId())));//安卓设备idmd5 0178c7b34876f7d1

        String androidId=android.getAndroidId();//"0178c7b34876f7d1";
        String unknown1=android.getUnknown1();//
        String core=android.getCore();//
        String rel=android.getRel();//
        String soFingerprinting=android.getSoFingerprinting();//
        String androidGuid=android.getAndroidGuid();//
        String unknown2=android.getUnknown2();//
        String compile=android.getCompile();//"N9500ZHS2BRC2";//编译序列


        byte[] hexTextMobile=AllToByte.textToByte(android.getSystem());//"android"
        byte[] hexTextVer=AllToByte.textToByte(android.getSystemVer());//"5.1.1"
        byte[] hexTextISP=AllToByte.textToByte(android.getIsp());//"China Mobile GSM"
        byte[] hexTextISPType=AllToByte.textToByte(android.getIspLink());//"wifi"
        byte[] hexPhoneModel=AllToByte.textToByte(android.getPhoneModel());//"LIO-AN00"
        byte[] hexPhoneBrand=AllToByte.textToByte(android.getPhoneBrand());//"HUAWEI"
        byte[] apkVer=AllToByte.hexToByte(app.getApkVer());//apk版本//"38 2E 38 2E 33 38"
        byte[] apkSig=AllToByte.hexToByte(app.getApkSig());//apk序列版本//"A6 B7 45 BF 24 A2 C2 77 52 77 16 F6 F3 6E B6 8D"
        int mRequestID=u.getSeq();//67203;//包序号
        byte[] hexMd5TextMac = AllToByte.hexToByte(MD5Helper.getMD5String(AllToByte.textToByte(android.getMac())));//"00:81:10:9a:e3:97"
        byte[] hexMd5TextImsI = AllToByte.hexToByte(MD5Helper.getMD5String(AllToByte.textToByte(android.getImsi())));//"460000613232591"
        String hexAuthType=u.getAuth();//"82";
        byte[] hexMd5TextBssId = AllToByte.hexToByte(MD5Helper.getMD5String(AllToByte.textToByte(android.getBssid())));//"00:81:97:9a:db:e3"
        byte[] hexTextISPName=AllToByte.textToByte(android.getIspName());//"\"yuyu\""
        int time=app.getTime();//1634310940
        byte[] hexSdkVer=AllToByte.textToByte(app.getHexSdkVer());//6.0.0.2487
        short hexLoginType=u.getLogin();//1
        byte[] hexTextImeI=AllToByte.textToByte(android.getImei());//"865166023432688"
        byte[] hexTextMac=AllToByte.textToByte(android.getMac());//"00:81:3b:04:e9:58"





        tlv_t0018.put("hexUser",hexUser);
        tlv_t0001.put("hexUser",hexUser);
        tlv_t0001.put("hexTime",hexTime);
        tlv_t0106.put("hexUser",hexUser);
        tlv_t0106.put("hexTime",hexTime);
        tlv_t0106.put("hexMd5Pass",hexMd5Pass);
        tlv_t0106.put("hexTgtKey",hexTgtKey);
        tlv_t0106.put("hexGuid",hexGuid);
        tlv_t0106.put("hexAppId",hexAppId);
        tlv_t0106.put("hexTextUser",hexTextUser);
        tlv_t0106.put("hexMd5UserPass",hexMd5UserPass);

        tlv_t0116.put("mMiscBitmap",mMiscBitmap);
        tlv_t0116.put("mSubSigMap",mSubSigMap);
        tlv_t0116.put("subAppIdList",subAppIdList);

        tlv_t0100.put("hexAppId",hexAppId);
//        tlv_t0100.put("mainSigMap",mainSigMap);

        tlv_t0108.put("ksId",ksId);
//        tlv_t0142.put("hexAppName",hexAppName);
        tlv_t0109.put("hexMd5TextAndroidId",hexMd5TextAndroidId);
        tlv_t052D.put("androidId",androidId);
        tlv_t052D.put("unknown1",unknown1);
        tlv_t052D.put("core",core);
        tlv_t052D.put("rel",rel);
        tlv_t052D.put("soFingerprinting",soFingerprinting);
        tlv_t052D.put("androidGuid",androidGuid);
        tlv_t052D.put("unknown2",unknown2);
        tlv_t052D.put("compile",compile);


        tlv_t0124.put("hexTextMobile",hexTextMobile);
        tlv_t0124.put("hexTextVer",hexTextVer);
        tlv_t0124.put("hexTextISP",hexTextISP);
        tlv_t0124.put("hexTextISPType",hexTextISPType);

        tlv_t0128.put("hexPhoneModel",hexPhoneModel);
        tlv_t0128.put("hexGuid",hexGuid);
        tlv_t0128.put("hexPhoneBrand",hexPhoneBrand);

        tlv_t016E.put("hexPhoneModel",hexPhoneModel);

        tlv_t0144.put("hexTgtKey",hexTgtKey);
        tlv_t0145.put("hexGuid",hexGuid);
        tlv_t0147.put("apkVer",apkVer);
        tlv_t0147.put("apkSig",apkSig);

        tlv_t0154.put("mRequestID",mRequestID);
        tlv_t0141.put("hexTextISP",hexTextISP);
        tlv_t0141.put("hexTextISPType",hexTextISPType);
        tlv_t0187.put("hexMd5TextMac",hexMd5TextMac);
        tlv_t0188.put("hexMd5TextAndroidId",hexMd5TextAndroidId);
        tlv_t0194.put("hexMd5TextImsI",hexMd5TextImsI);
        tlv_t0191.put("hexAuthType",hexAuthType);
        tlv_t0202.put("hexMd5TextBssId",hexMd5TextBssId);
        tlv_t0202.put("hexTextISPName",hexTextISPName);
        tlv_t0177.put("time",time);
        tlv_t0177.put("hexSdkVer",hexSdkVer);

        tlv_t0525.put("hexLoginType",hexLoginType);

        tlv_t0545.put("hexTextImeI",hexTextImeI);
        tlv_t0545.put("hexTextMac",hexTextMac);


        t.setTlv_t0018(tlv_t0018);
        t.setTlv_t0001(tlv_t0001);
        t.setTlv_t0106(tlv_t0106);
        t.setTlv_t0116(tlv_t0116);
        t.setTlv_t0100(tlv_t0100);
        t.setTlv_t0107(tlv_t0107);
        t.setTlv_t0108(tlv_t0108);
        t.setTlv_t0142(tlv_t0142);
        t.setTlv_t0109(tlv_t0109);
        t.setTlv_t052D(tlv_t052D);
        t.setTlv_t0124(tlv_t0124);
        t.setTlv_t0128(tlv_t0128);
        t.setTlv_t016E(tlv_t016E);
        t.setTlv_t0144(tlv_t0144);
        t.setTlv_t0145(tlv_t0145);
        t.setTlv_t0147(tlv_t0147);
        t.setTlv_t0154(tlv_t0154);
        t.setTlv_t0141(tlv_t0141);
        t.setTlv_t0008(tlv_t0008);
        t.setTlv_t0511(tlv_t0511);
        t.setTlv_t0187(tlv_t0187);
        t.setTlv_t0188(tlv_t0188);
        t.setTlv_t0194(tlv_t0194);
        t.setTlv_t0191(tlv_t0191);
        t.setTlv_t0202(tlv_t0202);
        t.setTlv_t0177(tlv_t0177);
        t.setTlv_t0516(tlv_t0516);
        t.setTlv_t0521(tlv_t0521);
        t.setTlv_t0525(tlv_t0525);
        t.setTlv_t0544(tlv_t0544);
        t.setTlv_t0545(tlv_t0545);
        t.setTlv_t0548(tlv_t0548);
        t.setTlv_t0192(tlv_t0192);
        t.setTlv_t0193(tlv_t0193);
        t.setTlv_t0104(tlv_t0104);






        Map m=new HashMap();

        m.put("user",u);
        m.put("mySocket1",mySocket1);//tcp协议
        m.put("getTcpNetty1",getTcpNetty1);
//        m.put("mySocket2",mySocket2);
//        m.put("mySocket3",mySocket3);
        m.put("app",app);
        m.put("android",android);
        m.put("ecdh",ecdh);
        m.put("unPackLogin",unPackLogin);
        m.put("fileServer",fileServer);

        return m;


    }

    private String random(String user,int number,String s){
        int not=number-user.length();
        String data="";
        for (int i = 0; i < not; i++) {
            data+=s;
        }
        data+=user;
        return data;
    }




}
