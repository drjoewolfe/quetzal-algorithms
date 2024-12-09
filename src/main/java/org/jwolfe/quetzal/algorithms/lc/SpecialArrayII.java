package org.jwolfe.quetzal.algorithms.lc;

public class SpecialArrayII {
    class Solution {
        public boolean[] isArraySpecial(int[] nums, int[][] queries) {
            if(nums == null || nums.length == 0 || queries == null || queries.length == 0) {
                return new boolean[0];
            }

            int n = nums.length;
            int[] violationCounts = new int[n];
            for(int i = 1; i < n; i++) {
                if(nums[i] % 2 == nums[i - 1] % 2) {
                    violationCounts[i] = violationCounts[i - 1] + 1;
                } else {
                    violationCounts[i] = violationCounts[i - 1];
                }
            }

            int m = queries.length;
            boolean[] results = new boolean[m];
            for(int qi = 0; qi < m; qi++) {
                int[] query = queries[qi];

                int from = query[0];
                int to = query[1];

                results[qi] = (violationCounts[to] == violationCounts[from]);
            }

            return results;
        }
    }

    class Solution_TLE {
        public boolean[] isArraySpecial(int[] nums, int[][] queries) {
            if(nums == null || nums.length == 0 || queries == null || queries.length == 0) {
                return new boolean[0];
            }

            int m = queries.length;
            boolean[] results = new boolean[m];
            for(int qi = 0; qi < m; qi++) {
                int[] query = queries[qi];

                int from = query[0];
                int to = query[1];

                boolean isSpecial = true;
                for(int i = from + 1; i <= to; i++) {
                    int prev = nums[i - 1];
                    int curr = nums[i];

                    if(prev % 2 == curr % 2) {
                        isSpecial = false;
                        break;
                    }
                }

                results[qi] = isSpecial;
            }

            return results;
        }
    }
}

//    3152. Special Array II
//    Medium
//    An array is considered special if every pair of its adjacent elements contains two numbers with different parity.
//
//    You are given an array of integer nums and a 2D integer matrix queries, where for queries[i] = [fromi, toi] your task is to check that
//    subarray
//    nums[fromi..toi] is special or not.
//
//    Return an array of booleans answer such that answer[i] is true if nums[fromi..toi] is special.
//
//
//
//    Example 1:
//
//    Input: nums = [3,4,1,2,6], queries = [[0,4]]
//
//    Output: [false]
//
//    Explanation:
//
//    The subarray is [3,4,1,2,6]. 2 and 6 are both even.
//
//    Example 2:
//
//    Input: nums = [4,3,1,6], queries = [[0,2],[2,3]]
//
//    Output: [false,true]
//
//    Explanation:
//
//    The subarray is [4,3,1]. 3 and 1 are both odd. So the answer to this query is false.
//    The subarray is [1,6]. There is only one pair: (1,6) and it contains numbers with different parity. So the answer to this query is true.
//
//
//    Constraints:
//
//    1 <= nums.length <= 105
//    1 <= nums[i] <= 105
//    1 <= queries.length <= 105
//    queries[i].length == 2
//    0 <= queries[i][0] <= queries[i][1] <= nums.length - 1