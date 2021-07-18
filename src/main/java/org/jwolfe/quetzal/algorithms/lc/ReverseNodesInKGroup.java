package org.jwolfe.quetzal.algorithms.lc;

public class ReverseNodesInKGroup {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    class Solution {
        public ListNode reverseKGroup(ListNode head, int k) {
            if (head == null || k <= 0) {
                return head;
            }

            ListNode anchor = new ListNode(-1);

            ListNode prev = anchor;
            ListNode start = head;
            ListNode end = null;
            ListNode next = null;

            ListNode node = head;

            while (node != null) {
                int i = 1;
                while (i < k && node != null) {
                    node = node.next;
                    i++;
                }

                if (node == null) {
                    break;
                }

                end = node;
                next = node.next;

                prev.next = reverse(start, end);
                start.next = next;

                node = next;
                prev = start;
                start = next;
            }

            return anchor.next;
        }

        private ListNode reverse(ListNode start, ListNode end) {
            ListNode prev = null;
            ListNode curr = start;
            ListNode next = null;

            while (curr.next != end.next) {
                next = curr.next;

                curr.next = prev;
                prev = curr;
                curr = next;
            }

            next = curr.next;
            curr.next = prev;
            prev = curr;
            return prev;
        }
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}

//    25. Reverse Nodes in k-Group
//    Hard
//    Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
//
//    k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.
//
//    You may not alter the values in the list's nodes, only nodes themselves may be changed.
//
//
//
//    Example 1:
//
//
//    Input: head = [1,2,3,4,5], k = 2
//    Output: [2,1,4,3,5]
//    Example 2:
//
//
//    Input: head = [1,2,3,4,5], k = 3
//    Output: [3,2,1,4,5]
//    Example 3:
//
//    Input: head = [1,2,3,4,5], k = 1
//    Output: [1,2,3,4,5]
//    Example 4:
//
//    Input: head = [1], k = 1
//    Output: [1]
//
//
//    Constraints:
//
//    The number of nodes in the list is in the range sz.
//    1 <= sz <= 5000
//    0 <= Node.val <= 1000
//    1 <= k <= sz
//
//
//    Follow-up: Can you solve the problem in O(1) extra memory space?
