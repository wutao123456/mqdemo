package hadoop.filter;

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

import java.io.IOException;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/5/29 22:58
 */
public class FilterRecordWriter extends RecordWriter<Text, NullWritable> {
    FSDataOutputStream fosbaidu;
    FSDataOutputStream fosOther;

    public FilterRecordWriter(TaskAttemptContext job) {
        try{
            FileSystem fs = FileSystem.get(job.getConfiguration());
            fosbaidu = fs.create(new Path("e:/baidu.log"));
            fosOther = fs.create(new Path("e:/other.log"));
        }catch (IOException e){

        }
    }

    public void write(Text text, NullWritable nullWritable) throws IOException, InterruptedException {
        //对输出内容进行过滤
        if(text.toString().contains("baidu")){
            fosbaidu.write(text.toString().getBytes());
        }else{
            fosOther.write(text.toString().getBytes());
        }
    }

    public void close(TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        IOUtils.closeStream(fosbaidu);
        IOUtils.closeStream(fosOther);
    }
}
