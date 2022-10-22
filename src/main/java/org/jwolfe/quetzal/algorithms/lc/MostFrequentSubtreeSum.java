package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MostFrequentSubtreeSum {
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
        public int[] findFrequentTreeSum(TreeNode root) {
            if (root == null) {
                return new int[0];
            }

            Map<Integer, Integer> sumFrequencies = new HashMap<>();
            computeTreeSumFrequencies(root, sumFrequencies);

            int maxFrequency = 0;
            Set<Integer> maxFrequencySums = new HashSet<>();
            for (var entry : sumFrequencies.entrySet()) {
                Integer sum = entry.getKey();
                Integer frequency = entry.getValue();

                if (frequency > maxFrequency) {
                    maxFrequency = frequency;
                    maxFrequencySums.clear();
                    maxFrequencySums.add(sum);
                } else if (frequency == maxFrequency) {
                    maxFrequencySums.add(sum);
                }
            }

            int[] results = new int[maxFrequencySums.size()];
            int i = 0;
            for (Integer sum : maxFrequencySums) {
                results[i++] = sum;
            }

            return results;
        }

        private int computeTreeSumFrequencies(TreeNode root, Map<Integer, Integer> sumFrequencies) {
            if (root == null) {
                return 0;
            }

            int sum = computeTreeSumFrequencies(root.left, sumFrequencies);
            sum += computeTreeSumFrequencies(root.right, sumFrequencies);

            sum += root.val;
            sumFrequencies.put(sum, sumFrequencies.getOrDefault(sum, 0) + 1);

            return sum;
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

//    508. Most Frequent Subtree Sum
//    Medium
//    Given the root of a binary tree, return the most frequent subtree sum. If there is a tie, return all the values with the highest frequency in any order.
//
//    The subtree sum of a node is defined as the sum of all the node values formed by the subtree rooted at that node (including the node itself).
//
//
//
//    Example 1:
//
//
//    Input: root = [5,2,-3]
//    Output: [2,-3,4]
//    Example 2:
//
//
//    Input: root = [5,2,-5]
//    Output: [2]
//
//
//    Constraints:
//
//    The number of nodes in the tree is in the range [1, 104].
//    -105 <= Node.val <= 105