package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.function.Consumer;

public class BinaryTreeInorderTraversal {
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
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> traversal = new ArrayList<>();

            Stack<TreeNode> stack = new Stack();
            while(root != null || !stack.isEmpty()) {
                while(root != null) {
                    stack.push(root);
                    root = root.left;
                }

                root = stack.pop();
                traversal.add(root.val);

                root = root.right;
            }

            return traversal;
        }
    }

    class Solution_Recursive_1 {
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> traversal = new ArrayList<>();

            inorderTraversal(root, traversal);
            return traversal;
        }

        private void inorderTraversal(TreeNode root, List<Integer> traversal) {
            if(root == null) {
                return;
            }

            inorderTraversal(root.left, traversal);
            traversal.add(root.val);
            inorderTraversal(root.right, traversal);
        }
    }


    class Solution_Correct_1 {
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> inorder = new ArrayList<>();
            if(root == null) {
                return inorder;
            }

            Consumer<TreeNode> visitor = node -> inorder.add(node.val);

            Stack<TreeNode> stack = new Stack<>();
            while(root != null || !stack.isEmpty()) {
                while(root != null) {
                    stack.push(root);
                    root = root.left;
                }

                root = stack.pop();
                visitor.accept(root);
                root = root.right;
            }

            return inorder;
        }
    }

    class Solution_Recursive {
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> inorder = new ArrayList<>();
            traverseInorder(root, node -> inorder.add(node.val));

            return inorder;
        }

        public void traverseInorder(TreeNode root, Consumer<TreeNode> visitor) {
            if(root == null) {
                return;
            }

            traverseInorder(root.left, visitor);
            if(visitor != null) {
                visitor.accept(root);
            }

            traverseInorder(root.right, visitor);
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

//    94. Binary Tree Inorder Traversal
//    Easy
//    Given the root of a binary tree, return the inorder traversal of its nodes' values.
//
//
//
//    Example 1:
//
//
//    Input: root = [1,null,2,3]
//    Output: [1,3,2]
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