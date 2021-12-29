package org.jwolfe.quetzal.algorithms.lc;

import java.util.LinkedList;
import java.util.Queue;

public class PopulatingNextRightPointersInEachNode {
    class Solution {
        public Node connect(Node root) {
            return connect(root, null, null);
        }

        private Node connect(Node root, Node leftSibling, Node rightSibling) {
            if (root == null) {
                return null;
            }

            if (leftSibling != null) {
                leftSibling.next = root;
            }

            root.next = rightSibling;

            connect(root.left, leftSibling != null ? leftSibling.right : null, null);
            connect(root.right, root.left, rightSibling != null ? rightSibling.left : null);

            return root;
        }
    }

    class Solution_Recursive_1 {
        public Node connect(Node root) {
            return connect(root, null, null);
        }

        private Node connect(Node node, Node leftSibling, Node rightSibling) {
            if (node == null) {
                return node;
            }

            if (leftSibling != null) {
                leftSibling.next = node;
            }

            if (rightSibling != null) {
                node.next = rightSibling;
            }

            connect(node.left, leftSibling != null ? leftSibling.right : null, node.right);
            connect(node.right, node.left, rightSibling != null ? rightSibling.left : null);

            return node;
        }
    }

    class Solution_Classic {
        public Node connect(Node root) {
            if (root == null) {
                return root;
            }

            Queue<Node> queue = new LinkedList<>();
            queue.offer(root);

            while (!queue.isEmpty()) {
                int size = queue.size();
                Node prev = null;
                for (int i = 0; i < size; i++) {
                    Node curr = queue.poll();
                    if (prev != null) {
                        prev.next = curr;
                    }

                    if (curr.left != null) {
                        queue.offer(curr.left);
                    }

                    if (curr.right != null) {
                        queue.offer(curr.right);
                    }

                    prev = curr;
                }

                prev.next = null;
            }

            return root;
        }
    }

    // Definition for a Node.
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}

//    116. Populating Next Right Pointers in Each Node
//    Medium
//    You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. The binary tree has the following definition:
//
//    struct Node {
//    int val;
//    Node *left;
//    Node *right;
//    Node *next;
//    }
//    Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
//
//    Initially, all next pointers are set to NULL.
//
//
//
//    Example 1:
//
//
//    Input: root = [1,2,3,4,5,6,7]
//    Output: [1,#,2,3,#,4,5,6,7,#]
//    Explanation: Given the above perfect binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B. The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.
//    Example 2:
//
//    Input: root = []
//    Output: []
//
//
//    Constraints:
//
//    The number of nodes in the tree is in the range [0, 212 - 1].
//    -1000 <= Node.val <= 1000
//
//
//    Follow-up:
//
//    You may only use constant extra space.
//    The recursive approach is fine. You may assume implicit stack space does not count as extra space for this problem.