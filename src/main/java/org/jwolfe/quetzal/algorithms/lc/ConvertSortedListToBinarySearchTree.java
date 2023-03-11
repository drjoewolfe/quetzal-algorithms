package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.List;

public class ConvertSortedListToBinarySearchTree {
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
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode() {}
     *     TreeNode(int val) { this.val = val; }
     *     TreeNode(int val, TreeNode left, TreeNode right) {
     *         this.val = val;
     *         this.left = left;
     *         this.right = right;
     *     }
     * }
     */
    class Solution {
        ListNode head;

        public TreeNode sortedListToBST(ListNode head) {
            if(head == null) {
                return null;
            }

            this.head = head;
            int length = getLength(head);
            return sortedListToBST(0, length - 1);
        }

        private TreeNode sortedListToBST(int start, int end) {
            if(start > end) {
                return null;
            }

            int mid = start + (end - start) / 2;

            TreeNode left = sortedListToBST(start, mid - 1);

            TreeNode node = new TreeNode(head.val);
            node.left = left;
            head = head.next;

            TreeNode right = sortedListToBST(mid + 1, end);
            node.right = right;

            return node;
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

    class Solution_Correct_1 {
        ListNode head;
        public TreeNode sortedListToBST(ListNode head) {
            if(head == null) {
                return null;
            }

            int length = getLength(head);
            this.head = head;
            return sortedListToBST(0, length - 1);
        }

        private TreeNode sortedListToBST(int start, int end) {
            if(start > end) {
                return null;
            }


            int mid = start + (end - start) / 2;
            TreeNode left = sortedListToBST(start, mid - 1);

            TreeNode node = new TreeNode(this.head.val);
            node.left = left;

            this.head = this.head.next;
            node.right = sortedListToBST(mid + 1, end);

            return node;
        }

        private int getLength(ListNode head) {
            int length = 0;
            while(head != null) {
                head = head.next;
                length++;
            }

            return length;
        }
    }

    class Solution_Space {
        public TreeNode sortedListToBST(ListNode head) {
            if(head == null) {
                return null;
            }

            List<Integer> list = getList(head);
            return sortedListToBST(list, 0, list.size() - 1);
        }

        private TreeNode sortedListToBST(List<Integer> list, int start, int end) {
            if(start > end) {
                return null;
            }

            int mid = start + (end - start) / 2;

            TreeNode node = new TreeNode(list.get(mid));
            node.left = sortedListToBST(list, start, mid - 1);
            node.right = sortedListToBST(list, mid + 1, end);

            return node;
        }

        private List<Integer> getList(ListNode head) {
            List<Integer> list = new ArrayList<>();
            while(head != null) {
                list.add(head.val);
                head = head.next;
            }

            return list;
        }

        private int getLength(ListNode head) {
            int length = 0;
            while(head != null) {
                head = head.next;
                length++;
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

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}

//    109. Convert Sorted List to Binary Search Tree
//    Medium
//    Given the head of a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
//
//    For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
//
//
//
//    Example 1:
//
//
//    Input: head = [-10,-3,0,5,9]
//    Output: [0,-3,9,-10,null,5]
//    Explanation: One possible answer is [0,-3,9,-10,null,5], which represents the shown height balanced BST.
//    Example 2:
//
//    Input: head = []
//    Output: []
//    Example 3:
//
//    Input: head = [0]
//    Output: [0]
//    Example 4:
//
//    Input: head = [1,3]
//    Output: [3,1]
//
//
//    Constraints:
//
//    The number of nodes in head is in the range [0, 2 * 104].
//    -10^5 <= Node.val <= 10^5