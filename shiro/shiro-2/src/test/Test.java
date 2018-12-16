import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2018/12/16 17:04
 */
public class Test {

    public static void main(String[] args) {
        String password = "123456";
        String hashAlgorithmName = "MD5";
        int hashIterations = 1024;
        String credentialsSalt = "user";
        Object pwd = new SimpleHash(hashAlgorithmName,password,credentialsSalt,hashIterations);
        System.out.println(pwd);
    }
}
