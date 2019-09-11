package structure.tree;

import java.security.MessageDigest;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/9/8 21:26
 * 参考文章
 * https://juejin.im/entry/5aae7502f265da23923620dd
 * 二叉树动态演示
 * http://algoanim.ide.sk/index.php?page=showanim&id=63
 */
public class RBTree<T> {

    public static class TreeNode<T>{
        private T data;

        private TreeNode<T> left;

        private TreeNode<T> right;

        private TreeNode<T> parent;

        private Color color;

        private int value;

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    enum Color{
        RED,BLACK
    }

    private TreeNode<T> root;

    /**
     * 插入节点
     * 参考 https://www.jianshu.com/p/de06c3170e4f
     * @param node
     */
    public void insert(TreeNode<T> node){
        if(root == null){
            root = node;
            root.left = null;
            root.right = null;
            root.parent = null;
            root.color = Color.BLACK;
            return;
        }

        TreeNode<T> currentNode = root;
        while (currentNode != null){
            if(currentNode.value >= node.value){
                if(currentNode.left == null){
                    currentNode.left = node;
                    break;
                }else{
                    currentNode = currentNode.left;
                }
            }else{
                if(currentNode.right == null){
                    currentNode.right = node;
                    break;
                }else{
                    currentNode = currentNode.right;
                }
            }
        }

        node.parent = currentNode;
        node.color = Color.RED;
        node.left = null;
        node.right = null;
        //TODO 平衡修复
        doAddBalance(node);
    }

    /**
     * 添加时进行的红黑树性质匹配操作
     * @param node
     */
    public void doAddBalance(TreeNode<T> node){
        TreeNode<T> grandP;
        TreeNode<T> uncle;
        TreeNode<T> current = node;
        while (current.parent != null && current.parent.color == Color.RED){
            grandP = current.parent.parent;
            if(grandP.left == current.parent){
                uncle = current.parent.parent.right;
                if(uncle == null || uncle.color == Color.BLACK){//叔叔为黑
                    if(current.parent.left == current){//当前节点为左子节点
                        current.parent.color = Color.BLACK;//父节点设置为黑色
                        grandP.color = Color.RED;//祖父节点设置为红色
                        rightRotate(grandP);//对祖父节点右旋
                    }else if(current.parent.right == current){
                        //父节点左旋
                        current = current.parent;
                        leftRotate(current);
                    }

                }else{//叔叔节点为红色
                    uncle.color = Color.BLACK;
                    current.parent.color = Color.BLACK;
                    if(grandP.parent != null){
                        grandP.color = Color.RED;
                    }
                    current = grandP;
                }
            }else if(grandP.right == current.parent){
                uncle = current.parent.parent.left;
                if(uncle == null || uncle.color == Color.BLACK){
                    if (current.parent.left == current) {
                        current = current.parent;
                        rightRotate(current);//父节点右旋
                    } else if (current.parent.right == current) {
                        current.parent.color = Color.BLACK;
                        grandP.color = Color.RED;
                        leftRotate(grandP);
                    }
                }else{//叔叔节点为红色
                    current.parent.color = Color.BLACK;
                    uncle.color = Color.BLACK;
                    if(grandP.parent != null){
                        grandP.color = Color.RED;
                    }
                    current = grandP;
                }
            }
        }
    }

    /**
     * 参考
     * https://www.cnblogs.com/skywang12345/p/3624343.html
     * @param node
     */
    public void remove(TreeNode<T> node){
        TreeNode<T> child,parent;
        Color color;

        if(node.left != null && node.right != null){
            TreeNode<T> replace = node;

            //获取后继节点
            replace = replace.right;
            while (replace.left != null){
                replace = replace.left;
            }

            if(parentOf(node) != null){
                if(parentOf(node).left == node){
                    parentOf(node).left = replace;
                }else{
                    parentOf(node).right = replace;
                }
            }else{
                this.root = replace;
            }


            //替换节点不可能存在左子节点(如果存在,最左子节点就不是当前替换节点)
            child = replace.right;
            parent = replace.parent;

            color = colorOf(replace);

            if(parent == node){
                parent = replace;
            }else{
                if(child != null){
                    setParent(child,parent);
                }

                //右子树的最左子节点
                parent.left = child;
                replace.right = node.right;
                setParent(node.right,replace);

            }

            replace.parent = node.parent;
            replace.color = node.color;
            replace.left = node.left;
            node.left.parent = replace;

            if(color == Color.BLACK){
                removeFixUp(child,parent);
            }
            node = null;
            return;
        }

        if(node.left != null){
            child = node.left;
        }else{
            child = node.right;
        }

        parent = node.parent;
        color = node.color;

        if(child != null){
            child.parent = parent;
        }

        if(parent != null){
            if(parent.left == node){
                parent.left = child;
            }else{
                parent.right = child;
            }

        }else{
            this.root = child;
        }

        if(color == Color.BLACK){
            removeFixUp(child,parent);
        }

        node = null;
    }

