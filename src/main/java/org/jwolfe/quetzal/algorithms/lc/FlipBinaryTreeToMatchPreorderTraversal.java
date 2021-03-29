package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.List;

public class FlipBinaryTreeToMatchPreorderTraversal {
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
        int index;

        public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
            List<Integer> flips = new ArrayList<>();
            if(root == null) {
                return flips;
            }

            index = 0;
            computeFlips(root, voyage, flips);
            return flips;
        }

        private boolean computeFlips(TreeNode root, int[] voyage, List<Integer> flips) {
            if(root == null) {
                return true;
            }

            if(root.val != voyage[index]) {
                flips.clear();
                flips.add(-1);
                return false;
            }

            index++;
            if(root.left != null && voyage[index] != root.left.val) {
                flips.add(root.val);
                flip(root);
            }

            boolean rv = computeFlips(root.left, voyage, flips);
            if(!rv) {
                return false;
            }

            rv = computeFlips(root.right, voyage, flips);

            return rv;
        }

        private void flip(TreeNode root) {
            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;
        }
    }

    public static class TreeNode {
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

// [1,2]
// [2,1]
}

//    971. Flip Binary Tree To Match Preorder Traversal
//    Medium
//    You are given the root of a binary tree with n nodes, where each node is uniquely assigned a value from 1 to n. You are also given a sequence of n values voyage, which is the desired pre-order traversal of the binary tree.
//
//    Any node in the binary tree can be flipped by swapping its left and right subtrees. For example, flipping node 1 will have the following effect:
//
//
//    Flip the smallest number of nodes so that the pre-order traversal of the tree matches voyage.
//
//    Return a list of the values of all flipped nodes. You may return the answer in any order. If it is impossible to flip the nodes in the tree to make the pre-order traversal match voyage, return the list [-1].
//
//
//
//    Example 1:
//
//
//    Input: root = [1,2], voyage = [2,1]
//    Output: [-1]
//    Explanation: It is impossible to flip the nodes such that the pre-order traversal matches voyage.
//    Example 2:
//
//
//    Input: root = [1,2,3], voyage = [1,3,2]
//    Output: [1]
//    Explanation: Flipping node 1 swaps nodes 2 and 3, so the pre-order traversal matches voyage.
//    Example 3:
//
//
//    Input: root = [1,2,3], voyage = [1,2,3]
//    Output: []
//    Explanation: The tree's pre-order traversal already matches voyage, so no nodes need to be flipped.

