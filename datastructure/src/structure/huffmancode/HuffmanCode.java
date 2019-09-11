package structure.huffmancode;

import java.util.*;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/8/5 23:52
 * 赫夫曼编码
 */
public class HuffmanCode {

    //TODO 赫夫曼压缩与解压(二进制的补码,反码)
    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        System.out.println(getNodeList(content.getBytes()));

        byte[] h = huffmanZip(content.getBytes());
        System.out.println(Arrays.toString(h));

    }

    private static byte[] huffmanZip(byte[] bytes){
        List<Node> nodeList = getNodeList(bytes);

        //创建赫夫曼树
        Node root = createHuffmanTree(nodeList);
        root.preOrder();

        //获取赫夫曼编码
        getCodes(root);
        System.out.println("生成的赫夫曼编码表 "+huffmanCodes);

        //根据赫夫曼编码压缩
        return zip(bytes,huffmanCodes);
    }

    public static byte[] zip(byte[] bytes,Map<Byte,String> huffmanCodes){
        StringBuilder stringBuilder = new StringBuilder();
        for(byte b:bytes){
            stringBuilder.append(huffmanCodes.get(b));
        }

        System.out.println("Stringbuilder="+stringBuilder);
        int len;
        if(stringBuilder.length() % 8 == 0){
            len = stringBuilder.length() / 8;
        }else{
            len = stringBuilder.length() / 8 + 1;
        }

        byte[] huffmanCode = new byte[len];
        int index = 0;
        for(int i = 0;i<stringBuilder.length();i += 8){
            String strBytes;
            if(i+8 > stringBuilder.length()){
                strBytes = stringBuilder.substring(i);
            }else{
                strBytes = stringBuilder.substring(i,i+8);
            }
            huffmanCode[index] = (byte) Integer.parseInt(strBytes,2);
            index++;
        }

        //生成长度为17的字节数组,压缩率为(40-17)/40
//        System.out.println(Arrays.toString(huffmanCode));
        return huffmanCode;
    }

    static Map<Byte,String> huffmanCodes = new HashMap<Byte,String>();
    static StringBuilder stringBuilder = new StringBuilder();


    private static Map<Byte,String> getCodes(Node root){
        if(root == null){
            return null;
        }

        getCodes(root.left,"0",stringBuilder);
        getCodes(root.right,"1",stringBuilder);
        return huffmanCodes;
    }
    private static void getCodes(Node node,String code,StringBuilder stringBuilder){
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        stringBuilder2.append(code);
        if(node != null){
            if(node.data == null){//非叶子节点
                getCodes(node.left,"0",stringBuilder2);
                getCodes(node.right,"1",stringBuilder2);
            }else{//叶子节点
                huffmanCodes.put(node.data,stringBuilder2.toString());
            }
        }
    }

    public static List<Node> getNodeList(byte[] bytes){
        List<Node> nodeList = new ArrayList<>();
        Map<Byte,Integer> map = new HashMap<>();
        for(Byte b:bytes){
            Integer count = map.get(b);
            if(count == null){
                map.put(b,1);
            }else{
                map.put(b,count+1);
            }
        }

        for(Map.Entry<Byte,Integer> entry:map.entrySet()){
            nodeList.add(new Node(entry.getKey(),entry.getValue()));
        }
        return nodeList;
    }

    public static Node createHuffmanTree(List<Node> nodes){
        while (nodes.size() > 1){
            Collections.sort(nodes);
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            Node parent = new Node(null,leftNode.weight + rightNode.weight);
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
    Byte data;
    int weight;
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
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
