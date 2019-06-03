package hadoop.cache;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.net.URI;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/6/2 22:43
 */
public class DistributedCacheDriver {

    /**
     * 运行该环境需安装hadoop window环境
     * hadoop bin目录下添加winutil.exe
     * Windows/System32 添加hadoop.dll
     * @param args
     * @throws Exception
     */
    public static void main(String[] args)throws Exception {

        // 0 根据自己电脑路径重新配置
        args = new String[] { "C:/Users/admin/Desktop/wc/cache/input", "C:/Users/admin/Desktop/wc/cache/output" };

        // 1 获取job信息
        Configuration configuration = new Configuration();
        Job job = Job.getInstance(configuration);

        // 2 设置加载jar包路径
        job.setJarByClass(DistributedCacheDriver.class);

        // 3 关联map
        job.setMapperClass(DistributedCacheMapper.class);

        // 4 设置最终输出数据类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);

        // 5 设置输入输出路径
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        // 6 加载缓存数据
        job.addCacheFile(new URI("file:///C:/Users/admin/Desktop/wc/cache/pd.txt"));

        // 7 Map端Join的逻辑不需要Reduce阶段，设置reduceTask数量为0
        job.setNumReduceTasks(0);

        // 8 提交
        boolean result = job.waitForCompletion(true);
        System.exit(result ? 0 : 1);
    }
}
