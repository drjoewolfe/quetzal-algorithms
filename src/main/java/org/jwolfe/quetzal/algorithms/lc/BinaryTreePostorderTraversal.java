package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreePostorderTraversal {
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
        public List<Integer> postorderTraversal(TreeNode root) {
            List<Integer> traversal = new ArrayList<>();
            if (root == null) {
                return traversal;
            }

            Stack<TreeNode> preStack = new Stack<>();
            Stack<TreeNode> postStack = new Stack<>();

            preStack.push(root);
            while (!preStack.isEmpty()) {
                var node = preStack.pop();
                postStack.push(node);

                if (node.left != null) {
                    preStack.push(node.left);
                }

                if (node.right != null) {
                    preStack.push(node.right);
                }
            }

            while (!postStack.isEmpty()) {
                traversal.add(postStack.pop().val);
            }

            return traversal;
        }
    }

    class Solution_Correct_1 {
        public List<Integer> postorderTraversal(TreeNode root) {
            List<Integer> postOrder = new ArrayList<>();
            if (root == null) {
                return postOrder;
            }

            Stack<TreeNode> preStack = new Stack<>();
            Stack<TreeNode> postStack = new Stack<>();

            preStack.push(root);
            while (!preStack.isEmpty()) {
                TreeNode node = preStack.pop();
                postStack.push(node);

                if (node.left != null) {
                    preStack.push(node.left);
                }

                if (node.right != null) {
                    preStack.push(node.right);
                }
            }

            while (!postStack.isEmpty()) {
                postOrder.add(postStack.pop().val);
            }

            return postOrder;
        }
    }

    class Solution_Recursive {
        public List<Integer> postorderTraversal(TreeNode root) {
            List<Integer> postOrder = new ArrayList<>();
            postorderTraversal(root, postOrder);

            return postOrder;
        }

        private void postorderTraversal(TreeNode root, List<Integer> postOrder) {
            if (root == null) {
                return;
            }

            postorderTraversal(root.left, postOrder);
            postorderTraversal(root.right, postOrder);

            postOrder.add(root.val);
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

//    145. Binary Tree Postorder Traversal
//    Easy
//    Given the root of a binary tree, return the postorder traversal of its nodes' values.
//
//
//
//    Example 1:
//
//
//    Input: root = [1,null,2,3]
//    Output: [3,2,1]
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
//    The number of the nodes in the tree is in the range [0, 100].
//    -100 <= Node.val <= 100
//
//
//    Follow up: Recursive solution is trivial, could you do it iteratively?
