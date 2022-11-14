package com.uohe.dao;



/**
* tea加解密接口
* 2021-3-17
* by GuangHeLiZi
* 更多请鉴 关于TEA加解密思路
*/
public interface TEADao {




    /**
     * @param in 需要加密的明文
     * @param in  明文长度
     * @param k 密钥
     * @return Message 密文
     */
    public byte[] encrypt(byte[] in, byte[] k);



    /**
     * @param in 需要被解密的密文
     * @param in 密文长度
     * @param k 密钥
     * @return Message 已解密的消息
     */
    public byte[] decrypt(byte[] in, byte[] k);


    /**
     * 加密自定义位置
     * @param in 明文字节数组
     * @param offset 开始加密的偏移
     * @param len 加密长度
     * @param k 密钥
     * @return 密文字节数组
     */
    public byte[] encrypt(byte[] in, int offset, int len, byte[] k);


    /**
     * 解密自定义位置
     * @param in 密文
     * @param offset 密文开始的位置
     * @param len 密文长度
     * @param k 密钥
     * @return 明文
     */
    public byte[] decrypt(byte[] in, int offset, int len, byte[] k);




}
