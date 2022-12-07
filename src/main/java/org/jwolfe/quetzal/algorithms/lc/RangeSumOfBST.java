package org.jwolfe.quetzal.algorithms.lc;

public class RangeSumOfBST {
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
        public int rangeSumBST(TreeNode root, int low, int high) {
            if(root == null) {
                return 0;
            }

            int sum = 0;
            if(root.val >= low && root.val <= high) {
                sum += root.val;
            }

            if(root.val > low) {
                sum += rangeSumBST(root.left, low, high);
            }

            if(root.val < high) {
                sum += rangeSumBST(root.right, low, high);
            }

            return sum;
        }
    }

    class Solution_Correct_2 {
        int sum;

        public int rangeSumBST(TreeNode root, int low, int high) {
            sum = 0;
            rangeSumBSTHelper(root, low, high);

            return sum;
        }

        private void rangeSumBSTHelper(TreeNode root, int low, int high) {
            if(root == null) {
                return;
            }

            if(root.val >= low && root.val <= high) {
                sum += root.val;
            }

            if(root.val >= low) {
                rangeSumBSTHelper(root.left, low, high);
            }

            if(root.val <= high) {
                rangeSumBSTHelper(root.right, low, high);
            }
        }
    }

    class Solution_Correct_1 {
        int sum = 0;

        public synchronized int rangeSumBST(TreeNode root, int low, int high) {
            sum = 0;

            rangeSumBSTHelper(root, low, high);
            return sum;
        }

        private void rangeSumBSTHelper(TreeNode root, int low, int high) {
            if (root == null) {
                return;
            }

            if ((root.val >= low) && (root.val <= high)) {
                sum += root.val;
            }

            if (root.val > low) {
                rangeSumBSTHelper(root.left, low, high);
            }

            if (root.val < high) {
                rangeSumBSTHelper(root.right, low, high);
            }
        }
    }

    class Solution_Classic {
        public int rangeSumBST(TreeNode root, int low, int high) {
            if (root == null) {
                return 0;
            }

            return rangeSumBST(root.left, low, high)
                    + rangeSumBST(root.right, low, high)
                    + (((root.val >= low) && (root.val <= high)) ? root.val : 0);
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

//    938. Range Sum of BST
//    Easy
//    Given the root node of a binary search tree and two integers low and high, return the sum of values of all nodes with a value in the inclusive range [low, high].
//
//
//
//    Example 1:
//
//
//    Input: root = [10,5,15,3,7,null,18], low = 7, high = 15
//    Output: 32
//    Explanation: Nodes 7, 10, and 15 are in the range [7, 15]. 7 + 10 + 15 = 32.
//    Example 2:
//
//
//    Input: root = [10,5,15,3,7,13,18,1,null,6], low = 6, high = 10
//    Output: 23
//    Explanation: Nodes 6, 7, and 10 are in the range [6, 10]. 6 + 7 + 10 = 23.
//
//
//    Constraints:
//
//    The number of nodes in the tree is in the range [1, 2 * 104].
//    1 <= Node.val <= 105
//    1 <= low <= high <= 105
//    All Node.val are unique.