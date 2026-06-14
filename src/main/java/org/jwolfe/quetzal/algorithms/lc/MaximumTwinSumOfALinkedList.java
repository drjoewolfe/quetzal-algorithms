package org.jwolfe.quetzal.algorithms.lc;

public class MaximumTwinSumOfALinkedList {
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
        public int pairSum(ListNode head) {
            if (head == null) {
                return 0;
            }

            ListNode secondHead = findMid(head);
            secondHead = reverse(secondHead);

            int maxSum = 0;
            while (secondHead != null) {
                int sum = head.val + secondHead.val;
                maxSum = Math.max(maxSum, sum);

                head = head.next;
                secondHead = secondHead.next;
            }

            return maxSum;
        }

        private ListNode findMid(ListNode head) {
            ListNode slow = head;
            ListNode fast = head;

            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }

            return slow;
        }

        private ListNode reverse(ListNode head) {
            ListNode prev = null;
            ListNode curr = head;

            while (curr != null) {
                ListNode next = curr.next;
                curr.next = prev;

                prev = curr;
                curr = next;
            }

            return prev;
        }
    }

    class Solution_Correct_2 {
        public int pairSum(ListNode head) {
            if (head == null) {
                return 0;
            }

            int n = getLength(head);

            ListNode secondHead = head;
            for (int i = 0; i < n / 2; i++) {
                secondHead = secondHead.next;
            }

            secondHead = reverse(secondHead);

            int maxSum = 0;
            for (int i = 0; i < n / 2; i++) {
                int sum = head.val + secondHead.val;
                maxSum = Math.max(maxSum, sum);

                head = head.next;
                secondHead = secondHead.next;
            }

            return maxSum;
        }

        private int getLength(ListNode head) {
            int n = 0;

            while (head != null) {
                head = head.next;
                n++;
            }

            return n;
        }

        private ListNode reverse(ListNode head) {
            ListNode prev = null;
            ListNode curr = head;

            while (curr != null) {
                ListNode next = curr.next;
                curr.next = prev;

                prev = curr;
                curr = next;
            }

            return prev;
        }
    }

    class Solution_Correct_1 {
        public int pairSum(ListNode head) {
            if (head == null) {
                return 0;
            }

            head = reverseLaterHalf(head);

            ListNode slow = head;
            ListNode fast = head;

            while (fast != null && fast.next != null) {
                fast = fast.next.next;
                slow = slow.next;
            }

            ListNode first = head;
            ListNode second = slow;
            int max = 0;

            while (second != null) {
                int sum = first.val + second.val;
                max = Math.max(max, sum);

                first = first.next;
                second = second.next;
            }

            return max;
        }

        private ListNode reverseLaterHalf(ListNode head) {
            ListNode slow = head;
            ListNode fast = head;
            ListNode firstEnd = head;

            while (fast != null && fast.next != null) {
                fast = fast.next.next;
                firstEnd = slow;
                slow = slow.next;
            }

            firstEnd.next = reverse(slow);
            return head;
        }

        private ListNode reverse(ListNode head) {
            ListNode prev = head;
            ListNode curr = head.next;
            ListNode next;
            prev.next = null;

            while (curr != null) {
                next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }

            return prev;
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
}

//    2130. Maximum Twin Sum of a Linked List
//    Medium
//    In a linked list of size n, where n is even, the ith node (0-indexed) of the linked list is known as the twin of the (n-1-i)th node, if 0 <= i <= (n / 2) - 1.
//
//    For example, if n = 4, then node 0 is the twin of node 3, and node 1 is the twin of node 2. These are the only nodes with twins for n = 4.
//    The twin sum is defined as the sum of a node and its twin.
//
//    Given the head of a linked list with even length, return the maximum twin sum of the linked list.
//
//
//
//    Example 1:
//
//
//    Input: head = [5,4,2,1]
//    Output: 6
//    Explanation:
//    Nodes 0 and 1 are the twins of nodes 3 and 2, respectively. All have twin sum = 6.
//    There are no other nodes with twins in the linked list.
//    Thus, the maximum twin sum of the linked list is 6.
//    Example 2:
//
//
//    Input: head = [4,2,2,3]
//    Output: 7
//    Explanation:
//    The nodes with twins present in this linked list are:
//    - Node 0 is the twin of node 3 having a twin sum of 4 + 3 = 7.
//    - Node 1 is the twin of node 2 having a twin sum of 2 + 2 = 4.
//    Thus, the maximum twin sum of the linked list is max(7, 4) = 7.
//    Example 3:
//
//
//    Input: head = [1,100000]
//    Output: 100001
//    Explanation:
//    There is only one node with a twin in the linked list having twin sum of 1 + 100000 = 100001.
//
//
//    Constraints:
//
//    The number of nodes in the list is an even integer in the range [2, 105].
//    1 <= Node.val <= 105