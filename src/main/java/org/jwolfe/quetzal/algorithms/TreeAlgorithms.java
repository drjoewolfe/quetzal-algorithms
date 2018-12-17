package org.jwolfe.quetzal.algorithms;


import org.jwolfe.quetzal.library.list.LinkedListNode;
import org.jwolfe.quetzal.library.tree.BTNode;
import org.jwolfe.quetzal.library.tree.BinaryTreeNode;
import org.jwolfe.quetzal.library.general.Pair;
import org.jwolfe.quetzal.library.utilities.Utilities;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

public class TreeAlgorithms {

    public static void visitPreOrder(BinaryTreeNode node) {
        Consumer<BinaryTreeNode> visit = new Consumer<BinaryTreeNode>() {
            @Override
            public void accept(BinaryTreeNode node) {
                System.out.print(node.getData() + " ");
            }
        };

        visitPreOrder(node, visit);
    }

    public static void visitPreOrder(BinaryTreeNode node, Consumer<BinaryTreeNode> visit) {
        if (node == null)
            return;

        if (visit != null) {
            visit.accept(node);
        }
        visitPreOrder(node.getLeft(), visit);
        visitPreOrder(node.getRight(), visit);
    }

    public static void visitPreOrderIterative(BinaryTreeNode node) {
        if (node == null)
            return;

        Stack<BinaryTreeNode> stack = new Stack<>();
        stack.push(node);

        while (!stack.isEmpty()) {
            var temp = stack.pop();
            System.out.print(temp.getData() + " ");

            if (temp.getRight() != null)
                stack.push(temp.getRight());
            if (temp.getLeft() != null)
                stack.push(temp.getLeft());
        }
    }

    public static void visitPreOrderMorris(BinaryTreeNode node){
        Consumer<BinaryTreeNode> visit = new Consumer<BinaryTreeNode>() {
            @Override
            public void accept(BinaryTreeNode node) {
                System.out.print(node.getData() + " ");
            }
        };

        visitPreOrderMorris(node, visit);
    }

    public static void visitPreOrderMorris(BinaryTreeNode node, Consumer<BinaryTreeNode> visit) {
        while(node != null) {
            if(node.getLeft() == null) {
                if(visit != null) {
                    visit.accept(node);
                }

                node = node.getRight();
            }
            else {
                BinaryTreeNode inOrderPredecessor = node.getLeft();
                while (inOrderPredecessor.getRight() != null && inOrderPredecessor.getRight() != node) {
                    inOrderPredecessor = inOrderPredecessor.getRight();
                }

                if(inOrderPredecessor.getRight() == node) {
                    inOrderPredecessor.setRight(null);
                    node = node.getRight();
                }
                else {
                    if(visit != null) {
                        visit.accept(node);
                    }

                    inOrderPredecessor.setRight(node);
                    node = node.getLeft();
                }
            }
        }
    }

    public static void visitInOrder(BinaryTreeNode node) {
        Consumer<BinaryTreeNode> visit = new Consumer<BinaryTreeNode>() {
            @Override
            public void accept(BinaryTreeNode node) {
                System.out.print(node.getData() + " ");
            }
        };

        visitInOrder(node, visit);
    }

    public static void visitInOrder(BinaryTreeNode node, Consumer<BinaryTreeNode> visit) {
        if (node == null)
            return;

        visitInOrder(node.getLeft(), visit);
        if (visit != null) {
            visit.accept(node);
        }
        visitInOrder(node.getRight(), visit);
    }

    public static void visitInOrder(BTNode<Character> node) {
        Consumer<BTNode<Character>> visit = new Consumer<BTNode<Character>>() {
            @Override
            public void accept(BTNode<Character> node) {
                System.out.print(node.getData() + " ");
            }
        };

        visitInOrder(node, visit);
    }

    public static void visitInOrder(BTNode<Character> node, Consumer<BTNode<Character>> visit) {
        if (node == null)
            return;

        visitInOrder(node.getLeft(), visit);
        if (visit != null) {
            visit.accept(node);
        }
        visitInOrder(node.getRight(), visit);
    }

    public static void visitInOrderIterative(BinaryTreeNode node) {
        Stack<BinaryTreeNode> stack = new Stack<>();
        BinaryTreeNode current = node;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.getLeft();
            }

            current = stack.pop();
            System.out.print(current.getData() + " ");

