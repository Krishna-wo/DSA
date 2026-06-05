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
// class Solution {
//     public List<List<Integer>> verticalTraversal(TreeNode root) {
        
//     }
// }
//     static ArrayList<Integer> inOrder(Node root) {
//         ArrayList<Integer> ans = new ArrayList<>();
//         Stack<Node> s = new Stack<>();
//         Node curr = root;
//         while (curr != null || !s.isEmpty()) {

//             while (curr != null) {
//                 s.push(curr);
//                 curr = curr.left;
//             }

//             // Current must be NULL at this point
//             curr = s.pop();
//             ans.add(curr.data);

//             // we have visited the node and its
//             // left subtree. Now, it's right
//             // subtree's turn
//             curr = curr.right;
//         }

//         return ans;
//     }
import java.util.*;

// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Tuple {
    TreeNode node;
    int col; // Horizontal distance (x-axis)
    int row; // Depth (y-axis)

    public Tuple(TreeNode node, int col, int row) {
        this.node = node;
        this.col = col;
        this.row = row;
    }
}

class Solution {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        TreeMap<Integer,TreeMap<Integer,PriorityQueue<Integer>>> map = new TreeMap<>();
        Queue<Tuple>q = new LinkedList<>();
        q.offer(new Tuple(root,0,0));
        while(!q.isEmpty()) {
            Tuple tuple=q.poll();
            TreeNode node=tuple.node;
            int x=tuple.col;
            int y=tuple.row;
            if(!map.containsKey(x)){
                map.put(x,new TreeMap<>());
            }
            if(!map.get(x).containsKey(y)){
                map.get(x).put(y,new PriorityQueue<>());
            }
            map.get(x).get(y).offer(node.val);
            if(node.left != null){
                q.offer(new Tuple(node.left,x-1,y+1));
            }
            if(node.right != null){
                q.offer(new Tuple(node.right,x+1,y+1));
            }
        }
        List<List<Integer>> list = new ArrayList<>();
        // Iterate through the sorted columns
        for (TreeMap<Integer, PriorityQueue<Integer>> ys : map.values()) {
            list.add(new ArrayList<>());
            // Iterate through the sorted rows within the current column
            for (PriorityQueue<Integer> nodes : ys.values()) {
                // Drain the priority queue to maintain sorted order for overlaps
                while (!nodes.isEmpty()) {
                    list.get(list.size() - 1).add(nodes.poll());
                }
            }
        }
        return list;
    }
}