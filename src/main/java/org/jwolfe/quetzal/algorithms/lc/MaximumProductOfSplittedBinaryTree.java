package org.jwolfe.quetzal.algorithms.lc;

public class MaximumProductOfSplittedBinaryTree {
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
        private int MOD = 1_000_000_007;
        private long maximumProduct = 0;

        public int maxProduct(TreeNode root) {
            long totalSum = sum(root);

            maximumProduct = 0;
            computeMaximumProduct(root, totalSum);

            return (int) (maximumProduct % MOD);
        }

        private long computeMaximumProduct(TreeNode root, long totalSum) {
            if (root == null) {
                return 0;
            }

            if (root.left == null && root.right == null) {
                // Leaf Node
                return root.val;
            }

            long leftTreeSum = computeMaximumProduct(root.left, totalSum);
            long rightTreeSum = computeMaximumProduct(root.right, totalSum);

            long leftSpliceProduct = (totalSum - leftTreeSum) * leftTreeSum;
            long rightSpliceProduct = (totalSum - rightTreeSum) * rightTreeSum;

            maximumProduct = Math.max(maximumProduct, leftSpliceProduct);
            maximumProduct = Math.max(maximumProduct, rightSpliceProduct);

            return root.val + leftTreeSum + rightTreeSum;
        }

        private long sum(TreeNode root) {
            if (root == null) {
                return 0;
            }

            return root.val + sum(root.left) + sum(root.right);
        }
    }

    class Solution_Correct_2 {
        long max;
        int MOD = 1_000_000_007;

        public int maxProduct(TreeNode root) {
            if (root == null) {
                return 0;
            }

            int sum = getSum(root);
            max = 0;
            computeMaxProduct(root, sum);

            return (int) (max % MOD);
        }

        private long computeMaxProduct(TreeNode root, int sum) {
            if (root == null) {
                return 0;
            }

            long leftSum = computeMaxProduct(root.left, sum);
            long rightSum = computeMaxProduct(root.right, sum);

            long subTreeSum = leftSum + rightSum + root.val;
            long otherTreeSum = sum - subTreeSum;

            long product = subTreeSum * otherTreeSum;
            max = Math.max(max, product);

            return subTreeSum;
        }

        private int getSum(TreeNode root) {
            if (root == null) {
                return 0;
            }

            return root.val + getSum(root.left) + getSum(root.right);
        }
    }

    class Solution_Correct_1 {
        long max;
        int MOD = 1_000_000_007;

        public int maxProduct(TreeNode root) {
            if (root == null) {
                return 0;
            }

            long sum = getSum(root);

            max = 0;
            computeMaxProduct(root, sum);

            return (int) (max % MOD);
        }

        private long computeMaxProduct(TreeNode root, long sum) {
            if (root == null) {
                return 0;
            }

            long left = computeMaxProduct(root.left, sum);
            long right = computeMaxProduct(root.right, sum);

            // If we split at this node
            long subTreeSum = (root.val + left + right);
            long remainingSum = sum - subTreeSum;

            max = Math.max(max, (subTreeSum * remainingSum));

            return subTreeSum;
        }

        private long getSum(TreeNode root) {
            if (root == null) {
                return 0;
            }

            return (root.val + getSum(root.left) + getSum(root.right));
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

// [1,2,3,4,5,6]

}

//    1339. Maximum Product of Splitted Binary Tree
//    Medium
//    Given the root of a binary tree, split the binary tree into two subtrees by removing one edge such that the product of the sums of the subtrees is maximized.
//
//    Return the maximum product of the sums of the two subtrees. Since the answer may be too large, return it modulo 109 + 7.
//
//    Note that you need to maximize the answer before taking the mod and not after taking it.
//
//
//
//    Example 1:
//
//
//    Input: root = [1,2,3,4,5,6]
//    Output: 110
//    Explanation: Remove the red edge and get 2 binary trees with sum 11 and 10. Their product is 110 (11*10)
//    Example 2:
//
//
//    Input: root = [1,null,2,3,4,null,null,5,6]
//    Output: 90
//    Explanation: Remove the red edge and get 2 binary trees with sum 15 and 6.Their product is 90 (15*6)
//    Example 3:
//
//    Input: root = [2,3,9,10,7,8,6,5,4,11,1]
//    Output: 1025
//    Example 4:
//
//    Input: root = [1,1]
//    Output: 1
//
//
//    Constraints:
//
//    The number of nodes in the tree is in the range [2, 5 * 104].
//    1 <= Node.val <= 104
