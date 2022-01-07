package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class LinkedListRandomNode {
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
        private Map<Integer, ListNode> map;
        private int length;
        private ListNode head;

        Random random;

        /** @param head The linked list's head.
        Note that the head is guaranteed to be not null, so it contains at least one node. */
        public Solution(ListNode head) {
            map = new HashMap<>();
            this.head = head;

            this.length = getLength(head);
            for(int i = 0; i < length; i++) {
                map.put(i, head);
                head = head.next;
            }

            random = new Random();
        }

        /** Returns a random node's value. */
        public int getRandom() {
            int index = random.nextInt(length);
            return map.get(index).val;
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

    class Solution_N {
        private int length;
        private ListNode head;

        Random random;

        /** @param head The linked list's head.
        Note that the head is guaranteed to be not null, so it contains at least one node. */
        public Solution_N(ListNode head) {
            this.length = getLength(head);
            this.head = head;

            random = new Random();
        }

        /** Returns a random node's value. */
        public int getRandom() {
            int index = random.nextInt(length);

            ListNode node = head;
            while(index > 0) {
                node = node.next;
                index--;
            }

            return node.val;
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

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(head);
 * int param_1 = obj.getRandom();
 */
}

//    382. Linked List Random Node
//    Medium
//    Given a singly linked list, return a random node's value from the linked list. Each node must have the same probability of being chosen.
//
//    Implement the Solution class:
//
//    Solution(ListNode head) Initializes the object with the integer array nums.
//    int getRandom() Chooses a node randomly from the list and returns its value. All the nodes of the list should be equally likely to be choosen.
//
//
//    Example 1:
//
//
//    Input
//    ["Solution", "getRandom", "getRandom", "getRandom", "getRandom", "getRandom"]
//    [[[1, 2, 3]], [], [], [], [], []]
//    Output
//    [null, 1, 3, 2, 2, 3]
//
//    Explanation
//    Solution solution = new Solution([1, 2, 3]);
//    solution.getRandom(); // return 1
//    solution.getRandom(); // return 3
//    solution.getRandom(); // return 2
//    solution.getRandom(); // return 2
//    solution.getRandom(); // return 3
//    // getRandom() should return either 1, 2, or 3 randomly. Each element should have equal probability of returning.
//
//
//    Constraints:
//
//    The number of nodes in the linked list will be in the range [1, 104].
//    -104 <= Node.val <= 104
//    At most 104 calls will be made to getRandom.
//
//
//    Follow up:
//
//    What if the linked list is extremely large and its length is unknown to you?
//    Could you solve this efficiently without using extra space?