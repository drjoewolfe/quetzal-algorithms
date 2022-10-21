package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ContainsDuplicateII {
    class Solution {
        public boolean containsNearbyDuplicate(int[] nums, int k) {
            if(nums == null || nums.length == 0 || k < 1) {
                return false;
            }

            int i = 0;
            Set<Integer> set = new HashSet<>();
            for(int j = 0; j < nums.length; j++) {
                int a = nums[j];

                if(set.contains(a)) {
                    return true;
                }

                set.add(a);
                if(j - i >= k) {
                    set.remove(nums[i++]);
                }
            }

            return false;
        }
    }

    class Solution_Map_Correct_2 {
        public boolean containsNearbyDuplicate(int[] nums, int k) {
            if(nums == null || nums.length == 0 || k < 1) {
                return false;
            }

            Map<Integer, Integer> lastIndexes = new HashMap<>();
            for(int i = 0; i < nums.length; i++) {
                int a = nums[i];

                if(lastIndexes.containsKey(a)
                        && (i - lastIndexes.get(a)) <= k) {
                    return true;
                }

                lastIndexes.put(a, i);
            }

            return false;
        }
    }

    class Solution_Correct_1 {
        public boolean containsNearbyDuplicate(int[] nums, int k) {
            if(nums == null || nums.length < 2 || k < 1) {
                return false;
            }

            int n = nums.length;
            Map<Integer, Integer> map = new HashMap<>();

            for(int i = 0; i < n; i++) {
                int a = nums[i];
                if(map.containsKey(a)) {
                    int li = map.get(a);
                    if(li + k >= i) {
                        return true;
                    }
                }

                map.put(a, i);
            }

            return false;
        }
    }

    class Solution_Map {
        public boolean containsNearbyDuplicate(int[] nums, int k) {
            if(nums == null || nums.length < 2 || k < 1) {
                return false;
            }

            int n = nums.length;
            Map<Integer, Set<Integer>> map = new HashMap<>();

            for(int i = 0; i < n; i++) {
                int a = nums[i];
                if(!map.containsKey(a)) {
                    map.put(a, new HashSet<>());
                }

                map.get(a).add(i);
            }

            for(var entry : map.entrySet()) {
                var set = entry.getValue();
                for(int b : set) {
                    for(int d = 1; d <= k; d++) {
                        if(set.contains(b + d)) {
                            return true;
                        }
                    }
                }
            }

            return false;
        }
    }

    class Solution_Brute {
        public boolean containsNearbyDuplicate(int[] nums, int k) {
            if(nums == null || nums.length < 2 || k < 1) {
                return false;
            }

            int n = nums.length;
            for(int i = 0; i <= n; i++) {
                for(int j = i + 1; j <= i + k && j < n; j++) {
                    if(nums[i] == nums[j]) {
                        return true;
                    }
                }
            }

            return false;
        }
    }

// [1,2,3,1]
// 3
}

//    219. Contains Duplicate II
//    Easy
//    Given an integer array nums and an integer k, return true if there are two distinct indices i and j in the array such that nums[i] == nums[j] and abs(i - j) <= k.
//
//
//
//    Example 1:
//
//    Input: nums = [1,2,3,1], k = 3
//    Output: true
//    Example 2:
//
//    Input: nums = [1,0,1,1], k = 1
//    Output: true
//    Example 3:
//
//    Input: nums = [1,2,3,1,2,3], k = 2
//    Output: false
//
//
//    Constraints:
//
//    1 <= nums.length <= 105
//    -109 <= nums[i] <= 109
//    0 <= k <= 105