package org.jwolfe.quetzal.algorithms.lc;

import org.jwolfe.quetzal.library.tree.TreeNode;

import java.util.*;

public class VerticalOrderTraversalOfABinaryTree {
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
        public List<List<Integer>> verticalTraversal(TreeNode root) {
            List<List<Integer>> results = new ArrayList<>();
            if(root == null) {
                return results;
            }

            Map<Integer, Map<Integer, PriorityQueue<Integer>>> map = new TreeMap<>();
            traverse(root, 0, 0, map);

            for(var entry : map.entrySet()) {
                var col = entry.getKey();
                var elementsByRow = entry.getValue();

                List<Integer> colElements = new ArrayList<>();
                results.add(colElements);

                for(var rowEntry : elementsByRow.entrySet()) {
                    var row = rowEntry.getKey();
                    var elements = rowEntry.getValue();

                    while(!elements.isEmpty()) {
                        colElements.add(elements.poll());
                    }
                }
            }

            return results;
        }

        private void traverse(TreeNode root, int row, int col, Map<Integer, Map<Integer, PriorityQueue<Integer>>> map) {
            if(root == null) {
                return;
            }

            traverse(root.left, row + 1, col - 1, map);

            if(!map.containsKey(col)) {
                map.put(col, new TreeMap<>());
            }

            var elementsByRow = map.get(col);
            if(!elementsByRow.containsKey(row)) {
                elementsByRow.put(row, new PriorityQueue<>());
            }

            elementsByRow.get(row).offer(root.val);

            traverse(root.right, row + 1, col + 1, map);
        }
    }

    class Solution_Correct_1 {
        public List<List<Integer>> verticalTraversal(TreeNode root) {
            List<List<Integer>> traversal = new ArrayList<>();
            if(root == null) {
                return traversal;
            }

            // Map<Integer, PriorityQueue<TreeNode>> xGroups = new TreeMap<>();
            Map<Integer, Map<Integer, Queue<TreeNode>>> xGroups = new TreeMap<>();

            Queue<CoordinatedNode> queue = new LinkedList<>();
            queue.offer(new CoordinatedNode(root, 0, 0));
            while(!queue.isEmpty()) {
                CoordinatedNode coNode = queue.poll();
                TreeNode node = coNode.node;
                int x = coNode.x;
                int y = coNode.y;

                if(!xGroups.containsKey(x)) {
                    xGroups.put(x, new TreeMap<>((a, b) -> b - a));
                }

                var xgroup = xGroups.get(x);
                if(!xgroup.containsKey(y)) {
                    xgroup.put(y, new PriorityQueue<TreeNode>((tn1, tn2) -> tn1.val - tn2.val));
                }

                var yGroup = xgroup.get(y).offer(node);

                if(node.left != null) {
                    queue.offer(new CoordinatedNode(node.left, x - 1, y - 1));
                }

                if(node.right != null) {
                    queue.offer(new CoordinatedNode(node.right, x + 1, y - 1));
                }
            }

            for(var xentry : xGroups.entrySet()) {
                var yGroups = xentry.getValue();
                var groupedValues = new ArrayList<Integer>();

                for(var yentry: yGroups.entrySet()) {
                    var groupedNodes = yentry.getValue();
                    while(!groupedNodes.isEmpty()) {
                        groupedValues.add(groupedNodes.poll().val);
                    }
                }

                traversal.add(groupedValues);
            }

            return traversal;
        }

        class CoordinatedNode {
            TreeNode node;
            int x;
            int y;

            public CoordinatedNode(TreeNode node, int x, int y) {
                this.node = node;
                this.x = x;
                this.y = y;
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

//    987. Vertical Order Traversal of a Binary Tree
//    Hard
//    Given the root of a binary tree, calculate the vertical order traversal of the binary tree.
//
//    For each node at position (row, col), its left and right children will be at positions (row + 1, col - 1) and (row + 1, col + 1) respectively. The root of the tree is at (0, 0).
//
//    The vertical order traversal of a binary tree is a list of top-to-bottom orderings for each column index starting from the leftmost column and ending on the rightmost column. There may be multiple nodes in the same row and same column. In such a case, sort these nodes by their values.
//
//    Return the vertical order traversal of the binary tree.
//
//
//
//    Example 1:
//
//
//    Input: root = [3,9,20,null,null,15,7]
//    Output: [[9],[3,15],[20],[7]]
//    Explanation:
//    Column -1: Only node 9 is in this column.
//    Column 0: Nodes 3 and 15 are in this column in that order from top to bottom.
//    Column 1: Only node 20 is in this column.
//    Column 2: Only node 7 is in this column.
//    Example 2:
//
//
//    Input: root = [1,2,3,4,5,6,7]
//    Output: [[4],[2],[1,5,6],[3],[7]]
//    Explanation:
//    Column -2: Only node 4 is in this column.
//    Column -1: Only node 2 is in this column.
//    Column 0: Nodes 1, 5, and 6 are in this column.
//    1 is at the top, so it comes first.
//    5 and 6 are at the same position (2, 0), so we order them by their value, 5 before 6.
//    Column 1: Only node 3 is in this column.
//    Column 2: Only node 7 is in this column.
//    Example 3:
//
//
//    Input: root = [1,2,3,4,6,5,7]
//    Output: [[4],[2],[1,5,6],[3],[7]]
//    Explanation:
//    This case is the exact same as example 2, but with nodes 5 and 6 swapped.
//    Note that the solution remains the same since 5 and 6 are in the same location and should be ordered by their values.
//
//
//    Constraints:
//
//    The number of nodes in the tree is in the range [1, 1000].
//    0 <= Node.val <= 1000