package org.jwolfe.quetzal.algorithms.lc;

public class ConvertBinaryNumberInALinkedListToInteger {
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
        public int getDecimalValue(ListNode head) {
            int number = 0;
            while(head != null) {
                number *= 2;
                number += head.val;
                head = head.next;
            }

            return number;
        }
    }

    class Solution_Correct_2 {
        public int getDecimalValue(ListNode head) {
            if (head == null) {
                return 0;
            }

            int val = 0;
            while (head != null) {
                val *= 2;
                val += head.val;
                head = head.next;
            }

            return val;
        }
    }

    class Solution_Correct_1 {
        public int getDecimalValue(ListNode head) {
            int value = 0;
            while (head != null) {
                value = (value * 2) + head.val;
                head = head.next;
            }

            return value;
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

//    1290. Convert Binary Number in a Linked List to Integer
//    Easy
//    Given head which is a reference node to a singly-linked list. The value of each node in the linked list is either 0 or 1. The linked list holds the binary representation of a number.
//
//    Return the decimal value of the number in the linked list.
//
//
//
//    Example 1:
//
//
//    Input: head = [1,0,1]
//    Output: 5
//    Explanation: (101) in base 2 = (5) in base 10
//    Example 2:
//
//    Input: head = [0]
//    Output: 0
//    Example 3:
//
//    Input: head = [1]
//    Output: 1
//    Example 4:
//
//    Input: head = [1,0,0,1,0,0,1,1,1,0,0,0,0,0,0]
//    Output: 18880
//    Example 5:
//
//    Input: head = [0,0]
//    Output: 0
//
//
//    Constraints:
//
//    The Linked List is not empty.
//    Number of nodes will not exceed 30.
//    Each node's value is either 0 or 1.