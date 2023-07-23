package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class AllPossibleFullBinaryTrees {
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
        public List<TreeNode> allPossibleFBT(int n) {
            Map<Integer, List<TreeNode>> memo = new HashMap<>();
            memo.put(1, Arrays.asList(new TreeNode()));

            return allPossibleFBT(n, memo);
        }

        public List<TreeNode> allPossibleFBT(int n, Map<Integer, List<TreeNode>> memo) {
            if (n < 1) {
                return new ArrayList<>();
            }

            if (memo.containsKey(n)) {
                return memo.get(n);
            }

            List<TreeNode> list = new ArrayList<TreeNode>();
            for (int i = 1; i < n; i++) {
                var leftNodes = allPossibleFBT(i, memo);
                var rightNodes = allPossibleFBT(n - i - 1, memo);

                for (var leftNode : leftNodes) {
                    for (var rightNode : rightNodes) {
                        TreeNode root = new TreeNode();
                        root.left = leftNode;
                        root.right = rightNode;

                        list.add(root);
                    }
                }
            }

            memo.put(n, list);
            return list;
        }
    }

    class Solution_Recursive {
        public List<TreeNode> allPossibleFBT(int n) {
            List<TreeNode> list = new ArrayList<TreeNode>();
            if (n < 1) {
                return list;
            }

            if (n == 1) {
                TreeNode node = new TreeNode();
                list.add(node);

                return list;
            }

            for (int i = 1; i < n; i++) {
                var leftNodes = allPossibleFBT(i);
                var rightNodes = allPossibleFBT(n - i - 1);

                for (var leftNode : leftNodes) {
                    for (var rightNode : rightNodes) {
                        TreeNode root = new TreeNode();
                        root.left = leftNode;
                        root.right = rightNode;

                        list.add(root);
                    }
                }
            }

            return list;
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

//    894. All Possible Full Binary Trees
//    Medium
//    Given an integer n, return a list of all possible full binary trees with n nodes. Each node of each tree in the answer must have Node.val == 0.
//
//    Each element of the answer is the root node of one possible tree. You may return the final list of trees in any order.
//
//    A full binary tree is a binary tree where each node has exactly 0 or 2 children.
//
//
//
//    Example 1:
//
//
//    Input: n = 7
//    Output: [[0,0,0,null,null,0,0,null,null,0,0],[0,0,0,null,null,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,null,null,null,null,0,0],[0,0,0,0,0,null,null,0,0]]
//    Example 2:
//
//    Input: n = 3
//    Output: [[0,0,0]]
//
//
//    Constraints:
//
//    1 <= n <= 20