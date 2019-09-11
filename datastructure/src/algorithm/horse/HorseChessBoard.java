package algorithm.horse;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/9/2 21:22
 * 骑士周游算法
 * https://blog.csdn.net/recall_tomorrow/article/details/70970293
 */
public class HorseChessBoard {

    private static int X;//棋盘的列数

    private static int Y;//棋盘的行数

    public static boolean finished = false;

    public static boolean[] visited;

    public static void main(String[] args) {
        System.out.println("骑士周游开始");
        X = 6;
        Y = 6;
        int row = 5;
        int column = 1;
        int[][] chessboard = new int[X][Y];
        visited = new boolean[ X * Y ];
        long start = System.currentTimeMillis();
        traversalChessBoard(chessboard,row - 1,column - 1,1);
        long end = System.currentTimeMillis();
        System.out.println("耗时"+(end - start));
        for(int[] rows:chessboard){
            for(int step:rows){
                System.out.print(step + "\t");
            }
            System.out.println();
        }




    }

    public static void traversalChessBoard(int[][] chessboard,int row,int column,int step){
        //记录该点的步数
        chessboard[row][column] = step;
        //设置该点已经被访问
        visited[row * X +column] = true;
        ArrayList<Point> ps = next(new Point(column,row));
        sort(ps);
        while (!ps.isEmpty()){
            Point p = ps.remove(0);
            if(!visited[p.y * X +p.x]){
                traversalChessBoard(chessboard,p.y,p.x,step+1);
            }
        }

        //总步数未达预期,重新来过
        if(step < X * Y && !finished){
            chessboard[row][column] = 0;
            visited[row * X +column] = false;
        }else{
            finished = true;
        }
    }

    /**
     * 获取该点下一步可以访问的坐标点
     * @param curPoint
     * @return
     */
    public static ArrayList<Point> next(Point curPoint){
        ArrayList<Point> ps = new ArrayList<>();
        Point p1 = new Point(curPoint);
        if((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y - 1 ) >= 0){
            ps.add(new Point(p1));
        }

        if((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y - 2 ) >= 0){
            ps.add(new Point(p1));
        }

        if((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1 ) < Y){
            ps.add(new Point(p1));
        }

        if((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2 ) < Y){
            ps.add(new Point(p1));
        }

        if((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y + 1 ) < Y){
            ps.add(new Point(p1));
        }

        if((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y - 1 ) >= 0){
            ps.add(new Point(p1));
        }

        if((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y - 2 ) >= 0){
            ps.add(new Point(p1));
        }

        if((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y + 2 ) < Y){
            ps.add(new Point(p1));
        }

        return ps;

    }

    //贪心算法优化
    public static void sort(ArrayList<Point> ps){
        ps.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                int count1 = next(o1).size();
                int count2 = next(o2).size();
                if(count1 <count2){
                    return -1;
                }else if(count1 > count2){
                    return 1;
                }else{
                    return 0;
                }
            }
        });
    }
}
