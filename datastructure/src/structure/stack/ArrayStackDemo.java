package structure.stack;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/7/28 21:15
 */
public class ArrayStackDemo {

    public static void main(String[] args) {

        ArrayStack stack = new ArrayStack(5);
        stack.push(1);
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.list();
        stack.pop();
        stack.list();

    }
}

class ArrayStack{

    private int maxSize;

    private int[] stack;

    private int top = -1;

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    public boolean isFull(){
        return top == maxSize - 1;
    }

    public boolean isEmpty(){
        return top == -1;
    }

    public void push(int value){
        if(isFull()){
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    public int pop(){
        if(isEmpty()){
            throw new RuntimeException("栈空");
        }
        int value = stack[top];
        top--;
        return value;
    }

    public void list(){
        if(isEmpty()){
            System.out.println("栈空");
            return;
        }

        for(int i = top;i>=0;i--){
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }

}
