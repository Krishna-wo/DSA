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
    public boolean isCousins(TreeNode root, int x, int y) {
        Queue<TreeNode> q =new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            int s= q.size();
            boolean foundX = false;
            boolean foundY = false;
            for(int i=0;i<s;i++){
                TreeNode n=q.poll();
                if(n.val == x) foundX = true;
                if(n.val == y) foundY = true;
                
                if(n.left!=null) q.add(n.left);
                if(n.right!=null) q.add(n.right);

            }
            if((foundX && foundY) && !isSibling(root, x, y)){
                return true;
            }
        }
        return false;
        
    }

   boolean isSibling(TreeNode root, int x, int y) {
    if(root == null) return false;
    if(root.left != null && root.right != null) {
        int l = root.left.val;
        int r = root.right.val;
        if((l== x && r==y)||(l==y && r == x))
            return true;
    }
    return isSibling(root.left, x, y) ||
           isSibling(root.right, x, y);
}
}