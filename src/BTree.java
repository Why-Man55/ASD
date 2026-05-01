import java.util.*;

class BTree {
    private BTreeUnit root;
    private int t;

    public BTree(int t) {
        this.t = t;
    }

    // ===== SEARCH =====
    public boolean search(int k) {
        return search(root, k);
    }

    private boolean search(BTreeUnit n, int k) {
        if (n == null) return false;

        int i = 0;
        while (i < n.keys.size() && k > n.keys.get(i)) {
            i++;
        }

        if (i < n.keys.size() && k == n.keys.get(i)) return true;
        if (n.leaf) return false;

        return search(n.children.get(i), k);
    }

    // ===== INSERT =====
    public void insert(int k) {
        if (root == null) {
            root = new BTreeUnit(true);
            root.keys.add(k);
            return;
        }

        if (root.keys.size() == 2 * t - 1) {
            BTreeUnit s = new BTreeUnit(false);
            s.children.add(root);
            split(s, 0);
            root = s;
        }

        insertNonFull(root, k);
    }

    private void insertNonFull(BTreeUnit n, int k) {
        int i = n.keys.size() - 1;

        if (n.leaf) {
            n.keys.add(0);
            while (i >= 0 && n.keys.get(i) > k) {
                n.keys.set(i + 1, n.keys.get(i));
                i--;
            }
            n.keys.set(i + 1, k);
        } else {
            while (i >= 0 && n.keys.get(i) > k) i--;
            i++;

            if (n.children.get(i).keys.size() == 2 * t - 1) {
                split(n, i);
                if (k > n.keys.get(i)) i++;
            }

            insertNonFull(n.children.get(i), k);
        }
    }

    private void split(BTreeUnit p, int i) {
        BTreeUnit y = p.children.get(i);
        BTreeUnit z = new BTreeUnit(y.leaf);

        for (int j = 0; j < t - 1; j++)
            z.keys.add(y.keys.remove(t));

        if (!y.leaf)
            for (int j = 0; j < t; j++)
                z.children.add(y.children.remove(t));

        p.children.add(i + 1, z);
        p.keys.add(i, y.keys.remove(t - 1));
    }

    // ===== DELETE (упрощённый, но рабочий) =====
    public void delete(int k) {
        delete(root, k);

        if (root != null && root.keys.isEmpty())
            root = root.leaf ? null : root.children.getFirst();
    }

    private void delete(BTreeUnit n, int k) {
        int i = 0;
        while (i < n.keys.size() && k > n.keys.get(i)) i++;

        // нашли ключ
        if (i < n.keys.size() && n.keys.get(i) == k) {
            if (n.leaf) {
                n.keys.remove(i);
            } else {
                BTreeUnit left = n.children.get(i);
                BTreeUnit right = n.children.get(i + 1);

                if (left.keys.size() >= t) {
                    int pred = getMax(left);
                    n.keys.set(i, pred);
                    delete(left, pred);
                } else if (right.keys.size() >= t) {
                    int succ = getMin(right);
                    n.keys.set(i, succ);
                    delete(right, succ);
                } else {
                    merge(n, i);
                    delete(left, k);
                }
            }
        } else {
            if (n.leaf) return;

            BTreeUnit child = n.children.get(i);

            if (child.keys.size() < t) {
                if (i > 0 && n.children.get(i - 1).keys.size() >= t)
                    borrowLeft(n, i);
                else if (i < n.children.size() - 1 && n.children.get(i + 1).keys.size() >= t)
                    borrowRight(n, i);
                else {
                    if (i < n.children.size() - 1) merge(n, i);
                    else merge(n, i - 1);
                }
            }

            delete(n.children.get(i < n.children.size() ? i : i - 1), k);
        }
    }

    private int getMax(BTreeUnit n) {
        while (!n.leaf) n = n.children.getLast();
        return n.keys.getLast();
    }

    private int getMin(BTreeUnit n) {
        while (!n.leaf) n = n.children.getFirst();
        return n.keys.getFirst();
    }

    private void borrowLeft(BTreeUnit p, int i) {
        BTreeUnit c = p.children.get(i);
        BTreeUnit l = p.children.get(i - 1);

        c.keys.addFirst(p.keys.get(i - 1));
        if (!c.leaf)
            c.children.addFirst(l.children.removeLast());

        p.keys.set(i - 1, l.keys.removeLast());
    }

    private void borrowRight(BTreeUnit p, int i) {
        BTreeUnit c = p.children.get(i);
        BTreeUnit r = p.children.get(i + 1);

        c.keys.add(p.keys.get(i));
        if (!c.leaf)
            c.children.add(r.children.removeFirst());

        p.keys.set(i, r.keys.removeFirst());
    }

    private void merge(BTreeUnit p, int i) {
        BTreeUnit c = p.children.get(i);
        BTreeUnit r = p.children.get(i + 1);

        c.keys.add(p.keys.remove(i));
        c.keys.addAll(r.keys);
        if (!c.leaf) c.children.addAll(r.children);

        p.children.remove(i + 1);
    }

    public void lengthRun() {
        lengthRun(root);
        System.out.println();
    }

    private void lengthRun(BTreeUnit n) {
        if (n == null) return;

        for (int i = 0; i < n.keys.size(); i++) {
            if (!n.leaf) lengthRun(n.children.get(i));
            System.out.print(n.keys.get(i) + " ");
        }
        if (!n.leaf) lengthRun(n.children.get(n.keys.size()));
    }

    public void widthRun() {
        if (root == null) return;

        Queue<BTreeUnit> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            BTreeUnit n = q.poll();
            System.out.print(n.keys + " \n");

            if (!n.leaf) q.addAll(n.children);
        }
        System.out.println();
    }
}
