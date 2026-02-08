package org.jwolfe.quetzal.algorithms.lc;

public class BalancedBinaryTree {
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
        public boolean isBalanced(TreeNode root) {
            if (root == null) {
                return true;
            }

            if (!isBalanced(root.left) || !isBalanced(root.right)) {
                return false;
            }

            int leftHeight = height(root.left);
            int rightHeight = height(root.right);

            if (Math.abs(leftHeight - rightHeight) <= 1) {
                return true;
            }

            return false;
        }

        private int height(TreeNode root) {
            if (root == null) {
                return 0;
            }

            int leftHeight = height(root.left);
            int rightHeight = height(root.right);

            return Math.max(leftHeight, rightHeight) + 1;
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

//    110. Balanced Binary Tree
//    Easy
//    Given a binary tree, determine if it is height-balanced.
//
//
//
//    Example 1:
//
//
//    Input: root = [3,9,20,null,null,15,7]
//    Output: true
//    Example 2:
//
//
//    Input: root = [1,2,2,3,3,null,null,4,4]
//    Output: false
//    Example 3:
//
//    Input: root = []
//    Output: true
//
//
//    Constraints:
//
//    The number of nodes in the tree is in the range [0, 5000].
//    -104 <= Node.val <= 104