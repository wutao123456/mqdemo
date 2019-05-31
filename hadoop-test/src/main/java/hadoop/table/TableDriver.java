package hadoop.table;

import hadoop.filter.FilterDriver;
import hadoop.filter.FilterFileOutputFormat;
import hadoop.filter.FilterMapper;
import hadoop.filter.FilterReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/5/30 23:07
 */
public class TableDriver {

    public static void main(String[] args) throws Exception{
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf , "table");
        job.setJarByClass(TableDriver.class);
        job.setMapperClass(TableMapper.class);
        job.setReducerClass(TableReducer.class);
        //设置map输出key和value
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(TableBean.class);
        //设置最终输出key和value
        job.setOutputKeyClass(TableBean.class);
        job.setOutputValueClass(NullWritable.class);
        //设置reduce端分组排序
        //设置输入输出路径
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
