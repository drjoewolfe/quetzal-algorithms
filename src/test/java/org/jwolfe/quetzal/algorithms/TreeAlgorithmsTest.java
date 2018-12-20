package org.jwolfe.quetzal.algorithms;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.jwolfe.quetzal.library.list.LinkedListNode;
import org.jwolfe.quetzal.library.tree.BTNode;
import org.jwolfe.quetzal.library.tree.BinaryTreeNode;
import org.jwolfe.quetzal.library.general.Pair;
import org.jwolfe.quetzal.library.utilities.Utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

class TreeAlgorithmsTest {
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void visitPreOrder() {
        System.out.println();

        BinaryTreeNode tree = new BinaryTreeNode(1);
        tree.setLeft(new BinaryTreeNode(2));
        tree.setRight(new BinaryTreeNode(3));
        tree.getLeft().setLeft(new BinaryTreeNode(4));
        tree.getLeft().setRight(new BinaryTreeNode(5));

        TreeAlgorithms.visitPreOrder(tree);
    }

    @Test
    void visitPreOrderIterative() {
        System.out.println();

        BinaryTreeNode tree = new BinaryTreeNode(1);
        tree.setLeft(new BinaryTreeNode(2));
        tree.setRight(new BinaryTreeNode(3));
        tree.getLeft().setLeft(new BinaryTreeNode(4));
        tree.getLeft().setRight(new BinaryTreeNode(5));

        TreeAlgorithms.visitPreOrderIterative(tree);
    }

