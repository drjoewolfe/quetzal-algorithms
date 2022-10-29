package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class DestroySequentialTargets {
    class Solution {
        public int destroyTargets(int[] nums, int space) {
            if(nums == null || nums.length == 0 || space < 1) {
                return 0;
            }

            int n = nums.length;
            Map<Integer, Integer> map = new HashMap<>();
            int maxCount = 0;
            for(int i = 0; i < n; i++) {
                int mod = nums[i] % space;
                map.put(mod, map.getOrDefault(mod, 0) + 1);
                maxCount = Math.max(maxCount, map.get(mod));
            }

            int answer = Integer.MAX_VALUE;
            for(int i = 0; i < n; i++) {
                if(map.get(nums[i] % space) == maxCount) {
                    answer = Math.min(answer, nums[i]);
                }
            }

            return answer;
        }
    }

    class Solution_Correct_1 {
        public int destroyTargets(int[] nums, int space) {
            if(nums == null || nums.length == 0 || space < 1) {
                return 0;
            }

            int n = nums.length;

            Arrays.sort(nums);
            int[] mods = new int[n];
            for(int i = 0; i < n; i++) {
                mods[i] = nums[i] % space;
            }

            Map<Integer, Integer> modCounts = new HashMap<>();
            Map<Integer, Integer> modEarliestIndex = new HashMap<>();
            for(int i = 0; i < n; i++) {
                int mod = mods[i];
                modCounts.put(mod, modCounts.getOrDefault(mod, 0) + 1);
                if(modCounts.get(mod) == 1) {
                    modEarliestIndex.put(mod, i);
                }
            }

            int maxCount = 0;
            int earliestIndex = -1;
            for(var entry : modCounts.entrySet()) {
                Integer mod = entry.getKey();
                Integer count = entry.getValue();

                if(count > maxCount) {
                    maxCount = count;
                    earliestIndex = modEarliestIndex.get(mod);
                } else if(count == maxCount) {
                    earliestIndex = Math.min(earliestIndex, modEarliestIndex.get(mod));
                }
            }

            return nums[earliestIndex];
        }
    }

    class Solution_Brute_TLE {
        public int destroyTargets(int[] nums, int space) {
            if(nums == null || nums.length == 0 || space < 1) {
                return 0;
            }

            TreeMap<Integer, Integer> map = new TreeMap<>();
            for(int a : nums) {
                map.put(a, map.getOrDefault(a, 0) + 1);
            }

            int maxCount = 0;
            int minSeed = -1;
            for(Integer a : map.keySet()) {
                int count = map.get(a);

                Integer last = a;
                while(true) {
                    Integer next = last + space;
                    if(next > map.lastKey()) {
                        break;
                    }

                    if(map.containsKey(next)) {
                        count += map.get(next);
                    }

                    last = next;
                }

                if(count > maxCount) {
                    maxCount = count;
                    minSeed = a;
                }
            }

            return minSeed;
        }
    }

// [3,7,8,1,1,5]
// 2

// [1,5,3,2,2]
// 10000

// [304415643,213512562,667776528,854523075,291102158]
// 4

// [1,3,5,2,4,6]
// 2

// [6,2,5]
// 100
}

//    2453. Destroy Sequential Targets
//    Medium
//    You are given a 0-indexed array nums consisting of positive integers, representing targets on a number line. You are also given an integer space.
//
//    You have a machine which can destroy targets. Seeding the machine with some nums[i] allows it to destroy all targets with values that can be represented as nums[i] + c * space, where c is any non-negative integer. You want to destroy the maximum number of targets in nums.
//
//    Return the minimum value of nums[i] you can seed the machine with to destroy the maximum number of targets.
//
//
//
//    Example 1:
//
//    Input: nums = [3,7,8,1,1,5], space = 2
//    Output: 1
//    Explanation: If we seed the machine with nums[3], then we destroy all targets equal to 1,3,5,7,9,...
//    In this case, we would destroy 5 total targets (all except for nums[2]).
//    It is impossible to destroy more than 5 targets, so we return nums[3].
//    Example 2:
//
//    Input: nums = [1,3,5,2,4,6], space = 2
//    Output: 1
//    Explanation: Seeding the machine with nums[0], or nums[3] destroys 3 targets.
//    It is not possible to destroy more than 3 targets.
//    Since nums[0] is the minimal integer that can destroy 3 targets, we return 1.
//    Example 3:
//
//    Input: nums = [6,2,5], space = 100
//    Output: 2
//    Explanation: Whatever initial seed we select, we can only destroy 1 target. The minimal seed is nums[1].
//
//
//    Constraints:
//
//    1 <= nums.length <= 105
//    1 <= nums[i] <= 109
//    1 <= space <= 109