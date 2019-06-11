package hadoop.order;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/5/26 23:15
 * 使用自定义的对象作为key输出,就必须实现WritableComparable接口,重写compareTo方法
 */
public class OrderGroupingComparator extends WritableComparator {

    public OrderGroupingComparator() {
        super(OrderBean.class,true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {

        //只要ID相同，就认为是相同的key
        OrderBean aBean = (OrderBean) a;
        OrderBean bBean = (OrderBean) b;
        int result ;
        if(aBean.getOrder_id() > bBean.getOrder_id()){
            result = 1;
        }else if(aBean.getOrder_id() < bBean.getOrder_id()){
            result = -1;
        }else{
            result = 0;
        }

        return result;
    }
}
