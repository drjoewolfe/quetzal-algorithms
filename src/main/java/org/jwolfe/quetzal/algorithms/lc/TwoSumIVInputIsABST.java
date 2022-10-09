package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashSet;
import java.util.Set;

public class TwoSumIVInputIsABST {
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
        public boolean findTarget(TreeNode root, int k) {
            return findTarget(root, k, new HashSet<>());
        }

        private boolean findTarget(TreeNode root, int k, Set<Integer> seen) {
            if(root == null) {
                return false;
            }

            int compl = k - root.val;
            if(seen.contains(compl)) {
                return true;
            }

            seen.add(root.val);

            return findTarget(root.left, k, seen) || findTarget(root.right, k, seen);
        }
    }

    class Solution_Correct_1 {
        public boolean findTarget(TreeNode root, int k) {
            if(root == null) {
                return false;
            }

            Set<Integer> set = new HashSet<>();
            return findTarget(root, k, set);
        }

        private boolean findTarget(TreeNode root, int k, Set<Integer> set) {
            if(root == null) {
                return false;
            }

            if(set.contains(k - root.val)) {
                return true;
            }

            set.add(root.val);
            return findTarget(root.left, k, set)
                    || findTarget(root.right, k, set);

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

// [5,3,6,2,4,null,7]
// 9
}

//    653. Two Sum IV - Input is a BST
//    Easy
//    Given the root of a Binary Search Tree and a target number k, return true if there exist two elements in the BST such that their sum is equal to the given target.
//
//
//
//    Example 1:
//
//
//    Input: root = [5,3,6,2,4,null,7], k = 9
//    Output: true
//    Example 2:
//
//
//    Input: root = [5,3,6,2,4,null,7], k = 28
//    Output: false
//
//
//    Constraints:
//
//    The number of nodes in the tree is in the range [1, 104].
//    -104 <= Node.val <= 104
//    root is guaranteed to be a valid binary search tree.
//    -105 <= k <= 105