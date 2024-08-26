package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class NAryTreePostorderTraversal {
    /*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

    class Solution {
        public List<Integer> postorder(Node root) {
            List<Integer> traversal = new ArrayList<>();
            if (root == null) {
                return traversal;
            }

            Stack<Node> preStack = new Stack<>();
            Stack<Node> postStack = new Stack<>();

            preStack.push(root);
            while (!preStack.isEmpty()) {
                var node = preStack.pop();
                postStack.push(node);

                for (var child : node.children) {
                    preStack.push(child);
                }
            }

            while (!postStack.isEmpty()) {
                traversal.add(postStack.pop().val);
            }

            return traversal;
        }
    }

    class Solution_Correct_1 {
        public List<Integer> postorder(Node root) {
            List<Integer> postOrder = new ArrayList<>();
            generatePostOrder(root, postOrder);

            return postOrder;
        }

        private void generatePostOrder(Node root, List<Integer> postOrder) {
            if (root == null) {
                return;
            }

            for (Node node : root.children) {
                generatePostOrder(node, postOrder);
            }

            postOrder.add(root.val);
        }
    }

    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    ;
}

//    590. N-ary Tree Postorder Traversal
//    Easy
//    Given the root of an n-ary tree, return the postorder traversal of its nodes' values.
//
//    Nary-Tree input serialization is represented in their level order traversal. Each group of children is separated by the null value (See examples)
//
//
//
//    Example 1:
//
//
//    Input: root = [1,null,3,2,4,null,5,6]
//    Output: [5,6,3,2,4,1]
//    Example 2:
//
//
//    Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
//    Output: [2,6,14,11,7,3,12,8,4,13,9,10,5,1]
//
//
//    Constraints:
//
//    The number of nodes in the tree is in the range [0, 104].
//    0 <= Node.val <= 104
//    The height of the n-ary tree is less than or equal to 1000.
//
//
//    Follow up: Recursive solution is trivial, could you do it iteratively?