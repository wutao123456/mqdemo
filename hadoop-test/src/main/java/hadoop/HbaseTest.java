package hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * @author wutao
 * @date 2019/5/8
 */
public class HbaseTest {

    public Connection connection;
    //用hbaseconfiguration初始化配置信息时会自动加载当前应用classpath下的hbase-site.xml
    public static Configuration configuration = HBaseConfiguration.create();
    public Table table;
    public Admin admin;

    public HbaseTest() throws Exception{

//        Configuration HBASE_CONFIG = new Configuration();
//        HBASE_CONFIG.set("hbase.zookeeper.quorum", "hadoopmaster");
//        HBASE_CONFIG.set("hbase.zookeeper.property.clientPort","2181");
//        HBaseConfiguration cfg = new HBaseConfiguration(HBASE_CONFIG);
        //对connection初始化
        connection = ConnectionFactory.createConnection(configuration);

        admin = connection.getAdmin();
        System.out.println("连接成功");
    }

    //创建表
    public void createTable(String tablename,String... cf1) throws Exception{
        //获取admin对象
        Admin admin = connection.getAdmin();
        //创建tablename对象描述表的名称信息
        TableName tname = TableName.valueOf(tablename);//customer
        //创建TableDescriptor对象，描述表信息
        TableDescriptorBuilder builder = TableDescriptorBuilder.newBuilder(TableName.valueOf(tablename));
        //判断是否表已存在
        if(admin.tableExists(tname)){
            admin.disableTable(tname);
            admin.deleteTable(tname);
        }
        //添加表列簇信息
        for(String cf:cf1){
            ColumnFamilyDescriptor family = ColumnFamilyDescriptorBuilder.of(cf);
            builder.setColumnFamily(family);
        }
        TableDescriptor tDescriptor = builder.build();
        //调用admin的createtable方法创建表
        admin.createTable(tDescriptor);
        System.out.println("表"+tablename+"创建成功");
    }

    //删除表
    public void deleteTable(String tablename) throws Exception{
        Admin admin = connection.getAdmin();
        TableName tName = TableName.valueOf(tablename);
        if(admin.tableExists(tName)){
            admin.disableTable(tName);
            admin.deleteTable(tName);
            System.out.println("删除表"+tablename+"成功！");
        }else{
            System.out.println("表"+tablename+"不存在。");
        }
    }

    public void putData() throws Exception{
        TableName tableName = TableName.valueOf("customer");
        Table table = connection.getTable(tableName);
        Random random = new Random();
        List<Put> batPut = new ArrayList<Put>();
        for(int i=0;i<10;i++){
            //构建put的参数是rowkey   rowkey_i (Bytes工具类，各种java基础数据类型和字节数组之间的相互转换)
            Put put = new Put(Bytes.toBytes("rowkey_"+i));
            put.addColumn(Bytes.toBytes("i"), Bytes.toBytes("username"), Bytes.toBytes("un_"+i));
            put.addColumn(Bytes.toBytes("i"), Bytes.toBytes("age"), Bytes.toBytes(random.nextInt(50)+1));
            put.addColumn(Bytes.toBytes("i"), Bytes.toBytes("birthday"), Bytes.toBytes("20170"+i+"01"));
            put.addColumn(Bytes.toBytes("j"), Bytes.toBytes("phone"), Bytes.toBytes("电话_"+i));
            put.addColumn(Bytes.toBytes("j"), Bytes.toBytes("email"), Bytes.toBytes("email_"+i));
            //单记录put
//            table.put(put);
            batPut.add(put);
        }
        table.put(batPut);
        System.out.println("表插入数据成功！");
    }

