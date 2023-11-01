package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.List;

public class FindModeInBinarySearchTree {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */
    class Solution {
        int maxStreak = 0;
        int currStreak = 0;
        int currNumber = -1;

        public int[] findMode(TreeNode root) {
            if (root == null) {
                return new int[0];
            }

            List<Integer> modes = new ArrayList<>();
            inorder(root, modes);
            return modes.stream().mapToInt(Integer::intValue).toArray();
        }

        private void inorder(TreeNode root, List<Integer> modes) {
            if (root == null) {
                return;
            }

            inorder(root.left, modes);

            if (currNumber == root.val) {
                currStreak++;
            } else {
                currNumber = root.val;
                currStreak = 0;
            }

            if (currStreak > maxStreak) {
                maxStreak = currStreak;
                modes.clear();
                modes.add(currNumber);
            } else if (currStreak == maxStreak) {
                modes.add(currNumber);
            }

            inorder(root.right, modes);
        }
    }

    class Solution_Correct_1 {
        Integer previousNumber = null;
        Integer maxModeFrequency = 0;
        Integer currentModeFrequency = 0;

        public int[] findMode(TreeNode root) {
            List<Integer> modes = new ArrayList<>();
            inOrderForMode(root, modes);

            int[] rvModes = new int[modes.size()];
            for (int i = 0; i < modes.size(); i++) {
                rvModes[i] = modes.get(i);
            }

            return rvModes;
        }

        private void inOrderForMode(TreeNode root, List<Integer> modes) {

            if (root == null) {
                return;
            }

            inOrderForMode(root.left, modes);

            if (previousNumber == null || previousNumber != root.val) {
                previousNumber = root.val;
                currentModeFrequency = 1;
            } else {
                currentModeFrequency++;
            }

            if (currentModeFrequency > maxModeFrequency) {
                modes.clear();
                modes.add(root.val);
                maxModeFrequency = currentModeFrequency;
            } else if (currentModeFrequency == maxModeFrequency) {
                modes.add(root.val);
            }

            inOrderForMode(root.right, modes);
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}

//    501. Find Mode in Binary Search Tree
//    Easy
//    Given the root of a binary search tree (BST) with duplicates, return all the mode(s) (i.e., the most frequently occurred element) in it.
//
//    If the tree has more than one mode, return them in any order.
//
//    Assume a BST is defined as follows:
//
//    The left subtree of a node contains only nodes with keys less than or equal to the node's key.
//    The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
//    Both the left and right subtrees must also be binary search trees.
//
//
//    Example 1:
//
//
//    Input: root = [1,null,2,2]
//    Output: [2]
//    Example 2:
//
//    Input: root = [0]
//    Output: [0]
//
//
//    Constraints:
//
//    The number of nodes in the tree is in the range [1, 104].
//    -105 <= Node.val <= 105
//
//
//    Follow up: Could you do that without using any extra space? (Assume that the implicit stack space incurred due to recursion does not count).