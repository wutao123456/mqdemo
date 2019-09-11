package structure.tree;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/8/4 20:47
 * 顺序存储二叉树
 */
public class ArrBinaryTreeDemo {

    /**
     * 数组中第n个元素的左子节点2n+1
     * 数组中第n个元素的右子节点2n+2
     * 第n个元素的父节点(n-1)/2
     * @param args
     */
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        ArrBinaryTree tree = new ArrBinaryTree(arr);
//        tree.preOrder(0);
//        tree.infixOrder(0);
        tree.postOrder(0);

    }
}

class ArrBinaryTree{
    private int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    /**
     * 前序遍历
     * @param index
     */
    public void preOrder(int index){
        if(arr == null || arr.length == 0){
            System.out.println("数组为空,不能进行二叉树的遍历");
        }

        System.out.println(arr[index]);

        if(index * 2 + 1 < arr.length){
            preOrder(index * 2 + 1);
        }

        if(index * 2 + 2 < arr.length){
            preOrder(index * 2 + 2);
        }
    }

    /**
     * 中序遍历
     * @param index
     */
    public void infixOrder(int index){
        if(arr == null || arr.length == 0){
            System.out.println("数组为空,不能进行二叉树的遍历");
        }


        if(index * 2 + 1 < arr.length){
            infixOrder(index * 2 + 1);
        }

        System.out.println(arr[index]);

        if(index * 2 + 2 < arr.length){
            infixOrder(index * 2 + 2);
        }
    }

    /**
     * 后序遍历
     * @param index
     */
    public void postOrder(int index){
        if(arr == null || arr.length == 0){
            System.out.println("数组为空,不能进行二叉树的遍历");
        }

        if(index * 2 + 1 < arr.length){
            postOrder(index * 2 + 1);
        }

        if(index * 2 + 2 < arr.length){
            postOrder(index * 2 + 2);
        }

        System.out.println(arr[index]);
    }
}