    public void getData() throws Exception{
        TableName tableName = TableName.valueOf("customer");
        table = connection.getTable(tableName);
        //构建get对象
        List<Get> gets = new ArrayList<Get>();
        for(int i=0;i<10;i++){
            Get get = new Get(Bytes.toBytes("rowkey_"+i));
            get.addFamily(Bytes.toBytes("i"));
            get.addFamily(Bytes.toBytes("j"));
            gets.add(get);
        }

        Get get = new Get(Bytes.toBytes("rowkey_0"));
        get.addFamily(Bytes.toBytes("j"));
        get.addColumn(Bytes.toBytes("j"),Bytes.toBytes("phone"));
        Result result = table.get(get);
        CellScanner cellScanner = result.cellScanner();
        while(cellScanner.advance()){
            Cell cell = cellScanner.current();
            //从单元格cell中把数据获取并输出
            //使用 CellUtil工具类，从cell中把数据获取出来
            String famliy = Bytes.toString(CellUtil.cloneFamily(cell));
            String qualify = Bytes.toString(CellUtil.cloneQualifier(cell));
            String rowkey = Bytes.toString(CellUtil.cloneRow(cell));
            String value = Bytes.toString(CellUtil.cloneValue(cell));
            System.out.println("rowkey:"+rowkey+",columnfamily:"+famliy+",qualify:"+qualify+",value:"+value);
        }



//        Result[] results = table.get(gets);
//        for(Result result:results){
//            //使用cell获取result里面的数据
//            CellScanner cellScanner = result.cellScanner();
//            while(cellScanner.advance()){
//                Cell cell = cellScanner.current();
//                //从单元格cell中把数据获取并输出
//                //使用 CellUtil工具类，从cell中把数据获取出来
//                String famliy = Bytes.toString(CellUtil.cloneFamily(cell));
//                String qualify = Bytes.toString(CellUtil.cloneQualifier(cell));
//                String rowkey = Bytes.toString(CellUtil.cloneRow(cell));
//                String value = Bytes.toString(CellUtil.cloneValue(cell));
//                System.out.println("rowkey:"+rowkey+",columnfamily:"+famliy+",qualify:"+qualify+",value:"+value);
//            }
//        }
    }

    public void scanFilter() throws Exception{
        TableName tableName = TableName.valueOf("customer");
        table = connection.getTable(tableName);
        Scan scan = new Scan();
        SingleColumnValueFilter filter = new SingleColumnValueFilter(
                Bytes.toBytes("i"),
                Bytes.toBytes("birthday"),
                CompareOperator.EQUAL,
                Bytes.toBytes("20170001")
        );

        //列族过滤器
        FamilyFilter familyFilter = new FamilyFilter(CompareOperator.EQUAL,new BinaryComparator("j".getBytes()));
        //列名过滤器
        QualifierFilter qualifierFilter = new QualifierFilter(CompareOperator.EQUAL,new BinaryComparator("email".getBytes()));
        //列前缀过滤器
        ColumnPrefixFilter columnPrefixFilter = new ColumnPrefixFilter("a".getBytes());
        byte [][] prefixs = new byte[][]{Bytes.toBytes("a"),Bytes.toBytes("u")};
        //多个列前缀过滤器
        MultipleColumnPrefixFilter multipleColumnPrefixFilter = new MultipleColumnPrefixFilter(prefixs);
        //行键过滤器
        RowFilter rowFilter = new RowFilter(CompareOperator.EQUAL,new BinaryComparator("rowkey_0".getBytes()));
        //第一个键过滤器
        FirstKeyOnlyFilter firstKeyOnlyFilter = new FirstKeyOnlyFilter();
        //键过滤器
        KeyOnlyFilter keyOnlyFilter = new KeyOnlyFilter();
        //列数过滤器
        ColumnCountGetFilter columnCountGetFilter = new ColumnCountGetFilter(3);
        //列值过滤器
        ValueFilter valueFilter = new ValueFilter(CompareOperator.EQUAL,new BinaryComparator("email_8".getBytes()));
        SkipFilter skipFilter = new SkipFilter(valueFilter);
        //分页过滤器
        PageFilter pageFilter = new PageFilter(3);
        //过滤器组合(AND 或 OR)
        FilterList filterList = new FilterList(FilterList.Operator.MUST_PASS_ALL);
        filterList.addFilter(rowFilter);
        filterList.addFilter(qualifierFilter);
        scan.setFilter(pageFilter);
//        scan.setFilter(filterList);
        ResultScanner scanner = table.getScanner(scan);

        Iterator<Result> iterator = scanner.iterator();
        while (iterator.hasNext()){
            Result result = iterator.next();
            CellScanner cellScanner = result.cellScanner();
            while(cellScanner.advance()){
                Cell cell = cellScanner.current();
                //从单元格cell中把数据获取并输出
                //使用 CellUtil工具类，从cell中把数据获取出来
                String famliy = Bytes.toString(CellUtil.cloneFamily(cell));
                String qualify = Bytes.toString(CellUtil.cloneQualifier(cell));
                String rowkey = Bytes.toString(CellUtil.cloneRow(cell));
                String value = Bytes.toString(CellUtil.cloneValue(cell));
                System.out.println("rowkey:"+rowkey+",columnfamily:"+famliy+",qualify:"+qualify+",value:"+value);
            }
        }
    }
    //关闭连接
    public void cleanUp() throws Exception{
        connection.close();
    }

    public static void main(String[] args)throws Exception{
        HbaseTest hbaseTest = new HbaseTest();
//        hbaseTest.createTable("test", "c1","c2");
//        hbaseTest.putData();
//        hbaseTest.getData();
        hbaseTest.scanFilter();
    }
}
