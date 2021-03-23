package org.jwolfe.quetzal.algorithms.lc;

public class BinaryTreeColoringGame {
    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        if (root == null || n <= 0 || x <= 0 || x > n) {
            return false;
        }

        TreeNode node = search(root, x);

        int leftCount = countNodes(node.left);
        int rightCount = countNodes(node.right);
        int parentCount = n - (leftCount + rightCount + 1);

        if (parentCount > (leftCount + rightCount + 1)
                || leftCount > (parentCount + rightCount + 1)
                || rightCount > (parentCount + leftCount + 1)) {
            return true;
        }

        return false;
    }

    private TreeNode search(TreeNode root, int val) {
        if (root == null) {
            return null;
        }

        if (root.val == val) {
            return root;
        }

        TreeNode node = search(root.left, val);
        if (node != null) {
            return node;
        }

        return search(root.right, val);
    }

    private int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return 1 + countNodes(root.left) + countNodes(root.right);
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

//    1145. Binary Tree Coloring Game
//    Medium
//    Two players play a turn based game on a binary tree.  We are given the root of this binary tree, and the number of nodes n in the tree.  n is odd, and each node has a distinct value from 1 to n.
//
//    Initially, the first player names a value x with 1 <= x <= n, and the second player names a value y with 1 <= y <= n and y != x.  The first player colors the node with value x red, and the second player colors the node with value y blue.
//
//    Then, the players take turns starting with the first player.  In each turn, that player chooses a node of their color (red if player 1, blue if player 2) and colors an uncolored neighbor of the chosen node (either the left child, right child, or parent of the chosen node.)
//
//    If (and only if) a player cannot choose such a node in this way, they must pass their turn.  If both players pass their turn, the game ends, and the winner is the player that colored more nodes.
//
//    You are the second player.  If it is possible to choose such a y to ensure you win the game, return true.  If it is not possible, return false.
//
//
//
//    Example 1:
//
//
//    Input: root = [1,2,3,4,5,6,7,8,9,10,11], n = 11, x = 3
//    Output: true
//    Explanation: The second player can choose the node with value 2.
//
//
//    Constraints:
//
//    root is the root of a binary tree with n nodes and distinct node values from 1 to n.
//    n is odd.
//    1 <= x <= n <= 100