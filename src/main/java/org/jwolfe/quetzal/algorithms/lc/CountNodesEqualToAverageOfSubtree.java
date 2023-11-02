package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;

public class CountNodesEqualToAverageOfSubtree {
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
        public int averageOfSubtree(TreeNode root) {
            Map<TreeNode, Integer> averageMap = new HashMap<>();
            computeAverages(root, averageMap);

            int count = 0;
            for (var entry : averageMap.entrySet()) {
                if (entry.getKey().val == entry.getValue()) {
                    count++;
                }
            }

            return count;
        }

        private int[] computeAverages(TreeNode root, Map<TreeNode, Integer> averageMap) {
            if (root == null) {
                return new int[]{0, 0};
            }

            int[] left = computeAverages(root.left, averageMap);
            int[] right = computeAverages(root.right, averageMap);

            int sum = left[0] + right[0] + root.val;
            int count = left[1] + right[1] + 1;

            int average = sum / count;
            averageMap.put(root, average);

            return new int[]{sum, count};
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
}

//    2265. Count Nodes Equal to Average of Subtree
//    Medium
//    Given the root of a binary tree, return the number of nodes where the value of the node is equal to the average of the values in its subtree.
//
//    Note:
//
//    The average of n elements is the sum of the n elements divided by n and rounded down to the nearest integer.
//    A subtree of root is a tree consisting of root and all of its descendants.
//
//
//    Example 1:
//
//
//    Input: root = [4,8,5,0,1,null,6]
//    Output: 5
//    Explanation:
//    For the node with value 4: The average of its subtree is (4 + 8 + 5 + 0 + 1 + 6) / 6 = 24 / 6 = 4.
//    For the node with value 5: The average of its subtree is (5 + 6) / 2 = 11 / 2 = 5.
//    For the node with value 0: The average of its subtree is 0 / 1 = 0.
//    For the node with value 1: The average of its subtree is 1 / 1 = 1.
//    For the node with value 6: The average of its subtree is 6 / 1 = 6.
//    Example 2:
//
//
//    Input: root = [1]
//    Output: 1
//    Explanation: For the node with value 1: The average of its subtree is 1 / 1 = 1.
//
//
//    Constraints:
//
//    The number of nodes in the tree is in the range [1, 1000].
//    0 <= Node.val <= 1000