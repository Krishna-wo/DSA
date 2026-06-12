/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;

        if (p.val < root.val && q.val < root.val)
            return lowestCommonAncestor(root.left, p, q);
            // BOTH are smaller → LCA is somewhere in left subtree
            // current root cannot be LCA

        if (p.val > root.val && q.val > root.val)
            return lowestCommonAncestor(root.right, p, q);
            // BOTH are larger → LCA is somewhere in right subtree
            // current root cannot be LCA

        return root;
        // they SPLIT here — one is left, one is right (or root == p or root == q)
        // this node is the lowest point where both paths diverge → it IS the LCA
    }
}