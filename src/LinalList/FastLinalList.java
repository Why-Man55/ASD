package LinalList;

public class FastLinalList {
    private Node head;
    private Node tail;

    public FastLinalList(){
        this.head = new Node();
        this.tail = head;
    }

    public FastLinalList(int info){
        this.head = new Node(info);
        this.tail = head;
    }

    public FastLinalList(int[] massive){
        for (int info : massive){
            addStart(info);
        }
    }

    public void addStart(int info){
        Node node = new Node(info);
        node.next = head;
    }

    public boolean deleteStart(){
        if (head != null){
            head = head.next;
            return true;
        } else {
            System.out.println("Список пуст");
            return false;
        }
    }

    public void addEnd(int info){
        Node node = new Node(info);
        tail.next = node;
    }

    public boolean deleteEnd(){
        if (tail != null){
            Node pointer = head;
            while (pointer.next != tail){
                pointer = pointer.next;
            }
            pointer.next = null;
            return true;
        } else {
            System.out.println("Список пуст");
            return false;
        }
    }

    public void addAt(int info, int id){
        if (this.getSize() >= id) {
            Node newNode = new Node(info);
            Node node = head;
            for (int i = 2; i <= id; i++){
                node = head.next;
            }
            newNode.next = node.next;
            node.next = newNode;
        } else {
            System.out.println("НЕВЕРНЫЙ ID");
        }
    }

    public void deleteAt(int id){
        if (id > 0 && id <= this.getSize()){
            if (id == this.getSize()){
                head = head.next;
            } else if (id == 1){
                head = head.next;
            } else {
                Node pointer = head;
                int counter = 1;
                while(counter != id - 1){
                    counter++;
                    pointer = head.next;
                }
                pointer.next = pointer.next.next;
            }
        } else {
            System.out.println("Неверный ID");
        }
    }

    public int getSize(){
        if (head != null) {
            int count = 1;
            Node node = head;
            while (node.next != null){
                count++;
                node = node.next;
            }
            return count;
        } else {
            return 0;
        }
    }
}
