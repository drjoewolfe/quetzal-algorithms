package org.jwolfe.quetzal.algorithms.lc;

public class InsertionSortList {
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
        public ListNode insertionSortList(ListNode head) {
            ListNode anchor = new ListNode();

            ListNode prev = null;
            ListNode curr = head;
            ListNode next = null;

            while (curr != null) {
                prev = anchor;
                next = prev.next;

                while (next != null) {
                    if (curr.val < next.val) {
                        break;
                    }

                    prev = next;
                    next = next.next;
                }

                var nextNode = curr.next;

                prev.next = curr;
                curr.next = next;

                curr = nextNode;
            }


            return anchor.next;
        }
    }

    class Solution_Correct_1 {
        public ListNode insertionSortList(ListNode head) {
            ListNode dummy = new ListNode();

            ListNode curr = head;
            ListNode prev = null;
            ListNode next = null;
            while (curr != null) {
                prev = dummy;
                next = dummy.next;

                while (next != null) {
                    if (curr.val < next.val) {
                        break;
                    }

                    prev = next;
                    next = next.next;
                }

                ListNode nextIter = curr.next;

                curr.next = next;
                prev.next = curr;
                curr = nextIter;
            }

            return dummy.next;
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

// [4,2,1,3]
}

//    147. Insertion Sort List
//    Medium
//    Given the head of a singly linked list, sort the list using insertion sort, and return the sorted list's head.
//
//    The steps of the insertion sort algorithm:
//
//    Insertion sort iterates, consuming one input element each repetition and growing a sorted output list.
//    At each iteration, insertion sort removes one element from the input data, finds the location it belongs within the sorted list and inserts it there.
//    It repeats until no input elements remain.
//    The following is a graphical example of the insertion sort algorithm. The partially sorted list (black) initially contains only the first element in the list. One element (red) is removed from the input data and inserted in-place into the sorted list with each iteration.
//
//
//
//
//    Example 1:
//
//
//    Input: head = [4,2,1,3]
//    Output: [1,2,3,4]
//    Example 2:
//
//
//    Input: head = [-1,5,3,4,0]
//    Output: [-1,0,3,4,5]
//
//
//    Constraints:
//
//    The number of nodes in the list is in the range [1, 5000].
//    -5000 <= Node.val <= 5000