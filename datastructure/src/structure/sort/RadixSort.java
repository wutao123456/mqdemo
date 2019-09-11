package structure.sort;

import java.util.Arrays;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/7/30 23:02
 * 基数排序(耗内存) 稳定排序
 * 注意:基数排序不支持负数或者对下面代码进行改进
 */
public class RadixSort {

    public static void main(String[] args) {
        int[] arr = {53,3,542,748,14,214};
        radixSort(arr);
    }

    public static void radixSort(int[] arr){

        //获取最大数,确定需要遍历几轮
        int max = arr[0];
        for(int l=1;l<arr.length;l++){
            if(arr[l]>max){
                max = arr[l];
            }
        }

        int maxLength = (max +"").length();

        int[][] bucket = new int[10][arr.length];

        //给每个桶计数
        int[] bucketElementCounts = new int[10];
        for(int m=0,n=1;m<maxLength;m++,n*=10) {

            //针对每个元素的对应位进行排序处理,第一次是个位,第二次是十位...
            for (int i = 0; i < arr.length; i++) {
                int digitOfElement = arr[i] /n % 10;
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[i];
                bucketElementCounts[digitOfElement]++;
            }

            int index = 0;
            //遍历每一个桶,取出桶中数据
            for (int k = 0; k < bucketElementCounts.length; k++) {
                if (bucketElementCounts[k] != 0) {
                    for (int j = 0; j < bucketElementCounts[k]; j++) {
                        arr[index++] = bucket[k][j];
                    }
                }

                //重置计数桶,以便下一轮使用
                bucketElementCounts[k] = 0;
            }

        }

        System.out.println(Arrays.toString(arr));
    }
}
