package structure.hashtable;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/8/24 22:11
 */
public class HashTableDemo {

    public static void main(String[] args) {

    }
}

class HashTab{
    public int size;

    public EmpLinkedList[] empLinkedListArr;

    public HashTab(int size) {
        this.size = size;
        this.empLinkedListArr = new EmpLinkedList[size];
    }

    public void add(Emp emp){
        int index = hashFun(emp.id);
        empLinkedListArr[index].add(emp);

    }

    public void list(){
        for(int i=0;i<size;i++){
            empLinkedListArr[i].list();
        }
    }

    public int hashFun(int id){
        return id % size;
    }
}

class Emp{
    public int id;

    public String name;

    public Emp next;

    public Emp(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
}

class EmpLinkedList{
    private Emp head;

    public void add(Emp emp){
        if(head == null){
            head = emp;
        }

        Emp curEmp = head;
        while (true){
            if(curEmp.next == null){
                break;
            }
            curEmp = curEmp.next;
        }
        curEmp.next = emp;
    }

    public void list(){
        if(head == null){
            System.out.println("链表为空");
            return;
        }
        Emp curEmp = head;
        while (true){
            System.out.printf("=> id=%d &name=%s\t",curEmp.id,curEmp.name);
            if(curEmp.next == null){
                break;
            }
            curEmp = curEmp.next;
        }
    }
}