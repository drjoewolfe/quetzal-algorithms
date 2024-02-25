package org.jwolfe.quetzal.algorithms.lc;

public class GreatestCommonDivisorTraversal {
    class Solution {
        public boolean canTraverseAllPairs(int[] nums) {
            if(nums == null || nums.length == 0) {
                return false;
            }

            int n = nums.length;
            if(n == 1) {
                return true;
            }

            int MAX = 100_000;
            boolean[] included = new boolean[MAX + 1];
            for(int x : nums) {
                included[x] = true;
            }

            if(included[1]) {
                return false;
            }

            int[] sieve = new int[MAX + 1];
            for(int d = 2; d <= MAX; d++) {
                if(sieve[d] == 0) {
                    for(int v = d; v <= MAX; v+=d) {
                        sieve[v] = d;
                    }
                }
            }

            // print(sieve);

            DisjointSet disjointSet = new DisjointSet(2 * MAX + 1);
            for(int x : nums) {
                int val = x;

                while(val > 1) {
                    int prime = sieve[val];
                    int root = prime + MAX;

                    int rootP = disjointSet.find(root);
                    int xp = disjointSet.find(x);

                    if(rootP != xp) {
                        disjointSet.union(rootP, xp);
                    }

                    while(val % prime == 0) {
                        val /= prime;
                    }
                }
            }

            // print(disjointSet.parent);

            int count = 0;
            for(int i = 2; i <= MAX; i++) {
                if(included[i] && disjointSet.find(i) == i) {
                    count++;
                }
            }

            return count == 1;
        }

        public class DisjointSet {
            int[] parent;
            int[] rank;

            public DisjointSet(int n) {
                parent = new int[n + 1];
                for(int i = 0; i < n + 1; i++) {
                    parent[i] = i;
                }

                rank = new int[n + 1];

            }

            public void union(int x, int y) {
                int xp = find(x);
                int yp = find(y);

                if(xp == yp) {
                    return;
                }

                if(rank[xp] < rank[yp]) {
                    parent[xp] = yp;
                } else if(rank[xp] > rank[yp]) {
                    parent[yp] = xp;
                } else {
                    parent[xp] = yp;
                    rank[yp]++;
                }
            }

            public int find(int x) {
                if(parent[x] != x) {
                    parent[x] = find(parent[x]);
                }

                return parent[x];
            }
        }

        private void print(int[] arr) {
            for(int a : arr) {
                System.out.print(a + " ");
            }

            System.out.println();
        }
    }

// [2,3,6]
}

//    2709. Greatest Common Divisor Traversal
//    Hard
//    You are given a 0-indexed integer array nums, and you are allowed to traverse between its indices. You can traverse between index i and index j, i != j, if and only if gcd(nums[i], nums[j]) > 1, where gcd is the greatest common divisor.
//
//    Your task is to determine if for every pair of indices i and j in nums, where i < j, there exists a sequence of traversals that can take us from i to j.
//
//    Return true if it is possible to traverse between all such pairs of indices, or false otherwise.
//
//
//
//    Example 1:
//
//    Input: nums = [2,3,6]
//    Output: true
//    Explanation: In this example, there are 3 possible pairs of indices: (0, 1), (0, 2), and (1, 2).
//    To go from index 0 to index 1, we can use the sequence of traversals 0 -> 2 -> 1, where we move from index 0 to index 2 because gcd(nums[0], nums[2]) = gcd(2, 6) = 2 > 1, and then move from index 2 to index 1 because gcd(nums[2], nums[1]) = gcd(6, 3) = 3 > 1.
//    To go from index 0 to index 2, we can just go directly because gcd(nums[0], nums[2]) = gcd(2, 6) = 2 > 1. Likewise, to go from index 1 to index 2, we can just go directly because gcd(nums[1], nums[2]) = gcd(3, 6) = 3 > 1.
//    Example 2:
//
//    Input: nums = [3,9,5]
//    Output: false
//    Explanation: No sequence of traversals can take us from index 0 to index 2 in this example. So, we return false.
//    Example 3:
//
//    Input: nums = [4,3,12,8]
//    Output: true
//    Explanation: There are 6 possible pairs of indices to traverse between: (0, 1), (0, 2), (0, 3), (1, 2), (1, 3), and (2, 3). A valid sequence of traversals exists for each pair, so we return true.
//
//
//    Constraints:
//
//    1 <= nums.length <= 105
//    1 <= nums[i] <= 105