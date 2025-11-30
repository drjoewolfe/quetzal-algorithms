package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;

public class MakeSumDivisibleByP {
    class Solution {
        public int minSubarray(int[] nums, int p) {
            if (nums == null || nums.length == 0) {
                return 0;
            }

            int n = nums.length;
            int sum = 0;
            for (int i = 0; i < n; i++) {
                sum = (sum + nums[i]) % p;
            }

            int target = sum % p;

            if (target == 0) {
                return 0;
            }

            Map<Integer, Integer> modMap = new HashMap<>();
            modMap.put(0, -1);

            int currentSum = 0;
            int minLength = n;
            for (int i = 0; i < n; i++) {
                int x = nums[i];
                currentSum += x;
                currentSum %= p;

                int requiredMod = (currentSum - target + p) % p;
                if (modMap.containsKey(requiredMod)) {
                    int length = i - modMap.get(requiredMod);
                    minLength = Math.min(minLength, length);
                }

                modMap.put(currentSum, i);
            }

            return minLength == n ? -1 : minLength;
        }
    }

    class Solution_Correct_1 {
        public int minSubarray(int[] nums, int p) {
            if (nums == null || nums.length == 0 || p < 1) {
                return 0;
            }

            int n = nums.length;
            long sum = 0;
            for (int val : nums) {
                sum += val;
            }

            long target = sum % p;
            if (target == 0) {
                return 0;
            }

            HashMap<Long, Integer> modMap = new HashMap<>();
            modMap.put(0L, -1);

            long currentSum = 0;
            int minLength = n;

            for (int i = 0; i < n; i++) {
                currentSum = (currentSum + nums[i]) % p;

                long targetDelta = (currentSum - target + p) % p;
                if (modMap.containsKey(targetDelta)) {
                    minLength = Math.min(minLength, i - modMap.get(targetDelta));
                }

                modMap.put(currentSum, i);
            }

            return (minLength == n) ? -1 : minLength;
        }
    }

    class Solution_TLE {
        public int minSubarray(int[] nums, int p) {
            if (nums == null || nums.length == 0 || p < 1) {
                return 0;
            }

            int n = nums.length;
            long sum = 0;
            for (int i = 1; i < n; i++) {
                sum += nums[i - 1];
            }

            if (sum < p) {
                return -1;
            }

            int rem = (int) (sum % p);
            if (rem == 0) {
                return 0;
            }

            int minLength = Integer.MAX_VALUE;
            Map<Integer, Integer> map = new HashMap<>();
            map.put(0, -1);

            int mod = 0;
            for (int i = 0; i < n; i++) {
                mod += nums[i];
                mod %= p;

                int diff = (mod - rem + p) % p;
                System.out.println(mod + ", " + diff + ", " + map);
                if (map.containsKey(diff)) {
                    minLength = Math.min(minLength, i - map.get(diff));
                }

                map.put(mod, i);
            }

            return minLength == Integer.MAX_VALUE ? -1 : minLength;
        }
    }

    class Solution_Brute {
        public int minSubarray(int[] nums, int p) {
            if (nums == null || nums.length == 0 || p < 1) {
                return 0;
            }

            long sum = 0;
            for (int a : nums) {
                sum += a;
            }

            if (sum < p) {
                return -1;
            }

            int rem = (int) (sum % p);
            if (rem == 0) {
                return 0;
            }

            int minLength = Integer.MAX_VALUE;
            int n = nums.length;
            for (int i = 0; i < n; i++) {
                int subArraySum = 0;
                for (int j = i; j < n; j++) {
                    subArraySum += nums[j];

                    if (subArraySum % p == rem) {
                        int length = j - i + 1;
                        if (length == n) {
                            continue;
                        }

                        minLength = Math.min(minLength, j - i + 1);
                    }
                }
            }

            return minLength == Integer.MAX_VALUE ? -1 : minLength;
        }
    }

// [3,1,4,2]
// 6

// [4,4,2]
// 7

// [1000000000,1000000000,1000000000]
// 3
}

//    1590. Make Sum Divisible by P
//    Medium
//    Given an array of positive integers nums, remove the smallest subarray (possibly empty) such that the sum of the remaining elements is divisible by p. It is not allowed to remove the whole array.
//
//    Return the length of the smallest subarray that you need to remove, or -1 if it's impossible.
//
//    A subarray is defined as a contiguous block of elements in the array.
//
//
//
//    Example 1:
//
//    Input: nums = [3,1,4,2], p = 6
//    Output: 1
//    Explanation: The sum of the elements in nums is 10, which is not divisible by 6. We can remove the subarray [4], and the sum of the remaining elements is 6, which is divisible by 6.
//    Example 2:
//
//    Input: nums = [6,3,5,2], p = 9
//    Output: 2
//    Explanation: We cannot remove a single element to get a sum divisible by 9. The best way is to remove the subarray [5,2], leaving us with [6,3] with sum 9.
//    Example 3:
//
//    Input: nums = [1,2,3], p = 3
//    Output: 0
//    Explanation: Here the sum is 6. which is already divisible by 3. Thus we do not need to remove anything.
//
//
//    Constraints:
//
//    1 <= nums.length <= 105
//    1 <= nums[i] <= 109
//    1 <= p <= 109