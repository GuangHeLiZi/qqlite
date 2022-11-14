package com.uohe.utils;



import com.uohe.tool.Protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.util.List;
import java.util.Map;

/*
 * 发送和接收的封装
 * 2021-3-17
 * by GuangHeLiZi
 * 更多请鉴
 * */
public class MySocket {

    private Socket socket;//构造客户端请求

    /**
     * @param ip 目标ip地址。
     * @param port 目标端口地址。
     * @return boolean
     */
    public boolean link(String ip,int port){
        this.socketEnd();
        try{
            this.socket =new Socket(ip,port);
            this.socket.setSoTimeout(500);//表示如果对方连接状态 0.5 秒没有收到数据的话强制断开客户端.(设置为零的时候必须得等待接收完成一次数据包后才能发送)
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    /**
     * @param ip 目标ip地址
     * @param port 目标端口地址
     * @param proxyIP 代理服务器地址
     * @param proxyPort 代理服务器端口
     * @return
     */
    public boolean link(String ip,int port,String proxyIP,int proxyPort){
        this.socketEnd();
        try{
            Proxy proxy = new Proxy(Proxy.Type.SOCKS, new InetSocketAddress(proxyIP, proxyPort));
            socket = new Socket(proxy);
            socket.connect(new InetSocketAddress(ip, port));//服务器的ip及地址
            this.socket.setSoTimeout(500);//超时时间关闭连接
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    /**
     * @param sendByte 发送数据。
     * @return boolean
     */
    public synchronized boolean sendData(byte[] sendByte){
        try {
            OutputStream outputStream = this.socket.getOutputStream();
            outputStream.write(sendByte);
            outputStream.flush();
            return true;
        }catch (Exception e){
          e.printStackTrace();
            return false;
        }
    }

    public synchronized byte[] getData() {//接收返回数据
        //接收服务器的相应
        byte[] reply = null;
        int i;
        byte[] bytes = new byte[1];
        byte[] bytesLen = new byte[4];

        try {
            InputStream inputStream = this.socket.getInputStream();//--接收服务器传回的消息的头信息
            inputStream.read(bytesLen, 0, 4);//长度
            int len = ByteToAll.byteToInt(bytesLen);
            while ((i = inputStream.read(bytes)) != -1) {
                if (i == bytes.length && reply == null) {//第一次缓存
                    reply = new byte[i];
                    System.arraycopy(bytes, 0, reply, 0, i);
                } else if (i == bytes.length && reply != null) {//多次缓存
                    byte[] replyLen = new byte[i];//当前长度
                    System.arraycopy(bytes, 0, replyLen, 0, i);
                    reply = ByteUtils.byteMerger(reply, replyLen);
                } else if (i < bytes.length && reply != null) {//最后一次缓存
                    byte[] replyLen = new byte[i];//当前长度
                    System.arraycopy(bytes, 0, replyLen, 0, i);
                    reply = ByteUtils.byteMerger(reply, replyLen);
//                    break;原方案
                } else if (i < bytes.length && reply == null) {//第一次就截取完
                    reply = new byte[i];
                    System.arraycopy(bytes, 0, reply, 0, i);
//                    break;原方案
                }
                if (len == reply.length + 4) {//代替方案.
                    reply = ByteUtils.byteMerger(bytesLen, reply);
                    break;
                }
            }
            return reply;
        } catch (Exception e) {
            return null;
        }
    }






    public synchronized void socketEnd(){//停止所有链接
        if (this.socket!=null){
            try{
                this.socket.close();//关闭此输入流并释放任何相关的系统资源流。
//                this.socket.shutdownInput();//需要约定好双方读写完成的条件，然后关闭输入输出流
//                this.socket.shutdownOutput();//需要约定好双方读写完成的条件，然后关闭输入输出流
//                this.socket.getInputStream().close();//关闭此输入流并释放任何相关的系统资源流。
//                this.socket.getOutputStream().close();//关闭此输入流并释放任何相关的系统资源流。
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }



}
