package com.dlh.generic.enumtest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2018/12/2 15:10
 */
public class EnumItem {

    private int value;

    private String desc;


    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * 泛型必须是枚举且实现EnumItem.IEnumItem接口
     * @param enums
     * @param val
     * @param <T>
     * @return
     */
    public static <T extends Enum<?> & EnumItem.IEnumItem> EnumItem getEnum(T[] enums, int val) {
        T[] var5 = enums;
        int var4 = enums.length;

        for(int var3 = 0; var3 < var4; ++var3) {
            T obj = var5[var3];
            if (obj.getValue() == val) {
                EnumItem item = new EnumItem();
                item.setValue((obj).getValue());
                item.setDesc((obj).getDesc());
                return item;
            }
        }

        return null;
    }

    @SafeVarargs
    public static <T extends Enum<?> & EnumItem.IEnumItem> List<EnumItem> getEnums(T[] enums, T... exclude) {
        T[] var5 = enums;
        int length = enums.length;

        List<EnumItem> list = new ArrayList<>();
        for(int i=0;i<length;i++){
            T obj = var5[i];
            boolean isExist = false;
            for(int j=0;j<exclude.length;j++){
                T t = exclude[j];
                if(obj.getValue() == t.getValue()){
                    isExist = true;
                }
            }

            if(isExist){
                continue;
            }

            EnumItem item = new EnumItem();
            item.setValue(obj.getValue());
            item.setDesc(obj.getDesc());
            list.add(item);
        }

        return list;
    }

    public interface IEnumItem{
        int getValue();

        String getDesc();
    }

    public static void main(String[] args) {
//        EnumItem enumItem = EnumItem.getEnum(CustomerType.values(),1);
//        System.out.println(enumItem.getValue() +":"+enumItem.getDesc());
        List<EnumItem> list = EnumItem.getEnums(CustomerType.values(),CustomerType.Resident_user);
        for(EnumItem item:list){
            System.out.println(item.getValue() +":"+item.getDesc());
        }

    }
}
