package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LongestPathWithDifferentAdjacentCharacters {
    class Solution {
        int longestPath;

        public int longestPath(int[] parent, String s) {
            if(parent == null || parent.length == 0 || s == null || s.length() == 0) {
                return 0;
            }

            Map<Integer, List<Integer>> tree = new HashMap<>();
            int n = parent.length;
            for(int u = 0; u < n; u++) {
                tree.put(u, new ArrayList<>());
            }

            for(int u = 1; u < n; u++) {
                int v = parent[u];

                tree.get(v).add(u);
            }

            longestPath = 1;
            computeLongestPath(tree, 0, s);

            return longestPath;
        }

        private int computeLongestPath(Map<Integer, List<Integer>> tree, Integer u, String s) {
            List<Integer> children = tree.get(u);
            int maxThroughHere = 1;
            int maxEndingHere = 1;

            List<Integer> longestPathEndingAtChildren = new ArrayList<>();
            for(Integer v : children) {
                int longestPathEndingAtChild = computeLongestPath(tree, v, s);
                longestPathEndingAtChildren.add(longestPathEndingAtChild);
            }

            for(int i = 0; i < children.size(); i++) {
                int v = children.get(i);
                if(s.charAt(u) == s.charAt(v)) {
                    continue;
                }

                maxEndingHere = Math.max(maxEndingHere, 1 + longestPathEndingAtChildren.get(i));
                maxThroughHere = Math.max(maxThroughHere, 1 + longestPathEndingAtChildren.get(i));

                for(int j = i + 1; j < children.size(); j++) {
                    int w = children.get(j);
                    if(s.charAt(u) == s.charAt(w)) {
                        continue;
                    }

                    maxThroughHere = Math.max(maxThroughHere, 1 + longestPathEndingAtChildren.get(i) + longestPathEndingAtChildren.get(j));
                }
            }

            longestPath = Math.max(longestPath, maxThroughHere);
            return maxEndingHere;
        }
    }

    class Solution_ForBinaryTree_Incorrect {
        int longestPath;

        public int longestPath(int[] parent, String s) {
            if(parent == null || parent.length == 0 || s == null || s.length() == 0) {
                return 0;
            }

            Map<Integer, List<Integer>> tree = new HashMap<>();
            int n = parent.length;
            for(int u = 0; u < n; u++) {
                tree.put(u, new ArrayList<>());
            }

            for(int u = 1; u < n; u++) {
                int v = parent[u];

                tree.get(v).add(u);
            }

            longestPath = 0;
            computeLongestPath(tree, 0, s);

            return longestPath;
        }

        private int computeLongestPath(Map<Integer, List<Integer>> tree, Integer u, String s) {
            if(u == -1) {
                return 0;
            }

            List<Integer> children = tree.get(u);
            int leftChild = children.size() > 0 ? children.get(0) : -1;
            int rightChild = children.size() > 1 ? children.get(1) : -1;

            int longestPathEndingAtLeft = computeLongestPath(tree, leftChild, s);
            int longestPathEndingAtRight = computeLongestPath(tree, rightChild, s);

            int maxThroughHere = 1;
            int maxEndingHere = 1;
            if(leftChild != -1 && s.charAt(u) != s.charAt(leftChild)) {
                maxThroughHere += longestPathEndingAtLeft;
                maxEndingHere += longestPathEndingAtLeft;
            }

            if(rightChild != -1 && s.charAt(u) != s.charAt(rightChild)) {
                maxThroughHere += longestPathEndingAtRight;
                maxEndingHere = Math.max(maxEndingHere, 1 + longestPathEndingAtRight);
            }

            longestPath = Math.max(longestPath, maxThroughHere);
            return maxEndingHere;
        }
    }

// [-1,0,0,1,1,2]
// "abacbe"

// [-1,0,0,0]
// "aabc"

// [-1,137,65,60,73,138,81,17,45,163,145,99,29,162,19,20,132,132,13,60,21,18,155,65,13,163,125,102,96,60,50,101,100,86,162,42,162,94,21,56,45,56,13,23,101,76,57,89,4,161,16,139,29,60,44,127,19,68,71,55,13,36,148,129,75,41,107,91,52,42,93,85,125,89,132,13,141,21,152,21,79,160,130,103,46,65,71,33,129,0,19,148,65,125,41,38,104,115,130,164,138,108,65,31,13,60,29,116,26,58,118,10,138,14,28,91,60,47,2,149,99,28,154,71,96,60,106,79,129,83,42,102,34,41,55,31,154,26,34,127,42,133,113,125,113,13,54,132,13,56,13,42,102,135,130,75,25,80,159,39,29,41,89,85,19]
// "ajunvefrdrpgxltugqqrwisyfwwtldxjgaxsbbkhvuqeoigqssefoyngykgtthpzvsxgxrqedntvsjcpdnupvqtroxmbpsdwoswxfarnixkvcimzgvrevxnxtkkovwxcjmtgqrrsqyshxbfxptuvqrytctujnzzydhpal"
}

//    2246. Longest Path With Different Adjacent Characters
//    Hard
//    You are given a tree (i.e. a connected, undirected graph that has no cycles) rooted at node 0 consisting of n nodes numbered from 0 to n - 1. The tree is represented by a 0-indexed array parent of size n, where parent[i] is the parent of node i. Since node 0 is the root, parent[0] == -1.
//
//    You are also given a string s of length n, where s[i] is the character assigned to node i.
//
//    Return the length of the longest path in the tree such that no pair of adjacent nodes on the path have the same character assigned to them.
//
//
//
//    Example 1:
//
//
//    Input: parent = [-1,0,0,1,1,2], s = "abacbe"
//    Output: 3
//    Explanation: The longest path where each two adjacent nodes have different characters in the tree is the path: 0 -> 1 -> 3. The length of this path is 3, so 3 is returned.
//    It can be proven that there is no longer path that satisfies the conditions.
//    Example 2:
//
//
//    Input: parent = [-1,0,0,0], s = "aabc"
//    Output: 3
//    Explanation: The longest path where each two adjacent nodes have different characters is the path: 2 -> 0 -> 3. The length of this path is 3, so 3 is returned.
//
//
//    Constraints:
//
//    n == parent.length == s.length
//    1 <= n <= 105
//    0 <= parent[i] <= n - 1 for all i >= 1
//    parent[0] == -1
//    parent represents a valid tree.
//    s consists of only lowercase English letters.