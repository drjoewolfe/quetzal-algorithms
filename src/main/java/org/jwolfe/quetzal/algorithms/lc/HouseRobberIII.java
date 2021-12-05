package org.jwolfe.quetzal.algorithms.lc;

public class HouseRobberIII {
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
        public int rob(TreeNode root) {
            if(root == null) {
                return 0;
            }

            var stats = robHelper(root);
            return Math.max(stats.withNode, stats.withoutNode);
        }

        private NodeRobStatistics robHelper(TreeNode root) {
            if(root == null) {
                return new NodeRobStatistics(0, 0);
            }

            var leftStats = robHelper(root.left);
            var rightStats = robHelper(root.right);

            int withNode = root.val + leftStats.withoutNode + rightStats.withoutNode;
            int withoutNode = Math.max(leftStats.withNode, leftStats.withoutNode) + Math.max(rightStats.withNode, rightStats.withoutNode);

            return new NodeRobStatistics(withNode, withoutNode);
        }

        private class NodeRobStatistics {
            int withNode;
            int withoutNode;

            public NodeRobStatistics(int withNode, int withoutNode) {
                this.withNode = withNode;
                this.withoutNode = withoutNode;
            }
        }
    }

    class Solution_Correct_1 {
        public int rob(TreeNode root) {
            Pair pair = robHelper(root);
            // Pair.x = rob root, Pair.y = dont rob root

            return Math.max(pair.x, pair.y);
        }

        private Pair robHelper(TreeNode root) {
            if(root == null) {
                return new Pair(0, 0);
            }

            Pair leftPair = robHelper(root.left);
            Pair rightPair = robHelper(root.right);

            Pair pair = new Pair();
            pair.x = root.val + leftPair.y + rightPair.y;
            pair.y = Math.max(leftPair.x, leftPair.y) + Math.max(rightPair.x, rightPair.y);

            return pair;
        }

        class Pair {
            Integer x;
            Integer y;

            Pair() {

            }

            Pair(int x, int y) {
                this.x = x;
                this.y = y;
            }
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

// [3,2,3,null,3,null,1]
// [3,4,5,1,3,null,1]
// [4,1,null,2,null,3]
}

//    337. House Robber III
//    Medium
//    The thief has found himself a new place for his thievery again. There is only one entrance to this area, called root.
//
//    Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that all houses in this place form a binary tree. It will automatically contact the police if two directly-linked houses were broken into on the same night.
//
//    Given the root of the binary tree, return the maximum amount of money the thief can rob without alerting the police.
//
//
//
//    Example 1:
//
//
//    Input: root = [3,2,3,null,3,null,1]
//    Output: 7
//    Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
//    Example 2:
//
//
//    Input: root = [3,4,5,1,3,null,1]
//    Output: 9
//    Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.
//
//
//    Constraints:
//
//    The number of nodes in the tree is in the range [1, 104].
//    0 <= Node.val <= 104