            current = current.getRight();
        }
    }

    public static void visitInOrderMorris(BinaryTreeNode node){
        Consumer<BinaryTreeNode> visit = new Consumer<BinaryTreeNode>() {
            @Override
            public void accept(BinaryTreeNode node) {
                System.out.print(node.getData() + " ");
            }
        };

        visitInOrderMorris(node, visit);
    }

    public static void visitInOrderMorris(BinaryTreeNode node, Consumer<BinaryTreeNode> visit) {
        while(node != null) {
            if(node.getLeft() == null) {
                if(visit != null) {
                    visit.accept(node);
                }

                node = node.getRight();
            }
            else {
                BinaryTreeNode inOrderSuccessor = node.getLeft();
                while(inOrderSuccessor.getRight() != null && inOrderSuccessor.getRight() != node) {
                    inOrderSuccessor = inOrderSuccessor.getRight();
                }

                if(inOrderSuccessor.getRight() == node) {
                    if(visit != null) {
                        visit.accept(node);
                    }

                    inOrderSuccessor.setRight(null);
                    node = node.getRight();
                }
                else {
                    inOrderSuccessor.setRight(node);
                    node = node.getLeft();
                }
            }
        }
    }

    public static void visitPostOrder(BinaryTreeNode node) {
        Consumer<BinaryTreeNode> visit = new Consumer<BinaryTreeNode>() {
            @Override
            public void accept(BinaryTreeNode node) {
                System.out.print(node.getData() + " ");
            }
        };

        visitPostOrder(node, visit);
    }

    public static void visitPostOrder(BinaryTreeNode node, Consumer<BinaryTreeNode> visit) {
        if (node == null)
            return;

        visitPostOrder(node.getLeft(), visit);
        visitPostOrder(node.getRight(), visit);
        if (visit != null) {
            visit.accept(node);
        }
    }

    public static void visitPostOrderIterative(BinaryTreeNode node) {
        if (node == null)
            return;

        Stack<BinaryTreeNode> stack1 = new Stack<>();
        Stack<BinaryTreeNode> stack2 = new Stack<>();

        stack1.push(node);
        while (!stack1.isEmpty()) {
            var temp = stack1.pop();

            stack2.push(temp);

            if (temp.getLeft() != null)
                stack1.push(temp.getLeft());
            if (temp.getRight() != null)
                stack1.push(temp.getRight());
        }

        while (!stack2.isEmpty()) {
            System.out.print(stack2.pop().getData() + " ");
        }
    }

    public static void visitLevelOrder(BinaryTreeNode node) {
        Consumer<BinaryTreeNode> visit = new Consumer<BinaryTreeNode>() {
            @Override
            public void accept(BinaryTreeNode node) {
                System.out.print(node.getData() + " ");
            }
        };

        visitLevelOrder(node, visit);
    }

    public static void visitLevelOrder(BinaryTreeNode node, Consumer<BinaryTreeNode> visit) {
        if (node == null)
            return;

        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            var temp = queue.poll();
            if(visit != null) {
                visit.accept(temp);
            }

            if (temp.getLeft() != null)
                queue.add(temp.getLeft());

            if (temp.getRight() != null)
                queue.add(temp.getRight());
        }
    }

    public static void visitLevelOrderRecursive(BinaryTreeNode node) {
        int height = getTreeHeight(node);
        for (int i = 1; i <= height; i++) {
            visitLevel(node, i);
        }
    }

    private static void visitLevel(BinaryTreeNode node, int level) {
        if (node == null)
            return;

        if (level <= 0)
            return;

        if (level == 1)
            System.out.print(node.getData() + " ");
        else {
            visitLevel(node.getLeft(), level - 1);
            visitLevel(node.getRight(), level - 1);
        }
    }

    private static int getTreeHeight(BinaryTreeNode node) {
        if (node == null)
            return 0;

        return Math.max(getTreeHeight(node.getLeft()),
                getTreeHeight(node.getRight()))
                + 1;
    }

    public static void visitLevelOrderReverse(BinaryTreeNode node) {
        if (node == null)
            return;

        Stack<BinaryTreeNode> stack = new Stack<>();
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            var temp = queue.poll();
            stack.push(temp);

            if (temp.getRight() != null)
                queue.add(temp.getRight());
            if (temp.getLeft() != null)
                queue.add(temp.getLeft());
        }

        while (!stack.isEmpty()) {
            System.out.print(stack.pop().getData() + " ");
        }
    }

    public static void visitLevelOrderReverseRecursive(BinaryTreeNode node) {
        int height = getTreeHeight(node);
        for (int i = height; i >= 1; i--) {
            visitLevel(node, i);
        }
    }

    public static int getDiameter(BinaryTreeNode root) {
        // Note: This implementation is O(n^2)
        // This can be done in O(n), if height is passed & set as parameter instead of being calculated in parallel.

        if (root == null) {
            return 0;
        }

        return Utilities.max(getDiameter(root.getLeft()),
                getDiameter(root.getRight()),
                getTreeHeight(root.getLeft()) + getTreeHeight(root.getRight()) + 1);
    }

    public static void topView(BinaryTreeNode root) {
        if (root == null) {
            return;
        }

        class NodeWithHD {
            int hd;
            BinaryTreeNode node;

            public NodeWithHD(int hd, BinaryTreeNode node) {
                this.hd = hd;
                this.node = node;
            }
        }

        Queue<NodeWithHD> queue = new LinkedList<>();
        Map<Integer, BinaryTreeNode> map = new TreeMap<>();

        queue.add(new NodeWithHD(0, root));
        while (!queue.isEmpty()) {
            var node = queue.poll();
            if (!map.containsKey(node.hd)) {
                map.put(node.hd, node.node);
            }

            if (node.node.getLeft() != null) {
                queue.add(new NodeWithHD(node.hd - 1, node.node.getLeft()));
            }

            if (node.node.getRight() != null) {
                queue.add(new NodeWithHD(node.hd + 1, node.node.getRight()));
            }
        }

        for (var entry : map.entrySet()) {
            System.out.print(entry.getValue().getData() + " ");
        }

        System.out.println();
    }

    public static boolean isSymmetricIterative(BinaryTreeNode node) {
        if (node == null) {
            return true;
        }

        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(node.getLeft());
        queue.add(node.getRight());

        while (!queue.isEmpty()) {
            var node1 = queue.poll();
            var node2 = queue.poll();

            if (node1 == null && node2 == null) {
                return true;
            }

            if (node1 == null || node2 == null) {
                return false;
            }

            if (node1.getData() != node2.getData()) {
                return false;
            }

            queue.add(node1.getLeft());
            queue.add(node2.getRight());
            queue.add(node1.getRight());
            queue.add(node2.getLeft());
        }

        return true;
    }

    public static int[] getCorners(BinaryTreeNode root) {
        if (root == null) {
            return null;
        }

        ArrayList<Integer> corners = new ArrayList<>();
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.offer(root);
        queue.offer(null);

        boolean isFirst = true;
        boolean isSingle = false;
        BinaryTreeNode lastNode = null;

        while (!queue.isEmpty()) {
            var node = queue.poll();

            if (isFirst) {
                corners.add(node.getData());

                if (node.getLeft() != null) {
                    queue.offer(node.getLeft());
                }
                if (node.getRight() != null) {
                    queue.offer(node.getRight());
                }

                isFirst = false;
                isSingle = true;
            } else if (node == null) {
                if (queue.size() > 0) {
                    queue.offer(null);
                }

                if (!isSingle) {
                    corners.add(lastNode.getData());
                }

                isFirst = true;
            } else {
                lastNode = node;
                isSingle = false;

                if (node.getLeft() != null) {
                    queue.offer(node.getLeft());
                }
                if (node.getRight() != null) {
                    queue.offer(node.getRight());
                }
            }
        }

        return corners.stream().mapToInt(i -> i).toArray();
    }

    public static int[] getPostOrderFromInOrderAndPreOrder(int[] inOrder, int[] preOrder) {
        if (inOrder == null
                || preOrder == null) {
            return null;
        }

        int n = inOrder.length;
        if (n != preOrder.length) {
            return null;
        }

        // Approach 1
        // preIndex = 0;
        // List<Integer> postOrder = new ArrayList<>();
        // getPostOrderFromInOrderAndPreOrder(inOrder, preOrder, 0, n - 1, postOrder);
        // return postOrder.stream().mapToInt(i -> i).toArray();

        // Approach 2
        int[] postOrder = new int[n];
        AtomicInteger postIndex = new AtomicInteger(n - 1);
        getPostOrderFromInOrderAndPreOrder(inOrder, preOrder, postOrder, 0, n - 1, 0, postIndex);
        return postOrder;
    }

    private static int preIndex;

    private static void getPostOrderFromInOrderAndPreOrder(int[] inOrder, int[] preOrder, int inStart, int inEnd, List<Integer> postOrder) {
        if (inStart > inEnd) {
            return;
        }

        int inIndex = search(inOrder, inStart, inEnd, preOrder[preIndex]);
        preIndex++;

        getPostOrderFromInOrderAndPreOrder(inOrder, preOrder, inStart, inIndex - 1, postOrder);
        getPostOrderFromInOrderAndPreOrder(inOrder, preOrder, inIndex + 1, inEnd, postOrder);

        postOrder.add(inOrder[inIndex]);
    }


    private static void getPostOrderFromInOrderAndPreOrder(int[] inOrder, int[] preOrder, int[] postOrder, int inStart, int inEnd, int preIndex, AtomicInteger postIndex) {
        if (inStart > inEnd) {
            return;
        }

        postOrder[postIndex.getAndDecrement()] = preOrder[preIndex];

        int inIndex = search(inOrder, inStart, inEnd, preOrder[preIndex]);

        getPostOrderFromInOrderAndPreOrder(inOrder, preOrder, postOrder, inIndex + 1, inEnd, preIndex + (inIndex - inStart) + 1, postIndex);
        getPostOrderFromInOrderAndPreOrder(inOrder, preOrder, postOrder, inStart, inIndex - 1, preIndex + 1, postIndex);
    }

    private static int search(int[] inorder, int inStart, int inEnd, int data) {
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == data) {
                return i;
            }
        }

        return -1;
    }

    public static void visitBoundary(BinaryTreeNode root) {
        if (root == null) {
            return;
        }

        System.out.print(root.getData() + " ");

        visitLeftBoundary(root.getLeft());
        visitLeaves(root.getLeft());
        visitLeaves(root.getRight());
        visitRightBoundary(root.getRight());
    }

    private static void visitLeftBoundary(BinaryTreeNode node) {
        if (node == null) {
            return;
        }

        if (node.getLeft() != null) {
            System.out.print(node.getData() + " ");
            visitLeftBoundary(node.getLeft());
        } else if (node.getRight() != null) {
            System.out.print(node.getData() + " ");
            visitLeftBoundary(node.getRight());
        }
    }

    private static void visitLeaves(BinaryTreeNode node) {
        if (node == null) {
            return;
        }

        visitLeaves(node.getLeft());

        if (node.getLeft() == null
                && node.getRight() == null) {
            System.out.print(node.getData() + " ");
        }

        visitLeaves(node.getRight());
    }

    private static void visitRightBoundary(BinaryTreeNode node) {
        if (node == null) {
            return;
        }

        if (node.getRight() != null) {
            visitRightBoundary(node.getRight());
            System.out.print(node.getData() + " ");
        } else if (node.getLeft() != null) {
            visitRightBoundary(node.getLeft());
            System.out.print(node.getData() + " ");
        }
    }

    // Pre-order code supports 'n' & 'l' (for internal node, leaf)
    public static int getDepthFromPreOrderCode(String preOrderCode) {
        AtomicInteger preIndex = new AtomicInteger(0);
        return getDepthFromPreOrderCode(preOrderCode, preIndex);
    }

    private static int getDepthFromPreOrderCode(String preOrderCode, AtomicInteger preIndex) {
        if (preIndex.intValue() >= preOrderCode.length()) {
            return 0;
        }

        char c = preOrderCode.charAt(preIndex.intValue());
        if (c == 'l') {
            return 0;
        }

        preIndex.getAndIncrement();
        int leftHeight = getDepthFromPreOrderCode(preOrderCode, preIndex);

        preIndex.getAndIncrement();
        int rightHeight = getDepthFromPreOrderCode(preOrderCode, preIndex);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static void visitLevelOrderInSpiralForm(BinaryTreeNode root) {
        if (root == null) {
            return;
        }

        Stack<BinaryTreeNode> stack1 = new Stack<>();
        Stack<BinaryTreeNode> stack2 = new Stack<>();

        stack1.push(root);

        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                BinaryTreeNode node = stack1.pop();
                System.out.print(node.getData() + " ");

                if (node.getRight() != null) {
                    stack2.push(node.getRight());
                }

                if (node.getLeft() != null) {
                    stack2.push(node.getLeft());
                }
            }

            System.out.println();
            while (!stack2.isEmpty()) {
                BinaryTreeNode node = stack2.pop();
                System.out.print(node.getData() + " ");

                if (node.getLeft() != null) {
                    stack1.push(node.getLeft());
                }

                if (node.getRight() != null) {
                    stack1.push(node.getRight());
                }
            }

            System.out.println();
        }
    }

    public static void visitLevelOrderInSpiralFormRecursive(BinaryTreeNode root) {
        if (root == null) {
            return;
        }

        int height = getTreeHeight(root) + 1;
        boolean ltr = false;
        for (int i = 1; i < height + 1; i++) {
            visitLevelOrderInSpiralFormRecursive(root, i, ltr);
            ltr = !ltr;

            System.out.println();
        }
    }

    private static void visitLevelOrderInSpiralFormRecursive(BinaryTreeNode node, int level, boolean ltr) {
        if (node == null
                || level < 1) {
            return;
        }

        if (level == 1) {
            System.out.print(node.getData());
        } else {
            if (ltr) {
                visitLevelOrderInSpiralFormRecursive(node.getLeft(), level - 1, ltr);
                visitLevelOrderInSpiralFormRecursive(node.getRight(), level - 1, ltr);
            } else {
                visitLevelOrderInSpiralFormRecursive(node.getRight(), level - 1, ltr);
                visitLevelOrderInSpiralFormRecursive(node.getLeft(), level - 1, ltr);
            }
        }
    }

    public static void visitNodesBetweenTwoLevels(BinaryTreeNode root, int startLevel, int endLevel) {
        if (root == null) {
            return;
        }

        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 1;

        while (!queue.isEmpty()) {
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                BinaryTreeNode node = queue.poll();

                if (startLevel <= level
                        && level <= endLevel) {
                    System.out.print(node.getData() + " ");
                }

                if (node.getLeft() != null) {
                    queue.offer(node.getLeft());
                }

                if (node.getRight() != null) {
                    queue.offer(node.getRight());
                }
            }

            if (startLevel <= level
                    && level <= endLevel) {
                System.out.println();
            }

            level++;
            if (level > endLevel) {
                break;
            }
        }
    }

    public static boolean isSubTree(BinaryTreeNode node1, BinaryTreeNode node2) {
        if (node2 == null) {
            return true;
        }

        if (node1 == null) {
            return false;
        }

        List<Integer> inOrderNode1 = getInorderList(node1);
        List<Integer> inOrderNode2 = getInorderList(node2);

        List<Integer> preOrderNode1 = getPreOrderList(node1);
        List<Integer> preOrderNode2 = getPreOrderList(node2);

        return isSubList(inOrderNode1, inOrderNode2)
                && isSubList(preOrderNode1, preOrderNode2);
    }

    private static List<Integer> getInorderList(BinaryTreeNode node) {
        ArrayList<Integer> inOrderList = new ArrayList<>();
        Consumer<BinaryTreeNode> inOrderVisitor = new Consumer<BinaryTreeNode>() {
            @Override
            public void accept(BinaryTreeNode node) {
                inOrderList.add(node.getData());
            }
        };

        visitInOrder(node, inOrderVisitor);
        return inOrderList;
    }

    private static List<Integer> getPreOrderList(BinaryTreeNode node) {
        ArrayList<Integer> preOrderList = new ArrayList<>();
        Consumer<BinaryTreeNode> preOrderVisitor = new Consumer<BinaryTreeNode>() {
            @Override
            public void accept(BinaryTreeNode node) {
                preOrderList.add(node.getData());
            }
        };

        visitPreOrder(node, preOrderVisitor);
        return preOrderList;
    }

    private static boolean isSubList(List<Integer> list1, List<Integer> list2) {
        int i = 0;
        int j = 0;

        while (i < list1.size()
                && j < list2.size()) {
            if (list1.get(i).equals(list2.get(j))) {
                i++;
                j++;

                if (j == list2.size()) {
                    return true;
                }

            } else {
                i++;
                j = 0;
            }
        }

        return false;
    }

    public static boolean isSubTreeRecursive(BinaryTreeNode node1, BinaryTreeNode node2) {
        if (node2 == null) {
            return true;
        }

        if (node1 == null) {
            return false;
        }

        if (areIdentical(node1, node2)) {
            return true;
        }

        return isSubTreeRecursive(node1.getLeft(), node2)
                || isSubTreeRecursive(node1.getRight(), node2);
    }

    public static boolean areIdentical(BinaryTreeNode node1, BinaryTreeNode node2) {
        if (node1 == null
                && node2 == null) {
            return true;
        }

        if (node1 == null || node2 == null) {
            return false;
        }

        if (node1.getData() != node2.getData()) {
            return false;
        }

        return areIdentical(node1.getLeft(), node2.getLeft())
                && areIdentical(node1.getRight(), node2.getRight());
    }

    public static boolean areLeafTraversalsSame(BinaryTreeNode node1, BinaryTreeNode node2) {
        if (node1 == null
                || node2 == null) {
            return false;
        }

        Stack<BinaryTreeNode> stack1 = new Stack<>();
        Stack<BinaryTreeNode> stack2 = new Stack<>();

        stack1.push(node1);
        stack2.push(node2);

        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            if (stack1.isEmpty() || stack2.isEmpty()) {
                return false;
            }

            var temp1 = stack1.pop();
            while (temp1 != null && !isLeaf(temp1)) {
                if (temp1.getRight() != null) {
                    stack1.push(temp1.getRight());
                }

                if (temp1.getLeft() != null) {
                    stack1.push(temp1.getLeft());
                }

                temp1 = stack1.pop();
            }

            var temp2 = stack2.pop();
            while (temp2 != null && !isLeaf(temp2)) {
                if (temp2.getRight() != null) {
                    stack2.push(temp2.getRight());
                }

                if (temp2.getLeft() != null) {
                    stack2.push(temp2.getLeft());
                }

                temp2 = stack2.pop();
            }

            if (temp1 == null && temp2 != null) {
                return false;
            }

            if (temp1 != null && temp2 == null) {
                return false;
            }

            if (temp1.getData() != temp2.getData()) {
                return false;
            }
        }

        return true;
    }

    private static boolean isLeaf(BinaryTreeNode node) {
        return (node.getLeft() == null
                && node.getRight() == null);
    }

    public static double getDensity(BinaryTreeNode node) {
        // Density = size / height

        if (node == null) {
            return 0;
        }

        AtomicInteger size = new AtomicInteger(0);
        int height = computeHeightAndSize(node, size);
        return size.doubleValue() / height;
    }

    private static int computeHeightAndSize(BinaryTreeNode node, AtomicInteger size) {
        if (node == null) {
            return 0;
        }

        int leftHeight = computeHeightAndSize(node.getLeft(), size);
        int rightHeight = computeHeightAndSize(node.getRight(), size);

        size.getAndIncrement();
        return Math.min(leftHeight, rightHeight) + 1;
    }

    public static boolean isCompleteBinaryTree(BinaryTreeNode root) {
        if (root == null) {
            return true;
        }

        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.offer(root);

        boolean nonFullNodeVisited = false;

        while (!queue.isEmpty()) {
            BinaryTreeNode node = queue.poll();

            if (node.getLeft() != null) {
                if (nonFullNodeVisited) {
                    return false;
                }

                queue.offer(node.getLeft());
            } else {
                nonFullNodeVisited = true;
            }

            if (node.getRight() != null) {
                if (nonFullNodeVisited) {
                    return false;
                }

                queue.offer(node.getRight());
            } else {
                nonFullNodeVisited = true;
            }
        }

        return true;
    }

    public static int getNodeLevel(BinaryTreeNode root, int data) {
        return getNodeLevel(root, data, 1);
    }

    private static int getNodeLevel(BinaryTreeNode node, int data, int level) {
        if (node == null) {
            return -1;
        }

        if (node.getData() == data) {
            return level;
        }

        int downLevel;
        downLevel = getNodeLevel(node.getLeft(), data, level + 1);
        if (downLevel != -1) {
            return downLevel;
        }

        downLevel = getNodeLevel(node.getRight(), data, level + 1);
        return downLevel;
    }

    public static boolean isSumSameForCoveredAndUncoveredNodes(BinaryTreeNode root) {

        int coveredSum = getSumForCoveredNodes(root);
        int sum = getSumForTree(root);
        int uncoveredSum = sum - coveredSum;

        return coveredSum == uncoveredSum;
    }

    private static int getSumForTree(BinaryTreeNode node) {
        if (node == null) {
            return 0;
        }

        return node.getData() + getSumForTree(node.getLeft()) + getSumForTree(node.getRight());
    }

    private static int getSumForCoveredNodes(BinaryTreeNode node) {
        int leftBoundarySum = getSumForLeftBoundary(node.getLeft());
        int rightBoundarySum = getSumForRightBoundary(node.getRight());

        return node.getData() + leftBoundarySum + rightBoundarySum;
    }

    private static int getSumForLeftBoundary(BinaryTreeNode node) {
        if (node == null) {
            return 0;
        }

        if (isLeaf(node)) {
            return node.getData();
        }

        if (node.getLeft() != null) {
            return node.getData() + getSumForLeftBoundary(node.getLeft());
        } else {
            return node.getData() + getSumForLeftBoundary(node.getRight());
        }
    }

    private static int getSumForRightBoundary(BinaryTreeNode node) {
        if (node == null) {
            return 0;
        }

        System.out.print(node.getData() + " ");
        if (isLeaf(node)) {
            return node.getData();
        }

        if (node.getRight() != null) {
            return node.getData() + getSumForRightBoundary(node.getRight());
        } else {
            return node.getData() + getSumForRightBoundary(node.getLeft());
        }
    }

    public static boolean areMirrorsIterative(BinaryTreeNode root1, BinaryTreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }

        if (root1 == null || root2 == null) {
            return false;
        }

        Stack<BinaryTreeNode> stack1 = new Stack<>();
        Stack<BinaryTreeNode> stack2 = new Stack<>();

        while (true) {
            while (root1 != null && root2 != null) {
                if (root1.getData() != root2.getData()) {
                    return false;
                }

                stack1.push(root1);
                stack2.push(root2);

                root1 = root1.getLeft();
                root2 = root2.getRight();
            }

            if ((root1 == null && root2 != null)
                    || (root1 != null && root2 == null)) {

                return false;
            }

            if (!stack1.isEmpty() && !stack2.isEmpty()) {
                root1 = stack1.pop();
                root2 = stack2.pop();

                root1 = root1.getRight();
                root2 = root2.getLeft();
            } else {
                break;
            }
        }

        return true;
    }

    public static Pair<Integer, Integer> findFirstUnmatchedLeaves(BinaryTreeNode root1, BinaryTreeNode root2) {
        // An alternate approach is to do in-order traversal, add leaves to 2 arrays & then compare them

        if (root1 == null && root2 == null) {
            return null;
        }

        Stack<BinaryTreeNode> stack1 = new Stack<>();
        Stack<BinaryTreeNode> stack2 = new Stack<>();

        stack1.push(root1);
        stack2.push(root2);

        while (!stack1.isEmpty() && !stack2.isEmpty()) {
            var node1 = stack1.pop();
            while (node1 != null
                    && !isLeaf(node1)) {
                stack1.push(node1.getRight());
                stack1.push(node1.getLeft());
                node1 = stack1.pop();
            }

            var node2 = stack2.pop();
            while (node2 != null
                    && !isLeaf(node2)) {
                stack2.push(node2.getRight());
                stack2.push(node2.getLeft());
                node2 = stack2.pop();
            }

            if (node1 != null && node2 != null) {
                if (node1.getData() != node2.getData()) {
                    return new Pair<>(node1.getData(), node2.getData());
                }
            }
        }

        return null;
    }

    public static void visitLevelOrderWithDirectionChangeAfterTwoLevels(BinaryTreeNode root) {
        if (root == null) {
            return;
        }

        Queue<BinaryTreeNode> queue = new LinkedList<>();
        Stack<BinaryTreeNode> stack = new Stack<>();
        queue.offer(root);
        int counter = 0;
        boolean leftToRight = true;

        while (!queue.isEmpty()) {
            counter++;

            int size = queue.size();
            for (int i = 0; i < size; i++) {
                var node = queue.poll();

                if (leftToRight) {
                    System.out.print(node.getData() + " ");
                } else {
                    stack.push(node);
                }

                if (node.getLeft() != null) {
                    queue.offer(node.getLeft());
                }
                if (node.getRight() != null) {
                    queue.offer(node.getRight());
                }
            }

            while (!stack.isEmpty()) {
                System.out.print(stack.pop().getData() + " ");
            }

            System.out.println();

            if (counter == 2) {
                counter = 0;
                leftToRight = !leftToRight;
            }
        }
    }

    public static int maxDifferenceBetweenAnyNodeAndAncestor(BinaryTreeNode root) {
        AtomicInteger maxDifference = new AtomicInteger(Integer.MIN_VALUE);

        maxDifferenceBetweenAnyNodeAndAncestor(root, maxDifference);
        return maxDifference.intValue();
    }

    private static int maxDifferenceBetweenAnyNodeAndAncestor(BinaryTreeNode node, AtomicInteger maxDifference) {
        if (node == null) {
            return Integer.MAX_VALUE;
        }

        if (node.getLeft() == null && node.getRight() == null) {
            return node.getData();
        }

        int minValue = Math.min(maxDifferenceBetweenAnyNodeAndAncestor(node.getLeft(), maxDifference),
                maxDifferenceBetweenAnyNodeAndAncestor(node.getRight(), maxDifference));

        int difference = node.getData() - minValue;
        if (difference > maxDifference.intValue()) {
            maxDifference.set(difference);
        }

        return Math.min(minValue, node.getData());
    }

    public static BinaryTreeNode removeHalfNodes(BinaryTreeNode root) {
        if (root == null) {
            return null;
        }

        root.setLeft(removeHalfNodes(root.getLeft()));
        root.setRight(removeHalfNodes(root.getRight()));

        if (isLeaf(root)) {
            return root;
        }

        if (root.getLeft() == null) {
            return root.getRight();
        } else if (root.getRight() == null) {
            return root.getLeft();
        }

        return root;
    }

    public static int sumOfLongestRootToLeafPath(BinaryTreeNode root) {
        if (root == null) {
            return Integer.MIN_VALUE;
        }

        AtomicInteger maxSum = new AtomicInteger(0);
        AtomicInteger maxLength = new AtomicInteger(0);

        sumOfLongestRootToLeafPath(root, 0, 0, maxLength, maxSum);
        return maxSum.intValue();
    }

    private static void sumOfLongestRootToLeafPath(BinaryTreeNode node, int length, int sum, AtomicInteger maxLength, AtomicInteger maxSum) {
        if (node == null) {
            if (length > maxLength.intValue()) {
                maxLength.set(length);
                maxSum.set(sum);
            } else if (length == maxLength.intValue()
                    && maxSum.intValue() < sum) {
                maxSum.set(sum);
            }

            return;
        }

        sum += node.getData();
        sumOfLongestRootToLeafPath(node.getLeft(), length + 1, sum, maxLength, maxSum);
        sumOfLongestRootToLeafPath(node.getRight(), length + 1, sum, maxLength, maxSum);
    }

    public static void reverseAlternateLevelsOfPerfectBinaryTree(BTNode<Character> root) {
        List<Character> store = new ArrayList<>();
        constructStoreForAlternateLevelsOfPerfectBinaryTree(root, store, 0);
        Collections.reverse(store);
        AtomicInteger index = new AtomicInteger(0);
        constructTreeFromStoreForAlternateLevelsOfPerfectBinaryTree(root, store, 0, index);
    }

    private static void constructStoreForAlternateLevelsOfPerfectBinaryTree(BTNode<Character> root, List<Character> store, int level) {
        if (root == null) {
            return;
        }

        constructStoreForAlternateLevelsOfPerfectBinaryTree(root.getLeft(), store, level + 1);
        if (level % 2 > 0) {
            store.add(root.getData());
        }
        constructStoreForAlternateLevelsOfPerfectBinaryTree(root.getRight(), store, level + 1);
    }

    private static void constructTreeFromStoreForAlternateLevelsOfPerfectBinaryTree(BTNode<Character> root, List<Character> store, int level, AtomicInteger index) {
        if (root == null) {
            return;
        }

        constructTreeFromStoreForAlternateLevelsOfPerfectBinaryTree(root.getLeft(), store, level + 1, index);
        if (level % 2 > 0) {
            root.setData(store.get(index.getAndIncrement()));
        }
        constructTreeFromStoreForAlternateLevelsOfPerfectBinaryTree(root.getRight(), store, level + 1, index);
    }

    public static void reverseAlternateLevelsOfPerfectBinaryTreeWithSingleTraversal(BTNode<Character> root) {
        if (root == null) {
            return;
        }

        preOrderAndSwapIfLevelOdd(root.getLeft(), root.getRight(), 1);
    }

    private static void preOrderAndSwapIfLevelOdd(BTNode<Character> root1, BTNode<Character> root2, int level) {
        if (root1 == null
                || root2 == null) {
            return;
        }

        if (level % 2 > 0) {
            char temp = root1.getData();
            root1.setData(root2.getData());
            root2.setData(temp);
        }

        preOrderAndSwapIfLevelOdd(root1.getLeft(), root2.getRight(), level + 1);
        preOrderAndSwapIfLevelOdd(root1.getRight(), root2.getLeft(), level + 1);
    }

    public static int getLargestSubtreeSum(BinaryTreeNode root) {
        AtomicInteger maxSubtreeSum = new AtomicInteger(0);
        getLargestSubtreeSum(root, maxSubtreeSum);

        return maxSubtreeSum.intValue();
    }

    private static int getLargestSubtreeSum(BinaryTreeNode root, AtomicInteger maxSubtreeSum) {
        if (root == null) {
            return 0;
        }

        int sum = root.getData()
                + getLargestSubtreeSum(root.getLeft(), maxSubtreeSum)
                + getLargestSubtreeSum(root.getRight(), maxSubtreeSum);

        if (sum > maxSubtreeSum.intValue()) {
            maxSubtreeSum.set(sum);
        }

        return sum;
    }

    public static int[] getBottomView(BinaryTreeNode root) {
        if (root == null) {
            return null;
        }

        Queue<Pair<BinaryTreeNode, Integer>> queue = new LinkedList<>();
        queue.offer(new Pair(root, 0));

        Map<Integer, Integer> map = new TreeMap<>();

        while (!queue.isEmpty()) {
            var item = queue.poll();
            BinaryTreeNode node = item.getFirst();
            int hd = item.getSecond();

            map.put(hd, node.getData());

            if (node.getLeft() != null) {
                queue.offer(new Pair(node.getLeft(), hd - 1));
            }

            if (node.getRight() != null) {
                queue.offer(new Pair(node.getRight(), hd + 1));
            }
        }

        int[] bottomView = new int[map.size()];
        int index = 0;
        for (var value : map.values()) {
            bottomView[index++] = value;
        }

        return bottomView;
    }

    public static int getMaxLevelSum(BinaryTreeNode root) {
        if (root == null) {
            return 0;
        }

        int maxSum = 0;
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            int sum = 0;

            for (int i = 0; i < size; i++) {
                var node = queue.poll();
                sum += node.getData();

                if (node.getLeft() != null) {
                    queue.offer(node.getLeft());
                }

                if (node.getRight() != null) {
                    queue.offer(node.getRight());
                }

                maxSum = Math.max(maxSum, sum);
            }
        }

        return maxSum;
    }

    public static int lengthOfRootToLeafPathWithMostDistinctNodes(BinaryTreeNode root) {
        Map<Integer, Integer> visitedNodes = new HashMap<>();
        return lengthOfRootToLeafPathWithMostDistinctNodes(root, visitedNodes);
    }

    public static int lengthOfRootToLeafPathWithMostDistinctNodes(BinaryTreeNode root, Map<Integer, Integer> visitedNodes) {
        if (root == null) {
            return visitedNodes.size();
        }

        if (visitedNodes.containsKey(root.getData())) {
            int count = visitedNodes.get(root.getData()) + 1;
            visitedNodes.put(root.getData(), count);
        } else {
            visitedNodes.put(root.getData(), 1);
        }

        int maxLength = Math.max(
                lengthOfRootToLeafPathWithMostDistinctNodes(root.getLeft(), visitedNodes),
                lengthOfRootToLeafPathWithMostDistinctNodes(root.getRight(), visitedNodes));

        int count = visitedNodes.get(root.getData()) - 1;
        if (count == 0) {
            visitedNodes.remove(root.getData());
        } else {
            visitedNodes.put(root.getData(), count);
        }

        return maxLength;
    }

    public static List<List<Integer>> getAllKSumPaths(BinaryTreeNode root, int k) {
        // Note: A path can start from any node and end at any node and must be downward only, i.e. they need not be root node and leaf node.
        List<List<Integer>> kSumPaths = new LinkedList<>();
        List<Integer> path = new LinkedList<>();
        constructKSumPaths(root, k, kSumPaths, path);
        return kSumPaths;
    }

    public static void constructKSumPaths(BinaryTreeNode root, int k, List<List<Integer>> kSumPaths, List<Integer> path) {
        if (root == null) {
            return;
        }

        path.add(root.getData());
        constructKSumPaths(root.getLeft(), k, kSumPaths, path);
        constructKSumPaths(root.getRight(), k, kSumPaths, path);

        int runningSum = 0;
        for (int i = path.size() - 1; i >= 0; i--) {
            runningSum += path.get(i);
            if (runningSum == k) {
                List<Integer> kPath = new LinkedList<>();
                Utilities.copy(kPath, path, i, path.size() - 1);

                kSumPaths.add(kPath);
            }
        }

        path.remove(path.size() - 1);
    }

    public static List<Integer> getRightView(BinaryTreeNode root) {
        if (root == null) {
            return null;
        }

        List<Integer> rightView = new LinkedList<>();
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                var node = queue.poll();

                if (i == n - 1) {
                    rightView.add(node.getData());
                }

                if (node.getLeft() != null) {
                    queue.offer(node.getLeft());
                }

                if (node.getRight() != null) {
                    queue.offer(node.getRight());
                }
            }
        }

        return rightView;
    }

    public static Integer getKthAncestor(BinaryTreeNode root, int nodeData, int k) {
        // Assumption: all nodes have unique values
        if (root == null) {
            return null;
        }

        AtomicBoolean ancestorFound = new AtomicBoolean(false);
        var ancestor = getKthAncestor(root, nodeData, new AtomicInteger(k), ancestorFound);
        if (ancestorFound.get()) {
            return ancestor.getData();
        }

        return null;
    }

    private static BinaryTreeNode getKthAncestor(BinaryTreeNode node, int nodeData, AtomicInteger k, AtomicBoolean ancestorFound) {
        if (node == null) {
            return null;
        }

        var leftResult = getKthAncestor(node.getLeft(), nodeData, k, ancestorFound);
        var rightResult = getKthAncestor(node.getRight(), nodeData, k, ancestorFound);
        var result = (leftResult != null) ? leftResult : rightResult;

        if (node.getData() == nodeData) {
            if (k.intValue() == 0) {
                ancestorFound.set(true);
            } else {
                k.decrementAndGet();
            }

            return node;
        } else if (result != null) {
            if (k.intValue() == 0) {
                ancestorFound.set(true);
                return node;
            } else {
                k.decrementAndGet();
            }

            return result;
        }

        return result;
    }

    public static Integer getKthAncestorApproach2(BinaryTreeNode root, int nodeData, int k) {
        // Assumption: all nodes have unique values
        if (root == null) {
            return null;
        }

        Map<Integer, Integer> parent = new HashMap<>();
        parent.put(root.getData(), null);

        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                var node = queue.poll();

                if (node.getLeft() != null) {
                    parent.put(node.getLeft().getData(), node.getData());
                    queue.offer(node.getLeft());
                }

                if (node.getRight() != null) {
                    parent.put(node.getRight().getData(), node.getData());
                    queue.offer(node.getRight());
                }
            }
        }

        if (!parent.containsKey(nodeData)) {
            return null;
        }

        int count = 0;
        Integer ancestor = nodeData;
        while (count < k
                && ancestor != null) {
            ancestor = parent.get(ancestor);
            count++;
        }

        return ancestor;
    }

    public static BinaryTreeNode constructPerfectBinaryTreeFromLinkedListOfLevelOrder(LinkedListNode head) {
        if(head == null) {
            return null;
        }

        BinaryTreeNode root = new BinaryTreeNode(head.getData());
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.offer(root);

        LinkedListNode current = head.getNext();

        while(!queue.isEmpty()
                && current != null) {
            var node = queue.poll();

            // Left Node
            if(current != null) {
                BinaryTreeNode left = new BinaryTreeNode(current.getData());
                node.setLeft(left);
                queue.offer(left);
                current = current.getNext();
            }

            // Right Node
            if(current != null) {
                BinaryTreeNode right = new BinaryTreeNode(current.getData());
                node.setRight(right);
                queue.offer(right);
                current = current.getNext();
            }
        }

        return root;
    }

    public static BinaryTreeNode flipTree(BinaryTreeNode root) {
        if(root == null) {
            return null;
        }

        if(isLeaf(root)) {
            return root;
        }

        var newRoot = flipTree(root.getLeft());

        var left = root.getLeft();
        var right = root.getRight();

        left.setLeft(right);
        left.setRight(root);
        root.setLeft(null);
        root.setRight(null);

        return newRoot;
    }

    public static int[] getLevelOrder(BinaryTreeNode root) {
        ArrayList<Integer> collector = new ArrayList<>();

        Consumer<BinaryTreeNode> visit = new Consumer<BinaryTreeNode>() {
            @Override
            public void accept(BinaryTreeNode node) {
                collector.add(node.getData());
            }
        };

        visitLevelOrder(root, visit);

        return collector.stream().mapToInt(i -> i).toArray();
    }

    public static int[] getPreOrder(BinaryTreeNode root) {
        ArrayList<Integer> collector = new ArrayList<>();

        Consumer<BinaryTreeNode> visit = new Consumer<BinaryTreeNode>() {
            @Override
            public void accept(BinaryTreeNode node) {
                collector.add(node.getData());
            }
        };

        visitPreOrder(root, visit);

        return collector.stream().mapToInt(i -> i).toArray();
    }

    public static int[] getInOrder(BinaryTreeNode root) {
        ArrayList<Integer> collector = new ArrayList<>();

        Consumer<BinaryTreeNode> visit = new Consumer<BinaryTreeNode>() {
            @Override
            public void accept(BinaryTreeNode node) {
                collector.add(node.getData());
            }
        };

        visitInOrder(root, visit);

        return collector.stream().mapToInt(i -> i).toArray();
    }

    public static int[] getPostOrder(BinaryTreeNode root) {
        ArrayList<Integer> collector = new ArrayList<>();

        Consumer<BinaryTreeNode> visit = new Consumer<BinaryTreeNode>() {
            @Override
            public void accept(BinaryTreeNode node) {
                collector.add(node.getData());
            }
        };

        visitPostOrder(root, visit);

        return collector.stream().mapToInt(i -> i).toArray();
    }

    public static Map<Integer, List<Integer>> getDiagonalTraversal(BinaryTreeNode root) {
        Map<Integer, List<Integer>> diagonalTraversal = new HashMap<>();
        constructDiagonalTraversal(root, 0, diagonalTraversal);

        return diagonalTraversal;
    }

    private static void constructDiagonalTraversal(BinaryTreeNode root, int diagonal, Map<Integer,List<Integer>> diagonalTraversal) {
        if(root == null) {
            return;
        }

        if(!diagonalTraversal.containsKey(diagonal)) {
            diagonalTraversal.put(diagonal, new ArrayList<>());
        }

        diagonalTraversal.get(diagonal).add(root.getData());

        constructDiagonalTraversal(root.getLeft(), diagonal + 1, diagonalTraversal);
        constructDiagonalTraversal(root.getRight(), diagonal, diagonalTraversal);
    }

    public static Map<Integer, List<Integer>> getDiagonalTraversalIterative(BinaryTreeNode root) {
        Map<Integer, List<Integer>> diagonalTraversal = new HashMap<>();

        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.offer(root);
        queue.offer(null);

        int diagonal = 0;

        while(!queue.isEmpty()) {
            if(!diagonalTraversal.containsKey(diagonal)) {
                diagonalTraversal.put(diagonal, new ArrayList<>());
            }

            var node = queue.poll();
            if(node == null) {
                if(queue.isEmpty()) {
                    break;
                }

                queue.offer(null);
                diagonal++;
                continue;
            }

            while(node != null) {
                if (node.getLeft() != null) {
                    queue.offer(node.getLeft());
                }

                diagonalTraversal.get(diagonal).add(node.getData());
                node = node.getRight();
            }
        }

        return diagonalTraversal;
    }

    public static Map<Integer, Integer> getSmallestValuesAtEachLevel(BinaryTreeNode root) {
        Map<Integer, Integer> map = new HashMap<>();

        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int level = 0;

        while(!queue.isEmpty()) {
            int n = queue.size();
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                var node = queue.poll();
                min = Math.min(min, node.getData());

                if(node.getLeft() != null) {
                    queue.offer(node.getLeft());
                }

                if(node.getRight() != null) {
                    queue.offer(node.getRight());
                }
            }

            map.put(level, min);
            level++;
        }

        return map;
    }

    public static boolean isBalanced(BinaryTreeNode root) {
        return isBalanced(root, new AtomicInteger(0));
    }

    public static boolean isBalanced(BinaryTreeNode root, AtomicInteger height) {
        if(root == null) {
            height.set(0);
            return true;
        }

        AtomicInteger leftHeight = new AtomicInteger(0);
        AtomicInteger rightHeight = new AtomicInteger(0);

        boolean leftBalanced = isBalanced(root.getLeft(), leftHeight);
        boolean rightBalanced = isBalanced(root.getRight(), rightHeight);
        height.set(Math.max(leftHeight.intValue(), rightHeight.intValue()) + 1);

        if(Math.abs(leftHeight.intValue() - rightHeight.intValue()) <= 1
            && leftBalanced
            && rightBalanced) {
            return true;
        }

        return false;
    }

    public static boolean isBalancedA2(BinaryTreeNode root) {
        if(root == null) {
            return true;
        }

        int leftHeight = getTreeHeight(root.getLeft());
        int rightHeight = getTreeHeight(root.getRight());

        if(Math.abs(leftHeight - rightHeight) <= 1
            && isBalancedA2(root.getLeft())
            && isBalancedA2(root.getRight())) {
            return true;
        }

        return false;
    }
}
