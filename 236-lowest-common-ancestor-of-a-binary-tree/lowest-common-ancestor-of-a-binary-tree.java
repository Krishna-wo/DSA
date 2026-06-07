/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// class Solution {
//     boolean findPath(TreeNode root, TreeNode target, List<TreeNode> path) {
//         if(root==null) return false;
//         path.add(root);
//         if(root == target)return true;
//         if(findPath(root.left,target,path) || findPath(root.right, target, path)) {
//             return true;
//         }
//         path.remove(path.size() - 1);
//         return false;
//     }
//     public TreeNode lowestCommonAncestor(TreeNode root,TreeNode p,TreeNode q){
//         List<TreeNode> path1 = new ArrayList<>();
//         List<TreeNode> path2 = new ArrayList<>();
//         findPath(root,p,path1);
//         findPath(root,q,path2);
//         TreeNode lca = null;
//         int n = Math.min(path1.size(), path2.size());
//         for(int i =0;i< n;i++) {
//             if (path1.get(i) == path2.get(i))
//                 lca = path1.get(i);
//             else
//                 break;
//         }
//         return lca;
//     }
// }

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Base Cases
        if (root == null || root == p || root == q) {
            return root;
        }
        //  Subproblems (Post-Order Traversal: Left, Right)
        TreeNode leftResult = lowestCommonAncestor(root.left, p, q);
        TreeNode rightResult = lowestCommonAncestor(root.right, p, q);

        // 3. DP Transition Logic (Node Phase)
        // If both sides found a target, this current node is the LCA split point
        // dono se aa raha hai node so current node return kr de 
        if (leftResult != null && rightResult != null) {
            return root;
        }

        // Otherwise, return whichever side actually found a node up to the parent
        // ese soch kya left se aa rhar hai node yaa right se aa raha h node w
        return (leftResult != null) ? leftResult : rightResult;
    }
}