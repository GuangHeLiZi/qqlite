package com.uohe.core.cmd.sb.tool;


import com.uohe.dao.TEADao;
import com.uohe.daoimpl.TEADaoDao;
import com.uohe.tool.Pack;
import com.uohe.tool.Protobuf;
import com.uohe.utils.*;

/**
 * 手表tlv
 * 2021-3-17
 * by GuangHeLiZi
 * 更多请鉴
 * */
public class Tlv {


    private Pack pack=new Pack();
    private TEADao teaDao=new TEADaoDao();
    private byte[] tlvPack(String t,byte[] bin){
        pack.empty();
        pack.setHex(t);
        pack.setShort((short)bin.length);
        pack.setBin(bin);
        return pack.getAll();
    }
    public byte[] tlv_t0018(byte[] hexUser){
        // 00 18
        // 00 16[22]
        // 00 01
        // 00 00 06 00
        // 00 00 00 10
        // 00 00 00 00
        // 41 31 36 1E
        // 00 00 00 00
        pack.empty();
        pack.setHex("00 01");//_ping_version=1
        pack.setHex("00 00 06 00");//_sso_version=1536
        pack.setHex("00 00 00 10");//_appid
        pack.setHex("00 00 00 00");//_app_client_version
        pack.setBin(hexUser);
        pack.setHex ("00 00 00 00");
        return tlvPack("00 18",pack.getAll());
    }

    public byte[] tlv_t0001(byte[] hexUser,byte[] hexTime){
        // 00 01
        // 00 14[20]
        // 00 01
        // 77 00 36 2F
        // 41 31 36 1E
        // 63 5D 23 5D
        // 00 00 00 00
        // 00 00
        pack.empty();
        pack.setHex("00 01");//ip_ver=1
        pack.setBin(randomUtils.randomToByte(4));//random32
        pack.setBin(hexUser);
        pack.setBin(hexTime);
        pack.setHex("00 00 00 00");//_ip_addr
        pack.setHex("00 00");
        return tlvPack("00 01",pack.getAll());
    }

    public byte[] tlv_t0106(byte[] hexUser,byte[] hexTime,byte[] hexMd5Pass,byte[] hexTgtKey,byte[] hexGuid,byte[] hexAppId,byte[] hexMd5UserPass){
        //01 06 00 78[120]
        // 3C EC FB DE 16 C6 8B 38 5D 71 64 CE 39 D3 D1 EC BD 63 17 93 41 D3 95 E8 BB B6 10 FE 7B E8 6C D5 11 3E F6 66 0A 76 0F 31 ED F7 1E 64 69 59 BB F6 9C 77 ED 21 C6 3A A6 EF CC 6A 4E 99 FF B4 4F 30 3A A4 9C DA 61 D7 B0 F9 D0 D9 B4 36 A4 53 4B C0 00 99 A7 69 B9 99 AB B0 7B EB 3A 91 CC 37 2C 96 EC E0 84 A0 40 BD FF 27 F7 74 1D E7 E0 BF BC A7 89 12 75 E0 23 CE 36 3A
        //BDAB2BD4A77E7FBD130518E170C0A6A4
        //00 03
        // 14 0F F6 B3
        // 00 00 00 05
        // 00 00 00 10
        // 00 00 00 01
        // 00 00 00 00
        // 41 31 36 1E
        // 63 5D 23 5C
        // 00 00 00 00 01
        // 32 30 1A 8A B0 F0 0F 77 80 81 59 0E 20 82 29 14
        // 65 71 33 54 57 70 52 54 6E 41 45 63 3E 39 5A 5F
        // 00 00 00 00 01
        // 5C DB 92 CF 6E F0 8B 24 90 E3 03 EC 65 CA 11 77
        // 20 02 FC 74
        // 00 00 00 05 00 00 08 DC C6 F4 9A 06
        pack.empty();
        pack.setHex("00 03");//TGTVer=4
        pack.setBin(randomUtils.randomToByte(4));//random32
        pack.setHex("00 00 00 05");//_SSoVer=18
        pack.setHex("00 00 00 10");//_appid
        pack.setHex("00 00 00 01");
        pack.setHex("00 00 00 00");
        pack.setBin(hexUser);
        pack.setBin(hexTime);
        pack.setHex ("00 00 00 00 01");
        pack.setBin(hexMd5Pass);
        pack.setBin(hexTgtKey);//8D B0 91 13 26 11 A2 C2 51 13 55 2D D9 A6 12 CF
        pack.setHex ("00 00 00 00 01");
        pack.setBin(hexGuid);//guidmd5需要和tlv106 tlv128 tlv145统一 这里固定了8D 39 38 60 36 BA 9E F5 8F 3B 85 85 28 99 DD 80
        pack.setBin(hexAppId);//appid1
        pack.setHex ("00 00 00 05 00 00 08 DC C6 F4 9A 06");
        return this.tlvPack("01 06",teaDao.encrypt(pack.getAll(),hexMd5UserPass));
    }
    public byte[] tlv_t0116(String mMiscBitmap,String mSubSigMap,String subAppIdList){
        //01 16
        // 00 0A[10]
        // 00 00
        // F7 FF 7C 00
        // 01 04 00 00
        pack.empty();
        pack.setHex(mMiscBitmap);//mMiscBitmap
        pack.setHex(mSubSigMap);//mSubSigMap
//        pack.setHex(subAppIdList);//_sub_appid_list
        return tlvPack("01 16",pack.getAll());
    }

