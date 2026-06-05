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


// That specific line of code is definitely a mouthful! Nested data structures in Java can look like an intimidating wall of angle brackets, but breaking it down makes it much easier to digest.

// Here is exactly what this data structure is doing and why it is built this way.

// The Big Picture
// In the Vertical Order Traversal problem, you need to group nodes by their x coordinate (columns), then by their y coordinate (rows), and finally sort any overlapping nodes by their integer value.

// This giant nested structure is essentially a 2D coordinate grid that automatically sorts everything for you: map.get(x).get(y) gives you a sorted list of all node values at that exact coordinate.

// Layer-by-Layer Breakdown
// Let's unwrap this type: TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>>

// 1. The Outer TreeMap (The Columns / X-Axis)

// Syntax: TreeMap<Integer, ...>

// What it does: The key is the x coordinate. A TreeMap in Java automatically sorts its keys in ascending order. This guarantees that when you read the final results, you process the columns from the furthest left (e.g., -2) to the furthest right (e.g., +2).

// 2. The Inner TreeMap (The Rows / Y-Axis)

// Syntax: ... TreeMap<Integer, ...>

// What it does: For a specific column x, this map's key is the y coordinate (the depth). It automatically sorts the rows from top to bottom (e.g., row 0, then row 1, then row 2).

// 3. The PriorityQueue (The Overlapping Nodes)

// Syntax: ... PriorityQueue<Integer>

// What it does: The problem states that if two nodes share the exact same row and column, they must be sorted by their value. A PriorityQueue (a min-heap) automatically sorts elements from smallest to largest as you add them.

// Why do we need the if statements?
// Java
// if (!map.containsKey(x)) {
//     map.put(x, new TreeMap<>());
// }

// if (!map.get(x).containsKey(y)) {
//     map.get(x).put(y, new PriorityQueue<>());
// }
// This is a common Java pattern called Lazy Initialization.

// Because map starts entirely empty, you cannot just say map.get(x).get(y).offer(node.val). If the column x or the row y doesn't exist yet, map.get() will return null, and your program will crash with a NullPointerException.

// Think of it like building the containers just before you need them:

// Check the Column: "Does column x exist? No? Create a new TreeMap to hold the rows for this column."

// Check the Row: "Look inside column x. Does row y exist? No? Create a new PriorityQueue to hold the overlapping values for this specific spot."

// Insert the Data: Now that you are 100% sure the containers exist, you can safely insert the node's value.