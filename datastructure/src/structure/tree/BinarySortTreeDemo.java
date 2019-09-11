package structure.tree;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/8/26 23:41
 * 二叉排序树
 */
public class BinarySortTreeDemo {

    public static void main(String[] args) {

        int[] arr = {7,3,10,12,5,1,9};
        BinarySortTree binarySortTree = new BinarySortTree();
        for(int i=0;i<arr.length;i++){
            binarySortTree.add(new Node(arr[i]));
        }

        System.out.println("中序遍历二叉排序树");
        binarySortTree.infixOrder();

    }
}

class BinarySortTree{

    private Node root;

    public void add(Node node){
        if(root == null){
            root = node;
        }else{
            root.add(node);
        }
    }

    public void infixOrder(){
        if(root != null){
            root.infixOrder();
        }else{
            System.out.println("    aaaaaaaaaa");
        }
    }

}

class Node{
    int value;

    Node left;

    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    public void add(Node node){
        if(node == null){
            return;
        }

        if(node.value < this.value){
            if(this.left == null){
                this.left = node;
            }else{
                this.left.add(node);
            }
        }else{
            if(this.right == null){
                this.right = node;
            }else{
                this.right.add(node);
            }
        }
    }

    public void infixOrder(){
        if(this.left != null){
            this.left.infixOrder();
        }

        System.out.println(this);

        if(this.right != null){
            this.right.infixOrder();
        }
    }

    /**
     * 删除节点
     * 1.被删除节点没有子节点
     * 2.被删除节点有一个字节点
     * 3.被删除节点有两个子节点
     * @param node
     */
    public void delNode(Node node){

    }
}
