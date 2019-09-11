package structure.sort;

import java.util.Arrays;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/7/29 23:04
 * 选择排序
 */
public class SelectSort {

    public static void main(String[] args) {

        int[] arr = {1,-1,3,60,5,4,9,2};
        selectSort(arr);
        System.out.println(Arrays.toString(arr));

    }

    public static void selectSort(int[] arr){
        for(int i=0;i<arr.length-1;i++){
            int min = arr[i];
            int minIndex = i;
            for(int j = i+1;j<arr.length;j++){
                if(min > arr[j]){
                    min = arr[j];
                    minIndex = j;
                }
            }

            if(minIndex != i){
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
    }
}
