package org.jwolfe.quetzal.algorithms.lc;

public class RemoveNthNodeFromEndOfList {
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
        public ListNode removeNthFromEnd(ListNode head, int n) {
            if (head == null || n < 1) {
                return null;
            }

            ListNode anchor = new ListNode(-1);
            anchor.next = head;

            ListNode fast = head;
            while (n > 1 && fast != null) {
                fast = fast.next;
                n--;
            }

            if (fast == null) {
                return head;
            }

            ListNode slow = head;
            ListNode prev = anchor;
            while (fast.next != null) {
                prev = slow;
                fast = fast.next;
                slow = slow.next;
            }

            prev.next = slow.next;

            return anchor.next;
        }
    }

    class Solution_Approach1 {
        public ListNode removeNthFromEnd(ListNode head, int n) {
            if (head == null) {
                return head;
            }

            ListNode dummy = new ListNode(-1);
            dummy.next = head;

            ListNode fast = dummy;
            ListNode slow = dummy;

            for (int i = 1; i <= n + 1 && fast != null; i++) {
                fast = fast.next;
            }

            while (fast != null) {
                fast = fast.next;
                slow = slow.next;
            }

            slow.next = slow.next.next;

            return dummy.next;
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

//    19. Remove Nth Node From End of List
//    Medium
//    Given the head of a linked list, remove the nth node from the end of the list and return its head.
//
//    Follow up: Could you do this in one pass?
//
//    Example 1:
//    Input: head = [1,2,3,4,5], n = 2
//    Output: [1,2,3,5]
//    Example 2:
//
//    Input: head = [1], n = 1
//    Output: []
//    Example 3:
//
//    Input: head = [1,2], n = 1
//    Output: [1]
//
//
//    Constraints:
//
//    The number of nodes in the list is sz.
//    1 <= sz <= 30
//    0 <= Node.val <= 100
//    1 <= n <= sz