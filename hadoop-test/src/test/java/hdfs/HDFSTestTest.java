package hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;

/**
 * @author wutao
 * @date 2019/6/18
 */
public class HDFSTestTest {

    private static final String HDFS_PATH="hdfs://ubuntu:9000";

    Configuration configuration = null;

    FileSystem fs = null;

    /**
     * 操作hdfs会出现用户权限不足,这时只需指定用户即可(设置环境变量HADOOP_USER_NAME)
     * http://www.huqiwen.com/2013/07/18/hdfs-permission-denied/
     * @throws Exception
     */
    @Test
    public void mkdir()throws Exception{
        fs.mkdirs(new Path("/user/wutao/test/test1"));
    }

    @Before
    public void setUp() throws Exception {

        configuration = new Configuration();
//        System.setProperty("HADOOP_USER_NAME","root");
//        fs = FileSystem.get(new URI(HDFS_PATH),configuration);
        fs = FileSystem.get(new URI(HDFS_PATH),configuration,"root");
        System.out.println("HDFSTestTest.setUp");
    }

    @After
    public void tearDown() throws Exception {
        configuration = null;
        fs = null;
        System.out.println("HDFSTestTest.tearDown");
    }
}