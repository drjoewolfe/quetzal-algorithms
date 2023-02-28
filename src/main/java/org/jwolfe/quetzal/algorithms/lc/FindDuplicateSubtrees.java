package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindDuplicateSubtrees {
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
        public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
            List<TreeNode> duplicateTrees = new ArrayList<>();
            traverse(root, new HashMap<String, Integer>(), duplicateTrees);

            return duplicateTrees;
        }

        private String traverse(TreeNode root, Map<String, Integer> counts, List<TreeNode> duplicateTrees) {
            if (root == null) {
                return "";
            }

            StringBuilder representationBuilder = new StringBuilder();
            representationBuilder.append("(" + traverse(root.left, counts, duplicateTrees) + ")");
            representationBuilder.append(root.val);
            representationBuilder.append("(" + traverse(root.right, counts, duplicateTrees) + ")");

            String representation = representationBuilder.toString();
            counts.put(representation, counts.getOrDefault(representation, 0) + 1);

            if (counts.get(representation) == 2) {
                duplicateTrees.add(root);
            }

            return representation;
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

//    652. Find Duplicate Subtrees
//    Medium
//    Given the root of a binary tree, return all duplicate subtrees.
//
//    For each kind of duplicate subtrees, you only need to return the root node of any one of them.
//
//    Two trees are duplicate if they have the same structure with the same node values.
//
//
//
//    Example 1:
//
//
//    Input: root = [1,2,3,4,null,2,4,null,null,4]
//    Output: [[2,4],[4]]
//    Example 2:
//
//
//    Input: root = [2,1,1]
//    Output: [[1]]
//    Example 3:
//
//
//    Input: root = [2,2,2,3,null,3,null]
//    Output: [[2,3],[3]]
//
//
//    Constraints:
//
//    The number of the nodes in the tree will be in the range [1, 5000]
//    -200 <= Node.val <= 200