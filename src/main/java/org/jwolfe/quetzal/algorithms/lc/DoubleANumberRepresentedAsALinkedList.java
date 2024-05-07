package org.jwolfe.quetzal.algorithms.lc;

public class DoubleANumberRepresentedAsALinkedList {
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
        public ListNode doubleIt(ListNode head) {
            if (head == null) {
                return head;
            }

            head = reverse(head);

            int sum = 0;
            int carry = 0;

            ListNode prev = null;
            ListNode curr = head;
            while (curr != null) {
                int val = curr.val * 2 + carry;

                curr.val = val % 10;
                carry = val / 10;

                prev = curr;
                curr = curr.next;
            }

            if (carry != 0) {
                prev.next = new ListNode(carry);
            }

            return reverse(head);
        }

        private ListNode reverse(ListNode head) {
            ListNode prev = null;
            ListNode curr = head;
            ListNode next = null;

            while (curr != null) {
                next = curr.next;

                curr.next = prev;
                prev = curr;
                curr = next;
            }

            return prev;
        }

        private void print(ListNode head) {
            while (head != null) {
                System.out.print(head.val + " ");
                head = head.next;
            }

            System.out.println();
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

//    2816. Double a Number Represented as a Linked List
//    Medium
//    You are given the head of a non-empty linked list representing a non-negative integer without leading zeroes.
//
//    Return the head of the linked list after doubling it.
//
//
//
//    Example 1:
//
//
//    Input: head = [1,8,9]
//    Output: [3,7,8]
//    Explanation: The figure above corresponds to the given linked list which represents the number 189. Hence, the returned linked list represents the number 189 * 2 = 378.
//    Example 2:
//
//
//    Input: head = [9,9,9]
//    Output: [1,9,9,8]
//    Explanation: The figure above corresponds to the given linked list which represents the number 999. Hence, the returned linked list reprersents the number 999 * 2 = 1998.
//
//
//    Constraints:
//
//    The number of nodes in the list is in the range [1, 104]
//    0 <= Node.val <= 9
//    The input is generated such that the list represents a number that does not have leading zeros, except the number 0 itself.