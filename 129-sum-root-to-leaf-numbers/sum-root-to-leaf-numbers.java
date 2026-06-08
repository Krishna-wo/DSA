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
 // pre order traversl 
 // so 2 funtioon call at last .left adn .right and otehr logic abou but it want toal value sum that cam then we can do sum of left adn right in end 
class Solution {
    public int sumNumbers(TreeNode root) {
        return f(root,0);
    }
    int f(TreeNode root,int i){
        if(root==null) return 0;
        i=i*10 + root.val;
        if(root.left==null && root.right==null) return i;
        return f(root.left,i)+f(root.right,i);
    }
}