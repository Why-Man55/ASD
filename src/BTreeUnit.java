import java.util.ArrayList;

class BTreeUnit {
    ArrayList<Integer> keys = new ArrayList<>();
    ArrayList<BTreeUnit> children = new ArrayList<>();
    boolean leaf;

    BTreeUnit(boolean leaf) {
        this.leaf = leaf;
    }
}