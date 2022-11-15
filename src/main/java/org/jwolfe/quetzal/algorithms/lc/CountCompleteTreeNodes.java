package org.jwolfe.quetzal.algorithms.lc;

public class CountCompleteTreeNodes {
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
        public int countNodes(TreeNode root) {
            if (root == null) {
                return 0;
            }

            int leftHeight = 1;
            int rightHeight = 1;

            TreeNode node = root;
            while (node.left != null) {
                leftHeight++;
                node = node.left;
            }

            node = root;
            while (node.right != null) {
                rightHeight++;
                node = node.right;
            }

            if (leftHeight == rightHeight) {
                return (int) Math.pow(2, leftHeight) - 1;
            }

            return 1 + countNodes(root.left) + countNodes(root.right);
        }
    }

    class Solution_Correct_1 {
        public int countNodes(TreeNode root) {
            int leftHeight = 0;
            int rightHeight = 0;

            TreeNode leftNode = root;
            TreeNode rightNode = root;

            while (leftNode != null) {
                leftHeight++;
                leftNode = leftNode.left;
            }

            while (rightNode != null) {
                rightHeight++;
                rightNode = rightNode.right;
            }

            if (leftHeight == rightHeight) {
                // Perfect tree
                return (int) Math.pow(2, leftHeight) - 1;
            }

            return countNodesHelper(root);
        }

        private int countNodesHelper(TreeNode root) {
            if (root == null) {
                return 0;
            }

            return 1 + countNodesHelper(root.left) + countNodesHelper(root.right);
        }
    }

    class Solution_Optimized {
        public int countNodes(TreeNode root) {
            if (root == null) {
                return 0;
            }

            return 1 + countNodes(root.left) + countNodes(root.right);
        }
    }


    class Solution_Standard {
        private int count;

        public int countNodes(TreeNode root) {
            if (root == null) {
                return 0;
            }

            count = 0;
            countNodesHelper(root);

            return count;
        }

        private void countNodesHelper(TreeNode root) {
            if (root == null) {
                return;
            }

            count++;
            countNodesHelper(root.left);
            countNodesHelper(root.right);
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

//    222. Count Complete Tree Nodes
//    Medium
//    Given the root of a complete binary tree, return the number of the nodes in the tree.
//
//    According to Wikipedia, every level, except possibly the last, is completely filled in a complete binary tree, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
//
//    Design an algorithm that runs in less than O(n) time complexity.
//
//
//
//    Example 1:
//
//
//    Input: root = [1,2,3,4,5,6]
//    Output: 6
//    Example 2:
//
//    Input: root = []
//    Output: 0
//    Example 3:
//
//    Input: root = [1]
//    Output: 1
//
//
//    Constraints:
//
//    The number of nodes in the tree is in the range [0, 5 * 104].
//    0 <= Node.val <= 5 * 104
//    The tree is guaranteed to be complete.