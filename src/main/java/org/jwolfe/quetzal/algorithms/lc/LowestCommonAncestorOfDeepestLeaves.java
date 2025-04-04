package org.jwolfe.quetzal.algorithms.lc;

import org.jwolfe.quetzal.library.general.Pair;

import java.util.ArrayList;
import java.util.List;

public class LowestCommonAncestorOfDeepestLeaves {
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
        public TreeNode lcaDeepestLeaves(TreeNode root) {
            return dfs(root).getKey();
        }

        private Pair<TreeNode, Integer> dfs(TreeNode root) {
            if (root == null) {
                return new Pair<>(root, 0);
            }

            var left = dfs(root.left);
            var right = dfs(root.right);

            int leftDepth = left.getValue();
            int rightDepth = right.getValue();

            if (leftDepth > rightDepth) {
                return new Pair<>(left.getKey(), leftDepth + 1);
            } else if (leftDepth < rightDepth) {
                return new Pair<>(right.getKey(), rightDepth + 1);
            } else {
                return new Pair<>(root, leftDepth + 1);
            }
        }
    }

    class Solution_Correct_1 {
        public TreeNode lcaDeepestLeaves(TreeNode root) {
            if (root == null) {
                return null;
            }

            if (isLeaf(root)) {
                return root;
            }

            int maxDepth = maxDepth(root, -1);

            List<TreeNode> deepestNodes = new ArrayList<>();
            gatherLeavesAtDepth(root, -1, maxDepth, deepestNodes);

            return findLca(root, deepestNodes);
        }

        private TreeNode findLca(TreeNode root, List<TreeNode> nodes) {
            List<List<TreeNode>> allPaths = new ArrayList<>();
            for (var node : nodes) {
                List<TreeNode> path = getPath(root, node);
                allPaths.add(path);
            }

            TreeNode prev = root;
            for (int i = 0; i < allPaths.get(0).size(); i++) {
                TreeNode curr = allPaths.get(0).get(i);
                for (int j = 1; j < allPaths.size(); j++) {
                    if (allPaths.get(j).get(i) != curr) {
                        return prev;
                    }
                }

                prev = curr;
            }

            return prev;
        }

        private List<TreeNode> getPath(TreeNode root, TreeNode node) {
            List<TreeNode> path = new ArrayList<>();
            constructPath(root, node, path);

            return path;
        }

        private boolean constructPath(TreeNode root, TreeNode node, List<TreeNode> path) {
            if (root == null) {
                return false;
            }

            path.add(root);
            if (root == node) {
                return true;
            }


            boolean pathFound = constructPath(root.left, node, path);
            if (!pathFound) {
                pathFound = constructPath(root.right, node, path);
            }

            if (!pathFound) {
                path.remove(path.size() - 1);
                return false;
            }

            return true;
        }

        private void gatherLeavesAtDepth(TreeNode root, int parentDepth, int requiredDepth, List<TreeNode> gatheredNodes) {
            if (root == null) {
                return;
            }

            if (isLeaf(root) && parentDepth + 1 == requiredDepth) {
                gatheredNodes.add(root);
                return;
            }

            gatherLeavesAtDepth(root.left, parentDepth + 1, requiredDepth, gatheredNodes);
            gatherLeavesAtDepth(root.right, parentDepth + 1, requiredDepth, gatheredNodes);
        }

        private int maxDepth(TreeNode root, int parentDepth) {
            if (root == null) {
                return parentDepth;
            }

            return Math.max(maxDepth(root.left, parentDepth + 1), maxDepth(root.right, parentDepth + 1));
        }

        private boolean isLeaf(TreeNode root) {
            return root.left == null && root.right == null;
        }

        private void print(List<TreeNode> nodes) {
            for (var node : nodes) {
                System.out.print(node.val + " ");
            }

            System.out.println();
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

//    1123. Lowest Common Ancestor of Deepest Leaves
//    Medium
//    Given the root of a binary tree, return the lowest common ancestor of its deepest leaves.
//
//    Recall that:
//
//    The node of a binary tree is a leaf if and only if it has no children
//    The depth of the root of the tree is 0. if the depth of a node is d, the depth of each of its children is d + 1.
//    The lowest common ancestor of a set S of nodes, is the node A with the largest depth such that every node in S is in the subtree with root A.
//
//
//    Example 1:
//
//
//    Input: root = [3,5,1,6,2,0,8,null,null,7,4]
//    Output: [2,7,4]
//    Explanation: We return the node with value 2, colored in yellow in the diagram.
//    The nodes coloured in blue are the deepest leaf-nodes of the tree.
//    Note that nodes 6, 0, and 8 are also leaf nodes, but the depth of them is 2, but the depth of nodes 7 and 4 is 3.
//    Example 2:
//
//    Input: root = [1]
//    Output: [1]
//    Explanation: The root is the deepest node in the tree, and it's the lca of itself.
//    Example 3:
//
//    Input: root = [0,1,3,null,2]
//    Output: [2]
//    Explanation: The deepest leaf node in the tree is 2, the lca of one node is itself.
//
//
//    Constraints:
//
//    The number of nodes in the tree will be in the range [1, 1000].
//    0 <= Node.val <= 1000
//    The values of the nodes in the tree are unique.
//
//
//    Note: This question is the same as 865: https://leetcode.com/problems/smallest-subtree-with-all-the-deepest-nodes/