package org.jwolfe.quetzal.algorithms.lc;

public class PartitionList {
    class Solution {
        public ListNode partition(ListNode head, int x) {
            ListNode partition1 = new ListNode(-1);
            ListNode partition2 = new ListNode(-1);

            ListNode beforePartition1 = partition1;
            ListNode beforePartition2 = partition2;

            while(head != null) {
                if(head.val < x) {
                    partition1.next = head;
                    partition1 = partition1.next;
                } else {
                    partition2.next = head;
                    partition2 = partition2.next;
                }

                head = head.next;
            }

            partition2.next = null;
            partition1.next = beforePartition2.next;


            return beforePartition1.next;
        }
    }

     public class ListNode {
         int val;
         ListNode next;
         ListNode(int x) { val = x; }
     }
}

//    86. Partition List
//    Medium
//    Given the head of a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
//
//    You should preserve the original relative order of the nodes in each of the two partitions.
//
//
//
//    Example 1:
//
//
//    Input: head = [1,4,3,2,5,2], x = 3
//    Output: [1,2,2,4,3,5]
//    Example 2:
//
//    Input: head = [2,1], x = 2
//    Output: [1,2]
//
//
//    Constraints:
//
//    The number of nodes in the list is in the range [0, 200].
//    -100 <= Node.val <= 100
//    -200 <= x <= 200