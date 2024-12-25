package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FindLargestValueInEachTreeRow {
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
        public List<Integer> largestValues(TreeNode root) {
            List<Integer> results = new ArrayList<>();
            if (root == null) {
                return results;
            }

            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);

            while (!queue.isEmpty()) {
                int size = queue.size();

                int levelMax = Integer.MIN_VALUE;
                for (int i = 0; i < size; i++) {
                    var node = queue.poll();
                    levelMax = Math.max(levelMax, node.val);

                    if (node.left != null) {
                        queue.offer(node.left);
                    }

                    if (node.right != null) {
                        queue.offer(node.right);
                    }
                }

                results.add(levelMax);
            }

            return results;
        }
    }

    class Solution_Correct_1 {
        public List<Integer> largestValues(TreeNode root) {
            List<Integer> results = new ArrayList<>();
            if (root == null) {
                return results;
            }

            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                int size = queue.size();
                int max = Integer.MIN_VALUE;
                for (int i = 0; i < size; i++) {
                    TreeNode node = queue.poll();
                    max = Math.max(max, node.val);

                    if (node.left != null) {
                        queue.offer(node.left);
                    }

                    if (node.right != null) {
                        queue.offer(node.right);
                    }
                }

                results.add(max);
            }

            return results;
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

//    515. Find Largest Value in Each Tree Row
//    Medium
//    Given the root of a binary tree, return an array of the largest value in each row of the tree (0-indexed).
//
//
//
//    Example 1:
//
//
//    Input: root = [1,3,2,5,3,null,9]
//    Output: [1,3,9]
//    Example 2:
//
//    Input: root = [1,2,3]
//    Output: [1,3]
//
//
//    Constraints:
//
//    The number of nodes in the tree will be in the range [0, 104].
//    -231 <= Node.val <= 231 - 1