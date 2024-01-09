package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.List;

public class LeafSimilarTrees {
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
        public boolean leafSimilar(TreeNode root1, TreeNode root2) {
            List<Integer> leaves1 = new ArrayList<>();
            List<Integer> leaves2 = new ArrayList<>();

            loadLeaves(root1, leaves1);
            loadLeaves(root2, leaves2);

            return leaves1.equals(leaves2);
        }

        private void loadLeaves(TreeNode root, List<Integer> leaves) {
            if (root == null) {
                return;
            }

            if (root.left == null && root.right == null) {
                leaves.add(root.val);
            } else {
                loadLeaves(root.left, leaves);
                loadLeaves(root.right, leaves);
            }
        }
    }

    class Solution_Correct_1 {
        public boolean leafSimilar(TreeNode root1, TreeNode root2) {
            if (root1 == null || root2 == null) {
                return false;
            }

            List<TreeNode> leaves1 = new ArrayList<>();
            List<TreeNode> leaves2 = new ArrayList<>();

            loadLeaves(root1, leaves1);
            loadLeaves(root2, leaves2);

            if (leaves1.size() != leaves2.size()) {
                return false;
            }

            for (int i = 0; i < leaves1.size(); i++) {
                if (leaves1.get(i).val != leaves2.get(i).val) {
                    return false;
                }
            }

            return true;
        }

        private void loadLeaves(TreeNode root, List<TreeNode> leaves) {
            if (root == null) {
                return;
            }

            if (root.left == null && root.right == null) {
                leaves.add(root);
                return;
            }

            loadLeaves(root.left, leaves);
            loadLeaves(root.right, leaves);
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


//    872. Leaf-Similar Trees
//    Easy
//    Consider all the leaves of a binary tree, from left to right order, the values of those leaves form a leaf value sequence.
//
//
//
//    For example, in the given tree above, the leaf value sequence is (6, 7, 4, 9, 8).
//
//    Two binary trees are considered leaf-similar if their leaf value sequence is the same.
//
//    Return true if and only if the two given trees with head nodes root1 and root2 are leaf-similar.
//
//
//
//    Example 1:
//
//
//    Input: root1 = [3,5,1,6,2,9,8,null,null,7,4], root2 = [3,5,1,6,7,4,2,null,null,null,null,null,null,9,8]
//    Output: true
//    Example 2:
//
//
//    Input: root1 = [1,2,3], root2 = [1,3,2]
//    Output: false
//
//
//    Constraints:
//
//    The number of nodes in each tree will be in the range [1, 200].
//    Both of the given trees will have values in the range [0, 200].