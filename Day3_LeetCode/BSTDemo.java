// BSTDemo.java
// Complete BST implementation with insert, search, delete and inorder traversal.

public class BSTDemo {

    // Node class
    static class Node {
        int val;
        Node left, right;

        Node(int val) {
            this.val = val;
            left = right = null;
        }
    }

    // BST class with core operations
    static class BST {
        Node root;

        BST() {
            root = null;
        }

        // Insert key into BST (returns new root for recursion)
        public Node insert(Node node, int key) {
            if (node == null) {
                return new Node(key);
            }

            if (key < node.val) {
                node.left = insert(node.left, key);
            } else if (key > node.val) {
                node.right = insert(node.right, key);
            }
            // if key == node.val, we do nothing (no duplicates). Change if you allow duplicates.
            return node;
        }

        // Public wrapper for insert
        public void insert(int key) {
            root = insert(root, key);
        }

        // Search key in BST (returns Node if found, else null)
        public Node search(Node node, int key) {
            if (node == null) return null;
            if (key == node.val) return node;
            if (key < node.val) return search(node.left, key);
            else return search(node.right, key);
        }

        // Public wrapper for search (returns boolean)
        public boolean contains(int key) {
            return search(root, key) != null;
        }

        // Find minimum node in subtree rooted at node
        private Node findMin(Node node) {
            if (node == null) return null;
            while (node.left != null) node = node.left;
            return node;
        }

        // Delete a node with given key and return new subtree root
        public Node delete(Node node, int key) {
            if (node == null) return null;

            if (key < node.val) {
                node.left = delete(node.left, key);
            } else if (key > node.val) {
                node.right = delete(node.right, key);
            } else {
                // node to be deleted found

                // Case 1: no child or one child
                if (node.left == null) {
                    return node.right; // could be null or right child
                } else if (node.right == null) {
                    return node.left;
                }

                // Case 3: two children
                // Replace node's value with inorder successor (smallest in right subtree)
                Node successor = findMin(node.right);
                node.val = successor.val;
                // Delete the inorder successor in right subtree
                node.right = delete(node.right, successor.val);
            }
            return node;
        }

        // Public wrapper for delete
        public void delete(int key) {
            root = delete(root, key);
        }

        // Inorder traversal (prints sorted order for BST)
        public void inorder(Node node) {
            if (node == null) return;
            inorder(node.left);
            System.out.print(node.val + " ");
            inorder(node.right);
        }

        public void printInorder() {
            inorder(root);
            System.out.println();
        }
    }

    // Demo / Testing
    public static void main(String[] args) {
        BST tree = new BST();

        // Insert nodes: example tree
        int[] keys = {50, 30, 70, 20, 40, 60, 80};
        for (int k : keys) tree.insert(k);

        System.out.print("Inorder after inserts (should be sorted): ");
        tree.printInorder(); // 20 30 40 50 60 70 80

        // Search examples
        int s1 = 40, s2 = 99;
        System.out.println("Contains " + s1 + "? " + tree.contains(s1)); // true
        System.out.println("Contains " + s2 + "? " + tree.contains(s2)); // false

        // Delete a leaf
        tree.delete(20);
        System.out.print("Inorder after deleting 20 (leaf): ");
        tree.printInorder(); // 30 40 50 60 70 80

        // Delete node with one child
        tree.delete(30); // 30 has right child 40
        System.out.print("Inorder after deleting 30 (one child): ");
        tree.printInorder(); // 40 50 60 70 80

        // Delete node with two children
        tree.delete(50); // root with two children
        System.out.print("Inorder after deleting 50 (two children): ");
        tree.printInorder(); // 40 60 70 80

        // Insert duplicate test (no-op in this implementation)
        tree.insert(60);
        System.out.print("Inorder after reinserting 60 (duplicate no-op): ");
        tree.printInorder(); // unchanged
    }
}
