package algorithm;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/8/31 22:33
 * 二分查找非递归
 */
public class BinaySearchNoRecur {

    public static void main(String[] args) {
        int[] arr = {1,3,8,10,11,67,100};
        int index = binarySearch(arr,101);
        System.out.println("index = "+index);

    }

    public static int binarySearch(int[] arr,int target){

        int left = 0;
        int right = arr.length - 1;
        while (left <= right){
            int mid = (left+right)/2;
            if(arr[mid] == target){
                return mid;
            }else if(arr[mid] > target){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }

        return -1;
    }
}
