package org.jwolfe.quetzal.algorithms.lc;

public class MinimumDepthOfBinaryTree {
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
        public int minDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }

            if (root.left == null && root.right == null) {
                return 1;
            }

            int leftDepth = Integer.MAX_VALUE;
            int rightDepth = Integer.MAX_VALUE;

            if (root.left != null) {
                leftDepth = minDepth(root.left);
            }

            if (root.right != null) {
                rightDepth = minDepth(root.right);
            }

            return Math.min(leftDepth, rightDepth) + 1;
        }
    }

    class Solution_Correct_1 {
        public int minDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }

            if (root.left == null && root.right == null) {
                return 1;
            }

            int minDepthOfLeftTree = Integer.MAX_VALUE;
            int minDepthOfRightTree = Integer.MAX_VALUE;

            if (root.left != null) {
                minDepthOfLeftTree = minDepth(root.left);
            }

            if (root.right != null) {
                minDepthOfRightTree = minDepth(root.right);
            }

            return Math.min(minDepthOfLeftTree, minDepthOfRightTree) + 1;
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

// [3,9,20,null,null,15,7]
// [2,null,3,null,4,null,5,null,6]
}

//    111. Minimum Depth of Binary Tree
//    Easy
//    Given a binary tree, find its minimum depth.
//
//    The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
//
//    Note: A leaf is a node with no children.
//
//
//
//    Example 1:
//
//
//    Input: root = [3,9,20,null,null,15,7]
//    Output: 2
//    Example 2:
//
//    Input: root = [2,null,3,null,4,null,5,null,6]
//    Output: 5
//
//
//    Constraints:
//
//    The number of nodes in the tree is in the range [0, 105].
//    -1000 <= Node.val <= 1000