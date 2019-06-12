package hadoop.index;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

/**
 * @author wutao
 * @date 2019/6/12
 */
public class OneIndexMapper extends Mapper<LongWritable, Text,Text, IntWritable> {

    private String name;

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        FileSplit fileSplit = (FileSplit) context.getInputSplit();
        name = fileSplit.getPath().getName();
    }


    Text k = new Text();
    IntWritable v = new IntWritable(1);

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] fields = line.split(" ");
       for(String word:fields){
            k.set(word + "--" + name);
            context.write(k,v);
       }
    }


}
