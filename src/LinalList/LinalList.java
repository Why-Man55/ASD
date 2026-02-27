package LinalList;

public class LinalList {
    private Unit head;

    public LinalList(){
        this.head = new Unit();
    }

    public LinalList(int info){
        this.head = new Unit(info);
    }

    public LinalList(int[] massive){
        for (int info : massive){
            add(info);
        }
    }

    public void add(int info){
        Unit unit = new Unit(info);
        unit.previous = head;
        head = unit;
    }

    public void addStart(int info){
        Unit unit = head;
        head = new Unit(info);
        head.previous = unit;
    }

    public void addAt(int info, int id){
        if (getSize(head) >= id) {
            Unit newUnit = new Unit(info);
            Unit unit = head;
            for (int i = 2; i <= id; i++){
                unit = head.previous;
            }
            newUnit.previous = unit.previous;
            unit.previous = newUnit;
        } else {
            System.out.println("НЕВЕРНЫЙ ID");
        }
    }

    public void addRange(int[] info){
        //TODO
    }

    public void print(){
        Unit unit = head;

        if (this.isEmpty()) {
            System.out.println("Пустой список.");
        } else {
            System.out.print(unit.info);
            unit = unit.previous;
            while (unit != null){
                System.out.print(" -> " + unit.info);
                unit = unit.previous;
            }
            System.out.println();
        }
    }

    public void deleteLast(){
        head = head.previous;
    }

    public void deleteAt(int id){
        if (id > 0 && id <= this.getSize()){
            if (id == this.getSize()){
                this.deleteLast();
            } else if (id == 1){
                head = head.previous;
            } else {
                Unit pointer = head;
                int counter = 1;
                while(counter != id - 1){
                    counter++;
                    pointer = head.previous;
                }
                pointer.previous = pointer.previous.previous;
            }
        } else {
            System.out.println("Неверный ID");
        }
    }

    public void deleteFrom(int id){
        if (id > 0 && id <= this.getSize()) {
            Unit pointer = head;
            int counter = 1;

            while (counter != id){
                pointer = pointer.previous;
                counter++;
            }
            pointer.previous = null;
        } else {
            System.out.println("Неверный ID");
        }
    }

    public void deleteSecond(){
        head.previous = head.previous.previous;
    }

    public void reverse(){
        //TODO
        if (this.getSize() < 2) {
            this.print();
        } else {
            Unit revList = head;
            Unit pointer = head;
            Unit holder;

            while (pointer != null) {
                holder = pointer.previous;
                pointer.previous = revList;
                revList = pointer;
                pointer = holder;
            }
            print(revList);
        }
    }

    public int getSize(){
        if (head != null) {
            int count = 1;
            Unit unit = head;
            while (unit.previous != null){
                count++;
                unit = unit.previous;
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
        Unit pointer = head;
        int counter = 1;
        boolean isFirst = true;
        String res = "";
        while (pointer.previous != null){
            if (pointer.info == info) {
                if (isFirst) {
                    isFirst = false;
                    res = "" + counter;
                } else {
                    res += ", " + counter;
                }
            }
            counter++;
            pointer = pointer.previous;
        }
        System.out.println(res);
    }

    public int getInfo(int id){
        try{
            if (id > 0 && id <= this.getSize()){
                Unit pointer = head;
                int counter = 1;
                while (counter != id){
                    pointer = pointer.previous;
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
            Unit pointer = head;
            Unit firstElem = null;
            Unit secondElem = null;

            while (pointer != null){
                if (counter == id1){
                    firstElem = pointer;
                }
                if (counter == id2) {
                    secondElem = pointer;
                }
                pointer = pointer.previous;
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
    private int getSize(Unit head){
        int count = 0;
        Unit unit = head;
        while (unit.previous != null){
            count++;
            unit = unit.previous;
        }
        return ++count;
    }

    private void print(Unit head){
        Unit unit = head;

        if (this.isEmpty()) {
            System.out.println("Пустой список.");
        } else {
            System.out.print(unit.info);
            unit = unit.previous;
            while (unit != null){
                System.out.print(" -> " + unit.info);
                unit = unit.previous;
            }
            System.out.println();
        }
    }
}