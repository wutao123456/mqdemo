package com.wutao.jce;

import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wutao
 * @date 2019/9/16
 * DH加密算法
 */
public class DHCoder extends Coder {

    private static final String ALGORITHM = "DH";

    private static final int KEY_SIZE = 1024;

    public static final String SECRET_ALGORITHM = "DES";

    private static final String PUBLIC_KEY = "DHPublicKey";

    private static final String PRIVATE_KEY = "DHPrivateKey";

    /**
     * 初始化甲方密钥
     * @return
     * @throws Exception
     */
    public static Map<String,Object> initKey()throws Exception{
        Map<String,Object> keyMap = new HashMap<>(2);
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
        keyPairGenerator.initialize(KEY_SIZE);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        //甲方公钥
        PublicKey publicKey = keyPair.getPublic();
        //甲方私钥
        PrivateKey privateKey = keyPair.getPrivate();
        keyMap.put(PUBLIC_KEY,publicKey);
        keyMap.put(PRIVATE_KEY,privateKey);
        return keyMap;
    }

    /**
     * 初始化乙方密钥
     * @param key 甲方密钥
     * @return
     * @throws Exception
     */
    public static Map<String,Object> initKey(String key)throws Exception{
        //解析甲方公钥
        byte[] keyBytes = decryptBASE64(key);
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory factory = KeyFactory.getInstance(ALGORITHM);
        PublicKey pubKey = factory.generatePublic(x509EncodedKeySpec);
        // 由甲方公钥构建乙方密钥
        DHParameterSpec dhParamSpec = ((DHPublicKey) pubKey).getParams();
        Map<String,Object> keyMap = new HashMap<>(2);
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
        keyPairGenerator.initialize(dhParamSpec);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        //乙方公钥
        PublicKey publicKey = keyPair.getPublic();
        //乙方私钥
        PrivateKey privateKey = keyPair.getPrivate();
        keyMap.put(PUBLIC_KEY,publicKey);
        keyMap.put(PRIVATE_KEY,privateKey);
        return keyMap;
    }

    public static SecretKey getSecretKey(String publicKey,String privateKey)throws Exception{
        //初始化公钥
        byte[] publicBytes = decryptBASE64(publicKey);
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
        PublicKey pubKey = keyFactory.generatePublic(x509EncodedKeySpec);

        //初始化私钥
        byte[] privateBytes = decryptBASE64(privateKey);
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateBytes);
        Key priKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        KeyAgreement keyAgreement = KeyAgreement.getInstance(keyFactory.getAlgorithm());
        keyAgreement.init(priKey);
        keyAgreement.doPhase(pubKey,true);
        //生成本地密钥
        SecretKey secretKey = keyAgreement.generateSecret(SECRET_ALGORITHM);
        return secretKey;
    }

    public static String getPublicKey(Map<String,Object> keyMap)throws Exception{
        Key key = (Key) keyMap.get(PUBLIC_KEY);
        return encryptBASE64(key.getEncoded());
    }

    public static String getPrivateKey(Map<String,Object> keyMap)throws Exception{
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        return encryptBASE64(key.getEncoded());
    }

    public static byte[] encrypt(byte[] data,String publicKey,String privateKey)throws Exception{
        SecretKey secretKey = getSecretKey(publicKey,privateKey);
        Cipher cipher = Cipher.getInstance(secretKey.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE,secretKey);
        return cipher.doFinal(data);
    }

    public static byte[] decrypt(byte[] data,String publicKey,String privateKey)throws Exception{
        SecretKey secretKey = getSecretKey(publicKey,privateKey);
        Cipher cipher = Cipher.getInstance(secretKey.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE,secretKey);
        return cipher.doFinal(data);
    }

    /**
     * 1.甲方构建密钥对儿，将公钥公布给乙方，将私钥保留；双方约定数据加密算法；乙方通过甲方公钥构建密钥对儿，将公钥公布给甲方，将私钥保留。
     * 2.甲方使用私钥、乙方公钥、约定数据加密算法构建本地密钥，然后通过本地密钥加密数据，发送给乙方加密后的数据；乙方使用私钥、甲方公钥、约定数据加密算法构建本地密钥，然后通过本地密钥对数据解密。
     * 3.乙方使用私钥、甲方公钥、约定数据加密算法构建本地密钥，然后通过本地密钥加密数据，发送给甲方加密后的数据；甲方使用私钥、乙方公钥、约定数据加密算法构建本地密钥，然后通过本地密钥对数据解密
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        Map<String,Object> keyMap = initKey();
        String apublicKey = getPublicKey(keyMap);
        String aprivateKey = getPrivateKey(keyMap);

        Map<String,Object> bkeyMap = initKey(apublicKey);
        String bpublicKey = getPublicKey(bkeyMap);
        String bprivateKey = getPrivateKey(bkeyMap);

        String input = "test";
        // 由甲方公钥，乙方私钥构建密文
        byte[] encryptData = encrypt(input.getBytes(),apublicKey,bprivateKey);
        //由乙方公钥,甲方私钥解密
        byte[] decryptData = decrypt(encryptData,bpublicKey,aprivateKey);
        System.out.println(new String(decryptData));

        // 由乙方公钥，甲方私钥构建密文
        byte[] encryptData1 = encrypt(input.getBytes(),bpublicKey,aprivateKey);
        //由甲方公钥,乙方私钥解密
        byte[] decryptData1 = decrypt(encryptData1,apublicKey,bprivateKey);
        System.out.println(new String(decryptData1));
    }

}
