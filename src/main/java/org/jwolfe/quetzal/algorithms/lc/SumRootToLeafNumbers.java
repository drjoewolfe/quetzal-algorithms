package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.List;

public class SumRootToLeafNumbers {
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
        public int sumNumbers(TreeNode root) {
            return sumNumbers(root, 0);
        }

        private int sumNumbers(TreeNode root, int number) {
            if(root == null) {
                return 0;
            }

            number *= 10;
            number += root.val;

            if(root.left == null && root.right == null) {
                return number;
            }

            return sumNumbers(root.left, number) + sumNumbers(root.right, number);
        }
    }

    class Solution_Correct_2 {
        int sum;

        public int sumNumbers(TreeNode root) {
            sum = 0;

            sumNumbers(root, 0);
            return sum;
        }

        private void sumNumbers(TreeNode root, int number) {
            if(root == null) {
                return;
            }

            number *= 10;
            number += root.val;

            if(root.left == null && root.right == null) {
                sum += number;
                return;
            }

            sumNumbers(root.left, number);
            sumNumbers(root.right, number);
        }
    }

    class Solution_Correct_1 {
        public int sumNumbers(TreeNode root) {
            return sumNumbers(root, 0);
        }

        private int sumNumbers(TreeNode root, int sum) {
            if(root == null) {
                return 0;
            }

            sum = sum * 10 + root.val;
            if(root.left == null && root.right == null) {
                return sum;
            } else {
                return sumNumbers(root.left, sum) + sumNumbers(root.right, sum);
            }
        }
    }

    class Solution_Classic {
        private int sum;

        public int sumNumbers(TreeNode root) {
            if(root == null) {
                return 0;
            }

            sum = 0;
            sumNumbers(root, new ArrayList<>()) ;

            return sum;
        }

        private void sumNumbers(TreeNode root, List<Integer> path) {
            if(root == null) {
                return;
            }

            path.add(root.val);
            if(root.left == null && root.right == null) {
                // Leaf Node
                int number = getNumber(path);
                sum += getNumber(path);
            } else {
                sumNumbers(root.left, path);
                sumNumbers(root.right, path);
            }

            path.remove(path.size() - 1);
            return;
        }

        private int getNumber(List<Integer> path) {
            int number = 0;
            for(int n : path) {
                number = number * 10 + n;
            }

            return number;
        }
    }

     public class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode(int x) { val = x; }
     }
}

//    129. Sum Root to Leaf Numbers
//    Medium
//    You are given the root of a binary tree containing digits from 0 to 9 only.
//
//    Each root-to-leaf path in the tree represents a number.
//
//    For example, the root-to-leaf path 1 -> 2 -> 3 represents the number 123.
//    Return the total sum of all root-to-leaf numbers. Test cases are generated so that the answer will fit in a 32-bit integer.
//
//    A leaf node is a node with no children.
//
//
//
//    Example 1:
//
//
//    Input: root = [1,2,3]
//    Output: 25
//    Explanation:
//    The root-to-leaf path 1->2 represents the number 12.
//    The root-to-leaf path 1->3 represents the number 13.
//    Therefore, sum = 12 + 13 = 25.
//    Example 2:
//
//
//    Input: root = [4,9,0,5,1]
//    Output: 1026
//    Explanation:
//    The root-to-leaf path 4->9->5 represents the number 495.
//    The root-to-leaf path 4->9->1 represents the number 491.
//    The root-to-leaf path 4->0 represents the number 40.
//    Therefore, sum = 495 + 491 + 40 = 1026.
//
//
//    Constraints:
//
//    The number of nodes in the tree is in the range [1, 1000].
//    0 <= Node.val <= 9
//    The depth of the tree will not exceed 10.