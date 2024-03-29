package org.jwolfe.quetzal.algorithms.lc;

public class OddEvenLinkedList {
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
        public ListNode oddEvenList(ListNode head) {
            if(head == null || head.next == null) {
                return head;
            }

            ListNode oddAnchor = new ListNode();
            ListNode evenAnchor = new ListNode();

            ListNode odd = oddAnchor;
            ListNode even = evenAnchor;

            while(head != null) {
                odd.next = head;
                odd = odd.next;
                head = head.next;

                if(head != null) {
                    even.next = head;
                    even = even.next;
                    head = head.next;
                }
            }

            even.next = null;
            odd.next = evenAnchor.next;
            return oddAnchor.next;
        }
    }

    class Solution_Correct_2 {
        public ListNode oddEvenList(ListNode head) {
            if(head == null || head.next == null) {
                return head;
            }

            ListNode oddAnchor = new ListNode();
            ListNode odd = oddAnchor;

            ListNode evenAnchor = new ListNode();
            ListNode even = evenAnchor;

            boolean oddMode = true;
            while(head != null) {
                if(oddMode) {
                    odd.next = head;
                    head = head.next;
                    odd = odd.next;
                    odd.next = null;
                } else {
                    even.next = head;
                    head = head.next;
                    even = even.next;
                    even.next = null;
                }

                oddMode = !oddMode;
            }

            odd.next = evenAnchor.next;
            return oddAnchor.next;
        }
    }

    class Solution_Correct_1 {
        public ListNode oddEvenList(ListNode head) {
            if(head == null || head.next == null) {
                return head;
            }

            ListNode odd = head;
            ListNode even = head.next;
            ListNode evenHead = even;

            while(even != null && even.next != null) {
                odd.next = even.next;
                odd = odd.next;

                even.next = odd.next;
                even = even.next;
            }

            odd.next = evenHead;
            return head;
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

//    328. Odd Even Linked List
//    Medium
//    Given the head of a singly linked list, group all the nodes with odd indices together followed by the nodes with even indices, and return the reordered list.
//
//    The first node is considered odd, and the second node is even, and so on.
//
//    Note that the relative order inside both the even and odd groups should remain as it was in the input.
//
//    You must solve the problem in O(1) extra space complexity and O(n) time complexity.
//
//
//
//    Example 1:
//
//
//    Input: head = [1,2,3,4,5]
//    Output: [1,3,5,2,4]
//    Example 2:
//
//
//    Input: head = [2,1,3,5,6,4,7]
//    Output: [2,3,6,7,1,5,4]
//
//
//    Constraints:
//
//    n == number of nodes in the linked list
//    0 <= n <= 104
//    -106 <= Node.val <= 106