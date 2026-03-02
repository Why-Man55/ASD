package DoubleLinalList;

public class DoubleLinalList {
    private DoubleNode head;

    public DoubleLinalList(int info){
        head = new DoubleNode(info);
    }

    public void addStart(int info){
        DoubleNode doubleNode = new DoubleNode(info);
        doubleNode.next = head;
        head = doubleNode;
    }

    public boolean deleteStart(){
        if (head != null){
            head = head.next;
            return true;
        } else {
            return false;
        }
    }

    public void addLast(int info){
        if (head != null){
            DoubleNode pointer = head;
            DoubleNode doubleNode = new DoubleNode(info);
            while (pointer.next != null){
                pointer = pointer.next;
            }
            pointer.next = doubleNode;
        } else {
            addStart(info);
        }
    }

    public boolean deleteLast(){
        if (head != null){
            if (head.next == null){
                head = null;
                return true;
            }
            DoubleNode pointer = head;
            while (pointer.next != null) {
                pointer = pointer.next;
            }
            pointer.prev.next = null;
            return true;
        } else {
            return false;
        }
    }

    public void addAt(int info, int id){
        if (head != null){
            if (id > 0 && id <= this.getSize()){
                DoubleNode pointer = head;
                int counter = 1;
                while (counter != id){
                    counter++;
                    pointer = pointer.next;
                }
                DoubleNode holder = pointer.next;
                pointer.prev.next = new DoubleNode(info);
                pointer.prev.next.next = holder;
            }
        } else {
            addStart(info);
        }
    }

    public boolean deleteAt(int id){
        if (head != null){
            if (id > 0 && id <= this.getSize()){
                DoubleNode pointer = head;
                int counter = 1;
                while (counter != id) {
                    counter++;
                    pointer = pointer.next;
                }
                pointer.prev.next = pointer.next;
            } else {
                System.out.println("WRONG ID");
                return false;
            }
        } else {
            deleteStart();
        }
        return true;
    }

    public int getSize(){
        int counter = 0;
        DoubleNode doubleNode = head;
        while (head != null){
            counter++;
            head = head.next;
        }
        return counter;
    }
}