    public byte[] tlv_t0100(byte[] hexAppId,int mainSigMap){
        // 01 00
        // 00 16[22]
        // 00 01
        // 00 00 00 05
        // 00 00 00 10
        // 20 02 FC 74
        // 00 00 00 00
        // 02 04 10 C0
        pack.empty();
        pack.setHex("00 01");//_db_buf_ver=1
        pack.setHex("00 00 00 05");//_sso_ver=18
        pack.setHex("00 00 00 10");//_appid
        pack.setBin(hexAppId);//appid1
        pack.setHex("00 00 00 00");//_app_client_version
        pack.setInt(mainSigMap);//_main_sigmap
        return tlvPack("01 00",pack.getAll());
    }

    public byte[] tlv_t0107(){
        //01 07
        //00 06[6]
        //00 00
        //00
        //00 00
        //01
        pack.empty();
        pack.setHex("00 00");//_pic_type
        pack.setHex("00");
        pack.setHex("00 00");
        pack.setHex("01");
        return tlvPack("01 07",pack.getAll());
    }


    public byte[] tlv_t0142(byte[] hexAppName){
        // 01 42
        // 00 16[22]
        // 00 00
        // 00 12
        // 63 6F 6D 2E 74 65 6E 63 65 6E 74 2E 71 71 6C 69 74 65
        pack.empty();
        pack.setHex("00 00");
        pack.setShort((short)hexAppName.length);
        pack.setBin(hexAppName);////request_global._apk_id=com.tencent.mobileqq
        return tlvPack("01 42",pack.getAll());
    }


    public byte[] tlv_t0109(byte[] hexMd5AndroidId){
        pack.empty();
        pack.setBin(hexMd5AndroidId);//安卓设备idmd5 0178c7b34876f7d1
        return tlvPack("01 09",pack.getAll());
    }

    public byte[] tlv_t0124(byte[] hexTextMobile,byte[] hexTextVer,byte[] hexTextISP,byte[] hexTextISPType){
        // 01 24
        // 00 2C[44]
        // 00 07
        // 61 6E 64 72 6F 69 64
        // 00 05
        // 35 2E 31 2E 31
        // 00 02
        // 00 10
        // 43 68 69 6E 61 20 4D 6F 62 69 6C 65 20 47 53 4D
        // 00 00
        // 00 04
        // 77 69 66 69
        pack.empty();
        pack.setShort((short)hexTextMobile.length);//os_type
        pack.setBin(hexTextMobile);//系统  android
        pack.setShort((short)hexTextVer.length);//os_version
        pack.setBin(hexTextVer);//系统版本 5.1.1
        pack.setHex("00 02");//_network_type
        pack.setShort((short)hexTextISP.length);//_sim_operator_name  联网服务提供商
        pack.setBin(hexTextISP);//China Mobile GSM
        pack.setHex("00 00");
        pack.setShort((short)hexTextISPType.length);//_apn 上网方式
        pack.setBin(hexTextISPType);//wifi
        return tlvPack("01 24",pack.getAll());
    }