    @Test
    void visitPreOrderMorris() {
        BinaryTreeNode tree;
        ArrayList<Integer> preOrder = new ArrayList<>();;
        int[] expectedPreOrder;

        tree = Utilities.constructBinaryTree(1, 2, 3, 4, 5);
        expectedPreOrder = Utilities.constructArray(1, 2, 4, 5, 3);
        preOrder.clear();
        TreeAlgorithms.visitPreOrderMorris(tree, n -> preOrder.add(n.getData()));
        assertArrayEquals(expectedPreOrder, preOrder.stream().mapToInt(i->i).toArray());

        tree = Utilities.constructBinaryTree(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
        expectedPreOrder = Utilities.constructArray(1, 2, 4, 8, 9, 5, 10, 11, 3, 6, 7);
        preOrder.clear();
        TreeAlgorithms.visitPreOrderMorris(tree, n -> preOrder.add(n.getData()));
        assertArrayEquals(expectedPreOrder, preOrder.stream().mapToInt(i->i).toArray());
    }

    @Test
    void visitInOrderMorris() {
        BinaryTreeNode tree;
        ArrayList<Integer> inOrder = new ArrayList<>();;
        int[] expectedInOrder;

        tree = Utilities.constructBinaryTree(1, 2, 3, 4, 5);
        expectedInOrder = Utilities.constructArray(4, 2, 5, 1, 3);
        inOrder.clear();
        TreeAlgorithms.visitInOrderMorris(tree, n -> inOrder.add(n.getData()));
        assertArrayEquals(expectedInOrder, inOrder.stream().mapToInt(i->i).toArray());

        tree = Utilities.constructBinaryTree(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
        expectedInOrder = Utilities.constructArray(8, 4, 9, 2, 10, 5, 11, 1, 6, 3, 7);
        inOrder.clear();
        TreeAlgorithms.visitInOrderMorris(tree, n -> inOrder.add(n.getData()));
        assertArrayEquals(expectedInOrder, inOrder.stream().mapToInt(i->i).toArray());
    }

    @Test
    void visitInOrder() {
        System.out.println();

        BinaryTreeNode tree = new BinaryTreeNode(1);
        tree.setLeft(new BinaryTreeNode(2));
        tree.setRight(new BinaryTreeNode(3));
        tree.getLeft().setLeft(new BinaryTreeNode(4));
        tree.getLeft().setRight(new BinaryTreeNode(5));

        TreeAlgorithms.visitInOrder(tree);
    }

    @Test
    void visitInOrderIterative() {
        System.out.println();

        BinaryTreeNode tree = new BinaryTreeNode(1);
        tree.setLeft(new BinaryTreeNode(2));
        tree.setRight(new BinaryTreeNode(3));
        tree.getLeft().setLeft(new BinaryTreeNode(4));
        tree.getLeft().setRight(new BinaryTreeNode(5));

        TreeAlgorithms.visitInOrderIterative(tree);
    }

    @Test
    void visitPostOrder() {
        System.out.println();

        BinaryTreeNode tree = new BinaryTreeNode(1);
        tree.setLeft(new BinaryTreeNode(2));
        tree.setRight(new BinaryTreeNode(3));
        tree.getLeft().setLeft(new BinaryTreeNode(4));
        tree.getLeft().setRight(new BinaryTreeNode(5));

        TreeAlgorithms.visitPostOrder(tree);
    }

    @Test
    void visitPostOrderIterative() {
        System.out.println();

        BinaryTreeNode tree = new BinaryTreeNode(1);
        tree.setLeft(new BinaryTreeNode(2));
        tree.setRight(new BinaryTreeNode(3));
        tree.getLeft().setLeft(new BinaryTreeNode(4));
        tree.getLeft().setRight(new BinaryTreeNode(5));

        TreeAlgorithms.visitPostOrderIterative(tree);
    }

    @Test
    void visitLevelOrder() {
        System.out.println();

        BinaryTreeNode tree = new BinaryTreeNode(1);
        tree.setLeft(new BinaryTreeNode(2));
        tree.setRight(new BinaryTreeNode(3));
        tree.getLeft().setLeft(new BinaryTreeNode(4));
        tree.getLeft().setRight(new BinaryTreeNode(5));

        TreeAlgorithms.visitLevelOrder(tree);
    }

    @Test
    void visitLevelOrderRecursive() {
        System.out.println();

        BinaryTreeNode tree = new BinaryTreeNode(1);
        tree.setLeft(new BinaryTreeNode(2));
        tree.setRight(new BinaryTreeNode(3));
        tree.getLeft().setLeft(new BinaryTreeNode(4));
        tree.getLeft().setRight(new BinaryTreeNode(5));

        TreeAlgorithms.visitLevelOrderRecursive(tree);
    }

    @Test
    void visitLevelOrderReverse() {
        System.out.println();

        BinaryTreeNode tree = new BinaryTreeNode(1);
        tree.setLeft(new BinaryTreeNode(2));
        tree.setRight(new BinaryTreeNode(3));
        tree.getLeft().setLeft(new BinaryTreeNode(4));
        tree.getLeft().setRight(new BinaryTreeNode(5));

        TreeAlgorithms.visitLevelOrderReverse(tree);
    }

    @Test
    void visitLevelOrderReverseRecursive() {
        System.out.println();

        BinaryTreeNode tree = new BinaryTreeNode(1);
        tree.setLeft(new BinaryTreeNode(2));
        tree.setRight(new BinaryTreeNode(3));
        tree.getLeft().setLeft(new BinaryTreeNode(4));
        tree.getLeft().setRight(new BinaryTreeNode(5));

        TreeAlgorithms.visitLevelOrderReverseRecursive(tree);
    }

    @Test
    void getDiameter() {
        System.out.println();

        BinaryTreeNode tree = Utilities.constructBinaryTree(1, 2, 3, 4, 5);
        int diameter = TreeAlgorithms.getDiameter(tree);
        assertEquals(4, diameter);
    }

    @Test
    void topView() {
        BinaryTreeNode tree = new BinaryTreeNode(1);
        tree.setLeft(new BinaryTreeNode(2));
        tree.setRight(new BinaryTreeNode(3));
        tree.getLeft().setRight(new BinaryTreeNode(4));
        tree.getLeft().getRight().setRight(new BinaryTreeNode(5));
        tree.getLeft().getRight().getRight().setRight(new BinaryTreeNode(6));

        System.out.println("Following are nodes in top view of Binary Tree");
        TreeAlgorithms.topView(tree);
    }

    @Test
    void isSymmetricIterative() {
        BinaryTreeNode tree = new BinaryTreeNode(1);
        tree.setLeft(new BinaryTreeNode(2));
        tree.setRight(new BinaryTreeNode(2));
        tree.getLeft().setLeft(new BinaryTreeNode(3));
        tree.getLeft().setRight(new BinaryTreeNode(4));
        tree.getRight().setLeft(new BinaryTreeNode(4));
        tree.getRight().setRight(new BinaryTreeNode(3));

        boolean isSymmetric = TreeAlgorithms.isSymmetricIterative(tree);
        assertEquals(true, isSymmetric);
        if (isSymmetric)
            System.out.println("The given tree is Symmetric");
        else
            System.out.println("The given tree is not Symmetric");
    }

    @Test
    void getCorners() {
        BinaryTreeNode tree = new BinaryTreeNode(15);
        tree.setLeft(new BinaryTreeNode(10));
        tree.setRight(new BinaryTreeNode(20));
        tree.getLeft().setLeft(new BinaryTreeNode(8));
        tree.getLeft().setRight(new BinaryTreeNode(12));
        tree.getRight().setLeft(new BinaryTreeNode(16));
        tree.getRight().setRight(new BinaryTreeNode(25));

        int[] corners = TreeAlgorithms.getCorners(tree);
        int[] expectedCorners = new int[] {15, 10, 20, 8, 25};
        System.out.println(Arrays.toString(corners));
        assertArrayEquals(expectedCorners, corners);
    }

    @Test
    void getPostorderFromInorderAndPreorder() {
        int[] inorder = { 4, 2, 5, 1, 3, 6 };
        int[] preorder = { 1, 2, 4, 5, 3, 6 };
        int[] expectedPostorder = {4, 5, 2, 6, 3, 1};

        int[] postorder = TreeAlgorithms.getPostOrderFromInOrderAndPreOrder(inorder, preorder);

        System.out.println("Inorder: \t" + Arrays.toString(inorder));
        System.out.println("Preorder: \t" + Arrays.toString(preorder));
        System.out.println("Postorder: \t" + Arrays.toString(postorder));

        assertArrayEquals(expectedPostorder, postorder);
    }

    @Test
    void visitBoundary() {
        BinaryTreeNode tree = new BinaryTreeNode(20);
        tree.setLeft(new BinaryTreeNode(8));
        tree.setRight(new BinaryTreeNode(22));
        tree.getLeft().setLeft(new BinaryTreeNode(4));
        tree.getLeft().setRight(new BinaryTreeNode(12));
        tree.getLeft().getRight().setLeft(new BinaryTreeNode(10));
        tree.getLeft().getRight().setRight(new BinaryTreeNode(14));
        tree.getRight().setRight(new BinaryTreeNode(25));

        TreeAlgorithms.visitBoundary(tree);
        System.out.println();
    }

    @Test
    void getDepthFromPreorderCode() {
        String  preorderCode;
        int depth;

        preorderCode = "nlnnlll";
        depth = TreeAlgorithms.getDepthFromPreOrderCode(preorderCode);
        System.out.println(preorderCode + " - > " + depth);
        assertEquals(3, depth);
    }

    @Test
    void visitLevelOrderInSpiralForm() {
        BinaryTreeNode tree = new BinaryTreeNode(1);
        tree.setLeft(new BinaryTreeNode(2));
        tree.setRight(new BinaryTreeNode(3));
        tree.getLeft().setLeft(new BinaryTreeNode(7));
        tree.getLeft().setRight(new BinaryTreeNode(6));
        tree.getRight().setLeft(new BinaryTreeNode(5));
        tree.getRight().setRight(new BinaryTreeNode(4));

        TreeAlgorithms.visitLevelOrderInSpiralForm(tree);
    }

    @Test
    void visitLevelOrderInSpiralFormRecursive() {
        BinaryTreeNode tree = new BinaryTreeNode(1);
        tree.setLeft(new BinaryTreeNode(2));
        tree.setRight(new BinaryTreeNode(3));
        tree.getLeft().setLeft(new BinaryTreeNode(7));
        tree.getLeft().setRight(new BinaryTreeNode(6));
        tree.getRight().setLeft(new BinaryTreeNode(5));
        tree.getRight().setRight(new BinaryTreeNode(4));

        TreeAlgorithms.visitLevelOrderInSpiralFormRecursive(tree);
    }

    @Test
    void visitNodesBetweenTwoLevels() {
        BinaryTreeNode tree = new BinaryTreeNode(1);
        tree.setLeft(new BinaryTreeNode(2));
        tree.setRight(new BinaryTreeNode(3));
        tree.getLeft().setLeft(new BinaryTreeNode(4));
        tree.getLeft().setRight(new BinaryTreeNode(5));
        tree.getRight().setLeft(new BinaryTreeNode(6));
        tree.getRight().setRight(new BinaryTreeNode(7));

        TreeAlgorithms.visitNodesBetweenTwoLevels(tree, 2, 3);
    }

    @Test
    void isSubTree() {
        BinaryTreeNode tree1 = new BinaryTreeNode(26);
        tree1.setLeft(new BinaryTreeNode(10));
        tree1.setRight(new BinaryTreeNode(3));
        tree1.getLeft().setLeft(new BinaryTreeNode(4));
        tree1.getLeft().setRight(new BinaryTreeNode(6));
        tree1.getLeft().getRight().setRight(new BinaryTreeNode(30));
        tree1.getRight().setRight(new BinaryTreeNode(3));

        BinaryTreeNode tree2 = new BinaryTreeNode(10);
        tree2.setLeft(new BinaryTreeNode(4));
        tree2.setRight(new BinaryTreeNode(6));
        tree2.getRight().setRight(new BinaryTreeNode(30));

        BinaryTreeNode tree3 = new BinaryTreeNode(1);
        tree3.setLeft(new BinaryTreeNode(2));
        tree3.setRight(new BinaryTreeNode(3));
        tree3.getLeft().setLeft(new BinaryTreeNode(7));
        tree3.getLeft().setRight(new BinaryTreeNode(6));
        tree3.getRight().setLeft(new BinaryTreeNode(5));
        tree3.getRight().setRight(new BinaryTreeNode(4));

        boolean isSubTree;

        isSubTree = TreeAlgorithms.isSubTree(tree1, tree2);
        assertEquals(true, isSubTree);

        isSubTree = TreeAlgorithms.isSubTree(tree1, tree1);
        assertEquals(true, isSubTree);

        isSubTree = TreeAlgorithms.isSubTree(tree2, tree2);
        assertEquals(true, isSubTree);

        isSubTree = TreeAlgorithms.isSubTree(tree1, tree3);
        assertEquals(false, isSubTree);

        isSubTree = TreeAlgorithms.isSubTree(tree2, tree3);
        assertEquals(false, isSubTree);

        isSubTree = TreeAlgorithms.isSubTree(tree3, tree1);
        assertEquals(false, isSubTree);

        isSubTree = TreeAlgorithms.isSubTree(tree3, tree2);
        assertEquals(false, isSubTree);

        isSubTree = TreeAlgorithms.isSubTree(tree2, tree1);
        assertEquals(false, isSubTree);
    }

    @Test
    void isSubTreeRecursive() {
        BinaryTreeNode tree1 = new BinaryTreeNode(26);
        tree1.setLeft(new BinaryTreeNode(10));
        tree1.setRight(new BinaryTreeNode(3));
        tree1.getLeft().setLeft(new BinaryTreeNode(4));
        tree1.getLeft().setRight(new BinaryTreeNode(6));
        tree1.getLeft().getRight().setRight(new BinaryTreeNode(30));
        tree1.getRight().setRight(new BinaryTreeNode(3));

        BinaryTreeNode tree2 = new BinaryTreeNode(10);
        tree2.setLeft(new BinaryTreeNode(4));
        tree2.setRight(new BinaryTreeNode(6));
        tree2.getRight().setRight(new BinaryTreeNode(30));

        BinaryTreeNode tree3 = new BinaryTreeNode(1);
        tree3.setLeft(new BinaryTreeNode(2));
        tree3.setRight(new BinaryTreeNode(3));
        tree3.getLeft().setLeft(new BinaryTreeNode(7));
        tree3.getLeft().setRight(new BinaryTreeNode(6));
        tree3.getRight().setLeft(new BinaryTreeNode(5));
        tree3.getRight().setRight(new BinaryTreeNode(4));

        boolean isSubTree;

        isSubTree = TreeAlgorithms.isSubTreeRecursive(tree1, tree2);
        assertEquals(true, isSubTree);

        isSubTree = TreeAlgorithms.isSubTreeRecursive(tree1, tree1);
        assertEquals(true, isSubTree);

        isSubTree = TreeAlgorithms.isSubTreeRecursive(tree2, tree2);
        assertEquals(true, isSubTree);

        isSubTree = TreeAlgorithms.isSubTreeRecursive(tree1, tree3);
        assertEquals(false, isSubTree);

        isSubTree = TreeAlgorithms.isSubTreeRecursive(tree2, tree3);
        assertEquals(false, isSubTree);

        isSubTree = TreeAlgorithms.isSubTreeRecursive(tree3, tree1);
        assertEquals(false, isSubTree);

        isSubTree = TreeAlgorithms.isSubTreeRecursive(tree3, tree2);
        assertEquals(false, isSubTree);

        isSubTree = TreeAlgorithms.isSubTreeRecursive(tree2, tree1);
        assertEquals(false, isSubTree);
    }

    @Test
    void areLeafTraversalsSame() {
        BinaryTreeNode tree1 = new BinaryTreeNode(1);
        tree1.setLeft(new BinaryTreeNode(2));
        tree1.setRight(new BinaryTreeNode(3));
        tree1.getLeft().setLeft(new BinaryTreeNode(4));
        tree1.getRight().setLeft(new BinaryTreeNode(6));
        tree1.getRight().setRight(new BinaryTreeNode(7));

        BinaryTreeNode tree2 = new BinaryTreeNode(0);
        tree2.setLeft(new BinaryTreeNode(1));
        tree2.setRight(new BinaryTreeNode(5));
        tree2.getLeft().setRight(new BinaryTreeNode(4));
        tree2.getRight().setLeft(new BinaryTreeNode(6));
        tree2.getRight().setRight(new BinaryTreeNode(7));

        BinaryTreeNode tree3 = new BinaryTreeNode(1);
        tree3.setLeft(new BinaryTreeNode(2));
        tree3.setRight(new BinaryTreeNode(3));
        tree3.getLeft().setLeft(new BinaryTreeNode(7));
        tree3.getLeft().setRight(new BinaryTreeNode(6));
        tree3.getRight().setLeft(new BinaryTreeNode(5));
        tree3.getRight().setRight(new BinaryTreeNode(4));

        boolean leafTraversalSame = TreeAlgorithms.areLeafTraversalsSame(tree1, tree2);
        assertEquals(true, leafTraversalSame);

        leafTraversalSame = TreeAlgorithms.areLeafTraversalsSame(tree1, tree3);
        assertEquals(false, leafTraversalSame);
    }

    @Test
    void getDensity() {
        BinaryTreeNode tree;
        double density;

        tree = Utilities.constructBinaryTree(1, 2, 3);
        density = TreeAlgorithms.getDensity(tree);
        System.out.println("Density: " + density);
        assertEquals(1.5, density);

        tree = Utilities.constructBinaryTree(1, 2, 3, 7, 6, 5, 4);
        density = TreeAlgorithms.getDensity(tree);
        System.out.println("Density: " + density);
        assertEquals(2, Math.round(density));
    }

    @Test
    void isCompleteBinaryTree() {
        BinaryTreeNode tree;
        boolean isCompleteTree;

        tree = Utilities.constructBinaryTree(1, 2, 3, 7, 6, 5, 4);
        isCompleteTree = TreeAlgorithms.isCompleteBinaryTree(tree);
        System.out.println("Is Complete: " + isCompleteTree);
        assertEquals(true, isCompleteTree);

        tree = Utilities.constructBinaryTree(1, 2, 3, 7, null, 5, 4);
        isCompleteTree = TreeAlgorithms.isCompleteBinaryTree(tree);
        System.out.println("Is Complete: " + isCompleteTree);
        assertEquals(false, isCompleteTree);

        tree = Utilities.constructBinaryTree(1, 2, 3, 7, 6, 5, null);
        isCompleteTree = TreeAlgorithms.isCompleteBinaryTree(tree);
        System.out.println("Is Complete: " + isCompleteTree);
        assertEquals(true, isCompleteTree);

        tree = Utilities.constructBinaryTree(1, 2, 3, 7);
        isCompleteTree = TreeAlgorithms.isCompleteBinaryTree(tree);
        System.out.println("Is Complete: " + isCompleteTree);
        assertEquals(true, isCompleteTree);

        tree = Utilities.constructBinaryTree(1, 2, 3, null, null, 7);
        isCompleteTree = TreeAlgorithms.isCompleteBinaryTree(tree);
        System.out.println("Is Complete: " + isCompleteTree);
        assertEquals(false, isCompleteTree);
    }

    @Test
    void getNodeLevel() {
        BinaryTreeNode tree;
        int level;

        tree = Utilities.constructBinaryTree(3, 2, 5, 1, 4);
        level = TreeAlgorithms.getNodeLevel(tree, 1);
        assertEquals(3, level);
        level = TreeAlgorithms.getNodeLevel(tree, 2);
        assertEquals(2, level);
        level = TreeAlgorithms.getNodeLevel(tree, 3);
        assertEquals(1, level);
        level = TreeAlgorithms.getNodeLevel(tree, 4);
        assertEquals(3, level);
        level = TreeAlgorithms.getNodeLevel(tree, 5);
        assertEquals(2, level);
    }

    @Test
    void isSumSameForCoveredAndUncoveredNodes() {
        BinaryTreeNode tree;
        boolean isSumSame;

        tree = Utilities.constructBinaryTree(3, 4, 2, 3, 6, null, 1, null, null, 5, 7, null, null, 5, null);
        isSumSame = TreeAlgorithms.isSumSameForCoveredAndUncoveredNodes(tree);
        assertEquals(true, isSumSame);

        tree = Utilities.constructBinaryTree(8, 3, 10, 1, 6, null, 14, null, null, 4, 7, null, null, 13, null);
        isSumSame = TreeAlgorithms.isSumSameForCoveredAndUncoveredNodes(tree);
        assertEquals(false, isSumSame);

        tree = Utilities.constructBinaryTree(9, 4, 17, 3, 6, null, 22, null, null, 5, 7, null, null, 20, null);
        isSumSame = TreeAlgorithms.isSumSameForCoveredAndUncoveredNodes(tree);
        assertEquals(false, isSumSame);
    }

    @Test
    void areMirrorsIterative() {
        BinaryTreeNode tree1;
        BinaryTreeNode tree2;
        boolean areMirrors;

        tree1 = Utilities.constructBinaryTree(1, 3, 2, null, null, 5, 4);
        tree2 = Utilities.constructBinaryTree(1, 2, 3, 4, 5);
        areMirrors = TreeAlgorithms.areMirrorsIterative(tree1, tree2);
        assertEquals(true, areMirrors);

        tree1 = Utilities.constructBinaryTree(1, 2, 3, 4, 5, 6, 7);
        tree2 = Utilities.constructBinaryTree(1, 3, 2, 5, 4, 7, 6);
        areMirrors = TreeAlgorithms.areMirrorsIterative(tree1, tree2);
        assertEquals(false, areMirrors);
    }

    @Test
    void findFirstUnmatchingLeaves() {
        BinaryTreeNode tree1;
        BinaryTreeNode tree2;
        Pair<Integer, Integer> firstUnmatchedLeaves;

        tree1 = Utilities.constructBinaryTree(5, 2, 7, 10, 11, null, null);
        tree2 = Utilities.constructBinaryTree(6, 10, 15);
        firstUnmatchedLeaves = TreeAlgorithms.findFirstUnmatchedLeaves(tree1, tree2);
        assertEquals(new Pair(11, 15), firstUnmatchedLeaves);
    }

    @Test
    void visitLevelOrderWithDirectionChangeAfterTwoLevels() {
        BinaryTreeNode tree;

        tree = Utilities.constructBinaryTree(1, 2, 3, 4, 5, 6, 7, 8, 9, 3, 1, 4, 2, 7, 2, null, null, 16, null, null, null, 17, 18,null, null, null,19);
        TreeAlgorithms.visitLevelOrderWithDirectionChangeAfterTwoLevels(tree);
    }

    @Test
    void maxDifferenceBetweenAnyNodeAndAncestor() {
        BinaryTreeNode tree;
        int maxDifference;

        tree = Utilities.constructBinaryTree(8, 3, 10, 1, 6, null, 14, null, null, 4, 7, null, null, 13, null);
        maxDifference = TreeAlgorithms.maxDifferenceBetweenAnyNodeAndAncestor(tree);
        assertEquals(7, maxDifference);
    }

    @Test
    void removeHalfNodes() {
        BinaryTreeNode tree;
        ArrayList<Integer> collector = new ArrayList<>();
        int[] expected;

        tree = Utilities.constructBinaryTree(2, 7, 5, null, 6, null, 9, null, null, 1, 11, null, null, 4, null);
        expected = Utilities.constructArray(1, 6, 11, 2, 4);

        TreeAlgorithms.visitInOrder(tree);
        TreeAlgorithms.removeHalfNodes(tree);

        System.out.println();
        TreeAlgorithms.visitInOrder(tree, new Consumer<BinaryTreeNode>() {
            @Override
            public void accept(BinaryTreeNode node) {
                collector.add(node.getData());
                System.out.print(node.getData() + " ");
            }
        });

        assertArrayEquals(expected, collector.stream().mapToInt( i -> i).toArray());
    }

    @Test
    void sumOfLongestRootToLeafPath() {
        BinaryTreeNode tree;
        int sum;

        tree = Utilities.constructBinaryTree(4, 2, 5, 7, 1, 2, 3, null, null, 6);
        sum = TreeAlgorithms.sumOfLongestRootToLeafPath(tree);
        assertEquals(13, sum);
    }

    @Test
    void areIdentical() {
        var tree1 = Utilities.constructBinaryTree(8, 3, 10, 1, 6, null, 14, null, null, 4, 7, null, null, 13, null);
        var tree2 = Utilities.constructBinaryTree(4, 2, 5, 7, 1, 2, 3, null, null, 6);
        boolean areIdentical = TreeAlgorithms.areIdentical(tree1, tree2);
        assertEquals(false, areIdentical);
    }

    @Test
    void reverseAlternateLevelsOfPerfectBinaryTree() {
        BTNode<Character> tree;
        char[] collector;
        AtomicInteger collectorIndex;
        char[] expected;

        tree = Utilities.constructBinaryTree('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o');
        expected = Utilities.constructArray('o', 'd', 'n', 'c', 'm', 'e', 'l', 'a', 'k', 'f', 'j', 'b', 'i', 'g', 'h');
        collector = new char[expected.length];
        collectorIndex = new AtomicInteger(0);
        TreeAlgorithms.visitInOrder(tree);
        TreeAlgorithms.reverseAlternateLevelsOfPerfectBinaryTree(tree);
        System.out.println();
        TreeAlgorithms.visitInOrder(tree, new Consumer<BTNode<Character>>() {
            @Override
            public void accept(BTNode<Character> node) {
                collector[collectorIndex.getAndIncrement()] = node.getData();
                System.out.print(node.getData() + " ");
            }
        });

        assertArrayEquals(expected, collector);
    }

    @Test
    void reverseAlternateLevelsOfPerfectBinaryTreeWithSingleTraversal() {
        BTNode<Character> tree;
        char[] collector;
        AtomicInteger collectorIndex;
        char[] expected;

        tree = Utilities.constructBinaryTree('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o');
        expected = Utilities.constructArray('o', 'd', 'n', 'c', 'm', 'e', 'l', 'a', 'k', 'f', 'j', 'b', 'i', 'g', 'h');
        collector = new char[expected.length];
        collectorIndex = new AtomicInteger(0);
        TreeAlgorithms.visitInOrder(tree);
        TreeAlgorithms.reverseAlternateLevelsOfPerfectBinaryTreeWithSingleTraversal(tree);
        System.out.println();
        TreeAlgorithms.visitInOrder(tree, new Consumer<BTNode<Character>>() {
            @Override
            public void accept(BTNode<Character> node) {
                collector[collectorIndex.getAndIncrement()] = node.getData();
                System.out.print(node.getData() + " ");
            }
        });

        assertArrayEquals(expected, collector);
    }

    @Test
    void getLargestSubtreeSum() {
        BinaryTreeNode tree;
        int sum;

        tree = Utilities.constructBinaryTree(1, -2, 3, 4, 5, -6, 2);
        sum = TreeAlgorithms.getLargestSubtreeSum(tree);
        assertEquals(7, sum);

        tree = Utilities.constructBinaryTree(1, 2, 3, 4, 5, 6, 7);
        sum = TreeAlgorithms.getLargestSubtreeSum(tree);
        assertEquals(28, sum);
    }

    @Test
    void getBottomView() {
        BinaryTreeNode tree;
        int[] bottomView;
        int[] expectedView;

        tree = Utilities.constructBinaryTree(20,8,22, 5, 3, null, 25, null, null, 10, 14);
        expectedView = Utilities.constructArray(5, 10, 3, 14, 25);
        bottomView = TreeAlgorithms.getBottomView(tree);
        Utilities.printArray(bottomView);
        assertArrayEquals(expectedView, bottomView);

        tree = Utilities.constructBinaryTree(20,8,22, 5, 3, 4, 25, null, null, 10, 14);
        expectedView = Utilities.constructArray(5, 10, 4, 14, 25);
        bottomView = TreeAlgorithms.getBottomView(tree);
        Utilities.printArray(bottomView);
        assertArrayEquals(expectedView, bottomView);
    }

    @Test
    void findMaxLevelSum() {
        BinaryTreeNode tree;
        int sum;

        tree = Utilities.constructBinaryTree(4, 2, -5, -1, 3, -2, 6);
        sum = TreeAlgorithms.getMaxLevelSum(tree);
        assertEquals(6, sum);

        tree = Utilities.constructBinaryTree(1, 2, 3, 4, 5, null, 8, null, null, null, null, null, null, 6, 7);
        sum = TreeAlgorithms.getMaxLevelSum(tree);
        assertEquals(17, sum);
    }

    @Test
    void lengthOfRootToLeafPathWithMostDistinctNodes() {
        BinaryTreeNode tree;
        int length;

        tree = Utilities.constructBinaryTree(1, 2, 3, 4, 5, 6, 3, null, null, null, null, null, 8, null, 9);
        length = TreeAlgorithms.lengthOfRootToLeafPathWithMostDistinctNodes(tree);
        assertEquals(4, length);
    }

    @Test
    void getAllKSumPaths() {
        BinaryTreeNode tree;
        List<List<Integer>> kSumPaths;

        tree = Utilities.constructBinaryTree(1, 3, -1, 2, 1, 4, 5, null, null, 1, null, 1, 2, null, 6);
        kSumPaths = TreeAlgorithms.getAllKSumPaths(tree, 5);
        assertEquals(8, kSumPaths.size());
        for (var path : kSumPaths) {
            int sum = path.stream().mapToInt(i -> i).sum();
            assertEquals(5, sum);
        }
    }

    @Test
    void getRightView() {
        BinaryTreeNode tree;
        List<Integer> rightView;

        tree = Utilities.constructBinaryTree(10, 2, 3, 7, 8, 12, 15, null, null, null, null, null, null, 14);
        rightView = TreeAlgorithms.getRightView(tree);
        assertEquals(4, rightView.size());
        assertEquals(true, rightView.contains(10));
        assertEquals(true, rightView.contains(3));
        assertEquals(true, rightView.contains(15));
        assertEquals(true, rightView.contains(14));
    }

    @Test
    void getKthAncestor() {
        BinaryTreeNode tree;
        Integer ancestor;

        tree = Utilities.constructBinaryTree(1, 2, 3, 4, 5);
        ancestor = TreeAlgorithms.getKthAncestor(tree, 4, 2);
        assertEquals(1, ancestor.intValue());

        tree = Utilities.constructBinaryTree(1, 2, 3, 4, 5);
        ancestor = TreeAlgorithms.getKthAncestor(tree, 5, 2);
        assertEquals(1, ancestor.intValue());

        tree = Utilities.constructBinaryTree(1, 2, 3, 4, 5);
        ancestor = TreeAlgorithms.getKthAncestor(tree, 5, 3);
        assertEquals(null, ancestor);
    }

    @Test
    void getKthAncestorApproach2() {
        BinaryTreeNode tree;
        Integer ancestor;

        tree = Utilities.constructBinaryTree(1, 2, 3, 4, 5);
        ancestor = TreeAlgorithms.getKthAncestorApproach2(tree, 4, 2);
        assertEquals(1, ancestor.intValue());

        tree = Utilities.constructBinaryTree(1, 2, 3, 4, 5);
        ancestor = TreeAlgorithms.getKthAncestorApproach2(tree, 5, 2);
        assertEquals(1, ancestor.intValue());

        tree = Utilities.constructBinaryTree(1, 2, 3, 4, 5);
        ancestor = TreeAlgorithms.getKthAncestorApproach2(tree, 5, 3);
        assertEquals(null, ancestor);
    }

    @Test
    void constructPerfectBinaryTreeFromLinkedListOfLevelOrder() {
        LinkedListNode head;
        BinaryTreeNode tree;
        int[] expected;
        ArrayList<Integer> collector;

        head = Utilities.createLinkedList(10, 12, 15, 25, 30, 36);
        expected = Utilities.constructArray(25, 12, 30, 10, 36, 15);
        tree = TreeAlgorithms.constructPerfectBinaryTreeFromLinkedListOfLevelOrder(head);
        collector = new ArrayList<>();
        TreeAlgorithms.visitInOrder(tree, node -> collector.add(node.getData()));
        assertArrayEquals(expected, collector.stream().mapToInt(i->i).toArray());
    }

    @Test
    void flipTree() {
        BinaryTreeNode tree;
        int[] levelOrder;
        int[] expectedLevelOrder;

        tree = Utilities.constructBinaryTree(1, 2, 3, null, null, 4, 5);
        expectedLevelOrder = Utilities.constructArray(2, 3, 1, 4, 5);
        TreeAlgorithms.visitLevelOrder(tree);
        tree = TreeAlgorithms.flipTree(tree);
        System.out.println();
        TreeAlgorithms.visitLevelOrder(tree);
        levelOrder = TreeAlgorithms.getLevelOrder(tree);
        assertArrayEquals(expectedLevelOrder, levelOrder);
    }

    @Test
    void getDiagonalTraversal() {
        BinaryTreeNode tree;
        Map<Integer, List<Integer>> diagonalTraversal;

        tree = Utilities.constructBinaryTree(8, 3, 10, 1, 6, null, 14, null, null, 4, 7, null, null, 13);
        diagonalTraversal = TreeAlgorithms.getDiagonalTraversal(tree);
        assertEquals(3, diagonalTraversal.size());
        assertArrayEquals(new int[] { 8, 10, 14}, diagonalTraversal.get(0).stream().mapToInt(i->i).toArray());
        assertArrayEquals(new int[] { 3, 6, 7, 13}, diagonalTraversal.get(1).stream().mapToInt(i->i).toArray());
        assertArrayEquals(new int[] { 1, 4}, diagonalTraversal.get(2).stream().mapToInt(i->i).toArray());
    }

    @Test
    void getDiagonalTraversalIterative() {
        BinaryTreeNode tree;
        Map<Integer, List<Integer>> diagonalTraversal;

        tree = Utilities.constructBinaryTree(8, 3, 10, 1, 6, null, 14, null, null, 4, 7, null, null, 13);
        diagonalTraversal = TreeAlgorithms.getDiagonalTraversalIterative(tree);
        assertEquals(3, diagonalTraversal.size());
        assertArrayEquals(new int[] { 8, 10, 14}, diagonalTraversal.get(0).stream().mapToInt(i->i).toArray());
        assertArrayEquals(new int[] { 3, 6, 7, 13}, diagonalTraversal.get(1).stream().mapToInt(i->i).toArray());
        assertArrayEquals(new int[] { 1, 4}, diagonalTraversal.get(2).stream().mapToInt(i->i).toArray());
    }

    @Test
    void getSmallestValuesAtEachLevel() {
        BinaryTreeNode tree;
        Map<Integer, Integer> smallestValuesByLevel;

        tree = Utilities.constructBinaryTree(7, 6, 5, 4, 3, 2, 1);
        smallestValuesByLevel = TreeAlgorithms.getSmallestValuesAtEachLevel(tree);
        assertEquals(3, smallestValuesByLevel.size());
        assertEquals(7, (int) smallestValuesByLevel.get(0));
        assertEquals(5, (int) smallestValuesByLevel.get(1));
        assertEquals(1, (int) smallestValuesByLevel.get(2));

        tree = Utilities.constructBinaryTree(7, 16, 1, 4, 13);
        smallestValuesByLevel = TreeAlgorithms.getSmallestValuesAtEachLevel(tree);
        assertEquals(3, smallestValuesByLevel.size());
        assertEquals(7, (int) smallestValuesByLevel.get(0));
        assertEquals(1, (int) smallestValuesByLevel.get(1));
        assertEquals(4, (int) smallestValuesByLevel.get(2));
    }

    @Test
    void isBalanced() {
        BinaryTreeNode tree;
        boolean isBalanced;

        tree = Utilities.constructBinaryTree(1, 2, 3, 4, 5, 6, 7);
        isBalanced = TreeAlgorithms.isBalanced(tree);
        assertTrue(isBalanced);

        tree = Utilities.constructBinaryTree(1, 2, 3, 4,null, null, null, null, 5);
        isBalanced = TreeAlgorithms.isBalanced(tree);
        assertFalse(isBalanced);

        tree = Utilities.constructBinaryTree(1, 2, 3, 4);
        isBalanced = TreeAlgorithms.isBalanced(tree);
        assertTrue(isBalanced);
    }

    @Test
    void isBalancedA2() {
        BinaryTreeNode tree;
        boolean isBalanced;

        tree = Utilities.constructBinaryTree(1, 2, 3, 4, 5, 6, 7);
        isBalanced = TreeAlgorithms.isBalancedA2(tree);
        assertTrue(isBalanced);

        tree = Utilities.constructBinaryTree(1, 2, 3, 4,null, null, null, null, 5);
        isBalanced = TreeAlgorithms.isBalancedA2(tree);
        assertFalse(isBalanced);

        tree = Utilities.constructBinaryTree(1, 2, 3, 4);
        isBalanced = TreeAlgorithms.isBalancedA2(tree);
        assertTrue(isBalanced);
    }

    @Test
    void getDeepestNode() {
        BinaryTreeNode tree;
        int deepestNode;

        tree = Utilities.constructBinaryTree(1, 2, 3, 4, 5, 6, 7, null, null, null, null, null, null, null, 8);
        deepestNode = TreeAlgorithms.getDeepestNode(tree);
        assertEquals(8, deepestNode);

        tree = Utilities.constructBinaryTree(1, 2, 3, null, null, 6);
        deepestNode = TreeAlgorithms.getDeepestNode(tree);
        assertEquals(6, deepestNode);
    }

    @Test
    void isPerfect() {
        BinaryTreeNode tree;
        boolean isPerfect;

        tree = Utilities.constructBinaryTree(1, 2, 3, 4, 5, 6, 7, null, null, null, null, null, null, null, 8);
        isPerfect = TreeAlgorithms.isPerfect(tree);
        assertFalse(isPerfect);

        tree = Utilities.constructBinaryTree(1, 2, 3, null, null, 6);
        isPerfect = TreeAlgorithms.isPerfect(tree);
        assertFalse(isPerfect);

        tree = Utilities.constructBinaryTree(10, 20, 30, 40, 50, 60, 70);
        isPerfect = TreeAlgorithms.isPerfect(tree);
        assertTrue(isPerfect);

        tree = Utilities.constructBinaryTree(18, 15, 30);
        isPerfect = TreeAlgorithms.isPerfect(tree);
        assertTrue(isPerfect);

        tree = Utilities.constructBinaryTree(1, 2, 3, null, 4, 5, 6);
        isPerfect = TreeAlgorithms.isPerfect(tree);
        assertFalse(isPerfect);
    }

    @Test
    void getMaxWidth() {
        BinaryTreeNode tree;
        int maxWidth;

        tree = Utilities.constructBinaryTree(1, 2, 3, 4, 5, null, 8, null, null, null, null, null, null, 6, 7);
        maxWidth = TreeAlgorithms.getMaxWidth(tree);
        assertEquals(3, maxWidth);
    }

    @Test
    void getMaxWidthIterative() {
        BinaryTreeNode tree;
        int maxWidth;

        tree = Utilities.constructBinaryTree(1, 2, 3, 4, 5, null, 8, null, null, null, null, null, null, 6, 7);
        maxWidth = TreeAlgorithms.getMaxWidthIterative(tree);
        assertEquals(3, maxWidth);
    }

    @Test
    void visitZigZag() {
        BinaryTreeNode tree;
        List<Integer> zigZagTraversal;
        int[] expectedZigZagTraversal;

        tree = Utilities.constructBinaryTree(1, 2, 3, 7, 6, 5, 4);
        expectedZigZagTraversal = Utilities.constructArray(1, 3, 2, 7, 6, 5, 4);
        zigZagTraversal = new ArrayList<>();
        TreeAlgorithms.visitZigZag(tree, n -> {
            zigZagTraversal.add(n.getData());
            System.out.print(n.getData() + " ");
        });
        assertArrayEquals(expectedZigZagTraversal, zigZagTraversal.stream().mapToInt(i->i).toArray());
    }
}