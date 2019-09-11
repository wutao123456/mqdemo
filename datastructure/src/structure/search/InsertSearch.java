package structure.search;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/8/3 23:25
 */
public class InsertSearch {

    /**
     * 对于数据量较大,关键字分布比较均匀的查找表来说,采用插值查找,速度较快
     * @param args
     */
    public static void main(String[] args) {

        int[] arr = new int[100];
        for(int i=0;i<100;i++){
            arr[i] = i+1;
        }


        int index = insertSearch(arr,0,arr.length - 1,100);
        System.out.println("index = "+index);

    }

    public static int insertSearch(int[] arr,int left,int right,int findVal){

        System.out.println("插值查找");
        if(left > right || findVal < arr[left] || findVal > arr[right]){
            return -1;
        }

        //主要是这个公式
        int mid = left + (right - left)*(findVal - arr[left])/(arr[right] - arr[left]);
        int midVal = arr[mid];
        if(findVal > midVal){
            return insertSearch(arr,mid + 1,right,findVal);
        }else if(findVal < midVal){
            return insertSearch(arr,left,mid - 1,findVal);
        }else{
            return mid;
        }
    }
}
