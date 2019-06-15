#FLUME


**这是加粗的文字**

*test*

~~删除线~~

分割线
---

分割线2
***

超链接
[百度](https://baidu.com)

####无序列表
- 列表内容
+ 列表内容
* 列表内容

- 一级无序列表
  - 二级无序列表内容
  - 二级无序列表内容
    - 二级无序列表内容

####有序列表
1. 列表内容
2. 列表内容
3. 列表内容
4. 一级有序列表内容
   1. 二级有序列表内容
   2. 二级有序列表内容
      1. 三级有序列表内容

####表格
姓名|技能|排行
---:|:---:|---
刘备|哭|大哥大
关羽|打|二哥是
张飞|骂|三弟

转义字符\.

`this is code demo`

```
public static void main(String[] args) throws Exception{
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf , "table");
        job.setJarByClass(TableDriver.class);
        job.setMapperClass(TableMapper.class);
        job.setReducerClass(TableReducer.class);
        //设置map输出key和value
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(TableBean.class);
        //设置最终输出key和value
        job.setOutputKeyClass(TableBean.class);
        job.setOutputValueClass(NullWritable.class);
        //设置reduce端分组排序

        //设置reduce端压缩
        FileOutputFormat.setCompressOutput(job,true);
        FileOutputFormat.setOutputCompressorClass(job, BZip2Codec.class);
        //设置输入输出路径
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }

```

>引用


[手动链接](http://baidu.com)

<http://baidu.com>