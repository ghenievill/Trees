class TreeNode {
    int data;
    TreeNode left, right;

    public TreeNode(int data) {
        this.data = data;
        left = right = null;
    }
}

class BinaryTree {
    TreeNode root;

    public BinaryTree() {
        root = null;
    }

    public void insert(int data) {
        TreeNode newNode = new TreeNode(data);
        if (root == null) {
            root = newNode;
            return;
        }
        TreeNode current = root;
        TreeNode parent = null;
        while (true) {
            parent = current;
            if (data < current.data) {
                current = current.left;
                if (current == null) {
                    parent.left = newNode;
                    return;
                }
            } else {
                current = current.right;
                if (current == null) {
                    parent.right = newNode;
                    return;
                }
            }
        }
    }

    public void inOrder(TreeNode node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.data + " ");
            inOrder(node.right);
        }
    }

    public void preOrder(TreeNode node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    public void postOrder(TreeNode node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.print(node.data + " ");
        }
    }

    public boolean search(int data) {
        TreeNode current = root;
        while (current != null) {
            if (current.data == data) {
                return true;
            }
            current = data < current.data ? current.left : current.right;
        }
        return false;
    }
}

public class Trees {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        tree.insert(10);
        tree.insert(15);
        tree.insert(5);
        tree.insert(20);
        tree.insert(25);
        tree.insert(30);
        tree.insert(35);

        System.out.println("In-order traversal:");
        tree.inOrder(tree.root);

        System.out.println("\nPre-order traversal:");
        tree.preOrder(tree.root);

        System.out.println("\nPost-order traversal:");
        tree.postOrder(tree.root);

        int key = 33;
        int key2 = 5;

        System.out.println("\n\nSearching for " + key + ": " + (tree.search(key) ? "Found" : "Not Found"));
        System.out.println("Searching for " + key2 + ": " + (tree.search(key2) ? "Found" : "Not Found"));
    }
}
