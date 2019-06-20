package hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.mapreduce.TableReducer;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 简单的HBase MR操作.
 * 通常使用 MR 操作进行建立索引.
 *
 * */
public class HBaseMR {
    /**
     * HBase配置.
     *
     * */
    static Connection connection = null;
    static Configuration config = null;
    static{
        config = HBaseConfiguration.create();

//        config.set("hbase.zookeeper.quorum", "localhost:2181,localhost:2182,localhost:2183");
    }

    /**
     * 表信息.
     * */
    public static final String tableName = "word";
    public static final String colFamily = "content";
    public static final String col = "info";
    public static final String resultTableName = "stat";


    /**
     * 初始化表结构即其数据.
     * *
     */
    public static void initTable () throws IOException {
        connection = ConnectionFactory.createConnection(config);
        Admin admin = null;
        try{
            admin = connection.getAdmin();
            // 如果表存在, 则删除表(Demo写法)
            if(admin.tableExists(TableName.valueOf(tableName))||admin.tableExists(TableName.valueOf(resultTableName))){
                System.out.println("Table is exists.");
                admin.disableTable(TableName.valueOf(tableName));
                admin.deleteTable(TableName.valueOf(tableName));
                admin.disableTable(TableName.valueOf(resultTableName));
                admin.deleteTable(TableName.valueOf(resultTableName));
            }
            // 创建表
            // 创建数据表
            TableDescriptorBuilder builder = TableDescriptorBuilder.newBuilder(TableName.valueOf(tableName));
            ColumnFamilyDescriptor columnFamily = ColumnFamilyDescriptorBuilder.of(colFamily);
            builder.setColumnFamily(columnFamily);
            TableDescriptor tDescriptor = builder.build();
            admin.createTable(tDescriptor);
            // 创建结果表
            TableDescriptorBuilder builder1 = TableDescriptorBuilder.newBuilder(TableName.valueOf(resultTableName));
            ColumnFamilyDescriptor columnFamily1 = ColumnFamilyDescriptorBuilder.of(colFamily);
            builder1.setColumnFamily(columnFamily1);
            TableDescriptor tDescriptor1 = builder1.build();
            admin.createTable(tDescriptor1);

            // 插入数据
            admin.getConnection();
            Table table = connection.getTable(TableName.valueOf(tableName));
            // 编写写入的数据
            List<Put> listPut = new ArrayList<Put>();
            Put put1 = new Put(Bytes.toBytes("1"));
            put1.addColumn(colFamily.getBytes(), col.getBytes(), Bytes.toBytes("HELlO Kitty123"));
            listPut.add(put1);
            Put put2 = new Put(Bytes.toBytes("2"));
            put2.addColumn(colFamily.getBytes(), col.getBytes(), Bytes.toBytes("Mark 10029."));
            listPut.add(put2);
            Put put3 = new Put(Bytes.toBytes("3"));
            put3.addColumn(colFamily.getBytes(), col.getBytes(), Bytes.toBytes("Demo Kitty123"));
            listPut.add(put3);

            table.put(listPut);
            listPut.clear();
            System.out.println("Init Table MethodOK.");
        }catch(Exception e){

        }
    }

    public static class HBaseMRMapper extends TableMapper<Text, IntWritable> {

        // 输入为一行的<key:rowKey, value> 输出为<result>
        @Override
        protected void map(ImmutableBytesWritable key, Result value,
                           org.apache.hadoop.mapreduce.Mapper<ImmutableBytesWritable, Result, Text, IntWritable>.Context context)
                throws IOException, InterruptedException {
            // 读取一行数据
            String words = Bytes.toString(value.getValue(Bytes.toBytes(colFamily), Bytes.toBytes(col)));
            String wordSplit[] = words.split(" ");
            // 循环输出
            for(String word:wordSplit){
                context.write(new Text(word), new IntWritable(1));
            }
        }
    }

    public static class HBaseMRReducer extends TableReducer<Text, IntWritable, ImmutableBytesWritable>{

        @Override
        // 统计次数
        protected void reduce(Text key, Iterable<IntWritable> values,
                              org.apache.hadoop.mapreduce.Reducer<Text, IntWritable, ImmutableBytesWritable, Mutation>.Context context)
                throws IOException, InterruptedException {
            int sum = 0;
            for(IntWritable num:values){
                sum+=num.get();
            }
            Put put = new Put(Bytes.toBytes(key.toString()));
            put.addColumn(Bytes.toBytes(colFamily),Bytes.toBytes(col),Bytes.toBytes(String.valueOf(sum)));
            // 写到hbase
            context.write(new ImmutableBytesWritable(Bytes.toBytes(key.toString())), put);
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
//		config.set("fs.defaultFS", "hdfs://localhost:9000/");
//		config.set("fs.defaultFS", "hdfs://localhost:9000/");

//		config.set("mapreduce.framework.name", "yarn");
//        config.set("mapreduce.framework.name", "local");
        // 初始化表- 注意线程安全问题.
        initTable();

        System.out.println("Init Table OK.");

        Job job = Job.getInstance(config,"HBaseMR");
        job.setJarByClass(HBaseMR.class);

        // 创建Scan
        Scan scan = new Scan();
        scan.addColumn(Bytes.toBytes(colFamily), Bytes.toBytes(col));

        // 创建查询Mapper
        TableMapReduceUtil.initTableMapperJob(tableName, scan, HBaseMRMapper.class, Text.class, IntWritable.class, job);

        // 创建写入HBase的Reducer

        TableMapReduceUtil.initTableReducerJob(resultTableName, HBaseMRReducer.class, job);

        System.out.println(job.waitForCompletion(true)?0:1);

    }

}
