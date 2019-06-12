package hadoop.index;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;


/**
 * @author wutao
 * @date 2019/6/12
 */
public class TwoIndexReducer extends Reducer<Text, Text,Text,Text> {

    Text v = new Text();

    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        StringBuffer sb = new StringBuffer();
        for(Text text:values){
            sb.append(text.toString().replace("\t","-->")+"\t");
        }
        v.set(sb.toString());
        context.write(key,v);
    }
}
