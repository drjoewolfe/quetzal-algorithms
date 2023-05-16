package org.jwolfe.quetzal.algorithms.lc;

public class SwapNodesInPairs {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     */
    class Solution {
        public ListNode swapPairs(ListNode head) {
            ListNode anchor = new ListNode(-1);
            anchor.next = head;

            ListNode curr = anchor;
            while(curr.next != null && curr.next.next != null) {
                ListNode first = curr.next;
                ListNode second = curr.next.next;

                first.next = second.next;
                second.next = first;
                curr.next = second;

                curr = first;
            }

            return anchor.next;
        }
    }

    class Solution_Correct_2 {
        public ListNode swapPairs(ListNode head) {
            ListNode anchor = new ListNode(-1);
            anchor.next = head;
            ListNode curr = anchor;
            while(curr.next != null && curr.next.next != null) {
                ListNode first = curr.next;
                ListNode second = curr.next.next;

                first.next = second.next;
                second.next = first;

                curr.next = second;
                curr = first;
            }

            return anchor.next;
        }
    }

    class Solution_Correct_1 {
        public ListNode swapPairs(ListNode head) {
            ListNode dummy = new ListNode(-1);
            dummy.next = head;

            ListNode curr = dummy;
            ListNode first = null;
            ListNode second = null;

            while(curr.next != null && curr.next.next != null) {
                first = curr.next;
                second = curr.next.next;

                // Swap
                first.next = second.next;
                curr.next = second;
                curr.next.next = first;

                curr = curr.next.next;
            }

            return dummy.next;
        }
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}

//    24. Swap Nodes in Pairs
//    Medium
//    Given a linked list, swap every two adjacent nodes and return its head. You must solve the problem without modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)
//
//
//
//    Example 1:
//
//
//    Input: head = [1,2,3,4]
//    Output: [2,1,4,3]
//    Example 2:
//
//    Input: head = []
//    Output: []
//    Example 3:
//
//    Input: head = [1]
//    Output: [1]
//
//
//    Constraints:
//
//    The number of nodes in the list is in the range [0, 100].
//    0 <= Node.val <= 100
