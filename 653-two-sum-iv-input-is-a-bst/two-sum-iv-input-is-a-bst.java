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
    public boolean findTarget(TreeNode root, int k) {
        List<Integer> nums = new ArrayList<>();
        preorder(root, nums);
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            int complement = k - num;
            if (seen.contains(complement)) {
                return true;
            }
            seen.add(num);
        }
        
        return false;
    }
    
    private void preorder(TreeNode node, List<Integer> nums) {
        if (node == null) {
            return;
        }
        nums.add(node.val); 
        preorder(node.left, nums); 
        preorder(node.right, nums); 
    }
}