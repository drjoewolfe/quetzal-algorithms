package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class SmallestStringStartingFromLeaf {
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
        public String smallestFromLeaf(TreeNode root) {
            return smallestFromLeaf(root, new ArrayList<>());
        }

        private String smallestFromLeaf(TreeNode root, List<Character> list) {
            char c = (char) ('a' + root.val);
            list.add(c);

            String str = null;
            if (root.left == null && root.right == null) {
                StringBuilder builder = new StringBuilder();
                for (int i = list.size() - 1; i >= 0; i--) {
                    builder.append(list.get(i));
                }

                str = builder.toString();
            } else {
                String left = null;
                String right = null;

                if (root.left != null) {
                    left = smallestFromLeaf(root.left, list);
                }

                if (root.right != null) {
                    right = smallestFromLeaf(root.right, list);
                }

                if (left != null && right != null) {
                    str = (left.compareTo(right) > 0) ? right : left;
                } else if (left != null) {
                    str = left;
                } else {
                    str = right;
                }
            }

            list.remove(list.size() - 1);
            return str;
        }
    }

    class Solution_Correct_1 {

        String smallestString;

        public String smallestFromLeaf(TreeNode root) {
            if (root == null) {
                return null;
            }

            smallestString = "}";
            StringBuilder builder = new StringBuilder();
            smallestFromLeaf(root, builder);

            return smallestString;
        }

        private void smallestFromLeaf(TreeNode root, StringBuilder builder) {
            if (root == null) {
                return;
            }

            char c = (char) ('a' + root.val);
            builder.append(c);

            if (root.left == null && root.right == null) {
                StringBuilder sb = new StringBuilder(builder);
                String s = sb.reverse().toString();
                if (smallestString.compareTo(s) > 0) {
                    smallestString = s;
                }

            } else {
                smallestFromLeaf(root.left, builder);
                smallestFromLeaf(root.right, builder);
            }

            builder.deleteCharAt(builder.length() - 1);
        }
    }

    class Solution_Set {
        public String smallestFromLeaf(TreeNode root) {
            if (root == null) {
                return null;
            }

            TreeSet<String> set = new TreeSet<>();
            StringBuilder builder = new StringBuilder();
            smallestFromLeaf(root, builder, set);

            return set.first();
        }

        private void smallestFromLeaf(TreeNode root, StringBuilder builder, TreeSet<String> set) {
            if (root == null) {
                return;
            }

            char c = (char) ('a' + root.val);
            builder.append(c);

            if (root.left == null && root.right == null) {
                StringBuilder sb = new StringBuilder(builder);
                String s = sb.reverse().toString();
                set.add(s);
            } else {
                smallestFromLeaf(root.left, builder, set);
                smallestFromLeaf(root.right, builder, set);
            }

            builder.deleteCharAt(builder.length() - 1);
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

// [0,1,2,3,4,3,4]
}

//    988. Smallest String Starting From Leaf
//    Medium
//    You are given the root of a binary tree where each node has a value in the range [0, 25] representing the letters 'a' to 'z'.
//
//    Return the lexicographically smallest string that starts at a leaf of this tree and ends at the root.
//
//    As a reminder, any shorter prefix of a string is lexicographically smaller.
//
//    For example, "ab" is lexicographically smaller than "aba".
//    A leaf of a node is a node that has no children.
//
//
//
//    Example 1:
//
//
//    Input: root = [0,1,2,3,4,3,4]
//    Output: "dba"
//    Example 2:
//
//
//    Input: root = [25,1,3,1,3,0,2]
//    Output: "adz"
//    Example 3:
//
//
//    Input: root = [2,2,1,null,1,0,null,0]
//    Output: "abc"
//
//
//    Constraints:
//
//    The number of nodes in the tree is in the range [1, 8500].
//    0 <= Node.val <= 25