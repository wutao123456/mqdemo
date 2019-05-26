package hadoop;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/5/26 21:44
 */
public class WordcountCombiner extends Reducer<Text,IntWritable,Text,IntWritable> {

    IntWritable result = new IntWritable();

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int sum = 0;
        for(IntWritable value:values){
            sum += value.get();
        }

        result.set(sum);
        context.write(key,result);
    }
}
