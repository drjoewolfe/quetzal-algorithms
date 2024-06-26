package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.List;

public class BalanceABinarySearchTree {
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
        public TreeNode balanceBST(TreeNode root) {
            if (root == null) {
                return root;
            }

            List<TreeNode> inorder = new ArrayList<>();
            constructInorder(root, inorder);

            return constructTreeFromInorder(inorder, 0, inorder.size() - 1);
        }

        private void constructInorder(TreeNode root, List<TreeNode> inorder) {
            if (root == null) {
                return;
            }

            constructInorder(root.left, inorder);
            inorder.add(root);
            constructInorder(root.right, inorder);
        }

        private TreeNode constructTreeFromInorder(List<TreeNode> inorder, int left, int right) {
            if (left > right) {
                return null;
            }

            int mid = left + (right - left) / 2;
            TreeNode root = inorder.get(mid);
            root.left = constructTreeFromInorder(inorder, left, mid - 1);
            root.right = constructTreeFromInorder(inorder, mid + 1, right);

            return root;
        }
    }

    class Solution_Correct_1 {
        public TreeNode balanceBST(TreeNode root) {
            if (root == null) {
                return null;
            }

            List<TreeNode> inorder = new ArrayList<>();
            traverseInorder(root, inorder);

            return constructTreeFromInorder(inorder, 0, inorder.size() - 1);
        }

        private TreeNode constructTreeFromInorder(List<TreeNode> inorder, int left, int right) {
            if (left > right) {
                return null;
            }

            int mid = left + (right - left) / 2;
            TreeNode root = inorder.get(mid);
            root.left = constructTreeFromInorder(inorder, left, mid - 1);
            root.right = constructTreeFromInorder(inorder, mid + 1, right);

            return root;
        }

        private void traverseInorder(TreeNode root, List<TreeNode> inorder) {
            if (root == null) {
                return;
            }

            traverseInorder(root.left, inorder);
            inorder.add(root);
            traverseInorder(root.right, inorder);
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

//    1382. Balance a Binary Search Tree
//    Medium
//    Given the root of a binary search tree, return a balanced binary search tree with the same node values. If there is more than one answer, return any of them.
//
//    A binary search tree is balanced if the depth of the two subtrees of every node never differs by more than 1.
//
//
//
//    Example 1:
//
//
//    Input: root = [1,null,2,null,3,null,4,null,null]
//    Output: [2,1,3,null,null,null,4]
//    Explanation: This is not the only correct answer, [3,1,4,null,2] is also correct.
//    Example 2:
//
//
//    Input: root = [2,1,3]
//    Output: [2,1,3]
//
//
//    Constraints:
//
//    The number of nodes in the tree is in the range [1, 104].
//    1 <= Node.val <= 105
