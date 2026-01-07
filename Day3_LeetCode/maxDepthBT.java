import java.util.*;

class Node {
    int data;
    Node left, right;
    Node(int val) { data = val; left = right = null; }
}

public class maxDepthBT {

    // -----------------------
    // 1) Recursive (DFS)
    // -----------------------
    // Returns maximum depth (height) of the tree.
    static int maxDepth(Node root) {
        // base case: empty tree has height 0
        if (root == null) return 0;

        // compute depth of left and right subtrees
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);

        // current node contributes 1 + max(child depths)
        return 1 + Math.max(leftDepth, rightDepth);
    }

    // -----------------------
    // 2) Iterative (BFS / level-order)
    // -----------------------
    // Uses a queue and counts levels.
    static int maxDepthIterative(Node root) {
        if (root == null) return 0;

        Queue<Node> q = new LinkedList<>();
        q.add(root);
        int depth = 0;

        while (!q.isEmpty()) {
            int levelSize = q.size();          // number of nodes at current level
            for (int i = 0; i < levelSize; i++) {
                Node cur = q.poll();
                if (cur.left != null) q.add(cur.left);
                if (cur.right != null) q.add(cur.right);
            }
            depth++;                           // finished one level
        }

        return depth;
    }

    // -----------------------
    // Simple main to test
    // -----------------------
    public static void main(String[] args) {
        /*
               5
              / \
             3   8
            / \   \
           1   4   9
        */
        Node root = new Node(5);
        root.left = new Node(3);
        root.right = new Node(8);
        root.left.left = new Node(1);
        root.left.right = new Node(4);
        root.right.right = new Node(9);

        System.out.println("Recursive max depth: " + maxDepth(root));            // expected 3
        System.out.println("Iterative max depth: " + maxDepthIterative(root));  // expected 3
    }