package structure.sort;

import java.util.Arrays;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/7/30 22:04
 * 快速排序算法
 */
public class QuickSort {

    /**
     * https://blog.csdn.net/morewindows/article/details/6684558
     * @param args
     */
    public static void main(String[] args) {
        int[] arr = {1, 4, 5, 67, 2, 7, 8, 6, 9, 44};
        quickSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));

    }

    public static void quickSort(int[] arr,int left,int right){

        int l = left;
        int r = right;

        //获取中间值
        //比中间值小的全部移到左边,比中间值大的全部移到右边
        int pivot = arr[ (left + right)/2 ];

        //该循环将支点排好序
        while (l < r){
            while (arr[l] < pivot){
                l++;
            }

            while (arr[r] > pivot){
                r--;
            }

            if(l >= r){
                break;
            }

            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

//            if(arr[l] == pivot){
//                r--;
//            }
//
//            if(arr[r] == pivot){
//                l++;
//            }

        }

        if(r == l){
            l++;
            r--;
        }

        if(left < r){
            quickSort(arr,left,r);
        }

        if(l < right){
            quickSort(arr,l,right);
        }
    }
}
