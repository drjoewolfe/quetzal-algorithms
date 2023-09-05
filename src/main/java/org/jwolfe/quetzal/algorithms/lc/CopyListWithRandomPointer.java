package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;

public class CopyListWithRandomPointer {
    class Solution {
        public Node copyRandomList(Node head) {
            if(head == null) {
                return head;
            }

            Node anchor = new Node(-1);
            Node prev = anchor;

            // Interweave
            while(head != null) {
                prev.next = head;

                Node clone = new Node(head.val);
                clone.next = head.next;
                head.next = clone;

                head = clone.next;
                prev = clone;
            }

            // Set random
            head = anchor.next;
            while(head != null) {
                Node clone = head.next;
                if(head.random != null) {
                    clone.random = head.random.next;
                }

                head = clone.next;
            }

            // Fix lists
            prev = anchor;
            head = anchor.next;
            while(head != null) {
                Node clone = head.next;
                prev.next = clone;
                prev = clone;

                head.next = clone.next;
                head = clone.next;
            }

            return anchor.next;
        }

        private void print(Node head) {
            while(head != null) {
                System.out.print(head.val + " -> ");
                head = head.next;
            }

            System.out.println();
        }
    }

    class Solution_Correct_2 {
        public Node copyRandomList(Node head) {
            if(head == null) {
                return null;
            }

            Node anchor = new Node(-1);
            Node prev = anchor;
            while(head != null) {
                prev.next = head;

                Node clone = new Node(head.val);
                clone.next = head.next;
                head.next = clone;

                head = clone.next;
                prev = clone;
            }

            head = anchor.next;
            while(head != null) {
                Node clone = head.next;
                if(head.random != null) {
                    clone.random = head.random.next;
                }

                head = clone.next;
            }

            head = anchor.next;
            prev = anchor;
            while(head != null) {
                Node clone = head.next;
                prev.next = clone;
                prev = clone;

                head.next = clone.next;
                head = clone.next;
            }

            return anchor.next;
        }
    }

    class Solution_Space {
        public Node copyRandomList(Node head) {
            if(head == null) {
                return head;
            }

            Node anchor = new Node(-1);
            Node prev = anchor;

            Map<Node, Node> map = new HashMap<>();

            Node runner = head;
            while(runner != null) {
                Node node = new Node(runner.val);
                prev.next = node;

                map.put(runner, node);

                runner = runner.next;
                prev = prev.next;
            }

            runner = head;
            Node clone = anchor.next;
            while(runner != null) {
                if(runner.random != null) {
                    clone.random = map.get(runner.random);
                }

                runner = runner.next;
                clone = clone.next;
            }

            return anchor.next;
        }
    }

    class Solution_Correct_1 {
        public Node copyRandomList(Node head) {
            if(head == null) {
                return null;
            }

            Node anchor = new Node(-1);
            Node prev = anchor;
            while(head != null) {
                prev.next = head;

                Node clone = new Node(head.val);
                clone.next = head.next;
                head.next = clone;

                head = clone.next;
                prev = clone;
            }

            head = anchor.next;
            while(head != null) {
                Node clone = head.next;
                if(head.random != null) {
                    clone.random = head.random.next;
                }

                head = clone.next;
            }

            head = anchor.next;
            prev = anchor;
            while(head != null) {
                Node clone = head.next;
                prev.next = clone;
                prev = clone;

                head.next = clone.next;
                head = clone.next;
            }

            return anchor.next;
        }
    }

    class Solution_Classic {
        public Node copyRandomList(Node head) {
            if(head == null) {
                return null;
            }

            Map<Node, Node> map = new HashMap<>();

            Node anchor = new Node(-1);
            Node prev = anchor;

            Node runner = head;
            while(runner != null) {
                Node clone = new Node(runner.val);
                prev.next = clone;
                prev = clone;

                map.put(runner, clone);
                runner = runner.next;
            }

            runner = head;
            Node clone = anchor.next;
            while(runner != null) {
                Node randomNext = runner.random;
                if(map.containsKey(randomNext)) {
                    clone.random = map.get(randomNext);
                }

                runner = runner.next;
                clone = clone.next;
            }

            return anchor.next;
        }
    }

    class Solution_Incorrect {
        public Node copyRandomList(Node head) {
            if(head == null) {
                return head;
            }

            Node anchor = new Node(-1);

            Node runner = head;
            Node prev = anchor;
            while(runner != null) {
                Node clone = new Node(runner.val);
                clone.random = runner.random;
                prev.next = clone;
                prev = prev.next;

                Node next = runner.next;
                runner.next = clone;

                runner = next;
            }

            runner = anchor.next;
            while(runner != null) {
                if(runner.random != null) {
                    runner.random = runner.random.next;
                }

                runner = runner.next;
            }

            return anchor.next;
        }
    }

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}

//    138. Copy List with Random Pointer
//    Medium
//    A linked list of length n is given such that each node contains an additional random pointer, which could point to any node in the list, or null.
//
//    Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes, where each new node has its value set to the value of its corresponding original node. Both the next and random pointer of the new nodes should point to new nodes in the copied list such that the pointers in the original list and copied list represent the same list state. None of the pointers in the new list should point to nodes in the original list.
//
//    For example, if there are two nodes X and Y in the original list, where X.random --> Y, then for the corresponding two nodes x and y in the copied list, x.random --> y.
//
//    Return the head of the copied linked list.
//
//    The linked list is represented in the input/output as a list of n nodes. Each node is represented as a pair of [val, random_index] where:
//
//    val: an integer representing Node.val
//    random_index: the index of the node (range from 0 to n-1) that the random pointer points to, or null if it does not point to any node.
//    Your code will only be given the head of the original linked list.
//
//
//    Example 1:
//
//
//    Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
//    Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]
//    Example 2:
//
//
//    Input: head = [[1,1],[2,1]]
//    Output: [[1,1],[2,1]]
//    Example 3:
//
//
//
//    Input: head = [[3,null],[3,0],[3,null]]
//    Output: [[3,null],[3,0],[3,null]]
//    Example 4:
//
//    Input: head = []
//    Output: []
//    Explanation: The given linked list is empty (null pointer), so return null.
//
//
//    Constraints:
//
//    0 <= n <= 1000
//    -10000 <= Node.val <= 10000
//    Node.random is null or is pointing to some node in the linked list.