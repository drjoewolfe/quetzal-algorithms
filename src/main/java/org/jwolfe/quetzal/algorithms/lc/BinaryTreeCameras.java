package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashSet;
import java.util.Set;

public class BinaryTreeCameras {
    class Solution {
        private int minCameraCount;

        public int minCameraCover(TreeNode root) {
            if(root == null) {
                return 0;
            }

            minCameraCount = 0;
            Set<TreeNode> coveredNodes = new HashSet<>();
            coveredNodes.add(null);

            minCameraCover(root, null, coveredNodes);

            return minCameraCount;
        }

        private void minCameraCover(TreeNode node, TreeNode parent, Set<TreeNode> coveredNodes) {
            if(node == null) {
                return;
            }

            minCameraCover(node.left, node, coveredNodes);
            minCameraCover(node.right, node, coveredNodes);

            if(!coveredNodes.contains(node.left)
                    || !coveredNodes.contains(node.right)
                    || (parent == null && !coveredNodes.contains(node))) {
                minCameraCount++;
                coveredNodes.add(parent);
                coveredNodes.add(node);
                coveredNodes.add(node.left);
                coveredNodes.add(node.right);
            }
        }
    }

    class Solution_Greedy {
        private int minCameraCount;

        public int minCameraCover(TreeNode root) {
            if(root == null) {
                return 0;
            }

            minCameraCount = 0;
            Set<TreeNode> coveredNodes = new HashSet<>();
            coveredNodes.add(null);

            minCameraCover(root, null, coveredNodes);

            return minCameraCount;
        }

        private void minCameraCover(TreeNode node, TreeNode parent, Set<TreeNode> coveredNodes) {
            if(node == null) {
                return;
            }

            minCameraCover(node.left, node, coveredNodes);
            minCameraCover(node.right, node, coveredNodes);

            if(!coveredNodes.contains(node.left)
                    || !coveredNodes.contains(node.right)
                    || (parent == null && !coveredNodes.contains(node))) {
                minCameraCount++;
                coveredNodes.add(parent);
                coveredNodes.add(node);
                coveredNodes.add(node.left);
                coveredNodes.add(node.right);
            }
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}

//    968. Binary Tree Cameras
//    Hard
//    Given a binary tree, we install cameras on the nodes of the tree.
//
//    Each camera at a node can monitor its parent, itself, and its immediate children.
//
//    Calculate the minimum number of cameras needed to monitor all nodes of the tree.
//
//
//
//    Example 1:
//
//
//    Input: [0,0,null,0,0]
//    Output: 1
//    Explanation: One camera is enough to monitor all nodes if placed as shown.
//    Example 2:
//
//
//    Input: [0,0,null,0,null,0,null,null,0]
//    Output: 2
//    Explanation: At least two cameras are needed to monitor all nodes of the tree. The above image shows one of the valid configurations of camera placement.
//
//    Note:
//
//    The number of nodes in the given tree will be in the range [1, 1000].
//    Every node has value 0.