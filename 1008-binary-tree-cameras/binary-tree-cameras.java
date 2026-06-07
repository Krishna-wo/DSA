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
//  The Universal Tree Traversal Cheat SheetWhenever you read a new tree problem, use this exact rule of thumb to know which traversal to use instantly
// Post-Order (Left, Right, Node) $\rightarrow$ Bottom-Up DPUse it when: The parent needs information from the children to solve its
//  problem.Examples: Height of a tree, Diameter, Maximum Path Sum, Placing Cameras.
//  Pre-Order (Node, Left, Right) $\rightarrow$ Top-Down ExplorationUse it when: The parent needs to pass information down to the 
//  children.Examples: Backtracking paths, making a copy/clone of a tree, passing a running sum down.
//  In-Order (Left, Node, Right) $\rightarrow$ Sorted OrderUse it when: You are dealing with a Binary Search Tree (BST).
//  Examples: Because a BST has smaller values on the left and larger on the right, In-Order traversal will naturally visit the nodes in perfectly sorted numerical order!
// 2 means covered by other cenra 
// 1 means just cemra on itself
// 0 means need cemra 
class Solution {
    int count=0;
    public int minCameraCover(TreeNode root) {
        int rootState = dfs(root);
        if(rootState==0) count++;
        return count;
    }
    int dfs(TreeNode root){
        if(root==null) return 2; // means covered no need to pust cemra on leaf node 
        int left=dfs(root.left);
        int right =dfs(root.right);
        // Subproblems: Go all the way to the bottom leaves first
        if(left==0 || right==0){
            count++;
            return 1;
        }
        // Transition 2: A child has a camera, so I am automatically covered.
        if(left==1 || right==1){
            return 2;//Return State 2: "I am covered"
        }
    
       // Transition 3: Both children are covered, but I am not. I need help.
        // (This naturally triggers all the leaves to return 0 because their null children return 2)
        return 0; // Return State 0: "I need a camera"
    }
    
}