package org.jwolfe.quetzal.algorithms.lc;

public class AddOneRowToTree {
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
        public TreeNode addOneRow(TreeNode root, int v, int d) {
            if(d == 1) {
                TreeNode node = new TreeNode(v);
                node.left = root;

                return node;
            }

            return addOneRow(root, v, d, 1);
        }

        private TreeNode addOneRow(TreeNode root, int v, int d, int currentDepth) {
            if(root == null) {
                return null;
            }

            if(currentDepth == d - 1) {
                TreeNode leftNode = new TreeNode(v);
                leftNode.left = root.left;

                TreeNode rightNode = new TreeNode(v);
                rightNode.right = root.right;

                root.left = leftNode;
                root.right = rightNode;
            } else {
                addOneRow(root.left, v, d, currentDepth + 1);
                addOneRow(root.right, v, d, currentDepth + 1);
            }

            return root;
        }
    }

    class Solution_Correct_2 {
        public TreeNode addOneRow(TreeNode root, int v, int d) {
            if(d == 1) {
                TreeNode node = new TreeNode(v);
                node.left = root;
                return node;
            }

            return addOneRow(root, v, d, 1);
        }

        private TreeNode addOneRow(TreeNode root, int v, int d, int currentDepth) {
            if(root == null) {
                return root;
            }

            if(currentDepth == d - 1) {
                TreeNode leftNode = new TreeNode(v);
                TreeNode rightNode = new TreeNode(v);

                leftNode.left = root.left;
                rightNode.right = root.right;

                root.left = leftNode;
                root.right = rightNode;
            } else {
                addOneRow(root.left, v, d, currentDepth + 1);
                addOneRow(root.right, v, d, currentDepth + 1);
            }

            return root;
        }
    }

    class Solution_Correct_1 {
        public TreeNode addOneRow(TreeNode root, int v, int d) {
            return addOneRow(root, true, v, d);
        }

        private TreeNode addOneRow(TreeNode root, boolean left, int v, int d) {
            if(d == 1) {
                TreeNode newRoot = new TreeNode(v);
                if(left) {
                    newRoot.left = root;
                } else {
                    newRoot.right = root;
                }

                return newRoot;
            }

            if(root == null) {
                return root;
            }

            root.left = addOneRow(root.left, true, v, d - 1);
            root.right = addOneRow(root.right, false, v, d - 1);

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