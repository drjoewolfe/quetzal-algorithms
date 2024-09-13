package org.jwolfe.quetzal.algorithms.lc;

public class XORQueriesOfASubarray {
    class Solution {
        public int[] xorQueries(int[] arr, int[][] queries) {
            if (arr == null || arr.length == 0 || queries == null || queries.length == 0) {
                return new int[0];
            }

            int n = arr.length;
            int[] prefixXor = new int[n + 1];
            for (int i = 0; i < n; i++) {
                prefixXor[i + 1] = prefixXor[i] ^ arr[i];
            }

            int m = queries.length;
            int[] results = new int[m];
            for (int i = 0; i < m; i++) {
                int[] query = queries[i];
                int left = query[0];
                int right = query[1];

                results[i] = prefixXor[left] ^ prefixXor[right + 1];
            }

            return results;
        }
    }
}

//    1310. XOR Queries of a Subarray
//    Medium
//    You are given an array arr of positive integers. You are also given the array queries where queries[i] = [lefti, righti].
//
//    For each query i compute the XOR of elements from lefti to righti (that is, arr[lefti] XOR arr[lefti + 1] XOR ... XOR arr[righti] ).
//
//    Return an array answer where answer[i] is the answer to the ith query.
//
//
//
//    Example 1:
//
//    Input: arr = [1,3,4,8], queries = [[0,1],[1,2],[0,3],[3,3]]
//    Output: [2,7,14,8]
//    Explanation:
//    The binary representation of the elements in the array are:
//    1 = 0001
//    3 = 0011
//    4 = 0100
//    8 = 1000
//    The XOR values for queries are:
//    [0,1] = 1 xor 3 = 2
//    [1,2] = 3 xor 4 = 7
//    [0,3] = 1 xor 3 xor 4 xor 8 = 14
//    [3,3] = 8
//    Example 2:
//
//    Input: arr = [4,8,2,10], queries = [[2,3],[1,3],[0,0],[0,3]]
//    Output: [8,0,4,4]
//
//
//    Constraints:
//
//    1 <= arr.length, queries.length <= 3 * 104
//    1 <= arr[i] <= 109
//    queries[i].length == 2
//    0 <= lefti <= righti < arr.length
