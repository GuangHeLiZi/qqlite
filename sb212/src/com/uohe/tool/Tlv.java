package com.uohe.tool;


import com.uohe.dao.TEADao;
import com.uohe.daoimpl.TEADaoDao;
import com.uohe.utils.*;

/**
 * tlv
 * 2021-3-17
 * by GuangHeLiZi
 * 更多请鉴
 * */
public class Tlv {

    AllToByte allToByte=new AllToByte();
    ByteToAll byteToAll=new ByteToAll();
    ByteUtils byteUtils=new ByteUtils();
    MD5Helper md5Helper=new MD5Helper();
    HexUtils hexUtils=new HexUtils();
    private Pack pack=new Pack();
    private TEADao teaDao=new TEADaoDao();
    RandomUtils randomUtils=new RandomUtils();
    private byte[] tlvPack(String t,byte[] bin){
        pack.empty();
        pack.setHex(t);
        pack.setShort((short)bin.length);
        pack.setBin(bin);
        return pack.getAll();
    }
    public byte[] tlv_t0018(byte[] hexUser){
        //00 18
        //00 16[22]
        //00 01
        //00 00 06 00
        //00 00 00 10 //_appid
        //00 00 00 00
        //98 EB 9F 77  [2565578615]
        //00 00 00 00
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
        //00 01
        //00 14[20]
        //00 01
        //75 A5 B9 ED //随机32位
        //98 EB 9F 77  [2565578615]
        //61 88 E1 A5  [2021-11-08 16:36:53]
        //00 00 00 00
        //00 00
        pack.empty();
        pack.setHex("00 01");//ip_ver=1
        pack.setBin(randomUtils.randomToByte(4));//random32
        pack.setBin(hexUser);
        pack.setBin(hexTime);
        pack.setHex("00 00 00 00");//_ip_addr
        pack.setHex("00 00");
        return tlvPack("00 01",pack.getAll());
    }

    public byte[] tlv_t0106(byte[] hexUser,byte[] hexTime,byte[] hexMd5Pass,byte[] hexTgtKey,byte[] hexGuid,byte[] hexAppId,byte[] hexTextUser,byte[] hexMd5UserPass){
        //00 78[120]
        //33 E6 E4 33 69 AA 0D 8A F3 37 57 50 25 55 04 9D 86 59 17 C7 11 E0 9F B3 55 7E B3 D6 DA CF DC 71 5C C3 85 55 E3 16 3B F9 59 AA 6D 8D E2 C8 EF 58 D6 19 AF A3 77 11 82 E4 CC FF B3 3C 79 E6 A7 EF 82 E2 99 58 D3 4A 8E 30 21 40 78 3A 76 ED 80 19 24 FF 65 31 FC 84 95 21 4B 32 AA 6B 8C 6B F8 49 F9 ED 0F FC 7B AD BC 49 26 D0 F9 A3 7B 3B B1 FC 38 35 93 C3 D0 BB A7 CF
        //key A2FB3B67673757B63AF27339CF8E480F
        pack.empty();
        pack.setHex("00 04");//TGTVer=4
        pack.setBin(randomUtils.randomToByte(4));//random32
        pack.setHex("00 00 00 12");//_SSoVer=18
        pack.setHex("00 00 00 10");//_appid
        pack.setHex("00 00 00 00");
        pack.setHex("00 00 00 00");
        pack.setBin(hexUser);
        pack.setBin(hexTime);
        pack.setHex ("00 00 00 00 01");
        pack.setBin(hexMd5Pass);
        pack.setBin(hexTgtKey);//8D B0 91 13 26 11 A2 C2 51 13 55 2D D9 A6 12 CF
        pack.setHex ("00 00 00 00 01");
        pack.setBin(hexGuid);//guidmd5需要和tlv106 tlv128 tlv145统一 这里固定了8D 39 38 60 36 BA 9E F5 8F 3B 85 85 28 99 DD 80
        pack.setBin(hexAppId);//appid1
        pack.setHex ("00 00 00 01");
        pack.setShort((short) hexTextUser.length);
        pack.setBin(hexTextUser);
        pack.setHex ("00 00");
        return this.tlvPack("01 06",teaDao.encrypt(pack.getAll(),hexMd5UserPass));
    }
    public byte[] tlv_t0116(String mMiscBitmap,String mSubSigMap,String subAppIdList){
        //01 16
        //00 0E[14]
        //
        //00 0A[10]
        //F7 FF 7C 00  [4160715776]
        //01 04 00 01  [17039361]
        //
        //5F 5E 10 E2 [1600000226]
        pack.empty();
        pack.setHex("00 0A");//mMiscBitmap+mSubSigMap = length
        pack.setHex(mMiscBitmap);//mMiscBitmap
        pack.setHex(mSubSigMap);//mSubSigMap
        pack.setHex(subAppIdList);//_sub_appid_list
        return tlvPack("01 16",pack.getAll());
    }

