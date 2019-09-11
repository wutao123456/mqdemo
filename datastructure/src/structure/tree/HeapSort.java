package structure.tree;

import java.util.Arrays;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/8/4 23:38
 * 堆排序
 * 大顶堆特性：父节点大于等于其子节点的值
 * 小顶堆特性：父节点小于等于其子节点的值
 */
public class HeapSort {

    public static void main(String[] args) {
        int[] arr = {4,6,8,5,9,12,45};
        sort(arr);

        System.out.println(Arrays.toString(arr));

    }

    public static void sort(int[] arr){

        /**
         * 构建大顶堆
         * 节点i大于其左子节点 arr[i] >= arr[2*i+1]
         * 节点i大于其右子节点 arr[i] >= arr[2*i+2]
         */
        for(int i=arr.length/2-1;i>=0;i--){
            //从第一个非叶子节点从下至上,从右至左调整结构
            adjustHeap(arr,i,arr.length);
        }

        System.out.println("大顶堆数组："+ Arrays.toString(arr));
        //2.调整堆结构+交换堆顶元素与末尾元素,将堆顶元素沉到数组末端
        for(int j = arr.length-1;j>0;j--){
            swap(arr,0,j);//将堆顶元素与末尾元素进行交换
            adjustHeap(arr,0,j);//重新对堆进行调整
        }
    }

    /**
     * 功能：完成以i对应的非叶子节点的树调整成大顶堆
     * @param arr 待调整的数组
     * @param i i表示非叶子节点在数组中的索引
     * @param length 表示对多少个元素继续调整,length是在逐渐的减少
     */
    public static void adjustHeap(int[] arr,int i,int length){
        int temp = arr[i];
        //k是i节点的左子节点
        for(int k = i*2+1;k<length;k = k*2+1){//从i的左子节点开始
            if(k+1<length && arr[k]<arr[k+1]){//如果左子节点小于右子节点,k指向右子节点
                k++;
            }

            if(arr[k]>temp){//如果子节点大于父节点,则将子节点赋值给父节点
                arr[i] = arr[k];
                i = k; //i指向k,继续循环比较
            }else{
                break;
            }
        }

        arr[i] = temp;
    }

    public static void swap(int[] arr,int a,int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}

