package org.jwolfe.quetzal.algorithms.lc;

public class ReorderList {
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
        public void reorderList(ListNode head) {
            if (head == null || head.next == null) {
                return;
            }

            ListNode fast = head;
            ListNode slow = head;

            while (fast != null && fast.next != null) {
                fast = fast.next.next;
                slow = slow.next;
            }

            ListNode head2 = slow.next;
            slow.next = null;

            head2 = reverse(head2);

            ListNode list = new ListNode();
            while (head != null && head2 != null) {
                list.next = head;
                list = list.next;
                head = head.next;

                list.next = head2;
                list = list.next;
                head2 = head2.next;
            }

            if (head != null) {
                list.next = head;
            }

            if (head2 != null) {
                list.next = head2;
            }
        }

        private ListNode reverse(ListNode head) {
            ListNode prev = null;
            ListNode curr = head;
            ListNode next = null;

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

    class Solution_Correct_1 {
        public void reorderList(ListNode head) {
            if (head == null || head.next == null) {
                return;
            }

            ListNode fast = head;
            ListNode slow = head;

            while (fast.next != null && fast.next.next != null) {
                fast = fast.next.next;
                slow = slow.next;
            }

            ListNode head2 = slow.next;
            slow.next = null;
            head2 = reverse(head2);

            ListNode curr = head;
            ListNode next = null;
            while (head2 != null) {
                next = curr.next;
                curr.next = head2;
                curr = curr.next;

                head2 = head2.next;
                curr.next = next;
                curr = curr.next;
            }
        }

        private void print(ListNode head) {
            while (head != null) {
                System.out.print(head.val + " ");
                head = head.next;
            }

            System.out.println();
        }

        private ListNode reverse(ListNode head) {
            ListNode prev = head;
            ListNode curr = head;
            ListNode next = null;
            while (curr != null) {
                next = curr.next;
                curr.next = prev;
                prev = curr;

                curr = next;
            }

            head.next = null;
            return prev;
        }
    }

    class Solution_Incorrect {
        public void reorderList(ListNode head) {
            if (head == null || head.next == null) {
                return;
            }


            print(head);
            ListNode list1Anchor = new ListNode();
            ListNode list2Anchor = new ListNode();

            ListNode list1 = head;
            ListNode list2 = head.next;

            head = head.next.next;

            list1Anchor.next = list1;
            list1.next = null;

            list2Anchor.next = list2;
            list2.next = null;

            while (head != null) {
                list1.next = head;
                list1 = list1.next;

                head = head.next;

                if (head != null) {
                    list2.next = head;
                    list2 = list2.next;

                    head = head.next;
                }
            }

            list1.next = null;
            list2.next = null;

            list1 = list1Anchor.next;
            list2 = list2Anchor.next;

            print(list1);
            print(list2);
            list2 = reverse(list2);
            print(list2);

            ListNode anchor = new ListNode();
            head = anchor;
            while (list1 != null && list2 != null) {
                head.next = list1;
                head = head.next;
                list1 = list1.next;

                head.next = list2;
                head = head.next;
                list2 = list2.next;
            }

            if (list1 != null) {
                head.next = list1;
            }

            if (list2 != null) {
                head.next = list2;
            }

            return;
        }

        private ListNode reverse(ListNode head) {
            ListNode prev = null;
            ListNode curr = head;
            ListNode next = null;

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

// [1,2,3,4]
}

//    143. Reorder List
//    Medium
//    You are given the head of a singly linked-list. The list can be represented as:
//
//    L0 → L1 → … → Ln - 1 → Ln
//    Reorder the list to be on the following form:
//
//    L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
//    You may not modify the values in the list's nodes. Only nodes themselves may be changed.
//
//
//
//    Example 1:
//
//
//    Input: head = [1,2,3,4]
//    Output: [1,4,2,3]
//    Example 2:
//
//
//    Input: head = [1,2,3,4,5]
//    Output: [1,5,2,4,3]
//
//
//    Constraints:
//
//    The number of nodes in the list is in the range [1, 5 * 104].
//    1 <= Node.val <= 1000
