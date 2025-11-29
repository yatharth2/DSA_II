import java.util.*;

/**
 * AVLTree.java
 * Complete AVL Tree implementation in Java with insert, delete, search and traversals.
 *
 * Usage: compile and run. The main() demonstrates insertion, search, deletion and traversals.
 */
public class AVLTree {

    // Node class
    static class Node {
        int key;
        int height;
        Node left, right;

        Node(int d) {
            key = d;
            height = 1; // new node is initially added at leaf
            left = right = null;
        }
    }

    private Node root;

    // Utility: get height of a node
    private int height(Node n) {
        return n == null ? 0 : n.height;
    }

    // Utility: max of two ints
    private int max(int a, int b) {
        return (a > b) ? a : b;
    }

    // Right rotate subtree rooted with y
    //        y                              x
    //       / \   Right Rotate (y)         /  \
    //      x   T3   - - - - - - - >       T1   y
    //     / \                                  / \
    //   T1  T2                               T2  T3
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        // Return new root
        return x;
    }

    // Left rotate subtree rooted with x
    //    x                              y
    //   / \     Left Rotate(x)         /  \
    //  T1  y   - - - - - - - ->       x   T3
    //     / \                        / \
    //   T2  T3                     T1  T2
    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        // Update heights
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        // Return new root
        return y;
    }

    // Get balance factor of node N
    private int getBalance(Node N) {
        if (N == null)
            return 0;
        return height(N.left) - height(N.right);
    }

    // Insert a key into AVL tree and return new root of subtree
    public void insert(int key) {
        root = insert(root, key);
    }

    private Node insert(Node node, int key) {
        // 1. Perform the normal BST insertion
        if (node == null)
            return (new Node(key));

        if (key < node.key)
            node.left = insert(node.left, key);
        else if (key > node.key)
            node.right = insert(node.right, key);
        else // Duplicate keys not allowed
            return node;

        // 2. Update height of this ancestor node
        node.height = 1 + max(height(node.left), height(node.right));

        // 3. Get the balance factor to check whether this node became unbalanced
        int balance = getBalance(node);

        // If unbalanced, there are 4 cases

        // Left Left Case
        if (balance > 1 && key < node.left.key)
            return rightRotate(node);

        // Right Right Case
        if (balance < -1 && key > node.right.key)
            return leftRotate(node);

        // Left Right Case
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        // return the unchanged node pointer
        return node;
    }

    // Find node with minimum key value found in that tree
    private Node minValueNode(Node node) {
        Node current = node;
        /* loop down to find the leftmost leaf */
        while (current.left != null)
            current = current.left;
        return current;
    }

    // Delete a key and return new root
    public void delete(int key) {
        root = deleteNode(root, key);
    }

    private Node deleteNode(Node root, int key) {
        // STEP 1: Perform standard BST delete
        if (root == null)
            return root;

        // If the key to be deleted is smaller than the root's key,
        // then it lies in left subtree
        if (key < root.key)
            root.left = deleteNode(root.left, key);

            // If the key to be deleted is greater than the root's key,
            // then it lies in right subtree
        else if (key > root.key)
            root.right = deleteNode(root.right, key);

            // if key is same as root's key, then this is the node to be deleted
        else {
            // node with only one child or no child
            if ((root.left == null) || (root.right == null)) {
                Node temp = null;
                if (temp == root.left)
                    temp = root.right;
                else
                    temp = root.left;

                // No child case
                if (temp == null) {
                    temp = root;
                    root = null;
                } else   // One child case
                    root = temp; // Copy the contents of the non-empty child
            } else {
                // node with two children: Get the inorder successor (smallest in the right subtree)
                Node temp = minValueNode(root.right);

                // Copy the inorder successor's data to this node
                root.key = temp.key;

                // Delete the inorder successor
                root.right = deleteNode(root.right, temp.key);
            }
        }

        // If the tree had only one node then return
        if (root == null)
            return root;

        // STEP 2: Update height of the current node
        root.height = max(height(root.left), height(root.right)) + 1;

        // STEP 3: Get the balance factor
        int balance = getBalance(root);

        // If this node becomes unbalanced, then there are 4 cases

        // Left Left Case
        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRotate(root);

        // Left Right Case
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // Right Right Case
        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRotate(root);

        // Right Left Case
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    // Search for a key in the AVL tree (returns true if found)
    public boolean search(int key) {
        return search(root, key);
    }

    private boolean search(Node node, int key) {
        if (node == null)
            return false;
        if (key == node.key)
            return true;
        else if (key < node.key)
            return search(node.left, key);
        else
            return search(node.right, key);
    }

    // Inorder traversal (Left, Root, Right)
    public void inorder() {
        inorder(root);
        System.out.println();
    }

    private void inorder(Node node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.key + " ");
            inorder(node.right);
        }
    }

    // Preorder traversal (Root, Left, Right)
    public void preorder() {
        preorder(root);
        System.out.println();
    }

    private void preorder(Node node) {
        if (node != null) {
            System.out.print(node.key + " ");
            preorder(node.left);
            preorder(node.right);
        }
    }

    // Postorder traversal (Left, Right, Root)
    public void postorder() {
        postorder(root);
        System.out.println();
    }

    private void postorder(Node node) {
        if (node != null) {
            postorder(node.left);
            postorder(node.right);
            System.out.print(node.key + " ");
        }
    }

    // Level order traversal (BFS)
    public void levelOrder() {
        if (root == null)
            return;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            Node node = q.poll();
            System.out.print(node.key + " ");
            if (node.left != null)
                q.add(node.left);
            if (node.right != null)
                q.add(node.right);
        }
        System.out.println();
    }

    // Utility to print inorder along with node heights (for debugging)
    public void inorderWithHeights() {
        inorderWithHeights(root);
        System.out.println();
    }

    private void inorderWithHeights(Node node) {
        if (node != null) {
            inorderWithHeights(node.left);
            System.out.print("(" + node.key + ", h=" + node.height + ") ");
            inorderWithHeights(node.right);
        }
    }

    // Main for demonstration
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        /* Example insertions */
        int[] keys = { 10, 20, 30, 40, 50, 25 };
        for (int k : keys) {
            tree.insert(k);
        }

        System.out.println("Inorder traversal of constructed AVL tree is:");
        tree.inorder();            // Should be sorted: 10 20 25 30 40 50

        System.out.println("Preorder traversal:");
        tree.preorder();

        System.out.println("Postorder traversal:");
        tree.postorder();

        System.out.println("Level order traversal:");
        tree.levelOrder();

        System.out.println("Inorder with node heights:");
        tree.inorderWithHeights();

        // Search demo
        int searchKey = 25;
        System.out.println("Search " + searchKey + ": " + (tree.search(searchKey) ? "Found" : "Not Found"));

        // Delete demo
        System.out.println("Deleting 40");
        tree.delete(40);

        System.out.println("Inorder after deletion of 40:");
        tree.inorder();

        System.out.println("Deleting 30");
        tree.delete(30);

        System.out.println("Inorder after deletion of 30:");
        tree.inorder();

        // Final tree
        System.out.println("Final level order:");
        tree.levelOrder();
    }
}
