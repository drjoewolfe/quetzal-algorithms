package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashSet;
import java.util.Set;

public class DeleteNodesFromLinkedListPresentInArray {
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
        public ListNode modifiedList(int[] nums, ListNode head) {
            if (nums == null || nums.length == 0 || head == null) {
                return head;
            }

            Set<Integer> set = new HashSet<>();
            for (int val : nums) {
                set.add(val);
            }

            ListNode anchor = new ListNode();
            ListNode prev = anchor;
            ListNode curr = head;

            while (curr != null) {
                if (!set.contains(curr.val)) {
                    prev.next = curr;
                    prev = curr;
                    curr = curr.next;
                } else {
                    curr = curr.next;
                }
            }

            prev.next = null;
            return anchor.next;
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

// [1,2,3]
// [1,2,3,4,5]

// [9,2,5]
// [2,10,9]
}

//    3217. Delete Nodes From Linked List Present in Array
//    Medium
//    You are given an array of integers nums and the head of a linked list. Return the head of the modified linked list after removing all nodes from the linked list that have a value that exists in nums.
//
//
//
//    Example 1:
//
//    Input: nums = [1,2,3], head = [1,2,3,4,5]
//
//    Output: [4,5]
//
//    Explanation:
//
//
//
//    Remove the nodes with values 1, 2, and 3.
//
//    Example 2:
//
//    Input: nums = [1], head = [1,2,1,2,1,2]
//
//    Output: [2,2,2]
//
//    Explanation:
//
//
//
//    Remove the nodes with value 1.
//
//    Example 3:
//
//    Input: nums = [5], head = [1,2,3,4]
//
//    Output: [1,2,3,4]
//
//    Explanation:
//
//
//
//    No node has value 5.
//
//
//
//    Constraints:
//
//    1 <= nums.length <= 105
//    1 <= nums[i] <= 105
//    All elements in nums are unique.
//    The number of nodes in the given list is in the range [1, 105].
//    1 <= Node.val <= 105
//    The input is generated such that there is at least one node in the linked list that has a value not present in nums.
