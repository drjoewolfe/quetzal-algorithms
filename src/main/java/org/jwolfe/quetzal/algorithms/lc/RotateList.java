package org.jwolfe.quetzal.algorithms.lc;

public class RotateList {
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
        public ListNode rotateRight(ListNode head, int k) {
            if (head == null || k < 1) {
                return head;
            }

            int length = getLength(head);
            k = k % length;

            ListNode last = head;
            for (int i = 0; i < k; i++) {
                last = last.next;
            }

            ListNode prev = head;
            while (last.next != null) {
                prev = prev.next;
                last = last.next;
            }

            last.next = head;
            head = prev.next;
            prev.next = null;

            return head;
        }

        private int getLength(ListNode head) {
            int length = 0;
            while (head != null) {
                length++;
                head = head.next;
            }

            return length;
        }
    }

    class Solution_Correct_1 {
        public ListNode rotateRight(ListNode head, int k) {
            if (head == null || head.next == null || k == 0) {
                return head;
            }

            int length = getLength(head);
            k = k % length;

            ListNode first = head;
            ListNode prev = head;
            ListNode last = head;

            int counter = 0;
            while (counter < k && last.next != null) {
                last = last.next;
                counter++;
            }

            if (counter < k) {
                return head;
            }

            while (last.next != null) {
                prev = prev.next;
                last = last.next;
            }


            last.next = first;
            head = prev.next;
            prev.next = null;

            return head;
        }

        private int getLength(ListNode head) {
            int length = 1;
            while (head.next != null) {
                head = head.next;
                length++;
            }

            return length;
        }
    }

    class Solution_Brute {
        public ListNode rotateRight(ListNode head, int k) {
            if (head == null || head.next == null || k == 0) {
                return head;
            }

            int length = getLength(head);
            k = k % length;

            for (int i = 0; i < k; i++) {
                head = rotateOnce(head);
            }

            return head;
        }

        private int getLength(ListNode head) {
            int length = 1;
            while (head.next != null) {
                head = head.next;
                length++;
            }

            return length;
        }

        private ListNode rotateOnce(ListNode head) {
            ListNode first = head;
            ListNode last = head;
            ListNode prev = null;
            while (last.next != null) {
                prev = last;
                last = last.next;
            }

            if (prev == null) {
                return head;
            }

            last.next = first;
            prev.next = null;

            return last;
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

//    61. Rotate List
//    Medium
//    Given the head of a linked list, rotate the list to the right by k places.
//
//
//
//    Example 1:
//
//
//    Input: head = [1,2,3,4,5], k = 2
//    Output: [4,5,1,2,3]
//    Example 2:
//
//
//    Input: head = [0,1,2], k = 4
//    Output: [2,0,1]
//
//
//    Constraints:
//
//    The number of nodes in the list is in the range [0, 500].
//    -100 <= Node.val <= 100
//    0 <= k <= 2 * 109