package org.jwolfe.quetzal.algorithms.lc;

public class ConstructBinarySearchTreeFromPreorderTraversal {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode() {}
     *     TreeNode(int val) { this.val = val; }
     *     TreeNode(int val, TreeNode left, TreeNode right) {
     *         this.val = val;
     *         this.left = left;
     *         this.right = right;
     *     }
     * }
     */

    class Solution {
        public TreeNode bstFromPreorder(int[] preorder) {
            if(preorder == null || preorder.length == 0) {
                return null;
            }

            return bstFromPreorder(preorder, 0, preorder.length - 1);
        }

        private TreeNode bstFromPreorder(int[] preorder, int left, int right) {
            if(left > right) {
                return null;
            }

            if(left == right) {
                return new TreeNode(preorder[left]);
            }

            TreeNode node = new TreeNode(preorder[left]);
            int pivot = findNextLargerIndex(preorder, preorder[left], left + 1, right);
            if(pivot == -1) {
                node.left = bstFromPreorder(preorder, left + 1, right);
            } else {
                node.left = bstFromPreorder(preorder, left + 1, pivot - 1);
                node.right = bstFromPreorder(preorder, pivot, right);
            }

            return node;
        }

        private int findNextLargerIndex(int[] arr, int val, int left, int right) {
            int index = -1;
            while(left <= right) {
                int mid = left + (right - left) / 2;

                if(arr[mid] > val) {
                    index = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            return index;
        }
    }

    class Solution_Correct_1 {
        public TreeNode bstFromPreorder(int[] preorder) {
            if(preorder == null || preorder.length == 0) {
                return null;
            }

            return bstFromPreorder(preorder, 0, preorder.length - 1);
        }

        private TreeNode bstFromPreorder(int[] preorder, int start, int end) {
            if(start > end) {
                return null;
            }

            TreeNode root = new TreeNode(preorder[start]);
            int pivot = binarySearchForNextGreaterIndex(preorder, root.val, start + 1, end);

            if(pivot == -1) {
                root.left = bstFromPreorder(preorder, start + 1, end);
            } else {
                root.left = bstFromPreorder(preorder, start + 1, pivot - 1);
                root.right = bstFromPreorder(preorder, pivot, end);
            }

            return root;
        }

        private int binarySearchForNextGreaterIndex(int[] arr, int num, int left, int right) {
            int index = -1;
            while(left <= right) {
                int mid = left + (right - left) / 2;

                if(arr[mid] <= num) {
                    left = mid + 1;
                } else {
                    index = mid;
                    right = mid - 1;
                }
            }

            return index;
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}

//    1008. Construct Binary Search Tree from Preorder Traversal
//    Medium
//    Given an array of integers preorder, which represents the preorder traversal of a BST (i.e., binary search tree), construct the tree and return its root.
//
//    It is guaranteed that there is always possible to find a binary search tree with the given requirements for the given test cases.
//
//    A binary search tree is a binary tree where for every node, any descendant of Node.left has a value strictly less than Node.val, and any descendant of Node.right has a value strictly greater than Node.val.
//
//    A preorder traversal of a binary tree displays the value of the node first, then traverses Node.left, then traverses Node.right.
//
//
//
//    Example 1:
//
//
//    Input: preorder = [8,5,1,7,10,12]
//    Output: [8,5,10,1,7,null,12]
//    Example 2:
//
//    Input: preorder = [1,3]
//    Output: [1,null,3]
//
//
//    Constraints:
//
//    1 <= preorder.length <= 100
//    1 <= preorder[i] <= 108
//    All the values of preorder are unique.
