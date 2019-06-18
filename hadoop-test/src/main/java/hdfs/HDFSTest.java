package hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.net.URI;

/**
 * @author wutao
 * @date 2019/6/18
 */
public class HDFSTest {

    /**
     * <property>
     * <!-- 是否在HDFS中开启权限检查。-->
     * <name>dfs.permissions.enabled</name>
     * <value>true</value>
     * </property>
     * <property>
     * <!-- 是否在hdfs开启acl，默认为false-->
     * <name>dfs.namenode.acls.enabled</name>
     * <value>true</value>
     * </property>
     */

    private static final String HDFS_PATH="hdfs://ubuntu:9000";

    private static Configuration configuration = null;

    private static FileSystem fs = null;

    public static void init() throws Exception {
        configuration = new Configuration();
//        System.setProperty("HADOOP_USER_NAME","root");
//        fs = FileSystem.get(new URI(HDFS_PATH),configuration);
        fs = FileSystem.get(new URI(HDFS_PATH),configuration,"root");
        System.out.println("HDFSTestTest.setUp");
    }

    public static void close() throws Exception {
        configuration = null;
        fs = null;
        System.out.println("HDFSTestTest.tearDown");
    }


    /**
     * 操作hdfs会出现用户权限不足,这时只需指定用户即可(设置环境变量HADOOP_USER_NAME)
     * http://www.huqiwen.com/2013/07/18/hdfs-permission-denied/
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        init();
//        fs.mkdirs(new Path("/user/wutao/test/test1"));
        fs.delete(new Path("/user/wutao/test/test1"));
    }
}
