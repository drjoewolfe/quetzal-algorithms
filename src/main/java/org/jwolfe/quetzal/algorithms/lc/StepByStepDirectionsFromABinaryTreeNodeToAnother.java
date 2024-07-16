package org.jwolfe.quetzal.algorithms.lc;

public class StepByStepDirectionsFromABinaryTreeNodeToAnother {
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
        public String getDirections(TreeNode root, int startValue, int destValue) {
            TreeNode lca = getLowestCommonAncestor(root, startValue, destValue);

            StringBuilder pathToStart = new StringBuilder();
            StringBuilder pathToDest = new StringBuilder();

            constructPath(lca, startValue, pathToStart);
            constructPath(lca, destValue, pathToDest);

            StringBuilder path = new StringBuilder();
            for (int i = 0; i < pathToStart.length(); i++) {
                path.append("U");
            }

            path.append(pathToDest);

            return path.toString();
        }

        private TreeNode getLowestCommonAncestor(TreeNode root, int startValue, int destValue) {
            if (root == null) {
                return null;
            }

            if (root.val == startValue || root.val == destValue) {
                return root;
            }

            TreeNode leftLca = getLowestCommonAncestor(root.left, startValue, destValue);
            TreeNode rightLca = getLowestCommonAncestor(root.right, startValue, destValue);

            if (leftLca == null) {
                return rightLca;
            } else if (rightLca == null) {
                return leftLca;
            } else {
                return root;
            }
        }

        private boolean constructPath(TreeNode root, int target, StringBuilder path) {
            if (root == null) {
                return false;
            }

            if (root.val == target) {
                return true;
            }

            // Try Left
            path.append("L");
            if (constructPath(root.left, target, path)) {
                return true;
            }

            path.deleteCharAt(path.length() - 1);

            // Try Right
            path.append("R");
            if (constructPath(root.right, target, path)) {
                return true;
            }

            path.deleteCharAt(path.length() - 1);

            return false;
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

//    2096. Step-By-Step Directions From a Binary Tree Node to Another
//    Medium
//    You are given the root of a binary tree with n nodes. Each node is uniquely assigned a value from 1 to n. You are also given an integer startValue representing the value of the start node s, and a different integer destValue representing the value of the destination node t.
//
//    Find the shortest path starting from node s and ending at node t. Generate step-by-step directions of such path as a string consisting of only the uppercase letters 'L', 'R', and 'U'. Each letter indicates a specific direction:
//
//    'L' means to go from a node to its left child node.
//    'R' means to go from a node to its right child node.
//    'U' means to go from a node to its parent node.
//    Return the step-by-step directions of the shortest path from node s to node t.
//
//
//
//    Example 1:
//
//
//    Input: root = [5,1,2,3,null,6,4], startValue = 3, destValue = 6
//    Output: "UURL"
//    Explanation: The shortest path is: 3 → 1 → 5 → 2 → 6.
//    Example 2:
//
//
//    Input: root = [2,1], startValue = 2, destValue = 1
//    Output: "L"
//    Explanation: The shortest path is: 2 → 1.
//
//
//    Constraints:
//
//    The number of nodes in the tree is n.
//    2 <= n <= 105
//    1 <= Node.val <= n
//    All the values in the tree are unique.
//    1 <= startValue, destValue <= n
//    startValue != destValue