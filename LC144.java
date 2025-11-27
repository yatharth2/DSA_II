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

//144. Binary Tree Preorder Traversal

class LC144 {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        preOrder(node, res);
        return res;
    }

    private void preOrder(TreeNode node, List<Integer> res){
        if (node == null)
            return;
        res.add(node.val);
        preOrder(node.left, res);
        preOrder(node.right, res);
    }
}