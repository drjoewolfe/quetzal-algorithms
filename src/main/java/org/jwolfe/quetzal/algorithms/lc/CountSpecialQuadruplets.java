package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;

public class CountSpecialQuadruplets {
    class Solution {
        public int countQuadruplets(int[] nums) {
            if(nums == null || nums.length < 4) {
                return 0;
            }

            int n = nums.length;
            int count = 0;

            int[] seen = new int[101];
            int sdiff = nums[n - 1] - nums[n - 2];
            if(sdiff >= 0) {
                seen[sdiff]++;
            }

            for(int b = n - 3; b >= 1; b--) {
                int bv = nums[b];

                for(int a = b - 1; a >= 0; a--) {
                    int av = nums[a];
                    int sum = av + bv;

                    if(sum <= 100) {
                        count += seen[sum];
                    }
                }

                int c = b;
                int cv = bv;
                for(int d = n - 1; d > c; d--) {
                    int dv = nums[d];

                    int diff = dv - cv;
                    if(diff >= 0) {
                        seen[diff]++;
                    }
                }
            }

            return count;
        }
    }

    class Solution_N2 {
        public int countQuadruplets(int[] nums) {
            if(nums == null || nums.length < 4) {
                return 0;
            }

            int n = nums.length;
            int count = 0;

            Map<Integer, Integer> map = new HashMap<>();
            map.put(nums[n - 1] - nums[n - 2], 1);

            for(int b = n - 3; b >= 1; b--) {
                int bv = nums[b];

                for(int a = b - 1; a >= 0; a--) {
                    int av = nums[a];
                    int sum = av + bv;

                    count += map.getOrDefault(sum, 0);
                }

                int c = b;
                int cv = bv;
                for(int d = n - 1; d > c; d--) {
                    int dv = nums[d];

                    int diff = dv - cv;
                    map.put(diff, map.getOrDefault(diff, 0) + 1);
                }
            }

            return count;
        }
    }

    class Solution_N3_Better {
        public int countQuadruplets(int[] nums) {
            if(nums == null || nums.length < 4) {
                return 0;
            }

            int n = nums.length;
            int count = 0;

            int[] seen = new int[101];
            seen[nums[n - 1]]++;

            for(int c = n - 2; c >= 2; c--) {
                int cv = nums[c];
                for(int b = c - 1; b >= 1; b--) {
                    int bv = nums[b];
                    for(int a = b - 1; a >= 0; a--) {
                        int av = nums[a];

                        int dv = av + bv + cv;
                        if(dv <= 100) {
                            count += seen[dv];
                        }
                    }
                }

                seen[cv]++;
            }

            return count;
        }
    }

    class Solution_N3 {
        public int countQuadruplets(int[] nums) {
            if(nums == null || nums.length < 4) {
                return 0;
            }

            int n = nums.length;
            int count = 0;

            Map<Integer, Integer> map = new HashMap<>();
            map.put(nums[n - 1], 1);

            for(int c = n - 2; c >= 2; c--) {
                int cv = nums[c];
                for(int b = c - 1; b >= 1; b--) {
                    int bv = nums[b];
                    for(int a = b - 1; a >= 0; a--) {
                        int av = nums[a];

                        int dv = av + bv + cv;
                        if(map.containsKey(dv)) {
                            count += map.get(dv);
                        }
                    }
                }

                map.put(cv, map.getOrDefault(cv, 0) + 1);
            }

            return count;
        }
    }

    class Solution_Brute {
        public int countQuadruplets(int[] nums) {
            if(nums == null || nums.length < 4) {
                return 0;
            }

            int n = nums.length;
            int count = 0;
            for(int a = 0; a < n - 3; a++) {
                int av = nums[a];
                for(int b = a + 1; b < n - 2; b++) {
                    int bv = nums[b];
                    for(int c = b + 1; c < n - 1; c++) {
                        int cv = nums[c];
                        for(int d = c + 1; d < n; d++) {
                            int dv = nums[d];

                            if(av + bv + cv == dv) {
                                count++;
                            }
                        }
                    }
                }
            }

            return count;
        }
    }


// [1,2,3,6]
// [3,3,6,4,5]
// [9,6,8,23,39,23]
// [23,39,3,35,40,37]
// [36,13,38,87,2,90,53,62,44,10,100]
}

//    1995. Count Special Quadruplets
//    Easy
//    Given a 0-indexed integer array nums, return the number of distinct quadruplets (a, b, c, d) such that:
//
//    nums[a] + nums[b] + nums[c] == nums[d], and
//    a < b < c < d
//
//
//    Example 1:
//
//    Input: nums = [1,2,3,6]
//    Output: 1
//    Explanation: The only quadruplet that satisfies the requirement is (0, 1, 2, 3) because 1 + 2 + 3 == 6.
//    Example 2:
//
//    Input: nums = [3,3,6,4,5]
//    Output: 0
//    Explanation: There are no such quadruplets in [3,3,6,4,5].
//    Example 3:
//
//    Input: nums = [1,1,1,3,5]
//    Output: 4
//    Explanation: The 4 quadruplets that satisfy the requirement are:
//    - (0, 1, 2, 3): 1 + 1 + 1 == 3
//    - (0, 1, 3, 4): 1 + 1 + 3 == 5
//    - (0, 2, 3, 4): 1 + 1 + 3 == 5
//    - (1, 2, 3, 4): 1 + 1 + 3 == 5
//
//
//    Constraints:
//
//    4 <= nums.length <= 50
//    1 <= nums[i] <= 100