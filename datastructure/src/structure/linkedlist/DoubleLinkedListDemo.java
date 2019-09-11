package structure.linkedlist;


/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/7/28 10:12
 */
public class DoubleLinkedListDemo {

    public static void main(String[] args) {
        HeroNode2 node1 = new HeroNode2(1,"宋江","及时雨");
        HeroNode2 node2 = new HeroNode2(2,"卢俊义","玉麒麟");
        HeroNode2 node3 = new HeroNode2(3,"吴用","智多星");
        HeroNode2 node4 = new HeroNode2(4,"林冲","豹子头");
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(node1);
        doubleLinkedList.add(node4);
        doubleLinkedList.add(node2);
        doubleLinkedList.add(node3);
        doubleLinkedList.list();
        doubleLinkedList.delete(new HeroNode2(1,"",""));
        System.out.println("删除节点");
        doubleLinkedList.list();

    }
}

class DoubleLinkedList{
    private HeroNode2 head = new HeroNode2(0,"","");

    public void add(HeroNode2 node){
        HeroNode2 temp = head;

        while (true){
            if(temp.next == null){
                break;
            }
            temp = temp.next;
        }
        temp.next = node;
        node.pre = temp;
    }

    public void list(){
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }

        HeroNode2 temp = head.next;
        while (true){
            if(temp == null){
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }

    /**
     * 顺序插入
     * @param node
     */
    public void addByOrder(HeroNode2 node){
        HeroNode2 temp = head;
        boolean flag = false;
        while (true){
            if(temp.next == null){
                break;
            }

            if(temp.next.no > node.no){
                break;
            }else if(node.no == temp.next.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if(flag){

        }else{
            node.next = temp.next;
            temp.next = node;
        }

    }

    public void update(HeroNode2 node){
        HeroNode2 temp = head;
        if(temp.next == null){
            System.out.println("链表为空");
            return;
        }

        boolean flag = false;
        while (true){
            if(temp.next == null){
                break;
            }
            if(temp.next.no == node.no){
                flag = true;
                break;
            }

            temp = temp.next;

        }

        if(flag){
            temp.next.name = node.name;
            temp.next.nickName = node.nickName;
        }else{
            System.out.printf("没找到编号为%d的节点,不能修改\n",node.no);
        }
    }

    public void delete(HeroNode2 node){
        HeroNode2 temp = head.next;
        if(temp  == null){
            System.out.println("链表为空");
            return;
        }

        boolean flag = false;
        while (true){
            if(temp.no == node.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if(flag){
            if(temp.pre != null){
                temp.pre.next = temp.next;
            }
            if(temp.next != null){
                temp.next.pre = temp.pre;
            }
        }
    }
}

class HeroNode2{
    public int no;

    public String name;

    public String nickName;

    public HeroNode2 next;

    public HeroNode2 pre;

    public HeroNode2(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
