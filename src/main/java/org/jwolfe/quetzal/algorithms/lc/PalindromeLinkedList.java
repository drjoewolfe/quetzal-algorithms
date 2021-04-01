package org.jwolfe.quetzal.algorithms.lc;

public class PalindromeLinkedList {
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
        public boolean isPalindrome(ListNode head) {
            if(head == null) {
                return false;
            }

            ListNode fast = head;
            ListNode slow = head;

            while(fast != null && fast.next != null) {
                fast = fast.next.next;
                slow = slow.next;
            }

            ListNode first = head;
            ListNode second = reverse(slow);

            while(first != null && second != null) {
                if(first.val != second.val) {
                    return false;
                }

                first = first.next;
                second = second.next;
            }

            return true;
        }

        private ListNode reverse(ListNode root) {
            ListNode prev = null;
            ListNode curr = root;
            ListNode next = null;

            while(curr != null) {
                next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }

            return prev;
        }
    }

    class Solution_NSpace {
        public boolean isPalindrome(ListNode head) {
            if(head == null) {
                return false;
            }

            int length = getLength(head);
            int[] arr = new int[length];
            for(int i = 0; i < length; i++) {
                arr[i] = head.val;
                head = head.next;
            }

            return isPalindrome(arr);
        }

        private boolean isPalindrome(int[] arr) {
            int i = 0;
            int j = arr.length - 1;

            while(i < j) {
                if(arr[i] != arr[j]) {
                    return false;
                }

                i++;
                j--;
            }

            return true;
        }

        private int getLength(ListNode head) {
            int length = 0;
            while(head != null) {
                length++;
                head = head.next;
            }

            return length;
        }
    }

     public static class ListNode {
         int val;
         ListNode next;
         ListNode() {}
         ListNode(int val) { this.val = val; }
         ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     }

    // 1 - 2

    // 1 - 2 - 2 - 1

    // 1 - 2 - 3 - 2 - 1
}

//    234. Palindrome Linked List
//    Easy
//    Given the head of a singly linked list, return true if it is a palindrome.
//
//
//
//    Example 1:
//
//
//    Input: head = [1,2,2,1]
//    Output: true
//    Example 2:
//
//
//    Input: head = [1,2]
//    Output: false
//
//
//    Constraints:
//
//    The number of nodes in the list is in the range [1, 105].
//    0 <= Node.val <= 9
//
//
//    Follow up: Could you do it in O(n) time and O(1) space?