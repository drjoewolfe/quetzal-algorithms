package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AverageOfLevelsInBinaryTree {
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
