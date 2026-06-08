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
     private String smallestString = "~"; // '~' has a higher ASCII value than any lowercase letter like a max value 
    public String smallestFromLeaf(TreeNode root) {
        f(root,"");//root and string 
        return smallestString;
    }
    void f(TreeNode node ,String curr){
        if(node==null)  return;
        char c=(char)(node.val +'a');
        String neww = c+curr;
        if (node.left == null && node.right == null) {
            if (neww.compareTo(smallestString) < 0) {
                smallestString = neww;
            }
            return;
        }
        f(node.left,neww);
        f(node.right,neww);
    }
}


  