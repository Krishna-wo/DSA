class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        return f(head, null);
    }

    TreeNode f(ListNode head, ListNode tail) {
        if (head == tail) return null;          // base case

        ListNode m = mid(head, tail);
        TreeNode root = new TreeNode(m.val);
        root.left  = f(head, m);               // left sublist [head, m)
        root.right = f(m.next, tail);           // right sublist [m+1, tail)
        return root;
    }

    ListNode mid(ListNode head, ListNode tail) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != tail && fast.next != tail) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;                            // slow IS the mid
    }
}