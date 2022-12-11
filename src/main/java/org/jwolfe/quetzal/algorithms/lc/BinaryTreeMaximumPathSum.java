package org.jwolfe.quetzal.algorithms.lc;

public class BinaryTreeMaximumPathSum {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */
    class Solution {
        int maxSum;

        public int maxPathSum(TreeNode root) {
            maxSum = Integer.MIN_VALUE;
            maxPathSumHelper(root);

            return maxSum;
        }

        private int maxPathSumHelper(TreeNode root) {
            if (root == null) {
                return 0;
            }

            int leftPathSum = maxPathSumHelper(root.left);
            int rightPathSum = maxPathSumHelper(root.right);

            int maxSumAtRoot = max(root.val,
                    root.val + leftPathSum,
                    root.val + rightPathSum);
            int maxSumThroughRoot = max(maxSumAtRoot,
                    root.val + leftPathSum + rightPathSum);

            maxSum = Math.max(maxSum, maxSumThroughRoot);
            return maxSumAtRoot;
        }

        private int max(int... args) {
            int maxVal = Integer.MIN_VALUE;
            for (int val : args) {
                maxVal = Math.max(maxVal, val);
            }

            return maxVal;
        }
    }

    class Solution_Correct_1 {
        private int maxSum = Integer.MIN_VALUE;

        public int maxPathSum(TreeNode root) {
            maxSum = Integer.MIN_VALUE;

            processMaxPathSum(root);
            return maxSum;
        }

        private int processMaxPathSum(TreeNode root) {
            if (root == null) {
                return 0;
            }

            int leftSum = processMaxPathSum(root.left);
            int rightSum = processMaxPathSum(root.right);

            int maxEndingHere = Math.max(
                    Math.max(leftSum, rightSum) + root.val,
                    root.val);

            int maxThroughHere = Math.max(
                    maxEndingHere,
                    leftSum + root.val + rightSum);

            maxSum = max(maxSum, maxThroughHere);
            return maxEndingHere;
        }

        private int max(int... values) {
            int max = Integer.MIN_VALUE;

            for (int number : values) {
                max = Math.max(max, number);
            }

            return max;
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

// [1,2,3]
}

//    124. Binary Tree Maximum Path Sum
//    Hard
//    A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them. A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.
//
//    The path sum of a path is the sum of the node's values in the path.
//
//    Given the root of a binary tree, return the maximum path sum of any non-empty path.
//
//
//
//    Example 1:
//
//
//    Input: root = [1,2,3]
//    Output: 6
//    Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.
//    Example 2:
//
//
//    Input: root = [-10,9,20,null,null,15,7]
//    Output: 42
//    Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.
//
//
//    Constraints:
//
//    The number of nodes in the tree is in the range [1, 3 * 104].
//    -1000 <= Node.val <= 1000