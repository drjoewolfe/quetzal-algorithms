package org.jwolfe.quetzal.algorithms.lc;

import java.util.LinkedList;
import java.util.Queue;

public class FindBottomLeftTreeValue {
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
        public int findBottomLeftValue(TreeNode root) {
            if (root == null) {
                return -1;
            }

            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);

            TreeNode current = null;
            while (!queue.isEmpty()) {
                current = queue.poll();

                if (current.right != null) {
                    queue.offer(current.right);
                }

                if (current.left != null) {
                    queue.offer(current.left);
                }
            }

            return current.val;
        }
    }

    class Solution_Correct_1 {
        public int findBottomLeftValue(TreeNode root) {
            if (root == null) {
                return -1;
            }

            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);

            TreeNode bottomLeftNode = null;
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode node = queue.poll();
                    if (i == 0) {
                        bottomLeftNode = node;
                    }

                    if (node.left != null) {
                        queue.add(node.left);
                    }

                    if (node.right != null) {
                        queue.add(node.right);
                    }
                }
            }

            return bottomLeftNode != null ? bottomLeftNode.val : -1;
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

//    513. Find Bottom Left Tree Value
//    Medium
//    Given the root of a binary tree, return the leftmost value in the last row of the tree.
//
//
//
//    Example 1:
//
//
//    Input: root = [2,1,3]
//    Output: 1
//    Example 2:
//
//
//    Input: root = [1,2,3,4,null,5,6,null,null,7]
//    Output: 7
//
//
//    Constraints:
//
//    The number of nodes in the tree is in the range [1, 104].
//    -231 <= Node.val <= 231 - 1