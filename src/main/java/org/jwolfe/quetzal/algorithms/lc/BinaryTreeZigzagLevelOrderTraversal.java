package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreeZigzagLevelOrderTraversal {
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
        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            List<List<Integer>> traversal = new ArrayList<>();
            if (root == null) {
                return traversal;
            }

            Stack<TreeNode> zigStack = new Stack<>();
            Stack<TreeNode> zagStack = new Stack<>();

            zigStack.push(root);

            boolean zig = true;
            Stack<TreeNode> stack = zigStack;

            while (!stack.isEmpty()) {
                List<Integer> level = new ArrayList<>();

                Stack<TreeNode> nextStack = null;
                if (zig) {
                    nextStack = zagStack;
                } else {
                    nextStack = zigStack;
                }

                int size = stack.size();
                for (int i = 0; i < size; i++) {
                    TreeNode node = stack.pop();
                    level.add(node.val);

                    if (zig) {
                        if (node.left != null) {
                            nextStack.push(node.left);
                        }

                        if (node.right != null) {
                            nextStack.push(node.right);
                        }
                    } else {
                        if (node.right != null) {
                            nextStack.push(node.right);
                        }

                        if (node.left != null) {
                            nextStack.push(node.left);
                        }
                    }
                }

                traversal.add(level);

                zig = !zig;
                stack = nextStack;
            }

            return traversal;
        }
    }

    class Solution_Correct_1 {
        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            List<List<Integer>> zigzag = new ArrayList<>();
            if (root == null) {
                return zigzag;
            }

            Stack<TreeNode> zigStack = new Stack<>();
            Stack<TreeNode> zagStack = new Stack<>();

            zigStack.push(root);

            boolean ltr = true;
            Stack<TreeNode> stack = zigStack;
            while (!stack.isEmpty()) {
                int n = stack.size();
                List<Integer> level = new ArrayList<>();
                zigzag.add(level);

                for (int i = 0; i < n; i++) {
                    TreeNode node = stack.pop();
                    level.add(node.val);

                    if (ltr) {
                        if (node.left != null) {
                            zagStack.push(node.left);
                        }

                        if (node.right != null) {
                            zagStack.push(node.right);
                        }

                    } else {
                        if (node.right != null) {
                            zigStack.push(node.right);
                        }

                        if (node.left != null) {
                            zigStack.push(node.left);
                        }
                    }
                }

                ltr = !ltr;
                stack = ltr ? zigStack : zagStack;
            }

            return zigzag;
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

//    103. Binary Tree Zigzag Level Order Traversal
//    Medium
//    Given the root of a binary tree, return the zigzag level order traversal of its nodes' values. (i.e., from left to right, then right to left for the next level and alternate between).
//
//
//
//    Example 1:
//
//
//    Input: root = [3,9,20,null,null,15,7]
//    Output: [[3],[20,9],[15,7]]
//    Example 2:
//
//    Input: root = [1]
//    Output: [[1]]
//    Example 3:
//
//    Input: root = []
//    Output: []
//
//
//    Constraints:
//
//    The number of nodes in the tree is in the range [0, 2000].
//    -100 <= Node.val <= 100