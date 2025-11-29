/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

//98. Validate Binary Search Tree

class LC98 {
    public boolean isValidBST(TreeNode root) {
        return helper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    // node must satisfy: low < node.val < high
    private boolean helper(TreeNode node, long low, long high) {
        if (node == null) return true;
        long v = node.val;
        if (v <= low || v >= high) return false;
        return helper(node.left, low, v) && helper(node.right, v, high);
    }
}
