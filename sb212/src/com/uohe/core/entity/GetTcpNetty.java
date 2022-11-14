package com.uohe.core.entity;



import java.io.Serializable;
import java.util.*;

/**
 * 队列结构封装
 */
public class GetTcpNetty implements Serializable {

    //static修饰的成员变量和成员方法独立于该类的任何对象。也就是说，它不依赖类特定的实例，被类的所有实例共享。
    private Deque<Map> deque= new LinkedList<>();
    private Deque<Map> deque1= new LinkedList<>();//记录头
    private Deque<byte[]> deque2= new LinkedList<>();//回执,直到获取关键key上传文件非常重要
    private Deque<List> deque3= new LinkedList<>();//消息记录 设置1024大小防止内存溢出。方便导出和导入
    private Deque<Map> deque4= new LinkedList<>();//qq消息记录,100条先进先出
    private Integer dequeNum=8192;//队列大小
    private Integer deque3Num=1024;//队列大小
    private Integer deque4Num=1024;//队列大小
    private boolean stop=false;




    /**
     * 心跳线程和处理
     */
    private Thread allUnpack;
    private Thread pulse;
    private Thread msg;

    private Map cache;//回执,直到获取关键key上传文件非常重要
    private boolean cacheOk=true;//回执,直到获取关键key上传文件非常重要判断是否完成

    private List cacheVoice=new ArrayList();//语音消息的token


    public Deque<Map> getDeque() {
        return deque;
    }

    public void setDeque(Deque<Map> deque) {
        this.deque = deque;
    }

    public Integer getDequeNum() {
        return dequeNum;
    }

    public void setDequeNum(Integer dequeNum) {
        this.dequeNum = dequeNum;
    }


    public Deque<Map> getDeque1() {
        return deque1;
    }

    public void setDeque1(Deque<Map> deque1) {
        this.deque1 = deque1;
    }

    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }


    public Thread getAllUnpack() {
        return allUnpack;
    }

    public void setAllUnpack(Thread allUnpack) {
        this.allUnpack = allUnpack;
    }

    public Thread getPulse() {
        return pulse;
    }

    public void setPulse(Thread pulse) {
        this.pulse = pulse;
    }

    public Thread getMsg() {
        return msg;
    }

    public void setMsg(Thread msg) {
        this.msg = msg;
    }

    public Deque<byte[]> getDeque2() {
        return deque2;
    }

    public void setDeque2(Deque<byte[]> deque2) {
        this.deque2 = deque2;
    }

    public Map getCache() {
        return cache;
    }

    public void setCache(Map cache) {
        this.cache = cache;
    }

    public boolean isCacheOk() {
        return cacheOk;
    }

    public void setCacheOk(boolean cacheOk) {
        this.cacheOk = cacheOk;
    }

    public List getCacheVoice() {
        return cacheVoice;
    }

    public void setCacheVoice(List cacheVoice) {
        this.cacheVoice = cacheVoice;
    }

    public Deque<List> getDeque3() {
        return deque3;
    }

    public void setDeque3(Deque<List> deque3) {
        this.deque3 = deque3;
    }

    public Deque<Map> getDeque4() {
        return deque4;
    }

    public void setDeque4(Deque<Map> deque4) {
        this.deque4 = deque4;
    }

    public Integer getDeque3Num() {
        return deque3Num;
    }

    public void setDeque3Num(Integer deque3Num) {
        this.deque3Num = deque3Num;
    }

    public Integer getDeque4Num() {
        return deque4Num;
    }

    public void setDeque4Num(Integer deque4Num) {
        this.deque4Num = deque4Num;
    }

}
