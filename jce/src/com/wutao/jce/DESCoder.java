package com.wutao.jce;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.Key;
import java.security.SecureRandom;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/9/11 23:20
 */
public class DESCoder extends Coder {

    public static final String ALGORITHM = "DES";

    public static String initKey()throws Exception{
        return initKey(null);
    }

    /**
     * 生成密钥
     * @param seed
     * @return
     * @throws Exception
     */
    public static String initKey(String seed)throws Exception{
        SecureRandom secureRandom = null;
        if(seed != null){
            secureRandom = new SecureRandom(decryptBASE64(seed));
        }else{
            secureRandom = new SecureRandom();
        }
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        SecretKey key = keyGenerator.generateKey();
        return encryptBASE64(key.getEncoded());
    }

    public static Key toKey(byte[] key)throws Exception{
        DESKeySpec dks = new DESKeySpec(key);
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        SecretKey secretKey = secretKeyFactory.generateSecret(dks);

        return secretKey;
    }

    public static byte[] encrypt(String key,byte[] data)throws Exception{
        Key k = toKey(decryptBASE64(key));

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE,k);
        return cipher.doFinal(data);
    }

    public static byte[] decrypt(String key,byte[] data)throws Exception{
        Key k = toKey(decryptBASE64(key));

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE,k);
        return cipher.doFinal(data);
    }

    public static void main(String[] args) throws Exception{
        String key = initKey();
        byte[] encryptData = encrypt(key,"test".getBytes());
        System.out.println(new String(encryptData));

        byte[] decryptData = decrypt(key,encryptData);
        System.out.println(new String(decryptData));

    }
}
