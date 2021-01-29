package org.jwolfe.quetzal.algorithms.lc;

import org.jwolfe.quetzal.library.tree.TreeNode;

import java.util.*;

public class VerticalOrderTraversalOfABinaryTree {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> traversal = new ArrayList<>();
        if (root == null) {
            return traversal;
        }

        TreeMap<Integer, PriorityQueue<NodeWithCoordinates>> map = new TreeMap<>();

        Queue<NodeWithCoordinates> queue = new LinkedList<>();
        queue.offer(new NodeWithCoordinates(root, 0, 0));

        while (!queue.isEmpty()) {
            var element = queue.poll();
            var node = element.node;
            int x = element.x;
            int y = element.y;

            map.putIfAbsent(x, new PriorityQueue<>((nc1, nc2) -> {
                if (nc1.y == nc2.y) {
                    return nc1.node.val - nc2.node.val;
                }

                return nc2.y - nc1.y;
            }));
            map.get(x).add(element);

            if (node.left != null) {
                queue.offer(new NodeWithCoordinates(node.left, x - 1, y - 1));
            }

            if (node.right != null) {
                queue.offer(new NodeWithCoordinates(node.right, x + 1, y - 1));
            }
        }

        for (Integer key : map.keySet()) {
            List<Integer> level = new ArrayList<>();
            var heap = map.get(key);
            while (!heap.isEmpty()) {
                level.add(heap.poll().node.val);
            }

            traversal.add(level);
        }

        return traversal;
    }

    class NodeWithCoordinates {
        TreeNode node;
        int x;
        int y;

        public NodeWithCoordinates(TreeNode node, int x, int y) {
            this.node = node;
            this.x = x;
            this.y = y;
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
