package com.dlh.un.ds;

public class DataSourceContextHolder {

    private static final ThreadLocal<String> contextholder = new ThreadLocal<String>();

    /**
     * 设置数据源
     * @param dataSourceType
     */
    public static void setDataSourceType(String dataSourceType){
        contextholder.set(dataSourceType);
    }

    /**
     * 获取数据源
     * @return
     */
    public static String getDataSourceType(){
        return contextholder.get();
    }

    /**
     * 清除数据源
     */
    public static void clearDataSourceType(){
        contextholder.remove();
    }
}
