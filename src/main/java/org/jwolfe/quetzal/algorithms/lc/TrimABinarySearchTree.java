package org.jwolfe.quetzal.algorithms.lc;

public class TrimABinarySearchTree {
    public TreeNode trimBST(TreeNode root, int L, int R) {
        if(root == null) {
            return null;
        }

        root.left = trimBST(root.left, L, R);
        root.right = trimBST(root.right, L, R);

        if(root.val >= L & root.val <= R) {
            return root;
        } else if (root.val < L) {
            return root.right;
        } else {
            return root.left;
        }
    }

    /**
     * Definition for a binary tree node.
     **/
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
