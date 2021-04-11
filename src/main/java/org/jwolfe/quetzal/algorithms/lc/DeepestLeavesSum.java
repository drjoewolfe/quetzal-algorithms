package org.jwolfe.quetzal.algorithms.lc;

public class DeepestLeavesSum {
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
        int deepestSum;
        int deepestLevel;

        public int deepestLeavesSum(TreeNode root) {
            deepestSum = 0;
            deepestLevel = 0;

            deepestLeavesSum(root, 0);

            return deepestSum;
        }

        private void deepestLeavesSum(TreeNode root, int level) {
            if(root == null) {
                return;
            }

            if(level == deepestLevel) {
                deepestSum += root.val;
            } else if(level > deepestLevel) {
                deepestLevel = level;
                deepestSum = root.val;
            }

            deepestLeavesSum(root.left, level + 1);
            deepestLeavesSum(root.right, level + 1);
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
}

//    1302. Deepest Leaves Sum
//    Medium
//    Given the root of a binary tree, return the sum of values of its deepest leaves.
//
//
//    Example 1:
//
//
//    Input: root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
//    Output: 15
//    Example 2:
//
//    Input: root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
//    Output: 19
//
//
//    Constraints:
//
//    The number of nodes in the tree is in the range [1, 104].
//    1 <= Node.val <= 100