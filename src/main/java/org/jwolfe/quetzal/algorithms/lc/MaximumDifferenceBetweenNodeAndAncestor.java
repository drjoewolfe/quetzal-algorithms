package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.List;

public class MaximumDifferenceBetweenNodeAndAncestor {
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
        public int maxAncestorDiff(TreeNode root) {
            if(root == null) {
                return 0;
            }

            return maxAncestorDiff(root, root.val, root.val);
        }

        private int maxAncestorDiff(TreeNode root, int min, int max) {
            if(root == null) {
                return 0;
            }

            int maxAbsDiff = Math.max(Math.abs(root.val - min),
                    Math.abs(max - root.val));

            min = Math.min(min, root.val);
            max = Math.max(max, root.val);
            maxAbsDiff = Math.max(maxAbsDiff,
                    maxAncestorDiff(root.left, min, max));
            maxAbsDiff = Math.max(maxAbsDiff,
                    maxAncestorDiff(root.right, min, max));

            return maxAbsDiff;
        }
    }

    class Solution_Correct_2 {
        int  maxAbsoluteDifference;

        public int maxAncestorDiff(TreeNode root) {
            maxAbsoluteDifference = 0;
            maxAncestorDiff(root, root.val, root.val);

            return maxAbsoluteDifference;
        }

        private void maxAncestorDiff(TreeNode root, int min, int max) {
            if(root == null) {
                return;
            }

            int minDiff = Math.abs(root.val - min);
            int maxDiff = Math.abs(root.val - max);
            maxAbsoluteDifference = Math.max(maxAbsoluteDifference, Math.max(minDiff, maxDiff));

            maxAncestorDiff(root.left, Math.min(root.val, min), Math.max(root.val, max));
            maxAncestorDiff(root.right, Math.min(root.val, min), Math.max(root.val, max));
        }
    }

    class Solution_Correct_1 {
        int result = 0;

        public int maxAncestorDiff(TreeNode root) {
            if(root == null) {
                return 0;
            }

            result = 0;
            maxAncestorDiff(root, root.val, root.val);

            return result;
        }

        private void maxAncestorDiff(TreeNode root, int min, int max) {
            if(root == null) {
                return;
            }

            if(root.val < min) {
                result = Math.max(result, Math.abs(root.val - max));
            }

            if(root.val > max) {
                result = Math.max(result, Math.abs(root.val - min));
            }

            maxAncestorDiff(root.left, Math.min(root.val, min), Math.max(root.val, max));
            maxAncestorDiff(root.right, Math.min(root.val, min), Math.max(root.val, max));
        }
    }


    class Solution_Brute {
        public int maxAncestorDiff(TreeNode root) {
            if(root == null) {
                return 0;
            }

            return maxAncestorDiff(root, new ArrayList<>());
        }

        private int maxAncestorDiff(TreeNode root, List<TreeNode> ancestors) {
            if(root == null) {
                return 0;
            }

            int thisMax = 0;
            for(var node : ancestors) {
                thisMax = Math.max(thisMax, Math.abs(node.val - root.val));
            }

            ancestors.add(root);
            int maxLeft = maxAncestorDiff(root.left, ancestors);
            int maxRight = maxAncestorDiff(root.right, ancestors);
            ancestors.remove(ancestors.size() - 1);


            return Math.max(thisMax,
                    Math.max(maxLeft, maxRight));
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

//    1026. Maximum Difference Between Node and Ancestor
//    Medium
//    Given the root of a binary tree, find the maximum value v for which there exist different nodes a and b where v = |a.val - b.val| and a is an ancestor of b.
//
//    A node a is an ancestor of b if either: any child of a is equal to b or any child of a is an ancestor of b.
//
//
//
//    Example 1:
//
//
//    Input: root = [8,3,10,1,6,null,14,null,null,4,7,13]
//    Output: 7
//    Explanation: We have various ancestor-node differences, some of which are given below :
//    |8 - 3| = 5
//    |3 - 7| = 4
//    |8 - 1| = 7
//    |10 - 13| = 3
//    Among all possible differences, the maximum value of 7 is obtained by |8 - 1| = 7.
//    Example 2:
//
//
//    Input: root = [1,null,2,null,0,3]
//    Output: 3
//
//
//    Constraints:
//
//    The number of nodes in the tree is in the range [2, 5000].
//    0 <= Node.val <= 105