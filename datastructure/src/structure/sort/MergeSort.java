package structure.sort;

import java.util.Arrays;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/7/30 22:33
 * 归并排序
 */
public class MergeSort {

    /**
     * 分治策略
     * @param args
     */
    public static void main(String[] args) {
        int[] arr = {8,4,5,7,1,3,6,2};
        int[] temp = new int[arr.length];
        mergeSort(arr,0,arr.length-1,temp);
        System.out.println(Arrays.toString(arr));

    }

    public static void mergeSort(int[] arr,int left,int right,int[] temp){
        int mid = (left +right)/2;
        if(left < right){
            mergeSort(arr,left,mid,temp);

            mergeSort(arr,mid + 1,right,temp);

            merge(arr,left,mid,right,temp);
        }
    }

    public static void merge(int[] arr,int left,int mid,int right,int[] temp){
        int i = left;//左边序列的初始索引
        int j = mid+1;//右边序列的初始索引
        int t = 0;

        //先把左边两边的序列填充到temp数组中,直到有一边处理完毕
        while (i<=mid && j<=right){
            if(arr[i] <= arr[j]){
                temp[t] = arr[i];
                i++;
                t++;
            }else{
                temp[t] = arr[j];
                j++;
                t++;
            }
        }

        //左边有序队列还有剩余元素,全部填充到temp数组
        while (i<=mid){
            temp[t] = arr[i];
            t++;
            i++;
        }

        //右边有序队列还有剩余元素,全部填充到temp数组
        while (j<= right){
            temp[t] = arr[j];
            t++;
            j++;
        }

        //上面的操作已经排好序放入temp数组中
//        System.out.println(Arrays.toString(temp));

        t = 0;
        int tempLeft = left;
        while (tempLeft <= right){
            arr[tempLeft] = temp[t];
            t++;
            tempLeft ++;
        }
    }
}
