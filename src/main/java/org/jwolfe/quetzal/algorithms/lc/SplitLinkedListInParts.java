package org.jwolfe.quetzal.algorithms.lc;

public class SplitLinkedListInParts {
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
        public ListNode[] splitListToParts(ListNode root, int k) {
            if(k < 1) {
                return new ListNode[0];
            }

            ListNode[] parts = new ListNode[k];
            if(root == null) {
                return parts;
            }

            int length = getLength(root);

            ListNode node = root;
            if(length <= k) {
                for(int i = 0; i < length; i++) {
                    parts[i] = node;
                    node = node.next;
                    parts[i].next = null;
                }

                return parts;
            }

            int size = length / k;
            int rem = length % k;
            parts[0] = node;
            ListNode prev = null;

            for(int i = 1; i < k; i++) {
                for(int j = 1; j <= size; j++) {
                    prev = node;
                    node = node.next;
                }

                if(rem > 0) {
                    prev = node;
                    node = node.next;

                    rem--;
                }

                parts[i] = node;
                prev.next = null;
            }

            return parts;
        }

        private int getLength(ListNode root) {
            int length = 0;
            while(root != null) {
                length++;
                root = root.next;
            }

            return length;
        }
    }

    class Solution_Correct_2 {
        public ListNode[] splitListToParts(ListNode head, int k) {
            if(k == 0) {
                return new ListNode[0];
            }

            ListNode[] partitions = new ListNode[k];
            if(head == null) {
                return partitions;
            }

            int length = getLength(head);
            if(length <= k) {
                for(int i = 0; i < length; i++) {
                    partitions[i] = head;
                    head = head.next;

                    partitions[i].next = null;
                }

                return partitions;
            }

            int size = length / k;
            int overflow = length % k;

            for(int i = 0; i < k; i++) {
                ListNode tail = head;
                for(int j = 1; j < size; j++) {
                    tail = tail.next;
                }

                if(overflow > 0) {
                    tail = tail.next;
                    overflow--;
                }

                partitions[i] = head;
                head = tail.next;

                tail.next = null;
            }

            return partitions;
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

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

// [1,2,3,4]
// 5

// [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
// 3
}

//    725. Split Linked List in Parts
//    Medium
//    Given the head of a singly linked list and an integer k, split the linked list into k consecutive linked list parts.
//
//    The length of each part should be as equal as possible: no two parts should have a size differing by more than one. This may lead to some parts being null.
//
//    The parts should be in the order of occurrence in the input list, and parts occurring earlier should always have a size greater than or equal to parts occurring later.
//
//    Return an array of the k parts.
//
//
//
//    Example 1:
//
//
//    Input: head = [1,2,3], k = 5
//    Output: [[1],[2],[3],[],[]]
//    Explanation:
//    The first element output[0] has output[0].val = 1, output[0].next = null.
//    The last element output[4] is null, but its string representation as a ListNode is [].
//    Example 2:
//
//
//    Input: head = [1,2,3,4,5,6,7,8,9,10], k = 3
//    Output: [[1,2,3,4],[5,6,7],[8,9,10]]
//    Explanation:
//    The input has been split into consecutive parts with size difference at most 1, and earlier parts are a larger size than the later parts.
//
//
//    Constraints:
//
//    The number of nodes in the list is in the range [0, 1000].
//    0 <= Node.val <= 1000
//    1 <= k <= 50