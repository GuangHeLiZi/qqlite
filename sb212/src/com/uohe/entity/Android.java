package com.uohe.entity;

import com.uohe.utils.*;

import java.io.Serializable;


/*
 * 安卓全局配置
 * 2021-10-15
 * *严格
 *
 * by GuangHeLiZi
 * 更多请鉴
 * */
public class Android implements Serializable {






    private String imei;//865166023140588  国际移动电话设备识别码
    private String mac;//00:81:10:9a:e3:97 MAC地址
    private String guid;//md5(byte(imei+mac)) 全球唯一标识符(GUID)
    private String imsi;//460000613232591 中国移动用户标识码
    private String bssid;//00:81:97:9a:db:e3 WiFi子路由
    private String androidId;//0178c7b34876f7d1  安卓id
    private String system;//android  系统
    private String systemVer;//5.1.1 系统版本
    private String isp;//China Mobile GSM 网络服务提供者
    private String ispLink;//wifi 网络服务提供者类型
    private String phoneModel;//LIO-AN00 手机型号
    private String phoneBrand;//HUAWEI 手机品牌
    private String compile;//编译序列
    private String ispName;//wifi名称
    private String unknown1;//52D
    private String core;//52D
    private String rel;//52D
    private String soFingerprinting;//52D
    private String androidGuid;//52D
    private String unknown2;//52D



    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getBssid() {
        return bssid;
    }

    public void setBssid(String bssid) {
        this.bssid = bssid;
    }

    public String getAndroidId() {
        return androidId;
    }

    public void setAndroidId(String androidId) {
        this.androidId = androidId;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getSystemVer() {
        return systemVer;
    }

    public void setSystemVer(String systemVer) {
        this.systemVer = systemVer;
    }

    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }

    public String getIspLink() {
        return ispLink;
    }

    public void setIspLink(String ispLink) {
        this.ispLink = ispLink;
    }

    public String getPhoneModel() {
        return phoneModel;
    }

    public void setPhoneModel(String phoneModel) {
        this.phoneModel = phoneModel;
    }

    public String getPhoneBrand() {
        return phoneBrand;
    }

    public void setPhoneBrand(String phoneBrand) {
        this.phoneBrand = phoneBrand;
    }

    public String getCompile() {
        return compile;
    }

    public void setCompile(String compile) {
        this.compile = compile;
    }

    public String getIspName() {
        return ispName;
    }

    public void setIspName(String ispName) {
        this.ispName = ispName;
    }

    public String getUnknown1() {
        return unknown1;
    }

    public void setUnknown1(String unknown1) {
        this.unknown1 = unknown1;
    }

    public String getCore() {
        return core;
    }

    public void setCore(String core) {
        this.core = core;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getSoFingerprinting() {
        return soFingerprinting;
    }

    public void setSoFingerprinting(String soFingerprinting) {
        this.soFingerprinting = soFingerprinting;
    }

    public String getAndroidGuid() {
        return androidGuid;
    }

    public void setAndroidGuid(String androidGuid) {
        this.androidGuid = androidGuid;
    }

    public String getUnknown2() {
        return unknown2;
    }

    public void setUnknown2(String unknown2) {
        this.unknown2 = unknown2;
    }
}
