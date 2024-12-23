package org.jwolfe.quetzal.algorithms.lc;

import java.util.*;

public class MinimumNumberOfOperationsToSortABinaryTreeByLevel {
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
        public int minimumOperations(TreeNode root) {
            if (root == null) {
                return 0;
            }

            int swaps = 0;

            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);

            while (!queue.isEmpty()) {
                int size = queue.size();
                int[] levelValues = new int[size];

                for (int i = 0; i < size; i++) {
                    var node = queue.poll();
                    levelValues[i] = node.val;

                    if (node.left != null) {
                        queue.offer(node.left);
                    }

                    if (node.right != null) {
                        queue.offer(node.right);
                    }
                }

                swaps += getMinSwaps(levelValues);
            }

            return swaps;
        }

        private int getMinSwaps(int[] original) {
            int swaps = 0;
            int n = original.length;

            int[] sorted = original.clone();
            Arrays.sort(sorted);
            Map<Integer, Integer> locations = new HashMap<>();
            for (int i = 0; i < n; i++) {
                locations.put(original[i], i);
            }

            for (int i = 0; i < n; i++) {
                int x = original[i];
                int y = sorted[i];
                if (x != y) {
                    swaps++;

                    int j = locations.get(y);

                    // Swap i & j
                    locations.put(x, j);
                    locations.put(y, i);

                    original[i] = y;
                    original[j] = x;
                }
            }

            return swaps;
        }

        private void print(int[] arr) {
            for (int a : arr) {
                System.out.print(a + " ");
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

//    2471. Minimum Number of Operations to Sort a Binary Tree by Level
//    Medium
//    You are given the root of a binary tree with unique values.
//
//    In one operation, you can choose any two nodes at the same level and swap their values.
//
//    Return the minimum number of operations needed to make the values at each level sorted in a strictly increasing order.
//
//    The level of a node is the number of edges along the path between it and the root node.
//
//
//
//    Example 1:
//
//
//    Input: root = [1,4,3,7,6,8,5,null,null,null,null,9,null,10]
//    Output: 3
//    Explanation:
//    - Swap 4 and 3. The 2nd level becomes [3,4].
//    - Swap 7 and 5. The 3rd level becomes [5,6,8,7].
//    - Swap 8 and 7. The 3rd level becomes [5,6,7,8].
//    We used 3 operations so return 3.
//    It can be proven that 3 is the minimum number of operations needed.
//    Example 2:
//
//
//    Input: root = [1,3,2,7,6,5,4]
//    Output: 3
//    Explanation:
//    - Swap 3 and 2. The 2nd level becomes [2,3].
//    - Swap 7 and 4. The 3rd level becomes [4,6,5,7].
//    - Swap 6 and 5. The 3rd level becomes [4,5,6,7].
//    We used 3 operations so return 3.
//    It can be proven that 3 is the minimum number of operations needed.
//    Example 3:
//
//
//    Input: root = [1,2,3,4,5,6]
//    Output: 0
//    Explanation: Each level is already sorted in increasing order so return 0.
//
//
//    Constraints:
//
//    The number of nodes in the tree is in the range [1, 105].
//    1 <= Node.val <= 105
//    All the values of the tree are unique.