package org.jwolfe.quetzal.algorithms.lc;

public class AddTwoNumbersII {
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
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode rl1 = reverse(l1);
            ListNode rl2 = reverse(l2);

            ListNode anchor = new ListNode();
            ListNode prev = anchor;

            int carry = 0;
            int sum = 0;

            while (rl1 != null || rl2 != null) {
                sum = carry;
                if (rl1 != null) {
                    sum += rl1.val;
                    rl1 = rl1.next;
                }

                if (rl2 != null) {
                    sum += rl2.val;
                    rl2 = rl2.next;
                }

                carry = sum / 10;
                sum = sum % 10;

                ListNode node = new ListNode(sum);
                prev.next = node;
                prev = node;
            }

            if (carry != 0) {
                ListNode node = new ListNode(carry);
                prev.next = node;
            }

            return reverse(anchor.next);
        }

        private ListNode reverse(ListNode node) {
            ListNode prev = null;
            ListNode curr = node;
            ListNode next = null;
            while (curr != null) {
                next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }

            return prev;
        }
    }

    class Solution_Reverse {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode rl1 = reverse(l1);
            ListNode rl2 = reverse(l2);

            ListNode dummy = new ListNode();
            ListNode prev = dummy;
            int sum = 0;
            int carry = 0;
            while (rl1 != null && rl2 != null) {
                sum = rl1.val + rl2.val + carry;

                ListNode node = new ListNode(sum % 10);
                prev.next = node;
                prev = node;

                carry = sum / 10;

                rl1 = rl1.next;
                rl2 = rl2.next;
            }

            while (rl1 != null) {
                sum = rl1.val + carry;

                ListNode node = new ListNode(sum % 10);
                prev.next = node;
                prev = node;

                carry = sum / 10;

                rl1 = rl1.next;
            }

            while (rl2 != null) {
                sum = rl2.val + carry;

                ListNode node = new ListNode(sum % 10);
                prev.next = node;
                prev = node;

                carry = sum / 10;

                rl2 = rl2.next;
            }

            if (carry != 0) {
                prev.next = new ListNode(carry);
            }

            return reverse(dummy.next);
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

//    445. Add Two Numbers II
//    Medium
//    You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.
//
//    You may assume the two numbers do not contain any leading zero, except the number 0 itself.
//
//
//
//    Example 1:
//
//
//    Input: l1 = [7,2,4,3], l2 = [5,6,4]
//    Output: [7,8,0,7]
//    Example 2:
//
//    Input: l1 = [2,4,3], l2 = [5,6,4]
//    Output: [8,0,7]
//    Example 3:
//
//    Input: l1 = [0], l2 = [0]
//    Output: [0]
//
//
//    Constraints:
//
//    The number of nodes in each linked list is in the range [1, 100].
//    0 <= Node.val <= 9
//    It is guaranteed that the list represents a number that does not have leading zeros.
//
//
//    Follow up: Could you solve it without reversing the input lists?