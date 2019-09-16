package com.wutao.jce;

import java.security.*;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wutao
 * @date 2019/9/16
 * 数字签名算法DSA
 */
public class DSACoder extends Coder {
    public static final String ALGORITHM = "DSA";

    /**
     * 默认密钥字节数
     *
     * <pre>
     * DSA
     * Default Keysize 1024
     * Keysize must be a multiple of 64, ranging from 512 to 1024 (inclusive).
     * </pre>
     */
    private static final int KEY_SIZE = 1024;

    /**
     * 默认种子
     */
    private static final String DEFAULT_SEED = "0f22507a10bbddd07d8a3082122966e3";

    private static final String PUBLIC_KEY = "DSAPublicKey";
    private static final String PRIVATE_KEY = "DSAPrivateKey";

    public static Map<String,Object> initKey(String seed)throws Exception{
        Map<String,Object> keyMap = new HashMap<>(2);
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.setSeed(seed.getBytes());
        keyPairGenerator.initialize(KEY_SIZE,secureRandom);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        DSAPublicKey publicKey = (DSAPublicKey)keyPair.getPublic();
        DSAPrivateKey privateKey = (DSAPrivateKey)keyPair.getPrivate();
        keyMap.put(PUBLIC_KEY,publicKey);
        keyMap.put(PRIVATE_KEY,privateKey);
        return keyMap;
    }

    public static Map<String,Object> initKey()throws Exception{
        Map<String,Object> keyMap = initKey(DEFAULT_SEED);
        return keyMap;
    }

    public static String getPublicKey(Map<String,Object> keyMap)throws Exception{
        Key key = (Key) keyMap.get(PUBLIC_KEY);
        return encryptBASE64(key.getEncoded());
    }

    public static String getPrivateKey(Map<String,Object> keyMap)throws Exception{
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        return encryptBASE64(key.getEncoded());
    }

    public static String sign(byte[] data,String privateKey)throws Exception{
        byte[] keyBytes = decryptBASE64(privateKey);
        // 构造PKCS8EncodedKeySpec对象
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
        // KEY_ALGORITHM 指定的加密算法
        KeyFactory factory = KeyFactory.getInstance(ALGORITHM);
        // 取私钥匙对象
        PrivateKey priKey = factory.generatePrivate(pkcs8EncodedKeySpec);

        Signature signature = Signature.getInstance(factory.getAlgorithm());
        signature.initSign(priKey);
        signature.update(data);
        return encryptBASE64(signature.sign());

    }

    public static boolean verify(byte[] data,String publicKey,String sign)throws Exception{
        byte[] keyBytes = decryptBASE64(publicKey);
        // 构造X509EncodedKeySpec对象
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
        // ALGORITHM 指定的加密算法
        KeyFactory factory = KeyFactory.getInstance(ALGORITHM);
        // 取公钥对象
        PublicKey pubKey = factory.generatePublic(x509EncodedKeySpec);

        Signature signature = Signature.getInstance(factory.getAlgorithm());
        signature.initVerify(pubKey);
        signature.update(data);
        return signature.verify(decryptBASE64(sign));

    }

    /**
     * 不单单只有公钥、私钥，还有数字签名。私钥加密生成数字签名，公钥验证数据及签名。
     * 如果数据和签名不匹配则认为验证失败！数字签名的作用就是校验数据在传输过程中不被修改。数字签名，是单向加密的升级！
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        Map<String,Object> keyMap = initKey();
        String publicKey = getPublicKey(keyMap);
        String privateKey = getPrivateKey(keyMap);
        String test = "sign";
        String sign = sign(test.getBytes(),privateKey);
        System.out.println("签名："+sign);
        boolean verify = verify(test.getBytes(),publicKey,sign);
        System.out.println("验证签名是否正确："+verify);
    }

}