    public byte[] tlv_t0128(byte[] hexPhoneModel,byte[] hexGuid,byte[] hexPhoneBrand){
        // 01 28
        // 00 2F[47]
        // 00 00
        // 01
        // 01
        // 00
        // 11 00 00 00
        // 00 06
        // 53 4B 57 2D 41 30
        // 00 10
        // 5C DB 92 CF 6E F0 8B 24 90 E3 03 EC 65 CA 11 77
        // 00 0A
        // 62 6C 61 63 6B 73 68 61 72 6B
        pack.empty();
        pack.setHex("00 00");//0
        pack.setHex("01");//request_global._new_install
        pack.setHex("01");//request_global._read_guid
        pack.setHex("00");//request_global._guid_chg
        pack.setHex("11 00 00 00");//request_global._dev_report
        pack.setShort((short)hexPhoneModel.length);//request_global._device 手机型号
        pack.setBin(hexPhoneModel);//LIO-AN00
        pack.setShort((short)hexGuid.length);
        pack.setBin(hexGuid);//guidmd5需要和tlv106 tlv128 tlv145统一 这里固定了8D 39 38 60 36 BA 9E F5 8F 3B 85 85 28 99 DD 80
        pack.setShort((short)hexPhoneBrand.length);//手机品牌
        pack.setBin(hexPhoneBrand);//HUAWEI
        return tlvPack("01 28",pack.getAll());
    }

    public byte[] tlv_t016E(byte[] hexPhoneModel){
        pack.empty();
        //pack.setShort((short)hexPhoneModel.length);//request_global._device 手机型号
        pack.setBin(hexPhoneModel);//LIO-AN00
        return tlvPack("01 6E",pack.getAll());
    }

    public byte[] tlv_t0144(byte[] tlv_t0109,byte[] tlv_t0124,byte[] tlv_t0128,byte[] tlv_t016E,byte[] hexTgtKey){
        //01 44
        //01 D0[464]
        //37 83 F0 F7 8A 4A 50 72 DC FB F8 BF E9 F0 90 3D 39 1A C1 8C AF D5 25 43 8F 4D 83 87 45 C2 92 36 6C 84 A8 68 BB C6 34 3C F6 CF 78 F3 5D 22 5F B5 14 64 7D 20 18 42 8A 6A 34 83 63 A8 61 FF B3 FF 31 33 3E A1 A0 92 DF E7 67 F7 43 D7 6B 5E 6B B2 6F AE D5 D1 22 AF C6 AD 97 1C 8A E1 D7 5E 32 CA A1 07 7B 3D 2F 06 D2 2D 40 65 AF A6 51 40 B0 5B 7A 88 65 78 16 AB DE AB 85 C1 29 7D 61 F4 5A D0 9C 4B 68 F2 A4 7D 60 7F 92 88 08 41 EB BC 80 40 D4 55 72 D2 E1 5D AC EB A8 E7 00 59 84 51 AA 10 30 65 72 08 7E E0 6E 1D 1C 87 21 8C 20 58 8F 04 A8 E2 DE C1 C1 C6 24 EA 60 59 11 13 9A 75 08 23 44 B5 B0 63 F5 16 14 B3 FB 63 55 31 33 F2 B2 29 C3 F0 C4 43 4C 76 1F 5F 1B 18 52 6F 4C 53 39 9E 22 2C 03 6D E4 0D 1F AE 1B 59 F0 9A EA C8 68 7F 9C 75 B7 9B 7E 96 60 BE F4 EB 29 8B 6C E1 46 70 C3 F7 4D 43 BB 9A 37 2C 4E 1F B2 74 C6 9E A5 F2 1B 87 25 AA 17 89 81 CA 0D D5 4F 8D D0 83 63 FA AF BD 05 7D 14 6D 41 F8 8F 19 54 42 67 F5 F0 EF 9D 72 D0 A0 85 2C 84 E0 D1 60 CA C3 4C 9B 4C 74 13 6B 82 E9 7E F8 0B 9E DF CD 27 AA A4 67 A0 33 C6 CA A0 20 81 A2 25 EA 30 99 E3 E9 D0 56 EB 2A 85 A8 4E 5D 48 71 82 DC 1F 67 C2 24 66 FD 7D 46 1F B2 E5 65 C8 4B 6D CA B5 71 7E 96 51 A1 00 18 C1 9C EC 9E BA A9 DF 1B BB 3A 56 98 2F BF 43 13 05 79 0B 7E 6E 4E 88 90 67 A2 EC 6A 0C 27 A8 2E FC 27 C2 E6 15 15 7C ED 42 79 A5 D8 E6 68 22 38 80 D3 42 25 79 93 62 73 8A E1 43 50 40 CE 9A A8 18 7E 4F 15 A2 BC BA 54 95 55 3A F4 C6 BC D4 61
        //
        //tgtkey 8D B0 91 13 26 11 A2 C2 51 13 55 2D D9 A6 12 CF
        pack.empty();
        pack.setShort((short) 4);
        pack.setBin(tlv_t0109);
        pack.setBin(tlv_t0124);
        pack.setBin(tlv_t0128);
        pack.setBin(tlv_t016E);
        return tlvPack("01 44",teaDao.encrypt(pack.getAll(),hexTgtKey));
    }


