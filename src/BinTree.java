public class BinTree {
    BinTreeNode root;

    public void add(int key, String info){
        BinTreeNode newNode = new BinTreeNode(key, info);
        if (root == null){
            root = newNode;
        } else {
            add(root, newNode);
        }
    }
    private void add(BinTreeNode parent, BinTreeNode newNode){
        if (newNode.key > parent.key){
            if (parent.left != null){
                add(parent.left, newNode);
            } else {
                parent.left = newNode;
            }
        } else {
            if (parent.right != null){
                add(parent.right, newNode);
            } else {
                parent.right = newNode;
            }
        }
    }
}
