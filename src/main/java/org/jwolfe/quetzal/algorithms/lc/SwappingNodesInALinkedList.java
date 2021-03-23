package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.List;

public class SwappingNodesInALinkedList {
   public static class Solution {
        public ListNode swapNodes(ListNode head, int k) {
            if(head == null || k < 1) {
                return head;
            }

            List<ListNode> nodes = convertToList(head);

            int n = nodes.size();

            ListNode left = nodes.get(k - 1);
            ListNode right = nodes.get(n - k);

            int temp = left.val;
            left.val = right.val;
            right.val = temp;

            return head;
        }

        private List<ListNode> convertToList(ListNode head) {
            List<ListNode> nodes = new ArrayList<>();
            while(head != null) {
                nodes.add(head);
                head = head.next;
            }

            return nodes;
        }
    }

    public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
}

//    1721. Swapping Nodes in a Linked List
//    Medium
//    You are given the head of a linked list, and an integer k.
//
//    Return the head of the linked list after swapping the values of the kth node from the beginning and the kth node from the end (the list is 1-indexed).
//
//
//
//    Example 1:
//
//
//    Input: head = [1,2,3,4,5], k = 2
//    Output: [1,4,3,2,5]
//    Example 2:
//
//    Input: head = [7,9,6,6,7,8,3,0,9,5], k = 5
//    Output: [7,9,6,6,8,7,3,0,9,5]
//    Example 3:
//
//    Input: head = [1], k = 1
//    Output: [1]
//    Example 4:
//
//    Input: head = [1,2], k = 1
//    Output: [2,1]
//    Example 5:
//
//    Input: head = [1,2,3], k = 2
//    Output: [1,2,3]
//
//
//    Constraints:
//
//    The number of nodes in the list is n.
//    1 <= k <= n <= 105
//    0 <= Node.val <= 100