    public byte[] tlv_t0145(byte[] hexGuid){//globle.guid ＝ 到大写 (hash.MD5 (到字节集 (globle.imei ＋ globle.Mac)))
        // 01 45
        //00 10[16]
        //8D 39 38 60 36 BA 9E F5 8F 3B 85 85 28 99 DD 80//guid
        pack.empty();
        pack.setBin(hexGuid);//guidmd5需要和tlv106 tlv128 tlv145统一 这里固定了8D 39 38 60 36 BA 9E F5 8F 3B 85 85 28 99 DD 80
        return tlvPack("01 45",pack.getAll());
    }

    public byte[] tlv_t0147(byte[] apkVer,byte[] apkSig){
        // 01 47
        // 00 1D[29]
        // 00 00 00 10
        // 00 05
        // 32 2E 31 2E 32
        // 00 10
        // A6 B7 45 BF 24 A2 C2 77 52 77 16 F6 F3 6E B6 8D
        pack.empty();
        pack.setHex("00 00 00 10");//_appid
        pack.setShort((short)apkVer.length);//request_global._apk_v  apk版本
        pack.setBin(apkVer);//38 2E 38 2E 33 38
        pack.setShort((short)apkSig.length);//request_global._apk_sig  apk序列版本
        pack.setBin(apkSig);//A6 B7 45 BF 24 A2 C2 77 52 77 16 F6 F3 6E B6 8D
        return tlvPack("01 47",pack.getAll());
    }

    public byte[] tlv_t016A(byte[] tlv0019Token){//扫码确认返回令牌
        // 01 6A
        // 00 40[64]
        // 73 FB 95 E3 5E 11 D5 9D 76 91 6A F2 87 17 C0 6B AC 16 DA 0A 54 04 72 82 64 56 E4 84 D9 70 0B 70 3F 3B 7B 48 16 9B 6E 5D 45 0B A0 22 34 E5 AC 3C 1F 13 0D 70 F5 D3 4D 00 5E 25 C0 36 FE B2 95 19
        pack.empty();
        pack.setBin(tlv0019Token);
        return tlvPack("01 6A",pack.getAll());
    }

    public byte[] tlv_t0154(int mRequestID){//和seq统一自增
        //01 54
        //00 04[4]
        //00 01 06 83 [67203]
        pack.empty();
        pack.setInt(mRequestID);//mRequestID 包序号  00 01 06 83 [67203]//this._g._sso_seq
        return tlvPack("01 54",pack.getAll());
    }

    public byte[] tlv_t0141(byte[] hexTextISP,byte[] hexTextISPType){
        // 01 41
        // 00 1C[28]
        // 00 01
        // 00 10
        // 43 68 69 6E 61 20 4D 6F 62 69 6C 65 20 47 53 4D
        // 00 02
        // 00 04
        // 77 69 66 69
        pack.empty();
        pack.setShort((short) 1);//00 01
        pack.setShort((short)hexTextISP.length);//_sim_operator_name  联网服务提供商
        pack.setBin(hexTextISP);//China Mobile GSM
        pack.setShort((short) 2);//00 02
        pack.setShort((short)hexTextISPType.length);//_apn 上网方式
        pack.setBin(hexTextISPType);//wifi
        return tlvPack("01 41",pack.getAll());
    }

    public byte[] tlv_t0008(){
        //00 08
        //00 08[8]
        //00 00
        //00 00 08 04
        //00 00
        pack.empty();
        pack.setHex("00 00");
        pack.setHex("00 00 08 04");//request_global._local_id
        pack.setHex("00 00");
        return tlvPack("00 08",pack.getAll());
    }



    public byte[] tlv_t0187(byte[] hexMd5TextMac){
        // 01 87
        // 00 10[16]
        // 8D 36 90 BA 02 F3 C0 49 35 14 0D F6 72 21 3B 40
        pack.empty();
        pack.setBin(hexMd5TextMac);//mactextmd5 00:81:10:9a:e3:97   8D 36 90 BA 02 F3 C0 49 35 14 0D F6 72 21 3B 40
        return tlvPack("01 87",pack.getAll());
    }

