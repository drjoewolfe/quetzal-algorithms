package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class DeleteNodesAndReturnForest {
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
        public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
            List<TreeNode> forest = new ArrayList<>();
            if (root == null) {
                return forest;
            }

            Set<Integer> deleteSet = new HashSet<>();
            for (int val : to_delete) {
                deleteSet.add(val);
            }

            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);

            if (!deleteSet.contains(root.val)) {
                forest.add(root);
            }

            while (!queue.isEmpty()) {
                var node = queue.poll();

                if (node.left != null) {
                    queue.offer(node.left);

                    if (deleteSet.contains(node.left.val)) {
                        node.left = null;
                    }
                }

                if (node.right != null) {
                    queue.offer(node.right);

                    if (deleteSet.contains(node.right.val)) {
                        node.right = null;
                    }
                }

                if (deleteSet.contains(node.val)) {
                    if (node.left != null) {
                        forest.add(node.left);
                    }

                    if (node.right != null) {
                        forest.add(node.right);
                    }
                }
            }

            return forest;
        }
    }

    class Solution_Correct_1 {
        public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
            List<TreeNode> forest = new ArrayList<>();
            if (root == null) {
                return forest;
            }

            if (to_delete == null || to_delete.length == 0) {
                forest.add(root);
                return forest;
            }

            Set<Integer> set = new HashSet<>();
            for (int a : to_delete) {
                set.add(a);
            }

            if (set.contains(root.val)) {
                if (root.left != null && !set.contains(root.left.val)) {
                    forest.add(root.left);
                }

                if (root.right != null && !set.contains(root.right.val)) {
                    forest.add(root.right);
                }

                delNodes(root.left, root, set, forest);
                delNodes(root.right, root, set, forest);
            } else {
                forest.add(root);
                delNodes(root, null, set, forest);
            }

            return forest;
        }

        private void delNodes(TreeNode root, TreeNode parent, Set<Integer> deleteSet, List<TreeNode> forest) {
            if (root == null) {
                return;
            }

            if (deleteSet.contains(root.val)) {
                // Delete this node
                if (parent.left == root) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }

                if (root.left != null && !deleteSet.contains(root.left.val)) {
                    forest.add(root.left);
                }

                if (root.right != null && !deleteSet.contains(root.right.val)) {
                    forest.add(root.right);
                }
            }

            delNodes(root.left, root, deleteSet, forest);
            delNodes(root.right, root, deleteSet, forest);
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


// [1,2,3,4,5,6,7]
// [3,5]

}

//    1110. Delete Nodes And Return Forest
//    Medium
//    Given the root of a binary tree, each node in the tree has a distinct value.
//
//    After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).
//
//    Return the roots of the trees in the remaining forest. You may return the result in any order.
//
//
//
//    Example 1:
//
//
//    Input: root = [1,2,3,4,5,6,7], to_delete = [3,5]
//    Output: [[1,2,null,4],[6],[7]]
//    Example 2:
//
//    Input: root = [1,2,4,null,3], to_delete = [3]
//    Output: [[1,2,4]]
//
//
//    Constraints:
//
//    The number of nodes in the given tree is at most 1000.
//    Each node has a distinct value between 1 and 1000.
//    to_delete.length <= 1000
//    to_delete contains distinct values between 1 and 1000.