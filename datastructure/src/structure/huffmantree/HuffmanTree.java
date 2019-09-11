package structure.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/8/5 22:54
 * 赫夫曼树：带权路径长度最短的树
 */
public class HuffmanTree {

    public static void main(String[] args) {

        int[] arr = {13,7,8,3,29,6,1};
        Node root = createHuffmanTree(arr);
        preOrder(root);

    }

    //前序遍历
    public static void preOrder(Node root){
        if(root != null){
            root.preOrder();
        }
    }

    /**
     * 构建赫夫曼树
     * @param arr
     * @return
     */
    public static Node createHuffmanTree(int[] arr){
        List<Node> nodes = new ArrayList<>();
        for(int value:arr){
            nodes.add(new Node(value));
        }
        while (nodes.size() > 1){
            //从小到大排序
            Collections.sort(nodes);
//            System.out.println(nodes);

            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);

            Node parent = new Node(leftNode.value+rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
        }

        return nodes.get(0);
    }
}

class Node implements Comparable<Node>{
    int value;//节点权值
    Node left;//左子节点
    Node right;//右子节点


    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }

    public void preOrder(){
        System.out.println(this);
        if(this.left != null){
            this.left.preOrder();
        }

        if(this.right != null){
            this.right.preOrder();
        }
    }
}
