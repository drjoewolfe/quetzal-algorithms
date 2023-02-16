package org.jwolfe.quetzal.algorithms.lc;

public class MaximumDepthOfBinaryTree {
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
        public int maxDepth(TreeNode root) {
            if(root == null) {
                return 0;
            }

            return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
        }
    }

    class Solution_Correct_2 {
        public int maxDepth(TreeNode root) {
            if(root == null) {
                return 0;
            }

            return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
        }
    }

    class Solution_Correct_1 {
        public int maxDepth(TreeNode root) {
            return maxDepth(root, 0);
        }

        private int maxDepth(TreeNode root, int depth) {
            if (root == null) {
                return depth;
            }

            return Math.max(maxDepth(root.left, depth + 1), maxDepth(root.right, depth + 1));
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

//    104. Maximum Depth of Binary Tree
//    Easy
//    Given the root of a binary tree, return its maximum depth.
//
//    A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
//
//
//
//    Example 1:
//
//
//    Input: root = [3,9,20,null,null,15,7]
//    Output: 3
//    Example 2:
//
//    Input: root = [1,null,2]
//    Output: 2
//
//
//    Constraints:
//
//    The number of nodes in the tree is in the range [0, 104].
//    -100 <= Node.val <= 100