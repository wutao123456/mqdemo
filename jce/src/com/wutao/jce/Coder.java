package com.wutao.jce;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/9/11 23:02
 */
public abstract class Coder {

    public static final String KEY_SHA = "SHA";
    public static final String KEY_MD5 = "MD5";
    public static final String KEY_MAC = "HmacMD5";

    public static String encryptBASE64(byte[] key)throws Exception{
        return (new BASE64Encoder()).encodeBuffer(key);
    }

    public static byte[] decryptBASE64(String key)throws Exception{
        return (new BASE64Decoder()).decodeBuffer(key);
    }

    public static byte[] encryptMD5(byte[] data)throws Exception{
        MessageDigest digest = MessageDigest.getInstance(KEY_MD5);
        digest.update(data);
        return digest.digest();
    }

    public static byte[] encryptSHA(byte[] data)throws Exception{
        MessageDigest sha = MessageDigest.getInstance(KEY_SHA);
        sha.update(data);
        return sha.digest();
    }

    /**
     * 初始化MAC密钥
     * @return
     * @throws Exception
     */
    public static String initMacKey()throws Exception{
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_MAC);
        SecretKey secretKey = keyGenerator.generateKey();
        return encryptBASE64(secretKey.getEncoded());
    }

    /**
     * HMAC加密
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encryptHMAC(byte[] data,String key)throws Exception{
        SecretKey secretKey = new SecretKeySpec(decryptBASE64(key),KEY_MAC);
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        mac.init(secretKey);
        return mac.doFinal(data);
    }


    public static void main(String[] args) throws Exception{
        String inputStr = "简单加密";
        System.err.println("原文:\t" + inputStr);

        byte[] inputData = inputStr.getBytes();
        String code = Coder.encryptBASE64(inputData);

        System.err.println("BASE64加密后:\t" + code);

        byte[] output = Coder.decryptBASE64(code);

        String outputStr = new String(output);

        System.err.println("BASE64解密后:\t" + outputStr);
    }

}
