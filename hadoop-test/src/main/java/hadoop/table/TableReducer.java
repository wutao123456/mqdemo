package hadoop.table;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/5/30 23:00
 */
public class TableReducer extends Reducer<Text,TableBean,TableBean, NullWritable> {

    @Override
    protected void reduce(Text key, Iterable<TableBean> values, Context context) throws IOException, InterruptedException {
        ArrayList<TableBean> tableBeans = new ArrayList<TableBean>();
        TableBean pBean = new TableBean();
        for(TableBean tableBean:values){
            if("order".equals(tableBean.getFlag())){
                TableBean tmpBean = new TableBean();
                try {
                    BeanUtils.copyProperties(tmpBean,tableBean);
                    tableBeans.add(tmpBean);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

            }else{
                try {
                    BeanUtils.copyProperties(pBean,tableBean);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

        for(TableBean tableBean:tableBeans){
            tableBean.setPname(pBean.getPname());
            context.write(tableBean,NullWritable.get());
        }
    }
}
