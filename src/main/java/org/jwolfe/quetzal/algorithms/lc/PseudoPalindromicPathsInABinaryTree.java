package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PseudoPalindromicPathsInABinaryTree {
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
        public int pseudoPalindromicPaths(TreeNode root) {
            if (root == null) {
                return 0;
            }

            Map<Integer, Integer> pathMap = new HashMap<>();
            return pseudoPalindromicPaths(root, pathMap);
        }

        private int pseudoPalindromicPaths(TreeNode root, Map<Integer, Integer> pathMap) {
            if (root == null) {
                return 0;
            }

            pathMap.put(root.val, pathMap.getOrDefault(root.val, 0) + 1);

            int pathCount = 0;
            if (root.left == null && root.right == null) {
                // Leaf node
                pathCount = isPalindromicPath(pathMap) ? 1 : 0;
            } else {
                pathCount += pseudoPalindromicPaths(root.left, pathMap);
                pathCount += pseudoPalindromicPaths(root.right, pathMap);
            }

            pathMap.put(root.val, pathMap.get(root.val) - 1);
            return pathCount;
        }

        private boolean isPalindromicPath(Map<Integer, Integer> pathMap) {
            int oddCount = 0;
            for (var entry : pathMap.entrySet()) {
                Integer key = entry.getKey();
                Integer count = entry.getValue();

                if (count % 2 != 0) {
                    oddCount++;
                }
            }

            return oddCount < 2;
        }
    }

    class Solution_Correct_1 {

        private int count;

        public int pseudoPalindromicPaths(TreeNode root) {
            if (root == null) {
                return 0;
            }

            count = 0;

            Map<Integer, Integer> valueCounts = new HashMap<>();
            valueCounts.put(root.val, 1);

            pseudoPalindromicPaths(root, valueCounts);
            return count;
        }

        private void pseudoPalindromicPaths(TreeNode root, Map<Integer, Integer> valueCounts) {
            if (root == null) {
                return;
            }

            if (root.left == null && root.right == null) {
                // Leaf
                if (isPseudoPalindrome(valueCounts)) {
                    count++;
                }

                return;
            }

            if (root.left != null) {
                int val = root.left.val;
                valueCounts.put(val, valueCounts.getOrDefault(val, 0) + 1);
                pseudoPalindromicPaths(root.left, valueCounts);
                valueCounts.put(val, valueCounts.get(val) - 1);
            }

            if (root.right != null) {
                int val = root.right.val;
                valueCounts.put(val, valueCounts.getOrDefault(val, 0) + 1);
                pseudoPalindromicPaths(root.right, valueCounts);
                valueCounts.put(val, valueCounts.get(val) - 1);
            }
        }

        private boolean isPseudoPalindrome(Map<Integer, Integer> valueCounts) {
            int oddCount = 0;
            int size = 0;
            for (var entry : valueCounts.entrySet()) {
                int count = entry.getValue();
                size += count;
                if (count % 2 != 0) {
                    oddCount++;

                    if (oddCount > 1) {
                        return false;
                    }
                }
            }

            if (size % 2 == 0) {
                return oddCount == 0;
            } else {
                return oddCount == 1;
            }
        }
    }


    class Solution_TLE {

        private int count;

        public int pseudoPalindromicPaths(TreeNode root) {
            if (root == null) {
                return 0;
            }

            count = 0;
            List<TreeNode> currentPath = new ArrayList<>();
            currentPath.add(root);

            pseudoPalindromicPaths(root, currentPath);
            return count;
        }

        private void pseudoPalindromicPaths(TreeNode root, List<TreeNode> path) {
            if (root == null) {
                return;
            }

            if (root.left == null && root.right == null) {
                // Leaf
                if (isPseudoPalindrome(path)) {
                    count++;
                }

                return;
            }

            if (root.left != null) {
                path.add(root.left);
                pseudoPalindromicPaths(root.left, path);
                path.remove(path.size() - 1);
            }

            if (root.right != null) {
                path.add(root.right);
                pseudoPalindromicPaths(root.right, path);
                path.remove(path.size() - 1);
            }
        }

        private boolean isPseudoPalindrome(List<TreeNode> path) {
            Map<Integer, Integer> counts = new HashMap<>();
            for (TreeNode node : path) {
                int val = node.val;
                counts.put(val, counts.getOrDefault(val, 0) + 1);
            }

            int oddCount = 0;
            for (var entry : counts.entrySet()) {
                int count = entry.getValue();
                if (count % 2 != 0) {
                    oddCount++;
                }
            }

            if (path.size() % 2 == 0) {
                return oddCount == 0;
            } else {
                return oddCount == 1;
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

//    1457. Pseudo-Palindromic Paths in a Binary Tree
//    Medium
//    Given a binary tree where node values are digits from 1 to 9. A path in the binary tree is said to be pseudo-palindromic if at least one permutation of the node values in the path is a palindrome.
//
//    Return the number of pseudo-palindromic paths going from the root node to leaf nodes.
//
//
//
//    Example 1:
//
//
//
//    Input: root = [2,3,1,3,1,null,1]
//    Output: 2
//    Explanation: The figure above represents the given binary tree. There are three paths going from the root node to leaf nodes: the red path [2,3,3], the green path [2,1,1], and the path [2,3,1]. Among these paths only red path and green path are pseudo-palindromic paths since the red path [2,3,3] can be rearranged in [3,2,3] (palindrome) and the green path [2,1,1] can be rearranged in [1,2,1] (palindrome).
//    Example 2:
//
//
//
//    Input: root = [2,1,1,1,3,null,null,null,null,null,1]
//    Output: 1
//    Explanation: The figure above represents the given binary tree. There are three paths going from the root node to leaf nodes: the green path [2,1,1], the path [2,1,3,1], and the path [2,1]. Among these paths only the green path is pseudo-palindromic since [2,1,1] can be rearranged in [1,2,1] (palindrome).
//    Example 3:
//
//    Input: root = [9]
//    Output: 1
//
//
//    Constraints:
//
//    The number of nodes in the tree is in the range [1, 105].
//    1 <= Node.val <= 9