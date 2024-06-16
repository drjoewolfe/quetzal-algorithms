package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PatchingArray {
    class Solution {
        public int minPatches(int[] nums, int n) {
            if (nums == null || nums.length == 0 || n <= 0) {
                return 0;
            }

            long next = 1;
            int patches = 0;
            int i = 0;

            while (next <= n) {
                if (i < nums.length
                        && nums[i] <= next) {
                    // Use current
                    next += nums[i];
                    i++;
                } else {
                    // Patch
                    patches++;
                    next += next;
                }
            }

            return patches;
        }
    }

    class Solution_Correct_1 {
        public int minPatches(int[] nums, int n) {
            if (n <= 0) {
                return 0;
            }

            if (nums == null || nums.length == 0) {
                return n;
            }

            int patches = 0;
            long reach = 0;
            int i = 0;
            int size = nums.length;

            while (reach < n) {
                if (i >= size) {
                    patches++;
                    reach += (reach + 1);
                } else {
                    int val = nums[i];
                    if (val <= (reach + 1)) {
                        reach += val;
                        i++;
                    } else {
                        patches++;
                        reach += (reach + 1);
                    }
                }
            }

            return patches;
        }
    }

    class Solution_Brute {
        public int minPatches(int[] nums, int n) {
            if (n <= 0) {
                return 0;
            }

            if (nums == null || nums.length == 0) {
                return n;
            }

            Set<Integer> sums = new HashSet<>();
            generateSums(nums, 0, new ArrayList<>(), sums);

            int patches = 0;
            for (int val = 1; val <= n; val++) {
                if (sums.contains(val)) {
                    continue;
                }

                patches++;
                List<Integer> current = new ArrayList<>(sums);
                sums.add(val);
                for (int sum : current) {
                    sums.add(sum + val);
                }
            }

            return patches;
        }

        private void generateSums(int[] nums, int index, List<Integer> current, Set<Integer> sums) {
            if (index == nums.length) {
                int sum = 0;
                for (int val : current) {
                    sum += val;
                }

                sums.add(sum);
                return;
            }

            current.add(nums[index]);
            generateSums(nums, index + 1, current, sums);
            current.remove(current.size() - 1);

            generateSums(nums, index + 1, current, sums);
        }
    }

// [1,3]
// 6

// [1,2,31,33]
// 2147483647
}

//    330. Patching Array
//    Hard
//    Given a sorted integer array nums and an integer n, add/patch elements to the array such that any number in the range [1, n] inclusive can be formed by the sum of some elements in the array.
//
//    Return the minimum number of patches required.
//
//
//
//    Example 1:
//
//    Input: nums = [1,3], n = 6
//    Output: 1
//    Explanation:
//    Combinations of nums are [1], [3], [1,3], which form possible sums of: 1, 3, 4.
//    Now if we add/patch 2 to nums, the combinations are: [1], [2], [3], [1,3], [2,3], [1,2,3].
//    Possible sums are 1, 2, 3, 4, 5, 6, which now covers the range [1, 6].
//    So we only need 1 patch.
//    Example 2:
//
//    Input: nums = [1,5,10], n = 20
//    Output: 2
//    Explanation: The two patches can be [2, 4].
//    Example 3:
//
//    Input: nums = [1,2,2], n = 5
//    Output: 0
//
//
//    Constraints:
//
//    1 <= nums.length <= 1000
//    1 <= nums[i] <= 104
//    nums is sorted in ascending order.
//    1 <= n <= 231 - 1
