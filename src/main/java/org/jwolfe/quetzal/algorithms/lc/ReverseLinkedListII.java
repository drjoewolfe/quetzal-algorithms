package org.jwolfe.quetzal.algorithms.lc;

public class ReverseLinkedListII {
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
    class Solution {
        public ListNode reverseBetween(ListNode head, int m, int n) {
            if(head == null) {
                return head;
            }

            ListNode anchor = new ListNode(-1);
            anchor.next = head;

            ListNode curr = anchor;
            for(int i = 1; i < m && curr != null; i++) {
                curr = curr.next;
            }

            if(curr == null) {
                return anchor.next;
            }

            ListNode left = curr;
            curr = curr.next;

            ListNode prev = null;
            ListNode next = null;
            ListNode last = curr;

            for(int i = m; i <= n && curr != null; i++) {
                next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }

            left.next = prev;
            last.next = curr;

            return anchor.next;
        }
    }

    class Solution_Correct_3 {
        public ListNode reverseBetween(ListNode head, int m, int n) {
            if(head == null) {
                return head;
            }

            ListNode dummy = new ListNode();
            dummy.next = head;

            int counter = 0;
            ListNode node = dummy;
            while(node != null && counter < m - 1) {
                node = node.next;
                counter++;
            }

            ListNode left = node;

            node = node.next;
            if(node == null) {
                return dummy.next;
            }

            ListNode last = node;
            ListNode previous = null;
            ListNode current = node;
            ListNode next = null;
            while(current != null && counter < n) {
                next = current.next;
                current.next = previous;

                previous = current;
                current = next;
                counter++;
            }

            left.next = previous;
            last.next = current;

            return dummy.next;
        }
    }

    class Solution_Correct_2 {
        public ListNode reverseBetween(ListNode head, int left, int right) {
            if(head == null) {
                return head;
            }

            ListNode anchor = new ListNode(-1);
            anchor.next = head;

            ListNode curr = anchor;
            ListNode leftAnchor = null;
            for(int i = 1; i <= left && curr != null; i++) {
                leftAnchor = curr;
                curr = curr.next;
            }

            ListNode last = curr;

            ListNode prev = null;
            ListNode next = null;

            for(int i = left; i <= right && curr != null; i++) {
                next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }

            leftAnchor.next = prev;
            last.next = next;

            return anchor.next;
        }
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}

//    92. Reverse Linked List II
//    Medium
//    Given the head of a singly linked list and two integers left and right where left <= right, reverse the nodes of the list from position left to position right, and return the reversed list.
//
//
//
//    Example 1:
//
//
//    Input: head = [1,2,3,4,5], left = 2, right = 4
//    Output: [1,4,3,2,5]
//    Example 2:
//
//    Input: head = [5], left = 1, right = 1
//    Output: [5]
//
//
//    Constraints:
//
//    The number of nodes in the list is n.
//    1 <= n <= 500
//    -500 <= Node.val <= 500
//    1 <= left <= right <= n
//
//
//    Follow up: Could you do it in one pass?