    private void removeFixUp(TreeNode<T> node,TreeNode<T> parent){
        TreeNode<T> other;
        while ((node == null ||node.color == Color.BLACK) && node != this.root){
            if(parent.left == node){
                other = parent.right;
                if(other.color == Color.RED){//兄弟节点是红色
                    other.color = Color.BLACK;
                    parent.color = Color.RED;
                    leftRotate(parent);
                    other = parent.right;
                }

                //兄弟节点为黑色,且两个子节点都为黑色
                if((other.left == null||other.left.color == Color.BLACK) &&
                    (other.right == null || other.right.color == Color.BLACK)){
                    other.color = Color.RED;
                    node = parent;
                    parent = parentOf(node);
                }else{
                    //兄弟节点为黑色,左子节点为红色,右子节点为黑色
                    if(other.right == null || other.right.color == Color.BLACK){
                        other.left.color = Color.BLACK;
                        other.color = Color.RED;
                        rightRotate(other);
                        other = parent.right;
                    }

                    other.color = parent.color;
                    parent.color = Color.BLACK;
                    other.right.color = Color.BLACK;
                    leftRotate(parent);
                    node = this.root;
                    break;

                }
            }else{
                other = parent.left;
                if(other.color == Color.RED){//兄弟节点是红色
                    other.color = Color.BLACK;
                    parent.color = Color.RED;
                    rightRotate(parent);
                    other = parent.left;
                }

                //兄弟节点为黑色,且两个子节点都为黑色
                if((other.left == null||other.left.color == Color.BLACK) &&
                        (other.right == null || other.right.color == Color.BLACK)){
                    other.color = Color.RED;
                    node = parent;
                    parent = parentOf(node);
                }else{
                    //兄弟节点为黑色,左子节点为黑色,右子节点为宏色
                    if(other.left == null || other.left.color == Color.BLACK){
                        other.right.color = Color.BLACK;
                        other.color = Color.RED;
                        leftRotate(other);
                        other = parent.left;
                    }

                    other.color = parent.color;
                    other.left.color = Color.BLACK;
                    parent.color = Color.BLACK;
                    rightRotate(parent);
                    node = this.root;
                    break;

                }
            }
        }

        if(node != null){
            node.color = Color.BLACK;
        }
    }

    private TreeNode<T> parentOf(TreeNode<T> node){
        return node != null ? node.parent : null;
    }

    private Color colorOf(TreeNode<T> node){
        return node != null ? node.color : null;
    }

    private void setParent(TreeNode<T> node,TreeNode<T> parent){
        if(node != null){
            node.parent = parent;
        }
    }

    public void leftRotate(TreeNode<T> node){
        TreeNode<T> x = node;
        TreeNode<T> y = node.right;
        TreeNode<T> b = y.left;
        x.right = b;
        if(b != null){
            b.parent = x;
        }

        y.parent = x.parent;
        if(x.parent == null){
            root = y;
        }else{
            if(x.parent.left == x){//x是父节点的左子节点
                x.parent.left = y;
            }else if(x.parent.right == x){//x是父节点的右子节点
                x.parent.right = y;
            }
        }

        x.parent = y;//将y设置为x的父亲
        y.left = x;//将x设为y的左子节点
    }

    public void rightRotate(TreeNode<T> node){
        TreeNode<T> y = node;
        TreeNode<T> x = node.left;
        TreeNode<T> b = x.right;
        y.left = b;
        if(b != null){
            b.parent = x;
        }

        //将y的父亲设置为x的父亲
        x.parent = y.parent;
        if(y.parent == null){
            root = x;
        }else{
            if(y.parent.left == y){//y是父节点的左子节点
                y.parent.left = x;
            }else if(y.parent.right == y){//y是父节点的右子节点
                y.parent.right = x;
            }
        }

        y.parent = x;//将x设置为y的父亲
        x.right = y;//将y设为x的右子节点
    }


    private void print(TreeNode<T> tree, int value, int direction) {

        if (tree != null) {

            if (direction == 0)    // tree是根节点
                System.out.printf("%2d(B) is root\n", tree.value);
            else                // tree是分支节点
                System.out.printf("%2d(%s) is %2d's %6s child\n", tree.value, tree.color == Color.RED ? "R" : "B", value, direction == 1 ? "right" : "left");

            print(tree.left, tree.value, -1);
            print(tree.right, tree.value, 1);
        }

    }

    public void print() {
        if (root != null)
            print(root, root.value, 0);
    }


    private static final int a[] = {10, 40, 30, 60, 90, 70, 20, 50, 80};

    public static void main(String[] args) {
        int i, ilen = a.length;
        RBTree<TreeNode> tree = new RBTree<TreeNode>();
        System.out.printf("== 原始数据: ");
        for (i = 0; i < ilen; i++)
            System.out.printf("%d ", a[i]);
        System.out.printf("\n");

        for (i = 0; i < ilen; i++) {
            TreeNode node = new TreeNode<>();
            node.setValue(a[i]);
            tree.insert(node);

        }

        tree.print();
        System.out.println("删除节点");
        TreeNode node = new TreeNode<>();
        node.setValue(60);
        tree.remove(node);
        tree.print();


    }
}
