package org.jwolfe.quetzal.algorithms.lc;

public class MinimumDistanceBetweenBSTNodes {
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
        private int minValue;
        private TreeNode prev;

        public int minDiffInBST(TreeNode root) {
            minValue = Integer.MAX_VALUE;

            inorder(root);
            return minValue;
        }

        private void inorder(TreeNode root) {
            if(root == null) {
                return;
            }

            inorder(root.left);

            if(prev != null) {
                minValue = Math.min(minValue, root.val - prev.val);
            }
            prev = root;

            inorder(root.right);
        }
    }

    class Solution_Correct_1 {
        public int minDiffInBST(TreeNode root) {
            if(root == null) {
                return Integer.MAX_VALUE;
            }

            int minDistance = Integer.MAX_VALUE;

            TreeNode predecessor = inorderPredecessor(root);
            TreeNode successor = inorderSuccessor(root);

            if(predecessor != null) {
                int leftMin = minDiffInBST(root.left);
                minDistance = Math.min(minDistance,
                        Math.min(leftMin,
                                root.val - predecessor.val));
            }

            if(successor != null) {
                int rightMin = minDiffInBST(root.right);
                minDistance = Math.min(minDistance,
                        Math.min(rightMin,
                                successor.val - root.val));
            }

            return minDistance;
        }

        private TreeNode inorderPredecessor(TreeNode root) {
            if(root.left == null) {
                return null;
            }

            root = root.left;
            while(root.right != null) {
                root = root.right;
            }

            return root;
        }

        private TreeNode inorderSuccessor(TreeNode root) {
            if(root.right == null) {
                return null;
            }

            root = root.right;
            while(root.left != null) {
                root = root.left;
            }

            return root;
        }
    }


    class Solution_Adjacent_WrongForQ {
        public int minDiffInBST(TreeNode root) {
            if(root == null) {
                return Integer.MAX_VALUE;
            }

            int minDistance = Integer.MAX_VALUE;
            if(root.left != null) {
                minDistance = Math.min(minDistance,
                        Math.min(minDiffInBST(root.left),
                                Math.abs(root.val - root.left.val)));
            }

            if(root.right != null) {
                minDistance = Math.min(minDistance,
                        Math.min(minDiffInBST(root.right),
                                Math.abs(root.val - root.right.val)));
            }

            return minDistance;
        }
    }

    class Solution_Incorrect {
        public int minDiffInBST(TreeNode root) {
            if(root == null) {
                return Integer.MAX_VALUE;
            }

            int min = Integer.MAX_VALUE;

            if(root.left != null) {
                int leftDiff = root.val - root.left.val;
                min = Math.min(min, leftDiff);
                min = Math.min(min, minDiffInBST(root.left));
            }

            if(root.right != null) {
                int rightDiff = root.right.val - root.val;
                min = Math.min(min, rightDiff);
                min = Math.min(min, minDiffInBST(root.right));
            }

            return min;
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

// [4,2,6,1,3]
// [90,69,null,49,89,null,52]
}

//    783. Minimum Distance Between BST Nodes
//    Easy
//    Given the root of a Binary Search Tree (BST), return the minimum difference between the values of any two different nodes in the tree.
//
//
//
//    Example 1:
//
//
//    Input: root = [4,2,6,1,3]
//    Output: 1
//    Example 2:
//
//
//    Input: root = [1,0,48,null,null,12,49]
//    Output: 1
//
//
//    Constraints:
//
//    The number of nodes in the tree is in the range [2, 100].
//    0 <= Node.val <= 105
//
//
//    Note: This question is the same as 530: https://leetcode.com/problems/minimum-absolute-difference-in-bst/