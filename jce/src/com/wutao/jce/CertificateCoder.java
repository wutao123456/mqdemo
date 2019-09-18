package com.wutao.jce;

import sun.security.x509.X500Name;
import sun.security.x509.X509CertImpl;
import sun.security.x509.X509CertInfo;

import javax.crypto.Cipher;
import javax.net.ssl.*;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.Enumeration;

/**
 * @author wutao
 * @date 2019/9/17
 * 数字证书
 */
public class CertificateCoder extends Coder {

    /**
     * Java密钥库(Java Key Store，JKS)KEY_STORE
     */
    public static final String KEY_STORE = "JKS";

    public static final String X509 = "X.509";

    /**
     * 根据密钥库路径获取密钥库
     * @param keyStorePath
     * @param password
     * @return
     * @throws Exception
     */
    private static KeyStore getKeyStore(String keyStorePath, String password) throws Exception {
        FileInputStream fis = new FileInputStream(keyStorePath);
        KeyStore ks = KeyStore.getInstance(KEY_STORE);
        ks.load(fis, password.toCharArray());
        fis.close();
        return ks;
    }

    /**
     * 从密钥库中获取证书
     * @param alias
     * @param keyStorePath
     * @param password
     * @return
     * @throws Exception
     */
    private static Certificate getCertificate(String alias, String keyStorePath, String password) throws Exception {
        KeyStore ks = getKeyStore(keyStorePath, password);
        Certificate certificate = ks.getCertificate(alias);
        return certificate;
    }

    /**
     * 从文件中读取证书(该证书实际也是将密钥库中的证书写入到该文件的)
     * @param certificatePath
     * @return
     * @throws Exception
     */
    private static Certificate getCertificate(String certificatePath) throws Exception {
        CertificateFactory factory = CertificateFactory.getInstance(X509);
        FileInputStream fis = new FileInputStream(certificatePath);
        Certificate certificate = factory.generateCertificate(fis);
        return certificate;
    }

    /**
     * 从keyStore获得私钥
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
     * 从Certificate获得公钥
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
        X509Certificate x509Certificate = (X509Certificate)getCertificate(alias,keyStorePath,password);
        KeyStore keyStore = getKeyStore(keyStorePath,password);
        PrivateKey privateKey = (PrivateKey)keyStore.getKey(alias,password.toCharArray());

        Signature signature = Signature.getInstance(x509Certificate.getSigAlgName());
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
        //从证书获取公钥
        X509Certificate x509Certificate = (X509Certificate)getCertificate(certificatePath);
        PublicKey publicKey = x509Certificate.getPublicKey();
        Signature signature = Signature.getInstance(x509Certificate.getSigAlgName());
        signature.initVerify(publicKey);
        signature.update(data);
        return signature.verify(decryptBASE64(sign));
    }

    public static SSLSocketFactory getSSLSocketFactory(String password,String keyStorePath,String trustKeyStorePath)throws Exception{
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
        KeyStore keyStore = getKeyStore(keyStorePath,password);
        keyManagerFactory.init(keyStore,password.toCharArray());

        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("SunX509");
        KeyStore trustKeyStore = getKeyStore(trustKeyStorePath,password);
        trustManagerFactory.init(trustKeyStore);

        SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(keyManagerFactory.getKeyManagers(),trustManagerFactory.getTrustManagers(),null);
        return sslContext.getSocketFactory();
    }

    public static void configSSLSocketFactory(HttpsURLConnection connection, String password, String keyStorePath, String trustKeyStorePath)throws Exception{
        connection.setSSLSocketFactory(getSSLSocketFactory(password,keyStorePath,trustKeyStorePath));
    }

    private static String password = "123456";
    private static String alias = "www.wutao.com";
    private static String certificatePath = "f:/wutao.cer";
    private static String keyStorePath = "f:/wutao.keystore";

    public static void main(String[] args) throws Exception{
        System.err.println("公钥加密——私钥解密");
        String inputStr = "Ceritifcate";
        byte[] data = inputStr.getBytes();

        byte[] encrypt = CertificateCoder.encryptByPublicKey(data,
                certificatePath);

        byte[] decrypt = CertificateCoder.decryptByPrivateKey(encrypt,
                alias, keyStorePath, password);
        String outputStr = new String(decrypt);

        System.err.println("加密前: " + inputStr + "\n\r" + "解密后: " + outputStr);





        System.err.println("私钥加密——公钥解密");
        String signIn = "sign";
        byte[] signBytes = signIn.getBytes();

        byte[] encryptData = CertificateCoder.encryptByPrivateKey(signBytes,
                alias,keyStorePath,password);

        byte[] decryptData = CertificateCoder.decryptByPublicKey(encryptData,
                certificatePath);
        String signOut = new String(decryptData);

        System.err.println("加密前: " + signIn + "\n\r" + "解密后: " + signOut);

        String sign = CertificateCoder.sign(signBytes,keyStorePath,alias,password);
        System.out.println("签名："+sign);

        boolean verify = CertificateCoder.verify(signBytes,certificatePath,sign);
        System.out.println("验证签名："+verify);


        KeyStore keyStore = getKeyStore(keyStorePath,password);
        Enumeration enumeration = keyStore.aliases();
        //从密钥库中遍历证书
        while (enumeration.hasMoreElements()){
            String alias = (String)enumeration.nextElement();
            X509Certificate certificate = (X509Certificate)keyStore.getCertificate(alias);
            System.out.println( " 输出证书信息:\n " + certificate.toString());
            System.out.println( " 版本号: " + certificate.getVersion());
            System.out.println( " 序列号: " + certificate.getSerialNumber().toString( 16 ));
            System.out.println( " 主体名： " + certificate.getSubjectDN());
            System.out.println( " 签发者： " + certificate.getIssuerDN());
            System.out.println( " 有效期： " + certificate.getNotAfter());
            System.out.println( " 签名算法： " + certificate.getSigAlgName());
        }
        System.out.println("是否包含："+keyStore.containsAlias(alias));
        Certificate c = keyStore.getCertificate(alias);
        byte[] encode = c.getEncoded();
        X509CertImpl impl = new X509CertImpl(encode);
        //获取X509CertInfo对象
        X509CertInfo info = (X509CertInfo)impl.get(X509CertImpl.NAME + "." + X509CertImpl.INFO);
        //获取X509Name类型的签发者信息
        X500Name issuer = (X500Name)info.get(X509CertInfo.SUBJECT + "." + X509CertInfo.DN_NAME);

        testHttps();


    }

    public static void testHttps()throws Exception{
        URL url = new URL("https://www.wutao.com/examples/");
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

        conn.setDoInput(true);
        conn.setDoOutput(true);

        CertificateCoder.configSSLSocketFactory(conn, password,
                keyStorePath, keyStorePath);

        InputStream is = conn.getInputStream();

        int length = conn.getContentLength();

        DataInputStream dis = new DataInputStream(is);
        byte[] data = new byte[length];
        dis.readFully(data);

        dis.close();
        System.err.println(new String(data));
        conn.disconnect();
    }
}