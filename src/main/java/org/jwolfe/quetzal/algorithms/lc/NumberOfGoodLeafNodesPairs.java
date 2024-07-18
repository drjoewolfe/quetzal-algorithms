package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class NumberOfGoodLeafNodesPairs {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */
    class Solution {
        public int countPairs(TreeNode root, int distance) {
            if (root == null || distance < 1) {
                return 0;
            }

            List<TreeNode> leaves = new ArrayList<>();
            Map<TreeNode, List<TreeNode>> graph = new HashMap<>();
            traverse(root, null, leaves, graph);

            if (leaves.size() == 1) {
                return 0;
            }

            int count = 0;
            for (var leaf : leaves) {
                count += bfs(leaf, graph, distance);
            }

            return count / 2;
        }

        private void traverse(TreeNode node, TreeNode parent, List<TreeNode> leaves, Map<TreeNode, List<TreeNode>> graph) {
            if (node == null) {
                return;
            }

            if (node.left == null && node.right == null) {
                leaves.add(node);
            }

            if (parent != null) {
                if (!graph.containsKey(parent)) {
                    graph.put(parent, new ArrayList<>());
                }

                if (!graph.containsKey(node)) {
                    graph.put(node, new ArrayList<>());
                }

                graph.get(parent).add(node);
                graph.get(node).add(parent);
            }

            traverse(node.left, node, leaves, graph);
            traverse(node.right, node, leaves, graph);
        }

        private int bfs(TreeNode start, Map<TreeNode, List<TreeNode>> graph, int maxDistance) {
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(start);
            int distance = 1;
            int count = 0;

            Set<TreeNode> visited = new HashSet<>();
            visited.add(start);

            while (!queue.isEmpty() && distance <= maxDistance) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    var currNode = queue.poll();

                    for (var nextNode : graph.get(currNode)) {
                        if (visited.contains(nextNode)) {
                            continue;
                        }

                        if (nextNode.left == null && nextNode.right == null) {
                            count++;
                        }

                        queue.offer(nextNode);
                        visited.add(nextNode);
                    }
                }

                distance++;
            }

            return count;
        }
    }

    class Solution_Correct_1 {
        public int countPairs(TreeNode root, int distance) {
            if (root == null || distance < 1) {
                return 0;
            }

            List<TreeNode> leaves = new ArrayList<>();
            collectLeaves(root, leaves);

            Map<TreeNode, List<TreeNode>> graph = new HashMap<>();
            createGraph(root, null, graph);

            int count = 0;
            for (TreeNode leaf : leaves) {
                Set<TreeNode> visited = new HashSet<>();
                count += dfs(leaf, graph, visited, 0, distance);
            }

            return count / 2;
        }

        private int dfs(TreeNode root, Map<TreeNode, List<TreeNode>> graph, Set<TreeNode> visited, int currentDistance, int maxDistance) {
            if (root == null || currentDistance > maxDistance) {
                return 0;
            }

            visited.add(root);

            if (currentDistance != 0
                    && root.left == null && root.right == null) {
                return 1;
            }

            int count = 0;
            for (var neighbour : graph.get(root)) {
                if (visited.contains(neighbour)) {
                    continue;
                }

                count += dfs(neighbour, graph, visited, currentDistance + 1, maxDistance);
            }

            return count;
        }

        private void createGraph(TreeNode root, TreeNode parent, Map<TreeNode, List<TreeNode>> graph) {
            if (root == null) {
                return;
            }

            if (!graph.containsKey(root)) {
                graph.put(root, new ArrayList<>());
            }

            if (parent != null) {
                graph.get(root).add(parent);
            }

            if (root.left != null) {
                graph.get(root).add(root.left);
                createGraph(root.left, root, graph);
            }

            if (root.right != null) {
                graph.get(root).add(root.right);
                createGraph(root.right, root, graph);
            }
        }

        private void collectLeaves(TreeNode root, List<TreeNode> leaves) {
            if (root == null) {
                return;
            }

            if (root.left == null && root.right == null) {
                leaves.add(root);
                return;
            }

            collectLeaves(root.left, leaves);
            collectLeaves(root.right, leaves);
        }

        private void print(Map<TreeNode, List<TreeNode>> graph) {
            for (var key : graph.keySet()) {
                System.out.print(key.val + " [");
                for (var child : graph.get(key)) {
                    System.out.print(child.val + " ");
                }

                System.out.println("]");
            }
        }

        private void print(List<TreeNode> list) {
            for (var child : list) {
                System.out.print(child.val + " ");
            }

            System.out.println();
        }
    }

    class Solution_Incorrect {
        public int countPairs(TreeNode root, int distance) {
            if (root == null || distance < 1) {
                return 0;
            }

            List<TreeNode> leaves = new ArrayList<>();
            collectLeaves(root, leaves);

            int count = 0;
            for (int i = 0; i < leaves.size() - 1; i++) {
                TreeNode leaf1 = leaves.get(i);
                for (int j = i + 1; j < leaves.size(); j++) {
                    TreeNode leaf2 = leaves.get(j);

                    int leafDistance = getDistance(root, leaf1, leaf2);
                    if (leafDistance <= distance) {
                        count++;
                    }

                    System.out.println(leaf1.val + " -> " + leaf2.val + " : " + leafDistance + ", " + count);
                }
            }

            return count;
        }

        private void collectLeaves(TreeNode root, List<TreeNode> leaves) {
            if (root == null) {
                return;
            }

            if (root.left == null && root.right == null) {
                leaves.add(root);
                return;
            }

            collectLeaves(root.left, leaves);
            collectLeaves(root.right, leaves);
        }

        private int getDistance(TreeNode root, TreeNode leaf1, TreeNode leaf2) {
            if (root == null) {
                return -1;
            }

            if (root == leaf1 || root == leaf2) {
                return 1;
            }

            int left = getDistance(root.left, leaf1, leaf2);
            int right = getDistance(root.right, leaf1, leaf2);

            if (left > -1 && right > -1) {
                return left + right;
            } else if (left == -1 && right == -1) {
                return -1;
            } else if (left == -1) {
                return right;
            } else {
                return left;
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

// [1,2,3,null,4]
// 3

// [1,2,3,4,5,6,7]
// 3

// [11,99,88,77,null,null,66,55,null,null,44,33,null,null,22]
// 4

// [100]
// 1
}

//    1530. Number of Good Leaf Nodes Pairs
//    Medium
//    You are given the root of a binary tree and an integer distance. A pair of two different leaf nodes of a binary tree is said to be good if the length of the shortest path between them is less than or equal to distance.
//
//    Return the number of good leaf node pairs in the tree.
//
//
//
//    Example 1:
//
//
//    Input: root = [1,2,3,null,4], distance = 3
//    Output: 1
//    Explanation: The leaf nodes of the tree are 3 and 4 and the length of the shortest path between them is 3. This is the only good pair.
//    Example 2:
//
//
//    Input: root = [1,2,3,4,5,6,7], distance = 3
//    Output: 2
//    Explanation: The good pairs are [4,5] and [6,7] with shortest path = 2. The pair [4,6] is not good because the length of ther shortest path between them is 4.
//    Example 3:
//
//    Input: root = [7,1,4,6,null,5,3,null,null,null,null,null,2], distance = 3
//    Output: 1
//    Explanation: The only good pair is [2,5].
//
//
//    Constraints:
//
//    The number of nodes in the tree is in the range [1, 210].
//    1 <= Node.val <= 100
//    1 <= distance <= 10