package com.uohe.tool;


import com.uohe.utils.AllToByte;
import com.uohe.utils.ByteToAll;

import java.io.Serializable;

/*
 * Pack类实现包整体结构
 * 2021-3-17
 * by GuangHeLiZi
 * 更多请鉴
 * */
public class Pack implements Serializable {//过程组包
    private byte[] bytes;//声明全局变量
    public void empty(){//创建byte[]=null
        this.bytes=null;
    }
    public byte[] getAll(){//返回当前字节
        return this.bytes;
    }
    public Integer lenth(){//取长度
        return this.bytes.length;
    }
    public void setBin(byte[] bytes){//放入容器
        if (bytes!=null&&this.bytes!=null){
            int len=bytes.length;
            int lent=this.bytes.length;
            byte[] bytes1=new byte[len+lent];
            System.arraycopy(this.bytes,0,bytes1,0,lent);
            System.arraycopy(bytes,0,bytes1,lent,len);
            this.bytes=bytes1;
        }else if(bytes!=null){
            this.bytes=bytes;
        }
    }
    public void setByte(byte by){//直接拼接到全局变量bytes
        this.setBin(new byte[]{by});
    }
    public void setDate(byte[] bys){//直接拼接到全局变量bytes
        this.setBin(bys);
    }
    public void setHex(String str){//直接拼接到全局变量bytes
        AllToByte allToByte=new AllToByte();
        this.setBin(allToByte.hexToByte(str));
    }
    public void setInt(int number){//直接拼接到全局变量bytes
        AllToByte allToByte=new AllToByte();
        ByteToAll byteToAll=new ByteToAll();
        this.setBin(allToByte.intToByte(number));
    }
    public void setLong(Long l){//直接拼接到全局变量bytes
        AllToByte allToByte=new AllToByte();
        this.setBin(allToByte.longToByte(l));
    }
    public void setShort(short s){//直接拼接到全局变量bytes
        AllToByte allToByte=new AllToByte();
        ByteToAll byteToAll=new ByteToAll();
        this.setBin(allToByte.shortToByte(s));
    }
    public void setStr(String str){//直接拼接到全局变量bytes
        AllToByte allToByte=new AllToByte();
        this.setBin(allToByte.textToByte(str));
    }
    public void setToken(byte[] bytes){//直接拼接到全局变量bytes
        this.setBin(bytes);
    }
    public void setUint(int number){//直接拼接到全局变量bytes
        AllToByte allToByte=new AllToByte();
        this.setBin(allToByte.intToByte(number));
    }



}