    public byte[] tlv_t0100(byte[] hexAppId,int mainSigMap){
        //01 00
        //00 16[22]
        //
        //00 01 //_db_buf_ver=1
        //00 00 00 12 //_sso_ver=18
        //00 00 00 10 //_appid
        //20 03 80 E0 //appid1
        //00 00 00 00 //_app_client_version
        //02 14 10 E0 //_main_sigmap 34869472
        pack.empty();
        pack.setHex("00 01");//_db_buf_ver=1
        pack.setHex("00 00 00 12");//_sso_ver=18
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

    public byte[] tlv_t0108(byte[] ksId){
        //01 08
        //00 10[16]
        //8A 40 7D B3 20 35 BA EF A8 E5 BF F3 F3 5E C3 EA
        pack.empty();
        pack.setBin(ksId);//_ksid 随机16[8A 40 7D B3 20 35 BA EF A8 E5 BF F3 F3 5E C3 EA] randomUtils.randomToByte(16)
        return tlvPack("01 08",pack.getAll());
    }
    public byte[] tlv_t0142(byte[] hexAppName){
        //01 42
        //00 18[24]
        //00 00
        //00 14[20]
        //63 6F 6D 2E 74 65 6E 63 65 6E 74 2E 6D 6F 62 69 6C 65 71 71
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
        pack.empty();
        pack.setHex("00 00");//0
        pack.setHex("00");//request_global._new_install
        pack.setHex("01");//request_global._read_guid
        pack.setHex("00");//request_global._guid_chg
        pack.setHex("01 00 00 00");//request_global._dev_report
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

    public byte[] tlv_t0144(byte[] tlv_t0109,byte[] tlv_t052D,byte[] tlv_t0124,byte[] tlv_t0128,byte[] tlv_t016E,byte[] hexTgtKey){
        //01 44
        //01 D0[464]
        //37 83 F0 F7 8A 4A 50 72 DC FB F8 BF E9 F0 90 3D 39 1A C1 8C AF D5 25 43 8F 4D 83 87 45 C2 92 36 6C 84 A8 68 BB C6 34 3C F6 CF 78 F3 5D 22 5F B5 14 64 7D 20 18 42 8A 6A 34 83 63 A8 61 FF B3 FF 31 33 3E A1 A0 92 DF E7 67 F7 43 D7 6B 5E 6B B2 6F AE D5 D1 22 AF C6 AD 97 1C 8A E1 D7 5E 32 CA A1 07 7B 3D 2F 06 D2 2D 40 65 AF A6 51 40 B0 5B 7A 88 65 78 16 AB DE AB 85 C1 29 7D 61 F4 5A D0 9C 4B 68 F2 A4 7D 60 7F 92 88 08 41 EB BC 80 40 D4 55 72 D2 E1 5D AC EB A8 E7 00 59 84 51 AA 10 30 65 72 08 7E E0 6E 1D 1C 87 21 8C 20 58 8F 04 A8 E2 DE C1 C1 C6 24 EA 60 59 11 13 9A 75 08 23 44 B5 B0 63 F5 16 14 B3 FB 63 55 31 33 F2 B2 29 C3 F0 C4 43 4C 76 1F 5F 1B 18 52 6F 4C 53 39 9E 22 2C 03 6D E4 0D 1F AE 1B 59 F0 9A EA C8 68 7F 9C 75 B7 9B 7E 96 60 BE F4 EB 29 8B 6C E1 46 70 C3 F7 4D 43 BB 9A 37 2C 4E 1F B2 74 C6 9E A5 F2 1B 87 25 AA 17 89 81 CA 0D D5 4F 8D D0 83 63 FA AF BD 05 7D 14 6D 41 F8 8F 19 54 42 67 F5 F0 EF 9D 72 D0 A0 85 2C 84 E0 D1 60 CA C3 4C 9B 4C 74 13 6B 82 E9 7E F8 0B 9E DF CD 27 AA A4 67 A0 33 C6 CA A0 20 81 A2 25 EA 30 99 E3 E9 D0 56 EB 2A 85 A8 4E 5D 48 71 82 DC 1F 67 C2 24 66 FD 7D 46 1F B2 E5 65 C8 4B 6D CA B5 71 7E 96 51 A1 00 18 C1 9C EC 9E BA A9 DF 1B BB 3A 56 98 2F BF 43 13 05 79 0B 7E 6E 4E 88 90 67 A2 EC 6A 0C 27 A8 2E FC 27 C2 E6 15 15 7C ED 42 79 A5 D8 E6 68 22 38 80 D3 42 25 79 93 62 73 8A E1 43 50 40 CE 9A A8 18 7E 4F 15 A2 BC BA 54 95 55 3A F4 C6 BC D4 61
        //
        //tgtkey 8D B0 91 13 26 11 A2 C2 51 13 55 2D D9 A6 12 CF
        pack.empty();
        pack.setShort((short) 5);
        pack.setBin(tlv_t0109);
        pack.setBin(tlv_t052D);
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
        //01 47
        //00 1E[30]
        //
        //00 00 00 10 //_appid
        //
        //00 06[6]
        //38 2E 38 2E 33 38 [8.8.38]
        //
        //00 10[16]
        //A6 B7 45 BF 24 A2 C2 77 52 77 16 F6 F3 6E B6 8D
        pack.empty();
        pack.setHex("00 00 00 10");//_appid
        pack.setShort((short)apkVer.length);//request_global._apk_v  apk版本
        pack.setBin(apkVer);//38 2E 38 2E 33 38
        pack.setShort((short)apkSig.length);//request_global._apk_sig  apk序列版本
        pack.setBin(apkSig);//A6 B7 45 BF 24 A2 C2 77 52 77 16 F6 F3 6E B6 8D
        return tlvPack("01 47",pack.getAll());
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
        //01 41
        //00 1C[28]
        //00 01
        //00 10[16]
        //43 68 69 6E 61 20 4D 6F 62 69 6C 65 20 47 53 4D [China Mobile GSM]
        //00 02
        //00 04[4]
        //77 69 66 69 [wifi]
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

    public byte[] tlv_t0511(){
        //05 11
        //00 D3[211]
        //
        //00 0E  [14]
        //
        //01 00 0A 74 65 6E 70 61 79 2E 63 6F 6D 01 00 11 6F 70 65 6E 6D 6F 62 69 6C 65 2E 71 71 2E 63 6F 6D 01 00 0B 64 6F 63 73 2E 71 71 2E 63 6F 6D 01 00 0E 63 6F 6E 6E 65 63 74 2E 71 71 2E 63 6F 6D 01 00 0C 71 7A 6F 6E 65 2E 71 71 2E 63 6F 6D 01 00 0A 76 69 70 2E 71 71 2E 63 6F 6D 01 00 11 67 61 6D 65 63 65 6E 74 65 72 2E 71 71 2E 63 6F 6D 01 00 0A 71 75 6E 2E 71 71 2E 63 6F 6D 01 00 0B 67 61 6D 65 2E 71 71 2E 63 6F 6D 01 00 0C 71 71 77 65 62 2E 71 71 2E 63 6F 6D 01 00 09 74 69 2E 71 71 2E 63 6F 6D 01 00 0D 6F 66 66 69 63 65 2E 71 71 2E 63 6F 6D 01 00 0B 6D 61 69 6C 2E 71 71 2E 63 6F 6D 01 00 0A 6D 6D 61 2E 71 71 2E 63 6F 6D
        pack.empty();
        pack.setShort((short)14);//总数
        pack.setHex("01");//分割
        pack.setShort((short)allToByte.textToByte("tenpay.com").length);
        pack.setBin(allToByte.textToByte("tenpay.com"));
        pack.setHex("01");//分割
        pack.setShort((short)allToByte.textToByte("openmobile.qq.com").length);
        pack.setBin(allToByte.textToByte("openmobile.qq.com"));
        pack.setHex("01");//分割
        pack.setShort((short)allToByte.textToByte("docs.qq.com").length);
        pack.setBin(allToByte.textToByte("docs.qq.com"));
        pack.setHex("01");//分割
        pack.setShort((short)allToByte.textToByte("connect.qq.com").length);
        pack.setBin(allToByte.textToByte("connect.qq.com"));
        pack.setHex("01");//分割
        pack.setShort((short)allToByte.textToByte("qzone.qq.com").length);
        pack.setBin(allToByte.textToByte("qzone.qq.com"));
        pack.setHex("01");//分割
        pack.setShort((short)allToByte.textToByte("vip.qq.com").length);
        pack.setBin(allToByte.textToByte("vip.qq.com"));
        pack.setHex("01");//分割
        pack.setShort((short)allToByte.textToByte("gamecenter.qq.com").length);
        pack.setBin(allToByte.textToByte("gamecenter.qq.com"));
        pack.setHex("01");//分割
        pack.setShort((short)allToByte.textToByte("qun.qq.com").length);
        pack.setBin(allToByte.textToByte("qun.qq.com"));
        pack.setHex("01");//分割
        pack.setShort((short)allToByte.textToByte("game.qq.com").length);
        pack.setBin(allToByte.textToByte("game.qq.com"));
        pack.setHex("01");//分割
        pack.setShort((short)allToByte.textToByte("qqweb.qq.com").length);
        pack.setBin(allToByte.textToByte("qqweb.qq.com"));
        pack.setHex("01");//分割
        pack.setShort((short)allToByte.textToByte("ti.qq.com").length);
        pack.setBin(allToByte.textToByte("ti.qq.com"));
        pack.setHex("01");//分割
        pack.setShort((short)allToByte.textToByte("office.qq.com").length);
        pack.setBin(allToByte.textToByte("office.qq.com"));
        pack.setHex("01");//分割
        pack.setShort((short)allToByte.textToByte("mail.qq.com").length);
        pack.setBin(allToByte.textToByte("mail.qq.com"));
        pack.setHex("01");//分割
        pack.setShort((short)allToByte.textToByte("mma.qq.com").length);
        pack.setBin(allToByte.textToByte("mma.qq.com"));
        return tlvPack("05 11",pack.getAll());
    }

    public byte[] tlv_t0187(byte[] hexMd5TextMac){
        //01 87
        //00 10[16]
        //8D 36 90 BA 02 F3 C0 49 35 14 0D F6 72 21 3B 40 //00:81:10:9a:e3:97 mac textmd5
        pack.empty();
        pack.setBin(hexMd5TextMac);//mactextmd5 00:81:10:9a:e3:97   8D 36 90 BA 02 F3 C0 49 35 14 0D F6 72 21 3B 40
        return tlvPack("01 87",pack.getAll());
    }

    public byte[] tlv_t0188(byte[] hexMd5TextAndroidId){
        //01 88
        //00 10[16]
        //6F 44 D0 5C 2E 72 81 14 2B AD B0 34 82 B2 B9 DE //安卓设备idMD5文本 0178c7b34876f7d1
        pack.empty();
        pack.setBin(hexMd5TextAndroidId);//安卓设备idtextMD5 0178c7b34876f7d1  6F 44 D0 5C 2E 72 81 14 2B AD B0 34 82 B2 B9 DE
        return tlvPack("01 88",pack.getAll());
    }

    public byte[] tlv_t0194(byte[] hexMd5TextImsI){
        //01 94
        //00 10[16]
        //81 3C 82 E1 87 FB CD 3E 6E 67 FE 4E B4 31 B3 B0 //imsitextmd5 460000613232591
        pack.empty();
        pack.setBin(hexMd5TextImsI);//imeitextmd5 460000613232591   81 3C 82 E1 87 FB CD 3E 6E 67 FE 4E B4 31 B3 B0
        return tlvPack("01 94",pack.getAll());
    }

    public byte[] tlv_t0191(String hexAuthType){
        //01 91
        //00 01
        //82
        pack.empty();
        pack.setHex(hexAuthType);////验证码方式 82是滑块 01是四字 用byte十进制表示130  1
        return tlvPack("01 91",pack.getAll());
    }

    public byte[] tlv_t0202(byte[] hexMd5TextBssId,byte[] hexTextISPName){
        //02 02
        //00 1A[26]
        //
        //00 10[16]
        //B0 24 3E 1E 2A 04 A5 06 69 BE FD 69 2D 3B 12 1C //bssidtextmd5  00:81:97:9a:db:e3
        //
        //00 06[6]
        //22 79 75 79 75 22 ["yuyu"]
        pack.empty();
        pack.setShort((short)hexMd5TextBssId.length);
        pack.setBin(hexMd5TextBssId);//bssidtextmd5  00:81:97:9a:db:e3  B0 24 3E 1E 2A 04 A5 06 69 BE FD 69 2D 3B 12 1C
        pack.setShort((short)hexTextISPName.length);
        pack.setBin(hexTextISPName);//wifi上网名称 22 79 75 79 75 22
        return tlvPack("02 02",pack.getAll());
    }

    public byte[] tlv_t0177(int time,byte[] hexSdkVer){
        //01 77
        //00 11[17]
        //
        //01
        //61 69 9B 1C  [1634310940]
        //
        //00 0A[10]
        //36 2E 30 2E 30 2E 32 34 38 37 [6.0.0.2487]
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
        //05 21
        //00 06[6]
        //00 00 00 00 00 00
        pack.empty();
        pack.setHex("00 00 00 00 00 00");
        return tlvPack("05 21",pack.getAll());
    }

    public byte[] tlv_t0525(short hexLoginType){
        //05 25
        //00 47[71]
        //
        //
        //00 01
        //05 36
        //00 41[65]
        //
        //01
        //
        //03
        //00 00 00 00 41 31 36 1E 04 DA 13 DF 16 61 88 C2 CA 20 03 70 0C
        //00 00 00 00 41 31 36 1E 04 DA 13 DF 16 61 88 C3 E4 20 03 70 0C
        //00 00 00 00 41 31 36 1E 04 DA 13 DF 16 61 88 C2 43 20 03 70 0C
        pack.empty();
        pack.setShort((short)hexLoginType);// 1 普通登录 2 假锁登录
        pack.setHex("05 36");//tlv
        pack.setHex("00 41");//len
        pack.setHex("01");
        pack.setHex("03");//*总数
        pack.setHex("00 00 00 00 41 31 36 1E 04 DA 13 DF 16 61 88 C2 CA 20 03 70 0C");
        pack.setHex("00 00 00 00 41 31 36 1E 04 DA 13 DF 16 61 88 C2 CA 20 03 70 0C");
        pack.setHex("00 00 00 00 41 31 36 1E 04 DA 13 DF 16 61 88 C2 CA 20 03 70 0C");
        return tlvPack("05 25",pack.getAll());
    }


    public byte[] tlv_t0544(){//推测记录token类的
        //05 44
        //00 CB[203]
        //68 65 68 61  [heha]
        //
        //00 00 00 01 01
        //00 00 00 00
        //00 00 00 01 01
        //
        //00 04
        //
        //03
        //00 00 00 00
        //20 77 8F DF
        //00 00 00 02
        //00 00
        //
        //00 A6
        //00 01
        //00 08[8]
        //00 00 01 7C FE B1 6E CD
        //00 02
        //00 0A[10]
        //66 76 37 32 4E 64 77 33 65 32 [fv72Ndw3e2]
        //00 03
        //00 04[4]
        //01 00 00 01
        //
        //00 05
        //
        //00 04[4]
        //01 00 00 01
        //
        //00 04
        //00 04[4]
        //00 00 00 00
        //
        //
        //
        //
        //
        //00 06
        //00 04[4]
        //01 00 00 04
        //00 07
        //00 04[4]
        //01 00 00 05
        //00 08
        //00 04[4]
        //01 00 00 06
        //00 09
        //00 20[32]
        //AE 5A B2 C5 12 CF 27 02 BE D8 8A 19 0D 01 D5 E6 92 72 D3 39 AC 51 01 68 94 CA 57 AE 0E CE 6B 78
        //00 0A
        //00 10[16]
        //D6 FE BA 5B 95 8D F5 DB 25 E8 D3 6A F7 B5 CB 97
        //00 0B
        //00 10[16]
        //1A B8 AF 9A 16 27 82 8A CA FE BB D0 BD FF 95 57
        //00 0C
        //00 04[4]
        //01 00 00 01
        //00 0D
        //00 04[4]
        //00 00 00 01
        pack.empty();
        pack.setHex("68 65 68 61");//未知  heha
        pack.setHex("00 00 00 01 01");//未知
        pack.setHex("00 00 00 00");//未知
        pack.setHex("00 00 00 01 01");//未知
        pack.setHex("00 04");//未知
        pack.setHex("03");//未知
        pack.setHex("00 00 00 00");//未知
        pack.setHex("20 77 8F DF");//未知
        pack.setHex("00 00 00 02");//未知
        pack.setHex("00 00");//未知

        pack.setHex("00 A6");//总长
        pack.setHex("00 01");//1
        pack.setHex("00 08");//长
        pack.setHex("00 00 01 7C FE B1 6E CD");//内容
        pack.setHex("00 02");//2
        pack.setHex("00 0A");//长
        pack.setHex("66 76 37 32 4E 64 77 33 65 32");//内容
        pack.setHex("00 03");//3
        pack.setHex("00 04");//长
        pack.setHex("01 00 00 01");//内容

        pack.setHex("00 05");//5 ta就是5在前4在后，没有调整
        pack.setHex("00 04");//长
        pack.setHex("01 00 00 01");//内容

        pack.setHex("00 04");//4
        pack.setHex("00 04");//长
        pack.setHex("00 00 00 00");//内容

        pack.setHex("00 06");//6
        pack.setHex("00 04");//长
        pack.setHex("01 00 00 04");//内容

        pack.setHex("00 07");//7
        pack.setHex("00 04");//长
        pack.setHex("01 00 00 05");//内容

        pack.setHex("00 08");//8
        pack.setHex("00 04");//长
        pack.setHex("01 00 00 06");//内容

        pack.setHex("00 09");//8
        pack.setHex("00 20");//长
        pack.setHex("AE 5A B2 C5 12 CF 27 02 BE D8 8A 19 0D 01 D5 E6 92 72 D3 39 AC 51 01 68 94 CA 57 AE 0E CE 6B 78");//内容

        pack.setHex("00 0A");//10
        pack.setHex("00 10");//长
        pack.setHex("D6 FE BA 5B 95 8D F5 DB 25 E8 D3 6A F7 B5 CB 97");//内容

        pack.setHex("00 0B");//11
        pack.setHex("00 10");//长
        pack.setHex("1A B8 AF 9A 16 27 82 8A CA FE BB D0 BD FF 95 57");//内容

        pack.setHex("00 0C");//12
        pack.setHex("00 04");//长
        pack.setHex("01 00 00 01");//内容

        pack.setHex("00 0D");//13
        pack.setHex("00 04");//长
        pack.setHex("00 00 00 01");//内容

        return tlvPack("05 44",pack.getAll());
    }


    public byte[] tlv_t0545(byte[] hexTextImeI,byte[] hexTextMac){// imei和mac 貌似做了位移处理十进制公差292,100
        // 05 45
        //00 21[33]
        //
        //
        //38 36 35 31 36 36 30 32 33 34 33 32 36 38 38
        //5F
        //30 30 3A 38 31 3A 33 62 3A 30 34 3A 65 39 3A 35 38
        pack.empty();
        pack.setBin(hexTextImeI);// 38 36 35 31 36 36 30 32 33 34 33 32 36 38 38 y865166023140588 u865166023432688
        pack.setHex("5F");//分割
        pack.setBin(hexTextMac);//30 30 3A 38 31 3A 33 62 3A 30 34 3A 65 39 3A 35 38 y 00:81:10:9a:e3:97 u00:81:3b:04:e9:58
        return tlvPack("05 45",pack.getAll());
    }

    public byte[] tlv_t0548(){
        //05 48
        //01 E4[484]
        //
        //01 02 01 01
        //00 0A  [10]
        //00 00
        //00 80[128]
        //E3 47 E5 D5 5D C9 AF D7 F9 BA D0 74 C5 83 EA E6 B8 39 64 FA EE A2 FA F9 CB 22 3A D9 D9 00 62 51 1E 53 ED DA CD 08 1D C2 0B A3 18 6A C2 7C EF D7 AD A0 A8 40 5B 4C 60 AF 1C 60 94 C8 52 28 7D F4 91 49 2A 05 C5 59 9D A2 03 30 A9 5F E7 26 84 96 37 37 B8 02 2F BA 57 24 24 48 79 B5 D3 72 F9 10 A4 B2 BE D6 99 F0 64 AF 8F D9 92 F3 97 64 47 E1 64 DD 33 3B 7E 0C 0A 62 D9 9F CD 48 9F 2F 21 2A
        //00 20[32]
        //AD D2 38 F7 C8 E2 F4 30 7E 4B 1E BF C0 81 D1 45 F3 2C B7 EB C8 F5 FB 7F 35 D5 36 C1 A5 23 BD C8
        //
        //00 AC[172]
        //01 02 01 02
        //00 0A  [10]
        //00 00
        //00 80[128]
        // E3 47 E5 D5 5D C9 AF D7 F9 BA D0 74 C5 83 EA E6 B8 39 64 FA EE A2 FA F9 CB 22 3A D9 D9 00 62 51 1E 53 ED DA CD 08 1D C2 0B A3 18 6A C2 7C EF D7 AD A0 A8 40 5B 4C 60 AF 1C 60 94 C8 52 28 7D F4 91 49 2A 05 C5 59 9D A2 03 30 A9 5F E7 26 84 96 37 37 B8 02 2F BA 57 24 24 48 79 B5 D3 72 F9 10 A4 B2 BE D6 99 F0 64 AF 8F D9 92 F3 97 64 47 E1 64 DD 33 3B 7E 0C 0A 62 D9 9F CD 48 9F 2F 21 2A
        //00 20[32]
        //AD D2 38 F7 C8 E2 F4 30 7E 4B 1E BF C0 81 D1 45 F3 2C B7 EB C8 F5 FB 7F 35 D5 36 C1 A5 23 BD C8
        //
        //
        //
        //00 80[128]
        //E3 47 E5 D5 5D C9 AF D7 F9 BA D0 74 C5 83 EA E6 B8 39 64 FA EE A2 FA F9 CB 22 3A D9 D9 00 62 51 1E 53 ED DA CD 08 1D C2 0B A3 18 6A C2 7C EF D7 AD A0 A8 40 5B 4C 60 AF 1C 60 94 C8 52 28 7D F4 91 49 2A 05 C5 59 9D A2 03 30 A9 5F E7 26 84 96 37 37 B8 02 2F BA 57 24 24 48 79 B5 D3 72 F9 10 A4 B2 BE D6 99 F0 64 AF 8F D9 92 F3 97 64 47 E1 64 DD 33 3B 7E 0C 0A 62 D9 9F CD 48 9F 2F 48 3A
        //
        // 00 00 00 27 00 00 27 10
        pack.empty();
        pack.setHex("01 02 01 01");
        pack.setHex("00 0A");//10
        pack.setHex("00 00");
        pack.setHex("00 80");//len
        pack.setHex("E3 47 E5 D5 5D C9 AF D7 F9 BA D0 74 C5 83 EA E6 B8 39 64 FA EE A2 FA F9 CB 22 3A D9 D9 00 62 51 1E 53 ED DA CD 08 1D C2 0B A3 18 6A C2 7C EF D7 AD A0 A8 40 5B 4C 60 AF 1C 60 94 C8 52 28 7D F4 91 49 2A 05 C5 59 9D A2 03 30 A9 5F E7 26 84 96 37 37 B8 02 2F BA 57 24 24 48 79 B5 D3 72 F9 10 A4 B2 BE D6 99 F0 64 AF 8F D9 92 F3 97 64 47 E1 64 DD 33 3B 7E 0C 0A 62 D9 9F CD 48 9F 2F 21 2A");
        pack.setHex("00 20");//len
        pack.setHex("AD D2 38 F7 C8 E2 F4 30 7E 4B 1E BF C0 81 D1 45 F3 2C B7 EB C8 F5 FB 7F 35 D5 36 C1 A5 23 BD C8");

        pack.setHex("00 AC");//127
        pack.setHex("01 02 01 02");
        pack.setHex("00 0A");//10
        pack.setHex("00 00");
        pack.setHex("00 80");//len
        pack.setHex("E3 47 E5 D5 5D C9 AF D7 F9 BA D0 74 C5 83 EA E6 B8 39 64 FA EE A2 FA F9 CB 22 3A D9 D9 00 62 51 1E 53 ED DA CD 08 1D C2 0B A3 18 6A C2 7C EF D7 AD A0 A8 40 5B 4C 60 AF 1C 60 94 C8 52 28 7D F4 91 49 2A 05 C5 59 9D A2 03 30 A9 5F E7 26 84 96 37 37 B8 02 2F BA 57 24 24 48 79 B5 D3 72 F9 10 A4 B2 BE D6 99 F0 64 AF 8F D9 92 F3 97 64 47 E1 64 DD 33 3B 7E 0C 0A 62 D9 9F CD 48 9F 2F 21 2A");
        pack.setHex("00 20");//len
        pack.setHex("AD D2 38 F7 C8 E2 F4 30 7E 4B 1E BF C0 81 D1 45 F3 2C B7 EB C8 F5 FB 7F 35 D5 36 C1 A5 23 BD C8");

        pack.setHex("00 80");//len
        pack.setHex("E3 47 E5 D5 5D C9 AF D7 F9 BA D0 74 C5 83 EA E6 B8 39 64 FA EE A2 FA F9 CB 22 3A D9 D9 00 62 51 1E 53 ED DA CD 08 1D C2 0B A3 18 6A C2 7C EF D7 AD A0 A8 40 5B 4C 60 AF 1C 60 94 C8 52 28 7D F4 91 49 2A 05 C5 59 9D A2 03 30 A9 5F E7 26 84 96 37 37 B8 02 2F BA 57 24 24 48 79 B5 D3 72 F9 10 A4 B2 BE D6 99 F0 64 AF 8F D9 92 F3 97 64 47 E1 64 DD 33 3B 7E 0C 0A 62 D9 9F CD 48 9F 2F 48 3A");

        pack.setHex("00 00 00 27 00 00 27 10");


        return tlvPack("05 48",pack.getAll());
    }


    public byte[] tlv_t0193(String ticketCode){
        //01 93
        //00 83[131]
        // 74 30 33 4E 72 74 2D 78 58 5F 6A 47 35 37 77 35 67 5F 63 73 31 45 36 50 5F 2D 6B 57 70 36 41 38 64 77 6C 74 74 6B 6D 71 72 33 78 44 49 61 73 70 49 31 64 74 56 64 45 30 62 6C 67 69 64 67 36 64 6B 36 75 66 78 58 72 43 4B 6E 32 69 4B 46 36 49 42 4A 39 67 6C 52 75 65 30 55 78 54 6B 34 68 6F 68 71 74 59 42 79 69 59 55 31 56 6A 56 69 44 7A 79 69 64 75 75 63 32 35 4F 4B 4D 74 43 71 61 6E 68 51 65 [t03Nrt-xX_jG57w5g_cs1E6P_-kWp6A8dwlttkmqr3xDIaspI1dtVdE0blgidg6dk6ufxXrCKn2iKF6IBJ9glRue0UxTk4hohqtYByiYU1VjViDzyiduuc25OKMtCqanhQe]
        pack.empty();
        pack.setBin(allToByte.textToByte(ticketCode));
        return tlvPack("01 93",pack.getAll());
    }
    public byte[] tlv_t0104(byte[] code){
        //01 04
        //00 24[36]
        // 41 6D 44 37 4C 51 77 61 75 57 6D 70 53 55 2B 76 71 71 4B 6F 4C 6F 6B 6B 69 57 50 75 6E 4C 48 7A 41 67 3D 3D [AmD7LQwauWmpSU+vqqKoLokkiWPunLHzAg==]
        pack.empty();
        pack.setBin(code);
        return tlvPack("01 04",pack.getAll());
    }

    public byte[] tlv_t0547(){
        pack.empty();
        pack.setHex("01 02 01 01");
        pack.setHex("00 0A");//10
        pack.setHex("00 00");
        pack.setHex("00 80");//len
        pack.setHex("E3 47 E5 D5 5D C9 AF D7 F9 BA D0 74 C5 83 EA E6 B8 39 64 FA EE A2 FA F9 CB 22 3A D9 D9 00 62 51 1E 53 ED DA CD 08 1D C2 0B A3 18 6A C2 7C EF D7 AD A0 A8 40 5B 4C 60 AF 1C 60 94 C8 52 28 7D F4 91 49 2A 05 C5 59 9D A2 03 30 A9 5F E7 26 84 96 37 37 B8 02 2F BA 57 24 24 48 79 B5 D3 72 F9 10 A4 B2 BE D6 99 F0 64 AF 8F D9 92 F3 97 64 47 E1 64 DD 33 3B 7E 0C 0A 62 D9 9F CD 48 9F 2F 21 2A");
        pack.setHex("00 20");//len
        pack.setHex("AD D2 38 F7 C8 E2 F4 30 7E 4B 1E BF C0 81 D1 45 F3 2C B7 EB C8 F5 FB 7F 35 D5 36 C1 A5 23 BD C8");

        pack.setHex("00 AC");//127
        pack.setHex("01 02 01 02");
        pack.setHex("00 0A");//10
        pack.setHex("00 00");
        pack.setHex("00 80");//len
        pack.setHex("E3 47 E5 D5 5D C9 AF D7 F9 BA D0 74 C5 83 EA E6 B8 39 64 FA EE A2 FA F9 CB 22 3A D9 D9 00 62 51 1E 53 ED DA CD 08 1D C2 0B A3 18 6A C2 7C EF D7 AD A0 A8 40 5B 4C 60 AF 1C 60 94 C8 52 28 7D F4 91 49 2A 05 C5 59 9D A2 03 30 A9 5F E7 26 84 96 37 37 B8 02 2F BA 57 24 24 48 79 B5 D3 72 F9 10 A4 B2 BE D6 99 F0 64 AF 8F D9 92 F3 97 64 47 E1 64 DD 33 3B 7E 0C 0A 62 D9 9F CD 48 9F 2F 21 2A");
        pack.setHex("00 20");//len
        pack.setHex("AD D2 38 F7 C8 E2 F4 30 7E 4B 1E BF C0 81 D1 45 F3 2C B7 EB C8 F5 FB 7F 35 D5 36 C1 A5 23 BD C8");

        pack.setHex("00 80");//len
        pack.setHex("E3 47 E5 D5 5D C9 AF D7 F9 BA D0 74 C5 83 EA E6 B8 39 64 FA EE A2 FA F9 CB 22 3A D9 D9 00 62 51 1E 53 ED DA CD 08 1D C2 0B A3 18 6A C2 7C EF D7 AD A0 A8 40 5B 4C 60 AF 1C 60 94 C8 52 28 7D F4 91 49 2A 05 C5 59 9D A2 03 30 A9 5F E7 26 84 96 37 37 B8 02 2F BA 57 24 24 48 79 B5 D3 72 F9 10 A4 B2 BE D6 99 F0 64 AF 8F D9 92 F3 97 64 47 E1 64 DD 33 3B 7E 0C 0A 62 D9 9F CD 48 9F 2F 48 3A");

        pack.setHex("00 00 00 27 00 00 27 10");
        return tlvPack("05 47",pack.getAll());
    }


    public byte[] tlv_t0174(byte[] code){
        pack.empty();
        pack.setBin(code);
        return tlvPack("01 74",pack.getAll());
    }
    public byte[] tlv_t017A(){
        pack.empty();
        pack.setHex("00 00 00 09");
        return tlvPack("01 7A",pack.getAll());
    }
    public byte[] tlv_t0197(){
        pack.empty();
        pack.setHex("00");
        return tlvPack("01 97",pack.getAll());
    }
    public byte[] tlv_t0542(){
        pack.empty();
        pack.setHex("A2 01 06 6A 04 50 01 68 01");
        return tlvPack("0542",pack.getAll());
    }
    public byte[] tlv_t0401(byte[] rand){//随机16字节
        pack.empty();
        pack.setBin(rand);
        return tlvPack("0401",pack.getAll());
    }


}
