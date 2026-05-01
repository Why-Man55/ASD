//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        BTree tree = new BTree(3);

        int[] vals = {10, 20, 5, 6, 12, 30, 7, 17};

        for (int v : vals) tree.insert(v);

        System.out.println("DFS:");
        tree.lengthRun();

        System.out.println("BFS:");
        tree.widthRun();

        System.out.println("Search 6: " + tree.search(6));

        tree.delete(6);
        tree.delete(13); // не существует

        System.out.println("После удаления:");
        tree.lengthRun();
    }
}