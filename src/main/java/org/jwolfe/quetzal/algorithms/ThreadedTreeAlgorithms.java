package org.jwolfe.quetzal.algorithms;

import org.jwolfe.quetzal.library.tree.BinaryTreeNode;
import org.jwolfe.quetzal.library.tree.ThreadedTreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public class ThreadedTreeAlgorithms {
    public static ThreadedTreeNode convertToThreadedTree(BinaryTreeNode root) {
        ThreadedTreeNode threadedRoot = cloneBinaryTree(root);
        convertToThreadedNode(threadedRoot);

        return threadedRoot;
    }

    private static ThreadedTreeNode convertToThreadedNode(ThreadedTreeNode root) {
        if(root == null) {
            return null;
        }

        if(isLeaf(root)) {
            return root;
        }

        if(root.getLeft() != null) {
            var inOrderPredecessor = convertToThreadedNode(root.getLeft());

            inOrderPredecessor.setRight(root);
            inOrderPredecessor.setThreaded(true);
        }

        if(root.getRight() == null) {
            return root;
        }

        return convertToThreadedNode(root.getRight());
    }

    public static ThreadedTreeNode convertToThreadedTreeA2(BinaryTreeNode root) {
        ThreadedTreeNode threadedRoot = cloneBinaryTree(root);

        Queue<ThreadedTreeNode> queue = new LinkedList<>();
        visitInOrderAsBinaryTree(threadedRoot, n -> queue.offer(n));
        convertToThreadedNodeA2(threadedRoot, queue);
        return threadedRoot;
    }

    private static void convertToThreadedNodeA2(ThreadedTreeNode node, Queue<ThreadedTreeNode> inOrderQueue) {
        if(node == null) {
            return;
        }

        if(node.getLeft() != null) {
            convertToThreadedNodeA2(node.getLeft(), inOrderQueue);
        }

        inOrderQueue.poll();

        if(node.getRight() != null) {
            convertToThreadedNodeA2(node.getRight(), inOrderQueue);
        }
        else {
            node.setRight(inOrderQueue.peek());
            node.setThreaded(true);
        }
    }

    private static ThreadedTreeNode cloneBinaryTree(BinaryTreeNode node) {
        if(node == null) {
            return null;
        }

        ThreadedTreeNode threadedNode = new ThreadedTreeNode(node.getData());
        threadedNode.setLeft(cloneBinaryTree(node.getLeft()));
        threadedNode.setRight(cloneBinaryTree(node.getRight()));

        return threadedNode;
    }

    public static void visitInOrder(ThreadedTreeNode root) {
        visitInOrder(root, n -> System.out.print(n.getData() + " "));
    }

    public static void visitInOrder(ThreadedTreeNode root, Consumer<ThreadedTreeNode> visit) {
        if(root == null) {
            return;
        }

        var node = getLeftMostNode(root);
        while(node != null) {
            if(visit != null) {
                visit.accept(node);
            }

            if(node.isThreaded()) {
                node = node.getRight();
            }
            else {
                node = getLeftMostNode(node.getRight());
            }
        }

    }

    private static ThreadedTreeNode getLeftMostNode(ThreadedTreeNode node) {
        while(node != null
                && node.getLeft() != null) {
            node = node.getLeft();
        }

        return node;
    }

    public static void visitInOrderAsBinaryTree(ThreadedTreeNode threadedNode, Consumer<ThreadedTreeNode> visit) {
        if(threadedNode == null) {
            return;
        }

        visitInOrderAsBinaryTree(threadedNode.getLeft(), visit);

        if(visit != null) {
            visit.accept(threadedNode);
        }

        visitInOrderAsBinaryTree(threadedNode.getRight(), visit);
    }

    public static boolean isLeaf(ThreadedTreeNode node) {
        return node.getLeft() == null
                && node.getRight() == null;
    }
}
