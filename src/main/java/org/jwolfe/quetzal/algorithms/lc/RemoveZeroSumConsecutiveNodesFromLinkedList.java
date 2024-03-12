package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;

public class RemoveZeroSumConsecutiveNodesFromLinkedList {
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
        public ListNode removeZeroSumSublists(ListNode head) {
            ListNode anchor = new ListNode(0, head);
            ListNode current = anchor;

            int prefixSum = 0;
            Map<Integer, ListNode> prefixSumMap = new HashMap<>();
            prefixSumMap.put(0, anchor);

            while (current != null) {
                prefixSum += current.val;
                prefixSumMap.put(prefixSum, current);

                current = current.next;
            }

            prefixSum = 0;
            current = anchor;

            while (current != null) {
                prefixSum += current.val;
                current.next = prefixSumMap.get(prefixSum).next;

                current = current.next;
            }

            return anchor.next;
        }
    }

    class Solution_Correct_1 {
        public ListNode removeZeroSumSublists(ListNode head) {
            ListNode anchor = new ListNode();
            anchor.next = head;

            ListNode prev = anchor;

            loop:
            while (head != null) {
                ListNode curr = head;

                int sum = 0;
                while (curr != null) {
                    sum += curr.val;
                    if (sum == 0) {
                        prev.next = curr.next;
                        head = curr.next;
                        continue loop;
                    }

                    curr = curr.next;
                }

                prev = head;
                head = head.next;
            }

            return anchor.next;
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

//    1171. Remove Zero Sum Consecutive Nodes from Linked List
//    Medium
//    Given the head of a linked list, we repeatedly delete consecutive sequences of nodes that sum to 0 until there are no such sequences.
//
//    After doing so, return the head of the final linked list.  You may return any such answer.
//
//
//
//    (Note that in the examples below, all sequences are serializations of ListNode objects.)
//
//    Example 1:
//
//    Input: head = [1,2,-3,3,1]
//    Output: [3,1]
//    Note: The answer [1,2,1] would also be accepted.
//    Example 2:
//
//    Input: head = [1,2,3,-3,4]
//    Output: [1,2,4]
//    Example 3:
//
//    Input: head = [1,2,3,-3,-2]
//    Output: [1]
//
//
//    Constraints:
//
//    The given linked list will contain between 1 and 1000 nodes.
//    Each node in the linked list has -1000 <= node.val <= 1000.