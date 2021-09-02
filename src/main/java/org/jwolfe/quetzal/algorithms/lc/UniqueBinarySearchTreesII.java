package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.List;

public class UniqueBinarySearchTreesII {
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
        public List<TreeNode> generateTrees(int n) {
            if(n < 1) {
                return new ArrayList<>();
            }

            return generateTrees(1, n);
        }

        private List<TreeNode> generateTrees(int start, int end) {
            List<TreeNode> list = new ArrayList<>();
            if(start > end) {
                list.add(null);
                return list;
            }

            for(int i = start; i <= end; i++) {
                var leftTrees = generateTrees(start, i - 1);
                var rightTrees = generateTrees(i + 1, end);

                for(var lTree : leftTrees) {
                    for(var rTree: rightTrees) {
                        TreeNode node = new TreeNode(i);
                        node.left = lTree;
                        node.right = rTree;

                        list.add(node);
                    }
                }
            }

            return list;
        }
    }

    class Solution_Correct_2 {
        public List<TreeNode> generateTrees(int n) {
            List<TreeNode> trees = new ArrayList<>();
            if(n == 0) {
                return trees;
            }

            trees.addAll(generateTrees(1, n));
            return trees;
        }

        private List<TreeNode> generateTrees(int left, int right) {
            List<TreeNode> trees = new ArrayList<>();
            if(left > right) {
                trees.add(null);
                return trees;
            }

            if(left == right) {
                trees.add(new TreeNode(left));
                return trees;
            }

            for(int curr = left; curr <= right; curr++) {
                List<TreeNode> leftTrees = generateTrees(left, curr - 1);
                List<TreeNode> rightTrees = generateTrees(curr + 1, right);

                for(TreeNode leftNode : leftTrees) {
                    for(TreeNode rightNode : rightTrees) {
                        TreeNode node = new TreeNode(curr);
                        node.left = leftNode;
                        node.right = rightNode;

                        trees.add(node);
                    }
                }
            }

            return trees;
        }
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

//    95. Unique Binary Search Trees II
//    Medium
//    Given an integer n, return all the structurally unique BST's (binary search trees), which has exactly n nodes of unique values from 1 to n. Return the answer in any order.
//
//
//
//    Example 1:
//
//
//    Input: n = 3
//    Output: [[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
//    Example 2:
//
//    Input: n = 1
//    Output: [[1]]
//
//
//    Constraints:
//
//    1 <= n <= 8