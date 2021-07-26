package org.jwolfe.quetzal.algorithms.lc;

public class ConvertSortedArrayToBinarySearchTree {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    class Solution {
        public TreeNode sortedArrayToBST(int[] nums) {
            if(nums == null || nums.length == 0) {
                return null;
            }

            return sortedArrayToBST(nums, 0, nums.length - 1);
        }

        private TreeNode sortedArrayToBST(int[] nums, int start, int end) {
            if(start > end) {
                return null;
            }

            int mid = start + (end - start) / 2;

            TreeNode node = new TreeNode(nums[mid]);
            node.left = sortedArrayToBST(nums, start, mid - 1);
            node.right = sortedArrayToBST(nums, mid + 1, end);

            return node;
        }
    }

    class Solution_Correct_2 {
        public TreeNode sortedArrayToBST(int[] nums) {
            if(nums == null || nums.length == 0) {
                return null;
            }

            return sortedArrayToBST(nums, 0, nums.length - 1);
        }

        private TreeNode sortedArrayToBST(int[] nums, int left, int right) {
            if(left > right) {
                return null;
            }

            int mid = left + (right - left) / 2;

            TreeNode node = new TreeNode(nums[mid]);
            node.left = sortedArrayToBST(nums, left, mid - 1);
            node.right = sortedArrayToBST(nums, mid + 1, right);

            return node;
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}

//    108. Convert Sorted Array to Binary Search Tree
//    Easy
//    Given an integer array nums where the elements are sorted in ascending order, convert it to a height-balanced binary search tree.
//
//    A height-balanced binary tree is a binary tree in which the depth of the two subtrees of every node never differs by more than one.
//
//
//
//    Example 1:
//
//
//    Input: nums = [-10,-3,0,5,9]
//    Output: [0,-3,9,-10,null,5]
//    Explanation: [0,-10,5,null,-3,null,9] is also accepted:
//
//    Example 2:
//
//
//    Input: nums = [1,3]
//    Output: [3,1]
//    Explanation: [1,3] and [3,1] are both a height-balanced BSTs.
//
//
//    Constraints:
//
//    1 <= nums.length <= 104
//    -104 <= nums[i] <= 104
//    nums is sorted in a strictly increasing order.
