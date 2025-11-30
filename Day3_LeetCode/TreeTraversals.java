import java.util.Stack;

class Node {
    int data;
    Node left, right;

    Node(int val) {
        data = val;
        left = right = null;
    }
}

public class TreeTraversals {

    // ------------------------------------------------------------
    // 1️⃣ ITERATIVE INORDER (Left → Node → Right)
    // ------------------------------------------------------------
    static void inorderIterative(Node root) {
        Stack<Node> stack = new Stack<>();
        Node curr = root;

        while (curr != null || !stack.isEmpty()) {

            // Go all the way LEFT
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            // Pop the leftmost
            curr = stack.pop();

            System.out.print(curr.data + " ");

            // Go RIGHT after visiting node
            curr = curr.right;
        }
    }

    // ------------------------------------------------------------
    // 2️⃣ ITERATIVE PREORDER (Node → Left → Right)
    // ------------------------------------------------------------
    static void preorderIterative(Node root) {
        if (root == null)
            return;

        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node curr = stack.pop();
            System.out.print(curr.data + " ");

            // Push RIGHT first → so LEFT is processed first
            if (curr.right != null)
                stack.push(curr.right);

            if (curr.left != null)
                stack.push(curr.left);
        }
    }

    // ------------------------------------------------------------
    // 3️⃣ ITERATIVE POSTORDER (Left → Right → Node)
    // ⭐ HARD VERSION — ONE STACK ⭐
    // ------------------------------------------------------------
    static void postorderIterative(Node root) {
        Stack<Node> stack = new Stack<>();
        Node curr = root;
        Node lastVisited = null;

        while (curr != null || !stack.isEmpty()) {

            // Push all left children
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            Node peekNode = stack.peek();

            // If right child exists AND isn't processed yet → go right
            if (peekNode.right != null && lastVisited != peekNode.right) {
                curr = peekNode.right;
            } else {
                // Visit the node
                System.out.print(peekNode.data + " ");
                lastVisited = stack.pop();
            }
        }
    }

    // ------------------------------------------------------------
    // MAIN → Build a sample tree and run all traversals
    // ------------------------------------------------------------
    public static void main(String[] args) {

        /*
                 1
               /   \
              2     3
             / \   / \
            4   5 6   7
        */

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        System.out.print("Inorder   : ");
        inorderIterative(root);

        System.out.print("\nPreorder  : ");
        preorderIterative(root);

        System.out.print("\nPostorder : ");
        postorderIterative(root);

        System.out.println();
    }
}
