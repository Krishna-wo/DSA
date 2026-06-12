/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
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
    public TreeNode sortedListToBST(ListNode head) {
        ListNode tail=null;
         return f(head,tail);
    }
    TreeNode f(ListNode head,ListNode tail){
         if (head == tail) return null;
        ListNode m=mid(head,tail);
        TreeNode root=new TreeNode(m.val);
        root.left=f(head,m);
        root.right=f(m.next,tail);
        return root;
    }
    ListNode mid(ListNode head ,ListNode tail){
       ListNode slow=head;
       ListNode fast=head;
       while(fast!=tail && fast.next!=tail){
        slow=slow.next;
        fast=fast.next.next;
       }
       return slow;
    }
}