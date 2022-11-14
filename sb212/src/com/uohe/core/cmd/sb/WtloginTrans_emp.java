package com.uohe.core.cmd.sb;

import com.uohe.core.cmd.sb.entity.Cmd;
import com.uohe.core.cmd.sb.init.UserConf0;
import com.uohe.dao.TEADao;
import com.uohe.daoimpl.TEADaoDao;
import com.uohe.entity.*;
import com.uohe.tool.Jce;
import com.uohe.tool.Pack;
import com.uohe.tool.Tlv;
import com.uohe.utils.AllToByte;
import com.uohe.utils.ByteToAll;
import com.uohe.utils.MySocket;
import com.uohe.utils.UnPack;

import java.util.*;

import static com.uohe.utils.Image.ShowImage;
import static com.uohe.utils.Image.createQrCode;


/**
 * 手表获取二维码
 * 2021-3-17
 * by GuangHeLiZi
 * 更多请鉴
 * */
public class WtloginTrans_emp {


        private Pack pack=new Pack();
        private Tlv tlv=new Tlv();
        private TEADao teaDao=new TEADaoDao();

        public byte[] part2Outer(byte[] random16,byte[] publicKey,byte[] part2Core){
            pack.empty();
            pack.setHex("1F 41");
            pack.setHex("08 12");
            pack.setHex("00 01");
            pack.setHex("00 00 00 00");//98 EB 9F 77  [2565578615]
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


        public byte[] part2Core(byte[] share_key){//key  79 87 8B 45 4A B0 1B CF A1 E5 44 39 81 6F EE 2E
            pack.empty();
            //pack.setHex("00 00 62 00 00 00 10 00 00 00 72 00 00 00 62 F2 3E 7F 02 00 5E 00 12 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 03 00 00 00 32 00 00 00 17 00 00 00 00 00 00 00 00 00 05 01 00 00 00 75 00 00 00 10 00 18 05 11 16 B6 02 C5 46 F4 D2 19 50 E9 D9 F1 23 7A C0 15 95 19 1A 15 53 67 00 00 00 00 00 00 00 00 08 00 00 00 00 03");
            //pack.setHex("00 01 11 00 00 00 10 00 00 00 72 00 00 00 61 BD 3E F7 02 01 0D 00 31 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 03 00 00 00 32 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 10 00 00 00 00 00 00 00 00 08 00 00 00 06 00 16 00 49 00 00 00 05 00 00 00 10 20 03 02 C6 7B 14 F7 F4 07 5A 60 A2 15 62 F2 BD B5 67 13 11 00 12 63 6F 6D 2E 74 65 6E 63 65 6E 74 2E 71 71 6C 69 74 65 00 05 32 2E 31 2E 33 00 10 A6 B7 45 BF 24 A2 C2 77 52 77 16 F6 F3 6E B6 8D 00 1B 00 1E 00 00 00 00 00 00 00 00 00 00 00 08 00 00 00 04 00 00 00 48 00 00 00 02 00 00 00 02 00 00 00 1D 00 0E 01 00 F7 FF 7C 00 00 00 00 00 00 00 00 00 00 1F 00 2D 01 00 07 61 6E 64 72 6F 69 64 00 05 37 2E 31 2E 32 00 02 00 10 43 68 69 6E 61 20 4D 6F 62 69 6C 65 20 47 53 4D 00 00 00 04 77 69 66 69 00 33 00 10 7B 14 F7 F4 07 5A 60 A2 15 62 F2 BD B5 67 13 11 00 35 00 04 00 00 00 73 03");
            pack.setHex("00 01 11 00 00 00 10 00 00 00 72 00 00 00 63 15 C2 F1 02 01 0D 00 31 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 03 00 00 00 32 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 10 00 00 00 00 00 00 00 00 08 00 00 00 06 00 16 00 49 00 00 00 05 00 00 00 10 20 02 FC 74 4B C7 A2 8B E7 DC 7C 89 9A 65 94 F8 A6 30 F8 4B 00 12 63 6F 6D 2E 74 65 6E 63 65 6E 74 2E 71 71 6C 69 74 65 00 05 32 2E 31 2E 32 00 10 A6 B7 45 BF 24 A2 C2 77 52 77 16 F6 F3 6E B6 8D 00 1B 00 1E 00 00 00 00 00 00 00 00 00 00 00 08 00 00 00 04 00 00 00 48 00 00 00 02 00 00 00 02 00 00 00 1D 00 0E 01 00 F7 FF 7C 00 00 00 00 00 00 00 00 00 00 1F 00 2D 01 00 07 61 6E 64 72 6F 69 64 00 05 35 2E 31 2E 31 00 02 00 10 43 68 69 6E 61 20 4D 6F 62 69 6C 65 20 47 53 4D 00 00 00 04 77 69 66 69 00 33 00 10 4B C7 A2 8B E7 DC 7C 89 9A 65 94 F8 A6 30 F8 4B 00 35 00 04 00 00 00 75 03");

            return teaDao.encrypt(pack.getAll(), share_key);
        }




        public byte[] part1Outer(int seq,int appid1,int appid2,byte[] tlv010A,byte[] part1Token,byte[] cmd_0,byte[] imei,byte[] ksId,byte[] imsi,byte[] appVersion){
            pack.empty();
            pack.setInt(seq);
            pack.setInt(appid1);
            pack.setInt(appid2);
            pack.setHex("01 00 00 00");
            pack.setHex("00 00 00 00");
            pack.setHex("00 00 00 00");
            pack.setHex("00 00 00 04");
            pack.setInt((cmd_0.length + 4));
            pack.setBin(cmd_0);
            pack.setHex("00 00 00 08 73 C6 0D AD");
            pack.setInt((imei.length+4));
            pack.setBin(imei);
            pack.setHex("00 00 00 04");
            pack.setHex("00 20 7C 34 36 30 30 30 30 36 31 33 32 33 32 35 39 31 7C 41 74 65 73 74 72 65 76 69 73 69 6F 6E");
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
            pack.setHex("00 00 00 05");//user ascii 长度5
            pack.setHex("30");//user
            pack.setBin(bin);
            bin=pack.getAll();
            pack.empty();
            pack.setInt(bin.length+4);
            pack.setBin(bin);
            return pack.getAll();
        }




        public byte[] wtloginTrans_emp(Map initUser){
            User u= (User) initUser.get("user");
            App app= (App) initUser.get("app");
            Android android= (Android) initUser.get("android");
            Ecdh ecdh= (Ecdh) initUser.get("ecdh");
            UnPackLogin unPackLogin= (UnPackLogin) initUser.get("unPackLogin");
            u.addSeq();//自增
            WtloginTrans_emp wtloginTrans_emp=new WtloginTrans_emp();
            try {


                byte[] part2Core=wtloginTrans_emp.part2Core(AllToByte.hexToByte(ecdh.getShare_key()));
                byte[] random16=u.getKsId();
                byte[] puKey= AllToByte.hexToByte(ecdh.getPublic_key());
                byte[] hexUser= AllToByte.hexToByte(u.toUserHex());//"98 EB 9F 77"
                byte[] part2Outer=wtloginTrans_emp.part2Outer(random16,puKey,part2Core);

                byte[] cmd_0= AllToByte.textToByte(Cmd.SB_0);
                byte[] imsi = AllToByte.textToByte(android.getImsi());
                byte[] appVersion = AllToByte.textToByte(app.getAppVersion());
                byte[] tlv_t010A;
                if(unPackLogin.getToken()==null){
                    tlv_t010A=null;
                }else {
                    tlv_t010A=(byte[])unPackLogin.getToken().get("tlv_t010A");
                }
                byte[] part1Outer = wtloginTrans_emp.part1Outer(u.getSeq(), app.getAppid1(), app.getAppid2(),tlv_t010A , unPackLogin.getPart1Token(), cmd_0, AllToByte.textToByte(android.getImei()), random16, imsi, appVersion);

                byte[] outer=wtloginTrans_emp.outer(part1Outer,part2Outer, AllToByte.textToByte(u.getUser()));

                return outer;

            }catch (Exception e){

                return null;
            }


        }


    public static void main(String[] args) {
        UserConf0 userConf0 = new  UserConf0();
        Map initUser=userConf0.UserConf0("100000","123456",null,null);
        WtloginTrans_emp wtloginTrans_emp=new WtloginTrans_emp();
        byte[] bytes=wtloginTrans_emp.wtloginTrans_emp(initUser);
        MySocket mySocket1= (MySocket) initUser.get("mySocket1");
        mySocket1.sendData(bytes);
        List list = null;
        for (int i = 0; i < 10; i++) {
            byte[] wtloginTrans_empData=mySocket1.getData();
            if (wtloginTrans_empData!=null) {
                 list=unPack(wtloginTrans_empData,initUser);
                break;
            }
        }

        if (list!=null){

            byte[] t0018= (byte[]) list.get(0);
            String qr= (String) list.get(1);//二维码
            System.out.println(qr);
            String url2=System.getProperty("user.dir");//运行路劲
            String url=url2+"\\qr.jpg";
            createQrCode(qr, url2, "qr.jpg");
            ShowImage(url);


            WtloginTrans_emp_state wtloginTrans_emp_state=new WtloginTrans_emp_state();
            byte[] wtloginTrans_emp_stateData=wtloginTrans_emp_state.wtloginTrans_emp_state(initUser, t0018);
//            System.out.println("生成："+ByteToAll.byteToHxe(wtloginTrans_emp_stateData));

            for (int i = 0; i < 40; i++) {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mySocket1.sendData(wtloginTrans_emp_stateData);
                byte[] bytes1=mySocket1.getData();
                unPackState(bytes1,initUser);
            }


        }



    }

//00 00 00 38 00 02 00 38 00 12 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 03 00 00 00 32 00 00 00 21 00 00 00 00 00 00 00 00 00 05 01 00 00 00 75 00 00 00 10 30 03    //没有扫

//00 00 00 38 00 02 00 38 00 12 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 03 00 00 00 32 00 00 00 26 00 00 00 00 00 00 00 00 00 05 01 00 00 00 75 00 00 00 10 35 03 //扫码了
//00 00 00 38 00 02 00 38 00 12 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 03 [四字节递增] 00 00 00 26 00 00 00 00 00 00 00 00 00 05 01 00 00 00 75 00 00 00 10 [30没有扫码35扫码了] 03 //扫码了






    /**
     * 解析扫码状态
     * @param data 未知的结构
     * @return int值
     */
    public static List unPackState(byte[] data, Map initUser){
        List list = new ArrayList();
        Ecdh ecdh= (Ecdh) initUser.get("ecdh");
        TEADao teaDao=new TEADaoDao();//tea加解密
        Jce jce=new Jce();
        UnPack unPack=new UnPack();
        unPack.setData(data);
        unPack.getInt();//包长 00 00 01 63//包长度355
        int type=unPack.getInt();//包类型 00 00 00 08//包类型8
        byte key=unPack.getByte();//包加密方式 02//包加密方式2
        unPack.getByte();//
        unPack.getInt();//00 00 00 05
        unPack.getByte();//30


        try {
            unPack.setData(teaDao.decrypt(unPack.getAll(), new byte[16]));//outer
            byte[] part1Outer=unPack.getBin(unPack.getInt()-4);//Len part1Outer
            byte[] part2Outer=unPack.getBin(unPack.getInt()-4);//Len part2Outer
            //part1Outer
            unPack.setData(part1Outer);
            unPack.getBin(4);//用来配备发送的数据,seq相等就是此数据的返回包
            unPack.getInt();//00 00 00 00
            unPack.getInt();//00 00 00 04
            byte[] cmd=unPack.getBin(unPack.getInt()-4);//用来配备发送的数据,cmd相等就是此数据的返回包
            byte[] part1Token=unPack.getBin(unPack.getInt()-4);//token 记录一下，接下来的发送改成这里的

            unPack.setData(part2Outer);//part2Outer

            unPack.getByte();//02
            unPack.getShort();//包长度
            unPack.getShort();//1F 41
            unPack.getShort();//08 12
            unPack.getShort();//00 01
            unPack.getInt();//00 00 00 00
            unPack.getBin(3);//登录状态
            unPack.setData(unPack.getBin(unPack.getAll().length-1));//去掉03
            unPack.setData(teaDao.decrypt(unPack.getAll(), AllToByte.hexToByte(ecdh.getShare_key())));

            unPack.getShort();//00 00
            unPack.getShort();//长度
            unPack.getShort();//00 02
            unPack.getShort();//长度
            unPack.getBin(unPack.getShort());//00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00
            unPack.getInt();//00 00 00 03
            unPack.getInt();//00 00 00 32
            unPack.getInt();//自增req
            unPack.getInt();//00 00 00 00
            unPack.getInt();//00 00 00 00
            unPack.getInt();//00 05 01 00
            unPack.getInt();//00 00 75 00
            int typeState=unPack.getInt();//扫码状态判断登录 00 00 10 30 未扫码 00 00 10 35 扫码待确认 00 00 10 36 扫码取消 00 00 10 00 返回登录所需参数
            if (typeState==4096){
                unPack.getInt();//00 00 00 00
                unPack.getInt();//qq
                unPack.getInt();//随机63 49 0E 5E
                short tlvSize=unPack.getShort();//tlv总数
                byte[] tlvByte=unPack.getAll();
                Map map=unTlv(tlvSize,tlvByte);
                System.out.println("登录令牌："+map);
            }
            if (typeState==4144){//未扫码
                System.out.println("未扫码");
            }
            if (typeState==4149){//扫码待确认
                System.out.println("扫码待确认");
            }

            if (typeState==4150){//扫码取消
                System.out.println("扫码取消");
            }

            return list;


        }catch (Exception e){
            e.printStackTrace();
            return list;
        }




    }


    public static Map unTlv(short size, byte[] data){
        UnPack unPack=new UnPack();
        unPack.setData(data);
        Map map=new HashMap();
        for (int i = 0; i < size; i++) {
            byte[] tlvCmd = unPack.getBin(2);//头
            short len = unPack.getShort();//取长度
            map.put("tlv_t" + ByteToAll.byteToHxe(tlvCmd), ByteToAll.byteToHxe(unPack.getBin(len)));
        }
        return map;
    }








    /**
     * 解析二维码图片
     * @param data 未知的结构
     * @return int值
     */
    public static List unPack(byte[] data,Map initUser){
        List list = new ArrayList();
        Ecdh ecdh= (Ecdh) initUser.get("ecdh");
        TEADao teaDao=new TEADaoDao();//tea加解密
        Jce jce=new Jce();
        UnPack unPack=new UnPack();
        unPack.setData(data);
        unPack.getInt();//包长
        int type=unPack.getInt();//包类型
        byte key=unPack.getByte();//包加密方式
        unPack.getByte();//0
        unPack.getInt();//00 00 00 05
        unPack.getByte();//30


        try {
            unPack.setData(teaDao.decrypt(unPack.getAll(), new byte[16]));//outer
            byte[] part1Outer=unPack.getBin(unPack.getInt()-4);//Len part1Outer
            byte[] part2Outer=unPack.getBin(unPack.getInt()-4);//Len part2Outer
            //part1Outer
            unPack.setData(part1Outer);
            unPack.getBin(4);//用来配备发送的数据,seq相等就是此数据的返回包
            unPack.getInt();//0
            unPack.getInt();//04
            byte[] cmd=unPack.getBin(unPack.getInt()-4);//用来配备发送的数据,cmd相等就是此数据的返回包
            byte[] part1Token=unPack.getBin(unPack.getInt()-4);//token 记录一下，接下来的发送改成这里的





            unPack.setData(part2Outer);//part2Outer

            unPack.getByte();//02
            unPack.getShort();//02 F1
            unPack.getShort();//1F 41
            unPack.getShort();//08 12
            unPack.getShort();//00 01
            unPack.getInt();//00 00 00 00
            unPack.getBin(3);//00 00 00 登录状态
            unPack.setData(unPack.getBin(unPack.getAll().length-1));

            unPack.setData(teaDao.decrypt(unPack.getAll(), AllToByte.hexToByte(ecdh.getShare_key())));

            unPack.getShort();//00 00
            unPack.getShort();//02 CD 递增
            unPack.getShort();//00 02
            unPack.getShort();//02 CD 递增
            unPack.getBin(49);//
            byte[] t0018=unPack.getBin(24);//获取扫码状态需要

            list.add(t0018);

            String qr=ByteToAll.byteToText(unPack.getAll());//
            int tag=qr.indexOf("https");
            String qrHex=ByteToAll.byteToHxe(AllToByte.textToByte(qr.substring(tag)));//
            int end=qrHex.indexOf("1A");

            qrHex=qrHex.substring(0,end);

            qr=ByteToAll.byteToText(AllToByte.hexToByte(qrHex));//
            list.add(qr);
            return list;


        }catch (Exception e){
            return list;
        }




    }






}
