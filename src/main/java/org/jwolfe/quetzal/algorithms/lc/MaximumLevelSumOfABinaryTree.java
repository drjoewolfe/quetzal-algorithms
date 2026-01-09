package org.jwolfe.quetzal.algorithms.lc;

import java.util.LinkedList;
import java.util.Queue;

public class MaximumLevelSumOfABinaryTree {
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
        public int maxLevelSum(TreeNode root) {
            if (root == null) {
                return 0;
            }

            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);

            int level = 0;
            int maxLevel = 1;
            int maxLevelSum = Integer.MIN_VALUE;

            while (!queue.isEmpty()) {
                level++;

                int levelSum = 0;
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode node = queue.poll();
                    levelSum += node.val;

                    if (node.left != null) {
                        queue.offer(node.left);
                    }

                    if (node.right != null) {
                        queue.offer(node.right);
                    }
                }

                if (levelSum > maxLevelSum) {
                    maxLevelSum = levelSum;
                    maxLevel = level;
                }
            }

            return maxLevel;
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

//    1161. Maximum Level Sum of a Binary Tree
//    Medium
//    Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.
//
//    Return the smallest level x such that the sum of all the values of nodes at level x is maximal.
//
//
//
//    Example 1:
//
//
//    Input: root = [1,7,0,7,-8,null,null]
//    Output: 2
//    Explanation:
//    Level 1 sum = 1.
//    Level 2 sum = 7 + 0 = 7.
//    Level 3 sum = 7 + -8 = -1.
//    So we return the level with the maximum sum which is level 2.
//    Example 2:
//
//    Input: root = [989,null,10250,98693,-89388,null,null,null,-32127]
//    Output: 2
//
//
//    Constraints:
//
//    The number of nodes in the tree is in the range [1, 104].
//    -105 <= Node.val <= 105