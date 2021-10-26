package org.jwolfe.quetzal.algorithms.lc;

public class InvertBinaryTree {
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
        public TreeNode invertTree(TreeNode root) {
            if(root == null) {
                return null;
            }

            invertTree(root.left);
            invertTree(root.right);

            swapChildren(root);
            return root;
        }

        private void swapChildren(TreeNode root) {
            TreeNode left = root.left;
            root.left = root.right;
            root.right = left;
        }
    }

    class Solution_Correct_1 {
        public TreeNode invertTree(TreeNode root) {
            if(root == null) {
                return null;
            }

            invertTree(root.left);
            invertTree(root.right);

            TreeNode right = root.right;
            root.right = root.left;
            root.left = right;

            return root;
        }
    }

     public class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode(int x) { val = x; }
     }
}

//    226. Invert Binary Tree
//    Easy
//    Given the root of a binary tree, invert the tree, and return its root.
//
//
//
//    Example 1:
//
//
//    Input: root = [4,2,7,1,3,6,9]
//    Output: [4,7,2,9,6,3,1]
//    Example 2:
//
//
//    Input: root = [2,1,3]
//    Output: [2,3,1]
//    Example 3:
//
//    Input: root = []
//    Output: []
//
//
//    Constraints:
//
//    The number of nodes in the tree is in the range [0, 100].
//    -100 <= Node.val <= 100