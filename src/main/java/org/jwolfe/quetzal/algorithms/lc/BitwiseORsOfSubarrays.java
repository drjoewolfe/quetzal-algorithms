package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashSet;
import java.util.Set;

public class BitwiseORsOfSubarrays {
    class Solution {
        public int subarrayBitwiseORs(int[] arr) {
            if (arr == null || arr.length == 0) {
                return 0;
            }

            int n = arr.length;

            Set<Integer> ans = new HashSet<>();
            Set<Integer> prev = new HashSet<>();
            for (int i = 0; i < n; i++) {
                Set<Integer> curr = new HashSet<>();
                int val = arr[i];
                curr.add(val);

                for (int predicate : prev) {
                    curr.add(predicate | val);
                }

                ans.addAll(curr);
                prev = curr;
            }

            return ans.size();
        }
    }
}

//    898. Bitwise ORs of Subarrays
//    Medium
//    Given an integer array arr, return the number of distinct bitwise ORs of all the non-empty subarrays of arr.
//
//    The bitwise OR of a subarray is the bitwise OR of each integer in the subarray. The bitwise OR of a subarray of one integer is that integer.
//
//    A subarray is a contiguous non-empty sequence of elements within an array.
//
//
//
//    Example 1:
//
//    Input: arr = [0]
//    Output: 1
//    Explanation: There is only one possible result: 0.
//    Example 2:
//
//    Input: arr = [1,1,2]
//    Output: 3
//    Explanation: The possible subarrays are [1], [1], [2], [1, 1], [1, 2], [1, 1, 2].
//    These yield the results 1, 1, 2, 1, 3, 3.
//    There are 3 unique values, so the answer is 3.
//    Example 3:
//
//    Input: arr = [1,2,4]
//    Output: 6
//    Explanation: The possible results are 1, 2, 3, 4, 6, and 7.
//
//
//    Constraints:
//
//    1 <= arr.length <= 5 * 104
//    0 <= arr[i] <= 109