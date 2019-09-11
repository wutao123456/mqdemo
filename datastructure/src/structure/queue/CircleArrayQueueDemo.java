package structure.queue;

import java.util.Scanner;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/7/22 21:38
 */
public class CircleArrayQueueDemo {

    public static void main(String[] args) {
        CircleArrayQueue queue = new CircleArrayQueue(4);//队列最大有效数据3个
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop){
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):获取队列中数据");
            System.out.println("h(head):获取队列头数据");
            key = scanner.next().charAt(0);
            switch (key){
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g':
                    int res = queue.getQueue();
                    System.out.printf("取出的数据是%d\n",res);
                    break;
                case 'h':
                    int r = queue.headQueue();
                    System.out.printf("取出的头部数据是%d\n",r);
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                        break;

            }
        }
        System.out.println("程序退出~~~~~~~~");
    }
}

class CircleArrayQueue{
    private int maxSize;
    private int front;
    private int rear;
    private int[] arr;

    public CircleArrayQueue(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
    }

    public boolean isFull(){
        return (rear + 1) % maxSize == front;
    }

    public boolean isEmpty(){
        return rear == front;
    }

    public void addQueue(int n){
        if(isFull()){
            System.out.println("队列已满");
            return;
        }

        arr[rear] = n;
        rear = (rear + 1) % maxSize;
    }

    public int getQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列为空");
        }
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    public void showQueue(){
        if(isEmpty()){
            System.out.println("队列为空");
            return;
        }
        for(int i= front;i<front + size();i++){
            System.out.printf("arr[%d]=%d\n",i % maxSize,arr[i % maxSize]);
        }
    }

    //队列有效数
    public int size(){
        return (rear + maxSize - front) % maxSize;
    }

    public int headQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列为空");
        }
        return arr[front];
    }
}
