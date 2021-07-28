package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;

public class BeautifulArray {
    class Solution {
        public int[] beautifulArray(int N) {
            return generateBeautifulArray(N, new HashMap<>());
        }

        private int[] generateBeautifulArray(int n, Map<Integer, int[]> memo) {
            if(memo.containsKey(n)) {
                return memo.get(n);
            }

            int[] ans = new int[n];
            if(n == 1) {
                ans[0] = 1;
            } else {
                int index = 0;

                // Odd
                int[] left = generateBeautifulArray((n + 1) / 2, memo);
                for(int a : left) {
                    ans[index] = 2*a - 1;
                    index++;
                }

                // Even
                int[] right = generateBeautifulArray(n / 2, memo);
                for(int a : right) {
                    ans[index] = 2*a;
                    index++;
                }
            }

            memo.put(n, ans);
            return ans;
        }
    }
}

//    932. Beautiful Array
//    Medium
//    For some fixed n, an array nums is beautiful if it is a permutation of the integers 1, 2, ..., n, such that:
//
//    For every i < j, there is no k with i < k < j such that nums[k] * 2 = nums[i] + nums[j].
//
//    Given n, return any beautiful array nums.  (It is guaranteed that one exists.)
//
//
//
//    Example 1:
//
//    Input: n = 4
//    Output: [2,1,4,3]
//    Example 2:
//
//    Input: n = 5
//    Output: [3,1,2,5,4]
//
//
//    Note:
//
//    1 <= n <= 1000