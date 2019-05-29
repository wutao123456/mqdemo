package hadoop.filter;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/5/29 22:55
 */
public class FilterReducer extends Reducer<Text, NullWritable, Text,NullWritable> {

    @Override
    protected void reduce(Text key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
        String line = key.toString();
        line += "\r\n";
        Text k = new Text();
        k.set(line);
        for(NullWritable nullWritable:values){
            context.write(k,NullWritable.get());
        }
    }
}
