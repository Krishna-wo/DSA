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
class Solution {
    // Declare the pointers as instance variables inside the class
    private TreeNode first;
    private TreeNode prev;
    private TreeNode middle;
    private TreeNode last;

    public void recoverTree(TreeNode root) {
        // Initialize the pointers
        first = middle = last = null;
        prev = new TreeNode(Integer.MIN_VALUE);
        
        // Run the traversal
        inorder(root);
        
        // Fix the swapped nodes
        if(first != null && last != null) {
            int t = first.val;
            first.val = last.val;
            last.val = t;
        }
        else if(first != null && middle != null) {
            int t = first.val;
            first.val = middle.val;
            middle.val = t;
        }
    }
    
    private void inorder(TreeNode root) {
        if(root == null) return;

        inorder(root.left);

        if (prev != null && (root.val < prev.val)) {
            // If this is the first violation, mark these two nodes as 'first' and 'middle'
            if (first == null) {
                first = prev;
                middle = root;
            }
            // If this is the second violation, mark this node as 'last'
            else {
                last = root;
            }
        }

        // Mark this node as previous before moving to the right child
        prev = root;
        inorder(root.right);
    }
}