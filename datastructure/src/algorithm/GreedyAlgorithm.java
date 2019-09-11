package algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/9/1 21:52
 * 贪心算法
 */
public class GreedyAlgorithm {

    public static void main(String[] args) {
        HashMap<String, HashSet<String>> broadcasts = new HashMap<>();

        HashSet<String> hashSet1 = new HashSet<>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");

        HashSet<String> hashSet2 = new HashSet<>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");

        HashSet<String> hashSet3 = new HashSet<>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");

        HashSet<String> hashSet4 = new HashSet<>();
        hashSet4.add("上海");
        hashSet4.add("天津");

        HashSet<String> hashSet5 = new HashSet<>();
        hashSet5.add("杭州");
        hashSet5.add("大连");

        broadcasts.put("K1",hashSet1);
        broadcasts.put("K2",hashSet2);
        broadcasts.put("K3",hashSet3);
        broadcasts.put("K4",hashSet4);
        broadcasts.put("K5",hashSet5);

        HashSet<String> allAreas = new HashSet<>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");

        //存放选择的电台集合
        ArrayList<String> selects = new ArrayList<>();

        //存放当前遍历的广播电台的选项
        HashSet<String> tempSet = new HashSet<>();

        String maxKey = null;
        while (allAreas.size() > 0){

            maxKey = null;
            for(String key:broadcasts.keySet()){
                tempSet.clear();
                HashSet<String> areas = broadcasts.get(key);
                tempSet.addAll(areas);
                tempSet.retainAll(allAreas);
                //tempSet.size() > broadcasts.get(key).size()体现出贪心算法的特点,每次都取最优解
                if(tempSet.size() > 0 && (maxKey == null || tempSet.size() > broadcasts.get(key).size())){
                    maxKey = key;
                }
            }

            if(maxKey != null){
                selects.add(maxKey);
                allAreas.removeAll(broadcasts.get(maxKey));
            }
        }

        System.out.println("得到的选择结果为: "+selects);
    }
}
