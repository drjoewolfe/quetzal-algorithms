package org.jwolfe.quetzal.algorithms;

import org.junit.jupiter.api.Test;
import org.jwolfe.quetzal.library.tree.BinaryTreeNode;
import org.jwolfe.quetzal.library.tree.ThreadedTreeNode;
import org.jwolfe.quetzal.library.utilities.Utilities;

import static org.junit.jupiter.api.Assertions.*;

class ThreadedTreeAlgorithmsTest {
    @Test
    void convertToThreadedTree() {
        BinaryTreeNode tree;
        ThreadedTreeNode threadedTree;

        tree = Utilities.constructBinaryTree(1, 2, 3, 4, 5, 6);
        threadedTree = ThreadedTreeAlgorithms.convertToThreadedTree(tree);
        ThreadedTreeAlgorithms.visitInOrder(threadedTree);
        assertNotNull(threadedTree.getLeft().getLeft().getRight());
        assertTrue(threadedTree.getLeft().getLeft().isThreaded());
        assertNotNull(threadedTree.getLeft().getRight().getRight());
        assertTrue(threadedTree.getLeft().getRight().isThreaded());
        assertNotNull(threadedTree.getRight().getLeft().getRight());
        assertTrue(threadedTree.getRight().getLeft().isThreaded());
        assertNull(threadedTree.getRight().getRight());
    }

    @Test
    void convertToThreadedTreeA2() {
        BinaryTreeNode tree;
        ThreadedTreeNode threadedTree;

        tree = Utilities.constructBinaryTree(1, 2, 3, 4, 5, 6);
        threadedTree = ThreadedTreeAlgorithms.convertToThreadedTreeA2(tree);
        ThreadedTreeAlgorithms.visitInOrder(threadedTree);
        assertNotNull(threadedTree.getLeft().getLeft().getRight());
        assertTrue(threadedTree.getLeft().getLeft().isThreaded());
        assertNotNull(threadedTree.getLeft().getRight().getRight());
        assertTrue(threadedTree.getLeft().getRight().isThreaded());
        assertNotNull(threadedTree.getRight().getLeft().getRight());
        assertTrue(threadedTree.getRight().getLeft().isThreaded());
        assertNull(threadedTree.getRight().getRight());
    }
}