package org.jwolfe.quetzal.algorithms.lc;

import java.util.LinkedList;
import java.util.Queue;

public class SerializeAndDeserializeBinaryTree {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */
    public class Codec {
        private final String NULL = "N";
        private final String SEPARATOR = ",";

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder builder = new StringBuilder();
            preOrderSerialize(root, builder);

            return builder.toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            String[] elements = data.split(SEPARATOR);
            Queue<String> queue = new LinkedList<>();
            for (String element : elements) {
                queue.offer(element);
            }

            return preOrderDeserialize(queue);
        }

        private void preOrderSerialize(TreeNode root, StringBuilder builder) {
            if (root == null) {
                builder.append(NULL).append(SEPARATOR);
                return;
            }

            builder.append(Integer.toString(root.val) + SEPARATOR);
            preOrderSerialize(root.left, builder);
            preOrderSerialize(root.right, builder);
        }

        private TreeNode preOrderDeserialize(Queue<String> queue) {
            if (queue.isEmpty()) {
                return null;
            }

            String element = queue.poll();
            if (element.equals(NULL)) {
                return null;
            }

            TreeNode root = new TreeNode(Integer.valueOf(element));
            root.left = preOrderDeserialize(queue);
            root.right = preOrderDeserialize(queue);

            return root;
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
}

//    297. Serialize and Deserialize Binary Tree
//    Hard
//    Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
//
//    Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.
//
//    Clarification: The input/output format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
//
//
//
//    Example 1:
//
//
//    Input: root = [1,2,3,null,null,4,5]
//    Output: [1,2,3,null,null,4,5]
//    Example 2:
//
//    Input: root = []
//    Output: []
//
//
//    Constraints:
//
//    The number of nodes in the tree is in the range [0, 104].
//    -1000 <= Node.val <= 1000