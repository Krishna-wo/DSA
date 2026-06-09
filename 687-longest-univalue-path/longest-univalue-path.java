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
    int maxl=0;
    public int longestUnivaluePath(TreeNode root) {
       dfs(root);
       return maxl;
    }
    int dfs(TreeNode node){
        if(node==null) return 0;
        int larm  =dfs(node.left);
        int rarm=dfs(node.right);
        if(node.left!=null && node.left.val==node.val){
            larm+=1;
        }else{
            larm=0;
        }
        if(node.right!=null && node.right.val==node.val){
            rarm+=1;
        }else{
            rarm=0;
        }
        maxl=Math.max(maxl,larm+rarm);
        return Math.max(larm, rarm);
    }

}

