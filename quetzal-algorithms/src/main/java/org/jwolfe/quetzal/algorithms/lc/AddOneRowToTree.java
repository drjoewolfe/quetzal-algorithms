package org.jwolfe.quetzal.algorithms.lc;

public class AddOneRowToTree {
    public TreeNode addOneRow(TreeNode root, int v, int d) {
        if(d <= 0) {
            return root;
        }

        if(d == 1) {
            TreeNode node = new TreeNode(v);
            node.left = root;

            return node;
        }

        return addOneRow(root, v, d, 1);
    }

    private TreeNode addOneRow(TreeNode root, int v, int d, int cd) {
        if(root == null) {
            return root;
        }

        if(d == cd + 1) {
            TreeNode left = new TreeNode(v);
            left.left = root.left;

            TreeNode right = new TreeNode(v);
            right.right = root.right;

            root.left = left;
            root.right = right;
        } else {
            addOneRow(root.left, v, d, cd + 1);
            addOneRow(root.right, v, d, cd + 1);
        }

        return root;
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

//    623. Add One Row to Tree
//    Medium
//    Given the root of a binary tree, then value v and depth d, you need to add a row of nodes with value v at the given depth d. The root node is at depth 1.
//
//    The adding rule is: given a positive integer depth d, for each NOT null tree nodes N in depth d-1, create two tree nodes with value v as N's left subtree root and right subtree root. And N's original left subtree should be the left subtree of the new left subtree root, its original right subtree should be the right subtree of the new right subtree root. If depth d is 1 that means there is no depth d-1 at all, then create a tree node with value v as the new root of the whole original tree, and the original tree is the new root's left subtree.
//
//    Example 1:
//    Input:
//    A binary tree as following:
//    4
//    /   \
//    2     6
//    / \   /
//    3   1 5
//
//    v = 1
//
//    d = 2
//
//    Output:
//    4
//    / \
//    1   1
//    /     \
//    2       6
//    / \     /
//    3   1   5
//
//    Example 2:
//    Input:
//    A binary tree as following:
//    4
//    /
//    2
//    / \
//    3   1
//
//    v = 1
//
//    d = 3
//
//    Output:
//    4
//    /
//    2
//    / \
//    1   1
//    /     \
//    3       1
//    Note:
//    The given d is in range [1, maximum depth of the given tree + 1].
//    The given binary tree has at least one tree node.