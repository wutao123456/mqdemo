package hadoop.cache;

import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hbase.thirdparty.io.netty.util.internal.StringUtil;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/6/2 22:45
 */
public class DistributedCacheMapper extends Mapper<LongWritable, Text,Text, NullWritable> {

    Map<String,String> pMap = new HashMap<String, String>();

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        URI[] cacheFiles = context.getCacheFiles();
        String path = cacheFiles[0].getPath().toString();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(path),"UTF-8"));
        String line;
        while (!StringUtil.isNullOrEmpty(line = bufferedReader.readLine())){
            String[] fields = line.split(" ");
            pMap.put(fields[0],fields[1]);
        }

        IOUtils.closeStream(bufferedReader);
    }

    Text k = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] fields = line.split(" ");
        String pid = fields[1];
        String pname = pMap.get(pid);
        line = line + pname;
        k.set(line);
        context.write(k,NullWritable.get());
    }
}
