package LinalList;

public class LinalList {
    private Node head;
    private int size;

    public LinalList(int info){
        this.head = new Node(info);
        size = 1;
    }

    public LinalList(int[] massive){
        for (int info : massive){
            addStart(info);
        }
        size = massive.length;
    }

    public void addStart(int info){
        Node node = head;
        head = new Node(info);
        head.next = node;
        size++;
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
            size++;
        } else {
            System.out.println("НЕВЕРНЫЙ ID");
        }
    }

//    public void addRange(int[] info){
//        //TODO
//    }

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

    public boolean deleteFirst(){
        if (head != null){
            head = head.next;
            size--;
            return true;
        } else {
            System.out.println("Пустой список.");
            return false;
        }
    }

    public boolean deleteLast(){
        if (head != null){
            if (head.next == null){
                this.deleteSecond();
            } else {
                Node pointer = head;
                while (pointer.next.next != null){
                    pointer = pointer.next;
                }
                pointer.next = null;
            }
            size--;
            return true;
        } else {
            System.out.println("Пустой список.");
            return false;
        }
    }

    public boolean deleteAt(int id){
        if (id > 0 && id <= size){
            if (id == size){
                this.deleteFirst();
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
            size--;
            return true;
        } else {
            System.out.println("Неверный ID");
            return false;
        }
    }

    public boolean deleteFrom(int id){
        if (id > 0 && id <= size) {
            Node pointer = head;
            int counter = 1;

            while (counter != id){
                pointer = pointer.next;
                counter++;
            }
            pointer.next = null;
            size--;
            return true;
        } else {
            System.out.println("Неверный ID");
            return false;
        }
    }

    public boolean deleteSecond(){
        if (head != null){
            if (head.next != null){
                head.next = head.next.next;
                size--;
                return true;
            } else {
                System.out.println("Второго элемента нет.");
            }
        } else {
            System.out.println("Пустой список.");
        }
        return false;
    }

    public void reverse(){
        //TODO
        if (size < 2) {
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
        return size;
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
            if (id > 0 && id <= size){
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
        if (id1 > 0 && id2 > 0 && id2 <= size && id1 <= id2){
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
        int capacity = size;
        while (!isSorted){
            isSorted = true;
            for (int i = 1; i < capacity - 1; i++){
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