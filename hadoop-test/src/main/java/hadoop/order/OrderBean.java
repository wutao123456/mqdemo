package hadoop.order;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/5/26 22:39
 */
public class OrderBean implements WritableComparable<OrderBean> {

    private int order_id;

    private double price;

    public OrderBean() {
        super();
    }

    public OrderBean(int order_id, double price) {
        this.order_id = order_id;
        this.price = price;
    }

    public int compareTo(OrderBean o) {
        int result ;
        if(order_id > o.order_id){
            result = 1;
        }else if(order_id < o.order_id){
            result = -1;
        }else {
            if(price > o.price){
                result = -1;
            }else if (price < o.price){
                result = 1;
            }else{
                result = 0;
            }
        }

        return result;
    }

    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(order_id);
        dataOutput.writeDouble(price);
    }

    public void readFields(DataInput dataInput) throws IOException {
       order_id = dataInput.readInt();
       price = dataInput.readDouble();
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderBean{" +
                "order_id=" + order_id +
                ", price=" + price +
                '}';
    }

}
