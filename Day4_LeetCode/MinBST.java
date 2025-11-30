import java.util.ArrayList;

// Node structure
class Node {
    int data;
    Node left, right;

    Node(int val) {
        data = val;
        left = right = null;
    }
}

class MinBST {

    // Performs inorder traversal of BST 
    // and stores values in sorted order
    static void inorder(Node root, ArrayList<Integer> sortedInorder) {
        if (root == null) return;
    
        // Traverse left subtree
        inorder(root.left, sortedInorder);
    
        sortedInorder.add(root.data);
    
        // Traverse right subtree
        inorder(root.right, sortedInorder);
    }
    
    // Returns the minimum value in a BST
    static int minValue(Node root) {
        if (root == null) return -1;
    
        ArrayList<Integer> sortedInorder = new ArrayList<>();
    
        // Get all BST values in sorted order
        inorder(root, sortedInorder);
    
        return sortedInorder.get(0);
    }

    public static void main(String[] args) {

//Create BST 

        Node root = new Node(5);
        root.left = new Node(4);
        root.right = new Node(6);
        root.left.left = new Node(3);
        root.right.right = new Node(7);
        root.left.left.left = new Node(1);

        System.out.println(minValue(root));
    }
}
