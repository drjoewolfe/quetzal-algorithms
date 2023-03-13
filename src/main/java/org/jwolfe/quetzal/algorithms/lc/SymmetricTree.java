package org.jwolfe.quetzal.algorithms.lc;

import java.util.LinkedList;
import java.util.Queue;

public class SymmetricTree {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    class Solution {
        public boolean isSymmetric(TreeNode root) {
            return isSymmetric(root, root);
        }

        private boolean isSymmetric(TreeNode root1, TreeNode root2) {
            if(root1 == null && root2 == null) {
                return true;
            }

            if(root1 == null || root2 == null) {
                return false;
            }

            return root1.val == root2.val
                    && isSymmetric(root1.left, root2.right)
                    && isSymmetric(root1.right, root2.left);
        }
    }

    class Solution_Correct_1 {
        public boolean isSymmetric(TreeNode root) {
            if(root == null) {
                return true;
            }

            return isSymmetric(root.left, root.right);
        }

        private boolean isSymmetric(TreeNode root1, TreeNode root2) {
            if(root1 == null && root2 == null) {
                return true;
            }

            if(root1 == null || root2 == null) {
                return false;
            }

            return (root1.val == root2.val)
                    && isSymmetric(root1.left, root2.right)
                    && isSymmetric(root1.right, root2.left);
        }

        public boolean isSymmetricIterative(TreeNode root) {
            if(root == null) {
                return true;
            }

            Queue<TreeNode> queue = new LinkedList<>();

            queue.offer(root);
            queue.offer(root);

            while(!queue.isEmpty()) {
                int n = queue.size();
                for(int i = 0; i <n; i++) {
                    TreeNode node1 = queue.poll();
                    TreeNode node2 = queue.poll();

                    if(node1 == null && node2 == null) {
                        continue;
                    }

                    if(node1 == null || node2 == null) {
                        return false;
                    }

                    if(node1.val != node2.val) {
                        return false;
                    }

                    queue.offer(node1.left);
                    queue.offer(node2.right);
                    queue.offer(node1.right);
                    queue.offer(node2.left);
                }
            }

            return true;
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}

//    101. Symmetric Tree
//    Easy
//    Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).
//
//
//
//    Example 1:
//
//
//    Input: root = [1,2,2,3,4,4,3]
//    Output: true
//    Example 2:
//
//
//    Input: root = [1,2,2,null,3,null,3]
//    Output: false
//
//
//    Constraints:
//
//    The number of nodes in the tree is in the range [1, 1000].
//    -100 <= Node.val <= 100
//
//
//    Follow up: Could you solve it both recursively and iteratively?