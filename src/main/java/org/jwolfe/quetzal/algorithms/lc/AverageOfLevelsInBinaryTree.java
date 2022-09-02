package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AverageOfLevelsInBinaryTree {
    class Solution {
        public List<Double> averageOfLevels(TreeNode root) {
            List<Double> averages = new ArrayList<>();


            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while(!queue.isEmpty()) {
                int size = queue.size();
                double sum = 0;

                for(int i = 0; i < size; i++) {
                    TreeNode node = queue.poll();
                    sum += node.val;

                    if(node.left != null) {
                        queue.offer(node.left);
                    }

                    if(node.right != null) {
                        queue.offer(node.right);
                    }
                }

                double average = sum / size;
                averages.add(average);
            }

            return averages;
        }
    }

    class Solution_Correct_1 {
        public List<Double> averageOfLevels(TreeNode root) {
            List<Double> averages = new ArrayList<>();

            Map<Integer, Double> levelSums = new HashMap<>();
            Map<Integer, Integer> levelCounts = new HashMap<>();
            traverse(root, 0, levelSums, levelCounts);

            for(int i = 0; i < levelSums.size(); i++) {
                double levelAverage = levelSums.get(i) / levelCounts.get(i);
                averages.add(levelAverage);
            }


            return averages;
        }

        private void traverse(TreeNode root, int level, Map<Integer, Double> levelSums, Map<Integer, Integer> levelCounts) {
            if(root == null) {
                return;
            }

            levelSums.put(level, levelSums.getOrDefault(level, 0d) + root.val);
            levelCounts.put(level, levelCounts.getOrDefault(level, 0) + 1);

            traverse(root.left, level + 1, levelSums, levelCounts);
            traverse(root.right, level + 1, levelSums, levelCounts);
        }
    }

    class Solution_Correct_BFS {
        public List<Double> averageOfLevels(TreeNode root) {
            List<Double> levelAverages = new ArrayList<>();
            if(root == null) {
                return levelAverages;
            }

            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);

            while(!queue.isEmpty()) {
                double levelSum = 0;
                int levelSize = queue.size();

                for(int i = 0; i < levelSize; i++) {
                    TreeNode node = queue.poll();
                    levelSum += node.val;

                    if(node.left != null) {
                        queue.offer(node.left);
                    }

                    if(node.right != null) {
                        queue.offer(node.right);
                    }
                }

                double levelAvg = levelSum / levelSize;
                levelAverages.add(levelAvg);
            }

            return levelAverages;
        }
    }

// [3,9,20,null,null,15,7]
// [2147483647,2147483647,2147483647]

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}

//    637. Average of Levels in Binary Tree
//    Easy
//    Given a non-empty binary tree, return the average value of the nodes on each level in the form of an array.
//    Example 1:
//    Input:
//    3
//    / \
//    9  20
//    /  \
//    15   7
//    Output: [3, 14.5, 11]
//    Explanation:
//    The average value of nodes on level 0 is 3,  on level 1 is 14.5, and on level 2 is 11. Hence return [3, 14.5, 11].
//    Note:
//    The range of node's value is in the range of 32-bit signed integer.
