package structure;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/7/22 0:00
 */
public class SpareArray {

    public static void main(String[] args) {
        int chessArr[][] = new int[11][11];
        chessArr[1][2] = 1;
        chessArr[2][3] = 2;
        for(int[] row:chessArr){
            for(int i:row){
                System.out.printf("%d\t",i);
            }
            System.out.println();
        }

        //转换稀疏数组
        int sum = 0;
        for(int[] row:chessArr){
            for(int i:row){
                if(i != 0){
                    sum++;
                }
            }
        }

        int count = 0;
        int spareArr[][] = new int[sum+1][3];
        spareArr[0][0] = 11;
        spareArr[0][1] = 11;
        spareArr[0][2] = sum;
        for(int i=0;i<chessArr.length;i++){
            int[] row = chessArr[i];
            for(int j=0;j<row.length;j++){
                if(row[j] != 0){
                    count ++;
                    spareArr[count][0] = i;
                    spareArr[count][1] = j;
                    spareArr[count][2] = row[j];
                }
            }
        }

        //输出稀疏数组
        System.out.println("----------稀疏数组--------------");
        for(int i=0;i<spareArr.length;i++){
            System.out.printf("%d\t%d\t%d\t",spareArr[i][0],spareArr[i][1],spareArr[i][2]);
            System.out.println();
        }



        int chessArr2[][] = new int[spareArr[0][0]][spareArr[0][1]];
        for(int i=1;i<spareArr.length;i++){
            int[] row = spareArr[i];
            chessArr2[row[0]][row[1]] = row[2];
        }

        System.out.println("----还原数组");
        for(int[] row:chessArr2){
            for(int i:row){
                System.out.printf("%d\t",i);
            }
            System.out.println();
        }

    }
}
