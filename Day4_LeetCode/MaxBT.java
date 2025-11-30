class Node {
    int data;
    Node left, right;

    Node(int item) {
        data = item;
        left = right = null;
    }
}

public class MaxBT {
    Node root;

    void inorder(Node node, List<Integer> sortedInorder) {
        // Base Case
        if (node == null) {
            return;
        }

        // Traverse left subtree
        inorder(node.left, sortedInorder);

        // Store the current node's data
        sortedInorder.add(node.data);

        // Traverse right subtree
        inorder(node.right, sortedInorder);
    }

    int maxValue(Node node) {
        if (node == null) {
            return -1;
        }

        // Using a list to hold inorder elements
        List<Integer> sortedInorder = new ArrayList<>();

        // Call the recursive inorder function
        inorder(node, sortedInorder);

        // Return the last element, which is the maximum
        return sortedInorder.get(sortedInorder.size() - 1);
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        // Representation of input binary search tree
        //        5
        //       / \
        //      4   6
        //     /     \
        //    3       7
        //   /  
        //  1
        tree.root = new Node(5);
        tree.root.left = new Node(4);
        tree.root.right = new Node(6);
        tree.root.left.left = new Node(3);
        tree.root.right.right = new Node(7);
        tree.root.left.left.left = new Node(1);

        System.out.println(tree.maxValue(tree.root));
    }
}