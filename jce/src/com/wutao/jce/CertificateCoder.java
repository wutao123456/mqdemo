package com.wutao.jce;

import javax.crypto.Cipher;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.*;
import java.util.Date;

/**
 * @author wutao
 * @date 2019/9/17
 */
public class CertificateCoder extends Coder {

    /**
     * Java密钥库(Java Key Store，JKS)KEY_STORE
     */
    public static final String KEY_STORE = "JKS";

    public static final String X509 = "X.509";

    private static KeyStore getKeyStore(String keyStorePath, String password) throws Exception {
        FileInputStream fis = new FileInputStream(keyStorePath);
        KeyStore ks = KeyStore.getInstance(KEY_STORE);
        ks.load(fis, password.toCharArray());
        fis.close();
        return ks;
    }

    private static Certificate getCertificate(String alias, String keyStorePath, String password) throws Exception {
        KeyStore ks = getKeyStore(keyStorePath, password);
        Certificate certificate = ks.getCertificate(alias);
        return certificate;
    }

    private static Certificate getCertificate(String certificatePath) throws Exception {
        CertificateFactory factory = CertificateFactory.getInstance(X509);
        FileInputStream fis = new FileInputStream(certificatePath);
        Certificate certificate = factory.generateCertificate(fis);
        return certificate;
    }

    /**
     * 由keyStore获得私钥
     *
     * @param alias
     * @param keyStorePath
     * @param password
     * @return
     * @throws Exception
     */
    private static PrivateKey getPrivateKey(String alias, String keyStorePath, String password) throws Exception {
        KeyStore ks = getKeyStore(keyStorePath, password);
        PrivateKey privateKey = (PrivateKey) ks.getKey(alias, password.toCharArray());
        return privateKey;
    }

    /**
     * 由Certificate获得公钥
     *
     * @param certificatePath
     * @return
     * @throws Exception
     */
    private static PublicKey getPublicKey(String certificatePath) throws Exception {
        Certificate certificate = getCertificate(certificatePath);
        PublicKey publicKey = certificate.getPublicKey();
        return publicKey;
    }

    private static byte[] encryptByPrivateKey(byte[] data, String alias, String keyStorePath, String password) throws Exception {
        PrivateKey privateKey = getPrivateKey(alias, keyStorePath, password);

        Cipher cipher = Cipher.getInstance(privateKey.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }

    private static byte[] encryptByPublicKey(byte[] data, String certificatePath) throws Exception {
        PublicKey publicKey = getPublicKey(certificatePath);

        Cipher cipher = Cipher.getInstance(publicKey.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }

    private static byte[] decryptByPrivateKey(byte[] data, String alias, String keyStorePath, String password) throws Exception {
        PrivateKey privateKey = getPrivateKey(alias, keyStorePath, password);

        Cipher cipher = Cipher.getInstance(privateKey.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }

    private static byte[] decryptByPublicKey(byte[] data, String certificatePath) throws Exception {
        PublicKey publicKey = getPublicKey(certificatePath);

        Cipher cipher = Cipher.getInstance(publicKey.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }

    public static boolean verifyCertificate(Date date, String certificatePath) {
        boolean status = true;
        try {
            Certificate certificate = getCertificate(certificatePath);
            status = verifyCertificate(date, certificate);
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }

        return status;
    }

    public static boolean verifyCertificate(Date date, Certificate certificate) {
        boolean status = true;
        try {
            X509Certificate x509Certificate = (X509Certificate) certificate;
            x509Certificate.checkValidity(date);
        } catch (Exception e) {
            status = false;
        }
        return status;
    }

    public static boolean verifyCertificate(String certificatePath) {
        return verifyCertificate(new Date(), certificatePath);
    }

    public static boolean verifyCertificate(Date date, String keyStorePath, String alias, String password) {
        boolean status = true;
        try {
            Certificate certificate = getCertificate(alias, keyStorePath, password);
            status = verifyCertificate(date, certificate);
        } catch (Exception e) {
            status = false;
        }
        return status;
    }

    public static boolean verifyCertificate(String keyStorePath, String alias, String password) {
        return verifyCertificate(new Date(),keyStorePath,alias,password);
    }

    /**
     * 签名
     * @param data
     * @param keyStorePath
     * @param alias
     * @param password
     * @return
     * @throws Exception
     */
    public static String sign(byte[] data,String keyStorePath, String alias, String password)throws Exception{
        PrivateKey privateKey = getPrivateKey(alias,keyStorePath,password);

        Signature signature = Signature.getInstance(privateKey.getAlgorithm());
        signature.initSign(privateKey);
        signature.update(data);
        return encryptBASE64(signature.sign());
    }

    /**
     * 验证签名
     * @param data
     * @param certificatePath
     * @param sign
     * @return
     * @throws Exception
     */
    public static boolean verify(byte[] data,String certificatePath,String sign)throws Exception{
        PublicKey publicKey = getPublicKey(certificatePath);
        Signature signature = Signature.getInstance(publicKey.getAlgorithm());
        signature.initVerify(publicKey);
        signature.update(data);
        return signature.verify(decryptBASE64(sign));
    }

    private static String password = "123456";
    private static String alias = "www.wutao.site";
    private static String certificatePath = "d:/wutao.cer";
    private static String keyStorePath = "d:/wutao.keystore";

    public static void main(String[] args) throws Exception{
        System.err.println("公钥加密——私钥解密");
        String inputStr = "Ceritifcate";
        byte[] data = inputStr.getBytes();

        byte[] encrypt = CertificateCoder.encryptByPublicKey(data,
                certificatePath);

        byte[] decrypt = CertificateCoder.decryptByPrivateKey(encrypt,
                keyStorePath, alias, password);
        String outputStr = new String(decrypt);

        System.err.println("加密前: " + inputStr + "\n\r" + "解密后: " + outputStr);
    }
}