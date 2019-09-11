package algorithm;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/8/31 22:59
 * 分治算法(汉诺塔)
 */
public class HanoiTower {

    public static void main(String[] args) {

    }

    public static void hanoiTower(int num,char a,char b,char c){
        if(num == 1){
            System.out.println("第一个盘从" + a +"->" + c);
        }else{
            //先把最上面所有盘 A->B
            hanoiTower(num - 1,a,c,b);

            //把最下面的盘 A->C
            System.out.println("第" + num + "个盘从" + a +"->" + c);

            //把B塔所有盘从 B->C
            hanoiTower(num-1,b,a,c);
        }
    }
}
