package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class ValidateBinaryTreeNodes {
    class Solution {
        public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
            if(n < 1 || leftChild == null || rightChild == null || leftChild.length != n || rightChild.length != n) {
                return false;
            }

            int[] indegrees = new int[n];
            for(int p = 0; p < n; p++) {
                int l = leftChild[p];
                int r = rightChild[p];

                if(l != -1) {
                    if(indegrees[l] == 1) {
                        return false;
                    }

                    indegrees[l]++;
                }

                if(r != -1) {
                    if(indegrees[r] == 1) {
                        return false;
                    }

                    indegrees[r]++;
                }
            }

            int root = -1;
            for(int u = 0; u < n; u++) {
                if(indegrees[u] == 0) {
                    if(root != -1) {
                        return false;
                    }

                    root = u;
                }
            }

            if(root == -1) {
                // No root
                return false;
            }

            return reachableNodeCount(root, leftChild, rightChild) == n;
        }

        private int reachableNodeCount(int root, int[] leftChild, int[] rightChild) {
            if(root == -1) {
                return 0;
            }

            return 1
                    + reachableNodeCount(leftChild[root], leftChild, rightChild)
                    + reachableNodeCount(rightChild[root], leftChild, rightChild);
        }
    }

    class Solution_InCorrect_2 {
        public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
            if(n < 1 || leftChild == null || rightChild == null || leftChild.length != n || rightChild.length != n) {
                return false;
            }

            int[] parent = new int[n];
            Arrays.fill(parent, -1);

            for(int p = 0; p < n; p++) {
                int l = leftChild[p];
                int r = rightChild[p];

                if(l != -1) {
                    if(parent[l] != -1) {
                        return false;
                    }

                    parent[l] = p;
                }

                if(r != -1) {
                    if(parent[r] != -1) {
                        return false;
                    }

                    parent[r] = p;
                }
            }

            int nodesWithNoParent = 0;
            for(int i = 0; i < n; i++) {
                if(parent[i] == -1) {
                    nodesWithNoParent++;
                }
            }

            return nodesWithNoParent == 1;
        }
    }

    class Solution_Incorrect_1 {
        public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
            if(n < 1 || leftChild == null || rightChild == null || leftChild.length != n || rightChild.length != n) {
                return false;
            }

            int[] parent = new int[n];
            Arrays.fill(parent, -1);

            for(int i = 0; i < n; i++) {
                int left = leftChild[i];
                int right = rightChild[i];

                if(left != -1) {
                    if(parent[left] != -1) {
                        return false;
                    }

                    parent[left] = i;
                }

                if(right != -1) {
                    if(parent[right] != -1) {
                        return false;
                    }

                    parent[right] = i;
                }
            }

            print(parent);
            boolean rootFound = false;
            for(int i = 0; i < n; i++) {
                if(parent[i] == -1) {
                    if(rootFound) {
                        return false;
                    }

                    rootFound = true;
                }
            }

            return rootFound;
        }

        private void print(int[] arr) {
            for(int a : arr) {
                System.out.print(a + " ");
            }

            System.out.println();
        }
    }

// 4
// [1,-1,3,-1]
// [2,-1,-1,-1]

// 4
// [1,0,3,-1]
// [-1,-1,-1,-1]
}

//    1361. Validate Binary Tree Nodes
//    Medium
//    You have n binary tree nodes numbered from 0 to n - 1 where node i has two children leftChild[i] and rightChild[i], return true if and only if all the given nodes form exactly one valid binary tree.
//
//    If node i has no left child then leftChild[i] will equal -1, similarly for the right child.
//
//    Note that the nodes have no values and that we only use the node numbers in this problem.
//
//
//
//    Example 1:
//
//
//    Input: n = 4, leftChild = [1,-1,3,-1], rightChild = [2,-1,-1,-1]
//    Output: true
//    Example 2:
//
//
//    Input: n = 4, leftChild = [1,-1,3,-1], rightChild = [2,3,-1,-1]
//    Output: false
//    Example 3:
//
//
//    Input: n = 2, leftChild = [1,0], rightChild = [-1,-1]
//    Output: false
//
//
//    Constraints:
//
//    n == leftChild.length == rightChild.length
//    1 <= n <= 104
//    -1 <= leftChild[i], rightChild[i] <= n - 1
