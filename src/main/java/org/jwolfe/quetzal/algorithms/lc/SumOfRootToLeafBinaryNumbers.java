package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.List;

public class SumOfRootToLeafBinaryNumbers {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */
    class Solution {
        int sum;

        public int sumRootToLeaf(TreeNode root) {
            sum = 0;
            sumRootToLeafHelper(root, 0);

            return sum;
        }

        private void sumRootToLeafHelper(TreeNode root, int currentSum) {
            if (root == null) {
                return;
            }

            currentSum *= 2;
            currentSum += root.val;

            if (isLeaf(root)) {
                sum += currentSum;
            } else {
                sumRootToLeafHelper(root.left, currentSum);
                sumRootToLeafHelper(root.right, currentSum);
            }
        }

        private boolean isLeaf(TreeNode root) {
            return root.left == null && root.right == null;
        }
    }

    class Solution_Correct_1 {
        public int sumRootToLeaf(TreeNode root) {
            return sumRootToLeaf(root, 0);
        }

        public int sumRootToLeaf(TreeNode root, int runningSum) {
            if (root == null) {
                return 0;
            }

            runningSum = (runningSum * 2) + root.val;

            if (root.left == null && root.right == null) {
                // Leaf
                return runningSum;
            }

            return sumRootToLeaf(root.left, runningSum) + sumRootToLeaf(root.right, runningSum);
        }

        // Classic implementation
        public int sumRootToLeafClassic(TreeNode root) {
            if (root == null) {
                return 0;
            }

            List<Integer> treeNumbers = new ArrayList<>();
            List<Integer> pathNumbers = new ArrayList<>();

            collectRootToLeafNumbers(root, pathNumbers, treeNumbers);

            int sum = 0;
            for (int num : treeNumbers) {
                sum += num;
            }

            return sum;
        }

        private void collectRootToLeafNumbers(TreeNode root, List<Integer> pathNumbers, List<Integer> treeNumbers) {
            if (root == null) {
                return;
            }

            pathNumbers.add(root.val);

            if (root.left == null && root.right == null) {
                // At leaf
                int number = getNumberFromPath(pathNumbers);
                treeNumbers.add(number);
            } else {
                collectRootToLeafNumbers(root.left, pathNumbers, treeNumbers);
                collectRootToLeafNumbers(root.right, pathNumbers, treeNumbers);
            }

            pathNumbers.remove(pathNumbers.size() - 1);
        }

        private int getNumberFromPath(List<Integer> pathNumbers) {
            int number = 0;

            for (int n : pathNumbers) {
                number = (number * 2) + n;
            }

            return number;
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}

//    1022. Sum of Root To Leaf Binary Numbers
//    Easy
//    You are given the root of a binary tree where each node has a value 0 or 1. Each root-to-leaf path represents a binary number starting with the most significant bit.
//
//    For example, if the path is 0 -> 1 -> 1 -> 0 -> 1, then this could represent 01101 in binary, which is 13.
//    For all leaves in the tree, consider the numbers represented by the path from the root to that leaf. Return the sum of these numbers.
//
//    The test cases are generated so that the answer fits in a 32-bits integer.
//
//
//
//    Example 1:
//
//
//    Input: root = [1,0,1,0,1,0,1]
//    Output: 22
//    Explanation: (100) + (101) + (110) + (111) = 4 + 5 + 6 + 7 = 22
//    Example 2:
//
//    Input: root = [0]
//    Output: 0
//
//
//    Constraints:
//
//    The number of nodes in the tree is in the range [1, 1000].
//    Node.val is 0 or 1.