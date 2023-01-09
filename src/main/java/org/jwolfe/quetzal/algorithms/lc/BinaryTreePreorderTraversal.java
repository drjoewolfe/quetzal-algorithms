package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreePreorderTraversal {
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
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> traversal = new ArrayList<>();
            if (root == null) {
                return traversal;
            }

            Stack<TreeNode> stack = new Stack<>();
            stack.add(root);

            while (!stack.isEmpty()) {
                TreeNode node = stack.pop();
                traversal.add(node.val);

                if (node.right != null) {
                    stack.push(node.right);
                }

                if (node.left != null) {
                    stack.push(node.left);
                }
            }

            return traversal;
        }
    }

    class Solution_Correct_2 {
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> traversal = new ArrayList<>();

            preorderTraversal(root, traversal);
            return traversal;
        }

        private void preorderTraversal(TreeNode root, List<Integer> traversal) {
            if (root == null) {
                return;
            }

            traversal.add(root.val);
            preorderTraversal(root.left, traversal);
            preorderTraversal(root.right, traversal);
        }
    }

    class Solution_Correct_1 {
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> preorder = new ArrayList<>();
            if (root == null) {
                return preorder;
            }

            Stack<TreeNode> stack = new Stack<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                TreeNode node = stack.pop();

                preorder.add(node.val);
                if (node.right != null) {
                    stack.push(node.right);
                }
                if (node.left != null) {
                    stack.push(node.left);
                }
            }

            return preorder;
        }
    }

    class Solution_Recursive {
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> preorder = new ArrayList<>();
            preorderTraversal(root, preorder);

            return preorder;
        }

        private void preorderTraversal(TreeNode root, List<Integer> preorder) {
            if (root == null) {
                return;
            }

            preorder.add(root.val);
            preorderTraversal(root.left, preorder);
            preorderTraversal(root.right, preorder);
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

//    144. Binary Tree Preorder Traversal
//    Easy
//    Given the root of a binary tree, return the preorder traversal of its nodes' values.
//
//
//
//    Example 1:
//
//
//    Input: root = [1,null,2,3]
//    Output: [1,2,3]
//    Example 2:
//
//    Input: root = []
//    Output: []
//    Example 3:
//
//    Input: root = [1]
//    Output: [1]
//
//
//    Constraints:
//
//    The number of nodes in the tree is in the range [0, 100].
//    -100 <= Node.val <= 100
//
//
//    Follow up: Recursive solution is trivial, could you do it iteratively?