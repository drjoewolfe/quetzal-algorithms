package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class FindElementsInAContaminatedBinaryTree {
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

    class FindElements {
        Set<Integer> set;

        public FindElements(TreeNode root) {
            set = new HashSet<>();

            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            root.val = 0;

            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                set.add(node.val);

                if (node.left != null) {
                    node.left.val = node.val * 2 + 1;
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    node.right.val = node.val * 2 + 2;
                    queue.offer(node.right);
                }
            }
        }

        public boolean find(int target) {
            return set.contains(target);
        }
    }

    class FindElements_Correct_1 {
        TreeNode root;
        Set<Integer> set;

        public FindElements_Correct_1(TreeNode root) {
            set = new HashSet<>();
            recoverTree(root, 0);
            this.root = root;
        }

        public boolean find(int target) {
            return set.contains(target);
        }

        private void recoverTree(TreeNode root, int val) {
            if (root == null) {
                return;
            }

            root.val = val;
            set.add(val);

            recoverTree(root.left, val * 2 + 1);
            recoverTree(root.right, val * 2 + 2);
        }
    }

/**
 * Your FindElements object will be instantiated and called as such:
 * FindElements obj = new FindElements(root);
 * boolean param_1 = obj.find(target);
 */

// ["FindElements","find","find"]
// [[[-1,null,-1]],[1],[2]]
}

//    1261. Find Elements in a Contaminated Binary Tree
//    Medium
//    Given a binary tree with the following rules:
//
//    root.val == 0
//    For any treeNode:
//    If treeNode.val has a value x and treeNode.left != null, then treeNode.left.val == 2 * x + 1
//    If treeNode.val has a value x and treeNode.right != null, then treeNode.right.val == 2 * x + 2
//    Now the binary tree is contaminated, which means all treeNode.val have been changed to -1.
//
//    Implement the FindElements class:
//
//    FindElements(TreeNode* root) Initializes the object with a contaminated binary tree and recovers it.
//    bool find(int target) Returns true if the target value exists in the recovered binary tree.
//
//
//    Example 1:
//
//
//    Input
//    ["FindElements","find","find"]
//    [[[-1,null,-1]],[1],[2]]
//    Output
//    [null,false,true]
//    Explanation
//    FindElements findElements = new FindElements([-1,null,-1]);
//    findElements.find(1); // return False
//    findElements.find(2); // return True
//    Example 2:
//
//
//    Input
//    ["FindElements","find","find","find"]
//    [[[-1,-1,-1,-1,-1]],[1],[3],[5]]
//    Output
//    [null,true,true,false]
//    Explanation
//    FindElements findElements = new FindElements([-1,-1,-1,-1,-1]);
//    findElements.find(1); // return True
//    findElements.find(3); // return True
//    findElements.find(5); // return False
//    Example 3:
//
//
//    Input
//    ["FindElements","find","find","find","find"]
//    [[[-1,null,-1,-1,null,-1]],[2],[3],[4],[5]]
//    Output
//    [null,true,false,false,true]
//    Explanation
//    FindElements findElements = new FindElements([-1,null,-1,-1,null,-1]);
//    findElements.find(2); // return True
//    findElements.find(3); // return False
//    findElements.find(4); // return False
//    findElements.find(5); // return True
//
//
//    Constraints:
//
//    TreeNode.val == -1
//    The height of the binary tree is less than or equal to 20
//    The total number of nodes is between [1, 104]
//    Total calls of find() is between [1, 104]
//    0 <= target <= 106
