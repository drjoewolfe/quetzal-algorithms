package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class AllNodesDistanceKInBinaryTree {
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
        public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
            List<Integer> results = new ArrayList<>();

            Map<Integer, Set<Integer>> graph = new HashMap<>();
            buildGraph(graph, root, null);

            Queue<Integer> queue = new LinkedList<>();
            queue.offer(target.val);

            Set<Integer> visited = new HashSet<>();
            visited.add(target.val);

            int distance = 0;
            while(!queue.isEmpty() && distance <= K) {
                int size = queue.size();
                for(int i = 0; i < size; i++) {
                    Integer u = queue.poll();

                    if(distance == K) {
                        results.add(u);
                    }

                    if(!graph.containsKey(u)) {
                        continue;
                    }

                    for(var v : graph.get(u)) {
                        if(!visited.contains(v)) {
                            visited.add(v);
                            queue.offer(v);
                        }
                    }
                }

                distance++;
            }

            return results;
        }

        private void buildGraph(Map<Integer, Set<Integer>> graph, TreeNode node, TreeNode parent) {
            if(node == null) {
                return;
            }

            if(parent != null) {
                if(!graph.containsKey(node.val)) {
                    graph.put(node.val, new HashSet<>());
                }

                if(!graph.containsKey(parent.val)) {
                    graph.put(parent.val, new HashSet<>());
                }

                graph.get(node.val).add(parent.val);
                graph.get(parent.val).add(node.val);
            }

            buildGraph(graph, node.left, node);
            buildGraph(graph, node.right, node);
        }
    }

    class Solution_Correct_1 {
        public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
            List<Integer> results = new ArrayList<>();
            if(root == null || target == null || K < 0) {
                return results;
            }

            if(K == 0) {
                results.add(target.val);
                return results;
            }

            Map<TreeNode, TreeNode> parents = new HashMap<>();
            loadParents(root, null, parents);

            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(target);

            Set<TreeNode> visited = new HashSet<>();
            visited.add(null);

            int distance = 0;

            while(!queue.isEmpty()) {
                int size = queue.size();
                for(int i = 0; i < size; i++) {
                    TreeNode node = queue.poll();
                    visited.add(node);

                    if(distance == K) {
                        results.add(node.val);
                    }

                    if(!visited.contains(node.left)) {
                        queue.add(node.left);
                    }

                    if(!visited.contains(node.right)) {
                        queue.add(node.right);
                    }

                    TreeNode parent = parents.get(node);
                    if(!visited.contains(parents.get(node))) {
                        queue.add(parent);
                    }
                }

                distance++;
                if(distance > K) {
                    break;
                }
            }

            return results;
        }

        private void loadParents(TreeNode node, TreeNode parent, Map<TreeNode, TreeNode> parents) {
            if(node == null) {
                return;
            }

            parents.put(node, parent);
            loadParents(node.left, node, parents);
            loadParents(node.right, node, parents);
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

// [3,5,1,6,2,0,8,null,null,7,4]
// 5
// 2
}

//    863. All Nodes Distance K in Binary Tree
//    Medium
//    Given the root of a binary tree, the value of a target node target, and an integer k, return an array of the values of all nodes that have a distance k from the target node.
//
//    You can return the answer in any order.
//
//
//
//    Example 1:
//
//
//    Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, k = 2
//    Output: [7,4,1]
//    Explanation: The nodes that are a distance 2 from the target node (with value 5) have values 7, 4, and 1.
//    Example 2:
//
//    Input: root = [1], target = 1, k = 3
//    Output: []
//
//
//    Constraints:
//
//    The number of nodes in the tree is in the range [1, 500].
//    0 <= Node.val <= 500
//    All the values Node.val are unique.
//    target is the value of one of the nodes in the tree.
//    0 <= k <= 1000