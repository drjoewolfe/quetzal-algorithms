package org.jwolfe.quetzal.algorithms.lc;

import java.util.PriorityQueue;

public class MergeKSortedLists {
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
