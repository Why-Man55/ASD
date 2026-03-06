package LinalList;

public class Test1 {
    public static void main(String[] arg){
        LinalList list1 = new LinalList(1);
        LinalList list2 = new LinalList(10);
        LinalList list3 = new LinalList(new int[]{20, 200});

        list1.addStart(1);
        list1.addStart(2);
        list1.addStart(3);
        list1.addStart(2);
        list1.addStart(5);
        list1.addAt(10, 3);
        list1.print();
        list1.findInfo(2);
        System.out.println(list1.getSize());
        list1.bubbleSort();
        System.out.println();

        list2.deleteFirst();
        list2.print();
        System.out.println();

        list3.print();
        list3.deleteSecond();
        list3.print();
        list3.addStart(100);
        list3.print();

        //Errors
        list1.addAt(10, 20);
        list2.deleteAt(50);
        list3.changePositions(-1, 1);
        list1.getInfo(-15);
    }
}