package structure.avl;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/8/28 22:13
 * 平衡二叉树(左右子树高度差的绝对值不超过1)
 */
public class AVLTreeDemo {

    public static void main(String[] args) {
//        int[] arr = {4,3,6,5,7,8};

//        int[] arr = {10,12,8,9,7,6};
        int[] arr = {10,11,7,6,8,9};
        AVLTree avlTree = new AVLTree();
        for(int i=0;i<arr.length;i++){
            avlTree.add(new Node(arr[i]));
        }

        System.out.println("中序遍历");
        avlTree.infixOrder();

        System.out.println("树的高度："+avlTree.getRoot().height());
        System.out.println("树的左子树的高度："+avlTree.getRoot().leftHeight());
        System.out.println("树的右子树的高度："+avlTree.getRoot().rightHeight());
    }
}

class AVLTree{
    private Node root;

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

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

        //双旋转
        if((rightHeight() - leftHeight()) > 1){
            //若当前节点的右子节点的左子树的高度大于右子树的高度，先对右子节点进行右旋
            if(right != null && right.leftHeight() > right.rightHeight()){
                right.rightRotate();
            }
            leftRotate();
            return;
        }


        if((leftHeight() - rightHeight()) > 1){
            //若当前节点的左子节点的右子树的高度大于左子树的高度，先对左子节点进行左旋
            if(left != null && left.rightHeight() > left.leftHeight()){
                left.leftRotate();
            }
            rightRotate();
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

    public int leftHeight(){
        if(left == null){
            return 0;
        }
        return left.height();
    }

    public int rightHeight(){
        if(right == null){
            return 0;
        }
        return right.height();
    }


    public int height(){
        return Math.max(left == null ? 0 : left.height(),right == null ? 0 : right.height()) + 1;
    }

    /**
     * 左旋
     */
    public void leftRotate(){
        //以当前根节点的值创建新的节点
        Node newNode = new Node(value);
        //把新的节点的左子树设置为当前节点的左子树
        newNode.left = left;
        //把新的节点的右子树设置为右子节点的左子树
        newNode.right = right.left;
        //把当前节点的值替换为右子节点的值
        value = right.value;
        //把当前节点的右子树替换为右子节点的右子树
        right = right.right;
        //把当前节点的左子树替换为当前节点
        left = newNode;
    }

    /**
     * 右旋
     */
    public void rightRotate(){
        Node newNode = new Node(value);
        newNode.right = right;
        newNode.left = left.right;
        value = left.value;
        left = left.left;
        right = newNode;
    }
}
