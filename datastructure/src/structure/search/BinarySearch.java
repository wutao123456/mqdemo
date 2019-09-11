package structure.search;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/7/30 23:17
 * 二分查找
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        int index = binartSearch(arr,0,arr.length-1,88);
        System.out.println("findIndex= "+ index);
    }



    public static int binartSearch(int[] arr,int left,int right,int findVal){

        //防止栈溢出
        if(left > right){
            return -1;
        }

        int mid = (left + right)/2;
        int midVal = arr[mid];

        if(findVal > midVal){
            return binartSearch(arr,mid+1,right,findVal);
        }else if(findVal < midVal){
            return binartSearch(arr,left,mid-1,findVal);
        }else{
            return mid;
        }

    }

}
