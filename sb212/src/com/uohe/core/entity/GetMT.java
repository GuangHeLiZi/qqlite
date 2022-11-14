package com.uohe.core.entity;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;

/**
 * 队列结构封装
 */
public class GetMT implements Serializable {

    //static修饰的成员变量和成员方法独立于该类的任何对象。也就是说，它不依赖类特定的实例，被类的所有实例共享。
    private Deque<Map> deque= new LinkedList<>();
    private Integer port=1024;//监听端口
    private Integer buffLen=16384;//接收长度限制
    private Integer dequeNum=2048;//队列大小 这个是7777端口的限制当前连接大小(子端口的数量)

    private Deque<Map> deque1= new LinkedList<>();
    private Deque<Map> deque2= new LinkedList<>();//用来存放子端口的数据
    private Integer dequeNum1=2048;//队列大小 创建子端口时判断 表示当前可以创建子端口数量

    private Integer conf;//配置成功判断 app和android 为2表示配置完成可执行登录
    private Map<String, ArrayList<Thread>> threadMap;//存入线程对象方便关闭







    public Deque<Map> getDeque() {
        return deque;
    }
    public void setDeque(Deque<Map> deque) {
        this.deque = deque;
    }
    public Integer getPort() {
        return port;
    }
    public void setPort(Integer port) {
        this.port = port;
    }

    public Integer getBuffLen() {
        return buffLen;
    }

    public void setBuffLen(Integer buffLen) {
        this.buffLen = buffLen;
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

    public Deque<Map> getDeque2() {
        return deque2;
    }

    public void setDeque2(Deque<Map> deque2) {
        this.deque2 = deque2;
    }

    public Integer getDequeNum1() {
        return dequeNum1;
    }

    public void setDequeNum1(Integer dequeNum1) {
        this.dequeNum1 = dequeNum1;
    }

    public Integer getConf() {
        return conf;
    }

    public void setConf(Integer conf) {
        this.conf = conf;
    }

    public Map<String, ArrayList<Thread>> getThreadMap() {
        return threadMap;
    }

    public void setThreadMap(Map<String, ArrayList<Thread>> threadMap) {
        this.threadMap = threadMap;
    }

}
