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
    int[] nums;
    public TreeNode sortedArrayToBST(int[] nums) {
        this.nums=nums;
       return f(0,nums.length-1);
    }
    TreeNode f(int l,int r){
        if(l>r) return null;
        int m=l+(r-l)/2;
        TreeNode root =new TreeNode(nums[m]);
        root.left=f(l,m-1);
        root.right=f(m+1,r);
        return root;
    }
}