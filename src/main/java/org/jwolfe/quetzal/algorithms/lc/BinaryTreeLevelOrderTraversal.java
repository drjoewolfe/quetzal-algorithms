package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeLevelOrderTraversal {
    class Solution {
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> levelOrder = new ArrayList<>();

            if(root == null) {
                return levelOrder;
            }

            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);

            while(!queue.isEmpty()) {
                int n = queue.size();
                List<Integer> level = new ArrayList<>();
                levelOrder.add(level);

                for(int i = 0; i < n; i++) {
                    TreeNode node = queue.poll();
                    level.add(node.val);

                    if(node.left != null) {
                        queue.offer(node.left);
                    }

                    if(node.right != null) {
                        queue.offer(node.right);
                    }
                }
            }

            return levelOrder;
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

//    102. Binary Tree Level Order Traversal
//    Medium
//    Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).
//
//
//
//    Example 1:
//
//
//    Input: root = [3,9,20,null,null,15,7]
//    Output: [[3],[9,20],[15,7]]
//    Example 2:
//
//    Input: root = [1]
//    Output: [[1]]
//    Example 3:
//
//    Input: root = []
//    Output: []
//
//
//    Constraints:
//
//    The number of nodes in the tree is in the range [0, 2000].
//    -1000 <= Node.val <= 1000