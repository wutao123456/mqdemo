package hadoop.order;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/5/26 22:57
 */
public class OrderReducer extends Reducer<OrderBean, NullWritable,OrderBean,NullWritable> {

    @Override
    protected void reduce(OrderBean key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
        int i = 0;
        //输出top2
        for(NullWritable nullWritable:values){
            if(i == 2){
                break;
            }
            context.write(key,nullWritable.get());
            i++;
        }
    }
}
