package com.dlh.https;

import javax.net.ssl.*;
import java.io.*;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/4/3 22:50
 */
public class HttpsUtil {

    public static final class DefaultTrustManager implements X509TrustManager{

        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

        }

        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

        }

        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    private static HttpsURLConnection getHttpsURLConnection(String uri,String method)throws IOException {
        SSLContext context = null;
        try {
            context = SSLContext.getInstance("TLS");
            context.init(new KeyManager[0],new TrustManager[]{new DefaultTrustManager()},new SecureRandom());
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }

        SSLSocketFactory sslSocketFactory = context.getSocketFactory();
        URL url = new URL(uri);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setSSLSocketFactory(sslSocketFactory);
        connection.setHostnameVerifier(new HostnameVerifier() {
            public boolean verify(String s, SSLSession sslSession) {
                return true;
            }
        });
        connection.setRequestMethod(method);
        connection.setDoInput(true);
        connection.setDoOutput(true);
        return connection;
    }

    private static void setBytesToStream(OutputStream outputStream,byte[] bytes)throws IOException{
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        byte[] kb = new byte[1024];
        int len;
        while ((len = byteArrayInputStream.read(kb)) != -1){
            outputStream.write(kb,0,len);
        }

        outputStream.flush();
        outputStream.close();
        byteArrayInputStream.close();
    }

    private static byte[] getBytesFromStream(InputStream inputStream)throws IOException{
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] kb = new byte[1024];
        int len;
        while ((len = inputStream.read(kb)) != -1){
            byteArrayOutputStream.write(kb,0,len);
        }

        byte[] bytes = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();
        inputStream.close();
        return bytes;
    }

    public static byte[] doGet(String url)throws IOException{
        HttpsURLConnection connection = getHttpsURLConnection(url,"GET");
        return getBytesFromStream(connection.getInputStream());
    }

    public static byte[] doPost(String url,String data)throws IOException{
        HttpsURLConnection connection = getHttpsURLConnection(url,"POST");
        setBytesToStream(connection.getOutputStream(),data.getBytes());
        return getBytesFromStream(connection.getInputStream());
    }

    public static void main(String[] args) throws IOException{
        String uri = "https://www.baidu.com";
        byte[] bytes = doGet(uri);
        System.out.println(new String(bytes,"UTF-8"));
        FileOutputStream fileOutputStream = new FileOutputStream(new File(""));
        fileOutputStream.write(bytes);
        fileOutputStream.close();
        System.out.println("done");
    }
}
