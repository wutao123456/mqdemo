package structure.sort;

import java.util.Arrays;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/7/29 23:17
 */
public class InsertSort {

    /**
     * 插入排序的基本操作就是将一个数据插入到已经排好序的有序数据中，从而得到一个新的、个数加一的有序数据。
     *
     * 第一个元素是有序队列，从第二个元素开始向有序队列中插入，插入完成后将第三个元素向有序队列中插入，依次进行，直到将最后一个元素插入完毕。
     *
     * 在将元素插入到有序队列中，要将这个元素与有序队列的元素依次比较，如果小于有序队列的某个元素，将其插入到该元素的前面，否则不做操作。依次比较完毕，没有比其大的，就将其放在有序队列的末尾。
     * @param args
     */
    public static void main(String[] args) {
        int[] arr = {1,-1,3,60,5,4,9,2};
        insertSort(arr);
        System.out.println(Arrays.toString(arr));

    }

    public static void insertSort(int[] arr){
        for(int i=1;i<arr.length;i++){
            int insertVal = arr[i];
            int insertIndex = i - 1;
            //将arr[i]依次与前面的元素比较,小于前面的元素,前面的元素后移,
            //再与前面一个元素比较,知道不小于那个元素,插入到该位置
            while (insertIndex >=0 && insertVal < arr[insertIndex]){
                arr[insertIndex+1] = arr[insertIndex];
                insertIndex--;
            }

            arr[insertIndex+1] = insertVal;
        }
    }
}
