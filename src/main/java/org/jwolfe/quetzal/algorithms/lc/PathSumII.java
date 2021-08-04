package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PathSumII {
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
        public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
            List<List<Integer>> pathSums = new ArrayList<>();
            LinkedList<Integer> currentPath = new LinkedList<>();

            pathSum(root, targetSum, currentPath, pathSums);
            return pathSums;
        }

        private void pathSum(TreeNode root, int targetSum, LinkedList<Integer> currentPath, List<List<Integer>> pathSums) {
            if(root == null) {
                return;
            }

            if(root.left == null && root.right == null) {
                if(targetSum == root.val) {
                    List<Integer> pathSum = new ArrayList<>(currentPath);
                    pathSum.add(root.val);

                    pathSums.add(pathSum);
                }

                return;
            }

            currentPath.add(root.val);
            pathSum(root.left, targetSum - root.val, currentPath, pathSums);
            pathSum(root.right, targetSum - root.val, currentPath, pathSums);
            currentPath.removeLast();
        }
    }

    class Solution_Correct_2 {
        public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
            List<List<Integer>> paths = new ArrayList<>();
            List<Integer> currentPath = new ArrayList<>();

            generatePathsForSum(root, targetSum, currentPath, paths);
            return paths;
        }

        private void generatePathsForSum(TreeNode root, int targetSum, List<Integer> currentPath, List<List<Integer>> paths) {
            if(root == null) {
                return;
            }

            currentPath.add(root.val);
            if(root.left == null && root.right == null & targetSum == root.val) {
                paths.add(new ArrayList<>(currentPath));
            } else {
                generatePathsForSum(root.left, targetSum - root.val, currentPath, paths);
                generatePathsForSum(root.right, targetSum - root.val, currentPath, paths);
            }

            currentPath.remove(currentPath.size() - 1);
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

//    113. Path Sum II
//    Medium
//    Given the root of a binary tree and an integer targetSum, return all root-to-leaf paths where each path's sum equals targetSum.
//
//    A leaf is a node with no children.
//
//
//
//    Example 1:
//
//
//    Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
//    Output: [[5,4,11,2],[5,8,4,5]]
//    Example 2:
//
//
//    Input: root = [1,2,3], targetSum = 5
//    Output: []
//    Example 3:
//
//    Input: root = [1,2], targetSum = 0
//    Output: []
//
//
//    Constraints:
//
//    The number of nodes in the tree is in the range [0, 5000].
//    -1000 <= Node.val <= 1000
//    -1000 <= targetSum <= 1000