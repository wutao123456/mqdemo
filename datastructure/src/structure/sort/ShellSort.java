package structure.sort;

import java.util.Arrays;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/7/29 23:52
 * 希尔排序
 */
public class ShellSort {

    public static void main(String[] args) {
        int[] arr = {1,-1,3,60,5,4,9,2};
        //1,-1,3,2,5,4,9,60
        //-1,1,2,3,4,5,9,60
        shellSort2(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 交换法
     * @param arr
     */
    public static void shellSort(int[] arr){
        int temp = 0;
        for(int gap = arr.length/2;gap > 0;gap/=2){
            for(int i = gap;i<arr.length;i++){
                for(int j = i-gap;j>=0;j-=gap){
                    if(arr[j]>arr[j+gap]){
                        temp = arr[j];
                        arr[j] = arr[j+gap];
                        arr[j+gap] = temp;
                    }
                }
            }
        }
    }

    /**
     * 移位法
     * @param arr
     */
    public static void shellSort2(int[] arr){
        for(int gap = arr.length/2;gap > 0;gap/=2){
            for(int i=gap;i<arr.length;i++){
                int j=i;
                int temp = arr[j];
                if(arr[j] < arr[j-gap]){
                    while (j-gap >=0 && temp <arr[j-gap]){
                        arr[j] = arr[j-gap];
                        j -= gap;
                    }

                    arr[j] = temp;
                }
            }
        }
    }
}
