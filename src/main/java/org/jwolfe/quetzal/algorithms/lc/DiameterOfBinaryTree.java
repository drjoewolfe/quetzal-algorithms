package org.jwolfe.quetzal.algorithms.lc;

public class DiameterOfBinaryTree {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    class Solution {
        private int diameter;

        public int diameterOfBinaryTree(TreeNode root) {
            diameter = 0;
            computeDiameter(root);

            return diameter;
        }

        private int computeDiameter(TreeNode root) {
            if(root == null) {
                return 0;
            }

            int maxLeftLength = computeDiameter(root.left);
            int maxRightLength = computeDiameter(root.right);

            int lengthIncludingRoot = maxLeftLength + maxRightLength;
            diameter = Math.max(diameter, lengthIncludingRoot);

            return Math.max(maxLeftLength, maxRightLength) + 1;
        }
    }

    class Solution_Correct_2 {
        private int diameter;

        public int diameterOfBinaryTree(TreeNode root) {
            diameter = 0;
            computeDiameterOfBinaryTree(root);
            return diameter;
        }

        public int computeDiameterOfBinaryTree(TreeNode root) {
            if(root == null) {
                return 0;
            }

            int leftRadius = computeDiameterOfBinaryTree(root.left);
            int rightRadius = computeDiameterOfBinaryTree(root.right);

            int diameterThroughHere = leftRadius + rightRadius;
            diameter = Math.max(diameter, diameterThroughHere);

            return Math.max(leftRadius, rightRadius) + 1;
        }
    }

    class Solution_Correct_1 {
        private int diameter;

        public int diameterOfBinaryTree(TreeNode root) {
            diameter = 0;

            longestPath(root);
            return diameter;
        }

        private int longestPath(TreeNode root) {
            if(root == null) {
                return 0;
            }

            int leftDepth = longestPath(root.left);
            int rightDepth = longestPath(root.right);

            int maxRootPath = leftDepth + rightDepth;
            diameter = Math.max(diameter, maxRootPath);

            int maxDepth = 1 + Math.max(leftDepth, rightDepth);
            return maxDepth;
        }
    }

    // [1,2,3,4,5]
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}

//    543. Diameter of Binary Tree
//    Easy
//    Given the root of a binary tree, return the length of the diameter of the tree.
//
//    The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
//
//    The length of a path between two nodes is represented by the number of edges between them.
//
//
//
//    Example 1:
//
//
//    Input: root = [1,2,3,4,5]
//    Output: 3
//    Explanation: 3 is the length of the path [4,2,1,3] or [5,2,1,3].
//    Example 2:
//
//    Input: root = [1,2]
//    Output: 1
//
//
//    Constraints:
//
//    The number of nodes in the tree is in the range [1, 104].
//    -100 <= Node.val <= 100