    public byte[] tlv_t0188(byte[] hexMd5TextAndroidId){
        // 01 88
        // 00 10[16]
        // 6F 44 D0 5C 2E 72 81 14 2B AD B0 34 82 B2 B9 DE
        pack.empty();
        pack.setBin(hexMd5TextAndroidId);//安卓设备idtextMD5 0178c7b34876f7d1  6F 44 D0 5C 2E 72 81 14 2B AD B0 34 82 B2 B9 DE
        return tlvPack("01 88",pack.getAll());
    }

    public byte[] tlv_t0194(byte[] hexMd5TextImsI){
        //01 94
        // 00 10[16]
        // 81 3C 82 E1 87 FB CD 3E 6E 67 FE 4E B4 31 B3 B0
        pack.empty();
        pack.setBin(hexMd5TextImsI);//imeitextmd5 460000613232591   81 3C 82 E1 87 FB CD 3E 6E 67 FE 4E B4 31 B3 B0
        return tlvPack("01 94",pack.getAll());
    }

    public byte[] tlv_t0191(String hexAuthType){
        //01 91
        // 00 01[1]
        // 00
        pack.empty();
        pack.setHex(hexAuthType);////验证码方式 82是滑块 01是四字 00是扫码 用byte十进制表示130  1
        return tlvPack("01 91",pack.getAll());
    }

    public byte[] tlv_t0202(byte[] hexMd5TextBssId,byte[] hexTextISPName){
        // 02 02
        // 00 1A[26]
        // 00 10
        // B0 24 3E 1E 2A 04 A5 06 69 BE FD 69 2D 3B 12 1C
        // 00 06
        // 22 79 75 79 75 22
        pack.empty();
        pack.setShort((short)hexMd5TextBssId.length);
        pack.setBin(hexMd5TextBssId);//bssidtextmd5  00:81:97:9a:db:e3  B0 24 3E 1E 2A 04 A5 06 69 BE FD 69 2D 3B 12 1C
        pack.setShort((short)hexTextISPName.length);
        pack.setBin(hexTextISPName);//wifi上网名称 22 79 75 79 75 22
        return tlvPack("02 02",pack.getAll());
    }

    public byte[] tlv_t0177(int time,byte[] hexSdkVer){
        // 01 77
        // 00 11[17]
        // 01
        // 5C F5 11 BB
        // 00 0A
        // 36 2E 30 2E 30 2E 32 33 36 35
        pack.empty();
        pack.setHex("01");
        pack.setInt(time);//BUILD_TIME 发布时间 61 69 9B 1C   1634310940
        pack.setShort((short)hexSdkVer.length);
        pack.setBin(hexSdkVer);//SDK_VERSION 发布版本 6.0.0.2487  36 2E 30 2E 30 2E 32 34 38 37
        return tlvPack("01 77",pack.getAll());
    }

    public byte[] tlv_t0516(){
        //05 16
        //00 04[4]
        //00 00 00 00
        pack.empty();
        pack.setHex("00 00 00 00");
        return tlvPack("05 16",pack.getAll());
    }

    public byte[] tlv_t0521(){
        // 05 21
        // 00 06[6]
        // 00 00 00 75 00 00
        pack.empty();
        pack.setHex("00 00 00 75 00 00");
        return tlvPack("05 21",pack.getAll());
    }


    public byte[] tlv_t0318(byte[] tlv0318Token){//扫码确认返回令牌
        //03 18
        // 00 60[96]
        // 45 10 42 82 48 E4 D6 85 6C BC 4C F0 2A 2A 93 2B 7C DD B7 F5 5A 27 96 1A BB 4F D4 C1 E1 E1 7D FA CE 54 2D C6 EF 84 83 F4 CF 06 B4 D5 50 16 46 14 83 3E 31 E3 04 AB B4 91 D6 88 BB 4C 77 1C CA DF 8B 6C 3B 72 4C 72 90 CC 50 FB D2 F9 55 31 CD F2 04 24 14 49 0B 0A B9 91 98 F0 19 3B D2 A3 45 AD
        pack.empty();
        pack.setBin(tlv0318Token);
        return tlvPack("03 18",pack.getAll());
    }


}
