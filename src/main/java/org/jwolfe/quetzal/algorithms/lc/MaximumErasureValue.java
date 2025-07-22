package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MaximumErasureValue {
    class Solution {
        public int maximumUniqueSubarray(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }

            int n = nums.length;
            int left = 0;
            int right = 0;

            int maxScore = 0;
            int score = 0;
            Map<Integer, Integer> map = new HashMap<>();

            while (right < n) {
                System.out.println(left + ", " + right + ", " + score);
                int val = nums[right];
                if (map.containsKey(val)) {
                    int index = map.get(val);
                    for (; left <= index; left++) {
                        int leftVal = nums[left];
                        score -= leftVal;
                        map.remove(leftVal);
                    }
                }

                map.put(val, right);
                score += val;
                maxScore = Math.max(maxScore, score);

                right++;
            }

            return maxScore;
        }
    }

    class Solution_Correct_1 {
        public int maximumUniqueSubarray(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }

            int n = nums.length;

            Set<Integer> set = new HashSet<>();
            set.add(nums[0]);

            int left = 0;
            int right = 0;
            int score = nums[0];
            int maxScore = score;

            while (right < n - 1) {
                right++;
                int a = nums[right];
                while (set.contains(a)) {
                    int b = nums[left];
                    score -= b;
                    set.remove(b);
                    left++;
                }

                set.add(a);
                score += a;
                maxScore = Math.max(maxScore, score);
            }

            return maxScore;
        }
    }

// [4,2,4,5,6]
}

//    1695. Maximum Erasure Value
//    Medium
//    You are given an array of positive integers nums and want to erase a subarray containing unique elements. The score you get by erasing the subarray is equal to the sum of its elements.
//
//    Return the maximum score you can get by erasing exactly one subarray.
//
//    An array b is called to be a subarray of a if it forms a contiguous subsequence of a, that is, if it is equal to a[l],a[l+1],...,a[r] for some (l,r).
//
//
//
//    Example 1:
//
//    Input: nums = [4,2,4,5,6]
//    Output: 17
//    Explanation: The optimal subarray here is [2,4,5,6].
//    Example 2:
//
//    Input: nums = [5,2,1,2,5,2,1,2,5]
//    Output: 8
//    Explanation: The optimal subarray here is [5,2,1] or [1,2,5].
//
//
//    Constraints:
//
//    1 <= nums.length <= 105
//    1 <= nums[i] <= 104
