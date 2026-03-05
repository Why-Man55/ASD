package LinalList;

public class LinalList {
    private Node head;

    public LinalList(){
        this.head = new Node();
    }

    public LinalList(int info){
        this.head = new Node(info);
    }

    public LinalList(int[] massive){
        for (int info : massive){
            add(info);
        }
    }

    public void add(int info){
        Node node = new Node(info);
        node.next = head;
        head = node;
    }

    public void addStart(int info){
        Node node = head;
        head = new Node(info);
        head.next = node;
    }

    public void addAt(int info, int id){
        if (getSize(head) >= id) {
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

    public void addRange(int[] info){
        //TODO
    }

    public void print(){
        Node node = head;

        if (this.isEmpty()) {
            System.out.println("Пустой список.");
        } else {
            System.out.print(node.info);
            node = node.next;
            while (node != null){
                System.out.print(" -> " + node.info);
                node = node.next;
            }
            System.out.println();
        }
    }

    public void deleteLast(){
        head = head.next;
    }

    public void deleteAt(int id){
        if (id > 0 && id <= this.getSize()){
            if (id == this.getSize()){
                this.deleteLast();
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

    public void deleteFrom(int id){
        if (id > 0 && id <= this.getSize()) {
            Node pointer = head;
            int counter = 1;

            while (counter != id){
                pointer = pointer.next;
                counter++;
            }
            pointer.next = null;
        } else {
            System.out.println("Неверный ID");
        }
    }

    public void deleteSecond(){
        head.next = head.next.next;
    }

    public void reverse(){
        //TODO
        if (this.getSize() < 2) {
            this.print();
        } else {
            Node revList = head;
            Node pointer = head;
            Node holder;

            while (pointer != null) {
                holder = pointer.next;
                pointer.next = revList;
                revList = pointer;
                pointer = holder;
            }
            print(revList);
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

    public boolean isEmpty(){
        return head == null;
    }

    public boolean isNotEmpty(){
        return head != null;
    }

    public void findInfo(int info){
        Node pointer = head;
        int counter = 1;
        boolean isFirst = true;
        String res = "";
        while (pointer.next != null){
            if (pointer.info == info) {
                if (isFirst) {
                    isFirst = false;
                    res = "" + counter;
                } else {
                    res += ", " + counter;
                }
            }
            counter++;
            pointer = pointer.next;
        }
        System.out.println(res);
    }

    public int getInfo(int id){
        try{
            if (id > 0 && id <= this.getSize()){
                Node pointer = head;
                int counter = 1;
                while (counter != id){
                    pointer = pointer.next;
                    counter++;
                }
                return pointer.info;
            } else {
                throw new ArithmeticException("НЕВЕРНЫЙ ID");
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public void changePositions(int id1, int id2){
        if (id1 > 0 && id2 > 0 && id2 <= this.getSize() && id1 <= id2){
            int counter = 1;
            Node pointer = head;
            Node firstElem = null;
            Node secondElem = null;

            while (pointer != null){
                if (counter == id1){
                    firstElem = pointer;
                }
                if (counter == id2) {
                    secondElem = pointer;
                }
                pointer = pointer.next;
                counter++;
            }

            this.deleteAt(id1);
            this.addAt(secondElem.info, id1);
            this.deleteAt(id2);
            this.addAt(firstElem.info, id2);

        }  else {
            System.out.println("Неверный ID");
        }
    }

    public void bubbleSort(){
        boolean isSorted = false;
        int size = this.getSize();
        while (!isSorted){
            isSorted = true;
            for (int i = 1; i < size - 1; i++){
                if (this.getInfo(i) > this.getInfo(i+1)){
                    this.changePositions(i,i+1);
                    isSorted = false;
                }
            }
        }
        System.out.println("Список отсортирован!");
        this.print();
    }

    //PRIVATE METHODS
    private int getSize(Node head){
        int count = 0;
        Node node = head;
        while (node.next != null){
            count++;
            node = node.next;
        }
        return ++count;
    }

    private void print(Node head){
        Node node = head;

        if (this.isEmpty()) {
            System.out.println("Пустой список.");
        } else {
            System.out.print(node.info);
            node = node.next;
            while (node != null){
                System.out.print(" -> " + node.info);
                node = node.next;
            }
            System.out.println();
        }
    }
}