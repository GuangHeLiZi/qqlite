package com.uohe.entity;

import java.io.Serializable;


/*
 * 应用全局配置
 * 2021-10-15
 * *严格
 *
 * by GuangHeLiZi
 * 更多请鉴
 * */
public class App implements Serializable {



    private Integer appid1;//537060805
    private Integer appid2;//537060805
    private String mMiscBitmap;//116 F7 FF 7C 00
    private String mSubSigMap;//116 01 04 00 01
    private String subAppIdList;//116 5F 5E 10 E2
    private Integer mainSigMap;//100  34869472
    private String packageFullName;//142 com.tencent.mobileqq
    private String apkVer;//147 38 2E 38 2E 33 38 apk版本[8.8.38]
    private String apkSig;//147 A6 B7 45 BF 24 A2 C2 77 52 77 16 F6 F3 6E B6 8D apk序列版本版本签名
    private Integer time;//tlv177 版本发布时间61 69 9B 1C   1634310940
    private String hexSdkVer;//177 36 2E 30 2E 30 2E 32 34 38 37 [6.0.0.2487] 发布版本
    private String appVersion;//A8.8.38.bce854eb


    public Integer getAppid1() {
        return appid1;
    }

    public void setAppid1(Integer appid1) {
        this.appid1 = appid1;
    }

    public Integer getAppid2() {
        return appid2;
    }

    public void setAppid2(Integer appid2) {
        this.appid2 = appid2;
    }

    public String getmMiscBitmap() {
        return mMiscBitmap;
    }

    public void setmMiscBitmap(String mMiscBitmap) {
        this.mMiscBitmap = mMiscBitmap;
    }

    public String getmSubSigMap() {
        return mSubSigMap;
    }

    public void setmSubSigMap(String mSubSigMap) {
        this.mSubSigMap = mSubSigMap;
    }

    public String getSubAppIdList() {
        return subAppIdList;
    }

    public void setSubAppIdList(String subAppIdList) {
        this.subAppIdList = subAppIdList;
    }

    public Integer getMainSigMap() {
        return mainSigMap;
    }

    public void setMainSigMap(Integer mainSigMap) {
        this.mainSigMap = mainSigMap;
    }

    public String getPackageFullName() {
        return packageFullName;
    }

    public void setPackageFullName(String packageFullName) {
        this.packageFullName = packageFullName;
    }

    public String getApkVer() {
        return apkVer;
    }

    public void setApkVer(String apkVer) {
        this.apkVer = apkVer;
    }

    public String getApkSig() {
        return apkSig;
    }

    public void setApkSig(String apkSig) {
        this.apkSig = apkSig;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getHexSdkVer() {
        return hexSdkVer;
    }

    public void setHexSdkVer(String hexSdkVer) {
        this.hexSdkVer = hexSdkVer;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }
}
