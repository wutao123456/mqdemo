package algorithm.kmp;

import java.util.Arrays;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/9/1 16:10
 * KMP算法
 */
public class KMPAlgorithm {

    public static void main(String[] args) {

        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        int[] next = kmpNext(str2);
        System.out.println(Arrays.toString(next));


        int index = kmpSearch(str1,str2,next);
        System.out.println("index = " +index);
    }

    public static int kmpSearch(String str1,String str2,int[] next){
        for(int i = 1,j=0;i<str1.length();i++){

            while (j>0 && str1.charAt(i) != str2.charAt(j)){
                j = next[j - 1];
            }
            if(str1.charAt(i) == str2.charAt(j)){
                j++;
            }
            if(j == str2.length()){
                return i-j+1;
            }
        }
        return -1;
    }

    /**
     * 部分匹配表(寻找前缀后缀最长公共元素长度)
     * @param dest
     * @return
     */
    public static int[] kmpNext(String dest){
        int[] next = new int[dest.length()];
        next[0] = 0;
        for(int i = 1,j=0;i<dest.length();i++){
            //KMP算法核心
            while (j > 0 && dest.charAt(i) != dest.charAt(j)){
                j = next[j - 1];
            }
            if(dest.charAt(i) == dest.charAt(j)){
                j++;
            }
            next[i] = j;
        }

        return next;
    }
}
