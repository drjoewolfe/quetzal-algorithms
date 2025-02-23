package org.jwolfe.quetzal.algorithms.lc;

public class ConstructBinaryTreeFromPreorderAndPostorderTraversal {
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
        int preIndex = 0;
        int postIndex = 0;

        public TreeNode constructFromPrePost(int[] pre, int[] post) {
            if (pre == null || pre.length == 0 || post == null || post.length != pre.length) {
                return null;
            }

            return constructTree(pre, post);
        }

        private TreeNode constructTree(int[] pre, int[] post) {
            TreeNode root = new TreeNode(pre[preIndex++]);

            if (root.val != post[postIndex]) {
                root.left = constructTree(pre, post);
            }

            if (root.val != post[postIndex]) {
                root.right = constructTree(pre, post);
            }

            postIndex++;
            return root;
        }
    }

    class Solution_Correct_1 {
        public TreeNode constructFromPrePost(int[] pre, int[] post) {
            if (pre == null || post == null || pre.length == 0 || pre.length != post.length) {
                return null;
            }

            return constructFromPrePost(pre, 0, pre.length - 1, post, 0, post.length - 1);
        }

        private TreeNode constructFromPrePost(int[] pre, int preStart, int preEnd, int[] post, int postStart, int postEnd) {
            if (preStart > preEnd) {
                return null;
            }

            int val = pre[preStart];
            TreeNode root = new TreeNode(val);

            if (preStart == preEnd) {
                return root;
            }

            int leftVal = pre[preStart + 1];
            int i = postStart;
            for (; i < postEnd; i++) {
                if (post[i] == leftVal) {
                    break;
                }
            }

            int size = i - postStart + 1;

            root.left = constructFromPrePost(pre, preStart + 1, preStart + size, post, postStart, i);
            root.right = constructFromPrePost(pre, preStart + size + 1, preEnd, post, i + 1, postEnd - 1);

            return root;
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

//    889. Construct Binary Tree from Preorder and Postorder Traversal
//    Medium
//    Given two integer arrays, preorder and postorder where preorder is the preorder traversal of a binary tree of distinct values and postorder is the postorder traversal of the same tree, reconstruct and return the binary tree.
//
//    If there exist multiple answers, you can return any of them.
//
//
//
//    Example 1:
//
//
//    Input: preorder = [1,2,4,5,3,6,7], postorder = [4,5,2,6,7,3,1]
//    Output: [1,2,3,4,5,6,7]
//    Example 2:
//
//    Input: preorder = [1], postorder = [1]
//    Output: [1]
//
//
//    Constraints:
//
//    1 <= preorder.length <= 30
//    1 <= preorder[i] <= preorder.length
//    All the values of preorder are unique.
//    postorder.length == preorder.length
//    1 <= postorder[i] <= postorder.length
//    All the values of postorder are unique.
//    It is guaranteed that preorder and postorder are the preorder traversal and postorder traversal of the same binary tree.