package com.uohe.core.cmd.sb;

import com.uohe.core.cmd.sb.entity.Cmd;
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

import java.util.Map;

/**
 * 手表获取扫码状态
 * 2021-3-17
 * by GuangHeLiZi
 * 更多请鉴
 * */
public class WtloginTrans_emp_state {


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


        public byte[] part2Core(int seq,byte[] t0018,byte[] share_key){//key  79 87 8B 45 4A B0 1B CF A1 E5 44 39 81 6F EE 2E
            pack.empty();

            pack.setHex("00 00 62 00 00 00 10 00 00 00 72 00 00 00");
            pack.setHex("63 15 C2 F1");//和获取的对应有时会自增2
            pack.setHex("02 00 5E 00 12 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 03 00 00 00 32");//
            pack.setInt(seq);//自增
            pack.setHex("00 00 00 00 00 00 00 00 00 05 01 00 00 00 75 00 00 00 10 00 18");//
            pack.setBin(t0018);//返回的cookie
            pack.setHex("00 00 00 00 00 00 00 00 08 00 00 00 00 03");//



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



        public byte[] outer(byte[] part1Outer,byte[] part2Outer){
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




        public byte[] wtloginTrans_emp_state(Map initUser,byte[] t0018){
            User u= (User) initUser.get("user");
            App app= (App) initUser.get("app");
            Android android= (Android) initUser.get("android");
            Ecdh ecdh= (Ecdh) initUser.get("ecdh");
            UnPackLogin unPackLogin= (UnPackLogin) initUser.get("unPackLogin");
            u.addSeq();//自增
            WtloginTrans_emp_state wtloginTrans_emp_state=new WtloginTrans_emp_state();
            try {


                byte[] part2Core=wtloginTrans_emp_state.part2Core(u.getSeq(),t0018,AllToByte.hexToByte(ecdh.getShare_key()));
                byte[] random16=u.getKsId();
                byte[] puKey= AllToByte.hexToByte(ecdh.getPublic_key());
                byte[] part2Outer=wtloginTrans_emp_state.part2Outer(random16,puKey,part2Core);

                byte[] cmd_0= AllToByte.textToByte(Cmd.SB_0);
                byte[] imsi = AllToByte.textToByte(android.getImsi());
                byte[] appVersion = AllToByte.textToByte(app.getAppVersion());
                byte[] tlv_t010A;
                if(unPackLogin.getToken()==null){
                    tlv_t010A=null;
                }else {
                    tlv_t010A=(byte[])unPackLogin.getToken().get("tlv_t010A");
                }
                byte[] part1Outer = wtloginTrans_emp_state.part1Outer(u.getSeq(), app.getAppid1(), app.getAppid2(),tlv_t010A , unPackLogin.getPart1Token(), cmd_0, AllToByte.textToByte(android.getImei()), random16, imsi, appVersion);

                byte[] outer=wtloginTrans_emp_state.outer(part1Outer,part2Outer);

                return outer;

            }catch (Exception e){

                return null;
            }


        }











}
