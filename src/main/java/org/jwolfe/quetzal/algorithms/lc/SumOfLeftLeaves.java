package org.jwolfe.quetzal.algorithms.lc;

public class SumOfLeftLeaves {
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
        public int sumOfLeftLeaves(TreeNode root) {
            return sumOfLeftLeaves(root, null);
        }

        private int sumOfLeftLeaves(TreeNode node, TreeNode parent) {
            if(node == null) {
                return 0;
            }

            if(node.left == null && node.right == null) {
                if(parent == null || node != parent.left) {
                    return 0;
                }

                return node.val;
            }

            return sumOfLeftLeaves(node.left, node) + sumOfLeftLeaves(node.right, node);
        }
    }

    class Solution_Correct_1 {
        public int sumOfLeftLeaves(TreeNode root) {
            return sumOfLeftLeaves(root, null);
        }

        private int sumOfLeftLeaves(TreeNode root, TreeNode parent) {
            if(root == null) {
                return 0;
            }

            int sum = 0;
            if(root.left == null && root.right == null
                    && parent != null && root == parent.left) {
                sum += root.val;
            } else {
                sum += sumOfLeftLeaves(root.left, root) + sumOfLeftLeaves(root.right, root);
            }

            return sum;
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

//    404. Sum of Left Leaves
//    Easy
//    Given the root of a binary tree, return the sum of all left leaves.
//
//
//
//    Example 1:
//
//
//    Input: root = [3,9,20,null,null,15,7]
//    Output: 24
//    Explanation: There are two left leaves in the binary tree, with values 9 and 15 respectively.
//    Example 2:
//
//    Input: root = [1]
//    Output: 0
//
//
//    Constraints:
//
//    The number of nodes in the tree is in the range [1, 1000].
//    -1000 <= Node.val <= 1000
