package org.jwolfe.quetzal.algorithms;

import org.jwolfe.quetzal.library.tree.BinaryTreeNode;
import org.jwolfe.quetzal.library.tree.ThreadedTreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public class ThreadedTreeAlgorithms {
    public static ThreadedTreeNode convertToThreadedTree(BinaryTreeNode root) {
        ThreadedTreeNode threadedRoot = cloneBinaryTree(root);

        Queue<ThreadedTreeNode> queue = new LinkedList<>();
        visitInOrderAsBinaryTree(threadedRoot, n -> queue.offer(n));
        convertToThreadedNode(threadedRoot, queue);
        return threadedRoot;
    }

    private static void convertToThreadedNode(ThreadedTreeNode node, Queue<ThreadedTreeNode> inOrderQueue) {
        if(node == null) {
            return;
        }

        if(node.getLeft() != null) {
            convertToThreadedNode(node.getLeft(), inOrderQueue);
        }

        inOrderQueue.poll();

        if(node.getRight() != null) {
            convertToThreadedNode(node.getRight(), inOrderQueue);
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
}
