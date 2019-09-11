package structure.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/8/28 23:50
 * 图
 */
public class Graph {

    private ArrayList<String> vertexList;//存储顶点集合

    private int[][] edges;//存储图对应的邻接矩阵

    private int numOfEdges;//边的数目

    private boolean[] isVisited;

    public static void main(String[] args) {

        int n = 5;
        String[] vertexs = {"A","B","C","D","E"};
        Graph graph = new Graph(n);
        for(String vertex:vertexs){
            graph.insertVertex(vertex);
        }

        //A-B,A-C,B-C,B-D,B-E
        graph.insertEdge(0,1,1);
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,2,1);
        graph.insertEdge(1,3,1);
        graph.insertEdge(1,4,1);

        graph.showGraph();

        graph.bfs();

    }


    public Graph(int n) {
        this.vertexList = new ArrayList<>(n);
        this.edges = new int[n][n];
        this.numOfEdges = 0;
        this.isVisited = new boolean[5];
    }

    /**
     * 获取第一个邻接点
     * @param index
     * @return
     */
    public int getFirstNeighbor(int index){
        for(int j=0;j<vertexList.size();j++){
            if(edges[index][j] > 0){
                return j;
            }
        }

        return -1;
    }

    /**
     * 获取下一个邻接点
     * @param v1
     * @param v2
     * @return
     */
    public int getNextNeighbor(int v1,int v2){
        for(int j = v2+1;j < vertexList.size();j++){
            if(edges[v1][j] > 0){
                return j;
            }
        }

        return -1;
    }


    /**
     * 深度优先
     * @param isVisited
     * @param i
     */
    public void dfs(boolean[] isVisited,int i){
        System.out.print(getValueByIndex(i)+"->");
        isVisited[i] = true;
        int w = getFirstNeighbor(i);
        while (w != -1){
            if(!isVisited[w]){
                dfs(isVisited,w);
            }
            w = getNextNeighbor(i,w);
        }
    }

    public void dfs(){
        for(int i=0;i<vertexList.size();i++){
            if(!isVisited[i]){
                dfs(isVisited,i);
            }
        }
    }

    /**
     * 广度优先
     * @param isVisited
     * @param i
     */
    public void bfs(boolean[] isVisited,int i){
        int u;//头节点的下标
        int w;//下一个邻接点的下标
        LinkedList queue = new LinkedList();
        System.out.println(getValueByIndex(i)+"->");
        isVisited[i] = true;
        queue.addLast(i);
        while (!queue.isEmpty()){
            u = (Integer) queue.removeFirst();
            w = getFirstNeighbor(u);
            while (w != -1){
                if(!isVisited[w]){
                    System.out.println(getValueByIndex(w)+"->");
                    isVisited[w] = true;
                    queue.addLast(w);
                }
                w = getNextNeighbor(u,w);//广度优先
            }
        }
    }

    public void bfs(){
        for(int i=0;i<vertexList.size();i++){
            if(!isVisited[i]){
                dfs(isVisited,i);
            }
        }
    }


    public void showGraph(){
        for(int[] link:edges){
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     * 返回顶点的个数
     * @return
     */
    public int getNumOfVertex() {
        return vertexList.size();
    }

    /**
     * 返回边的数目
     * @return
     */
    public int getNumOfEdges(){
        return numOfEdges;
    }

    /**
     * 返回节点下标对应的数据
     * @param i
     * @return
     */
    public String getValueByIndex(int i){
        return vertexList.get(i);
    }

    public int getWeight(int v1,int v2){
        return edges[v1][v2];
    }

    //插入顶点
    public void insertVertex(String vertex){
        vertexList.add(vertex);
    }

    /**
     * 添加边
     * @param v1 表示点的下标,即第几个顶点 "A-B",A=>0,B=>1
     * @param v2 第二个顶点的下标
     * @param weight
     */
    public void insertEdge(int v1,int v2,int weight){
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }
}
