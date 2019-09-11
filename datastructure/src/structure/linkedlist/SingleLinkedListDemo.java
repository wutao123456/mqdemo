package structure.linkedlist;


/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/7/28 10:12
 */
public class SingleLinkedListDemo {

    public static void main(String[] args) {
        HeroNode node1 = new HeroNode(1,"宋江","及时雨");
        HeroNode node2 = new HeroNode(2,"卢俊义","玉麒麟");
        HeroNode node3 = new HeroNode(3,"吴用","智多星");
        HeroNode node4 = new HeroNode(4,"林冲","豹子头");
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.addByOrder(node1);
        singleLinkedList.addByOrder(node4);
        singleLinkedList.addByOrder(node2);
        singleLinkedList.addByOrder(node3);
        singleLinkedList.list();
        singleLinkedList.update(new HeroNode(5,"吴涛","势必"));
        singleLinkedList.list();
        singleLinkedList.delete(new HeroNode(3,"",""));
        singleLinkedList.list();

    }
}

class SingleLinkedList{
    private HeroNode head = new HeroNode(0,"","");

    public void add(HeroNode node){
        HeroNode temp = head;

        while (true){
            if(temp.next == null){
                break;
            }
            temp = temp.next;
        }
        temp.next = node;
    }

    public void list(){
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }

        HeroNode temp = head.next;
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
    public void addByOrder(HeroNode node){
        HeroNode temp = head;
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

    public void update(HeroNode node){
        HeroNode temp = head;
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

    public void delete(HeroNode node){
        HeroNode temp = head;
        if(temp.next == null){
            System.out.println("链表为空");
            return;
        }

        boolean flag = false;
        while (true){
            if(temp.next.no == node.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if(flag){
            temp.next = temp.next.next;
        }
    }
}

class HeroNode{
    public int no;

    public String name;

    public String nickName;

    public HeroNode next;

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
