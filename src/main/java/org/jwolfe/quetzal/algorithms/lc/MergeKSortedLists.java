package org.jwolfe.quetzal.algorithms.lc;

import java.util.PriorityQueue;

public class MergeKSortedLists {
    class Solution {
        public ListNode mergeKLists(ListNode[] lists) {
            if(lists == null || lists.length == 0) {
                return null;
            }

            ListNode anchor = new ListNode(-1);
            PriorityQueue<ListNode> heap = new PriorityQueue<>((a, b) -> a.val - b.val);
            for(ListNode node : lists) {
                if(node != null) {
                    heap.offer(node);
                }
            }

            ListNode prev = anchor;
            while(!heap.isEmpty()) {
                ListNode node = heap.poll();

                prev.next = node;
                prev = prev.next;

                if(node.next != null) {
                    heap.offer(node.next);
                }
            }

            return anchor.next;
        }
    }

    class Solution_Correct_1 {
        public ListNode mergeKLists(ListNode[] lists) {
            if(lists == null || lists.length == 0) {
                return null;
            }

            int k = lists.length;
            PriorityQueue<ListNode> queue = new PriorityQueue<>((l1, l2) -> l1.val - l2.val);

            ListNode dummy = new ListNode(-1);
            ListNode merged = dummy;

            for(int i = 0; i <k; i++) {
                if(lists[i] != null) {
                    queue.offer(lists[i]);
                }
            }

            while(!queue.isEmpty()) {
                ListNode node = queue.poll();
                if(node.next != null) {
                    queue.offer(node.next);
                }

                merged.next = node;
                merged = merged.next;
            }

            return dummy.next;
        }
    }

// [[1,4,5],[1,3,4],[2,6]]

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) { val = x; }
     * }
     */

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}

//    23. Merge k Sorted Lists
//    Hard
//    You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
//
//    Merge all the linked-lists into one sorted linked-list and return it.
//
//
//
//    Example 1:
//
//    Input: lists = [[1,4,5],[1,3,4],[2,6]]
//    Output: [1,1,2,3,4,4,5,6]
//    Explanation: The linked-lists are:
//    [
//    1->4->5,
//    1->3->4,
//    2->6
//    ]
//    merging them into one sorted list:
//    1->1->2->3->4->4->5->6
//    Example 2:
//
//    Input: lists = []
//    Output: []
//    Example 3:
//
//    Input: lists = [[]]
//    Output: []
//
//
//    Constraints:
//
//    k == lists.length
//    0 <= k <= 104
//    0 <= lists[i].length <= 500
//    -104 <= lists[i][j] <= 104
//    lists[i] is sorted in ascending order.
//    The sum of lists[i].length will not exceed 104.