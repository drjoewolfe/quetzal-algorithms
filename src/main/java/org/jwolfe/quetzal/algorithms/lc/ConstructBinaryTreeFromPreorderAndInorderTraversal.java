package org.jwolfe.quetzal.algorithms.lc;

public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
    class Solution {
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            if(preorder == null || preorder.length == 0 || inorder == null || inorder.length != preorder.length) {
                return null;
            }

            int n = preorder.length;
            return buildTree(preorder, 0, n - 1, inorder, 0, n - 1);
        }

        private TreeNode buildTree(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
            if(preStart > preEnd) {
                return null;
            }

            int val = preorder[preStart];
            int length = 0;

            for(int i = inStart; i <= inEnd; i++) {
                if(val == inorder[i]) {
                    length = i - inStart;
                    break;
                }
            }

            TreeNode root = new TreeNode(val);
            root.left = buildTree(preorder, preStart + 1, preStart + length, inorder, inStart, inStart + length - 1);
            root.right = buildTree(preorder, preStart + length + 1, preEnd, inorder, inStart + length + 1, inEnd);

            return root;
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

//    105. Construct Binary Tree from Preorder and Inorder Traversal
//    Medium
//    Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree and inorder is the inorder traversal of the same tree, construct and return the binary tree.
//
//
//
//    Example 1:
//
//
//    Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
//    Output: [3,9,20,null,null,15,7]
//    Example 2:
//
//    Input: preorder = [-1], inorder = [-1]
//    Output: [-1]
//
//
//    Constraints:
//
//    1 <= preorder.length <= 3000
//    inorder.length == preorder.length
//    -3000 <= preorder[i], inorder[i] <= 3000
//    preorder and inorder consist of unique values.
//    Each value of inorder also appears in preorder.
//    preorder is guaranteed to be the preorder traversal of the tree.
//    inorder is guaranteed to be the inorder traversal of the tree.