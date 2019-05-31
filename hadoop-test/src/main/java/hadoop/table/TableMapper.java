package hadoop.table;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/5/30 22:52
 */
public class TableMapper extends Mapper<LongWritable, Text,Text,TableBean> {

    String name;

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        FileSplit split = (FileSplit) context.getInputSplit();
        name = split.getPath().getName();
    }


    TableBean tableBean = new TableBean();

    Text k = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String line = value.toString();
        if(name.startsWith("order")){
            String[] fields = line.split(" ");
            tableBean.setId(fields[0]);
            tableBean.setPid(fields[1]);
            tableBean.setAmount(Integer.parseInt(fields[2]));
            tableBean.setPname("");
            tableBean.setFlag("order");

            k.set(fields[1]);
        }else{
            String[] fields = line.split(" ");
            tableBean.setId("");
            tableBean.setPid(fields[0]);
            tableBean.setAmount(0);
            tableBean.setPname(fields[1]);
            tableBean.setFlag("pd");

            k.set(fields[0]);
        }

        context.write(k,tableBean);
    }
}
