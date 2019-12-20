package com.dlh.runtime;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Properties;

public class RunTimeTest {

    public static void main(String[] args) throws Exception{
        System.out.println("可用内存"+Runtime.getRuntime().freeMemory()/1024/1024+" MB");
        System.out.println("总内存"+Runtime.getRuntime().totalMemory()/1024/1024+" MB");
        System.out.println("最大内存"+Runtime.getRuntime().maxMemory()/1024/1024+" MB");
        System.err.println("你的电脑核心数量为"+Runtime.getRuntime().availableProcessors());

        //打开系统计算器
//        Runtime.getRuntime().exec("calc");


        Process process = Runtime.getRuntime().exec("javac -version");
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = null;
        while ((line = br.readLine()) != null)
            System.out.println(line);
        process.waitFor();//阻塞到外部命令执行结束,然后返回外部命令执行结果
        System.out.println("Process exitValue: " + process.exitValue());


        System.out.println(System.getProperty("os.name"));

        //获取系统环境变量
        System.out.println(">>>>>>>>>>>>> system env");
        Map<String,String> env = System.getenv();
        for(Map.Entry<String,String> entry:env.entrySet()){
            System.out.println(""+entry.getKey()+" : "+entry.getValue());
        }

        //获取系统属性
        Properties properties = System.getProperties();
        for(String key :properties.stringPropertyNames()){
            System.out.println(key+" : "+ properties.getProperty(key));
        }

        //获取系统核心数
        Runtime.getRuntime().availableProcessors();

    }
}
