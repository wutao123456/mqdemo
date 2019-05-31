package hadoop.table;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/5/30 22:50
 */
public class TableBean implements Writable {

    private String id;

    private String pid;

    private int amount;

    private String pname;

    private String flag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public TableBean() {
    }

    public TableBean(String id, String pid, int amount, String pname, String flag) {
        this.id = id;
        this.pid = pid;
        this.amount = amount;
        this.pname = pname;
        this.flag = flag;
    }

    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(id);
        dataOutput.writeUTF(pid);
        dataOutput.writeInt(amount);
        dataOutput.writeUTF(pname);
        dataOutput.writeUTF(flag);
    }

    public void readFields(DataInput dataInput) throws IOException {
        this.id = dataInput.readUTF();
        this.pid = dataInput.readUTF();
        this.amount = dataInput.readInt();
        this.pname = dataInput.readUTF();
        this.flag = dataInput.readUTF();
    }

    @Override
    public String toString() {
        return "TableBean{" +
                "id='" + id + '\'' +
                ", pid='" + pid + '\'' +
                ", amount=" + amount +
                ", pname='" + pname + '\'' +
                ", flag='" + flag + '\'' +
                '}';
    }
}
