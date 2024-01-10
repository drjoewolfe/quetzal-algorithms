package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class AmountOfTimeForBinaryTreeToBeInfected {
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

        int maxDistance;

        public int amountOfTime(TreeNode root, int start) {
            maxDistance = 0;
            traverse(root, start);
            return maxDistance;
        }

        private int traverse(TreeNode root, int start) {
            if(root == null) {
                return 0;
            }

            int leftDepth = traverse(root.left, start);
            int rightDepth = traverse(root.right, start);

            if(root.val == start) {
                int distance = Math.max(leftDepth, rightDepth);
                maxDistance = Math.max(maxDistance, distance);

                return -1;

            } else if(leftDepth >= 0 && rightDepth >= 0) {
                int depth = Math.max(leftDepth, rightDepth) + 1;
                return depth;

            } else {
                // subtree (left or right) contains the start node
                int distance = Math.abs(leftDepth) + Math.abs(rightDepth);
                maxDistance = Math.max(maxDistance, distance);

                int depth = Math.min(leftDepth, rightDepth) - 1;
                return depth;
            }
        }
    }

    class Solution_Correct_1 {
        public int amountOfTime(TreeNode root, int start) {
            if(root == null) {
                return 0;
            }

            Map<Integer, Set<Integer>> graph = new HashMap<>();
            constructGraph(root, graph);

            Queue<Integer> queue = new LinkedList<>();
            queue.offer(start);

            Set<Integer> visited = new HashSet<>();
            int time = -1;

            while(!queue.isEmpty()) {
                int size = queue.size();
                for(int i = 0; i < size; i++) {
                    int u = queue.poll();
                    visited.add(u);

                    for(Integer v : graph.get(u)) {
                        if(!visited.contains(v)) {
                            queue.offer(v);
                        }
                    }
                }

                time++;
            }

            return time;
        }

        private void constructGraph(TreeNode node, Map<Integer, Set<Integer>> graph) {
            if(node == null) {
                return;
            }

            constructGraph(node.left, graph);
            constructGraph(node.right, graph);

            Set<Integer> set = new HashSet<>();
            graph.put(node.val, set);

            if(node.left != null) {
                set.add(node.left.val);
                graph.get(node.left.val).add(node.val);
            }

            if(node.right != null) {
                set.add(node.right.val);
                graph.get(node.right.val).add(node.val);
            }
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

//    2385. Amount of Time for Binary Tree to Be Infected
//    Medium
//    You are given the root of a binary tree with unique values, and an integer start. At minute 0, an infection starts from the node with value start.
//
//    Each minute, a node becomes infected if:
//
//    The node is currently uninfected.
//    The node is adjacent to an infected node.
//    Return the number of minutes needed for the entire tree to be infected.
//
//
//
//    Example 1:
//
//
//    Input: root = [1,5,3,null,4,10,6,9,2], start = 3
//    Output: 4
//    Explanation: The following nodes are infected during:
//    - Minute 0: Node 3
//    - Minute 1: Nodes 1, 10 and 6
//    - Minute 2: Node 5
//    - Minute 3: Node 4
//    - Minute 4: Nodes 9 and 2
//    It takes 4 minutes for the whole tree to be infected so we return 4.
//    Example 2:
//
//
//    Input: root = [1], start = 1
//    Output: 0
//    Explanation: At minute 0, the only node in the tree is infected so we return 0.
//
//
//    Constraints:
//
//    The number of nodes in the tree is in the range [1, 105].
//    1 <= Node.val <= 105
//    Each node has a unique value.
//    A node with a value of start exists in the tree.